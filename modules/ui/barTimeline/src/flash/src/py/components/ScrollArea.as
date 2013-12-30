import py.components.ScrollBar;
import py.components.DragArea;

class py.components.ScrollArea extends MovieClip
{
	var _cWidth:Number;
	var _cHeight:Number;	
	var _clipWidth:Number;
	var _clipHeight:Number;	
	var _areaWidth:Number;
	var _areaHeight:Number;	
	var _canvas:MovieClip;
	var _canvasMask:MovieClip;
	var _canvasBackground:MovieClip;
	var _eventHandlers:Object;
	var _canvasX:Number;
	
	function ScrollArea(width_:Number, height_:Number, color_:Number, scrollType_:Number, eventHandlers_:Object)
	{
		this._eventHandlers = eventHandlers_;
		_cWidth = width_;
		_cHeight = height_;
		_clipWidth = _areaWidth = width_;// -16;
		_clipHeight = _areaHeight = height_;// -16;

		_canvasBackground = DragArea.create(this,"mcDrag",0,0,_clipWidth,_clipHeight,color_,false);
		//_canvasBackground._drawRect(0,0,_clipWidth,_clipHeight,0xf3f3f3,100);
		_canvas = this.createEmptyMovieClip("mcCanvas",this.getNextHighestDepth());
		_canvasMask = this.createEmptyMovieClip("mcCanvasMask",this.getNextHighestDepth());
		_canvasMask._drawRect(0,0,_clipWidth,_clipHeight,0x80,30);
		_canvas.setMask(_canvasMask);

		_root.attachMovie("dragcursor","cursor",_root.getNextHighestDepth());
		_root.cursor._visible = false;
	}
	
	public static function create(parentMovieClip_:MovieClip, name_:String, x_:Number, y_:Number, width_:Number, height_:Number, color_:Number, eventHandlers_:Object):ScrollArea
	{
		var mc:MovieClip = parentMovieClip_.createEmptyMovieClip(name_, parentMovieClip_.getNextHighestDepth());
		var current_constructor:Function = ScrollArea;
		mc.__proto__ = current_constructor.prototype;
		mc._x = x_;
		mc._y = y_;		
		current_constructor.call(mc, width_, height_,color_,0,eventHandlers_);
		return ScrollArea(mc);
	}
	
	function getCanvas()
	{
		return _canvas;
	}
	function setSize(width_:Number, height_:Number, clipWidth_:Number, clipHeight_:Number)
	{
		_clipHeight = _cHeight;
		_areaHeight = height_;
		_canvasBackground._width = _canvasMask._width = _clipWidth = clipWidth_;
		_areaWidth = width_;
		_canvasBackground._height = _canvasMask._height = _clipHeight = clipHeight_;
		_canvasBackground.showDrag(true);
		trace(width_);
	}
	function setCanvasSize(width_:Number, height_:Number)
	{
		_clipHeight = _cHeight;
		if (_clipHeight<height_)_areaHeight = height_; else _areaHeight = _clipHeight;
		
		_canvasBackground._width = _canvasMask._width = _clipWidth = _cWidth;

		if (_clipWidth<width_)_areaWidth = width_; else _areaWidth = _clipWidth;

		_canvasBackground._height = _canvasMask._height = _clipHeight = _cHeight;
		
	
		_canvasBackground.showDrag(true);
		trace(_areaWidth +"::"+ _clipWidth);
	}
	
	function scrollTo(x_:Number, y_:Number, callEventHandlers:Boolean)
	{
		var ox = _canvasX;
		_canvasX = -x_;
		_canvas._y = -y_;
		if (callEventHandlers && ox != -x_ && this._eventHandlers.onDrag) this._eventHandlers.onDrag(x_);
	}
	
	function onDrag(dx_:Number, dy_:Number)
	{
		//trace(_areaWidth +"::"+ _clipWidth);
		if (_areaWidth!=_clipWidth)
		{
			//trace(dx_ +":" + dy_);
			var x = _canvasX;
			_canvasX += Math.round(dx_);
			if (_canvasX<-_areaWidth+_clipWidth) _canvasX = -_areaWidth+_clipWidth;
			else if (_canvasX > 0) _canvasX = 0;
			if (x != _canvasX)
			{
				if (this._eventHandlers.onDrag) this._eventHandlers.onDrag.call(this._eventHandlers, -_canvasX);
			}
		} else {_canvasX = 0;}

		if (_areaHeight!=_clipHeight)
		{
			var y = _canvas._y;
			_canvas._y += Math.round(dy_);
			if (_canvas._y<-_areaHeight+_clipHeight) _canvas._y = -_areaHeight+_clipHeight;
			else if (_canvas._y>0) _canvas._y = 0;		
			if (y!=_canvas._y);		
		} else {_canvas._y = 0;}
	}
	
	public function onDragStart()
	{
		if (this._eventHandlers.onDragStart) this._eventHandlers.onDragStart.call(this._eventHandlers);
	}
	public function onDragEnd()
	{
		if (this._eventHandlers.onDragEnd) this._eventHandlers.onDragEnd.call(this._eventHandlers);
	}	
	
	public function getOffsetX()
	{
		return -_canvasX;
	}
}