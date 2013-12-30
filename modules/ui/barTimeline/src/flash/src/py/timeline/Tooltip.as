class py.timeline.Tooltip 
{
	
	private var theTip:MovieClip;
	private var tFormat:TextFormat;
	private var tipWidth:Number;
	private var tipHeight:Number;
	private var style:Object;
	private var isNoWrap:Boolean = false;
	private var lineLength:Number = 0;
	
	function Tooltip(style:Object) 
	{
		this.style = style;

		this.theTip = _root.createEmptyMovieClip("tooltip", _root.getNextHighestDepth());
		if ((this.style.nowrap != undefined) && (this.style.nowrap!=null)) {
			this.isNoWrap = this.style.nowrap;
		}
		if ((this.style.lineLength != undefined) && (this.style.lineLength != null)) {
			this.lineLength = this.style.lineLength
		}
		
		this.theTip.createTextField("theText",this.theTip.getNextHighestDepth(),10,0,200,150);
		_root.tooltip = this;
		
		this.tFormat = new TextFormat();
		this.tFormat.font = this.style.fontFamily;
		this.tFormat.size = this.style.fontSize;

		
		this.tFormat.align = this.style.textAlign;
		this.tFormat.color = this.style._color;
		this.theTip.theText.setNewTextFormat(this.tFormat);
		this.theTip._visible = false;
	}
	
	public function showTip(theText:String):Void 
	{

		this.theTip._visible = false;
		this.theTip.theText.selectable = false;
		this.theTip.theText.multiline = true;
		this.theTip.theText.wordWrap = !this.isNoWrap;
		this.theTip.theText.autoSize = true;
		this.theTip.theText.html = true;
	

		
		
		this.theTip.theText.htmlText = theText;
		
		this.tipHeight = this.theTip.theText.textHeight+5;
		this.tipWidth  = this.theTip.theText.textWidth+20;
		
		//this.theTip.theText.autoSize = false;
	//	if (this.tipWidth>220)
	//	{
	//		this.tipWidth = 920;
	//		this.theTip.theText._width = 900;
	//	}

		if (this.tipHeight>220)
		{
			this.tipHeight = 220;
			this.theTip.theText._height = 200;
		}

				
		this.theTip.clear();
		this.theTip.beginFill(this.style.backgroundColor);
		this.theTip.lineStyle(this.style.borderWidth, this.style.borderColor, this.tipWidth);
		this.theTip.moveTo(0, 0);
		this.theTip.lineTo(this.tipWidth, 0);
		this.theTip.lineTo(this.tipWidth, this.tipHeight);
		this.theTip.lineTo(0, this.tipHeight);
		this.theTip.lineTo(0, 0);
		this.theTip.endFill();

		
		var dx = 0;
		if (_root.tooltip.tipWidth+_root._xmouse+5>Stage.width)
			dx = -_root.tooltip.tipWidth-20;
		var dy = 0;
		if (_root.tooltip.tipHeight+_root._ymouse>Stage.height)
			dy = -(_root.tooltip.tipHeight - (Stage.height-_root._ymouse));
			
		
		this.theTip._x = _root._xmouse + 15 + dx;
		this.theTip._y = _root._ymouse + 0 + dy;
		
		
		this.theTip._visible = true;
		this.theTip.onMouseMove = function() {
			var dx1 = 0;
			if (_root.tooltip.tipWidth+_root._xmouse+5>Stage.width)
				dx1 = -_root.tooltip.tipWidth-20;
			var dy1 = 0;
			if (_root.tooltip.tipHeight+_root._ymouse>Stage.height)
				dy1 = -(_root.tooltip.tipHeight - (Stage.height-_root._ymouse));
				
			this._x = _root._xmouse + 15+ dx1;
			this._y = _root._ymouse + 0 + dy1;
			
			updateAfterEvent();
		}
		
	}
	
	public function removeTip():Void 
	{
		
		this.theTip._visible = false;
		
	}
	
	public function getTooltipLineLength():Number {
		return this.lineLength;
	}

}