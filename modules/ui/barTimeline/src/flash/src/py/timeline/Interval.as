/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class py.timeline.Interval 
{
	private var width:Number;
	private var zwidth:Number;
	private var fromDate:Date;
	private var toDate:Date;
	private var ztoDate:Date;
	private var zfromDate:Date;	
	private var intervals:Array; // [{step, len, unit??}]
	private var intervalIndex:Number;
	private var zintervalIndex:Number;
	private var interval:Object;
	private var zinterval:Object;
	private var len:Number;
	private var ticks:Array;
	private var zlen:Number;
	
	public function Interval(params:Object, width:Number, fromDate:Date, toDate:Date, zoomFromDate:Date, zoomToDate:Date) 
	{
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.intervals = [];
		for (var i:Number = 0; i < params.length; i++ )
		{
			this.intervals.push(createIntervalList(params[i]));
		}
		updateInterval(width, zoomFromDate, zoomToDate);
	}
	
	public function updateInterval(width:Number, zoomFromDate:Date, zoomToDate:Date)
	{
		var result:Object;
		this.width = width;
		this.zfromDate = zoomFromDate;
		this.ztoDate = zoomToDate;
		
		trace(zoomFromDate.toString() + "!!!" + zoomToDate.toString(); );
		
		if (!ticks)
		{
			intervalIndex = selectInterval(fromDate, toDate); trace(intervalIndex);
			interval = intervals[intervalIndex];
			len = (toDate.valueOf() - fromDate.valueOf()) / 1000;
			ticks = createTicks(width);
			result.intervalChanged = true;
		} else {
			updateTicks(ticks, width);
			result.intervalChanged = false;
		}
		
		var zi = selectInterval(zfromDate, ztoDate); //trace("new Z:" + zi + ", old Z:" + zintervalIndex);
		zlen = (ztoDate.valueOf() - zfromDate.valueOf()) / 1000;  // trace("zfromDate=" + zfromDate.toString() + "............ztoDate=" + ztoDate.toString());
		zwidth = Math.floor(len * width / zlen); trace("zwidth=" + zwidth + " ,len=" + len + " ,width=" + width + " ,zlen=" + zlen );
		
		if (zi != zintervalIndex)
		{
			zintervalIndex = zi;
			zinterval = intervals[zintervalIndex];
			result.zoomIntervalChanged = true;
		} else {
			result.zoomIntervalChanged = false;
		}
		
		return result;
	}	
	
	// only for main interval
	private function createTicks(w:Number)
	{
		var t = [];
		var d:Date = getFirstDate(fromDate, interval);
		var obj:Object;
		var label:String;
		//trace(d.toString()+"--------"+toDate.toString())
		while (d<toDate)
		{
			label = getLabel(d, interval);
			obj = { date:d, label:label};
			obj.x = Math.floor((d.valueOf() - fromDate.valueOf()) / 1000 * w / len); //trace(len+":"+((d.valueOf() - fromDate.valueOf()) / 1000)+"-"+w+":"+obj.x);
			t.push(obj);
			d = getNextDate(d, interval);
			/*c++;
			if (c > 2700) { trace(d.toString() + " " + toDate.toString() + " " + int.getStepValue + " " + int.stepValue + " " + getNextDate(d, int).toString()) break; }
		*/
		}
		trace(t.length);
		return t;
	}
	
	// only for zinterval
	public function getFirstTickDate(date1, date2)
	{
		var d:Date = getFirstDate(date1, zinterval);
		return (d && d<date2 ? d : null);
	}
	// only for zinterval
	public function getNextTickDate(date1, date2)
	{
		var d:Date = getNextDate(date1, zinterval);
		return (d && d<date2 ? d : null);
	}
	
	// default interval is zinterval
	public function getLabel(date:Date, int:Object)
	{
		//var m:Number = date.getMonth()+1;
		//return (date.getYear() + "/" + (m < 10 ? "0" : "") + m + "/" + date.getDate() + " " + date.getHours()+":"+date.getMinutes());
		return SimpleDateFormatter.formatDate(date, (int ? int.datePattern : zinterval.datePattern) );
	}
	
	private function updateTicks(t:Array, w:Number)
	{
		for (var i:Number = 0; i < t.length; i++ )
		{
			t[i].x = Math.floor((t[i].date.valueOf() - fromDate.valueOf()) / 1000 * w / len);
		}
	}	
	
	private function getFirstDate(date:Date, int:Object)
	{
		var result:Date = new Date(date.valueOf());
		result.setMilliseconds(0);
		result.setSeconds(0);
		if (int.stepChar == 'm') {
			result.setMinutes(getFirstDateValue(date.getMinutes(), int.stepValue));
			if (result < date) result.setMinutes(result.getMinutes() + int.stepValue);
		}
		else {
			result.setMinutes(0);
			if (int.stepChar == 'h') {
				result.setHours(getFirstDateValue(date.getHours(), int.stepValue));
				if (result < date) result.setHours(result.getHours() + int.stepValue);
			}
			else {
				result.setHours(0);
				if (int.stepChar == 'd') {
					result.setDate(getFirstDateValue(date.getDate(), int.stepValue));
					if (result < date) result.setDate(result.getDate() + int.stepValue);
				}
				else {
					//if (interval.stepChar == 'w') result.setDate(getFirstDateValue(this.fromDate.getDate(), interval.stepValue));
					//else {
						result.setDate(1);
						if (int.stepChar == 'M') {
							result.setMonth(getFirstDateValue(date.getMonth(), int.stepValue));
							if (result < date) result.setMonth(result.getMonth() + int.stepValue);
						}
						else {
							result.setHours(0);
							if (int.stepChar == 'y') {
								result.setFullYear(getFirstDateValue(date.getFullYear(), int.stepValue));
								if (result < date) result.setFullYear(result.getFullYear() + int.stepValue);
							}
							else {
								result = null;
							}							
						}						
					//}					
				}
			}
		}
		return result;
	}
	
	private function getNextDate(date:Date, int:Object)
	{
		var d:Date = new Date(date.valueOf());
		d["set" + int.getStepValue](d["get" + int.getStepValue]() + int.stepValue); 
		return d;
	}
	
	private function getFirstDateValue(value:Number, step:Number)
	{
		var v = value % step;
	
		//trace(value + ":" + step + "=" + v);
		return (v != 0 ? value - v + step : value);
	}
	
	private function selectInterval(date1:Date, date2:Date)
	{
		var i:Number = 0;
		var d:Date;
		var interval:Object;
		while (i<intervals.length-1)
		{
			interval = intervals[i];
			d = new Date(date1.valueOf());
			d["set" + interval.getValue](d["get" + interval.getValue]() + interval.lenValue);
			//trace (interval.lenValue+interval.lenChar+":"+date2.toString() + " @@@ " + d.toString());
			if (date2 <= d) break;
			i++;
		}
		return i;
	}
	
	private function createIntervalList(dataObj:Object)
	{
		var result:Object = { };
		result.lenValue = parseInt(dataObj.length.substr(0, dataObj.length.length - 1));
		result.lenChar = dataObj.length.charAt(dataObj.length.length - 1);
		result.stepValue = parseInt(dataObj.marker.substr(0, dataObj.marker.length - 1));
		result.stepChar = dataObj.marker.charAt(dataObj.marker.length - 1);
		result.datePattern = dataObj.datepattern;
		switch (result.lenChar)
		{
			case 'm': result.getValue = "UTCMinutes"; break;
			case 'h': result.getValue = "UTCHours"; break;
			case 'd': result.getValue = "Date"; break;
			case 'M': result.getValue = "Month"; break;
			case 'y': result.getValue = "FullYear"; break;
			case '+': result.getValue = "FullYear"; result.lenChar = "y"; result.lenValue = 0; break;
			default: break;
		}
		switch (result.stepChar)
		{
			case 'm': result.getStepValue = "UTCMinutes"; break;
			case 'h': result.getStepValue = "UTCHours"; break;
			case 'd': result.getStepValue = "Date"; break;
			case 'M': result.getStepValue = "Month"; break;
			case 'y': result.getStepValue = "FullYear"; break;
			default: break;
		}
		return result;
	}
	
	public function getTicks()
	{
		return ticks;
	}

	public function getWidth()
	{
		return width;
	}	
	
	public function getZWidth()
	{
		return zwidth;
	}
	
	public function getLen()
	{
		return len;
	}
	
	public function getZLen()
	{
		return zlen;
	}
	
	public function getZFromDate()
	{
		return zfromDate;
	}
}