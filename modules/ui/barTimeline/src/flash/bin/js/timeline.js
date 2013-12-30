FlashTimeline = {
	getComponent: function(componentId) {
	
		return $(componentId).component;
	},
	ASTrace: function (msg)
	{
		console.log(msg);
	},
	ASAlert: function (msg)
	{
		alert(msg);
	}
};

Timeline = Class.create({
	
	flashVersion: '9.0.28',

	initialize: function(id, flashUrl, expressUrl,width,height,flashModeParam,xmlFile,cssFile,popupMenuId,eventHandlers) {
		this["rich:destructor"] = "destroy";
		this.id = id;
		this.flashUrl = flashUrl;
		this.expressUrl = expressUrl;
		this.element = $(id);
		this.flashComponent = null;
		this.flashContainerId = id+":flashContainer";
		this.height = height;
		this.width  = width;
		this.eventHandlers = eventHandlers || {}; // onzoomerchange, ontimelineinit, oncontextmenu
		this.element.component = this;
		this.flashModeParam = flashModeParam;
		this.popupMenuId = popupMenuId;
		this.xmlFile     = xmlFile;
		this.cssFile     = cssFile;
		this.lastSelectedEvent = null;
		this.lastSelectedEventIds =[];
		this.lastEventsInputId = this.id+":lastEvents";
		this.lastEventsTimeInputId = this.id+":eventDate";
		this.timelinePositionStateInputId  = this.id+":timelinePositionState";
		this.timelineRulerStateInputId  = this.id+":timelineRulerState";
		this.timelineEventsStateInputId = this.id+":timelineEventsState";
		this.xmlStringId = this.id+":xml";
		this.initialized = false;
		this.initFlash();

	},
	destroy:function(){
        	this.releaseEventHandlers();
			if(this.flashComponent){
				this.flashComponent.onmouseout=null;
			}
			this.id = null;
			this.flashUrl = null;
			this.expressUrl = null;
			this.flashComponent = null;
			this.flashContainerId = null;
			this.height= null;
			this.width  = null;
			this.eventHandlers = null;
			this.id = null;
            if(this.element){
			    this.element.component = null;
            }
			this.element = null;	
			this.flashModeParam = null;
			this.popupMenuId = null;
			this.container = null;
			this.cssFile  = null;
			this.lastEventsInputId = null;
			this.lastSelectedEvent=null;
			this.lastEventsInputId = null;
			this.timelineStateInputId= null;
			this.timelineRulerStateInputId  =null;
			this.timelineEventsStateInputId = null;
	},
	releaseEventHandlers : function(){
		RightClick.destroy(this.id, this.flashContainerId);
	},
	initFlash: function (){
			var flashvars = {id:this.id, xmlUrl : this.xmlFile, cssUrl : this.cssFile};
			var params = {
				menu: "false",
				scale: "noScale",
				bgcolor: "#FFFFFF",
				wmode  : this.flashModeParam,
				id: this.flashContainerId,
				name: this.flashContainerId,				
				AllowScriptAccess: "samedomain"
			};
		swfobject.embedSWF(this.flashUrl, this.flashContainerId, this.width, this.height, this.flashVersion, this.expressUrl, flashvars, params);
	},
	
	onFlashInit: function (){
			this.flashComponent = (document[this.flashContainerId]) ? document[this.flashContainerId] : (window[this.flashContainerId] ? window[this.flashContainerId] : $(this.flashContainerId));

			if (this.initialized) {
				return;
			}
		
			if (this.flashComponent)
			{
				this.flashComponent.onmouseout = this.flashOnMouseOut.bind(this);
				var xmlStringEl = document.getElementById(this.xmlStringId).value;
				if (xmlStringEl) {
					this.flashComponent.setXmlString(xmlStringEl);
				}
			}
		
			RightClick.init(this.id, this.flashContainerId);
			
			this.initialized = true;
	},
	
	flashOnMouseOut: function() {
		this.flashComponent.mouseOut();
	},
	
	initTimelineState : function(){
		//alert("initTimelinestate!");
		if($(this.timelinePositionStateInputId)){
			var variables = $(this.timelinePositionStateInputId).value;
			//alert("variables="+variables);
			if(variables && variables!=void(0) && variables!=""){
				var stateValues = variables.split(",");
				//alert(stateValues);
				if((stateValues.length) == 2 && ((stateValues[0]!="") && (stateValues[1]!="") && stateValues[0]!="null" &&
				stateValues[1]!="null")){
				//	console.log("initTimelineState: stateValue[0]="+stateValues[0]+",stateValues[1]="+stateValues[1]);
					this.setVisibleInterval(new Date(new Number(stateValues[0])),new Date(new Number(stateValues[1])));
				}
				
			}
		
		}
		if($(this.timelineRulerStateInputId)){
			var value = $(this.timelineRulerStateInputId).value;
			if(value && value!="" && new Number(value)!=0){
				this.setVerticalRuller(value);
			}
		}
		if($(this.timelineEventsStateInputId)){
			var value = $(this.timelineEventsStateInputId).value;
			if(value && value.length > 0){
				var events =  value.split(",");
				for(var i = 0 ; i < events.length ; i ++){
					this.hideEventType(events[i]);
				}
			}
		}
		this.callEventHandler(this.eventHandlers["ontimelineinit"]);
	},
	setLastSelectedEvent :function(lastSelectedEvent){
		this.lastSelectedEvent = lastSelectedEvent;
	},
	getLastSelectedEvent: function(){
		return this.lastSelectedEvent;
	},
	callEventHandler: function(handler){
		if (handler) {
			if (typeof handler == "function") {
				handler();
			}else if (typeof handler == "string"){
				new Function("",handler)();
			}
		}
	},
	onContextMenu: function (mouseOffsetX, mouseOffsetY, dateNumber, item) {
		//item object:
		//{id:, type:, startDate:, endDate:, swimlineId:, isSelected:}
		
		alert("onContexMenu: x:"+mouseOffsetX+" y:"+mouseOffsetY+" date:"+new Date(dateNumber)+" item id:"+(item ? item.id : null));
		
		this.callEventHandler(this.eventHandlers["oncontextmenu"]);
	},

	onDateRangeChanged : function(viewFromDateNumber, viewToDateNumber){
		//TODO
		var fromDate = new Date(viewFromDateNumber);
		var toDate = new Date(viewToDateNumber);
		if(fromDate && toDate && $(this.timelinePositionStateInputId)){
			$(this.timelinePositionStateInputId).value=viewFromDateNumber+","+viewToDateNumber;
		}
		
		this.callHandler(this.eventHandlers["onzoomerchange"]);

		if($(this.timelineRulerStateInputId)){
			var value = $(this.timelineRulerStateInputId).value;
			if(value && value!="" && new Number(value)!=0){
				this.setVerticalRuller(value);
			}
		}
	
	}, 
	
	/*onScroll : function(viewFromDateNumber, viewToDateNumber){
		//TODO
	},*/

	disable: function (flag) {
		if(this.flashComponent) {
			this.flashComponent.disable(flag);
		}else{
//			alert("Component not found");
		}
	},
	setVerticalRuller : function(dateNumber){
		if(this.flashComponent && dateNumber!=void(0)){
			
			this.flashComponent.setVerticalRuller(dateNumber);
			if($(this.timelineRulerStateInputId)){
				$(this.timelineRulerStateInputId).value = dateNumber;
			}
		}
	},
	hideVerticalRuller : function(){
		if(this.flashComponent){
			this.flashComponent.hideVerticalRuller();
			if($(this.timelineRulerStateInputId)){
				$(this.timelineRulerStateInputId).value =0;
			}
		}	
	},
	getVerticalRullerDate : function(){
		if(this.flashComponent){
			return this.flashComponent.getVerticalRullerDate();
		}	
	},
   /**
    * The function updates Visible timelineViewer interval
	* fromDate, toDate should be passed as parameters. 
	*  If null, component will display the full time period in Timeline control
    */	
	setVisibleInterval: function (fromDate, toDate) {
		if (this.flashComponent)
		{
			if ( (fromDate!=void(0) && toDate!=void(0)) && (fromDate.getTime()>=toDate.getTime()) ){
				 return false;
			}
			this.flashComponent.setVisibleInterval(fromDate == void(0) ? null : fromDate.getTime(),toDate == void(0) ? null : toDate.getTime());
			return true;
		}
		
		return false;
	},
	
   /**
    * If null, first event date and last event date are returned
    */	
	// return value is an object with two timestamps and two Dates {fromDateNumber, toDateNumber, fromDate, toDate}
	getVisibleInterval: function () {
		var result = null;
		if (this.flashComponent)
		{
			result = this.flashComponent.getVisibleInterval();
			result.fromDate = new Date(result.fromDateNumber);
			result.toDate = new Date(result.toDateNumber);
		}
		
		return result;
	},
    
   /**
    * FromDate, toDate should be passed as parameters. 
    *  If null, number of events for the full time period is returned
    */ 
    getVisibleEventsNumber: function (fromDate, toDate) {
		if (this.flashComponent)
		{
			
			return this.flashComponent.getVisibleEventsNumber(fromDate == null ? null : fromDate.getTime(), toDate == null ? null :toDate.getTime());
		}
		
		return void(0);	
    },
    
   /**
   	* The function The function updates Visible timelineViewer interval
	*  Coefficient should be passed as a parameter to be divided/  multiplied the Visible timelineViewer interval on 
    *  If null, the Visible timelineViewer interval is not changed
   	*/ 
    zoomIn: function (factor) {
		if (this.flashComponent)
		{
			this.flashComponent.zoomIn(factor);
			return true;
		}
		return false;
    },
    
     /**
   	* The function The function updates Visible timelineViewer interval
	*  Coefficient should be passed as a parameter to be divided/  multiplied the Visible timelineViewer interval on 
    *  If null, the Visible timelineViewer interval is not changed
   	*/ 
    zoomOut: function (factor) {
		if (this.flashComponent)
		{
			this.flashComponent.zoomOut(factor);
			return true;
		}
		return false;
    }
});
