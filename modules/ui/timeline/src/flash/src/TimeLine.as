/**
/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
import flash.external.*;
import flash.geom.Rectangle;
import flash.filters.BlurFilter;
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
	private var eventTypesVisibility:Object = {};
	private var mcEvents:MovieClip;
	private var selectedEventData:Object;
	private var hoveredEvent:MovieClip;
	private var redraw:Boolean = false; //TODO: not used
	private var tooltip:Tooltip;
	private var verticalRuller:VerticalRuller;
	private var hoverGroupStyleClass:Object;
	private var selectGroupStyleClass:Object;
	private var groupStyleClass:Object;
	private var eventStyleClass:Object;
	private var zoomCanvas:MovieClip;
	private var verticalRullerDate:Date;
	private var groupInterval:Number;
	private var contextMenuData:Object;
	private var useFlashContextMenu:Boolean;
	private var disabler:MovieClip;
	
	public function TimeLine(params:Object) 
	{
		this.dataObject = params.dataObject["timeline-root"][0];
		this.useFlashContextMenu = (params.useFlashContextMenu);
		this.styles = new Styles( { text:(this.dataObject.styles ? this.dataObject.styles[0].__TEXT : ""), styleSheet:params.sh }, getDefaultStyles());
		this.groupStyleClass = this.dataObject.data[0].eventgroup ? styles.getStyleObject(this.dataObject.data[0].eventgroup[0].styleclass) : null;
		this.selectGroupStyleClass = this.dataObject.data[0].eventgroup ? styles.getStyleObject(this.dataObject.data[0].eventgroup[0].selectedstyleclass) : null;
		this.hoverGroupStyleClass = this.dataObject.data[0].eventgroup ? styles.getStyleObject(this.dataObject.data[0].eventgroup[0].hoverstyleclass) : null;
		this.eventStyleClass = this.dataObject.data[0].eventtypes ? styles.getStyleObject(this.dataObject.data[0].eventtypes[0].styleclass) : null;
		
		var groupIntervalAsObj:Object   = this.dataObject.data[0].eventgroup ? this.dataObject.data[0].eventgroup[0].eventinterval : 0;
		if (groupIntervalAsObj != undefined) {
			if (groupIntervalAsObj.toString().indexOf("px") > 1) {
				this.groupInterval = parseInt(groupIntervalAsObj.toString().substring(0, groupIntervalAsObj.toString().length - 2));
			}else {
				this.groupInterval = parseInt(groupIntervalAsObj.toString());
			}
		}
		
		this.processSwimLines(dataObject.data[0].swimlines[0]);
		this.fromDate = this.getDate(dataObject.data[0].intervals[0].fromdate);
		this.toDate = this.getDate(dataObject.data[0].intervals[0].todate);
		this.viewFromDate = this.getDate(dataObject.data[0].intervals[0].viewfromdate);
		this.viewToDate = this.getDate(dataObject.data[0].intervals[0].viewtodate);
		
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
		if (this.useFlashContextMenu && this.dataObject.properties && this.dataObject.properties[0].menu) {
			this.contextMenuData = this.dataObject.properties[0].menu[0];
			var myMenu_cm:ContextMenu = new ContextMenu(this.onContextMenu);
			myMenu_cm.hideBuiltInItems();
			_root.menu = myMenu_cm;
		}
		
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
	
	public function onContextMenu (obj:Object, menu:ContextMenu) {
		var _this = TimeLineComponent.app;
		menu.customItems = new Array();
		if (_this.contextMenuData && !_this.disabler) {
			var pos = _this.background.getPositions();
			if (_this.zoomRuller.hitTest(_this._xmouse, _this._ymouse, true)) {
				
				var date:Date = _this.viewOffsetToDate(_this.zoomRuller._canvas._xmouse+_this.zoomRuller.getOffsetX());
				ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').onContextMenu("+_this._xmouse+","+_this._ymouse+","+date.valueOf()+","+_this.getEventsData(_this.hoveredEvent)+")");
				
				var menuItems = (_this.hoveredEvent ? _this.contextMenuData.eventmenu[0].item : _this.contextMenuData.globalmenu[0].item);
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
	}
	
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
		
		this.verticalRuller = VerticalRuller.create(this.zoomCanvas, "mcVRuller", 0, 0, pos.swimLinesHeight);
		var x:Number = viewDateToOffset(viewFromDate);
		this.zoomRuller.scrollTo(x, 0, false);
		scroller.onDrag(x);
		this.createEventTypes(dataObject.data[0].eventtypes[0].eventtype);
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
				this.disabler._drawRect(0, 0, Stage.width, Stage.height, 0xff0000, 0);
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
		ExternalInterface.addCallback("showEventType", this, showEventType);
		ExternalInterface.addCallback("hideEventType", this, hideEventType);
		ExternalInterface.addCallback("switchEventTypes", this, switchEventTypes);
		ExternalInterface.addCallback("setVisibleInterval", this, setVisibleInterval);
		ExternalInterface.addCallback("getVisibleInterval", this, getVisibleInterval);
		ExternalInterface.addCallback("getVisibleEventsNumber", this, getVisibleEventsNumber);
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
		checkGroupAll();
		this.drawZoomRuller(!f.zoomIntervalChanged);
		
		synhronizeViewRange();
	}

	public function updateViewDateRange3(len:Number)
	{
		this.prevViewFromDate = viewFromDate;
		this.prevViewToDate = viewToDate;
		
		var pos:Object = this.background.getPositions();
		var dateOffset = Math.floor((this.interval.getZLen() - len)*1000 / 2);
		viewFromDate = new Date( viewFromDate.valueOf() + dateOffset );
		viewToDate = new Date( viewToDate.valueOf() - dateOffset );

		var f:Object = this.interval.updateInterval(pos.mainRullerWidth, viewFromDate, viewToDate);
		
		this.zoomRuller.setSize(this.interval.getZWidth(), pos.zoomHeight, pos.mainRullerWidth, pos.zoomHeight);
		var x:Number = viewDateToOffset(viewFromDate);
		this.zoomRuller.scrollTo(x, 0, false);
		checkGroupAll();
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
		checkGroupAll();
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
									height : "30px",
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
		clearGroups();
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
		var e:Array = dataObject.data[0].events[0].event;
		var sl:Object = dataObject.data[0].swimlines[0];
		var mc:MovieClip;
		var ox:Number = Math.floor(TimeLineBackground.SCALE_SWIMLINE_HEIGHT / 2);
		var d:Number;
		/*if (!dataObject.data[0].events[0].isSorted)
		{
			//e.sortOn("date", Array.NUMERIC);
		}*/
		for (var i:Number = 0; i < e.length; i++)
		{
			d = parseInt(e[i].date);
			if (d >= fromDate.valueOf() && d <= toDate.valueOf()) {
				if (updateFlag) {
					mc = this.events["mcP" + i];
				} else
				{
					mc = this.events.createEmptyMovieClip("mcP" + i, this.events.getNextHighestDepth());
					mc._drawRect( -ox, -ox, TimeLineBackground.SCALE_SWIMLINE_HEIGHT, TimeLineBackground.SCALE_SWIMLINE_HEIGHT, this.eventStyleClass["borderColor"] || EVENT_BORDER_COLOR);
					mc._drawRect(-ox+1, -ox+1, TimeLineBackground.SCALE_SWIMLINE_HEIGHT-2, TimeLineBackground.SCALE_SWIMLINE_HEIGHT-2, this.eventStyleClass["_color"] || EVENT_COLOR);
				}
				mc._x = dateToOffset(new Date(d));
				//--- mc._y = sl[e[i].swimlineid].index * (TimeLineBackground.SCALE_SWIMLINE_HEIGHT+TimeLineBackground.SCALE_SWIMLINE_OFFSET);
				
				if (!eventTypesVisibility[e[i].type]){
					mc._visible = false;
				} else {
					mc._visible = true;
				}
				this.checkGroup(e, i);
			}
		}
	}
	
	private function clearGroups()
	{
		var e:Array = dataObject.data[0].events[0].event;
		var event:Object;
		for (var i:Number = 0; i < e.length; i++)
		{
			event = e[i];
			event.group = null;
			event.groupParent = null;
			event.groupParams = null;
		}		
	}
	
	private function checkGroupAll()
	{
		clearGroups();
		var e:Array = dataObject.data[0].events[0].event;
		var event:Object;
		var d:Number;
		for (var i:Number = 0; i < e.length; i++)
		{
			event = e[i];
			d = parseInt(event.date);
			if (d >= fromDate.valueOf() && d <= toDate.valueOf()) {
				this.checkGroup(e, i);
			}
		}
	}
	
	private function checkGroup(ev:Array, i:Number)
	{
		var e:Object = ev[i];
		if (!eventTypesVisibility[e.type] || e.groupParent) return;
		e.group = [];
		e.groupParams = {};
		var ee:Object;
		var w:Number = Math.floor(eventTypes[e.type].style.width / 2);
		var ww1:Number = w;
		var xx1:Number = viewDateToOffset(new Date(parseInt(e.date)));
		var x:Number = xx1;
		var xx2:Number;
		var ww2:Number;
		var counter = i;
		while (counter < ev.length-1)
		{
			counter++;
			ee = ev[counter];
			if (eventTypesVisibility[ee.type] && e.swimlineid == ee.swimlineid)
			{
				ww2 = Math.floor(eventTypes[ee.type].style.width / 2);  // 7 because width = 14pxx
				xx2 = viewDateToOffset(new Date(parseInt(ee.date)));    // interval in pixels
				ww2 = ww2 + this.groupInterval;// trace(xx1 + " " + ww1 +" "+ xx2 +" "+ ww2);
				
				if (xx1 + ww1+1> xx2 - ww2)
				{
					e.group.push(ee);;
					e.groupParams.width = xx2 - x + ww2;
					ee.groupParent = e;
					ww1 = ww2;
					xx1 = xx2;
					if (ee.selected) 
					{
						ee.selected = false;
						e.selected = true;
						this.selectedEventData = e;
					}
				} else {
					ee.groupParent = null;
					break;
				}
			}
		}
		if (e.group.length == 0) e.group = null;
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
		
		var e:Array = dataObject.data[0].events[0].event;
		var sl:Object = dataObject.data[0].swimlines[0];
		var d:Number;
		var dd:Number;
		var ey:Number;
		var obj:Object;
		var mc:MovieClip;
		var mcF:MovieClip;
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
		
		for (var i:Number = 0; i < e.length; i++)
		{
			// TODO: optimize e[i]
			dd = parseInt(e[i].date);
			d = (e[i].group ? parseInt(e[i].group[e[i].group.length-1].date) : dd);
			if (d >= viewFromDate.valueOf() && dd <= viewToDate.valueOf() && eventTypesVisibility[e[i].type])
			{	
				mc  = mcEvents["mcEvent"  + e[i].id];
				mcF = mcEvents["FmcEvent" + e[i].id];				
				if (!mc && !e[i].groupParent)
				{
					obj = sl[e[i].swimlineid];
					ey = obj.y + Math.floor( obj.height / 2);
					mc = eventTypes[e[i].type];
					mcF = mc.duplicateMovieClip("FmcEvent" + e[i].id, i*2+10000);
					mc  = mc.duplicateMovieClip("mcEvent"  + e[i].id, i*2+10001);
					mc.data = e[i];
					mc._x = mcF._x = viewDateToOffset(new Date(dd))-this.zoomRuller.getOffsetX();
					mc._y = mcF._y += ey;
					drawGroupEvent(mc);
					
					mc.useHandCursor = mcF.useHandCursor =false;
					
					mc.parent = this;
					mc.onRelease = f;
					mc.onReleaseOutside = rout;
					mc.onRollOver = rover;
					mc.onRollOut = rout;
				} else {
					if (mc.data.groupParent) {
						//delete group events
						mc.removeMovieClip();
						mcF.removeMovieClip();
					} else {
						drawGroupEvent(mc);
						mc._x = mcF._x = viewDateToOffset(new Date(dd))-this.zoomRuller.getOffsetX();	// TODO: not needed???
					}
				}
			} else {
				//delete unnessesury events
				if (!mc.data.selected)
				{
					mc = mcEvents["mcEvent" + e[i].id];
					if (mc) mc.removeMovieClip();
					mcF = mcEvents["FmcEvent" + e[i].id];
					if (mcF) mcF.removeMovieClip();
				}
			}
		}
		if (this.selectedEventData)
		{
			mc = mcEvents["mcEvent" + this.selectedEventData.id];
			if (mc)
			{
				drawGroupEvent(mc);
			}
		}
		updateVerticalRuller();
	}
	
	private function drawGroupEvent(event:MovieClip)
	{
		if (event.data.group)
		{
			if (event.data.selected)
			{
				drawEvent(event, this.selectGroupStyleClass ? this.selectGroupStyleClass : eventTypes[event.data.type].selectedStyle, true);
			} else if (event.data.hover) {
				drawEvent(event, this.hoverGroupStyleClass ? this.hoverGroupStyleClass : eventTypes[event.data.type].hoverStyle, true);
			} else {
				drawEvent(event, this.groupStyleClass ? this.groupStyleClass : eventTypes[event.data.type].style, true);
			}
		} else {
			drawEvent(event, event.data.selected ? eventTypes[event.data.type].selectedStyle : event.data.hover ? eventTypes[event.data.type].hoverStyle : eventTypes[event.data.type].style);
		}		
	}
	
	public function onEventSelect(event:MovieClip)
	{
		if (event.data.selected) return;
		
		if (this.selectedEventData)
		{
			if (this.selectedEventData == event.data) return;
			this.selectedEventData.selected = false;
			drawGroupEvent(mcEvents["mcEvent" + this.selectedEventData.id]);
		}
		event.data.selected = true;
		this.selectedEventData = event.data;
		drawGroupEvent(event);
	}

	public function onEventOver(event:MovieClip)
	{
		this.tooltip.showTip(getTooltipText(event.data));
		if (event.data.groupParent) event = mcEvents["mcEvent" + event.data.groupParent.id];
		if (event.data.hover) return;
		
		event.data.hover = true;
		this.hoveredEvent = event;
		if (!event.data.selected) drawGroupEvent(event);
	}
	public function onEventOut(event:MovieClip)
	{
		if (event.data.groupParent)
		{
			event = mcEvents["mcEvent" + event.data.groupParent.id];
			if (event.hitTest(_root._xmouse, _root._ymouse, true)) return
		}

		this.hoveredEvent = null;
		event.data.hover = false;		
		drawGroupEvent(event);
		
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
	// use group template for group of events
	private function getTooltipText(data:Object)
	{
		var template:Object = dataObject.data[0].eventgroup[0].tooltiptemplate[0];
		var maxToolTipCount:Number = dataObject.data[0].eventgroup[0].tooltiptemplate[0].maxdisplayed;
		var lineLength:Number = this.tooltip.getTooltipLineLength();

		if (maxToolTipCount == null || maxToolTipCount == undefined) {
			maxToolTipCount = 0;
		}
		if (data.group)
		{
			if (template) {
				var header:String = template.header ? template.header[0].__HTML ? template.header[0].__HTML : template.header[0].__TEXT : "";
				var content:String = template.content ? template.content[0].__HTML ? template.content[0].__HTML : template.content[0].__TEXT : "";
				var footer:String = template.footer ? template.footer[0].__HTML ? template.footer[0].__HTML : template.footer[0].__TEXT : "";
				var footerIfBreak:String = template.footerifbreak ? template.footerifbreak[0].__HTML ? template.footerifbreak[0].__HTML : template.footerifbreak[0].__TEXT : "";
				//trace("BreakFooter :  " + footerIfBreak);
				var result:String = "";
				result += header.split("{#count}").join(data.group.length + 1);
				var firstText:String = data.tooltip[0].__HTML ? data.tooltip[0].__HTML : data.tooltip[0].__TEXT;
				result += content.split("{#tooltip}").join(this.truncateIfNeed(lineLength,firstText));
				//trace("Max count = " + maxToolTipCount);
				for (var i:Number = 0; i < data.group.length; i++ )
				{
					if ((maxToolTipCount!=0) &&  (( i + 1) == maxToolTipCount )) {
						result += content.split("{#tooltip}").join(footerIfBreak);
						break;
					}
					var toolTipText:String = (data.group[i].tooltip[0].__HTML ? data.group[i].tooltip[0].__HTML : data.group[i].tooltip[0].__TEXT);
				
					result += content.split("{#tooltip}").join(this.truncateIfNeed(lineLength,toolTipText));
				
				}
				result += footer.split("{#count}").join(data.group.length + 1);
				return result;
				
			} else {
				var result:String = "Group of Events (" + (data.group.length+1) + ")<br/>"+(data.tooltip[0].__HTML ? data.tooltip[0].__HTML : data.tooltip[0].__TEXT);
				for (var i:Number = 0; i < data.group.length; i++ )
				{
					result += "<br/>" + (data.group[i].tooltip[0].__HTML ? data.group[i].tooltip[0].__HTML : data.group[i].tooltip[0].__TEXT);
				}
				return result;
			}			
		}
		
		return (data.tooltip[0].__HTML ? data.tooltip[0].__HTML : data.tooltip[0].__TEXT);
	}
	
	private function drawEvent(mc:MovieClip, style:Object, isGroup:Boolean)
	{
			var dx:Number;
			var dy:Number;
			var fdx:Number;
			var fdy:Number;
			var falpha:Number;
			var eventBorder:Number = 0;	
			var mcFilter:MovieClip;
			var w:Number;
			
			mcFilter = mcEvents["F"+mc._name];
			
			mc.clear();
			mcFilter.clear();
			mcFilter._x = mc._x;
			mcFilter._y = mc._y;
			
			dx = -Math.floor(style.width / 2);
			dy = -Math.floor(style.height / 2);
			
			if (isGroup)
			{
				w = mc.data.groupParams.width - dx; //TODO: + (delta between old and new width)/2 
			} else {
				w = style.width;
			}

			fdx = (style.filter.blurDx != undefined?Number(style.filter.blurDx):3);
			fdy = (style.filter.blurDy != undefined?Number(style.filter.blurDy):3);
			falpha = (style.filter.blurAlpha != undefined?Number(style.filter.blurAlpha):100);
			
			if (style.backgroundColor != undefined)
			{
				mc._drawRect( dx, dy, w, style.height, style.backgroundColor);
				eventBorder = EVENT_SELECTION_SIZE;
			}
			if (style.borderWidth && style.borderWidth > 0) {
				mc._drawRect( dx+eventBorder, dy+eventBorder, w-2*eventBorder, style.height-2*eventBorder, style.borderColor);
				mc._drawRect( dx + style.borderWidth+eventBorder, dy + style.borderWidth+eventBorder, w - 2*(style.borderWidth+eventBorder), style.height - 2*(style.borderWidth+eventBorder), style._color);
			} else {
				mc._drawRect( dx+eventBorder, dy+eventBorder, w-2*eventBorder, style.height2*eventBorder, style._color);
			}
			
			if (mcFilter && style.filter.blurX != undefined || style.filter.blurY != undefined)
			{
				mcFilter._drawRect( dx+fdx, dy+fdy, w, style.height, 0x000000);
				mcFilter._alpha = falpha;
				
				var filter:BlurFilter = new BlurFilter( (style.filter.blurX != undefined?style.filter.blurX:0),
														(style.filter.blurY != undefined?style.filter.blurY:0),
														(style.filter.blurQuality != undefined?style.filter.blurQuality:0)
														);
				mcFilter.filters = new Array(filter);
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
			eventTypesVisibility[eventType.id] = (eventType.visible == "false" ? false : true);
			mc.id = eventType.id; ///???
			
			mc.style = styles.getStyleObject(eventType.styleclass);
			mc.selectedStyle = styles.getStyleObject(eventType.selectedstyleclass);
			if (!mc.selectedStyle) mc.selectedStyle = mc.style;
			mc.hoverStyle = styles.getStyleObject(eventType.hoverstyleclass);
			if (!mc.hoverStyle) mc.hoverStyle = mc.style;

			mc._visible = false;
		}
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
		return Math.floor((date.valueOf()-fromDate.valueOf()) * interval.getZWidth() / (interval.getLen() * 1000) );
	}
	private function viewOffsetToDate(offset:Number)
	{
		return new Date(Math.floor(offset * interval.getLen() / interval.getZWidth() * 1000) + fromDate.valueOf());
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
	
	public function showEventType(type:String):Void {
		eventTypesVisibility[type] = true;
		updateEventVisibility();
		drawZoomRuller();
	}
	
	public function hideEventType(type:String):Void {
		eventTypesVisibility[type] = false;
		updateEventVisibility();
		drawZoomRuller();
	}
	
		
	public function switchEventTypes(obj : Object) : Void {
		for (var i:String in obj) {
			eventTypesVisibility[i] = obj[i];
		}
		updateEventVisibility();
		drawZoomRuller();
	}	
	
	private function updateEventVisibility():Void {
		clearGroups();
		// TODO: eventTypesVisibility not needed, we have e[i] that we can use or eventTypes[e[i].type].data (it's the same) for that
		var e:Array = dataObject.data[0].events[0].event;
		var mc:MovieClip;
		var d:Number;
		for (var i:Number = 0; i < e.length; i++)
		{
			mc = this.events["mcP" + i];
			d = parseInt(e[i].date);
			if (d >= fromDate.valueOf() && d <= toDate.valueOf() && eventTypesVisibility[e[i].type]) {
				mc._visible = true;
			} else {
				mc._visible = false;
			}
			this.checkGroup(e, i);
		} 		
	}
	
	public function getVisibleEventsNumber(fromDateNumber:Number, toDateNumber:Number):Number {
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
	}
	
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
			ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').onContextMenu("+this._xmouse+","+this._ymouse+","+date.valueOf()+","+getEventsData(this.hoveredEvent)+")");
		}
	}
	
	public function setVerticalRuller(dateNumber:Number)
	{
		if (dateNumber && dateNumber >= this.fromDate.valueOf() && dateNumber <= this.toDate.valueOf())
		{
			this.verticalRullerDate = new Date(dateNumber);
			this.updateVerticalRuller();
		} else {
			this.hideVerticalRuller();
		}
	}
	
	public function hideVerticalRuller()
	{
		this.verticalRuller.hide();
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
	
	private function getEventsData(event:MovieClip)
	{
		if (!event) return "[]";
		var a:String = "[";
		a += "{id:'" + event.data.id + "', type:'" + event.data.type + "', date:" + event.data.date + ", swimlineId:'" + event.data.swimlineid + "'}";
		if (event.data.group)
		{
			var e:Object;
			for (var i:Number = 0; i < event.data.group.length; i++)
			{
				e = event.data.group[i];
				a+=",{id:'" + e.id + "', type:'" + e.type + "', date:" + e.date + ", swimlineId:'" + e.swimlineid + "'}";
			}
		}
		a += "]";
		return a;
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
