import flash.external.*;
import py.utils;
import py.prototypes.Movieclip;
import ContextMenu;
import TextField.StyleSheet;
/**
 * @author Pavel Yaschenko / Exadel
 */
class TimeLineComponent 
{
	public static var app : TimeLine;
	
	public function TimeLineComponent() 
	{
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
	
	public static function setXmlString(xmlString)
	{
		//ExternalInterface.call("FlashTimeline.ASTrace('"+xmlString+"')");
		_root.xml.parseXML(xmlString);
		_root.xml.loaded = true;
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
		
		_root.xml = new XML();
		_root.xml.ignoreWhite = true;
		_root.xml.loaded = false;
		_root.xml.onLoad = function (success:Boolean) {
			//ExternalInterface.call("FlashTimeline.ASTrace('"+success+"')");
			if (success) _root.xml.loaded = true;
		}
		var styles = new StyleSheet();
		styles.loaded = false;
		styles.onLoad = function (success:Boolean) {
			if (success) styles.loaded = true;
		}
		
		//TimeLineComponent.properties = properties;
		//_root.xmlUrl = "data.xml";
		//_root.cssUrl = "css/styles.css";
		if (_root.xmlUrl) {
			_root.xml.load(_root.xmlUrl);
		} else {
			//ExternalInterface.call("FlashTimeline.ASTrace('"+_root.xmlString+"')");
			//xml.parseXML(_root.xmlString);
			//xml.loaded = true;
		}
		
		if (_root.cssUrl) {
			styles.load(_root.cssUrl);
		} else {
			styles.loaded = true;
		}
		
		ExternalInterface.addCallback("setXmlString", swfRoot, TimeLineComponent.setXmlString);
		ExternalInterface.addCallback("mouseOut", swfRoot, TimeLineComponent.mouseOut);
		
		ExternalInterface.call("FlashTimeline.getComponent('" + TimeLineComponent.getId() + "').onFlashInit()")
		
		//if (!swfRoot.id) TimeLineComponent.setProperties({xml:"fff"});

		// root object's onEnterFrame event listner
		// this function starts script when width and height passed to flash and xml file loaded
		swfRoot.onEnterFrame = function ()
		{
			if (!this.isInitialized) 
			{
				if (Stage.width!=0 && Stage.height!=0)
				{
					this.isInitialized = true;
				}
			}
			else if (_root.xml.loaded && styles.loaded)
			{
				delete _root.onEnterFrame;
				var properties = {
					dataObject: utils.createObjectsFromXML(_root.xml, true),
					sh: (_root.cssUrl ? styles : null),
					useFlashContextMenu: _root.useFlashContextMenu
				}
				
				TimeLineComponent.app = TimeLine.create(_root, "timeline", 0, 0, properties );
				
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
