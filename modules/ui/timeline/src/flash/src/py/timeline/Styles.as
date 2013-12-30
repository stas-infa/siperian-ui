import py.parsers;
import TextField.StyleSheet;

/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.timeline.Styles 
{
	private var styles:Object;
	private var defaultStyles:Object;

	public static function traceObject(obj:Object)
	{
		var o:Object;
		for (var name:String in obj)
		{
			o = obj[name]
			if (typeof(o) != "object")
			{
				trace(name + ": " + o +";");
				
			} else {
				trace(name +" = {");
				traceObject(o);
				trace("}");
			}
		}
	}	
	
	public function Styles(css:Object, defaultStyles:Object) 
	{
		// css : {text, styleSheet}
		var sh:StyleSheet = new StyleSheet();
		this.defaultStyles = processDefaultStyles(defaultStyles);
		if (css.styleSheet) {
			sh = css.styleSheet;
		} else 
		{
			sh.parseCSS(css.text);
		}
		this.styles = processStyles(sh);
		//traceObject(styles);
		//traceObject(defaultStyles);
	}
	
	private function processStyles(sh:StyleSheet)
	{
		var style:Object = {};
		var obj = sh["_css"];
		for (var name:String in obj)
		{
			if (name.charAt(0) == '.')
			{
				style[name.substr(1)] = parseStyles(obj[name]);
			}
		}
		return style;
	}
	
	private function processDefaultStyles(style:Object)
	{
		for (var name:String in style)
		{
			style[name] = parseStyles(style[name]);
		}
		return style;
	}
	
	private function parseStyles(styleObject:Object)
	{
		var value:String;
		var a:Array;
		for (var name:String in styleObject)
		{
			value = styleObject[name];
			if (typeof(value) == "string")
			{
				if (name=="filter")
				{
					var sh:StyleSheet = new StyleSheet();
					var clearValue:String = value;
					clearValue = clearValue.split(",").join(";");
					clearValue = ".filter {"+clearValue.split('"').join('')+"}";
					sh.parseCSS(clearValue);
					styleObject[name] = processStyles(sh).filter;
				} else 
				{
					if (value.charAt(0) == '#') {
						styleObject[(name=="color" ? '_' : '')+name] = parsers.parseColor(value);
					}
					else if (value.lastIndexOf("px") != -1) styleObject[name] = parsers.parseNumber(value);
				}
				/*else if (value.indexOf(" ")!=-1) {
					a = value.split(" ");
					for 
				}*/
			}
		}
		return styleObject;
	}
	
	public function getStyleObject(className:String, defaultClassName:String)
	{
		var style:Object;
		var obj:Object = defaultStyles[defaultClassName];
		
		if (obj)
		{
			style = { };
			for (var name:String in obj)
			{
				style[name] = obj[name];
			}
		}
		obj = styles[className];
		if (obj)
		{
			if (style == undefined) style = { };
			for (var name:String in obj)
			{
				style[name] = obj[name];
				if (name == "backgroundColor") style._default = false;
			}
		}
		return style;
	}
	
	public function getStyle(className:String, defaultClassName:String, styleName:String)
	{
		var value = styles[className];
		if (value)
		{
			value = value[styleName];
			if (value == undefined)
			{
				value = defaultStyles[defaultClassName][styleName];
			}
		}
		return value;
	}
	
}