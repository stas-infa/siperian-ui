import flash.external.*;
import py.utils;
import py.prototypes.Movieclip;
import ContextMenu;
import TextField.StyleSheet;
/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class TimeLineComponent 
{
	public static var app : TimeLine;
	private static var properties : Object;
	
	public function TimeLineComponent() 
	{
	}
	
	public static function setProperties(properties:Object)
	{
		TimeLineComponent.properties = properties;
		if (_root.xmlEmpty)
		{
			_root.xml.parseXML(properties.xml);
			_root.xml.loaded = true;
			if (properties.cssUrl)
			{
				_root.xml.loaded = false;
				TimeLineComponent.properties.sh = new StyleSheet();
				TimeLineComponent.properties.sh.onLoad = function (success:Boolean)
				{
					if (success) _root.xml.loaded = true;
				}
				TimeLineComponent.properties.sh.load(properties.cssUrl);
			}
			//_root.xml.load("../data.xml");
		}
	}

	public static function getId()
	{
		return _root.id;
	}
	
	public static function mouseOut()
	{
		_root.cursor._visible = false;
		Mouse.show();
	}
	
	public static function main(swfRoot:MovieClip):Void 
	{
		// entry point
		Movieclip.extend();
		
		// set the Flash movie to have a fixed anchor in the top left corner of? the screen.
		Stage.align = "LT";
		// prevent the Flash movie from resizing when the browser window changes size.
		Stage.scaleMode = "noScale";
		//Stage.showMenu = false;
		
		swfRoot.isInitialized = false;
		
		swfRoot.xml = new XML();
		swfRoot.xml.ignoreWhite = true;
		if (!_root.xmlEmpty)
		{
			if (_root.xmlString) 
			{
				swfRoot.xml.parseXML(_root.xmlString);
				swfRoot.xml.loaded = true;
			} else {
				swfRoot.xml.load("../data.xml");
			}
		}
		ExternalInterface.addCallback("setProperties", swfRoot, TimeLineComponent.setProperties);
		ExternalInterface.addCallback("mouseOut", swfRoot, TimeLineComponent.mouseOut);
		
		ExternalInterface.call("FlashTimeline.getComponent('"+TimeLineComponent.getId()+"').onFlashInit()");
		
		if (!swfRoot.id) TimeLineComponent.setProperties({xml:"fff"});

		// root object's onEnterFrame event listner
		// this function starts script when width and height passed to flash and xml file loaded
		swfRoot.onEnterFrame = function ()
		{
			if (!this.isInitialized) 
			{
				if (Stage.width!=0 && Stage.height!=0)
				{
					this.isInitialized = true;
					trace(Stage.width + ":" + Stage.height);
				}
			}
			else if (TimeLineComponent.properties && swfRoot.xml.loaded)
			{
				delete _root.onEnterFrame;
				TimeLineComponent.properties.dataObject = utils.createObjectsFromXML(swfRoot.xml, true);
				TimeLineComponent.properties.xml = "";
				delete swfRoot.xml;
				
				TimeLineComponent.app = TimeLine.create(_root, "timeline", 0, 0, TimeLineComponent.properties );
				
				var listener:Object = { };
				listener.onResize = function () {
					TimeLineComponent.app.onResize(Stage.width, Stage.height);
				}				
				Stage.addListener(listener);
				
				TimeLineComponent.app.run();
			}
		}
	}
}
