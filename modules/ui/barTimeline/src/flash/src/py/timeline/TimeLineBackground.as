import flash.external.*;
import flash.geom.Rectangle;
import py.parsers;
import py.timeline.Styles;
import py.timeline.TimeScroller;

/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.timeline.TimeLineBackground extends MovieClip
{
	private var backgroundBottom:MovieClip;
	private var backgroundLabels:MovieClip;
	private var backgroundTop:MovieClip; //?? not needed ???
	private var data:Object;
	private var styles:Styles;
	public static var BB_WIDTH:Number = 100;
	public static var FROZEN_WIDTH:Number = 100;
	public static var LABEL_LEFT_OFFSET:Number = 8;
	public static var BG_COLOR:Number = 0xffffff;
	public static var BORDER_COLOR:Number = 0xd5d5d7;
	public static var ZOOM_SCALE_HEIGHT:Number = 15;
	public static var SCALE_HEIGHT:Number = 12;
	public static var SCALE_BG_COLOR1:Number = 0xebebed;
	public static var SCALE_OFFSET_TOP:Number = 9; //--- was 12
	public static var SCALE_OFFSET_BOTTOM:Number = 5; //--- was 9
	public static var SCALE_SWIMLINE_HEIGHT:Number = 5; //--- was 3
	public static var SCALE_SWIMLINE_OFFSET:Number = 1;
	public static var SCALE_BG_COLOR2:Number = 0xc8d8e8;
	public static var SCALE_SWIMLINE_COLOR:Number = 0xabbbd4;
	public static var SCROLLER_OFFSET_TOP:Number = 5;
	public static var SCROLLER_HEIGHT:Number = 6;
	private var positions:Object = {
										mainRullerWidth:0,
										swimLinesHeight:0,
										zoomHeight:0,
										mainRullerY:0,
										mainRullerX:0,
										scaleHeight:0,
										scaleY:0
									};
	
	public function TimeLineBackground(data:Object, styles:Styles) 
	{
		this.data = data;
		this.styles = styles;
		this.backgroundBottom = this.createEmptyMovieClip("mcBottom", this.getNextHighestDepth());
		this.backgroundLabels = this.createEmptyMovieClip("mcLabels", this.getNextHighestDepth());
		this.backgroundTop = this.createEmptyMovieClip("mcTop", this.getNextHighestDepth());
		this.initDraw();
		//this.resize();
	}

	public static function create(parentMovieClip_:MovieClip, name_:String, data:Object, styles:Object):TimeLineBackground
	{
		var mc:MovieClip = parentMovieClip_.createEmptyMovieClip(name_, parentMovieClip_.getNextHighestDepth());
		var current_constructor:Function = TimeLineBackground;
		mc.__proto__ = current_constructor.prototype;
		mc._x = 0;
		mc._y = 0;		
		current_constructor.call(mc, data, styles);
		return TimeLineBackground(mc);
	}
	
	public function onResize(w:Number, h:Number)
	{
		this.backgroundBottom._width = w;
		this.positions.mainRullerWidth = w - 4 - data.frozenwidth;
	}	
	
	private function initDraw()
	{
		//this.beginFill(0x0000ff, 100); this.moveTo(0, 0); this.lineTo(150, 0); this.lineTo(150, 300); this.lineTo(0, 300); this.lineTo(0, 0); this.endFill();
		//this.beginFill(0xff0000, 100); this.moveTo(150, 0); this.lineTo(300, 0); this.lineTo(300, 300); this.lineTo(150, 300); this.lineTo(150, 0); this.endFill();
		correctData();
		createSwimLines();
		
	}
	
	private function correctData()
	{
		data.frozenwidth = (data.frozenwidth) ? parsers.parseNumber(data.frozenwidth) : FROZEN_WIDTH;
	}
	
	private function createSwimLines ()
	{
		var slHeight:Number = 0;
		var w:Number = data.frozenwidth + BB_WIDTH;
		var h:Number = 0;
		var style:Object;
		var bgColor:Number;
		
		//this.backgroundBottom._drawRect(0, 0, w, 500, 0xccffcc);
		this.positions.swimLinesHeight = 1;
		for (var i:Number = 0; i < this.data.swimline.length; i++)
		{
			style = styles.getStyleObject(data.swimline[i].styleclass, "swimLine");
			this.drawSwimLine(0, h, w, style.height, style.backgroundColor, style._default);
			this.createLabel("tfLabel" + i, data.swimline[i].name, 0, h, style);
			data.swimline[i].y = h;
			data.swimline[i].height = style.height;
			h += style.height;
			this.positions.swimLinesHeight += style.height;
			this.backgroundBottom._drawHLine(0, h, w, BORDER_COLOR);
		}
		this.backgroundBottom._drawHLine(0, 0, w, BORDER_COLOR);
		// scales
		h += ZOOM_SCALE_HEIGHT+1;
		this.positions.zoomHeight = this.positions.swimLinesHeight + ZOOM_SCALE_HEIGHT+1;		
		this.backgroundBottom._drawRect(2, h, w - 4, SCALE_HEIGHT - 1, SCALE_BG_COLOR1);
		h += SCALE_HEIGHT;
		this.backgroundBottom._drawHLine(0, h, w, BORDER_COLOR);
		this.positions.mainRullerY = h;
		this.positions.mainRullerX = data.frozenwidth + 2;
		h += 1;
		
		// --- var scaleHeight = (SCALE_SWIMLINE_HEIGHT + SCALE_SWIMLINE_OFFSET) * (this.data.swimline.length - 1) + SCALE_SWIMLINE_HEIGHT + SCALE_OFFSET_TOP + SCALE_OFFSET_BOTTOM;
		var scaleHeight = SCALE_SWIMLINE_HEIGHT + SCALE_OFFSET_TOP + SCALE_OFFSET_BOTTOM;
		
		this.positions.scaleHeight = scaleHeight-2;
		this.positions.scaleY = h+1;
		this.backgroundBottom._drawRect(2, h + 1, w - 4, scaleHeight - 2, SCALE_BG_COLOR2);
		h += SCALE_OFFSET_TOP;
		// --- one small swimline insteard of many
		// was:
		/*for (var i:Number = 0; i < this.data.swimline.length; i++)
		{
			this.backgroundBottom._drawRect(data.frozenwidth + 2, h, BB_WIDTH-4, SCALE_SWIMLINE_HEIGHT, SCALE_SWIMLINE_COLOR);
			h += SCALE_SWIMLINE_HEIGHT + SCALE_SWIMLINE_OFFSET;
		}*/
		// now:
		this.backgroundBottom._drawRect(data.frozenwidth + 2, h, BB_WIDTH-4, SCALE_SWIMLINE_HEIGHT, SCALE_SWIMLINE_COLOR);
		h += SCALE_SWIMLINE_HEIGHT + SCALE_SWIMLINE_OFFSET;
		// ---
 		h += SCALE_OFFSET_BOTTOM-SCALE_SWIMLINE_OFFSET;
		this.backgroundBottom._drawHLine(0, h, w, BORDER_COLOR);
		h += 1;
		h += SCROLLER_OFFSET_TOP;
		this.backgroundBottom._drawRect(0, h , w, SCROLLER_HEIGHT, BORDER_COLOR);
		h += SCROLLER_HEIGHT;
		
		// verticals
		this.backgroundBottom._drawVLine(0, 0, h, BORDER_COLOR);
		this.backgroundBottom._drawVLine(data.frozenwidth - 2, 0, h, BG_COLOR);
		this.backgroundBottom._drawVLine(data.frozenwidth - 1, 0, h+2, BORDER_COLOR, 2);
		this.backgroundBottom._drawVLine(data.frozenwidth +1, 0, h, BG_COLOR);

		this.backgroundBottom._drawVLine(w - 1, 0, h, BORDER_COLOR);

		//trace(h + 2);
		this.backgroundBottom.scale9Grid = new Rectangle(data.frozenwidth + 2, 2, w - data.frozenwidth - 4, h - 4);
		
		ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').setHeight("+(h+2)+")");

		this.onResize(Stage.width-TimeScroller.MIN_WIDTH/2, 0);
		
		
		/*onMouseMove = function ()
		{
			this.backgroundBottom._width = this._xmouse;
			this.backgroundBottom._height = this._ymouse;
		}*/
	}
	
	private function drawSwimLine(x:Number, y:Number, w:Number, h:Number, c:Number, g:Boolean)
	{
		if (g)
		{
			this.backgroundBottom._drawGradRoundedRect(x+2, y+2, w-4, h-3, 0, [c, BG_COLOR], [100, 100], 90, [false, false, false, false]);
		} else {
			this.backgroundBottom._drawRect(x+2, y+2, w-4, h-3, c);
		}
	}
	
	private function createLabel(name:String, str:String, x:Number, y:Number, style:Object)
	{
		var tf:TextFormat = (new TextField.StyleSheet()).transform(style);
		//for (var name1:String in tf) { trace(name1 + ": " + tf[name1]); }	
		var text:TextField = this.backgroundLabels._createTextField (name, str, x + LABEL_LEFT_OFFSET, y, tf);
		text._y = y + Math.floor((style.height - text.textHeight) / 2) - 1;
	}
	
	public function getPositions()
	{
		return this.positions;
	}
	
}