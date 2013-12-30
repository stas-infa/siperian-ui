/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.timeline.VerticalRuller extends MovieClip
{
	public static var BG_COLOR = 0xff7e02;
	public static var WIDTH = 2;
	
	public function VerticalRuller(height:Number) 
	{
		this.hide();
		this.draw(height);
	}
	
	public static function create(parentMovieClip:MovieClip, name:String, x:Number, y:Number, height:Number):VerticalRuller
	{
		var mc:MovieClip = parentMovieClip.createEmptyMovieClip(name, parentMovieClip.getNextHighestDepth());
		var current_constructor:Function = VerticalRuller;
		mc.__proto__ = current_constructor.prototype;
		mc._x = x;
		mc._y = y;		
		current_constructor.call(mc, height);
		return VerticalRuller(mc);
	}	
	
	private function draw(height:Number)
	{
		var halfWidth = Math.round(WIDTH / 2);
		super._drawRect( -halfWidth, 0, WIDTH, height, BG_COLOR);
	}
	
	public function setPosition(x:Number)
	{
		this._x = x;
		this.show();
	}
	
	public function getPosition()
	{
		return this._x;
	}
	
	public function hide()
	{
		this._visible = false;
	}
	
	public function show()
	{
		this._visible = true;
	}
}