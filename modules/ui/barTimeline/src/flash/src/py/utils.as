/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.utils
{
	
	private static function createObjectsFromXML_ex(xml, obj, lowercaseflag):Void
	{
		// recursive function that creates objects from xml
		// parameters:
		// xml - XML object
		// obj - Object
		// lowercaseflag - if true all nodeNames will be lowercased in final objects structure
		var i=0;
		var r=0;
		var nn="";
		var aname="";
		var name="";
		
		for (i=0;i<xml.childNodes.length;i++)
		{
			if (xml.childNodes[i].nodeType==1)
			{
				if (xml.childNodes[i].nodeName.toLowerCase() == "html")
				{
					if (!obj.__HTML) obj.__HTML="";
					obj.__HTML += xml.childNodes[i].toString();
				}
				else
				{
					nn = (lowercaseflag ? xml.childNodes[i].nodeName.toLowerCase() : xml.childNodes[i].nodeName);
					if (!obj[nn]) obj[nn] = new Array();
					r=obj[nn].length;
					obj[nn][r]=new Object();
					for  (name in xml.childNodes[i].attributes)
					{
						aname=(lowercaseflag ? name.toLowerCase() : name);
						obj[nn][r][aname] = xml.childNodes[i].attributes[name];
					}
					utils.createObjectsFromXML_ex(xml.childNodes[i], obj[nn][r], lowercaseflag);
				}
			} else if (xml.childNodes[i].nodeType==3)
			{
				if (!obj.__TEXT) obj.__TEXT = "";
				obj.__TEXT += xml.childNodes[i].nodeValue;
			}
		}		
	}

	public static function createObjectsFromXML(xml, lowercaseflag):Object
	{
		// main function that creates objects from xml
		// parameters:
		// xml - XML object
		// lowercaseflag - if true all nodeNames will be lowercased in final objects structure
		// return: Object based on xml data	
		var obj = new Object();
		utils.createObjectsFromXML_ex(xml,obj, lowercaseflag);
		return obj;
	}

	public static function strLeftTrim(str:String, char:String)
	{
		var p=0;
		var l = str.length;
		while (p<l) if (str.charAt(p)==char) p++; else return str.slice(p);
	}

	public function utils() 
	{
		
	}
	
}