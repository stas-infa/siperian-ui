import py.utils;
/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.parsers 
{
	
	public function parsers()
	{
	}
	
    static function parseColorList (_strColors:String)
    {
		if (_strColors==undefined || _strColors=='') return null;
		
		var a:Array = _strColors.split(',');
		var b:Array = new Array();
		for (var i=0;i<a.length;i++) b.push(parseColor(a[i]));

		return (b.length==0 ? null : b);
	}
		
    static function parseAlphaList (_strAlphas:String, _numColors:Number)
    {
		var b:Array = new Array();
		if (_strAlphas==undefined || _strAlphas=='')
		{
			for (var  i=0; i<_numColors;i++) b.push(100);
			return b;
		}

		var a:Array = _strAlphas.split(',');
		var s:String;
		for (var i=0;i<_numColors;i++)
		{
 			s = a[i];
            if (isNaN(s)) b.push(100); else b.push(parseInt(s));
        }
        return b;
    }
	static function parseNumber (_strNumber:String)
	{
		var str:String = _strNumber.toLowerCase();
		if (str.lastIndexOf("px") != -1)
		{
			str = str.substr(0, str.length-2);
		}
		return ( isNaN(str) ? 0 : parseInt(str) );
	}
	static function parseFNumber (_strNumber:String)
	{
		return ( isNaN(_strNumber) ? 0 : parseFloat(_strNumber) );
	}
	static function parseColor(_strColor)
	{
		_strColor = utils.strLeftTrim(_strColor, ' ');
		_strColor = utils.strLeftTrim(_strColor, '#');
		
		return ( _strColor!='' ? parseInt(_strColor, 16) : 0 );
	}
	static function parseBoolean(_strBoolean:String)
	{
		if (_strBoolean!=undefined && _strBoolean!='' && _strBoolean.toLowerCase()!='false'  && _strBoolean.toLowerCase()!='0') return true; else return false;
	}
	
	static function parseAlign(_strString:String)
	{
		var r:String;
		if (_strString!=undefined && _strString!='')
		{
			r =_strString.toLowerCase();
			if (r!='left' && r!='right') r = 'center';
		} else r='center';
		
		return r;
	}
	
	static function parseVAlign(_strString:String)
	{
		var r:String;
		if (_strString!=undefined && _strString!='')
		{
			r =_strString.toLowerCase();
			if (r!='bottom') r = 'top';
		} else r='top';
		
		return r;
	}	
	static function parsePosition(_strString:String)
	{
		var r:String;
		if (_strString!=undefined && _strString!='')
		{
			r =_strString.toLowerCase();
			if (r!='bottom' && r!='left' && r!='right') r = 'top';
		} else r='top';
		
		return r;
	}
}