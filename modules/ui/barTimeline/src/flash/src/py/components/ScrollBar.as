class py.components.ScrollBar extends MovieClip
{
	var TIMEOUT;
	var timeout;
	var autoScroll;
	
	var _size:Number;
	var _virtualSize:Number;
	var _isHorisontal:Boolean;

	var _scrollSize:Number;
	var _scrollPosition:Number;
	var _minPosition:Number;
	var _maxPosition:Number;	
	
	var _dragButtonSize:Number;
	
	var mcFone:MovieClip;
	var btUp:MovieClip;
	var btDown:MovieClip;
	var mcDrag:MovieClip;
	
	function ScrollBar(size_:Number, vsize_:Number, isHorisontal_:Boolean)
	{
		TIMEOUT = 10;
		timeout = 0;
		autoScroll = 0;
		
		_isHorisontal = (isHorisontal_==true ? true : false);
		_scrollPosition = -1;
		
		initDraw();
		
		update(size_, vsize_, isHorisontal_);
	}
	
	public static function create(parentMovieClip_:MovieClip, name_:String, x_:Number, y_:Number, size_:Number, vsize_:Number, isHorisontal_:Boolean):ScrollBar
	{
		var mc:MovieClip = parentMovieClip_.createEmptyMovieClip(name_, parentMovieClip_.getNextHighestDepth());
		var current_constructor:Function = ScrollBar;
		mc.__proto__ = current_constructor.prototype;
		mc._x = x_;
		mc._y = y_;		
		current_constructor.call(mc, size_, vsize_, isHorisontal_);
		return ScrollBar(mc);
	}
	
	function update(size_:Number, vsize_:Number)
	{
		_size = size_;
		_virtualSize = vsize_;
		_scrollSize = _size - btUp._height - btDown._height;
		_minPosition = btUp._height;
		
		placeObjects();
		
		_maxPosition = _size - _minPosition - mcDrag._height;
		
		//if (_scrollPosition<=0 || _scrollPosition>_maxPosition) setScrollPosition((_scrollPosition<0 ? 0 : _maxPosition));
		//setScrollPosition(0);
	}
	
	
	function initDraw()
	{
		mcFone = attachMovie("fone", "mcFone", this.getNextHighestDepth());
		btUp = attachMovie((_isHorisontal ? "scrollerButtonLeft" : "scrollerButtonUp"), "btUp", this.getNextHighestDepth());
		btDown = attachMovie((_isHorisontal ? "scrollerButtonRight" : "scrollerButtonDown"), "btDown", this.getNextHighestDepth());
		mcDrag = attachMovie((_isHorisontal ? "scrollerHButtonDrag" : "scrollerVButtonDrag"), "mcDrag", this.getNextHighestDepth());		

		if (_isHorisontal)
		{
			_rotation = -90;
			_y += _height;
		}		
		
		mcDrag.onPress = function()
		{
			this.y=this._y;
			this.startDrag(false, this._x, this._parent._minPosition, this._x, this._parent._maxPosition);
			this.onEnterFrame = function()
			{
				if (this.y!=this._y) 
				{
					this.y = this._y;
					this._parent.onScrollDrag();
					updateAfterEvent();
				}
			}
		}
		
		mcDrag.onRelease = function()
		{
			this.stopDrag();
			this.onEnterFrame = function(){};
			if (this.y!=this._y) this._parent.onScrollDrag();
		}
		
		mcDrag.onReleaseOutside = mcDrag.onRelease;
		
		btUp.onRelease = function()
		{
			this._parent.autoScroll = 0;
		}
		btUp.onReleaseOutside = btUp.onRelease;

		btUp.onPress = function()
		{
			this._parent.timeout = 0;
			this._parent.autoScroll = -1;
			this._parent.setScrollPosition();
		}
		
		btDown.onRelease = function()
		{
			this._parent.autoScroll = 0;
		}
		
		btDown.onReleaseOutside = btDown.onRelease;

		btDown.onPress = function()
		{
			this._parent.timeout = 0;	
			this._parent.autoScroll = 1;
			this._parent.setScrollPosition();	
		}
	}

	function placeObjects()
	{
		mcFone._height = _size;
		btDown._y = _size-btDown._height;
		var h = _size*_scrollSize/_virtualSize;
		mcDrag.b1._height = h-1;
		mcDrag.b2._height = h;
		mcDrag.b3._height = h-3;
		mcDrag.b4._height = h-4;
	}
	
	function setScrollPosition(val_:Number)
	{
		var d:Number = 20;
		var v:Number = _scrollPosition;
		_scrollPosition = (val_==undefined ? _scrollPosition : val_);
	
		if (this.autoScroll<0)
			_scrollPosition = (_scrollPosition-d<0 ? 0 : _scrollPosition-d);
		else if (this.autoScroll>0)
			_scrollPosition = (_scrollPosition+d>_virtualSize-_size ? _virtualSize-_size : _scrollPosition+d);

		if (_scrollPosition!=v) 
		{
			mcDrag._y = _minPosition + _scrollSize/_virtualSize*_scrollPosition;
			_parent.onScroll(_scrollPosition, _isHorisontal);
		}
		
	}
	
	function onScrollDrag()
	{
		var v:Number = _scrollPosition;
		_scrollPosition = (mcDrag._y-_minPosition)/_scrollSize*_virtualSize;
		if (_scrollPosition!=v) _parent.onScroll(_scrollPosition, _isHorisontal);
	}
	
	function onEnterFrame()
	{
		if (this.autoScroll!=0)
		{
			if (this.timeout!=this.TIMEOUT) this.timeout++;
			else 
			{
				this.setScrollPosition();
			}
		}
	}	
}