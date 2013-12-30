import flash.geom.Rectangle;
import mx.data.encoders.Num;
import py.timeline.Interval;
import py.timeline.TimeLineBackground;
/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.timeline.TimeScroller extends MovieClip
{
	public static var BG_COLOR:Number = 0x000000;
	public static var MIN_WIDTH:Number = 18;
	private var interval:Interval;
	private var timeAreaHeight:Number;
	private var scrollerHeight:Number;
	private var scrollerOffset:Number;
	private var styles:Object;
	private var baseX:Number;
	private var left:MovieClip;
	private var right:MovieClip;
	private var bg:MovieClip;
	private var parent:MovieClip;
	private var scrollbar:MovieClip;
	private var leftMouseOver:Boolean;
	private var rightMouseOver:Boolean;
	private var oldX:Number;
	private var oldWidth:Number;
	private var virtualWidth:Number;
	private var virtualX:Number;
	private var virtualLen:Number;
	private var virtualArea:MovieClip;
	
	
	public function TimeScroller(parentMovieClip:MovieClip, interval:Interval, baseX:Number, timeAreaHeight:Number, scrollerHeight:Number, scrollerOffset:Number, styles:Object) 
	{
		this.parent = parentMovieClip;
		this.interval = interval;
		this.baseX = baseX;
		this.timeAreaHeight = timeAreaHeight;
		this.scrollerHeight = scrollerHeight;
		this.scrollerOffset = scrollerOffset;
		this.styles = styles;
		this.leftMouseOver = false;
		this.rightMouseOver = false;
		this.virtualX = 0;
		draw();
	}
	
	public static function create(parentMovieClip:MovieClip, name:String, x:Number, y:Number, interval:Interval, timeAreaHeight:Number, scrollerHeight:Number, scrollerOffset:Number, styles:Object):TimeScroller
	{
		var mc:MovieClip = parentMovieClip.createEmptyMovieClip(name, parentMovieClip.getNextHighestDepth());
		var current_constructor:Function = TimeScroller;
		mc.__proto__ = current_constructor.prototype;
		mc._x = x;
		mc._y = y;		
		current_constructor.call(mc, parentMovieClip, interval, x, timeAreaHeight, scrollerHeight, scrollerOffset, styles);
		return TimeScroller(mc);
	}
	
	public function updateWidth()
	{
		
		var width = Math.floor(interval.getWidth() * interval.getZLen() / this.interval.getLen());
		//bg.clear();
		//bg._drawRect(0, 0, width, timeAreaHeight, BG_COLOR, 20);

		width = correctWidthUpdate(width);
		right._x = bg._width = width;
		scrollbar._width = bg._width + 1;
	}
	
	function correctWidthIn (width:Number, coeff:Number):Number
	{
		if (width < MIN_WIDTH)
		{
			virtualLen = Math.round((virtualX > 0 ? virtualLen : interval.getZLen()) / coeff) ;
			virtualWidth = Math.round( (virtualX > 0 ? virtualWidth : MIN_WIDTH) / coeff);
			virtualX = Math.round((MIN_WIDTH - virtualWidth) / 2);
			trace(virtualWidth + " " + virtualX+" "+virtualLen);
			width = MIN_WIDTH;
			virtualArea.clear();
			virtualArea._drawRect(virtualX, 0, virtualWidth, timeAreaHeight, BG_COLOR, 20);
			
		} else {
			virtualX = 0;
		}
		
		return width;
	}
	
	function correctWidthOut (width:Number, coeff:Number):Number
	{
		if (virtualX > 0)
		{
			virtualLen = Math.round(virtualLen * coeff) ;
			virtualWidth = Math.round( virtualLen * interval.getWidth() / interval.getLen());
			if (virtualWidth == 0) virtualWidth = 1;
			virtualX = Math.round((MIN_WIDTH - virtualWidth) / 2);
			trace(virtualWidth + " " + virtualX + " " + virtualLen);
			if (virtualWidth < MIN_WIDTH) {
				width = MIN_WIDTH;
				virtualArea.clear();
				virtualArea._drawRect(virtualX, 0, virtualWidth, timeAreaHeight, BG_COLOR, 20);
			} else {
				width = virtualWidth;
				virtualX = 0;
			}
		} else {
			width = Math.round(bg._width * coeff);
		}
		
		return width;
	}
	
	function correctWidthUpdate (width:Number):Number
	{
		virtualX = 0;
		virtualArea.clear();
		if (width < MIN_WIDTH)
		{
			virtualLen = Math.round(interval.getZLen()) ;
			virtualWidth = Math.round( virtualLen * interval.getWidth() / interval.getLen());
			if (virtualWidth == 0) virtualWidth = 1;
			virtualX = Math.round((MIN_WIDTH - virtualWidth) / 2);
			trace(virtualWidth + " " + virtualX + " " + virtualLen);
			width = MIN_WIDTH;
			virtualArea._drawRect(virtualX, 0, virtualWidth, timeAreaHeight, BG_COLOR, 20);
		}	
		
		return width;
	}	
	
	public function zoomIn(coeff:Number)
	{
		var width = Math.round(bg._width / coeff);

		width = correctWidthIn(width, coeff);
		
		_x += Math.floor((bg._width - width) / 2);
		right._x = bg._width = width;
		scrollbar._width = bg._width + 1;
		updateDateRange();
	}
	
	public function zoomOut(coeff:Number)
	{
		var width:Number;
		
		width = correctWidthOut(width, coeff);

		_x -= Math.floor((width - bg._width) / 2);
		if (_x < this.baseX) _x = this.baseX;
		if (width > this.interval.getWidth()) width = this.interval.getWidth();
		right._x = bg._width = width;
		scrollbar._width = bg._width + 1;
		if (_x + this.bg._width - baseX > this.interval.getWidth())
		{
			_x = this.interval.getWidth() - this.bg._width + baseX;
		}
		updateDateRange();
	}

	private function createScrollBar()
	{
		scrollbar = createEmptyMovieClip("mcScroll", getNextHighestDepth());
		scrollbar._drawRoundedRect(0, 0, 100, 12, 5, 0xffffff, 100, [true, true, true, true]);
		scrollbar._drawGradRoundedRect(1, 1, 98, 10, 5, [0xafb7c0, 0xd4dde6, 0xb8c4d2, 0xc0d1e1], [100, 100, 100, 100], 90, [true, true, true, true]);
		scrollbar._y = timeAreaHeight+TimeLineBackground.SCROLLER_OFFSET_TOP-1;
		scrollbar.scale9Grid = new Rectangle(7, 1, 86, 10);
		/*onMouseMove = function ()
		{
			this.scrollbar._width = this._xmouse;
			//this.scrollbar._height = this._ymouse;
		}*/
		scrollbar.parent = this;
		scrollbar.onPress = function()
		{
			this.drag = true;
			this.xm = this.parent._xmouse;
			this.oldX = this.parent._x;
		};
		scrollbar.onRelease = function()
		{
			this.drag = false;
			if (this.oldX != this.parent._x) this.parent.parent.synhronizeViewRange();
		};
		scrollbar.onReleaseOutside = function()
		{
			this.drag = false;
			if (this.oldX != this.parent._x) this.parent.parent.synhronizeViewRange();
		};
		scrollbar.onMouseMove = function()
		{
			if (this.drag)
			{
				var x = this.parent._x + (this.parent._xmouse - this.xm) - this.parent.baseX;// + this._xmouse;
				if (this.parent.virtualX > 0)
				{
					if (x+this.parent.virtualX < 0) x = -this.parent.virtualX;
					if (x + 1 + this.parent.virtualX + this.parent.virtualWidth > this.parent.interval.getWidth())
					{
						x = this.parent.interval.getWidth() - this.parent.virtualWidth - this.parent.virtualX + 1;
					}
					if (this.parent._x - this.parent.baseX != x) {
						this.parent.parent.onScroll(x+this.parent.virtualX);
						//this.updateAfterEvent();
					}					
				} else {
					if (x < 0) x = 0;
					if (x + this.parent.bg._width+1 > this.parent.interval.getWidth())
					{
						x = this.parent.interval.getWidth() - this.parent.bg._width+1;
					}
					if (this.parent._x - this.parent.baseX != x) {
						this.parent.parent.onScroll(x);
						//this.updateAfterEvent();
					}					
				}
			}
		};		
	}
	
	private function draw()
	{
		bg = createEmptyMovieClip("mcBG", getNextHighestDepth());
		virtualArea = createEmptyMovieClip("mcVA", getNextHighestDepth());
		left = createEmptyMovieClip("mcLeft", getNextHighestDepth());
		right = createEmptyMovieClip("mcRight", getNextHighestDepth());
		createScrollBar();
		left._drawRect(-3, 0, 5, timeAreaHeight, 0, 0);
		right._drawRect(-3, 0, 5, timeAreaHeight, 0, 0);
		left._drawVLine(0, 0, timeAreaHeight, TimeLineBackground.BG_COLOR);
		right._drawVLine(0, 0, timeAreaHeight, TimeLineBackground.BG_COLOR);
		bg._drawRect(0, 0, 64, timeAreaHeight, BG_COLOR, 20);
		updateWidth();
		
		_root.attachMovie("resizecursor","resizecursor",_root.getNextHighestDepth(), {cacheAsBitmap:true});
		_root.resizecursor._visible = false;

		
		left.parent = this;
		left.bg = bg;
		left.right = right;
		left.scrollbar = scrollbar;
		left.onPress = function()
		{
			this.drag = true;
		};
		left.onRelease = function()
		{
			this.parent.virtualX = 0;
			this.drag = false;
			this.parent.updateDateRange();
		};
		left.onReleaseOutside = left.onRelease;

		left.onMouseMove = function()
		{
			if (((this._ymouse >  0 && this._ymouse <= this._height) &&
				(this._xmouse > -3 && this._xmouse <  3)) || this.drag)
			{
				this.parent.leftMouseOver = true;
			}  else 
			{
				if (this.parent.leftMouseOver)
					this.parent.hideResizeCursor();
				this.parent.leftMouseOver = false;
			}
			
			if (this.drag)
			{
				this.parent.leftMouseOver = true;
				var x = this._xmouse;
				if (this.parent._x + x < this.parent.baseX)
				{
					x = -this.parent._x + this.parent.baseX;
				}
				if (this.bg._width - x < TimeScroller.MIN_WIDTH)
				{
					x = x - (TimeScroller.MIN_WIDTH - (this.bg._width - x));
				}
				this.bg._width = this.bg._width - x;
				this.scrollbar._width = this.bg._width + 1;
				this.right._x = this.bg._width;
				this.parent._x = this.parent._x + x;
				this.updateAfterEvent();
			}
			if (this.parent.leftMouseOver)
				this.parent.showResizeCursor();			
		};
		right.parent = this;
		right.bg = bg;
		right.scrollbar = scrollbar;
		right.onPress = function()
		{
			this.drag = true;
		};
		right.onRelease = function()
		{
			this.parent.virtualX = 0;
			this.drag = false;
			this.parent.updateDateRange(true);
		};
		right.onReleaseOutside = right.onRelease;

		right.onMouseMove = function()
		{
			if (((this._ymouse >  0 && this._ymouse <= this._height) &&
				(this._xmouse > -3 && this._xmouse <  3)) || this.drag)
			{
				this.parent.rightMouseOver = true;
			}  else 
			{
				if (this.parent.rightMouseOver)
					this.parent.hideResizeCursor();
				this.parent.rightMouseOver = false;
			}
			
			if (this.drag)
			{
				this.parent.rightMouseOver = true;
				var x = this._xmouse;
				if (this.parent._x - this.parent.baseX +this.bg._width + x > this.parent.interval.getWidth())
				{
					this.bg._width = this.parent.interval.getWidth() - this.parent._x + this.parent.baseX;
				} else if (this.bg._width + x < TimeScroller.MIN_WIDTH){
					this.bg._width = TimeScroller.MIN_WIDTH;
				} else {
					this.bg._width = this.bg._width + x;
				}
				//trace(this.parent.interval.getWidth()+"~~~~"+this.bg._width)
				this.scrollbar._width = this.bg._width + 1;
				this._x = this.bg._width;
				this.updateAfterEvent();
			}
			if (this.parent.rightMouseOver) {
				this.parent.showResizeCursor();
			}
		};
	}
	
	public function updateDateRange(isRight:Boolean)
	{
		if (virtualX > 0) this.parent.updateViewDateRange3(virtualLen);
		else {
			this.virtualArea.clear();
			this.parent.updateViewDateRange((isRight ? null : this._x - this.baseX), this._x + this.bg._width - this.baseX);
		}
	}
	
	public function onDrag(x:Number)
	{
		this._x = baseX + Math.floor(x * interval.getWidth() / interval.getZWidth()) - this.virtualX;
	}
	
	public function showResizeCursor()
	{
		if (this.parent.disabler) return;
		Mouse.hide();
		_root.resizecursor._visible = true;
		_root.resizecursor._x = _root._xmouse;
		_root.resizecursor._y = _root._ymouse;
	}
	public function hideResizeCursor()
	{
		_root.resizecursor._visible = false;
		Mouse.show();
		
	}
	
}