/**
/**
 * @author Pavel Yaschenkko / Exadel
 */

import flash.external.*;
import flash.geom.Rectangle;
import py.timeline.Interval;
import py.timeline.Styles;
import py.timeline.VerticalRuller;
import TextField.StyleSheet;
import py.components.ScrollArea;
import py.timeline.TimeLineBackground;
import py.timeline.TimeScroller;
import py.timeline.Tooltip;

 class TimeLine extends MovieClip
{
	//public static var EVENT_BG_COLOR:Number = 0x5d628a;
	public static var EVENT_BORDER_COLOR:Number = 0xffffff;
	public static var EVENT_COLOR:Number = 0x777777;
	public static var EVENT_SELECTION_SIZE:Number = 2;
	private var styles:Object;
	private var dataObject:Object;
	private var background:TimeLineBackground;
	private var mainRuller:MovieClip;
	private var zoomRuller:MovieClip;
	private var interval:Interval;
	private var fromDate:Date;
	private var toDate:Date;
	private var viewFromDate:Date;
	private var viewToDate:Date;
	private var prevViewFromDate:Date;
	private var prevViewToDate:Date;	
	private var scroller:MovieClip;
	private var events:MovieClip;
	private var labelLastIndex:Number;
	private var eventTypes:Object = { };
	private var mcEvents:MovieClip;
	private var selectedEventData:Object;
	private var hoveredEvent:MovieClip;
	private var redraw:Boolean = false; //TODO: not used
	private var tooltip:Tooltip;
	private var verticalRuller:VerticalRuller;
	private var eventStyleClass:Object;
	private var zoomCanvas:MovieClip;
	private var verticalRullerDate:Date;
	private var disabler:MovieClip;
	private var visibleItems:Array;
	
	public function TimeLine(params:Object) 
	{
		this.dataObject = params.dataObject["timeline-root"][0];
		//this.useFlashContextMenu = (params.useFlashContextMenu);
		this.styles = new Styles( { text:(this.dataObject.styles ? this.dataObject.styles[0].__TEXT : ""), styleSheet:params.sh }, getDefaultStyles());
		this.eventStyleClass = this.dataObject.data[0].itemtypes ? styles.getStyleObject(this.dataObject.data[0].itemtypes[0].styleclass) : null;
		
		this.processSwimLines(dataObject.data[0].swimlines[0]);
		var range = this.getMinMaxDate(dataObject.data[0].items[0].item);
		this.fromDate = new Date(range.minDate);// this.getDate(dataObject.data[0].intervals[0].fromdate);
		this.toDate = new Date(range.maxDate);// this.getDate(dataObject.data[0].intervals[0].todate);
		this.viewFromDate = fromDate;
		this.viewToDate = new Date(range.maxViewDate);
		
		this.verticalRullerDate = null;
		
		
		// localization
		if (this.dataObject.properties)
		{
			var a:Array = this.dataObject.properties[0].monthnames
			var str:String = a[0].__TEXT;
			if (str) SimpleDateFormatter.MONTH_NAMES = str.split(',');
			a = this.dataObject.properties[0].daynames
			str = a[0].__TEXT;		
			if (str) SimpleDateFormatter.DAY_NAMES = str.split(',');
		}
		// contextMenu for safary on mac
		/*if (this.useFlashContextMenu && this.dataObject.properties && this.dataObject.properties[0].menu) {
			this.contextMenuData = this.dataObject.properties[0].menu[0];
			var myMenu_cm:ContextMenu = new ContextMenu(this.onContextMenu);
			myMenu_cm.hideBuiltInItems();
			_root.menu = myMenu_cm;
		}*/
		
		this.tooltip = new Tooltip(this.styles.getStyleObject("tooltip","tooltip"));
	}
	
	public static function create(parentMovieClip:MovieClip, name:String, x:Number, y:Number, params:Object):TimeLine
	{
		var mc:MovieClip = parentMovieClip.createEmptyMovieClip(name, parentMovieClip.getNextHighestDepth());
		var current_constructor:Function = TimeLine;
		mc.__proto__ = current_constructor.prototype;
		mc._x = x;
		mc._y = y;		
		current_constructor.call(mc, params);
		return TimeLine(mc);
	}
	
	public function onResize(w:Number, h:Number)
	{
		var zwidth:Number = interval.getZWidth();
		//var width:Number = interval.getWidth();
		this.background.onResize(w-TimeScroller.MIN_WIDTH/2, h);
		var pos = this.background.getPositions();
		var f:Object = this.interval.updateInterval(pos.mainRullerWidth, viewFromDate, viewToDate);
		if (f.intervalChanged)
		{
			this.mainRuller.removeMovieClip();
			this.mainRuller = this.createEmptyMovieClip("mcMainRuller", this.getNextHighestDepth());
		}
		this.drawMainRuller(!f.intervalChanged);
		
		var ox:Number = this.zoomRuller.getOffsetX();
		var dateOffset:Number = ox * interval.getLen() / zwidth;
		this.zoomRuller.setSize(this.interval.getZWidth(), pos.zoomHeight, pos.mainRullerWidth, pos.zoomHeight);
		ox = Math.floor(dateOffset * this.interval.getZWidth() / interval.getLen());
		scroller.updateWidth();
		this.zoomRuller.scrollTo(ox, 0, true);
		this["mcCanvasMask"]._width = pos.mainRullerWidth;
		
		this.drawZoomRuller(!f.zoomIntervalChanged);
		
		if (this.disabler) {
			this.disabler._width = w;
			this.disabler._height = h;
		}
	}
	
	public function onDrag(x:Number)
	{
		updateViewDates(x);
		scroller.onDrag(x);
		drawZoomRuller(false);
	}
	public function onDragStart()
	{
		this.prevViewFromDate= viewFromDate;
		this.prevViewToDate = viewToDate;
	}
	public function onDragEnd()
	{
		synhronizeViewRange();
	}
	
	public function onScroll(x:Number)
	{
		var date:Date = offsetToDate(x);
		x = viewDateToOffset(date);
		this.zoomRuller.scrollTo(x, 0, true);
	}	
	
	private function updateViewDates(x:Number)
	{
		viewFromDate = viewOffsetToDate(x);
		viewToDate = new Date(viewFromDate.valueOf()+interval.getZLen()*1000); //trace(viewFromDate.toString()+"####"+viewToDate.toString())
	}
	
	private function processSwimLines(data:Object)
	{
		var sl:Array = data.swimline;
		var obj:Object;
		for (var i = 0; i < sl.length; i++ )
		{
			obj = sl[i];
			obj.index = i;
			data[obj.id] = obj;
		}
	}
	
	/*public function onContextMenu (obj:Object, menu:ContextMenu) {
		var _this = TimeLineComponent.app;
		menu.customItems = new Array();
		if (_this.contextMenuData && !_this.disabler) {
			var pos = _this.background.getPositions();
			if (_this.zoomRuller.hitTest(_this._xmouse, _this._ymouse, true)) {
				
				var date:Date = _this.viewOffsetToDate(_this.zoomRuller._canvas._xmouse+_this.zoomRuller.getOffsetX());
				ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').onContextMenu("+_this._xmouse+","+_this._ymouse+","+date.valueOf()+","+_this.getItemData(_this.hoveredEvent)+")");
				
				var menuItems = (_this.hoveredEvent ? _this.contextMenuData.itemmenu[0].item : _this.contextMenuData.globalmenu[0].item);
				for (var i = 0; i < menuItems.length; i++) {
					var newItem = new ContextMenuItem (menuItems[i].label, _this.onContextMenuItem);
					newItem.action = menuItems[i].jscode;
					menu.customItems.push(newItem);
				}
			}	
		}		
	}
	
	public function onContextMenuItem (obj:Object, menuItem:Object) {
		ExternalInterface.call(menuItem.action);
	}*/
	
	public function run()
	{
		/*for (var k = 0; k < 100; k++) 
		trace('<event id="'+k+'" type="type'+(Math.round(Math.random()*2)+1)+'" date="'+Math.round((Math.random()*(toDate.valueOf()-fromDate.valueOf())+fromDate.valueOf())/60000)*60000+'" swimLineId="1"><tooltip>tooltip1</tooltip></event>');
		for (var k = 100; k < 200; k++)
		trace('<event id="'+k+'" type="type'+(Math.round(Math.random()*2)+1)+'" date="'+Math.round((Math.random()*(toDate.valueOf()-fromDate.valueOf())+fromDate.valueOf())/60000)*60000+'" swimLineId="2"><tooltip>tooltip1</tooltip></event>');
		for (var k = 200; k < 300; k++)
		trace('<event id="'+k+'" type="type'+(Math.round(Math.random()*2)+1)+'" date="'+Math.round((Math.random()*(toDate.valueOf()-fromDate.valueOf())+fromDate.valueOf())/60000)*60000+'" swimLineId="3"><tooltip>tooltip1</tooltip></event>');
		for (var k = 300; k < 400; k++)
		trace('<event id="'+k+'" type="type'+(Math.round(Math.random()*2)+1)+'" date="'+Math.round((Math.random()*(toDate.valueOf()-fromDate.valueOf())+fromDate.valueOf())/60000)*60000+'" swimLineId="4"><tooltip>tooltip1</tooltip></event>');*/
		
		this.background = TimeLineBackground.create(this, "mcBackground", dataObject.data[0].swimlines[0], this.styles);
		var pos = this.background.getPositions();
		
		this.interval = new Interval(dataObject.data[0].intervals[0].interval, pos.mainRullerWidth, fromDate, toDate, viewFromDate, viewToDate);
		// scroller
		this.events = this.createEmptyMovieClip("mcEvents", this.getNextHighestDepth());
		this.scroller =  TimeScroller.create(this, "mcScroller", pos.mainRullerX, pos.scaleY, interval, pos.scaleHeight, 12, 5, null);
		
		// rullers
		this.mainRuller = this.createEmptyMovieClip("mcMainRuller", this.getNextHighestDepth());
		this.zoomRuller = ScrollArea.create(this, "mcZoomRuller", pos.mainRullerX, 0, pos.mainRullerWidth, pos.zoomHeight, 0xff0000, this );
		this.zoomRuller.setCanvasSize(this.interval.getZWidth(), pos.zoomHeight);
		
		this.zoomCanvas = createEmptyMovieClip("mcZoomCanvas", this.getNextHighestDepth());
		this.zoomCanvas._x = pos.mainRullerX;
		var _canvasMask = this.createEmptyMovieClip("mcCanvasMask", this.getNextHighestDepth());
		_canvasMask._x = pos.mainRullerX;
		_canvasMask._drawRect(0,0,pos.mainRullerWidth,pos.zoomHeight,0x80,30);
		this.zoomCanvas.setMask(_canvasMask);
		
		var x:Number = viewDateToOffset(viewFromDate);
		this.zoomRuller.scrollTo(x, 0, false);
		scroller.onDrag(x);
		this.createEventTypes(dataObject.data[0].itemtypes[0].itemtype);
		this.verticalRuller = VerticalRuller.create(this.zoomCanvas, "mcVRuller", 0, 0, pos.swimLinesHeight);
		this.drawMainRuller();
		this.drawZoomRuller();
		
		this.events._x = this.mainRuller._x = pos.mainRullerX;
		this.mainRuller._y = pos.mainRullerY;
		this.events._y = pos.scaleY + TimeLineBackground.SCALE_OFFSET_TOP + 1;
		
		this.addCallbacks();
	}
	
	public function disable(flag:Boolean):Void {
		if (flag) {
			if (!this.disabler) {
				this.disabler = this.createEmptyMovieClip("mcDisabler", this.getNextHighestDepth());
				this.disabler.useHandCursor = false;
				this.disabler.onRelease = function () { ; };
				this.disabler._drawRect(0, 0, Stage.width, Stage.height, 0xffffff, 50);
			}
		} else {
			if (this.disabler) {
				this.disabler.removeMovieClip();
				this.disabler = null;
			}
		}
	}
	
	private function addCallbacks()
	{
		ExternalInterface.addCallback("setVisibleInterval", this, setVisibleInterval);
		ExternalInterface.addCallback("getVisibleInterval", this, getVisibleInterval);
		ExternalInterface.addCallback("zoomIn", this, zoomIn);
		ExternalInterface.addCallback("zoomOut", this, zoomOut);
		ExternalInterface.addCallback("rightClick", this, onRightClick);
		ExternalInterface.addCallback("setVerticalRuller", this, setVerticalRuller);
		ExternalInterface.addCallback("hideVerticalRuller", this, hideVerticalRuller);
		ExternalInterface.addCallback("getVerticalRullerDate", this, getVerticalRullerDate);
		ExternalInterface.addCallback("disable", this, disable);
	}
	
	public function updateViewDateRange(x1:Number, x2:Number)
	{
		this.prevViewFromDate = viewFromDate;
		this.prevViewToDate = viewToDate;
		
		var pos:Object = this.background.getPositions();
		//trace("x1=" + x1 + ",x2=" + x2);
		if (x1!=null)
		{
			viewFromDate = offsetToDate(x1);
		}
		viewToDate = offsetToDate(x2);
		//trace(viewFromDate.toString() + ":" + viewToDate.toString());
		var f:Object = this.interval.updateInterval(pos.mainRullerWidth, viewFromDate, viewToDate);
		
		this.zoomRuller.setSize(this.interval.getZWidth(), pos.zoomHeight, pos.mainRullerWidth, pos.zoomHeight);
		var x:Number = viewDateToOffset(viewFromDate);
		this.zoomRuller.scrollTo(x, 0, false);

		this.drawZoomRuller(!f.zoomIntervalChanged);
		
		synhronizeViewRange();
	}

	public function updateViewDateRange3(len:Number)
	{
		this.prevViewFromDate = viewFromDate;
		this.prevViewToDate = viewToDate;
		
		var pos:Object = this.background.getPositions();
		var dateOffset = Math.floor((this.interval.getZLen() - len) * 1000 / 2);
		viewFromDate = new Date( viewFromDate.valueOf() + dateOffset );
		viewToDate = new Date( viewToDate.valueOf() - dateOffset );

		var f:Object = this.interval.updateInterval(pos.mainRullerWidth, viewFromDate, viewToDate);
		
		this.zoomRuller.setSize(this.interval.getZWidth(), pos.zoomHeight, pos.mainRullerWidth, pos.zoomHeight);
		var x:Number = viewDateToOffset(viewFromDate);
		this.zoomRuller.scrollTo(x, 0, false);

		this.drawZoomRuller(!f.zoomIntervalChanged);
		
		synhronizeViewRange();
	}	
	
	public function updateViewDateRange2()
	{
		var pos:Object = this.background.getPositions();

		var f:Object = this.interval.updateInterval(pos.mainRullerWidth, viewFromDate, viewToDate);
		
		this.zoomRuller.setSize(this.interval.getZWidth(), pos.zoomHeight, pos.mainRullerWidth, pos.zoomHeight);
		trace("updateViewDateRange2: zWidth=" + this.interval.getZWidth() + ", pos.mainRullerWidth=" + pos.mainRullerWidth);
		var x:Number = viewDateToOffset(viewFromDate);
		trace("updateViewDateRange2:  x=" + x);
		this.zoomRuller.scrollTo(x, 0, false);
		this.scroller.clean();
		this.scroller.updateWidth();
		this.scroller.onDrag(x);

		this.drawZoomRuller(!f.zoomIntervalChanged);
	}	
	
	private function getDate(dateStr:String):Date
	{
		var date:Date;
		if (dateStr.indexOf("/") != -1) date = new Date(parseInt(dateStr.substr(0, 4)), parseInt(dateStr.substr(5, 2)) - 1, parseInt(dateStr.substr(8, 2)));
		else date = new Date(parseInt(dateStr));
		return date;
	}
	
	private function createLabel(mc:MovieClip, name:String, str:String, x:Number, y:Number, style:Object):TextField
	{
		var tf:TextFormat = (new TextField.StyleSheet()).transform(style);
		//Styles.traceObject(tf);
		var text:TextField = mc._createTextField (name, str, x , y, tf);
		//text._y = y + Math.floor((style.height - text.textHeight) / 2)-1;
		return text;
	}
	
	private function getDefaultStyles():Object 
	{
		var styles:Object = {
								component: {
									borderColor: "#000000",
									_default: true
								},
								swimLine: {
									height : "20px",
									backgroundColor: "#ebebed",
									color: "#000000",
									fontFamily: "Arial",
									fontSize: "10px",
									fontWeight: "bold",
									_default: true
								},
								interval: {
									color: "#808080",
									fontFamily: "Arial",
									fontSize: "10px",
									fontWeight: "normal",
									_default: true									
								},
								tooltip: {
									backgroundColor: "#FFFFEC",
									borderColor: "#000000",
									borderWidth: "1px",
									textAlign: "left",
									color: "#000000",
									fontFamily: "Arial",
									fontSize: "11px",
									_default: true									
								}
								
							};
		return styles;
	}
	
	private function drawMainRuller(updateFlag:Boolean)
	{
		this.mainRuller.clear();
		var pos:Object= this.background.getPositions();
		var ticks:Array = this.interval.getTicks();
		var x:Number;
		var y:Number;
		//var flag = this.mainRuller.tfLabel0==undefined;
		var h:Number = Math.floor(TimeLineBackground.SCALE_OFFSET_TOP / 1.5);
		for (var i:Number = 0; i < ticks.length; i++)
		{
			x = Math.floor(ticks[i].x);
			y = 0;
			this.mainRuller._drawRect(x-1, y, 3, h, TimeLineBackground.BG_COLOR);
			this.mainRuller._drawVLine(x, y, h - 1, TimeLineBackground.BORDER_COLOR);
			var label:TextField;
			if (updateFlag)
			{
				label = this.mainRuller["tfLabel" + i]
				label._x = x - 1;
			} else {
				label = createLabel(this.mainRuller, "tfLabel" + i, ticks[i].label, x - 1, y - 14, styles.getStyleObject("interval", "interval"));
			}
			
			label._visible = !(label._x + label._width + pos.mainRullerX > Stage.width);
		}
		var e:Array = dataObject.data[0].items[0].item;
		var sl:Object = dataObject.data[0].swimlines[0];
		var mc:MovieClip;
		var ox:Number = Math.floor(TimeLineBackground.SCALE_SWIMLINE_HEIGHT / 2);
		var d:Date;
		/*if (!dataObject.data[0].events[0].isSorted)
		{
			//e.sortOn("date", Array.NUMERIC);
		}*/
		for (var i:Number = 0; i < e.length; i++)
		{
			d = getItemStartDate(e[i]);
			if (d.valueOf() >= fromDate.valueOf() && d.valueOf() <= toDate.valueOf()) {
				if (updateFlag) {
					mc = this.events["mcP" + i];
				} else
				{
					mc = this.events.createEmptyMovieClip("mcP" + i, this.events.getNextHighestDepth());
					mc._drawRect( -ox, -ox, TimeLineBackground.SCALE_SWIMLINE_HEIGHT, TimeLineBackground.SCALE_SWIMLINE_HEIGHT, this.eventStyleClass["borderColor"] || EVENT_BORDER_COLOR);
					mc._drawRect(-ox+1, -ox+1, TimeLineBackground.SCALE_SWIMLINE_HEIGHT-2, TimeLineBackground.SCALE_SWIMLINE_HEIGHT-2, this.eventStyleClass["_color"] || EVENT_COLOR);
				}
				mc._x = dateToOffset(d);
				//--- mc._y = sl[e[i].swimlineid].index * (TimeLineBackground.SCALE_SWIMLINE_HEIGHT+TimeLineBackground.SCALE_SWIMLINE_OFFSET);
				
				//mc._visible = true;
			}
		}
	}
		
	private function drawZoomRuller(updateFlag:Boolean)
	{
		//this.zoomRuller._canvas.clear();
		this.zoomCanvas.clear();
		var pos:Object= this.background.getPositions();
		var x:Number;
		var y:Number;
		var labelIndex = -1;
		var tf:TextField;
		var label:String;
		
		var tickDate:Date = interval.getFirstTickDate(viewFromDate, viewToDate);
		
		while (tickDate)
		{
			labelIndex++;
			x =  viewDateToOffset(tickDate) - this.zoomRuller.getOffsetX(); //trace(tickDate.toString()+" "+viewDateToOffset(tickDate)+" "+this.zoomRuller.getOffsetX()+" "+x);
			y = 0;
			this.zoomCanvas._drawRect(x-1, y, 3, pos.swimLinesHeight, TimeLineBackground.BG_COLOR);
			this.zoomCanvas._drawVLine(x, y, pos.swimLinesHeight, TimeLineBackground.BORDER_COLOR);
			label = interval.getLabel(tickDate);
			tf = this.zoomCanvas["tfLabel" + labelIndex];
			if (tf)
			{
				tf._x = x - 1;
				tf.text = label;
				tf._visible = true;
			} else {
				createLabel(this.zoomCanvas, "tfLabel" + labelIndex, label, x - 1, y + pos.swimLinesHeight, styles.getStyleObject("interval", "interval"));
			}
			
			tickDate = interval.getNextTickDate(tickDate, viewToDate);
		}
		
		//TODO: delete unnessesury labels
		if (this.labelLastIndex > labelIndex)
		{
			for (var i = labelIndex + 1; i <= this.labelLastIndex; i++) 
			{
				this.zoomCanvas["tfLabel" + i]._visible = false;
			}
		}
		
		this.labelLastIndex = labelIndex;
		
		var e:Array = dataObject.data[0].items[0].item;
		var sl:Object = dataObject.data[0].swimlines[0];
		var sd,ed:Date;
		var ey:Number;
		var obj:Object;
		var mc:MovieClip;
		var f:Function = function ()
		{
			this.parent.onEventSelect(this);
		}
		var rover:Function = function ()
		{
			this.parent.onEventOver(this);
		}
		var rout:Function = function ()
		{
			this.parent.onEventOut(this);
		}
		this.visibleItems = new Array();
		for (var i:Number = 0; i < e.length; i++)
		{
			// TODO: optimize e[i]
			sd = getItemStartDate(e[i]);
			ed = getItemEndDate(e[i]);
			//trace(ed+" ***** "+viewFromDate);
			//trace((sd.valueOf() > viewToDate.valueOf() && ed.valueOf() > viewToDate.valueOf()))
			if ((sd.valueOf() < viewFromDate.valueOf() && ed.valueOf() < viewFromDate.valueOf()) || (sd.valueOf() > viewToDate.valueOf() && ed.valueOf() > viewToDate.valueOf())) {
				//delete unnessesury events
				if (!mc.data.selected)
				{
					mc = mcEvents["mcEvent" + e[i].id];
					if (mc) mc.removeMovieClip();
				}
			} else {
				mc  = mcEvents["mcEvent"  + e[i].id];
				if (!mc)
				{
					obj = sl[e[i].swimlineid];
					ey = obj.y+2;
					mc = eventTypes[e[i].type];
					mc  = mc.duplicateMovieClip("mcEvent"  + e[i].id, i * 2 + 10001);
					mc.data = e[i];
					mc._x = viewDateToOffset(sd) - this.zoomRuller.getOffsetX();
					mc._y += ey;
					drawEvent(mc);
					
					mc.useHandCursor = false;
					
					mc.parent = this;
					mc.onRelease = f;
					mc.onReleaseOutside = rout;
					mc.onRollOver = rover;
					mc.onRollOut = rout;
				} else {
					if (updateFlag) drawEvent(mc);
					mc._x = viewDateToOffset(sd) - this.zoomRuller.getOffsetX();	// TODO: not needed???
					//trace(mc._x + " **** "+ sd)
				}
				this.visibleItems.push(mc);
			}
		}
		
		if (this.selectedEventData)
		{
			mc = mcEvents["mcEvent" + this.selectedEventData.id];
			if (mc)
			{
				drawEvent(mc);
			}
		}
		updateVerticalRuller();
	}
	
	private function getItemStartDate(item:Object):Date {
		return (item.startdate) ? new Date(parseInt(item.startdate)) : fromDate;
	}
	
	private function getItemEndDate(item:Object):Date {
		return (item.enddate) ? new Date(parseInt(item.enddate)) : toDate;
	}
	
	private function getItemStartDateNumber(item:Object):Number {
		return (item.startdate) ?parseInt(item.startdate) : fromDate.valueOf();
	}
	
	private function getItemEndDateNumber(item:Object):Number {
		return (item.enddate) ? parseInt(item.enddate) : toDate.valueOf();
	}
	
	public function onEventSelect(event:MovieClip)
	{
		if (event.data.selected) return;
		
		if (this.selectedEventData)
		{
			if (this.selectedEventData == event.data) return;
			this.selectedEventData.selected = false;
			drawEvent(mcEvents["mcEvent" + this.selectedEventData.id]);
		}
		event.data.selected = true;
		this.selectedEventData = event.data;
		drawEvent(event);
	}

	public function onEventOver(event:MovieClip)
	{
		this.tooltip.showTip(getTooltipText(event.data));
		if (event.data.hover) return;
		
		event.data.hover = true;
		this.hoveredEvent = event;
		if (!event.data.selected) drawEvent(event);
	}
	public function onEventOut(event:MovieClip)
	{
		this.hoveredEvent = null;
		event.data.hover = false;		
		drawEvent(event);
		
		this.tooltip.removeTip();
	}
	private function truncateIfNeed(lineLength:Number, text:String):String {
		var toolTipText:String = text;
		if ((lineLength > 0) && (toolTipText!=undefined ) && (toolTipText.length >= lineLength)) {
			toolTipText = toolTipText.substring(0, lineLength);
			toolTipText += "...";
		}
		return toolTipText;
	}
	// tooltip renderer
	private function getTooltipText(data:Object)
	{
		return (data.tooltip[0].__HTML ? data.tooltip[0].__HTML : data.tooltip[0].__TEXT);
	}
	
	private function drawEvent(mc:MovieClip)
	{
		var eventBorder:Number = 0;	
		var w,h:Number;
		var style = mc.data.selected ? eventTypes[mc.data.type].selectedStyle : mc.data.hover ? eventTypes[mc.data.type].hoverStyle : mc.data.highlighted ? eventTypes[mc.data.type].highlightStyle : eventTypes[mc.data.type].style;
		var sl:Object = dataObject.data[0].swimlines[0];
		
		mc.clear();
		
		w = getItemWidth(mc.data);
		h = sl[mc.data.swimlineid].height-2;

		if (style.backgroundColor != undefined)
		{
			mc._drawRect( 0, 0, w, h, style.backgroundColor);
			eventBorder = EVENT_SELECTION_SIZE;
		}

		if (style.borderWidth && style.borderWidth > 0) {
			mc._drawRect( eventBorder, eventBorder, w-2*eventBorder, h-2*eventBorder, style.borderColor);
			mc._drawRect( style.borderWidth+eventBorder, style.borderWidth+eventBorder, w - 2*(style.borderWidth+eventBorder), h - 2*(style.borderWidth+eventBorder), style._color);
		} else {
			mc._drawRect( eventBorder, eventBorder, w-2*eventBorder, h-2*eventBorder, style._color);
		}
		if (mc.data.startdate) {
			var minWidth = eventBorder * 2 + style.borderWidth * 2 + 6 + 5;
			if (w>=minWidth) mc._drawTriangle( eventBorder + style.borderWidth + 4, (h - 8) / 2, 5, 8, EVENT_BORDER_COLOR);
		} else {
			mc._drawHLine( style.borderWidth + 6, (h - 2) / 2, 7, style.borderColor);
		}
		if (!mc.data.enddate) {
			mc._drawHLine( w - 2 * (style.borderWidth) - 5 - 5, (h - 2) / 2, 7, style.borderColor);
			mc._drawVLine( w - 2 * (style.borderWidth) - 5 - 2, (h - 8) / 2, 7, style.borderColor);
		}
	}
	
	private function createEventTypes(et:Object)
	{
		var mc:MovieClip;
		var dx:Number;
		var dy:Number;
		var style:Object;
		var eventType:Object;
		
		mcEvents = this.zoomCanvas.createEmptyMovieClip("mcEvents", getNextHighestDepth());
		
		for (var i:Number = 0; i < et.length; i++ )
		{
			eventType = et[i];
			mc   = mcEvents.createEmptyMovieClip("mcE" + eventType.id, i);
			eventTypes[eventType.id] = mc;
			mc.id = eventType.id; ///???
			
			mc.style = styles.getStyleObject(eventType.styleclass);
			mc.selectedStyle = styles.getStyleObject(eventType.selectedstyleclass);
			if (!mc.selectedStyle) mc.selectedStyle = mc.style;
			mc.hoverStyle = styles.getStyleObject(eventType.hoverstyleclass);
			if (!mc.hoverStyle) mc.hoverStyle = mc.style;
			mc.highlightStyle = styles.getStyleObject(eventType.highlightstyleclass);
			if (!mc.highlightStyle) mc.highlightStyle = mc.style;

			mc._visible = false;
		}
	}
	
	private function getMinMaxDate(items:Object) {
		var range = { minDate:null, maxDate:null }
		var d:Number;
		for (var i = 0; i < items.length; i++) {
			d = parseInt(items[i].startdate);
			if (!isNaN(d) && (range.minDate == null || d < range.minDate)) {
				range.minDate = d;
			}
			d = parseInt(items[i].enddate);
			if (!isNaN(d) && (range.maxDate == null || d > range.maxDate)) {
				range.maxDate = d;
			}
		}
		
		var margin = Math.round((range.maxDate - range.minDate) * 0.05);
		var date:Date;
		if (range.minDate == null) {
			date = new Date();
			date.setMonth(date.getMonth() - 6);
			range.minDate = date.valueOf();
		} else {
			range.minDate -= margin;
		}
		
		if (range.maxDate == null) {
			date = new Date();
			date.setMonth(date.getMonth() + 6);
			range.maxDate = date.valueOf();
		} else {
			range.maxDate += margin;
		}
		
		range.maxViewDate = range.minDate + Math.round((range.maxDate - range.minDate) * 0.25);
		return range;
	}
	
	private function getItemWidth(itemData):Number {
		var sd:Date = getItemStartDate(itemData);
		var ed:Date = getItemEndDate(itemData);
		
		/*if (sd.valueOf() < viewFromDate.valueOf()) {
			sd = viewFromDate;
		}*/
		/*if (ed.valueOf() > viewToDate.valueOf()) {
			ed = viewToDate;
		}*/

		return viewDateToOffset(ed) - viewDateToOffset(sd);
	}
	
	private function dateToOffset(date:Date)
	{
		return Math.floor((date.valueOf()-fromDate.valueOf()) / 1000 * interval.getWidth() / interval.getLen());
	}
	private function offsetToDate(offset:Number)
	{
		var result:Date;
		var ms:Number;
		var tms:Number;
		if (offset <= 0) 
		{
			result = new Date(fromDate.valueOf());
		} else {
			ms = Math.floor(offset * interval.getLen() / interval.getWidth() * 1000) + fromDate.valueOf();
			tms = toDate.valueOf();
			result = ms > tms ? new Date(tms) : new Date(ms);
		}
		return result;
	}
	private function viewDateToOffset(date:Date)
	{
		return Math.round((date.valueOf()-fromDate.valueOf()) * interval.getZWidth() / (interval.getLen() * 1000) );
	}
	private function viewOffsetToDate(offset:Number)
	{
		return new Date(Math.round(offset * interval.getLen() / interval.getZWidth() * 1000) + fromDate.valueOf());
	}
	
	//TODO: getDate returns Date Object, we will just do that this.viewFromDate = this.getDate(fromDateString);
	public function setVisibleInterval(fromDateNumber:Number, toDateNumber:Number):Void {
		this.prevViewFromDate = viewFromDate;
		this.prevViewToDate = viewToDate;
		
		if (!fromDateNumber || fromDateNumber < this.fromDate.valueOf() || fromDateNumber >= this.toDate.valueOf()){
			this.viewFromDate = new Date (this.fromDate.valueOf()); 
		} else {
			this.viewFromDate = new Date (fromDateNumber); 
		}
		if (!toDateNumber || toDateNumber <= this.fromDate.valueOf() || toDateNumber > this.toDate.valueOf()){
			this.viewToDate = new Date (this.toDate.valueOf());
		} else {
			this.viewToDate = new Date (toDateNumber);
		}
		updateViewDateRange2();
		
		synhronizeViewRange();
	}
	
	public function getVisibleInterval():Object {
		return {fromDateNumber: this.viewFromDate.valueOf(), toDateNumber: this.viewToDate.valueOf()};
	}	
	
	/*private function updateEventVisibility():Void {
		var e:Array = dataObject.data[0].items[0].item;
		var mc:MovieClip;
		var sd,ed:Number;
		for (var i:Number = 0; i < e.length; i++)
		{
			mc = this.events["mcP" + i];
			sd = parseInt(e[i].startdate);
			ed = parseInt(e[i].enddate);
			trace("event: " + (new Date(sd)) + " *** " + (new Date(ed)));
			if ((sd < fromDate.valueOf() && ed < toDate.valueOf()) ||  (sd > fromDate.valueOf() && ed > toDate.valueOf())) {
				mc._visible = false;
			} else {
				mc._visible = true;
			}
		} 		
	}*/
	
	/*public function getVisibleEventsNumber(fromDateNumber:Number, toDateNumber:Number):Number {
		var e:Array = dataObject.data[0].events[0].event;
		var d:Number;
		var res:Number = 0;
		for (var i:Number = 0; i < e.length; i++)
		{
			d = parseInt(e[i].date);
			if (d >= fromDateNumber && d <= toDateNumber && eventTypesVisibility[e[i].type])
			{
				res ++;
			}
		}
		return res;
	}*/
	
	public function zoomIn(coeff:Number):Void {
           scroller.zoomIn(coeff);
	}	

	public function zoomOut(coeff:Number):Void {
		scroller.zoomOut(coeff);
	}
	
	public function onRightClick() {
		var pos = this.background.getPositions();
		if (this.zoomRuller.hitTest(this._xmouse, this._ymouse, true)) {
			var date:Date = viewOffsetToDate(this.zoomRuller._canvas._xmouse+this.zoomRuller.getOffsetX());
			ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').onContextMenu("+this._xmouse+","+this._ymouse+","+date.valueOf()+","+getItemData(this.hoveredEvent)+")");
		}
	}
	
	public function setVerticalRuller(dateNumber:Number)
	{
		if (dateNumber && dateNumber >= this.fromDate.valueOf() && dateNumber <= this.toDate.valueOf())
		{
			this.verticalRullerDate = new Date(dateNumber);
			this.updateVerticalRuller();
		} else {
			this.verticalRullerDate = null;
			this.hideVerticalRuller();
		}
		this.updateVisibleItems();
	}
	
	public function hideVerticalRuller()
	{
		this.verticalRullerDate = null;
		this.verticalRuller.hide();
		this.updateVisibleItems();
	}
	
	public function getVerticalRullerDate()
	{
		return verticalRullerDate;
	}
	
	private function updateVerticalRuller()
	{
		if (verticalRullerDate && verticalRullerDate >= this.viewFromDate && verticalRullerDate <= this.viewToDate)
		{
			this.verticalRuller.setPosition(viewDateToOffset(verticalRullerDate) - this.zoomRuller.getOffsetX());
		} else {
			this.verticalRuller.hide();
		}
	}
	
	private function updateVisibleItems() {
		//ExternalInterface.call("FlashTimeline.ASTrace('highlighted')");
		var flag = this.verticalRullerDate!=null;
		var item:MovieClip;
		for (var i = 0; i < this.visibleItems.length; i++) {
			item = this.visibleItems[i];
			if (flag && getItemStartDateNumber(item.data) <= this.verticalRullerDate.valueOf() && getItemEndDateNumber(item.data) >= this.verticalRullerDate.valueOf()) {
				item.data.highlighted = true;
			} else {
				item.data.highlighted = false;
			}

			drawEvent(item);
		}
	}
	
	private function getItemData(event:MovieClip)
	{
		if (!event) return "null";
		return "{id:'" + event.data.id + "', type:'" + event.data.type + "', startDate:" + event.data.startdate + ", endDate:" + event.data.enddate + ", swimlineId:'" + event.data.swimlineid + "', isSelected:" + (event.data.selected==true)+"}";
	}
	
	public function selectEvent(id:String)
	{
		//TODO: nothing happend if event mc wasn't created 
		var mc = mcEvents["mcEvent" + id];
		if (mc)
		{
			onEventSelect(mc);
		}
	}
	
	private function synhronizeViewRange()
	{
		if (prevViewFromDate.valueOf() != viewFromDate.valueOf() || prevViewToDate.valueOf() != viewToDate.valueOf())
		{
			ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').onDateRangeChanged("+viewFromDate.valueOf()+","+viewToDate.valueOf()+")");
		}
	}
}
