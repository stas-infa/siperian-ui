class py.components.DragArea extends MovieClip
{
	var _dragFlag:Boolean;
	var _showDrag:Boolean;
	var _flag:Boolean;
	
	function DragArea(width_:Number, height_:Number, color_:Number, showDrag_:Boolean)
	{
		useHandCursor = false;
		_showDrag = showDrag_;
		super._drawRect(0,0,width_,height_,color_,0);
		_dragFlag = false;
		_flag = false;
	}

	public static function create(parentMovieClip_:MovieClip, name_:String, x_:Number, y_:Number, width_:Number, height_:Number, color_:Number, showDrag_:Boolean):DragArea
	{
		var mc:MovieClip = parentMovieClip_.createEmptyMovieClip(name_, parentMovieClip_.getNextHighestDepth());
		var current_constructor:Function = DragArea;
		mc.__proto__ = current_constructor.prototype;
		mc._x = x_;     
		mc._y = y_;		
		current_constructor.call(mc, width_, height_, color_, showDrag_);
		return DragArea(mc);
	}
	
	function showDrag(f_:Boolean)
	{
		_showDrag = f_;
	}
	
	
	function moveCursor ()
	{
		_root.cursor._x =_root._xmouse-8;
		_root.cursor._y=_root._ymouse-8;
	}

    function onRollOver()
	{
		_flag = true;
		if (_showDrag)
		{
			_root.cursor._visible=true;
			moveCursor();
			Mouse.hide(); 
		}
	}

	function onRollOut()
	{
		_flag = false;
		if (_showDrag)
		{
			_root.cursor._visible=false;
			Mouse.show();
		}
	}

	function onPress()
	{
		this._dragFlag = true;
		_root.cursor.gotoAndStop(2);
		_parent.onDragStart();
	}

	function onRelease()
	{
		this._dragFlag = false;	
		_root.cursor.gotoAndStop(1);
		_parent.onDragEnd();
	}

	function onReleaseOutside()
	{
		_flag = false;
		this.onRelease();
		_root.cursor._visible = false;
		Mouse.show();
		_parent.onDragEnd();
	}

	function onMouseMove()
	{
		if (_flag)
		{
			if (!_root.cursor._visible)
			{
				_root.cursor._visible = true;
				Mouse.hide();
			}
			var x = _root.cursor._x;
			var y = _root.cursor._y;
			moveCursor();
			if (this._dragFlag) _parent.onDrag(_root.cursor._x - x, _root.cursor._y - y);
			updateAfterEvent();
		}
	}
}