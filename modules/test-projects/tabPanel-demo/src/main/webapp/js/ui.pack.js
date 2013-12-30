var RightClick={destroy:function(FlashContainerId,FlashObjectId){if(window.removeEventListener){window.removeEventListener("mousedown",this.onGeckoMouse,true);
window.removeEventListener("mousedown",this.onGeckoMouse,false)
}else{if(document.detachEvent){document.detachEvent("oncontextmenu",this.ieOnContextMenuHandler);
if(FlashContainerId&&$(FlashContainerId)&&$(FlashContainerId).detachEvent){$(FlashContainerId).detachEvent("onmouseup",this.ieFlashOnMouseUpHandler);
$(FlashContainerId).detachEvent("onmousedown",this.onIEMouse)
}}}},init:function(FlashContainerId,FlashObjectId){this.FlashObjectID=FlashObjectId;
this.FlashContainerID=FlashContainerId;
this.Cache=this.FlashObjectID;
if(window.addEventListener){window.addEventListener("mousedown",this.onGeckoMouse,true)
}else{if(document.attachEvent){document.attachEvent("oncontextmenu",this.ieOnContextMenuHandler);
if($(this.FlashContainerID).attachEvent){$(this.FlashContainerID).attachEvent("onmouseup",this.ieFlashOnMouseUpHandler);
$(this.FlashContainerID).attachEvent("onmousedown",this.onIEMouse)
}}}},ieOnContextMenuHandler:function(){if(window.event.srcElement.id==RightClick.FlashObjectID){return false
}else{RightClick.Cache="nan"
}},ieFlashOnMouseUpHandler:function(){document.getElementById(RightClick.FlashContainerID).releaseCapture()
},killEvents:function(eventObject){if(eventObject){if(eventObject.stopPropagation){eventObject.stopPropagation()
}if(eventObject.preventDefault){eventObject.preventDefault()
}if(eventObject.preventCapture){eventObject.preventCapture()
}if(eventObject.preventBubble){eventObject.preventBubble()
}}},onGeckoMouse:function(ev){if(ev.button!=0){RightClick.killEvents(ev);
if(ev.target.id==RightClick.FlashObjectID&&RightClick.Cache==RightClick.FlashObjectID){RightClick.call(ev)
}RightClick.Cache=ev.target.id
}},onIEMouse:function(){if(event.button>1){if(window.event.srcElement.id==RightClick.FlashObjectID&&RightClick.Cache==RightClick.FlashObjectID){RightClick.killEvents(window.event);
RightClick.call(window.event)
}document.getElementById(RightClick.FlashContainerID).setCapture();
if(window.event.srcElement.id){RightClick.Cache=window.event.srcElement.id
}}},call:function(ev){FlashTimeline.getComponent(this.FlashContainerID).setLastSelectedEvent(ev);
document.getElementById(this.FlashObjectID).rightClick()
}}

FlashTimeline={getComponent:function(componentId){return $(componentId).component
},ASTrace:function(msg){console.log(msg)
},ASAlert:function(msg){alert(msg)
}};
Timeline=Class.create({flashVersion:"9.0.28",initialize:function(id,flashUrl,expressUrl,width,height,flashModeParam,eventMenuId,popupMenuId,cssFile,containerId,onzoomerchange,ontimelineinit){this["rich:destructor"]="destroy";
this.id=id;
this.flashUrl=flashUrl;
this.expressUrl=expressUrl;
this.element=$(id);
this.flashComponent=null;
this.flashContainerId=id+":flashContainer";
this.xmlContentId=id+":flashContent";
this.height=height;
this.width=width;
this.onzoomerchange=onzoomerchange;
this.ontimelineinit=ontimelineinit;
this.element.component=this;
this.flashModeParam=flashModeParam;
this.eventMenuId=eventMenuId;
this.popupMenuId=popupMenuId;
this.cssFile=cssFile;
this.lastSelectedEvent=null;
this.lastSelectedEventIds=[];
this.lastEventsInputId=this.id+":lastEvents";
this.lastEventsTimeInputId=this.id+":eventDate";
this.timelinePositionStateInputId=this.id+":timelinePositionState";
this.timelineRulerStateInputId=this.id+":timelineRulerState";
this.timelineEventsStateInputId=this.id+":timelineEventsState";
this.containerId=containerId;
this.form=null;
this.flashOnMouseOutListener=this.flashOnMouseOut.bind(this);
this.initialized=false;
this.initFlash()
},destroy:function(){this.releaseEventHandlers();
if(this.flashComponent){this.flashComponent.onmouseout=null
}this.id=null;
this.flashUrl=null;
this.expressUrl=null;
this.flashComponent=null;
this.flashContainerId=null;
this.xmlContentId=null;
this.height=null;
this.width=null;
this.id=null;
if(this.element){this.element.component=null
}this.element=null;
this.flashModeParam=null;
this.eventMenuId=null;
this.popupMenuId=null;
this.container=null;
this.cssFile=null;
this.lastEventsInputId=null;
this.lastSelectedEvent=null;
this.lastEventsInputId=null;
this.form=null;
this.containerId=null;
this.timelineStateInputId=null;
this.timelineRulerStateInputId=null;
this.timelineEventsStateInputId=null;
this.onzoomerchange=null;
this.ontimelineinit=null;
this.flashOnMouseOutListener=null
},releaseEventHandlers:function(){RightClick.destroy(this.id,this.flashContainerId)
},initFlash:function(){var flashvars={xmlEmpty:true,id:this.id};
var params={menu:"false",scale:"noScale",bgcolor:"#FFFFFF",wmode:this.flashModeParam,id:this.flashContainerId,name:this.flashContainerId,AllowScriptAccess:"samedomain"};
swfobject.embedSWF(this.flashUrl,this.flashContainerId,this.width,this.height,this.flashVersion,this.expressUrl,flashvars,params)
},onFlashInit:function(){this.flashComponent=(document[this.flashContainerId])?document[this.flashContainerId]:(window[this.flashContainerId]?window[this.flashContainerId]:$(this.flashContainerId));
Utils.execOnLoad(function(){this._onFlashInit()
}.bind(this),function(){return(this.flashComponent&&this.flashComponent.setProperties&&typeof (this.flashComponent.setProperties)=="function")
}.bind(this),200)
},_onFlashInit:function(){if(this.initialized){return 
}var myXML=document.getElementById(this.xmlContentId).value;
if(this.cssFile!=void (0)&&this.cssFile!=""){this.flashComponent.setProperties({xml:myXML,cssUrl:this.cssFile})
}else{this.flashComponent.setProperties({xml:myXML})
}if(this.flashComponent){this.flashComponent.onmouseout=this.flashOnMouseOutListener
}RightClick.init(this.id,this.flashContainerId);
if(this.initInterval){window.clearInterval(this.initInterval);
this.initInterval=null
}this.initialized=true
},flashOnMouseOut:function(){this.flashComponent.mouseOut()
},initTimelineState:function(){if($(this.timelinePositionStateInputId)){var variables=$(this.timelinePositionStateInputId).value;
if(variables&&variables!=void (0)&&variables!=""){var stateValues=variables.split(",");
if((stateValues.length)==2&&((stateValues[0]!="")&&(stateValues[1]!="")&&stateValues[0]!="null"&&stateValues[1]!="null")){this.setVisibleInterval(new Date(new Number(stateValues[0])),new Date(new Number(stateValues[1])))
}}}if($(this.timelineRulerStateInputId)){var value=$(this.timelineRulerStateInputId).value;
if(value&&value!=""&&new Number(value)!=0){this.setVerticalRuller(value)
}}if($(this.timelineEventsStateInputId)){var value=$(this.timelineEventsStateInputId).value;
if(value&&value.length>0){var events=value.split(",");
for(var i=0;
i<events.length;
i++){this.hideEventType(events[i])
}}}if(this.ontimelineinit){new Function("",this.ontimelineinit)()
}},setLastSelectedEvent:function(lastSelectedEvent){this.lastSelectedEvent=lastSelectedEvent
},getLastSelectedEvent:function(){return this.lastSelectedEvent
},onContextMenu:function(mouseOffsetX,mouseOffsetY,dateNumber,events){this.lastSelectedEventIds=[];
if(this.eventMenuId!=void (0)&&this.eventMenuId!=""&&events!=void (0)&&events.length>0){for(var i=0;
i<events.length;
i++){this.lastSelectedEventIds[i]=events[i].id
}$(this.eventMenuId).component.show(this.getLastSelectedEvent(),null)
}else{if(this.popupMenuId&&this.popupMenuId!=""&&(events==null||events.length==0)){$(this.popupMenuId).component.show(this.getLastSelectedEvent(),null)
}}$(this.lastEventsInputId).value=this.getLastEventIds();
$(this.lastEventsTimeInputId).value=(events.length>0?events[0].date:dateNumber);
if(!this.options){var vId=this.id;
this.options={"parameters":{"ajaxSingle":vId}}
}this.submitForm(this.getLastSelectedEvent(),this.options)
},submitForm:function(eVent,options){if(!this.form){this.form=A4J.AJAX.locateForm($(this.id))
}var formId=(this.form)?this.form.id:null;
setTimeout(A4J.AJAX.Submit(this.containerId,formId,eVent,options),300)
},onDateRangeChanged:function(viewFromDateNumber,viewToDateNumber){var fromDate=new Date(viewFromDateNumber);
var toDate=new Date(viewToDateNumber);
if(fromDate&&toDate&&$(this.timelinePositionStateInputId)){$(this.timelinePositionStateInputId).value=viewFromDateNumber+","+viewToDateNumber
}if(this.onzoomerchange){new Function("",this.onzoomerchange)()
}if($(this.timelineRulerStateInputId)){var value=$(this.timelineRulerStateInputId).value;
if(value&&value!=""&&new Number(value)!=0){this.setVerticalRuller(value)
}}},getLastEventIds:function(){if(this.lastSelectedEventIds.length>0){return this.lastSelectedEventIds.toString()
}else{if(this.lastSelectedEventIds.lenght==1){return this.lastSelectedEventIds[0]
}}return null
},setVerticalRuller:function(dateNumber){if(this.flashComponent&&dateNumber!=void (0)){this.flashComponent.setVerticalRuller(dateNumber);
if($(this.timelineRulerStateInputId)){$(this.timelineRulerStateInputId).value=dateNumber
}}},hideVerticalRuller:function(){if(this.flashComponent){this.flashComponent.hideVerticalRuller();
if($(this.timelineRulerStateInputId)){$(this.timelineRulerStateInputId).value=0
}}},getVerticalRullerDate:function(){if(this.flashComponent){return this.flashComponent.getVerticalRullerDate()
}},showEventType:function(type){if(this.flashComponent){this.flashComponent.showEventType(type);
if($(this.timelineEventsStateInputId)){var value=$(this.timelineEventsStateInputId).value;
if(value&&value.length>0){var values=value.split(",");
var new_values=[];
var a=0;
for(var i=0;
i<values.length;
i++){if(values[i]!=type){new_values[a++]=values[i].replace(",","")
}}$(this.timelineEventsStateInputId).value=new_values.toString()
}}return true
}return false
},hideEventType:function(type){if(this.flashComponent){this.flashComponent.hideEventType(type);
if($(this.timelineEventsStateInputId)){var value=$(this.timelineEventsStateInputId).value;
if(value&&value.length>0&&value.indexOf(type)==-1){value=value+","+type
}else{value=type
}$(this.timelineEventsStateInputId).value=value
}return true
}return false
},switchEventTypes:function(objNames){if(this.flashComponent){this.flashComponent.switchEventTypes(objNames);
return true
}return false
},setVisibleInterval:function(fromDate,toDate){if(this.flashComponent){if((fromDate!=void (0)&&toDate!=void (0))&&(fromDate.getTime()>=toDate.getTime())){return false
}this.flashComponent.setVisibleInterval(fromDate==void (0)?null:fromDate.getTime(),toDate==void (0)?null:toDate.getTime());
return true
}return false
},getVisibleInterval:function(){var result=null;
if(this.flashComponent){result=this.flashComponent.getVisibleInterval();
result.fromDate=new Date(result.fromDateNumber);
result.toDate=new Date(result.toDateNumber)
}return result
},setHeight:function(height){if(this.flashComponent){var incValue=height;
if(height<new Number(this.height)){incValue+=20
}else{incValue+=10
}this.flashComponent.height="100%";
this.flashComponent.parentNode.style.height=incValue+"px";
setTimeout(function(){this.initTimelineState()
}.bind(this),250)
}},setLimitativeInterval:function(){},getLimitativeInterval:function(){},getEventId:function(){},getVisibleEventsNumber:function(fromDate,toDate){if(this.flashComponent){return this.flashComponent.getVisibleEventsNumber(fromDate==null?null:fromDate.getTime(),toDate==null?null:toDate.getTime())
}return void (0)
},zoomIn:function(factor){if(this.flashComponent){this.flashComponent.zoomIn(factor);
return true
}return false
},zoomOut:function(factor){if(this.flashComponent){this.flashComponent.zoomOut(factor);
return true
}return false
}})

CalendarKeyHandler=Class.create({initialize:function(calendarId,handler){this.id=calendarId;
this.defaultFirstDate=null;
if(handler){this.handler=handler
}else{this.handler=this.defaultKeyPressHandler.bind(this)
}this.currentYMPopupMode=null;
this.timePopupActivInput="H"
},init:function(){Event.observe(document,"keydown",this.handler)
},destroy:function(){Event.stopObserving(document,"keydown",this.handler)
},getTimeControlInput:function(){if(this.timePopupActivInput=="H"){return $(this.id+"TimeHours")
}else{if(this.timePopupActivInput=="M"){return $(this.id+"TimeMinutes")
}}},increaseTimeInput:function(keyCode){var timeInput=this.getTimeControlInput();
var value=timeInput.value;
if((keyCode==Event.KEY_LEFT)&&this.timePopupActivInput=="H"){value=value-1
}else{if(keyCode==Event.KEY_RIGHT&&this.timePopupActivInput=="H"){}else{if((keyCode==Event.KEY_LEFT)&&this.timePopupActivInput=="M"){}else{if(keyCode==Event.KEY_RIGHT&&this.timePopupActivInput=="M"){}}}}},changeDate:function(e,changeOffset){var selectedDate=this.getComponent().getSelectedDate();
if(!selectedDate){this.getComponent().today(false,true);
selectedDate=this.getComponent().getSelectedDate()
}if(selectedDate){var changed=false;
if(!this.getComponent().showApplyButton){this.getComponent().showApplyButton=true;
changed=true
}var newDate=new Date(selectedDate.getTime());
newDate.setDate(selectedDate.getDate()+changeOffset);
this.getComponent().selectDate(newDate,false,null);
if(changed){this.getComponent().showApplyButton=false
}}},changeMonth:function(e,changeOffset){var selectedDate=this.getComponent().getSelectedDate();
if(selectedDate){var changed=false;
if(!this.getComponent().showApplyButton){this.getComponent().showApplyButton=true;
changed=true
}var newDate=new Date(selectedDate.getTime());
newDate.setMonth(selectedDate.getMonth()+changeOffset);
var day=selectedDate.getDate();
var month=selectedDate.getMonth();
var year=selectedDate.getYear();
this.getComponent().selectDate(newDate,false,null);
if(changed){this.getComponent().showApplyButton=false
}}},changeYear:function(e,changeOffset){var selectedDate=this.getComponent().getSelectedDate();
if(selectedDate){var changed=false;
if(!this.getComponent().showApplyButton){this.getComponent().showApplyButton=true;
changed=true
}var newDate=new Date(selectedDate.getTime());
newDate.setFullYear(selectedDate.getFullYear()+changeOffset);
this.getComponent().selectDate(newDate,false,null);
if(changed){this.getComponent().showApplyButton=false
}}},isYearPanelIsActived:function(){return(!this.currentYMPopupMode||this.currentYMPopupMode=="Y")
},isMonthPanelIsActived:function(){return(this.currentYMPopupMode=="M")
},changeYearMonthPopupValue:function(e,changeOffset){var value=null;
var offset=0;
var checkedValueLow=0;
if(this.isYearPanelIsActived()){value=this.getComponent().dateEditorYear-this.getComponent().dateEditorStartYear;
checkedValueLow=9
}else{if(this.isMonthPanelIsActived()){value=this.getComponent().dateEditorMonth;
checkedValueLow=11
}}if(value==void (0)){value=0
}var newValue=value+changeOffset+offset;
if(newValue>checkedValueLow){newValue=checkedValueLow
}else{if(newValue<0){newValue=0
}}if(!this.currentYMPopupMode||this.currentYMPopupMode=="Y"){this.getComponent().dateEditorSelectYear(newValue)
}else{if(this.currentYMPopupMode=="M"){this.getComponent().dateEditorSelectMonth(newValue)
}}},moveDateUp:function(e){this.changeDate(e,-7)
},moveDateDown:function(e){this.changeDate(e,+7)
},moveDateLeft:function(e){this.changeDate(e,-1)
},moveDateRight:function(e){this.changeDate(e,+1)
},upOrDownHoursOrTime:function(e){if(e.keyCode==Event.KEY_LEFT){}else{if(e.keyCode==Event.KEY_RIGHT){}}},handleDaySelection:function(keys,e){var selectedDate=this.getComponent().getSelectedDate();
if(this.defaultFirstDate==null&&selectedDate){this.defaultFirstDate=selectedDate
}if(keys==Event.KEY_UP){this.moveDateUp(e)
}else{if(keys==Event.KEY_DOWN){this.moveDateDown(e)
}else{if(keys==Event.KEY_LEFT){this.moveDateLeft(e)
}else{if(keys==Event.KEY_RIGHT){this.moveDateRight(e)
}}}}},getComponent:function(){return $(this.id).component
},changeTimeDialogPanelState:function(){this.getTimeControlInput().focus();
if(this.timePopupActivInput=="M"){this.timePopupActivInput="H"
}else{if(this.timePopupActivInput=="H"){this.timePopupActivInput="M"
}}},defaultKeyPressHandler:function(e){switch(e.keyCode){case 188:if(!this.getComponent().isEditorVisible){if(e.shiftKey){this.getComponent().prevYear();
this.changeYear(e,-1)
}else{this.getComponent().prevMonth();
this.changeMonth(e,-1)
}}break;
case 190:if(!this.getComponent().isEditorVisible){if(e.shiftKey){this.getComponent().nextYear();
this.changeYear(e,1)
}else{this.getComponent().nextMonth();
this.changeMonth(e,1)
}}break;
case 27:if(this.getComponent().isEditorVisible&&!this.getComponent().isTimeEditorLayoutCreated){this.getComponent().hideDateEditor(false)
}else{if(this.getComponent().isEditorVisible&&this.getComponent().isTimeEditorLayoutCreated){this.getComponent().hideTimeEditor(false);
this.getComponent().isTimeEditorLayoutCreated=false
}else{if(!this.getComponent().isEditorVisible){this.defaultFirstDate=null;
this.getComponent().close(false);
this.destroy()
}}}break;
case Event.KEY_UP:case Event.KEY_DOWN:case Event.KEY_LEFT:case Event.KEY_RIGHT:if(!this.getComponent().isEditorVisible){this.handleDaySelection(e.keyCode,e)
}else{if(e.keyCode==Event.KEY_LEFT){if(e.shiftKey){this.getComponent().scrollEditorYear(-1)
}else{if(this.getComponent().isDateEditorLayoutCreated){var value=this.isYearPanelIsActived()?-5:-6;
this.changeYearMonthPopupValue(e,value)
}else{if(this.getComponent().isTimeEditorLayoutCreated){this.changeTimeDialogPanelState()
}}}}else{if(e.keyCode==Event.KEY_RIGHT){if(e.shiftKey){this.getComponent().scrollEditorYear(1)
}else{if(this.getComponent().isDateEditorLayoutCreated){var value=this.isYearPanelIsActived()?5:6;
this.changeYearMonthPopupValue(e,value)
}else{if(this.getComponent().isTimeEditorLayoutCreated){this.changeTimeDialogPanelState()
}}}}else{if(!e.shiftKey&&Event.KEY_UP==e.keyCode){if(this.getComponent().isDateEditorLayoutCreated){this.changeYearMonthPopupValue(e,-1)
}else{if(this.getComponent().isTimeEditorLayoutCreated){return true
}}}else{if(!e.shiftKey&&Event.KEY_DOWN==e.keyCode){if(this.getComponent().isDateEditorLayoutCreated){this.changeYearMonthPopupValue(e,1)
}else{if(this.getComponent().isTimeEditorLayoutCreated){return true
}}}}}}}break;
case 13:if(this.getComponent().isEditorVisible&&!this.getComponent().isTimeEditorLayoutCreated){this.getComponent().hideDateEditor(true);
var date=new Date(this.getComponent().dateEditorYear,this.getComponent().dateEditorMonth,1);
this.getComponent().selectDate(date,false,null)
}else{if(this.getComponent().isEditorVisible&&this.getComponent().isTimeEditorLayoutCreated){this.getComponent().hideTimeEditor(true);
this.getComponent().isTimeEditorLayoutCreated=false
}else{if(!this.getComponent().isEditorVisible){this.getComponent().doCollapse();
this.getComponent().showApplyButton=false;
this.getComponent().setInputField(this.getComponent().getSelectedDate()!=null?this.getComponent().getSelectedDateString(this.getComponent().params.datePattern):"",e);
this.destroy()
}}}break;
case 191:if(!this.getComponent().isEditorVisible){this.getComponent().resetSelectedDate()
}break;
case 84:if(!this.getComponent().isEditorVisible){this.getComponent().showTimeEditor();
this.getTimeControlInput().focus()
}break;
case 89:if(!this.getComponent().isEditorVisible){this.getComponent().isTimeEditorLayoutCreated=false;
this.getComponent().showDateEditor()
}else{if(this.getComponent().isEditorVisible){if(!this.currentYMPopupMode){this.currentYMPopupMode="M"
}else{if(this.currentYMPopupMode=="Y"){this.currentYMPopupMode="M"
}else{if(this.currentYMPopupMode=="M"){this.currentYMPopupMode="Y"
}}}}}case 9:break;
default:break
}Event.stop(e);
return false
}});
var SipSingleCalendar={init:function(){this.LastOpenedSingleCalendarInstance=null
},singleCalendarOnScrollHandler:function(){if(SipSingleCalendar.LastOpenedSingleCalendarInstance&&$(SipSingleCalendar.LastOpenedSingleCalendarInstance)){$(SipSingleCalendar.LastOpenedSingleCalendarInstance).style.display="none"
}},addKeyHandler:function(calendar,handler){Event.observe(calendar,handler)
},defaultKalendarHandler:function(e){var code=e.keyCode
}};
function customExpand(e,input,button){var cooficient=250;
if(this.isVisible){this.doCollapse();
if(input==this.customInput){return true
}}this.skipEventOnCollapse=false;
if(e&&e.type=="click"){this.skipEventOnCollapse=true
}if(!this.params.popup||this.isVisible){return 
}var element=$(this.id);
SipSingleCalendar.LastOpenedSingleCalendarInstance=this.id;
if(this.invokeEvent("expand",element,e)){var iframe=null;
if(Richfaces.browser.isIE6){iframe=$(this.IFRAME_ID)
}var base=$(this.POPUP_ID);
var baseInput=input;
this.customInput=baseInput;
var baseButton=button;
if(baseInput&&baseInput.value!=undefined){this.selectDate(baseInput.value,false,{event:e,element:element});
var field=$(this.INPUT_DATE_ID);
if(field){field.value=baseInput.value
}}var offsetBase=Position.cumulativeOffset(baseButton);
if(this.params.showInput){var offsetBase1=Position.cumulativeOffset(baseInput);
offsetBase=[offsetBase[0]<offsetBase1[0]?offsetBase[0]:offsetBase1[0],offsetBase[1]<offsetBase1[1]?offsetBase[1]:offsetBase1[1]];
var offsetDimInput=Richfaces.Calendar.getOffsetDimensions(baseInput)
}var inHeight=(window.innerHeight?window.innerHeight:document.documentElement.clientHeight);
var eClientY=e.clientY;
var v1=inHeight-eClientY;
if(!this.defaultDirection){this.defaultDirection=this.params.direction
}if(v1<=cooficient){this.params.direction="top-right"
}else{this.params.direction=this.defaultDirection
}var offsetDimBase=Richfaces.Calendar.getOffsetDimensions(base);
var offsetDimButton=Richfaces.Calendar.getOffsetDimensions(baseButton);
var offsetTemp=(window.opera?[0,0]:Position.realOffset(baseButton));
var o={left:offsetBase[0]-offsetTemp[0],top:offsetBase[1]-offsetTemp[1],width:offsetDimBase.width,height:(offsetDimInput&&offsetDimInput.height>offsetDimButton.height?offsetDimInput.height:offsetDimButton.height)};
Richfaces.Calendar.setElementPosition(element,o,this.params.jointPoint,this.params.direction,this.popupOffset);
if(iframe){iframe.style.left=element.style.left;
iframe.style.top=element.style.top;
var edim=Richfaces.Calendar.getOffsetDimensions(element);
iframe.style.width=edim.width+"px";
iframe.style.height=edim.height+"px";
Element.show(iframe)
}Element.show(element);
this.isVisible=true;
Event.observe(window.document,"click",this.eventOnCollapse,false)
}}
PNGFIX={};
PNGFIX.getContextPath=function(){var href=window.location.href;
if(href!=void (0)){var array=href.split("/");
if(array.length>=3){return"/"+array[3]
}}return""
};
PNGFIX.CLEAR=PNGFIX.getContextPath()+"/images/clear.gif";
PNGFIX.POSTFIX=".jsf";
PNGFIX.STACK=new Array();
PNGFIX.RE_PNG=/\.png/i;
PNGFIX.RE_PNGJSF=/\.png\.jsf/i;
PNGFIX.put=function(e,processChild){if(!isIE()){return 
}var i={};
i.processChild=processChild;
i.element=e;
PNGFIX.STACK.push(i)
};
PNGFIX.pngfix=function(){if(!isIE()){return 
}PNGFIX.initPostFix();
var l=PNGFIX.STACK.length;
for(var i=0;
i<l;
i++){var element=PNGFIX.STACK[i].element;
var processChild=PNGFIX.STACK[i].processChild;
PNGFIX.pngfixElement(element,processChild)
}};
PNGFIX.pngfixElement=function(element,processChild){if(!isIE()){return 
}if(processChild&&element){PNGFIX._pngfix(element);
var children=element.getElementsByTagName("*");
for(var j=0;
j<children.length;
j++){PNGFIX._pngfix(children[j])
}}else{if(element){PNGFIX._pngfix(element)
}}},PNGFIX.pngfixElements=function(){if(!isIE()){return 
}for(var i=0;
i<arguments.length;
i++){PNGFIX.pngfixElement(arguments[i],true)
}},PNGFIX.initPostFix=function(){if(!PNGFIX.POSTFIX){var postFix=PNGFIX.CLEAR.substring(PNGFIX.CLEAR.lastIndexOf(".")+1);
postFix=(postFix=="jsf")?"jsf":"";
PNGFIX.POSTFIX=postFix
}return PNGFIX.POSTFIX
};
PNGFIX.pngfixImg=function(img){if(!isIE()){return 
}var es=img.style;
if(img.src&&img.src.match(PNGFIX.RE_PNG)&&!es.filter){if(img.height&&img.height!=0){es.height=img.height+"px"
}else{if(img.clientHeight){es.height=img.clientHeight+"px"
}}if(img.width&&img.width!=0){es.width=img.width
}else{if(img.clientHeight){es.width=img.clientWidth+"px"
}}es.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+img.src+"',sizingMethod='crop')";
img.src=PNGFIX.CLEAR
}};
PNGFIX.pngfixImgPanel=function(element){if(isIE()){var src;
if(/\.png.jsf$/.test(element.src)){src=element.src;
element.src=PNGFIX.CLEAR
}if(src){element.runtimeStyle.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"',sizingMethod='scale')"
}}};
PNGFIX._pngfix=function(el){var es=el.style;
var elb=el.currentStyle.backgroundImage;
if(elb.indexOf("spn_close_btn.png")!=-1){return 
}if(el.src&&(el.src.match(PNGFIX.RE_PNGJSF)||el.src.match(PNGFIX.RE_PNG))&&!es.filter&&!el.onload){PNGFIX.pngfixImg(el)
}else{if(elb.match(PNGFIX.RE_PNGJSF)){var path=elb.split('"');
var rep=(el.currentStyle.backgroundRepeat=="no-repeat")?"crop":"scale";
es.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+path[1]+"',sizingMethod='"+rep+"')";
es.height=Richfaces.getComputedStyle(el,"height");
es.backgroundImage="none"
}}};
PNGFIX.pngChangeFilter=function(id,el,img,height,srcPrefix){PNGFIX.initPostFix();
if(!height){height=Richfaces.getComputedStyle(el,"height")
}try{var es=el.style;
var rep=(el.currentStyle.backgroundRepeat=="no-repeat")?"crop":"scale";
es.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+srcPrefix+img+PNGFIX.POSTFIX+"',sizingMethod='"+rep+"')";
es.height=height;
es.backgroundImage="none"
}catch(e){}};
PNGFIX.getImagePrefix=function(imgSrc){return imgSrc.substring(0,imgSrc.lastIndexOf("/")+1)
};
isIE=function(){return/MSIE (5\.5|6).+Win/.test(navigator.userAgent)
}

ExtDragIndicator={init:function(event){var ieVersion=RichFaces.getIEVersion();
ExtDragIndicator.isIE6=(ieVersion&&ieVersion<7)
},setContent:function(name,single,params){Element.clearChildren(this);
var p=DnD.getDnDDefaultParams(this);
if(!p){p={}
}if(params){Object.extend(p,params)
}if(!p["marker"]){if(p[name]){p["marker"]=p[name]
}else{p["marker"]=this.markers[name]
}}var parts;
if(single){parts=this.indicatorTemplates["single"]
}else{parts=this.indicatorTemplates["multi"]
}new Insertion.Top(this,parts.invoke("getContent",p).join(""));
if(ExtDragIndicator.isIE6){this.initIFrame()
}},show:function(){if(!this.floatedToBody){if(!this.realParent){this.realParent=this.parentNode;
this._nextSibling=this.nextSibling
}this.realParent.removeChild(this);
document.body.appendChild(this);
this.floatedToBody=true
}Element.show(this);
this.style.position="absolute"
},hide:function(){Element.hide(this);
this.style.position="";
this.offsets=undefined;
this.leave();
if(this.floatedToBody&&this.realParent){document.body.removeChild(this);
if(this._nextSibling){this.realParent.insertBefore(this,this._nextSibling)
}else{this.realParent.appendChild(this)
}this.floatedToBody=false
}},position:function(x,y){if(!this.offsets){Element.show(this);
this.style.position="absolute"
}Element.setStyle(this,{"left":x+"px","top":y+"px"})
},accept:function(){Element.removeClassName(this,"drgind_default");
Element.removeClassName(this,"drgind_reject");
Element.addClassName(this,"drgind_accept");
var acceptClass=this.getAcceptClass();
if(acceptClass){Element.addClassName(this,acceptClass)
}},reject:function(){Element.removeClassName(this,"drgind_default");
Element.removeClassName(this,"drgind_accept");
Element.addClassName(this,"drgind_reject");
var rejectClass=this.getRejectClass();
if(rejectClass){Element.addClassName(this,rejectClass)
}},leave:function(){Element.removeClassName(this,"drgind_accept");
Element.removeClassName(this,"drgind_default");
Element.addClassName(this,"drgind_reject");
var acceptClass=this.getAcceptClass();
var rejectClass=this.getRejectClass();
if(acceptClass){Element.removeClassName(this,acceptClass)
}if(rejectClass){Element.removeClassName(this,rejectClass)
}},getAcceptClass:function(){return this.ils_acceptClass
},getRejectClass:function(){return this.ils_rejectClass
},initIFrame:function(){var iframe=document.createElement("iframe");
Element.addClassName(iframe,"rich-dragindicator-iframe");
this.insertBefore(iframe,this.firstChild);
var table=iframe.nextSibling;
iframe.style.width=table.offsetWidth+"px";
iframe.style.height=table.offsetHeight+"px"
}};
function createExtDragIndicator(elt,acceptClass,rejectClass){Object.extend(elt,ExtDragIndicator);
elt.init();
elt.ils_acceptClass=acceptClass;
elt.ils_rejectClass=rejectClass
}
DnD.ExtSimpleDraggable=Class.create();
Object.extend(DnD.ExtSimpleDraggable.prototype,DnD.Draggable.prototype);
Object.extend(DnD.ExtSimpleDraggable.prototype,{initialize:function(id,options){this.id=$(id);
if(!this.id){return 
}this.options=options;
this.dragIndicatorId=this.options.dragIndicator;
this.eventMouseDown=this.initDrag.bindAsEventListener(this);
Event.observe(this.id,"mousedown",this.eventMouseDown);
this.form=this.id;
while(this.form&&!/^form$/i.test(this.form.tagName)){this.form=this.form.parentNode
}this.enableDraggableCursors(this.options.grab,this.options.grabbing)
},getDnDDragParams:function(){if(this.options.dndParams){return this.options.dndParams.parseJSON(EventHandlersWalk)
}return null
},getIndicator:function(){var dragIndicator=$(this.dragIndicatorId);
if(!dragIndicator){dragIndicator=this.getOrCreateDefaultIndicator()
}return dragIndicator
},ondragstart:function(event,drag){this.showDropTargets();
drag.params=this.options.parameters;
drag.params[this.id]=this.id;
this.setIndicator(event);
this.getIndicator().leave();
if(this.form){drag.params[this.form.id]=this.form.id
}},ondragend:function(event,drag){this.hideDropTargets()
},getContentType:function(){return this.options.dragType
},getDraggableOptions:function(){return this.options
},initDrag:function(event){if(Event.isLeftClick(event)){var src=Event.element(event);
if(src.tagName&&/^INPUT|SELECT|OPTION|BUTTON|TEXTAREA$/i.test(src.tagName)){return 
}Event.stop(event);
this.startDrag(event)
}},showDropTargets:function(){var prefix=this.id.id.replace("hdrag","hdrop");
var spans=this.id.up("thead").getElementsBySelector("span.extdt-hdrop");
for(i=0;
i<spans.length;
i++){var s=spans[i];
if(s.id.indexOf(prefix)==-1){s.style.visibility="visible";
s.childNodes[0].style.visibility="hidden";
s.childNodes[1].style.visibility="hidden"
}}},hideDropTargets:function(){var prefix=this.id.id.replace("hdrag","hdrop");
var spans=this.id.up("thead").getElementsBySelector("span.extdt-hdrop");
for(i=0;
i<spans.length;
i++){var s=spans[i];
if(s.id.indexOf(prefix)==-1){s.style.visibility="hidden";
s.childNodes[0].style.visibility="hidden";
s.childNodes[1].style.visibility="hidden"
}}}})

DnD.ExtSimpleDropZone=Class.create();
Object.extend(DnD.ExtSimpleDropZone.prototype,DnD.Dropzone.prototype);
Object.extend(DnD.ExtSimpleDropZone.prototype,{initialize:function(id,options){this.id=id;
var element=$(id);
if(!element){return 
}this.element=element;
if(options.acceptedTypes){this.acceptedTypes=options.acceptedTypes
}else{this.acceptedTypes=[]
}if(options.typeMapping){this.typeMapping=options.typeMapping
}else{this.typeMapping={}
}if(options.cursorTypeMapping){this.cursorTypeMapping=options.cursorTypeMapping
}else{this.cursorTypeMapping={}
}this.mouseoverBound=this.mouseover.bindAsEventListener(this);
this.mouseoutBound=this.mouseout.bindAsEventListener(this);
this.mouseupBound=this.mouseup.bindAsEventListener(this);
Event.observe(element,"mouseover",this.mouseoverBound);
Event.observe(element,"mouseout",this.mouseoutBound);
Event.observe(element,"mouseup",this.mouseupBound);
this.options=options||{};
this.enableDropzoneCursors(options.acceptCursor,options.rejectCursor)
},getDropzoneOptions:function(){return this.options
},getDnDDropParams:function(){if(this.options.dndParams){return this.options.dndParams.parseJSON(EventHandlersWalk)
}return null
},mouseover:function(event){if(window.drag){this.dragEnter(event);
this.element.childNodes[0].style.visibility="visible";
this.element.childNodes[1].style.visibility="visible"
}},mouseup:function(event){this.dragUp(event)
},mouseout:function(event){if(window.drag){this.dragLeave(event);
this.element.childNodes[0].style.visibility="hidden";
this.element.childNodes[1].style.visibility="hidden"
}},getAcceptedTypes:function(){return this.acceptedTypes
},getTypeMapping:function(){return this.typeMapping
},getCursorTypeMapping:function(){return this.cursorTypeMapping
},drop:function(event,drag){},onafterdrag:function(drag){if(this.options.onafterdrag){this.options.onafterdrag.call(this,drag)
}}})

hScroller=Class.create();
hScroller.CONTROL={};
hScroller.CONTROL.FORWARD="1";
hScroller.CONTROL.BACK="2";
Object.extend(hScroller.prototype,{scrollStep:0,scrollRange:0,scrollingStep:30,scrollMutex:false,initialized:false,itemsNCache:{},initialize:function(id,target,items,handlers,widthCorrection){this.id=id;
this.target=target;
this.items=items;
this.handlers=handlers;
this.widthCorrection=widthCorrection;
this.itemsNCache={};
this.initialized=false
},getTargetWidth:function(){var targetWidth=this.target.getScrollWidth();
if(this.widthCorrection){targetWidth=targetWidth+this.widthCorrection
}this.targetWidth=targetWidth;
return targetWidth
},init:function(force){if(!this.initialized||force){this.targetWidth=this.getTargetWidth();
this.calculateScrollWidth();
this.calculateScrollPosition();
this.initialized=true;
this.scrollMutex=false
}},isReady:function(){return this.target.offsetWidth!=0
},isScrollNeed:function(){if(!this.isReady()){return false
}this.calculateScrollWidth();
return(this.target.offsetWidth<this.scrollWidth)
},calculateScrollWidth:function(){var w=0;
var l=this.items.length;
for(var i=0;
i<l;
i++){var item=this.items[i];
if(!item){continue
}if(item){item.start=w;
w=w+item.offsetWidth;
if(!isIE()){w+=Richfaces.getComputedStyleSize(item,"margin-left");
w+=Richfaces.getComputedStyleSize(item,"margin-right")
}else{ml=Richfaces.getComputedStyleSize(item,"marginLeft");
mr=Richfaces.getComputedStyleSize(item,"marginRight");
w+=(ml)?ml:0;
w+=(mr)?mr:0
}item.end=w
}}this.scrollWidth=w
},calculateScrollPosition:function(){var l=this.target.scrollLeft;
var r=l+this.targetWidth;
this.left=0;
var length=this.items.length;
for(var i=0;
i<length;
i++){var item=this.items[i];
if(!item){continue
}if(item.start<=l){this.left=i
}if(item.start<=r){this.right=i
}}if(l==0&&this.handlers["onleft"]){this.handlers["onleft"](false)
}else{if(this.handlers["onleft"]){this.handlers["onleft"](true)
}}if(r==this.scrollWidth&&this.handlers["onright"]){this.handlers["onright"](false)
}else{if(this.handlers["onright"]){this.handlers["onright"](true)
}}},forward:function(){var l=this.target.scrollLeft;
var r=l+this.targetWidth;
if(r<this.scrollWidth){var item=this.items[this.right];
var end;
if(item.end>r){end=item.end-this.targetWidth
}else{if(this.right+1<this.items.length){end=this.items[this.right+1].end-this.targetWidth
}}this._scroll(end)
}},back:function(){var l=this.target.scrollLeft;
var r=l+this.targetWidth;
if(l>0){var item=this.items[this.left];
var start;
if(item.start<l){start=item.start
}else{if(this.left-1>=0){start=this.items[this.left-1].start
}}this._scroll(start)
}},_scroll:function(p){if(this.scrollMutex||Object.isUndefined(p)){return 
}this.scrollMutex=true;
if(this.scrollInterval){return 
}if(p>this.target.scrollLeft){this.scrollInterval=window.setInterval(function(){this._scrollF(p)
}.bind(this),50)
}else{this.scrollInterval=window.setInterval(function(){this._scrollB(p)
}.bind(this),50)
}},_scrollF:function(end){if(end<0){this._finish();
return 
}var i=this.target.scrollLeft;
i=i+this.scrollingStep;
if(i>=end){this.target.scrollLeft=end;
this.calculateScrollPosition();
this._finish();
return 
}else{this.target.scrollLeft=i
}},_scrollB:function(start){if(start<0){this._finish();
return 
}var i=this.target.scrollLeft;
i=i-this.scrollingStep;
if(i<=start){this.target.scrollLeft=start;
this.calculateScrollPosition();
this._finish();
return 
}else{this.target.scrollLeft=i
}},_finish:function(){if(this.scrollInterval){window.clearInterval(this.scrollInterval)
}this.scrollInterval=null;
this.scrollMutex=false;
this.scrollingStep=30;
if(this.callback){this.callback()
}},_doScrolling:function(forward){var scrollLeft=this.target.scrollLeft;
if(forward){if(scrollLeft+this.scrollingStep<this.scrollWidth-this.targetWidth){this.target.scrollLeft=scrollLeft+this.scrollingStep
}else{this.target.scrollLeft=this.scrollWidth-this.targetWidth
}}else{if(scrollLeft-this.scrollingStep>0){this.target.scrollLeft=scrollLeft-this.scrollingStep
}else{this.target.scrollLeft=0
}}},fireScrolling:function(forward){if(this.intervalId){return 
}if(forward){this.intervalId=window.setInterval(function(){this._doScrolling(true)
}.bind(this),50)
}else{this.intervalId=window.setInterval(function(){this._doScrolling(false)
}.bind(this),50)
}},startScrolling:function(e,forward){this.init();
if(this.scrollMutex||this.timeoutId){return 
}this.scrollMutex=true;
this.timeoutId=window.setTimeout(function(){this.fireScrolling(forward)
}.bind(this),200)
},endScrolling:function(forward){window.clearTimeout(this.timeoutId);
window.clearInterval(this.intervalId);
this.intervalId=null;
this.timeoutId=null;
this.calculateScrollPosition();
this.scrollMutex=false
},bindControl:function(env,controlType){this.unbindControls(env,controlType);
if(hScroller.CONTROL.FORWARD==controlType){Event.observe(env,"click",this.forward.bindAsEventListener(this));
Event.observe(env,"mousedown",function(e){this.startScrolling(e,true)
}.bind(this));
Event.observe(env,"mouseup",function(){this.endScrolling(true)
}.bind(this));
Event.observe(env,"mouseout",function(){this.endScrolling(true)
}.bind(this))
}else{Event.observe(env,"click",this.back.bindAsEventListener(this));
Event.observe(env,"mousedown",function(e){this.startScrolling(e,false)
}.bind(this));
Event.observe(env,"mouseup",function(){this.endScrolling(false)
}.bind(this));
Event.observe(env,"mouseout",function(){this.endScrolling(false)
}.bind(this))
}Event.observe(env,"drag",function(){return false
})
},activateTargetItem:function(callback,env){this.callback=callback;
if(this.isScrollNeed()){var n=this.findTargetNumber(env);
var start=this.items[n].start;
var end=this.items[n].end;
var l=this.target.scrollLeft;
var r=l+this.targetWidth;
if(start<l){this.scrollingStep=(l-start)/5>30?(l-start)/5:30;
this._scroll(start)
}else{if(end>r){this.scrollingStep=(end-r)/5>30?(end-r)/5:30;
this._scroll(end-this.targetWidth)
}else{if(this.callback){this.callback();
this.callback=null
}}}}else{try{this.target.scrollLeft=0
}catch(e){}if(this.callback){this.callback();
this.callback=null
}}},findTargetNumber:function(env){if(this.itemsNCache[env.id]){return this.itemsNCache[env.id]
}for(var i=0;
i<this.items.length;
i++){if(this.items[i]&&env.id==this.items[i].id){this.itemsNCache[env.id]=i;
return i
}}},correctScroll:function(callback){this.callback=callback;
if((this.scrollWidth-this.targetWidth)<this.target.scrollLeft){this._scroll(this.scrollWidth-this.targetWidth)
}else{this._finish()
}},unbindControls:function(env,controlType){Event.stopObserving(env,"click");
Event.stopObserving(env,"mousedown");
Event.stopObserving(env,"mouseup");
Event.stopObserving(env,"mouseout");
Event.stopObserving(env,"drag")
}})

if(!window.DW){window.DW={}
}if(!window.Siperian){window.Siperian={}
}function discardElement(element){var garbageBin=document.getElementById("IELeakGarbageBin");
if(!garbageBin){garbageBin=document.createElement("DIV");
garbageBin.id="IELeakGarbageBin";
garbageBin.style.display="none";
document.body.appendChild(garbageBin)
}window.RichFaces.Memory.clean(element);
garbageBin.appendChild(element);
garbageBin.innerHTML=""
}Selection={};
Selection.eventHandler=function(event){Event.stop(event)
};
Selection.eventHandler=Selection.eventHandler.bindAsEventListener(Selection);
Selection.disableSelection=function(element){if(typeof element.onselectstart!="undefined"){Event.observe(element,"selectstart",this.eventHandler)
}else{if(typeof element.style.MozUserSelect!="undefined"){element.style.MozUserSelect="none"
}else{Event.observe(element,"mousedown",this.eventHandler)
}}};
Selection.enableSelection=function(element){if(typeof element.onselectstart!="undefined"){Event.stopObserving(element,"selectstart",this.eventHandler)
}else{if(typeof element.style.MozUserSelect!="undefined"){element.style.MozUserSelect=""
}else{Event.stopObserving(element,"mousedown",this.eventHandler)
}}};
SipModalPanel=Class.create();
var ieVersion=RichFaces.getIEVersion();
if(ieVersion&&ieVersion<7){SipModalPanel.disableSelects=true
}SipModalPanel.panels=new Array();
SipModalPanel.activePanels=new Array();
function getSizeElement(){var element;
var element;
if(true){element=document.documentElement
}else{element=document.body
}return element
}SipModalPanel.getMinimumSize=function(size){return Math.max(size,2*SipModalPanel.Sizer.INITIAL_MIN+2)
};
SipModalPanel.prototype={initialize:function(id,options){this["rich:destructor"]="destroy";
this.markerId=$(id);
this.id=$(id+"Container");
this.options=options;
this.resizedAction=false;
this.baseZIndex=this.options.zindex?this.options.zindex:100;
this.minWidth=SipModalPanel.getMinimumSize(this.options.minWidth);
this.minHeight=SipModalPanel.getMinimumSize(this.options.minHeight);
this.div=id+"Div";
this.cursorDiv=id+"CursorDiv";
this.cdiv=id+"CDiv";
this.contentDiv=id+"ContentDiv";
this.contentTable=id+"ContentTable";
this.shadowDiv=id+"ShadowDiv";
this.backgroundDiv=id+"Bg";
this.backgroundImage=id+"BgImg";
this.panelDOMAttachment=this.options.panelDOMAttachment;
this.attachmentFormId=this.options.attachmentFormId;
this.isModal=this.options.isModal;
this.zoomFixNeeded=((RichFaces.getIEVersion()||0)==7);
this.borders=new Array();
if(this.options.resizeable){this.borders.push(new SipModalPanel.Border(id+"ResizerN",this,"N-resize",SipModalPanel.Sizer.N));
this.borders.push(new SipModalPanel.Border(id+"ResizerE",this,"E-resize",SipModalPanel.Sizer.E));
this.borders.push(new SipModalPanel.Border(id+"ResizerS",this,"S-resize",SipModalPanel.Sizer.S));
this.borders.push(new SipModalPanel.Border(id+"ResizerW",this,"W-resize",SipModalPanel.Sizer.W));
this.borders.push(new SipModalPanel.Border(id+"ResizerNWU",this,"NW-resize",SipModalPanel.Sizer.NWU));
this.borders.push(new SipModalPanel.Border(id+"ResizerNEU",this,"NE-resize",SipModalPanel.Sizer.NEU));
this.borders.push(new SipModalPanel.Border(id+"ResizerNEL",this,"NE-resize",SipModalPanel.Sizer.NEL));
this.borders.push(new SipModalPanel.Border(id+"ResizerSEU",this,"SE-resize",SipModalPanel.Sizer.SEU));
this.borders.push(new SipModalPanel.Border(id+"ResizerSEL",this,"SE-resize",SipModalPanel.Sizer.SEL));
this.borders.push(new SipModalPanel.Border(id+"ResizerSWL",this,"SW-resize",SipModalPanel.Sizer.SWL));
this.borders.push(new SipModalPanel.Border(id+"ResizerSWU",this,"SW-resize",SipModalPanel.Sizer.SWU));
this.borders.push(new SipModalPanel.Border(id+"ResizerNWL",this,"NW-resize",SipModalPanel.Sizer.NWL))
}if(this.options.moveable&&$(id+"Header")){this.header=new SipModalPanel.Border(id+"Header",this,"move",SipModalPanel.Header)
}this.markerId.component=this;
var eDiv=$(this.div);
if(eDiv.style.setExpression){if(SipModalPanel.disableSelects||Richfaces.getComputedStyle(eDiv,"position")!="fixed"){eDiv.style.position="absolute";
var eCursorDiv=$(this.cursorDiv);
eCursorDiv.style.position="absolute";
eDiv.style.zoom="1";
eCursorDiv.style.zoom="1";
var eCdiv=$(this.cdiv);
eCdiv.style.position="absolute";
eCdiv.mpUseExpr=true
}}this.pushPanel(id);
this.eventFirstOnfocus=this.firstOnfocus.bindAsEventListener(this);
this.eventLastOnfocus=this.lastOnfocus.bindAsEventListener(this);
this.eventResizeHandler=this.onresizeHandler.bindAsEventListener(this);
this.firstHref=id+"FirstHref";
this.lastHref=id+"LastHref";
this.selectBehavior=options.selectBehavior;
if(!this.isModal){var eDiv=$(this.div);
eDiv.style.display="none";
var eCursorDiv=$(this.cursorDiv);
eCursorDiv.style.display="none"
}this.pngfix();
if(!this.options.moveable){Event.observe(window,"resize",this.eventResizeHandler)
}},pushPanel:function(id){for(var i=0;
i<SipModalPanel.panels.length;
i++){var p=SipModalPanel.panels[i];
if(p&&p.id&&p.id.id==(id+"Container")){SipModalPanel.panels=SipModalPanel.panels.without(p);
break
}}SipModalPanel.panels.push(this)
},pngfix:function(){if(!isIE()){return 
}PNGFIX.pngfixImg($(this.backgroundImage+"TL"));
PNGFIX.pngfixImg($(this.backgroundImage+"TR"));
PNGFIX.pngfixImg($(this.backgroundImage+"BL"));
PNGFIX.pngfixImg($(this.backgroundImage+"BR"))
},updateBackroundsStyle:function(){var isIE7=/MSIE (7).+Win/.test(navigator.userAgent);
if(!isIE()||isIE7){return 
}var div=$(this.cdiv);
var width=div.offsetWidth;
var height=div.offsetHeight;
var halfWidth=new Number(parseInt(width/2));
var halfHeight=new Number(parseInt(height/2));
$(this.backgroundDiv+"TL").style.width=(halfWidth)+"px";
$(this.backgroundDiv+"TR").style.width=(halfWidth)+"px";
$(this.backgroundDiv+"BL").style.width=(halfWidth)+"px";
$(this.backgroundDiv+"BR").style.width=(halfWidth)+"px";
$(this.backgroundDiv+"TL").style.height=(halfHeight)+"px";
$(this.backgroundDiv+"TR").style.height=(halfHeight)+"px";
$(this.backgroundDiv+"BL").style.height=(halfHeight)+"px";
$(this.backgroundDiv+"BR").style.height=(halfHeight)+"px"
},width:function(){return this.getSizedElement().clientWidth
},height:function(){return this.getSizedElement().clientHeight
},getSizedElement:function(){if(!this._sizedElement){this._sizedElement=$(this.cdiv)
}return this._sizedElement
},getContentElement:function(){if(!this._contentElement){this._contentElement=this.options.autosized?$(this.contentTable):$(this.contentDiv)
}return this._contentElement
},destroy:function(){this._contentElement=null;
this._sizedElement=null;
SipModalPanel.panels=SipModalPanel.panels.without(this);
this.enableSelects();
SipModalPanel.activePanels=SipModalPanel.activePanels.without(this);
this.parent=null;
this.firstOutside=null;
this.lastOutside=null;
if(this.header){this.header.destroy();
this.header=null
}for(var k=0;
k<this.borders.length;
k++){this.borders[k].destroy()
}this.borders=null;
setTimeout(function(){if(this.floatedToBody||this.floatedToForm){var element=this.id;
var parent=element.parentNode;
if(parent){parent.removeChild(element);
discardElement(element)
}}}.bind(this),0);
this.markerId.component=null;
this.markerId=null
},initIframe:function(){if(this.contentWindow){Element.setStyle(this.contentWindow.document.body,{"margin":"0px 0px 0px 0px"})
}else{}if("transparent"==Element.getStyle(document.body,"background-color")){this.style.filter="alpha(opacity=0)";
this.style.opacity="0"
}this.style.backgroundColor="transparent"
},enableSelect:function(elt){if(elt._mdwProcessed){elt._mdwProcessed=undefined;
if(elt._mdwDisabled){elt.disabled=false;
elt._mdwDisabled=undefined
}if(typeof elt._mdwHidden!="undefined"){elt.style.visibility=elt._mdwHidden;
elt._mdwHidden=undefined
}}},disableSelect:function(elt){if(!elt._mdwProcessed){elt._mdwProcessed=true;
if("hide"==this.selectBehavior){if(elt.style.visibility!="hidden"){elt._mdwHidden=elt.style.visibility;
elt.style.visibility="hidden"
}}else{if(!elt.disabled){elt.disabled=true;
elt._mdwDisabled=true
}}}},enableInnerSelects:function(){if(SipModalPanel.disableSelects){var selects=this.id.getElementsByTagName("SELECT");
for(var k=0;
k<selects.length;
k++){this.enableSelect(selects[k])
}}},disableInnerSelects:function(){if(SipModalPanel.disableSelects){var selects=this.id.getElementsByTagName("SELECT");
for(var k=0;
k<selects.length;
k++){this.disableSelect(selects[k])
}}},enableSelects:function(){if(!SipModalPanel.disableSelects){return 
}var lastPanel=SipModalPanel.activePanels[SipModalPanel.activePanels.length-1];
var newLastPanel=SipModalPanel.activePanels[SipModalPanel.activePanels.length-2];
if(newLastPanel){if(lastPanel==this){newLastPanel.enableInnerSelects()
}}else{var children=document.body.childNodes;
for(var k=0;
k<children.length;
k++){var child=children[k];
if(!child.getElementsByTagName){continue
}var selects=child.getElementsByTagName("SELECT");
for(var i=0;
i<selects.length;
i++){this.enableSelect(selects[i])
}}}},disableOuterSelects:function(){if(!SipModalPanel.disableSelects){return 
}var lastPanel=SipModalPanel.activePanels.last();
if(lastPanel){lastPanel.disableInnerSelects();
this.enableInnerSelects()
}else{var children=document.body.childNodes;
var panel=$(this.id);
for(var k=0;
k<children.length;
k++){var child=children[k];
if(child==panel){continue
}if(!child.getElementsByTagName){continue
}var selects=child.getElementsByTagName("SELECT");
for(var i=0;
i<selects.length;
i++){if(!Element.isChildOf(selects[i],panel)){this.disableSelect(selects[i])
}}}}},setLeft:function(pos){var eCdiv=$(this.cdiv);
if(eCdiv.mpUseExpr){eCdiv.mpLeft=pos
}else{eCdiv.style.left=pos+"px"
}},setTop:function(pos){var eCdiv=$(this.cdiv);
if(eCdiv.mpUseExpr){eCdiv.mpTop=pos
}else{eCdiv.style.top=pos+"px"
}},firstOnfocus:function(event){var e=$(this.firstHref);
if(this.isModal&&e&&(SipModalPanel.activePanels.last()==this)){e.focus()
}},lastOnfocus:function(event){var e=$(this.lastHref);
if(this.isModal&&e&&(SipModalPanel.activePanels.last()==this)){e.focus()
}},formElements:"|a|input|select|button|textarea|",processAllFocusElements:function(root,callback){var idx=-1;
var tagName;
if(root.focus&&root.nodeType==1&&(tagName=root.tagName)&&(idx=this.formElements.indexOf(tagName.toLowerCase()))!=-1&&this.formElements.charAt(idx-1)==="|"&&this.formElements.charAt(idx+tagName.length)==="|"&&!root.disabled&&root.type!="hidden"&&(Richfaces.getComputedStyle(root,"display")!="none")&&(Richfaces.getComputedStyle(root,"visibility")!="hidden")){callback.call(this,root)
}else{if(root!=this.id){var child=root.firstChild;
while(child){if(!child.style||child.style.display!="none"){this.processAllFocusElements(child,callback)
}child=child.nextSibling
}}}},processTabindexes:function(input){if(!this.firstOutside&&!(input.tagName.toLowerCase()=="select"&&ModalPanel.disableSelects)){this.firstOutside=input
}this.lastOutside=input;
if(input.tabIndex&&!input.prevTabIndex){input.prevTabIndex=input.tabIndex
}input.tabIndex=undefined;
if(input.accesskey&&!input.prevAccesskey){input.prevAccesskey=input.accesskey
}input.accesskey=undefined
},restoreTabindexes:function(input){if(input.prevTabIndex){input.tabIndex=input.prevTabIndex;
input.prevTabIndex=undefined
}if(input.prevAccesskey){input.accesskey=input.prevAccesskey;
input.prevAccesskey=undefined
}},preventFocus:function(){this.processAllFocusElements(document,this.processTabindexes);
if(this.firstOutside){Event.observe(this.firstOutside,"focus",this.eventFirstOnfocus)
}if(this.lastOutside&&this.lastOutside!=this.firstOutside){Event.observe(this.lastOutside,"focus",this.eventLastOnfocus)
}},restoreFocus:function(){this.processAllFocusElements(document,this.restoreTabindexes);
if(this.firstOutside){Event.stopObserving(this.firstOutside,"focus",this.eventFirstOnfocus);
this.firstOutside=null
}if(this.lastOutside){Event.stopObserving(this.lastOutside,"focus",this.eventLastOnfocus);
this.lastOutside=null
}},replacePanelInDOM:function(element){if(this.panelDOMAttachment&&this.panelDOMAttachment=="body"&&!this.floatedToBody){this.parent=element.parentNode;
document.body.insertBefore(this.parent.removeChild(element),null);
this.floatedToBody=true
}if(this.panelDOMAttachment&&this.panelDOMAttachment=="form"&&!this.floatedToForm){this.parent=element.parentNode;
if(this.attachmentFormId&&this.attachmentFormId!=""&&$(this.attachmentFormId)){this.form=$(this.attachmentFormId);
this.form.insertBefore(this.parent.removeChild(element),null);
this.floatedToForm=true
}else{this.form=this.getPanelParentForm(element);
if(this.form){this.form.insertBefore(this.parent.removeChild(element),null);
this.floatedToForm=true
}}}},replacePanelInDOMBack:function(element){if(this.floatedToBody&&this.parent){document.body.removeChild(element);
this.parent.appendChild(element);
this.floatedToBody=false
}if(this.floatedToForm&&this.parent){this.form.removeChild(element);
this.parent.appendChild(element);
this.floatedToForm=false
}},getPanelParentForm:function(element){var forms=document.body.getElementsByTagName("form");
if(forms){for(var i=0;
i<forms.length;
i++){if(Element.isChildOf(element,forms[i])){return forms[i]
}}}},adjustZoominIE7:function(){var rect=document.body.getBoundingClientRect();
var newZoomLevel=Math.round((rect.right-rect.left)/document.body.clientWidth*100)+"%";
if(this.zoomLevel!=newZoomLevel){this.zoomLevel=newZoomLevel;
$(this.cdiv).setStyle({zoom:newZoomLevel});
window.scrollBy(1,0);
window.scrollBy(-1,0);
window.resizeBy(1,0);
window.resizeBy(-1,0)
}},cancelTimer:function(){if(this.timerId){clearInterval(this.timerId);
this.timerId=undefined
}},onresizeHandler:function(){if(this.shown){this.doShowAction(null,this.options,this.id)
}},show:function(event,opts){if(!this.shown&&this.invokeEvent("beforeshow",event,null,element)){var element=this.id;
this.doShowAction(event,opts,element)
}},doShowAction:function(event,opts,element){if(this.isModal){this.preventFocus()
}this.replacePanelInDOM(element);
if(this.zoomFixNeeded){this.cancelTimer();
this.timerId=setInterval(this.adjustZoominIE7.bind(this),100)
}var eCdiv=$(this.cdiv);
var forms=eCdiv.getElementsByTagName("form");
if(this.options.keepVisualState&&forms){this.formOnsubmit=this.setStateInput.bindAsEventListener(this);
for(var i=0;
i<forms.length;
i++){Event.observe(forms[i],"submit",this.formOnsubmit)
}}var eIframe;
if(SipModalPanel.disableSelects&&!this.iframe){this.iframe=this.id.id+"IFrame";
new Insertion.Top(eCdiv,'<iframe src="javascript:\'\'" frameborder="0" scrolling="no" id="'+this.iframe+'" class="spn-mpnl-iframe" style="width: 1px; height: 1px;"></iframe>');
eIframe=$(this.iframe);
Event.observe(eIframe,"load",this.initIframe.bindAsEventListener(eIframe))
}var options={};
this.userOptions={};
if(!eCdiv.mpSet){Object.extend(options,this.options)
}if(opts){Object.extend(options,opts);
Object.extend(this.userOptions,opts)
}this.currentMinHeight=SipModalPanel.getMinimumSize((options.minHeight||options.minHeight==0)?options.minHeight:this.minHeight);
this.currentMinWidth=SipModalPanel.getMinimumSize((options.minWidth||options.minWidth==0)?options.minWidth:this.minWidth);
var eContentElt=this.getContentElement();
if(!this.options.autosized){if(options.width&&options.width==-1){options.width=300
}if(options.height&&options.height==-1){options.height=200
}}if(options.width&&options.width!=-1){if(this.currentMinWidth>options.width){options.width=this.currentMinWidth
}eContentElt.style.width=options.width+(/px/.test(options.width)?"":"px")
}if(options.height&&options.height!=-1){if(this.currentMinHeight>options.height){options.height=this.currentMinHeight
}eContentElt.style.height=options.height+(/px/.test(options.height)?"":"px")
}eCdiv.mpSet=true;
if(this.isModal){this.disableOuterSelects()
}SipModalPanel.activePanels=SipModalPanel.activePanels.without(this);
SipModalPanel.activePanels.push(this);
var eDiv=$(this.div);
if(eDiv.style.position=="absolute"){var we='getSizeElement().clientWidth + "px"';
var he='getSizeElement().clientHeight + "px"';
eDiv.style.setExpression("width",we);
eDiv.style.setExpression("height",he);
var eCursorDiv=$(this.cursorDiv);
eCursorDiv.style.setExpression("width",we);
eCursorDiv.style.setExpression("height",he);
var le='-Position.cumulativeOffset(this.parentNode)[0] + getSizeElement().scrollLeft + "px"';
var te='-Position.cumulativeOffset(this.parentNode)[1] + getSizeElement().scrollTop + "px"';
eDiv.style.setExpression("left",le);
eDiv.style.setExpression("top",te);
eCursorDiv.style.setExpression("left",le);
eCursorDiv.style.setExpression("top",te);
var leftExpr='(this.mpLeft || 0) + -Position.cumulativeOffset(this.parentNode)[0] + getSizeElement().scrollLeft + "px"';
var topExpr='(this.mpTop || 0) + -Position.cumulativeOffset(this.parentNode)[1] + getSizeElement().scrollTop + "px"';
eCdiv.style.setExpression("left",leftExpr);
eCdiv.style.setExpression("top",topExpr)
}element.style.visibility="hidden";
Element.show(element);
this.correctShadowSizeEx();
if(options.left){var _left;
if(options.left!="auto"){_left=parseInt(options.left,10)
}else{var cw=getSizeElement().clientWidth;
var _width=this.width();
if(cw>=_width){_left=(cw-_width)/2
}else{_left=0
}}this.setLeft(Math.round(_left))
}if(options.top){var _top;
if(options.top!="auto"){_top=parseInt(options.top,10)
}else{var cw=getSizeElement().clientHeight;
var _height=this.height();
if(cw>=_height){_top=(cw-_height)/2
}else{_top=0
}}this.setTop(Math.round(_top))
}if(this.options.autosized){this.observerSize=window.setInterval(this.correctShadowSize.bindAsEventListener(this),500)
}this.doResizeOrMove(SipModalPanel.Sizer.Diff.EMPTY);
for(var k=0;
k<this.borders.length;
k++){this.borders[k].doPosition()
}if(this.header){this.header.doPosition()
}Element.hide(eCdiv);
element.style.visibility="";
this.lastOnfocus();
Element.show(eCdiv);
this.updateBackroundsStyle();
var event={};
event.parameters=opts||{};
this.shown=true;
this.invokeEvent("show",event,null,element)
},startDrag:function(border){for(var k=0;
k<this.borders.length;
k++){this.borders[k].hide()
}Selection.disableSelection(document.body);
Selection.disableSelection($(this.cdiv))
},endDrag:function(border){for(var k=0;
k<this.borders.length;
k++){this.borders[k].show();
this.borders[k].doPosition()
}Selection.enableSelection(document.body);
Selection.enableSelection($(this.cdiv))
},hide:function(event,opts){if(this.shown&&this.invokeEvent("beforehide",event,null,element)){this.currentMinHeight=undefined;
this.currentMinWidth=undefined;
if(this.isModal){this.restoreFocus();
this.enableSelects()
}if(this.zoomFixNeeded){this.cancelTimer()
}SipModalPanel.activePanels=SipModalPanel.activePanels.without(this);
var eDiv=$(this.div);
var eCdiv=$(this.cdiv);
if(eDiv.style.removeExpression){eDiv.style.removeExpression("width");
eDiv.style.removeExpression("height");
eDiv.style.removeExpression("left");
eDiv.style.removeExpression("top");
var eCursorDiv=$(this.cursorDiv);
eCursorDiv.style.removeExpression("width");
eCursorDiv.style.removeExpression("height");
eCursorDiv.style.removeExpression("left");
eCursorDiv.style.removeExpression("top");
eCdiv.style.removeExpression("left");
eCdiv.style.removeExpression("top")
}var element=$(this.id);
Element.hide(element);
this.replacePanelInDOMBack(element);
var event={};
event.parameters=opts||{};
if(this.options&&this.options.onhide){this.options.onhide(event)
}var forms=eCdiv.getElementsByTagName("form");
if(this.options.keepVisualState&&forms){for(var i=0;
i<forms.length;
i++){Event.stopObserving(forms[i],"submit",this.formOnsubmit)
}}this.shown=false;
if(this.options.autosized){window.clearInterval(this.observerSize)
}if(SipModalPanel.activePanels.length>0){SipModalPanel.activePanels.last().preventFocus()
}}},_getStyle:function(elt,name){return parseInt(elt.style[name].replace("px",""),10)
},doResizeOrMove:function(diff){var vetoes={};
var cssHash={};
var cssHashWH={};
var vetoeChange=false;
var newSize;
var eContentElt=this.getContentElement();
newSize=this._getStyle(eContentElt,"width");
var oldSize=newSize;
newSize+=diff.deltaWidth||0;
if(newSize>=this.currentMinWidth||this.options.autosized){if(diff.deltaWidth){cssHashWH.width=newSize+"px"
}}else{if(diff.deltaWidth){cssHashWH.width=this.currentMinWidth+"px";
vetoes.vx=oldSize-this.currentMinWidth
}vetoes.x=true
}if(vetoes.vx&&diff.deltaX){diff.deltaX=-vetoes.vx
}var eCdiv=$(this.cdiv);
if(diff.deltaX&&(vetoes.vx||!vetoes.x)){if(vetoes.vx){diff.deltaX=vetoes.vx
}var newPos;
newPos=this._getStyle(eCdiv,"left");
newPos+=diff.deltaX;
cssHash.left=newPos+"px"
}newSize=this._getStyle(eContentElt,"height");
var oldSize=newSize;
newSize+=diff.deltaHeight||0;
if(newSize>=this.currentMinHeight||this.options.autosized){if(diff.deltaHeight){cssHashWH.height=newSize+"px"
}}else{if(diff.deltaHeight){cssHashWH.height=this.currentMinHeight+"px";
vetoes.vy=oldSize-this.currentMinHeight
}vetoes.y=true
}if(vetoes.vy&&diff.deltaY){diff.deltaY=-vetoes.vy
}if(diff.deltaY&&(vetoes.vy||!vetoes.y)){if(vetoes.vy){diff.deltaY=vetoes.vy
}var newPos;
if(eCdiv.mpUseExpr){newPos=eCdiv.mpTop||0;
newPos+=diff.deltaY;
eCdiv.mpTop=newPos;
cssHash.top=newPos+"px"
}else{newPos=this._getStyle(eCdiv,"top");
newPos+=diff.deltaY;
cssHash.top=newPos+"px"
}}Element.setStyle(eContentElt,cssHashWH);
Element.setStyle(eCdiv,cssHash);
this.correctShadowSizeEx();
Object.extend(this.userOptions,cssHash);
Object.extend(this.userOptions,cssHashWH);
var w=this.width();
var h=this.height();
this.reductionData=null;
if(w<=2*SipModalPanel.Sizer.INITIAL_MAX){this.reductionData={};
this.reductionData.w=w
}if(h<=2*SipModalPanel.Sizer.INITIAL_MAX){if(!this.reductionData){this.reductionData={}
}this.reductionData.h=h
}if(this.header){this.header.doPosition()
}this.updateBackroundsStyle();
return vetoes
},_findForm:function(elt){var target=elt;
while(target){if(!target.tagName||target.tagName.toLowerCase()!="form"){target=target.parentNode
}else{break
}}return target
},setStateInput:function(e){var target=Event.element(e);
if(e&&target){target=this._findForm(target);
var input=document.createElement("input");
input.type="hidden";
input.id=this.markerId.id+"OpenedState";
input.name=this.markerId.id+"OpenedState";
input.value=this.shown?"true":"false";
target.appendChild(input);
var keys=$H(this.userOptions).keys();
if(keys){for(var i=0;
i<keys.length;
i++){input=document.createElement("input");
input.type="hidden";
input.id=this.id.id+"StateOption_"+keys[i];
input.name=this.id.id+"StateOption_"+keys[i];
input.value=this.userOptions[keys[i]];
target.appendChild(input)
}}return true
}},correctShadowSize:function(event){this.correctShadowSizeEx()
},correctShadowSizeEx:function(){var eShadowDiv=$(this.shadowDiv);
if(!eShadowDiv){return 
}var eIframe=$(this.iframe);
var dx=0;
var dy=0;
if(!Richfaces.browser.isIE){dx=eShadowDiv.offsetWidth-eShadowDiv.clientWidth;
dy=eShadowDiv.offsetHeight-eShadowDiv.clientHeight
}var w=this.width();
var h=this.height();
eShadowDiv.style.width=(w-dx)+"px";
eShadowDiv.style.height=(h-dy)+"px";
if(eIframe){eIframe.style.width=w+"px";
eIframe.style.height=h+"px"
}},invokeEvent:function(eventName,event,value,element){var eventFunction=this.options["on"+eventName];
var result;
if(eventFunction){var eventObj;
if(event){eventObj=event
}else{if(document.createEventObject){eventObj=document.createEventObject()
}else{if(document.createEvent){eventObj=document.createEvent("Events");
eventObj.initEvent(eventName,true,false)
}}}eventObj.rich={component:this};
eventObj.rich.value=value;
try{result=eventFunction.call(element,eventObj)
}catch(e){LOG.warn("Exception: "+e.Message+"\n[on"+eventName+"]")
}}if(result!=false){result=true
}return result
}};
Siperian.findModalPanel=function(id){if(id){var prefId=(id.charAt(0)==":"?id:":"+id);
for(var i=0;
i<SipModalPanel.panels.length;
i++){var pnl=SipModalPanel.panels[i];
if(pnl&&pnl.markerId){var pnlId=pnl.markerId.id;
if(pnlId){if(pnlId.length>=prefId.length){var substr=pnlId.substring(pnlId.length-prefId.length,pnlId.length);
if(substr==prefId){return pnl.markerId
}}}}}}};
Siperian.showModalPanel=function(id,opts,event){var invoke=(RichFaces.MSIE==RichFaces.navigatorType())?function(f){if(document.readyState!="complete"){var args=arguments;
var dis=this;
window.setTimeout(function(){args.callee.apply(dis,args)
},50)
}else{f()
}}:function(f){f()
};
var panel=$(id);
if(!panel){panel=Siperian.findModalPanel(id)
}invoke(function(){panel.component.show(event,opts)
})
};
Siperian.hideModalPanel=function(id,opts,event){var panel=$(id);
if(!panel){panel=Siperian.findModalPanel(id)
}panel.component.hide(event,opts)
}

SipModalPanel.Border=Class.create();
SipModalPanel.Border.prototype={initialize:function(id,modalPanel,cursor,sizer){this.id=id;
var element=$(id);
element.style.cursor=cursor;
this.boundStartDrag=this.startDrag.bindAsEventListener(this,new Date());
Event.observe(this.id,"mousedown",this.boundStartDrag);
this.modalPanel=modalPanel;
this.sizer=sizer;
this.boundDoDrag=this.doDrag.bindAsEventListener(this);
this.boundEndDrag=this.endDrag.bindAsEventListener(this)
},destroy:function(){if(this.doingDrag){Event.stopObserving(document,"mousemove",this.boundDoDrag);
Event.stopObserving(document,"mouseup",this.boundEndDrag)
}Event.stopObserving(this.id,"mousedown",this.boundStartDrag);
this.modalPanel=null
},show:function(){Element.show(this.id)
},hide:function(){Element.hide(this.id)
},startDrag:function(event){if(SipSingleCalendar){SipSingleCalendar.singleCalendarOnScrollHandler()
}this.doingDrag=true;
this.dragX=event.clientX;
this.dragY=event.clientY;
Event.observe(document,"mousemove",this.boundDoDrag);
Event.observe(document,"mouseup",this.boundEndDrag);
var eCursorDiv=$(this.modalPanel.cursorDiv);
eCursorDiv.style.cursor=$(this.id).style.cursor;
eCursorDiv.style.zIndex=10;
this.modalPanel.startDrag(this);
this.onselectStartHandler=document.onselectstart;
document.onselectstart=function(){return false
}
},doDrag:function(event){if(!this.doingDrag){return 
}var evtX=event.clientX;
var evtY=event.clientY;
var winSize=Richfaces.getWindowSize();
if(evtX<0){evtX=0
}else{if(evtX>=winSize.width){evtX=winSize.width-1
}}if(evtY<0){evtY=0
}else{if(evtY>=winSize.height){evtY=winSize.height-1
}}var dx=evtX-this.dragX;
var dy=evtY-this.dragY;
if(dx!=0||dy!=0){var id=this.id;
var diff=this.sizer.doDiff(dx,dy);
var doResize;
var element=$(this.modalPanel.cdiv);
if(diff.deltaWidth||diff.deltaHeight){doResize=this.modalPanel.invokeEvent("resize",event,null,element)
}else{if(diff.deltaX||diff.deltaY){doResize=this.modalPanel.invokeEvent("move",event,null,element)
}}var vetoes;
if(doResize){vetoes=this.modalPanel.doResizeOrMove(diff)
}if(vetoes){if(!vetoes.x){this.dragX=evtX
}else{if(!diff.deltaX){this.dragX-=vetoes.vx||0
}else{this.dragX+=vetoes.vx||0
}}if(!vetoes.y){this.dragY=evtY
}else{if(!diff.deltaY){this.dragY-=vetoes.vy||0
}else{this.dragY+=vetoes.vy||0
}}}}},endDrag:function(event){this.doingDrag=undefined;
Event.stopObserving(document,"mousemove",this.boundDoDrag);
Event.stopObserving(document,"mouseup",this.boundEndDrag);
this.modalPanel.endDrag(this);
this.modalPanel.doResizeOrMove(SipModalPanel.Sizer.Diff.EMPTY);
$(this.modalPanel.cursorDiv).style.zIndex=-200;
document.onselectstart=this.onselectStartHandler;
this.onselectStartHandler=null;
var id=this.id
},doPosition:function(){this.sizer.doPosition(this.modalPanel,$(this.id))
}};
SipModalPanel.Sizer=Class.create();
SipModalPanel.Sizer.INITIAL_MIN=4;
SipModalPanel.Sizer.INITIAL_MAX=40;
SipModalPanel.Sizer.Diff=Class.create();
SipModalPanel.Sizer.Diff.prototype={initialize:function(dX,dY,dWidth,dHeight){this.deltaX=dX;
this.deltaY=dY;
this.deltaWidth=dWidth;
this.deltaHeight=dHeight
}};
SipModalPanel.Sizer.Diff.EMPTY=new SipModalPanel.Sizer.Diff(0,0,0,0);
SipModalPanel.Sizer.prototype={initialize:function(){},doSetupSize:function(modalPanel,elt){var width=0;
var height=0;
var reductionData=modalPanel.reductionData;
if(reductionData){if(reductionData.w){width=reductionData.w/2
}if(reductionData.h){height=reductionData.h/2
}}if(width>0){if(elt.clientWidth>width){if(!elt.reducedWidth){elt.reducedWidth=elt.style.width
}elt.style.width=width+"px"
}else{if(width<SipModalPanel.Sizer.INITIAL_MAX&&elt.reducedWidth==SipModalPanel.Sizer.INITIAL_MAX+"px"){elt.style.width=width+"px"
}}}else{if(elt.reducedWidth){elt.style.width=elt.reducedWidth;
elt.reducedWidth=undefined
}}if(height>0){if(elt.clientHeight>height){if(!elt.reducedHeight){elt.reducedHeight=elt.style.height
}elt.style.height=height+"px"
}else{if(height<SipModalPanel.Sizer.INITIAL_MAX&&elt.reducedHeight==SipModalPanel.Sizer.INITIAL_MAX+"px"){elt.style.height=height+"px"
}}}else{if(elt.reducedHeight){elt.style.height=elt.reducedHeight;
elt.reducedHeight=undefined
}}},doSetupPosition:function(modalPanel,elt,left,top){elt.style.left=left+"px";
elt.style.top=top+"px"
},doPosition:function(modalPanel,elt){},doDiff:function(dx,dy){}};
SipModalPanel.Sizer.NWU=Object.extend(new SipModalPanel.Sizer(),{doPosition:function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,0,0)
},doDiff:function(dx,dy){return new SipModalPanel.Sizer.Diff(dx,dy,-dx,-dy)
}});
SipModalPanel.Sizer.N=new SipModalPanel.Sizer();
SipModalPanel.Sizer.N.doPosition=function(modalPanel,elt){elt.style.width=modalPanel.width()+"px";
this.doSetupPosition(modalPanel,elt,0,0)
};
SipModalPanel.Sizer.N.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,dy,0,-dy)
};
SipModalPanel.Sizer.NEU=new SipModalPanel.Sizer();
SipModalPanel.Sizer.NEU.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,modalPanel.width()-elt.clientWidth,0)
};
SipModalPanel.Sizer.NEU.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,dy,dx,-dy)
};
SipModalPanel.Sizer.NEL=new SipModalPanel.Sizer();
SipModalPanel.Sizer.NEL.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,modalPanel.width()-elt.clientWidth,0)
};
SipModalPanel.Sizer.NEL.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,dy,dx,-dy)
};
SipModalPanel.Sizer.E=new SipModalPanel.Sizer();
SipModalPanel.Sizer.E.doPosition=function(modalPanel,elt){elt.style.height=modalPanel.height()+"px";
this.doSetupPosition(modalPanel,elt,modalPanel.width()-elt.clientWidth,0)
};
SipModalPanel.Sizer.E.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,0,dx,0)
};
SipModalPanel.Sizer.SEU=new SipModalPanel.Sizer();
SipModalPanel.Sizer.SEU.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,modalPanel.width()-elt.clientWidth,modalPanel.height()-elt.clientHeight)
};
SipModalPanel.Sizer.SEU.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,0,dx,dy)
};
SipModalPanel.Sizer.SEL=new SipModalPanel.Sizer();
SipModalPanel.Sizer.SEL.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,modalPanel.width()-elt.clientWidth,modalPanel.height()-elt.clientHeight)
};
SipModalPanel.Sizer.SEL.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,0,dx,dy)
};
SipModalPanel.Sizer.S=new SipModalPanel.Sizer();
SipModalPanel.Sizer.S.doPosition=function(modalPanel,elt){elt.style.width=modalPanel.width()+"px";
this.doSetupPosition(modalPanel,elt,0,modalPanel.height()-elt.clientHeight)
};
SipModalPanel.Sizer.S.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(0,0,0,dy)
};
SipModalPanel.Sizer.SWL=new SipModalPanel.Sizer();
SipModalPanel.Sizer.SWL.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,0,modalPanel.height()-elt.clientHeight)
};
SipModalPanel.Sizer.SWL.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(dx,0,-dx,dy)
};
SipModalPanel.Sizer.SWU=new SipModalPanel.Sizer();
SipModalPanel.Sizer.SWU.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,0,modalPanel.height()-elt.clientHeight)
};
SipModalPanel.Sizer.SWU.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(dx,0,-dx,dy)
};
SipModalPanel.Sizer.W=new SipModalPanel.Sizer();
SipModalPanel.Sizer.W.doPosition=function(modalPanel,elt){elt.style.height=modalPanel.height()+"px";
this.doSetupPosition(modalPanel,elt,0,0)
};
SipModalPanel.Sizer.W.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(dx,0,-dx,0)
};
SipModalPanel.Sizer.NWL=new SipModalPanel.Sizer();
SipModalPanel.Sizer.NWL.doPosition=function(modalPanel,elt){this.doSetupSize(modalPanel,elt);
this.doSetupPosition(modalPanel,elt,0,0)
};
SipModalPanel.Sizer.NWL.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(dx,dy,-dx,-dy)
};
SipModalPanel.Header=new SipModalPanel.Sizer();
SipModalPanel.Header.doPosition=function(modalPanel,elt){};
SipModalPanel.Header.doDiff=function(dx,dy){return new SipModalPanel.Sizer.Diff(dx,dy,0,0)
}

if(!window.ExtendedDataTable){window.ExtendedDataTable={}
}ExtendedDataTable.DataTable=Class.create({initialize:function(id,options,onlayoutupdate,dragContext,onkeydown){this.id=id;
var table=$(this.id);
table.component=this;
this["rich:destructor"]="destroy";
this.groups=[];
this.dragContext=dragContext;
this.onkeydownhandler=onkeydown;
this.options=options;
if(onlayoutupdate){table.observe("rich:onlayoutupdate",onlayoutupdate)
}this.selectionManager=new ExtendedDataTable.SelectionManager(options,this);
this.forseSorting=this.options.forseSorting;
this.scrollable=this.options.scrollable!=null?this.options.scrollable:"true";
if(this.options.sortFunction){this.sortFct=this.options.sortFunction;
this.eventCellClicked=this.OnCellMouseClicked.bindAsEventListener(this)
}this.onGroupToggleFct=this.options.onGroupToggleFunction;
if(this.options.onColumnResize!=null){this.onColumnResize=this.options.onColumnResize;
this.columnWidths=""
}this.eventGroupRowClicked=this.OnGroupRowMouseClicked.bindAsEventListener(this);
this.minColumnWidth=this.options.minColumnWidth;
Utils.execOnLoad(function(){if(options.hoverRowClass){$A(this.selectionManager.getRows()).each(function(e){Event.observe(e,ClientUILib.isIE?"mouseenter":"mouseover",function(){Element.addClassName(e,options.hoverRowClass)
});
Event.observe(e,ClientUILib.isIE?"mouseleave":"mouseout",function(){Element.removeClassName(e,options.hoverRowClass)
})
})
}if(this.selectionManager){this.update(true)
}}.bind(this),Utils.Condition.ElementPresent(id+":od"),100)
},destroy:function(){if(this.tableB){var rows=this.tableB.rows;
rowCount=rows.length;
for(var i=0;
i<rowCount;
i++){Utils.DOM.Event.removeListeners(rows[i])
}}if(this.selectionManager){this.selectionManager.removeListeners()
}if(this.header){this.header.removeListeners();
var headerChildren=this.header.getColumnCells();
l=headerChildren.length;
for(var i=0;
i<l-1;
i++){Utils.DOM.Event.removeListeners(headerChildren[i])
}}if(this.groupRows){var l=this.groupRows.length;
for(var i=0;
i<l;
i++){Utils.DOM.Event.removeListeners(this.groupRows[i])
}}if(this.scrollingDiv){Event.stopObserving(this.scrollingDiv.element,"scroll")
}delete this.selectionManager;
delete this.header;
delete this.footer;
$(this.id).component=null;
this.table=null;
this.splashScreen=null;
this.mainDiv=null;
this.outerDiv=null;
this.tableB=null;
this.fakeIeRow=null;
this.fakeIeBodyRow=null;
this.cols=null;
this.scrollingDiv=null;
this.groupRows=null;
this.groups=null;
this.scrollPositionHolder=null
},showRow:function(rowIndex){var row=$(this.id+":n:"+rowIndex);
var offsetTop=this.tableB.offsetTop+row.offsetTop;
var scrollTop=this.scrollingDiv.getElement().scrollTop;
var dS=offsetTop-scrollTop;
if(dS<0){this.scrollingDiv.getElement().scrollTop=scrollTop+dS
}else{var scrollDivHeight=this.scrollingDiv.getHeight();
var rowHeight=row.getHeight();
dS=dS+rowHeight-scrollDivHeight;
if(dS>0){this.scrollingDiv.getElement().scrollTop=scrollTop+dS
}}},setColumnWidth:function(columnIndex,newWidth){if(columnIndex>=this.getColumnsNumber){return false
}else{this.getColumns()[columnIndex].width=newWidth
}},_findParentElement:function(event,element){var el=null;
if(ClientUILib.isSafari){var targetCell=event.currentTarget;
if(targetCell&&targetCell.tagName.toLowerCase()==element){el=targetCell
}else{var e=(event.target||event.srcElement);
while((e!=null)&&(e.tagName.toLowerCase()!=element)&&(e!=document)){e=e.parentNode
}if((e)&&(e.tagName.toLowerCase()==element)){el=e
}}}else{el=Event.findElement(event,element)
}return el
},OnCellMouseClicked:function(event){if(Event.element(event).tagName.toLowerCase()!="th"){var el=this._findParentElement(event,"th");
var columnId=(el)?el.id:null;
if(columnId&&(columnId!="")){this.showSplashScreen();
this.sortFct(event,columnId)
}Event.stop(event)
}},preSendAjaxRequest:function(){this.showSplashScreen()
},showSplashScreen:function(){var splshscr=this.splashScreen;
splshscr.className="extdt-ss-vsbl"
},hideSplashScreen:function(){this.splashScreen.className="extdt-ss-hdn"
},OnWindowResize:function(event){if(this.table){this.updateLayout()
}},getColumnsNumber:function(){return this.columnsNumber
},getColWidth:function(columnNumber){},getColumns:function(){return this.cols
},OnGroupRowMouseClicked:function(event){var groupRow=this._findParentElement(event,"tr");
var bExpanded=!(groupRow.getAttribute("expanded")=="true");
var sExpanded=bExpanded?"true":"false";
var groupIndex=parseInt(groupRow.getAttribute("groupindex"));
if(this.onGroupToggleFct){this.onGroupToggleFct(event,groupIndex)
}groupRow.setAttribute("expanded",sExpanded);
var imageDiv=groupRow.firstChild.firstChild.firstChild;
this.toggleImageSource(imageDiv);
this.setGroupExpanded(groupIndex,bExpanded);
Event.stop(event)
},toggleImageSource:function(imageDiv){var src=imageDiv.getAttribute("src");
var alternateSrc=imageDiv.getAttribute("alternatesrc");
imageDiv.setAttribute("src",alternateSrc);
imageDiv.setAttribute("alternatesrc",src)
},getColumnWidth:function(columnNumber){if((columnNumber<this.getColumnsNumber())&&(columnNumber>=0)){var col=this.getColumns()[columnNumber];
if(col.offsetWidth!=null){if(ClientUILib.isOpera){return parseInt(col.width)
}else{return col.offsetWidth
}}else{return parseInt(col.width)
}}else{return null
}},setGroupExpanded:function(iGroupIndex,bValue){var group=this.groups[iGroupIndex];
var sVisibility;
var sBorder;
var sEmptyCells;
if(bValue){sVisibility="";
sBorderStyle=""
}else{sVisibility="none";
sBorderStyle="none"
}var size=group.size();
for(var i=0;
i<size;
i++){group[i].style.display=sVisibility;
if(ClientUILib.isIE){var cells=group[i].childNodes;
var l=cells.length;
for(var j=0;
j<l;
j++){cells[j].style.borderStyle=sBorderStyle
}}}},createControls:function(){var id=this.id;
this.table=this.table||new ClientUI.common.box.Box(this.id+":tu",null,true);
var table=this.table;
this.splashScreen=$(this.id+":splashscreen");
this.mainDiv=this.mainDiv||new ClientUI.common.box.Box(this.id,null,true);
this.outerDiv=this.outerDiv||new ClientUI.common.box.Box(this.id+":od",null,true);
this.tableB=$(this.id+":n");
this.fakeIeRow=$(this.id+":fakeIeRow");
this.fakeIeBodyRow=$(this.id+":body:fakeIeRow");
this.header=this.header||new ExtendedDataTable.DataTable.header(this.id+":header",this);
this.header.minColumnWidth=this.minColumnWidth;
var colgroup=$(this.id+":colgroup:body");
this.cols=colgroup.getElementsByTagName("col");
this.columnsNumber=this.cols.length;
this.scrollingDiv=this.scrollingDiv||new ClientUI.common.box.Box(this.id+":sd",null,true);
this.groupRows=[];
this.footer=$(this.id+":footer");
if(ClientUILib.isOpera){this.scrollingDiv.setStyle({overflow:"scroll",width:this.mainDiv.getWidth()});
this.table.setStyle({width:this.mainDiv.getWidth()})
}var i=0;
var groupRow=$(id+":group-row:"+i);
while(groupRow!=null){this.groupRows[i]=groupRow;
Utils.DOM.Event.removeListeners(groupRow);
Utils.DOM.Event.observe(groupRow,"click",this.eventGroupRowClicked);
i++;
groupRow=$(id+":group-row:"+i)
}},getScrollbarWidth:function(){var sd=this.scrollingDiv.getElement();
return sd.offsetWidth-sd.clientWidth
},validateColumnsWidth:function(columns,excessWidth){var endIndex=columns.length-1;
var i=endIndex-1;
while((i>=0)&&(excessWidth>0)){if(ClientUILib.isIE){var colWidth=parseInt(this.getColumns()[i].width)-1
}else{var colWidth=this.header.getColumnWidth(i)
}var spareWidth=Math.max(colWidth-this.minColumnWidth,this.minColumnWidth);
var dW;
if(spareWidth>=excessWidth){dW=excessWidth
}else{dW=spareWidth
}this.setColumnWidth(i,colWidth-dW);
this.header.setColumnWidth(i,colWidth-dW);
excessWidth-=dW;
i--
}},getFooterHeight:function(){if(this.footer){return this.footer.getHeight()
}else{return 0
}},updateLayout:function(){ClientUILib.log(ClientUILogger.INFO,"updateLayout");
var mainDivWidth=this.mainDiv.getWidth();
var maxAllowedWidth=mainDivWidth;
if(this.scrollable=="true"){var mainDivHeight=this.mainDiv.getHeight();
var dataTableHeight;
var nonDataElementsHeight=this.header.getHeight()+this.getFooterHeight()+this.header.getCaptionHeight();
dataTableHeight=mainDivHeight-nonDataElementsHeight;
this.scrollingDiv.setStyle({height:dataTableHeight+"px"});
var scrollingDivElement=this.scrollingDiv.getElement();
var scrollbarWidth=scrollingDivElement.offsetWidth-scrollingDivElement.clientWidth;
maxAllowedWidth=mainDivWidth-scrollbarWidth;
this.scrollingDiv.setStyle({width:this.outerDiv.getWidth()+"px"})
}var cols=this.getColumns();
var headerChildren=this.header.getColumnCells();
var columnsNumber=this.getColumnsNumber();
if(!ClientUILib.isIE){for(var i=0;
i<columnsNumber-1;
i++){if(this.header.isColumnWidthPercentage(i)){var val=this.header.getColumn(i).width;
val=parseInt(val)/100;
var width=maxAllowedWidth*val;
this.header.setColumnWidth(i,width);
this.setColumnWidth(i,width)
}}var visibleHeaderWidth=this.header.getVisibleWidth();
var excessWidth=visibleHeaderWidth-maxAllowedWidth-1;
if(excessWidth>0){this.validateColumnsWidth(cols,excessWidth)
}}cols[columnsNumber-1].width=null;
cols[cols.length-1].width=null;
for(var i=0;
i<columnsNumber-1;
i++){if(this.options.isSingleCanHideTrueAvailable){var headerChild=headerChildren[i];
Utils.DOM.Event.removeListeners(headerChild);
Utils.DOM.Event.observe(headerChild,"mouseover",this.header.eventHeaderCellMouseOver);
Utils.DOM.Event.observe(headerChild,"mouseout",this.header.eventHeaderCellMouseOut)
}}this.header.adjustSeparators(this);
if(this.scrollable){this.scrollPositionHolder=$(this.id+"scrollPos");
if(this.scrollingDiv){if(this.scrollingDiv.element.scrollHeight>this.options.scrollPos){this.scrollingDiv.element.scrollTop=this.options.scrollPos
}}}this.hideSplashScreen();
if(this.scrollable&&this.scrollingDiv){Event.observe(this.scrollingDiv.element,"scroll",this.onScroll.bindAsEventListener(this))
}this.mainDiv.element.fire("rich:onlayoutupdate",{})
},onScroll:function(e){if(this.scrollPositionHolder&&this.scrollingDiv){this.scrollPositionHolder.value=this.scrollingDiv.element.scrollTop;
if(this.options.onScrollFunction){this.options.onScrollFunction(e)
}}},update:function(refreshEvents){this.createControls();
if(!ClientUILib.isIE){if(this.fakeIeRow){this.table.getElement().deleteRow(this.fakeIeRow.rowIndex);
this.fakeIeRow=null
}if(this.fakeIeBodyRow){this.tableB.deleteRow(this.fakeIeBodyRow.rowIndex);
this.fakeIeBodyRow=null
}}this.selectionManager.refreshEvents();
this.updateLayout();
this.selectionManager.restoreState()
},updateAfterSorting:function(refreshEvents){if(this.forseSorting){this.form=A4J.AJAX.locateForm($(this.id));
var formId=(this.form)?this.form.id:null;
var vId=this.id;
var value=$(this.id+"forseSorting").value;
if(new Number(value)==0){$(this.id+"forseSorting").click()
}}}});
ExtendedDataTable.DataTable.initSimpleDropZone=function(table,context,colId,dropZonePosition){var clientId=table.id;
colId=colId.substring(colId.lastIndexOf(":")+1);
var formId=context.formId;
var containerId=context.containerId;
var actionUrl=context.actionUrl;
var dZone=new DnD.ExtSimpleDropZone(clientId+"_hdrop_"+colId+dropZonePosition,{"acceptedTypes":"COLUMN_ORDER_"+clientId,"dndParams":"{}"});
dZone.drop=function(event,drag){var dragParams=drag.getParameters();
var source=dragParams["dragSourceId"];
var cId=clientId+":"+colId;
if(source!=(cId+":dnd_drag_script")){var options={};
options.parameters={};
options.parameters[cId+":dnd_drop_script_"+dropZonePosition]=cId+":dnd_drop_script_"+dropZonePosition;
options.actionUrl=actionUrl;
options.similarityGroupingId=cId;
options.parameters["dropTargetId"]=cId+":dnd_drop_script_"+dropZonePosition;
Object.extend(options.parameters,dragParams);
table.preSendAjaxRequest();
var dzOptions=this.getDropzoneOptions();
if(dzOptions.ondrop){if(!dzOptions.ondrop.call(this,event)){return 
}}A4J.AJAX.Submit(containerId,formId,event,options)
}};
return dZone
};
ExtendedDataTable.DataTable.initDraggable=function(table,colId,label){var clientId=table.id;
var cId=colId;
colId=colId.substring(colId.lastIndexOf(":")+1);
var options={};
options.dragIndicator=clientId+":dataTable_indicator";
options.dragType="COLUMN_ORDER_"+clientId;
options.dndParams='{"label":"'+label+'"}';
options.parameters={};
options.parameters.dragSourceId=cId+":dnd_drag_script";
options.parameters[cId+":dnd_drag_script"]=cId+":dnd_drag_script";
var draggable=new DnD.ExtSimpleDraggable(cId,options);
return draggable
};
ExtendedDataTable.DataTable.header=Class.create(ClientUI.common.box.Box,{initialize:function($super,elementId,extDt){this.extDt=extDt;
this.extDtId=this.extDt.id;
$super(elementId,extDt,true);
this.eventSepClick=this.OnSepClick.bindAsEventListener(this);
this.eventSepMouseDown=this.OnSepMouseDown.bindAsEventListener(this);
this.eventSepMouseMove=this.OnSepMouseMove.bindAsEventListener(this);
this.eventSepMouseUp=this.OnSepMouseUp.bindAsEventListener(this);
if(this.extDt.options.isSingleCanHideTrueAvailable){this.eventHeaderCellMouseOver=this.OnHeaderCellMouseOver.bindAsEventListener(this);
this.eventHeaderCellMouseOut=this.OnHeaderCellMouseOut.bindAsEventListener(this);
var showMenuFct=this.extDt.options.showMenuFunction;
if(showMenuFct){this.showMenuFct=showMenuFct;
this.menuImageMouseDown=this.OnMenuImageMouseDown.bindAsEventListener(this)
}}this.createControl(elementId)
},OnHeaderCellMouseOver:function(event){var el=this.extDt._findParentElement(event,"th");
var menuDiv=$(el.id+"header:menuDiv");
menuDiv.className="extdt-menu-div-on"
},getCaption:function(){return this.caption
},getCaptionHeight:function(){var caption=this.getCaption();
if(caption){return caption.getHeight()
}else{return 0
}},OnHeaderCellMouseOut:function(event){var el=this.extDt._findParentElement(event,"th");
var menuDiv=$(el.id+"header:menuDiv");
menuDiv.className="extdt-menu-div-out"
},removeListeners:function(){var columnCells=this.getColumnCells();
var l=columnCells.length;
for(var i=0;
i<l-1;
i++){var headerChild=columnCells[i];
var headerChildChildren=headerChild.childElements();
var sepSpan=headerChildChildren[1];
var menuImage=headerChildChildren[4];
Utils.DOM.Event.removeListeners(menuImage);
Utils.DOM.Event.removeListeners(sepSpan)
}},getVisibleWidth:function(){var sum=0;
var l=this.getColumnsNumber();
for(var i=0;
i<l-1;
i++){sum+=this.getColumnWidth(i)
}return sum
},createControl:function(elementId){if(!elementId){errMsg="Invalid id specified for ExtendedDataTableGridHeader.";
throw (errMsg)
}if(!this.parseTemplate(elementId)){errMsg="TODO insert commnet about header structure here";
throw (errMsg)
}},parseTemplate:function(template){if(!template){return false
}this.headerRow=new ClientUI.common.box.Box(this.extDtId+":headerRow",this.getElement(),true);
this.filterRow=new ClientUI.common.box.Box(this.extDtId+":filterRow",this.getElement(),true);
this.caption=new ClientUI.common.box.Box(this.extDtId+":caption",this.getElement(),true);
var colgroup=$(this.extDtId+":colgroup:header");
this.cols=colgroup.getElementsByTagName("col");
this.columnsNumber=this.cols.length;
this.columnCells=this.headerRow.getElement().childElements();
return true
},getColumns:function(){return this.cols
},getColumn:function(index){if(this.isValidColumnNumber(index)){return this.cols[index]
}else{return null
}},getColumnCells:function(){return this.columnCells
},getColumnsNumber:function(){return this.columnsNumber
},setColumnWidth:function(columnIndex,newWidth){if(columnIndex>=this.getColumnsNumber()){return false
}else{if(!newWidth){newWidth=null
}this.getColumns()[columnIndex].width=newWidth
}},isValidColumnNumber:function(columnNumber){return((columnNumber<this.columnsNumber)&&(columnNumber>=0))
},getColumnWidth:function(columnNumber){if(this.isValidColumnNumber(columnNumber)){var col=this.getColumnCells()[columnNumber];
return col.offsetWidth||parseInt(this.getColumns()[columnNumber])
}else{return null
}},isColumnWidthPercentage:function(columnNumber){if(this.isValidColumnNumber(columnNumber)){var col=this.getColumns()[columnNumber];
return/\d+%/.test(col.width)
}else{return null
}},getHeightWithoutFacets:function(){return this.headerRow.getHeight()+this.filterRow.getHeight()
},getTotalHeight:function(){var ret=this.headerRow.getHeight()+this.filterRow.getHeight();
if(this.caption){ret+=this.caption.getHeight()
}return ret
},OnMenuImageMouseDown:function(event){var el=this.extDt._findParentElement(event,"th");
var columnId=(el)?el.id:null;
if(columnId&&(columnId!="")){var menuId="#"+this.extDtId+":menu";
menuId=menuId.replace(/:/g,"\\:");
if(this.showMenuFct){this.showMenuFct(event,columnId,menuId)
}}Event.stop(event)
},initDragAndDropSupport:function(table,context,colId,label){ExtendedDataTable.DataTable.initDraggable(table,colId,label);
ExtendedDataTable.DataTable.initSimpleDropZone(table,context,colId,"left");
ExtendedDataTable.DataTable.initSimpleDropZone(table,context,colId,"right")
},adjustSeparators:function(table,context){var columnCells=this.getColumnCells();
var l=columnCells.length;
var headerRowHeight=this.headerRow.getHeight();
var headerRowY=this.headerRow.getY();
for(var i=0;
i<l-1;
i++){var headerChild=columnCells[i];
var headerNextChild=columnCells[i+1];
var headerChildChildren=headerChild.childElements();
var labelDiv=headerChildChildren[0];
var sepSpan=headerChildChildren[1];
sepSpan.columnIndex=i;
var sd=sepSpan.getWidth()/2+1;
var dropSpanLeft=headerChildChildren[2];
var dropSpanRight=headerChildChildren[3];
var menuImage=headerChildChildren[4];
var label=labelDiv.getAttribute("label");
if(table.dragContext){this.initDragAndDropSupport(table,table.dragContext,headerChild.id,label)
}var spanLeft=headerNextChild.offsetLeft-sd;
sepSpan.setStyle({height:headerRowHeight+"px",top:headerRowY+"px",left:spanLeft+"px"});
menuImage.setStyle({top:headerRowY+"px",left:(headerNextChild.offsetLeft-menuImage.offsetWidth-1)+"px"});
var w=headerChild.getWidth();
dropSpanLeft.setStyle({top:headerRowY+"px",left:(headerChild.offsetLeft)+"px",height:headerRowHeight+"px",width:(w/2)+"px"});
dropSpanRight.setStyle({top:headerRowY+"px",left:(headerChild.offsetLeft+w/2)+"px",height:headerRowHeight+"px",width:(w/2)+"px"})
}this.lastColWidth=this.extDt.getColumnWidth(this.getColumnsNumber()-1);
if(ClientUILib.isIE){this.lastColWidth-=15
}this._redrawTable(this.extDt.tableB)
},OnSepClick:function(event){Event.stop(event);
this.dragColumnInfo.mouseDown=false
},OnSepMouseDown:function(event){Event.stop(event);
this.dragColumnInfo={srcElement:Event.element(event),dragStarted:false,mouseDown:true,startX:Event.pointerX(event),originalX:0};
var srcElement=this.dragColumnInfo.srcElement;
this.maxDelta=this.getColumnWidth(this.getColumnsNumber()-1);
this.maxDelta-=this.extDt.getScrollbarWidth();
if(ClientUILib.isOpera){this.maxDelta-=1
}if(ClientUILib.isIE){this.maxDelta-=3
}this.minDelta=this.minColumnWidth-this.getColumnWidth(srcElement.columnIndex);
Event.observe(document,"mousemove",this.eventSepMouseMove,true);
Event.observe(document,"mouseup",this.eventSepMouseUp,true)
},_showSplitter:function(index){if(!this.columnSplitter){this._createSplitter()
}var pos=this.dragColumnInfo.srcElement.offsetLeft;
pos+=6;
this.dragColumnInfo.originalX=pos;
this.columnSplitter.show();
this.columnSplitter.setHeight(this.extDt.scrollingDiv.getHeight()+this.getHeightWithoutFacets());
this.columnSplitter.moveTo(pos,this.headerRow.getY())
},_hideSplitter:function(){if(this.columnSplitter){this.columnSplitter.hide()
}},_createSplitter:function(){this.columnSplitter=new ClientUI.common.box.Box(this.extDtId+":cs",this.extDt.grid);
this.columnSplitter.makeAbsolute();
this.columnSplitter.setWidth(this.minColumnWidth)
},OnSepMouseUp:function(event){this._hideSplitter();
Event.stop(event);
Event.stopObserving(document,"mousemove",this.eventSepMouseMove);
Event.stopObserving(document,"mouseup",this.eventSepMouseUp);
if(this.dragColumnInfo&&this.dragColumnInfo.dragStarted){this.dragColumnInfo.dragStarted=false;
this.dragColumnInfo.mouseDown=false;
var delta=Event.pointerX(event)-this.dragColumnInfo.startX;
if(delta<this.minDelta){delta=this.minDelta
}if(delta>this.maxDelta){delta=this.maxDelta
}var columnIndex=this.dragColumnInfo.srcElement.columnIndex;
var newWidth=this.getColumnWidth(columnIndex)+delta;
this.extDt.setColumnWidth(columnIndex,newWidth);
this.setColumnWidth(columnIndex,newWidth);
this.extDt.updateLayout();
if(this.extDt.onColumnResize){this.extDt.columnWidths="";
for(i=0;
i<this.columnsNumber;
i++){this.extDt.columnWidths+=""+this.getColumnWidth(i)+";"
}this.extDt.onColumnResize(event,this.extDt.columnWidths)
}}},OnSepMouseMove:function(event){if(this.dragColumnInfo&&this.dragColumnInfo.mouseDown){if(!this.dragColumnInfo.dragStarted&&this.extDt.options.allowColumnResize){this.dragColumnInfo.dragStarted=true;
this._showSplitter(this.dragColumnInfo.srcElement.columnIndex)
}var delta=Event.pointerX(event)-this.dragColumnInfo.startX;
if(delta<this.minDelta){delta=this.minDelta
}if(delta>this.maxDelta){delta=this.maxDelta
}var x=this.dragColumnInfo.originalX+delta;
var finalX=x-this.minColumnWidth-6;
this.columnSplitter.moveToX(finalX);
Event.stop(event)
}},_redrawTable:function(table){if(ClientUILib.isOpera){table.hide()
}var tr=table.insertRow(0);
var td=tr.insertCell(0);
td.setAttribute("colspan",5);
td.innerHTML="safari-must-have-something-inserted-to-redraw-table";
table.deleteRow(tr.rowIndex);
table.show()
}});
ExtendedDataTable.CellSelection=Class.create({initialize:function(allowCellSelection,cellSelectionClass,tableState,cellStateInput,table){this.allowCellSelection=allowCellSelection;
this.cellSelectionClass=cellSelectionClass;
this.tableState=tableState;
this.table=$(table);
this.stateInput=$(cellStateInput);
this.selectedCell=null;
this.cellIndex=-1;
this.rowIndex=-1;
this.restoreState()
},isIE7:function(){return/MSIE (7).+Win/.test(navigator.userAgent)
},reset:function(){this.selectedCell=null;
this.cellIndex=-1;
this.rowIndex=-1;
this.saveState()
},restoreState:function(){if(this.isAllowCellSelection()&&this.tableState&&this.table){var states=this.tableState.split(",");
if(states.length==2){var rowIndex=parseInt(states[0]);
var cellIndex=parseInt(states[1]);
if(rowIndex!=void (0)&&cellIndex!=void (0)&&rowIndex!=-1&&cellIndex!=-1){var rowIndex=this.isIE7()?rowIndex:(rowIndex+1);
this.selectCurrentCell(this.table.lastChild.rows[rowIndex].cells[cellIndex])
}}}},saveState:function(){if(this.isAllowCellSelection()){if(this.stateInput){this.stateInput.value=this.rowIndex+","+this.cellIndex
}}},isAllowCellSelection:function(){return this.allowCellSelection
},selectCurrentCell:function(tdObj){if(tdObj&&(tdObj.tagName=="TD"||tdObj.tagName=="td")){if(this.selectedCell){var lastId=this.selectedCell.id;
this.unselectCell(this.selectedCell);
if(lastId==tdObj.id){this.reset();
return 
}}tdObj.className=tdObj.className+" "+this.cellSelectionClass;
this.selectedCell=tdObj;
this.setCellIndex();
this.saveState()
}},setCellIndex:function(){var parentTr=this.selectedCell.parentNode;
var parentTrId=parentTr.id;
for(var j=0;
j<this.table.lastChild.rows.length;
j++){if(parentTrId==this.table.lastChild.rows[j].id){this.rowIndex=j
}}var thisId=this.selectedCell.id;
var cLength=parentTr.childNodes.length;
for(var i=0;
i<parentTr.childNodes.length;
i++){if(parentTr.childNodes[i].id==thisId){this.cellIndex=i;
break
}}},unselectCell:function(tdObj){if(tdObj){tdObj.className=tdObj.className.replace(" "+this.cellSelectionClass,"")
}},moveLeft:function(ev){if(this.selectedCell){var parentTr=this.selectedCell.parentNode;
var thisId=this.selectedCell.id;
for(var i=0;
i<parentTr.childNodes.length;
i++){if(parentTr.childNodes[i].id==thisId&&i>0){this.selectCurrentCell(parentTr.childNodes[i-1]);
break
}}}},moveRight:function(ev){if(this.selectedCell){var parentTr=this.selectedCell.parentNode;
var thisId=this.selectedCell.id;
var cLength=parentTr.childNodes.length;
for(var i=0;
i<cLength;
i++){if(parentTr.childNodes[i].id==thisId&&i<(cLength-2)){this.selectCurrentCell(parentTr.childNodes[i+1]);
break
}}}},moveUp:function(event,nextRowIndex){this.upOrDown(event,nextRowIndex)
},upOrDown:function(event,nextRowIndex){if(this.selectedCell){var rows=this.table.lastChild.rows;
var rLength=rows.length;
var rIndex=this.isIE7()?nextRowIndex+1:nextRowIndex;
var row=rows[rIndex];
if(row&&row.cells){var cells=row.cells;
this.selectCurrentCell(cells[this.cellIndex])
}}},moveDown:function(event,nextRowIndex){this.upOrDown(event,nextRowIndex)
}});
ExtendedDataTable.Selection=Class.create({initialize:function(){this.ranges=[]
},addId:function(id){id=parseInt(id);
if(this.isSelectedId(id)){return 
}var i=0;
while(i<this.ranges.length&&id>=this.ranges[i++].indexes[1]){}i--;
if(this.ranges[i-1]&&id==(this.ranges[i-1].indexes[1]+1)){if(id==(this.ranges[i].indexes[0]-1)){this.ranges[i-1].indexes[1]=this.ranges[i].indexes[1];
this.removeRange(i)
}else{this.ranges[i-1].indexes[1]++
}}else{if(this.ranges[i]){if(this.ranges[i]&&id==(this.ranges[i].indexes[0]-1)){this.ranges[i].indexes[0]--
}else{if(id==(this.ranges[i].indexes[1]+1)){this.ranges[i].indexes[1]++
}else{if(id<this.ranges[i].indexes[1]){this.addRange(i,new ExtendedDataTable.Range(id,id))
}else{this.addRange(i+1,new ExtendedDataTable.Range(id,id))
}}}}else{this.addRange(i,new ExtendedDataTable.Range(id,id))
}}this.selectionIsChanged=true
},addRange:function(index,range){var i=this.ranges.push(range)-2;
if(index>=0){while(i>=index){this.ranges[i+1]=this.ranges[i--]
}this.ranges[i+1]=range
}},removeRange:function(index){var i=index+1;
while(i!=this.ranges.length){this.ranges[i-1]=this.ranges[i++]
}this.ranges.pop()
},isSelectedId:function(id){var i=0;
while(i<this.ranges.length&&id>=this.ranges[i].indexes[0]){if(id>=this.ranges[i].indexes[0]&&id<=this.ranges[i].indexes[1]){return true
}else{i++
}}return false
},getSelectedIdsQuantity:function(){var number=0;
var l=this.ranges.length;
for(var i=0;
i<l;
i++){number+=this.ranges[i].size()
}return number
},size:function(){return this.getSelectedIdsQuantity()
},removeId:function(id){id=parseInt(id);
if(!this.isSelectedId(id)){return 
}var i=0;
while(i<this.ranges.length&&id>this.ranges[i++].indexes[1]){}i--;
if(this.ranges[i]){if(id==(this.ranges[i].indexes[1])){if(id==(this.ranges[i].indexes[0])){this.removeRange(i)
}else{this.ranges[i].indexes[1]--
}}else{if(id==(this.ranges[i].indexes[0])){this.ranges[i].indexes[0]++
}else{this.addRange(i+1,new ExtendedDataTable.Range(id+1,this.ranges[i].indexes[1]));
this.ranges[i].indexes[1]=id-1
}}}this.selectionIsChanged=true
},getState:function(){var s=this.clone();
return{size:function(){return s.size()
},each:function(iterator){s.each(iterator)
},isSelected:function(id){return s.isSelectedId(id)
}}
},clone:function(){var ret=Object.extend(new Object(),this);
var l=ret.ranges.length;
ret.ranges=new Array(l);
for(var i=0;
i<l;
i++){ret.ranges[i]=this.ranges[i].clone()
}return ret
},each:function(iterator){var l=this.ranges.length;
for(var i=0;
i<l;
i++){this.ranges[i].each(iterator)
}},getRanges:function(){return this.ranges
},setRanges:function(ranges){this.ranges=ranges
},initRanges:function(rangeStrRArray){if(rangeStrRArray.length==0){this.ranges=[];
return 
}this.ranges=new Array(rangeStrRArray.length);
var indexStrRArray;
var l=this.ranges.length;
for(var i=0;
i<l;
i++){indexStrRArray=rangeStrRArray[i].split(",");
this.ranges[i]=new ExtendedDataTable.Range(parseInt(indexStrRArray[0]),parseInt(indexStrRArray[1]))
}},inspectRanges:function(){var ranges=this.getRanges();
var ret="";
ranges.each(function(r){ret+=r.inspect()
});
return ret
}});
ExtendedDataTable.Range=Class.create({initialize:function(startIndex,endIndex){this.indexes=[startIndex,endIndex]
},inspect:function(){return this.indexes[0]+","+this.indexes[1]+";"
},toString:function(){return this.inspect()
},size:function(){return this.indexes[1]-this.indexes[0]+1
},each:function(iterator){var j=this.indexes[0];
while(j<=this.indexes[1]){iterator(j++)
}},clone:function(){var ret=Object.extend(new Object(),this);
ret.indexes=this.indexes.clone();
return ret
}});
ExtendedDataTable.SelectionManager=Class.create({initialize:function(options,owner){this.dataTable=owner;
this.options=options;
this.selectionFlag;
this.firstIndex;
this.activeRow=-1;
var element=options.gridId;
this.gridElement=document.getElementById(element+":n");
this.prefix=options.gridId;
this.selection=new ExtendedDataTable.Selection();
this.cellSelection=new ExtendedDataTable.CellSelection(this.options.allowCellSelection,this.options.selectedCellClass,this.options.selectedCellState,owner.id+"cellState",this.gridElement);
this.inputElement=options.selectionInput;
this.onselectionchange=options.onselectionchange;
this.onRowContextMenu=options.onRowContextMenu;
this.selectedClass=options.selectedClass;
this.activeClass=options.activeClass
},refreshEvents:function(){this.setListeners();
if(this.options.selectionMode!="none"){this.eventKeyPress=this.processKeyDown.bindAsEventListener(this);
Event.observe(document,"keydown",this.eventKeyPress)
}A4J.AJAX.AddListener({onafterajax:function(req,event,data){if(!$(this.prefix+":n")){Event.stopObserving(document,"keydown",this.eventKeyPress)
}}.bind(this)});
if(document.selection){this.eventResetSelection=this.resetSelection.bindAsEventListener(this);
Event.observe(this.gridElement,"click",this.eventResetSelection)
}this.eventLostFocus=this.processLostFocus.bindAsEventListener(this);
Event.observe(document,"click",this.eventLostFocus);
this.eventPreventLostFocus=this.processPreventLostFocus.bindAsEventListener(this);
Event.observe(this.gridElement,"click",this.eventPreventLostFocus)
},restoreState:function(){this.selectionFlag=null;
var selStrAr=$(this.inputElement).value.split(";");
var activeRow=NaN;
while(selStrAr.length!=0&&selStrAr[selStrAr.length-1].indexOf(",")==-1&&isNaN(activeRow=Number(selStrAr.pop()))){}if(!isNaN(activeRow)){this.setActiveRow(activeRow)
}this.selection.initRanges(selStrAr);
var i=0;
var j;
while(i<this.selection.ranges.length){j=this.selection.ranges[i].indexes[0];
while(j<=this.selection.ranges[i].indexes[1]){var nElement=$(this.prefix+":n:"+j);
Element.addClassName(nElement,"extdt-row-selected rich-sdt-row-selected "+this.selectedClass);
j++
}i++
}this.oldState=this.selection.getState()
},setListeners:function(){var nrows=$(this.prefix+":n").rows;
this.rowCount=nrows.length;
var rowIndex;
var groupingExists=$(this.prefix+":group-row:0")!=null;
if(!groupingExists){if(this.options.selectionMode!="none"){for(var i=0;
i<this.rowCount;
i++){var arr=nrows[i].id.split(":");
rowIndex=Number(arr[arr.length-1]);
this.addListener(nrows[i],rowIndex)
}}}else{var groupRow;
var lastGroupId=0;
var bGroupExpanded=true;
var bHideFirstRow=false;
if(ClientUILib.isIE){bHideFirstRow=true
}var groupId;
var groupItems=[];
var groupItem=0;
var groups=[];
var groupRows=this.dataTable.groupRows;
for(var i=0;
i<this.rowCount;
i++){tempo=nrows[i].id.split(this.prefix)[1];
var tempArr=tempo.split(":");
groupRow=tempArr[1]=="group-row";
groupId=Number(tempArr[2]);
if(groupRow){groups[lastGroupId]=groupItems;
bGroupExpanded=(groupRows[groupId].getAttribute("expanded")=="true");
var textSpan=groupRows[lastGroupId].lastChild.lastChild;
var txtNode=document.createTextNode("("+groupItems.size()+")");
if(textSpan.lastChild){textSpan.replaceChild(txtNode,textSpan.lastChild)
}else{textSpan.appendChild(txtNode)
}groupItem=0;
groupItems=[];
lastGroupId=groupId
}else{if(this.options.selectionMode!="none"){var arr=nrows[i].id.split(":");
rowIndex=Number(arr[arr.length-1]);
this.addListener(nrows[i],rowIndex)
}groupItems[groupItem++]=nrows[i];
if((i==0)&&(bHideFirstRow)){}if(!bGroupExpanded){nrows[i].style.display="none";
if((ClientUILib.isIE)&&(i!=0)){var cells=nrows[i].childNodes;
var l=cells.length;
for(var j=0;
j<l;
j++){cells[j].style.borderStyle="none"
}}}}}groups[lastGroupId]=groupItems;
var textSpan=groupRows[lastGroupId].lastChild.lastChild;
var txtNode=document.createTextNode("("+groupItems.size()+")");
if(textSpan.lastChild){textSpan.replaceChild(txtNode,textSpan.lastChild)
}else{textSpan.appendChild(txtNode)
}this.dataTable.groups=groups
}},addListener:function(tr,rowIndex){if(tr){var listener=this.processClick.bindAsEventListener(this,rowIndex);
Utils.DOM.Event.removeListeners(tr);
Utils.DOM.Event.observe(tr,"click",listener);
Utils.DOM.Event.observe(tr,"contextmenu",function(event){this.selectionFlag="x";
if(this.selection.isSelectedId(rowIndex)){if(this.onRowContextMenu){this.onRowContextMenu(event)
}return 
}this.setSelection([rowIndex,rowIndex]);
if(this.onRowContextMenu){this.onRowContextMenu(event)
}this.selectionChanged(event)
}.bind(this));
this.addCellListener(tr)
}},addCellListener:function(tr){if(tr&&this.cellSelection.isAllowCellSelection()){for(var i=0;
i<tr.childNodes.length;
i++){if(tr.childNodes[i].tagName=="TD"||tr.childNodes[i].tagName=="td"){Utils.DOM.Event.observe(tr.childNodes[i],"contextmenu",function(event){var target=event.currentTarget?event.currentTarget:event.target;
var e=target;
if(e.tagName!="TD"&&e.tagName!="td"){while(e){if(e.tagName=="TD"||e.tagName=="td"){break
}e=e.parentNode
}}this.cellSelection.selectCurrentCell(e)
}.bind(this))
}}}},removeListeners:function(){Event.stopObserving(document,"keydown",this.eventKeyPress);
if(document.selection){Event.stopObserving(this.gridElement,"click",this.eventResetSelection)
}Event.stopObserving(document,"click",this.eventLostFocus);
Event.stopObserving(this.gridElement,"click",this.eventPreventLostFocus);
if(this.options.selectionMode!="none"){var nrows=$(this.prefix+":n").rows;
var rowCount=nrows.length;
for(var i=0;
i<this.rowCount;
i++){Utils.DOM.Event.removeListeners(nrows[i])
}}},processPreventLostFocus:function(){this.inFocus=true;
this.preventLostFocus=true
},processLostFocus:function(){if(!this.preventLostFocus){this.lostFocus()
}else{this.preventLostFocus=false
}},lostFocus:function(){this.inFocus=false
},processKeyDown:function(event){if(this.inFocus){if(this.dataTable&&this.dataTable.onkeydownhandler&&typeof (this.dataTable.onkeydownhandler)=="function"){this.dataTable.onkeydownhandler(event)
}}if($(this.prefix+":n").rows.length>0){if(!event.shiftKey){this.shiftRow=null
}var range,rowIndex;
var activeRow=this.activeRow;
var noDefault=false;
var rows=$(this.prefix+":n").rows;
var rowCount=this.rowCount;
var arr=rows[0].id.split(":");
if(arr[arr.length-1]=="fakeIeRow"){arr=rows[1].id.split(":");
rowCount--
}this.firstIndex=Number(arr[arr.length-1]);
switch(event.keyCode||event.charCode){case Event.KEY_UP:if(this.inFocus&&activeRow!=null){if(this.firstIndex!=activeRow){rowIndex=(this.rowCount+activeRow-1)%this.rowCount;
if(!event.ctrlKey&&!event.shiftKey){this.selectionFlag="x";
range=[rowIndex,rowIndex];
this.setSelection(range);
this.cellSelection.moveUp(event,rowIndex)
}else{if(!event.ctrlKey&&event.shiftKey&&this.options.selectionMode=="multi"){if(!this.shiftRow){this.shiftRow=this.activeRow
}if(this.shiftRow>=this.activeRow){this.addRowToSelection(rowIndex)
}else{this.removeRowFromSelection(activeRow)
}}}noDefault=true;
this.setActiveRow(rowIndex)
}else{}}break;
case Event.KEY_DOWN:if(this.inFocus&&activeRow!=null){rowIndex=(activeRow+1)%rowCount;
if(this.firstIndex!=rowIndex){if(!event.ctrlKey&&!event.shiftKey){this.selectionFlag="x";
range=[rowIndex,rowIndex];
this.setSelection(range);
this.cellSelection.moveDown(event,rowIndex)
}else{if(!event.ctrlKey&&event.shiftKey&&this.options.selectionMode=="multi"){if(!this.shiftRow){this.shiftRow=this.activeRow
}if(this.shiftRow<=this.activeRow){this.addRowToSelection(rowIndex)
}else{this.removeRowFromSelection(activeRow)
}}}noDefault=true;
this.setActiveRow(rowIndex)
}else{}}break;
case Event.KEY_LEFT:this.cellSelection.moveLeft(event);
break;
case Event.KEY_RIGHT:this.cellSelection.moveRight(event);
break;
case 65:case 97:if(this.inFocus&&event.ctrlKey){this.selectionFlag="a";
for(var i=0;
i<this.rowCount;
i++){this.addRowToSelection(i)
}noDefault=true
}break;
case Event.KEY_TAB:this.lostFocus()
}if(noDefault){this.dataTable.showRow(this.activeRow);
this.selectionChanged(event);
if(event.preventBubble){event.preventBubble()
}Event.stop(event)
}}},processClick:function(event,rowIndex){if(this.options.selectionMode=="none"){return 
}var bSingleSelection=this.options.selectionMode=="single";
if(!event.shiftKey){this.shiftRow=null
}var range;
if(event.shiftKey&&!event.ctrlKey&&!bSingleSelection&&!event.altKey){var arr=$(this.prefix+":n").rows[0].id.split(":");
this.firstIndex=Number(arr[arr.length-1]);
this.selectionFlag="x";
if(!this.shiftRow){this.shiftRow=this.activeRow
}this.startRow=this.shiftRow;
if(((this.startRow<=rowIndex)&&(this.firstIndex<=this.startRow||rowIndex<this.firstIndex))||(this.startRow>rowIndex&&this.firstIndex<this.startRow&&rowIndex<=this.firstIndex)){this.endRow=rowIndex
}else{this.endRow=this.startRow;
this.startRow=rowIndex
}if(this.startRow>this.endRow){var t=this.startRow;
this.startRow=this.endRow;
this.endRow=t
}range=[this.startRow,this.endRow];
this.setSelection(range);
this.selectionChanged(event);
return 
}else{if(!event.shiftKey&&event.ctrlKey&&!event.altKey){if(this.selection.isSelectedId(rowIndex)){this.removeRowFromSelection(rowIndex)
}else{if(!bSingleSelection||this.selection.size()==0){this.addRowToSelection(rowIndex)
}}}else{if(!event.shiftKey&&!event.ctrlKey&&!event.altKey){this.selectionFlag="x";
range=[rowIndex,rowIndex];
this.setSelection(range);
this.cellSelection.upOrDown(event,rowIndex)
}}}this.setActiveRow(rowIndex);
if(event.shiftKey){if(window.getSelection){window.getSelection().removeAllRanges()
}else{if(document.selection){document.selection.empty()
}}}this.selectionChanged(event)
},selectionChanged:function(event){$(this.inputElement).value=this.selection.inspectRanges()+this.activeRow+";"+(this.selectionFlag?this.selectionFlag:"");
var state=this.selection.getState();
event.oldSelection=this.oldState;
event.newSelection=state;
if(this.onselectionchange&&this.selection.selectionIsChanged){this.onselectionchange(event)
}this.selection.selectionIsChanged=false;
this.oldState=state
},setShiftRow:function(event){if(event.shiftKey){if(!this.shiftRow){this.shiftRow=this.activeRow
}}else{this.shiftRow=null
}},setSelection:function(range){var l=this.selection.ranges.length;
for(var i=l-1;
i>=0;
i--){var selection=this.selection.ranges[i].indexes;
if(selection.inspect()==range.inspect()){continue
}var j=selection[0];
for(j;
j<=selection[1];
j++){this.removeRowFromSelection(j)
}}if(range[0]==range[1]){this.addRowToSelection(range[0]);
return 
}var i=range[0];
range[1]=(range[1]+1)%this.rowCount;
if(range[1]==0){range[1]=this.rowCount-1;
this.addRowToSelection(this.rowCount-1)
}while(i!=range[1]){this.addRowToSelection(i);
i=(i+1)%this.rowCount
}},resetSelection:function(e){if(e.shiftKey){document.selection.empty()
}},addRowToSelection:function(rowIndex){this.selection.addId(rowIndex);
var nElement=$(this.prefix+":n:"+rowIndex);
Element.addClassName(nElement,"extdt-row-selected");
Element.addClassName(nElement,"rich-sdt-row-selected");
Element.addClassName(nElement,this.selectedClass)
},removeRowFromSelection:function(rowIndex){this.selection.removeId(rowIndex);
var nElement=$(this.prefix+":n:"+rowIndex);
Element.removeClassName(nElement,"extdt-row-selected");
Element.removeClassName(nElement,"rich-sdt-row-selected");
Element.removeClassName(nElement,this.selectedClass)
},setActiveRow:function(rowIndex){var fElement,nElement;
if(this.activeRow!=null){nElement=$(this.prefix+":n:"+this.activeRow);
Element.removeClassName(nElement,"extdt-row-active");
Element.removeClassName(nElement,"rich-sdt-row-active");
Element.removeClassName(nElement,this.activeClass)
}nElement=$(this.prefix+":n:"+rowIndex);
Element.addClassName(nElement,"extdt-row-active");
Element.addClassName(nElement,"rich-sdt-row-active");
Element.addClassName(nElement,this.activeClass);
this.activeRow=rowIndex
},getRows:function(){return $(this.prefix+":n").rows
}});
function mouseClickH(obj){obj.style.backgroundColor="green"
}
A4J.AJAX.PrepareQuery=function(containerId,formId,domEvt,options){for(var li=0;
li<A4J.AJAX._listeners.length;
li++){var listener=A4J.AJAX._listeners[li];
if(listener.onbeforeajax){listener.onbeforeajax(formId,domEvt,options)
}}LOG.debug("Query preparation for form '"+formId+"' requested");
var form=window.document.getElementById(formId);
if((!form||form.nodeName.toUpperCase()!="FORM")&&domEvt){var srcElement=domEvt.target||domEvt.srcElement||null;
if(srcElement){form=A4J.AJAX.locateForm(srcElement)
}}if(!options.submitByForm&&form&&form.onsubmit){LOG.debug("Form have onsubmit function, call it");
if(form.onsubmit()==false){return false
}}var tosend=new A4J.Query(containerId,form);
if(options.parameters.ajaxSingle&&options.parameters["_sip_limit_request_flag"]&&!options.parameters["_sip_submit_regionsIds"]){tosend.appendParameter(formId,formId);
var v=A4J.AJAX.LocaleViewState(form);
if(v){tosend.appendParameter(v.name,v.value)
}}else{if(options.parameters["_sip_limit_request_flag"]&&options.parameters["_sip_submit_regionsIds"]!=void (0)){tosend.appendFormControls2(options.single,options.control,options.parameters["_sip_submit_regionsIds"])
}else{tosend.appendFormControls(options.single,options.control)
}}if(options.parameters){tosend.appendParameters(options.parameters)
}if(options.actionUrl){tosend.setActionUrl(options.actionUrl)
}return tosend
};
A4J.Query.prototype.appendFormControls=function(hiddenOnly,actionControl){try{var elems=this._form.elements;
if(elems){var k=0;
for(k=0;
k<elems.length;
k++){var element=elems[k];
if(element==actionControl){continue
}try{if(!hiddenOnly||element.type=="hidden"){this.appendControl(element,false)
}}catch(ee){LOG.error("exception in building query ( append form control ) "+ee)
}}}}catch(e){LOG.warn("Error with append form controls to query "+e)
}if(actionControl){this.appendControl(actionControl,true)
}};
A4J.Query.prototype.reset=function(){if(this.elements){this.elements=null;
this.arrayCounter=0
}};
A4J.Query.prototype.fetchQueryElements=function(region){if(!this.elements){this.elements=new Array;
this.arrayCounter=0
}if(region!=void (0)&&region!=null&&region!="undefined"){var tmpElements=jQuery("input[id*='"+selector+"']");
if(tmpElements){for(var i=0;
i<tmpElements.length;
i++){this.elements[this.arrayCounter++]=tmpElements[i]
}}}};
A4J.Query.prototype.isInRegion=function(element,selector){if(element.id=="javax.faces.ViewState"||element.id==""||element.id.indexOf(selector)!=-1){return true
}return false
};
A4J.Query.prototype.appendParameters=function(parameters){for(k in parameters){if(k!="_sip_limit_request_flag"&&typeof Object.prototype[k]=="undefined"){LOG.debug("parameter "+k+" with value "+parameters[k]);
this.appendParameter(k,parameters[k])
}}};
A4J.Query.prototype.appendFormControls2=function(hiddenOnly,actionControl,ids){try{var idsArray=ids.split(",");
if(idsArray&&idsArray.length>0){for(var i=0;
i<idsArray.length;
i++){if(!$(idsArray[i])){continue
}var inputs=$(idsArray[i]).getElementsByTagName("input");
if(inputs&&inputs.length>0){for(var z=0;
z<inputs.length;
z++){var element=inputs[z];
if(!hiddenOnly||element.type=="hidden"){this.appendControl(element,false)
}}}}if($("javax.faces.ViewState")){this.appendControl($("javax.faces.ViewState"),false)
}if(actionControl){this.appendControl(actionControl,true)
}}else{this.appendFormControls(hiddenOnly,actionControl)
}}catch(e){LOG.warn("Error with append form controls to query "+e)
}};
A4J.AJAX.LocaleViewState=function(form){var v=form.lastChild;
if(v.name!="javax.faces.ViewState"){v=v.previousSibling
}return v.name=="javax.faces.ViewState"?v:null
};
A4J.AJAX.HEAD_CACHE=null;
A4J.AJAX.HEAD_CACHE_ITEM=function(href,src,mappedRole,elementRole){this[href]=src;
if(elementRole){this[mappedRole]=elementRole
}};
A4J.AJAX.XMLHttpRequest.prototype._initHeadCache=function(tag,href,mappedRole){A4J.AJAX.HEAD_CACHE=A4J.AJAX.HEAD_CACHE||{};
A4J.AJAX.HEAD_CACHE[tag]={};
A4J.AJAX.HEAD_CACHE[tag]["roleAnchors"]={};
var oldscripts=document.getElementsByTagName(tag);
for(var j=0;
j<oldscripts.length;
j++){var oldscript=oldscripts[j];
var oldRole=oldscript[mappedRole];
var src=oldscript.getAttribute(href);
src=this._noSessionHref(src);
if(oldRole){A4J.AJAX.HEAD_CACHE[tag]["roleAnchors"][oldRole]=oldscript
}A4J.AJAX.HEAD_CACHE[src]=new A4J.AJAX.HEAD_CACHE_ITEM(href,src,mappedRole,oldRole)
}return A4J.AJAX.HEAD_CACHE
};
A4J.AJAX.XMLHttpRequest.prototype._appendNewElements=function(tag,href,role,roles,attributes,mappings,callback){var newscripts=this.getElementsByTagName(tag);
var mappedRole=(mappings&&mappings[role])||role;
var oldscripts=(A4J.AJAX.HEAD_CACHE&&A4J.AJAX.HEAD_CACHE[tag])?A4J.AJAX.HEAD_CACHE:this._initHeadCache(tag,href,mappedRole);
var elements=[];
for(var i=0;
i<newscripts.length;
i++){var element=newscripts[i];
var src=element.getAttribute(href);
var elementRole;
if(roles){elementRole=element.getAttribute(role)
}if(src){var exist=false;
LOG.debug("<"+tag+"> in response with src="+src);
exist=oldscripts[this._noSessionHref(src)];
if(role&&exist){var oldRole=exist[mappedRole];
if((!elementRole^!oldRole)||(elementRole&&oldRole&&elementRole!=oldRole)){LOG.warn("Roles are different")
}}if(!exist){var head=document.getElementsByTagName("head")[0]||document.documentElement;
var script=document.createElement(tag);
script.setAttribute(href,src);
for(var j=0;
j<attributes.length;
j++){this._copyAttribute(element,script,attributes[j])
}if(elementRole){script[mappedRole]=elementRole
}LOG.debug("append element to document");
for(var j=0;
j<A4J.AJAX._headTransformers.length;
j++){A4J.AJAX._headTransformers[j](script)
}var anchor=A4J.AJAX.HEAD_CACHE[tag]["roleAnchors"][elementRole];
if(anchor&&anchor.parentNode){anchor.parentNode.insertBefore(script,anchor)
}else{head.appendChild(script)
}src=this._noSessionHref(src);
A4J.AJAX.HEAD_CACHE[src]=new A4J.AJAX.HEAD_CACHE_ITEM(href,src,mappedRole,elementRole);
if(callback){callback(element,script)
}if(tag!="link"||script.type.toLowerCase()=="text/javascript"){elements.push(script)
}}}}return elements
}

SipButton={};
SipButton.disable=function(el){var el=$(el);
var container=$(el.id+"container");
var link=$(el.id+"butLink");
if(container&&link){container.className="disabled";
link.setAttribute("sipdisabled","true")
}};
SipButton.enable=function(el){var el=$(el);
var container=$(el.id+"container");
var link=$(el.id+"butLink");
if(container&&link){container.className="sipButCommon";
link.setAttribute("sipdisabled","false")
}};
isIE7=function(){return/MSIE (7|6).+Win/.test(navigator.userAgent)
}

if(!window.Richfaces){window.Richfaces={}
}RichFaces.SipComboBox=Class.create();
RichFaces.SipComboBox.prototype={initialize:function(combobox,listId,parentListId,valueFieldId,fieldId,buttonId,buttonBGId,shadowId,commonStyles,userStyles,listWidth,listHeight,itemsText,directInputSuggestions,filterNewValue,selectFirstOnUpdate,onlistcall,onlistclose,onselected,defaultMessage,isDisabled,value,showDelay,hideDelay,spacerImg,countOfItems,behavourStrategy){this.directInputSuggestions=directInputSuggestions;
this.filterNewValue=filterNewValue;
this.comboboxId=combobox;
this.combobox=$(this.comboboxId);
this.spacerImg=spacerImg;
this.comboValue=document.getElementById(valueFieldId);
this.field=document.getElementById(fieldId);
this.virtualField=document.getElementById(this.comboboxId+"comboboxFieldVirtual");
this.virtualValue="";
this.tempItem;
this.BUTTON_WIDTH=17;
this.classes=Richfaces.mergeStyles(userStyles,commonStyles.getCommonStyles());
this.button=document.getElementById(buttonId);
this.buttonBG=document.getElementById(buttonBGId);
this.setInputWidth();
listWidth=(!listWidth)?this.getCurrentWidth():listWidth;
this.isSiperianMode=(behavourStrategy=="siperian");
this.comboList=new Richfaces.SipComboBoxList(this.spacerImg,combobox,listId,parentListId,selectFirstOnUpdate,filterNewValue,this.classes.combolist,listWidth,listHeight,itemsText,onlistcall,onlistclose,fieldId,shadowId,showDelay,hideDelay,countOfItems,null,(behavourStrategy=="siperian"));
this.defaultMessage=defaultMessage;
if(value){var item=this.comboList.findItemBySubstr(value);
if(item){this.comboList.doSelectItem(item)
}this.comboValue.value=value
}else{if(this.defaultMessage){this.applyDefaultText()
}}this.onselected=onselected;
this.isSelection=true;
this.isDisabled=isDisabled;
if(this.onselected){this.combobox.observe("rich:onselect",this.onselected)
}if(this.isDisabled){this.disable()
}if(Richfaces.browser.isIE6){this.comboList.createIframe(this.comboList.listParent.parentNode,listWidth,this.combobox.id,"sip-combobox-list-width sip-combobox-list-scroll sip-combobox-list-position")
}this.combobox.component=this;
this.initHandlers();
this["rich:destructor"]="destroy"
},destroy:function(){Event.stopObserving(this.combobox,"rich:onselect");
Event.stopObserving(this.button,"click");
Event.stopObserving(this.button,"mouseup");
Event.stopObserving(this.button,"mousedown");
Event.stopObserving(this.button,"mouseover");
Event.stopObserving(this.button,"mouseout");
Event.stopObserving(this.field,"keydown");
Event.stopObserving(this.field,"blur");
Event.stopObserving(this.field,"focus");
Event.stopObserving(this.field,"keyup");
Event.stopObserving(this.comboList.listParent,"mousedown");
Event.stopObserving(this.comboList.listParent,"mouseup");
Event.stopObserving(this.comboList.listParent,"mousemove");
Event.stopObserving(this.comboList.listParent,"click");
this.comboValue=null;
this.button=null;
this.buttonBG=null;
this.field=null;
this.virtualField=null;
this.classes=null;
delete this.comboList;
this.combobox.component=null;
this.combobox=null
},initHandlers:function(){Event.observe(this.button,"click",this.buttonClickHandler.bindAsEventListener(this));
Event.observe(this.button,"mouseup",this.buttonMouseUpHandler.bindAsEventListener(this));
Event.observe(this.button,"mousedown",this.buttonMousedownHandler.bindAsEventListener(this));
Event.observe(this.button,"mouseover",this.buttonMouseOverHandler.bindAsEventListener(this));
Event.observe(this.button,"mouseout",this.buttonMouseOutHandler.bindAsEventListener(this));
Event.observe(this.field,"keydown",this.fieldKeyDownHandler.bindAsEventListener(this));
Event.observe(this.field,"blur",this.fieldBlurHandler.bindAsEventListener(this));
Event.observe(this.field,"focus",this.fieldFocusHandler.bindAsEventListener(this));
Event.observe(this.field,"keyup",this.dataUpdating.bindAsEventListener(this));
Event.observe(this.comboList.listParent,"mousedown",this.listMousedownHandler.bindAsEventListener(this));
Event.observe(this.comboList.listParent,"mouseup",this.listMouseUpHandler.bindAsEventListener(this));
Event.observe(this.comboList.listParent,"mousemove",this.listMouseMoveHandler.bindAsEventListener(this));
Event.observe(this.comboList.listParent,"click",this.listClickHandler.bindAsEventListener(this))
},setInputWidth:function(){var width=(parseInt(this.field.parentNode.style.width)-this.BUTTON_WIDTH)-Richfaces.getBorderWidth(this.field,"lr")-Richfaces.getBorderWidth(this.button,"lr")-Richfaces.getPaddingWidth(this.field,"lr");
this.field.style.width=width+"px"
},buttonClickHandler:function(event){this.comboList.isOpenedByClick=(this.isSiperianMode&&(this.field.value==null||this.field.value.length==0));
if(this.comboList.visible()){this.comboList.hideWithDelay()
}else{this.comboList.createDefaultList();
this.comboList.showWithDelay();
if(this.comboList.selectedItem){this.comboList.scrollingUpToItem(this.comboList.selectedItem)
}this.comboList.isList=false
}},buttonMouseUpHandler:function(e){this.buttonBG.className="sip-combobox-font sip-combobox-button-background sip-combobox-button";
this.button.className=this.classes.button.classes.active+" sip-combobox-button-hovered";
var styleCss=this.classes.button.style;
if(styleCss&&!styleCss.active.blank()){Element.setStyle(this.button,styleCss.active)
}this.field.focus()
},buttonMousedownHandler:function(e){this.buttonBG.className="sip-combobox-font sip-combobox-button-pressed-background sip-combobox-button";
this.button.className=this.classes.button.classes.active+" sip-combobox-button-hovered";
var styleCss=this.classes.button.style;
if(styleCss&&!styleCss.active.blank()){Element.setStyle(this.button,styleCss.active)
}this.comboList.isList=true
},buttonMouseOverHandler:function(e){var classCss=this.classes.button.classes;
var iconStyles=this.classes.buttonicon.style;
var styleCss=this.classes.button.style;
if(this.isActive()){this.button.className=classCss.active+" "+classCss.hovered;
if(styleCss&&!styleCss.active.blank()){Element.setStyle(this.button,styleCss.active)
}if(iconStyles&&!iconStyles.active.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.active})
}}else{this.button.className=classCss.normal+" "+classCss.hovered;
if(styleCss&&!styleCss.normal.blank()){Element.setStyle(this.button,styleCss.normal)
}if(iconStyles&&!iconStyles.normal.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.normal})
}}},buttonMouseOutHandler:function(e){var classCss=this.classes.button.classes;
var styleCss=this.classes.button.style;
var iconStyles=this.classes.buttonicon.style;
if(this.isActive()){this.button.className=classCss.active;
if(styleCss&&!styleCss.active.blank()){Element.setStyle(this.button,styleCss.active)
}if(iconStyles&&!iconStyles.active.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.active})
}}else{this.button.className=classCss.normal;
if(styleCss&&!styleCss.normal.blank()){Element.setStyle(this.button,styleCss.normal)
}if(iconStyles&&!iconStyles.normal.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.normal})
}}},listMouseMoveHandler:function(event){var item=event.target;
if(Element.match(item,"span")){if(item&&this.tempItem!=item){this.comboList.doActiveItem(item)
}this.tempItem=item
}},listMousedownHandler:function(event){if(!Prototype.Browser.Firefox){if(!Element.match(event.target,"span")){this.clickOnScroll=true
}}this.comboList.isList=true
},listMouseUpHandler:function(e){this.field.focus();
this.comboList.isList=false
},listClickHandler:function(event){LOG.debug("listClickHandler - begin");
this.isSelection=false;
this.setValue(true);
this.comboList.hideWithDelay()
},fieldKeyDownHandler:function(event){switch(event.keyCode){case Event.KEY_RETURN:LOG.debug("key_return");
this.setValue(!this.isSiperianMode);
if(this.isSiperianMode){this.comboValue.value=this.field.value;
RichFaces.SipComboBox.textboxSelect(this.field,this.field.value.length,this.field.value.length)
}if(true){this.comboList.hideWithDelay()
}Event.stop(event);
break;
case Event.KEY_DOWN:LOG.debug("fieldKeyDownHandler - KEY_DOWN"+this.comboList.isListOpen);
if(this.comboList.isListOpen){LOG.debug("before fieldKeyDownHandler - moveActiveItem");
if(!this.comboList.activeItem){if(this.comboList.getItems()&&this.comboList.getItems().length!=0){this.comboList.doActiveItem(this.comboList.getItems()[0])
}}else{this.comboList.moveActiveItem(event)
}}else{if(this.isSiperianMode){this.comboList.moveNotActiveItem(event)
}}break;
case Event.KEY_UP:if(this.comboList.isListOpen){this.comboList.moveActiveItem(event)
}else{if(this.isSiperianMode){this.comboList.moveNotActiveItem(event)
}}break;
case Event.KEY_ESC:this.field.value=this.field.value;
this.comboList.hideWithDelay();
if(this.isSiperianMode){this.field.value=this.comboList.defaultValue
}break;
case 8:if(this.isSiperianMode){this.field.value=this.field.value.substring(0,this.field.value.length);
this.comboList.activeItem=null
}break;
default:break
}},handlerVirtualField:function(event){if(event.keyCode==12){this.virtualField.value=this.virtualField.value.substring(0,this.virtualField.value.length-1)
}else{if(RichFaces.SipComboBox.SPECIAL_KEYS.indexOf(event.keyCode)==-1){this.virtualField.value=this.virtualField.value+event.keyCode
}}LOG.debug("handlerVirtualField - virtual value = "+this.virtualField.value)
},fieldFocusHandler:function(){this.doActive();
if((this.field.value==this.defaultMessage)&&(this.comboValue.value=="")){this.field.value=""
}else{if(this.isSelection){RichFaces.SipComboBox.textboxSelect(this.field,0,this.field.value.length)
}this.isSelection=true
}},fieldBlurHandler:function(event){if(!this.comboList.isList){this.enable();
var value=this.field.value;
if(value.length==0){this.applyDefaultText()
}else{var item=this.comboList.findItemBySubstr(value);
if(item){this.comboList.doSelectItem(item)
}}this.comboList.hideWithDelay()
}else{this.doActive()
}if(this.clickOnScroll){this.field.focus();
this.comboList.isList=false;
this.clickOnScroll=false
}},dataUpdating:function(event){LOG.debug("data Updating begin");
if(RichFaces.SipComboBox.SPECIAL_KEYS.indexOf(event.keyCode)==-1){if(this.filterNewValue){LOG.debug("DataUpdating "+this.field.value);
var allFoundLength=this.comboList.dataFilter(this.field.value);
if(this.comboList.getItems()&&this.comboList.getItems().length!=0){LOG.debug("dataUpdating list value= "+this.comboList.getItems().length);
var isSearchSuccessful=true;
LOG.debug("datUpdating isSearchSuccessul = true");
this.virtualField.value=this.field.value;
var item=this.comboList.findItemBySubstr(this.field.value);
LOG.debug("datUpdating found item = "+item);
if(item){LOG.debug("datUpdating item.innerHTML="+item.innerHTML);
this.comboList.dataFilter2(this.field.value,item.innerHTML);
if(allFoundLength==1){LOG.debug("dataUpdating - found 1 element close");
this.field.value=item.innerHTML;
this.comboList.hideWithDelay()
}var value=item.innerHTML;
var startInd=this.field.value.length;
var endInd=value.length;
this.field.value=value;
RichFaces.SipComboBox.textboxSelect(this.field,startInd,endInd)
}else{if(this.isSiperianMode){if(this.field.value&&this.field.value.length>0){this.field.value=this.field.value.substring(0,this.field.value.length-1)
}return 
}}}else{if(this.isSiperianMode){LOG.debug("dataUpdating - Search not successfully!");
Event.stop(event);
if(this.field.value&&this.field.value.length>0){this.field.value=this.field.value.substring(0,this.field.value.length-1)
}this.comboList.createDefaultList();
this.comboList.showWithDelay();
return 
}}}else{LOG.debug("DataUpdating else"+this.field.value);
if(!this.comboList.visible()){this.comboList.showWithDelay()
}var item=this.comboList.findItemBySubstr(this.field.value);
if(item){this.comboList.doActiveItem(item);
this.comboList.scrollingUpToItem(this.comboList.activeItem);
isSearchSuccessful=true
}}if(this.isValueSet(event)&&isSearchSuccessful){LOG.debug("dataUpdating () - this.isValueSet(event) && isSearchSuccessful");
this.setValue(false);
this.comboValue.value=this.field.value
}else{}}},wasTextDeleted:function(event){if((event.keyCode==Event.KEY_BACKSPACE)||(event.keyCode==Event.KEY_DELETE)||(event.ctrlKey&&(event.keyCode==88))){return true
}return false
},isValueSet:function(event){if(this.wasTextDeleted(event)||(event.keyCode==17)||event.altKey||event.ctrlKey||event.shiftKey){return false
}return true
},setValue:function(toSetOnly){LOG.debug("setValue() - begin");
if(!this.comboList.activeItem){LOG.debug("setValue() !this.comboList.activeItem- return;");
return 
}var value=jQuery(this.comboList.activeItem).text();
LOG.debug("setValue() value= "+value);
if(toSetOnly){LOG.debug("toSetOnly = true");
var oV=this.field.value;
if(oV==value){if(Prototype.Browser.Gecko){this.field.value="";
this.comboValue.value=""
}}this.field.prevValue=value;
this.field.value=value;
this.comboValue.value=value;
this.comboList.doSelectItem(this.comboList.activeItem);
this.combobox.fire("rich:onselect",{})
}else{LOG.debug("setValue() - this.directInputSuggestions ="+this.directInputSuggestions);
if(!this.isSiperianMode&&this.directInputSuggestions){var startInd=this.field.value.length;
var endInd=value.length;
this.field.value=value;
LOG.debug("select - startInd="+startInd+" endInd = "+endInd+"value="+this.field.value+",this.field.prevValue="+this.field.prevValue+",value="+value);
RichFaces.SipComboBox.textboxSelect(this.field,startInd,endInd)
}}},applyDefaultText:function(){this.field.className=this.classes.field.classes.disabled;
this.field.value=this.defaultMessage;
this.comboValue.value=""
},isActive:function(){return(this.field.className==this.classes.field.classes.active)
},doActive:function(){if(this.button.className.indexOf(this.classes.button.classes.hovered)!=-1){this.button.className=this.classes.button.classes.active+" "+this.classes.button.classes.hovered
}else{this.button.className=this.classes.button.classes.active
}var iconStyles=this.classes.buttonicon.style;
if(!iconStyles.active.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.active})
}this.field.className=this.classes.field.classes.active;
Element.setStyle(this.field,this.classes.field.style.active);
this.isDisabled=false
},disable:function(){this.button.className=this.classes.button.classes.disabled;
this.buttonBG.className=this.classes.buttonbg.classes.disabled;
this.field.className=this.classes.field.classes.disabled;
Element.setStyle(this.field,this.classes.field.style.disabled);
var styleCss=this.classes.button.style;
if(styleCss&&!styleCss.disabled.blank()){Element.setStyle(this.button,styleCss.disabled)
}var iconStyles=this.classes.buttonicon.style;
if(iconStyles&&!iconStyles.disabled.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.disabled})
}this.button.disabled=true;
this.field.disabled=true;
this.isDisabled=true
},enable:function(){this.button.className=this.classes.button.classes.normal;
this.buttonBG.className=this.classes.buttonbg.classes.normal;
this.field.className=this.classes.field.classes.normal;
var fieldStyles=this.classes.field.style.normal;
Element.setStyle(this.field,fieldStyles);
var iconStyles=this.classes.buttonicon.style;
if(!iconStyles.normal.blank()){Element.setStyle(this.button,{backgroundImage:iconStyles.normal})
}var styleCss=this.classes.button.style;
if(styleCss&&!styleCss.normal.blank()){Element.setStyle(this.button,styleCss.normal)
}this.button.disabled=false;
this.field.disabled=false;
this.isDisabled=false
},doDisable:function(){this.disable()
},doNormal:function(){this.enable()
},getCurrentWidth:function(){return this.combobox.firstChild.offsetWidth
},showList:function(){if(this.isDisabled){return 
}this.field.focus();
this.buttonClickHandler()
},hideList:function(){this.comboList.hideWithDelay()
}};
RichFaces.SipComboBox.textboxSelect=function(oTextbox,iStart,iEnd){if(Prototype.Browser.IE){var oRange=oTextbox.createTextRange();
oRange.moveStart("character",iStart);
oRange.moveEnd("character",-oTextbox.value.length+iEnd);
oRange.select()
}else{if(Prototype.Browser.Gecko){oTextbox.setSelectionRange(iStart,iEnd)
}else{oTextbox.setSelectionRange(iStart,iEnd)
}}};
RichFaces.SipComboBox.getSelectedText=function(oTextbox){if(window.getSelection){return window.getSelection().text
}else{if(document.selection){return document.selection.createRange()
}else{}}};
RichFaces.SipComboBox.SPECIAL_KEYS=[Event.KEY_RETURN,Event.KEY_UP,Event.KEY_DOWN,Event.KEY_RIGHT,Event.KEY_LEFT,Event.KEY_ESC,Event.KEY_TAB,16,8]

if(!window.Richfaces){window.Richfaces={}
}Richfaces.SipComboBoxStyles=Class.create();
Richfaces.SipComboBoxStyles.prototype={initialize:function(){this.commonStyles={button:{classes:{normal:"sip-combobox-font-inactive sip-combobox-button-icon-inactive sip-combobox-button-inactive",active:"sip-combobox-font sip-combobox-button-icon sip-combobox-button",disabled:"sip-combobox-font-disabled sip-combobox-button-icon-disabled sip-combobox-button-disabled",hovered:"sip-combobox-button-hovered"},style:{normal:"",active:"",disabled:""}},buttonbg:{classes:{normal:"sip-combobox-font-inactive sip-combobox-button-background-inactive sip-combobox-button-inactive",active:"sip-combobox-font sip-combobox-button-background sip-combobox-button",disabled:"sip-combobox-font-disabled sip-combobox-button-background-disabled sip-combobox-button-disabled"}},buttonicon:{style:{normal:"",active:"",disabled:""}},field:{classes:{normal:"sip-combobox-font-inactive sip-combobox-input-inactive",active:"sip-combobox-font sip-combobox-input",disabled:"sip-combobox-font-disabled sip-combobox-input-disabled"},style:{normal:"",active:"",disabled:""}},combolist:{list:{classes:{active:"sip-combobox-list-cord sip-combobox-list-scroll sip-combobox-list-decoration sip-combobox-list-position"},style:{active:""}},item:{normal:"sip-combobox-item",selected:"sip-combobox-item sip-combobox-item-selected"}}}
},getCommonStyles:function(){return this.commonStyles
}}

if(!window.Richfaces){window.Richfaces={}
}if(!window.SipComboUtils){window.SipComboUtils={}
}Richfaces.defined=function(o){return(typeof (o)!="undefined")
};
SipComboUtils.execOnLoad=function(func,condition,timeout){if(condition()){func()
}else{window.setTimeout(function(){SipComboUtils.execOnLoad(func,condition,timeout)
},timeout)
}};
SipComboUtils.Condition={ElementPresent:function(element){return function(){var el=document.getElementById(element);
return el&&el.offsetHeight>0
}
}};
Richfaces.getBody=function(){if(document.body){return document.body
}if(document.getElementsByTagName){var bodies=document.getElementsByTagName("BODY");
if(bodies!=null&&bodies.length>0){return bodies[0]
}}return null
};
Richfaces.zero=function(n){return(!Richfaces.defined(n)||isNaN(n))?0:n
};
Richfaces.getDocumentHeight=function(){var viewportheight=0;
var jqw=jQuery(window);
viewportheight=jqw.height()+jqw.scrollTop();
return viewportheight
};
Richfaces.getScrollWidth=function(elem){if(elem.clientWidth!=0){return elem.offsetWidth-elem.clientWidth
}return 0
};
Richfaces.getBorderWidth=function(el,side){return Richfaces.getStyles(el,side,Richfaces.borders)
};
Richfaces.getPaddingWidth=function(el,side){return Richfaces.getStyles(el,side,Richfaces.paddings)
};
Richfaces.getMarginWidth=function(el,side){return Richfaces.getStyles(el,side,Richfaces.margins)
};
Richfaces.getStyles=function(el,sides,styles){var val=0;
for(var i=0,len=sides.length;
i<len;
i++){var w=parseInt(Element.getStyle(el,styles[sides.charAt(i)]),10);
if(!isNaN(w)){val+=w
}}return val
};
Richfaces.borders={l:"border-left-width",r:"border-right-width",t:"border-top-width",b:"border-bottom-width"},Richfaces.paddings={l:"padding-left",r:"padding-right",t:"padding-top",b:"padding-bottom"},Richfaces.margins={l:"margin-left",r:"margin-right",t:"margin-top",b:"margin-bottom"}

if(!window.Richfaces){window.Richfaces={}
}Richfaces.SipComboBoxList=Class.create();
Richfaces.SipComboBoxList.prototype={initialize:function(spacerImg,comboboxId,listId,parentListId,selectFirstOnUpdate,filterNewValues,classes,width,height,itemsText,onlistcall,onlistclose,fieldId,shadowId,decorationId,showDelay,hideDelay,countOfItems,isSipMode){this.list=document.getElementById(listId);
this.listParent=$(parentListId);
this.listParentContainer=this.listParent.parentNode;
this.iframe=null;
this.fieldElem=document.getElementById(fieldId);
this.itemsText=itemsText;
LOG.debug("itemsText = "+itemsText);
this.shadowElem=document.getElementById(shadowId);
this.onlistcall=onlistcall;
this.onlistclose=onlistclose;
this.comboboxId=comboboxId;
this.spacerImg=spacerImg;
this.itemsCount=(countOfItems?countOfItems:0);
this.isSiperianMode=isSipMode;
if(this.onlistcall){this.listParent.observe("rich:onlistcall",this.onlistcall)
}if(this.onlistclose){this.listParent.observe("rich:onlistclose",this.onlistclose)
}this.selectFirstOnUpdate=selectFirstOnUpdate;
this.filterNewValues=filterNewValues;
this.isList=false;
this.defaultRowsAmount=15;
this.selectedItem=null;
this.activeItem=null;
this.showDelay=showDelay;
this.hideDelay=hideDelay;
this.classes=classes;
this.width=width;
this.height=height;
this.initDimensions();
this.scrollElements=null;
this.eventOnScroll=this.eventOnScroll.bindAsEventListener(this);
this.isListOpen=false;
this.isOpenedByClick=false;
this.defaultValue=this.fieldElem.value
},initDimensions:function(){this.listParent.style.visibility="hidden";
this.listParent.style.display="";
var el=this.listParent;
this.LAYOUT_BORDER_V=Richfaces.getBorderWidth(el,"tb");
this.LAYOUT_BORDER_H=Richfaces.getBorderWidth(el,"lr");
this.LAYOUT_PADDING_V=Richfaces.getPaddingWidth(el,"tb");
this.LAYOUT_PADDING_H=Richfaces.getPaddingWidth(el,"lr");
this.listParent.style.display="none";
this.listParent.style.visibility="visible"
},createDefaultList:function(){var items=new Array();
for(var i=0;
i<this.itemsText.length;
i++){items.push(this.createItem(this.itemsText[i],this.classes.item.normal))
}this.createNewList(items)
},getItems:function(){return this.getList().childNodes
},showWithDelay:function(){this.show()
},show:function(){this.defaultValue=this.fieldElem.value;
var pos=Position.cumulativeOffset(this.fieldElem);
this.fieldDimensions={};
this.fieldDimensions.left=pos[0];
this.fieldDimensions.top=pos[1];
this.fieldDimensions.height=this.fieldElem.parentNode.offsetHeight;
this.listParent.style.visibility="hidden";
this.listParent.style.display="";
this.setSize();
this.listParent.style.display="none";
this.listParent.style.visibility="visible";
this.injectListToBody(this.listParent);
this.setPosition(this.fieldDimensions.top,this.fieldDimensions.left,this.fieldDimensions.height);
LOG.debug("siperianMode = "+this.isSiperianMode);
if(!this.isOpenedByClick&&this.selectedItem){LOG.debug("show() - doSelectItem -"+this.selectedItem);
this.doSelectItem(this.findItemByDOMNode(this.selectedItem))
}var items=this.getItems();
if(items.length!=0){LOG.debug("show() - items length = "+items.length);
if(this.iframe){Element.show(this.iframe)
}this.listParent.style.display="";
if(!this.isOpenedByClick&&this.selectFirstOnUpdate){LOG.debug("show - ths.selectFirstOnUpdate");
if(this.selectedItem){this.doActiveItem(this.selectedItem)
}else{this.doActiveItem(items[0])
}}}this.listParent.fire("rich:onlistcall",{});
Richfaces.removeScrollEventHandlers(this.scrollElements,this.eventOnScroll);
this.scrollElements=Richfaces.setupScrollEventHandlers(this.listParentContainer.parentNode,this.eventOnScroll);
this.isListOpen=true
},injectListToBody:function(listElement){if(!this.listInjected){var parent=listElement.parentNode;
var child=document.body.insertBefore(parent.removeChild(listElement),null);
if(Richfaces.browser.isIE6&&this.iframe){document.body.insertBefore(parent.removeChild(this.iframe),child)
}this.listInjected=true
}},outjectListFromBody:function(parentElement,listElement){if(this.listInjected){var child=parentElement.appendChild(document.body.removeChild(listElement));
if(Richfaces.browser.isIE6&&this.iframe){parentElement.insertBefore(document.body.removeChild(this.iframe),child)
}this.listInjected=false
}},hideWithDelay:function(){this.hide();
this.listParent.fire("rich:onlistclose",{})
},hide:function(){Richfaces.removeScrollEventHandlers(this.scrollElements,this.eventOnScroll);
this.outjectListFromBody(this.listParentContainer,this.listParent);
this.resetState();
if(this.iframe){Element.hide(this.iframe)
}var component=this.listParent.parentNode;
component.style.position="static";
component.style.zIndex=0;
Element.hide(this.listParent);
this.isListOpen=false;
this.isOpenedByClick=false
},eventOnScroll:function(e){this.hideWithDelay()
},visible:function(){return this.listParent.visible()
},setSize:function(){var height=this.height;
var currentItemsHeight;
var rowsAmount;
var item=this.getItems()[0];
var actItPars=0;
if(item){var itemHeight=item.offsetHeight;
rowsAmount=this.getItems().length;
currentItemsHeight=itemHeight*rowsAmount;
if(this.height){if(parseInt(this.height)>currentItemsHeight){height=currentItemsHeight
}}else{if(rowsAmount<this.defaultRowsAmount){height=currentItemsHeight
}else{height=itemHeight*this.defaultRowsAmount
}}if(Prototype.Browser.IE){this.listParent.style.maxHeight=parseInt(height)+"px";
height=parseInt(height)+this.LAYOUT_BORDER_V+this.LAYOUT_PADDING_V
}height=parseInt(height)+"px";
this.getList().style.height=height;
if(this.shadowElem){if(!Richfaces.browser.isIE6){this.shadowElem.style.width=(parseInt(this.width)+7)+"px";
this.shadowElem.style.height=(parseInt(height)+9)+"px"
}else{this.shadowElem.style.visibility="hidden"
}}if(this.iframe){this.iframe.style.height=height
}this.setWidth(this.width)
}},setWidth:function(width){var positionElem=this.listParent.childNodes[1];
var combobox=this.listParent.parentNode;
var correction=parseInt(width)-Richfaces.getBorderWidth(positionElem.firstChild,"lr")-Richfaces.getPaddingWidth(positionElem.firstChild,"lr")+"px";
this.getList().style.width=correction;
if(this.iframe){this.iframe.style.width=correction
}},setPosition:function(fieldTop,fieldLeft,fieldHeight){var component=this.listParent.parentNode;
component.style.zIndex=2;
var docHeight=Richfaces.getDocumentHeight();
var comBottom=fieldTop+fieldHeight;
var listHeight=parseInt(this.getList().style.height);
if(this.getList().parentNode){listHeight+=Richfaces.getBorderWidth(this.getList().parentNode,"tb")
}var topPosition=comBottom;
var showPoint=fieldHeight;
if(parseInt(listHeight)>(docHeight-comBottom)){if(topPosition>(docHeight-comBottom)){showPoint=-parseInt(listHeight)
}}this.clonePosition(this.listParent,this.fieldElem,showPoint);
if(this.iframe){this.clonePosition(this.iframe,this.fieldElem,showPoint)
}},scrolling:function(event){var increment;
var scrollElem=this.getList();
var listTop=Richfaces.SipComboBoxList.getElemXY(scrollElem).top;
var scrollTop=scrollElem.scrollTop;
var itemTop=Richfaces.SipComboBoxList.getElemXY(this.activeItem).top;
if((event.keyCode==Event.KEY_UP)||(event.keyCode==33)){increment=(itemTop-scrollTop)-listTop;
if(increment<0){scrollElem.scrollTop+=increment
}}else{if((event.keyCode==Event.KEY_DOWN)||(event.keyCode==34)){var itemBottom=itemTop+this.activeItem.offsetHeight;
var increment=(itemBottom-scrollTop)-(listTop+scrollElem.clientHeight);
if(increment>0){scrollElem.scrollTop+=increment
}}}Event.stop(event)
},scrollingUpToItem:function(item){var scrollElem=this.getList();
var increment=(Richfaces.SipComboBoxList.getElemXY(item).top-scrollElem.scrollTop)-Richfaces.SipComboBoxList.getElemXY(scrollElem).top;
scrollElem.scrollTop+=increment
},doActiveItem:function(item){if(this.activeItem){this.doNormalItem(this.activeItem)
}this.activeItem=item;
this.changeItem(item,this.classes.item.selected)
},doNormalItem:function(item){this.activeItem=null;
this.changeItem(item,this.classes.item.normal)
},doSelectItem:function(item){this.selectedItem=item
},changeItem:function(item,className){item.className=className
},moveNotActiveItem:function(event){LOG.debug("moveNotActiveItem - event - "+event.keyCode);
var curItems=this.itemsText;
LOG.debug("this.selectedItem ="+this.selectedItem);
LOG.debug("currentItems length "+curItems.length);
if(this.selectedItem!=null&&curItems!=null&&curItems.length!=0){if(event.keyCode==Event.KEY_UP){LOG.debug("before do active item - ");
this.moveItem(event,this.selectedItem)
}else{if(event.keyCode==Event.KEY_DOWN){this.moveItem(event,this.selectedItem)
}}}},moveItem:function(event,item){LOG.debug("moveItem - item="+item.innerHTML);
var arrayLength=this.itemsText.length;
for(var i=0;
i<arrayLength;
i++){if(this.itemsText[i]==item.innerHTML){var realOffset=0;
if(event.keyCode==Event.KEY_UP){realOffset=i-1;
if(realOffset<0){realOffset=0
}this.fieldElem.value=this.itemsText[realOffset]
}else{if(event.keyCode==Event.KEY_DOWN){realOffset=i+1;
if(realOffset>=arrayLength){realOffset=arrayLength-1
}this.fieldElem.value=this.itemsText[realOffset]
}}this.selectedItem=document.createElement("span");
this.selectedItem.innerHTML=this.fieldElem.value;
this.selectedItem.setAttribute("class",this.classes.item.normal);
LOG.debug("After selected item = "+this.selectedItem.innerHTML)
}}},moveActiveItem:function(event){var item=this.activeItem;
LOG.debug("moveActiveItem ");
if(event.keyCode==Event.KEY_UP){LOG.debug("moveActiveItem - KEY_UP,current selected item = "+this.selectedItem);
if(!this.activeItem){LOG.debug("moveActiveItem - !this.activeItem ="+this.activeItem);
if(!this.selectFirstOnUpdate){var curItems=this.getItems();
if(curItems!=null&&curItems.length!=0){this.doActiveItem(curItems[curItems.length-1]);
this.scrollingUpToItem(curItems[curItems.length-1])
}}return 
}var prevItem=item.previousSibling;
LOG.debug("prevItem = "+prevItem);
if(prevItem){LOG.debug("prevItem succ = "+prevItem);
this.itemsRearrangement(item,prevItem)
}}else{if(event.keyCode==Event.KEY_DOWN){LOG.debug("moveActiveItem - KEY_DOWN,this.activeItem= "+this.activeItem);
if(!this.activeItem){if(!this.selectFirstOnUpdate){var curItems=this.getItems();
LOG.debug("moveActiveItem - KEY_DOWN,curItems.length = "+curItems.length);
if(curItems!=null&&curItems.length!=0){this.doActiveItem(curItems[0]);
this.scrollingUpToItem(curItems[0])
}}return 
}var nextItem=item.nextSibling;
LOG.debug("moveActiveItem - KEY_DOWN,nextItem= "+nextItem);
if(nextItem){LOG.debug("moveActiveItem - KEY_DOWN,nextItem= "+nextItem+" before this.itemsRearrangement");
this.itemsRearrangement(item,nextItem)
}}}this.scrolling(event)
},itemsRearrangement:function(item,newItem){this.doActiveItem(newItem)
},resetState:function(){if(this.filterNewValues){var tempList=this.list.cloneNode(false);
this.listParent.childNodes[1].firstChild.replaceChild(tempList,this.list);
this.list=$(tempList.id)
}else{if(this.activeItem){this.doNormalItem(this.activeItem)
}}this.activeItem=null;
this.isList=false
},dataFilter2:function(text,excludeItemAsText){this.createNewList(this.dataFilterAndRemoveFirstFoundItem(text,excludeItemAsText))
},dataFilter:function(text){var items=this.getFilteredItems(text);
var len=(items!=null?items.length:0);
this.createNewList(items);
return len
},dataFilterAndRemoveFirstFoundItem:function(text,excludeItemAsText){var items=new Array();
LOG.debug("getFilteredItems : - itemsText = "+this.itemsText);
for(var i=0;
i<this.itemsText.length;
i++){var itText=this.itemsText[i];
if(itText.toLowerCase()==excludeItemAsText.toLowerCase()){continue
}if(itText.substr(0,text.length).toLowerCase()==text.toLowerCase()){items.push(this.createItem(itText,this.classes.item.normal))
}}return items
},getFilteredItems:function(text){var items=new Array();
LOG.debug("getFilteredItems : - itemsText = "+this.itemsText);
for(var i=0;
i<this.itemsText.length;
i++){var itText=this.itemsText[i];
if(itText.substr(0,text.length).toLowerCase()==text.toLowerCase()){items.push(this.createItem(itText,this.classes.item.normal))
}}return items
},findItemByDOMNode:function(node){var substr=node.innerHTML.unescapeHTML();
return this.findItemBySubstr(substr)
},findItemBySubstr:function(substr){var items=this.getItems();
for(var i=0;
i<items.length;
i++){var item=items[i];
var itText=item.innerHTML.unescapeHTML();
if(itText.substr(0,substr.length).toLowerCase()==substr.toLowerCase()){return item
}}},createNewList:function(items){if(this.selectedItem){var node=this.selectedItem
}this.getList().innerHTML=items.join("");
if(this.selectedItem){var item=this.findItemByDOMNode(node);
if(item){this.doSelectItem(item)
}}},createItem:function(text,className){var escapedText=text.escapeHTML();
return'<span class="'+className+'">'+escapedText+"</span>"
},createIframe:function(parentElem,width,comboboxId,classes){var iframe=document.createElement("iframe");
iframe.id="iframe"+comboboxId;
iframe.style.display="none";
iframe.style.position="absolute";
iframe.frameBorder="0";
iframe.scrolling="no";
iframe.src="javascript:''";
iframe.style.width=width;
iframe.className=classes;
parentElem.insertBefore(iframe,parentElem.firstChild);
this.iframe=document.getElementById(iframe.id)
},PX_REGEX:/px$/,parseToPx:function(value){var v=value.strip();
if(this.PX_REGEX.test(v)){try{return parseFloat(v.replace(this.PX_REGEX,""))
}catch(e){}}return NaN
},createShadowDiv:function(){var shadowDiv=document.createElement("div");
shadowDiv.setAttribute("class","sip-combobox-shadow");
var styleAttr="";
if(this.itemsCount<3){styleAttr="display:none;"
}var table=document.createElement("table");
table.setAttribute("id",this.comboboxId+"shadow");
table.setAttribute("style",styleAttr);
table.setAttribute("border",0);
var tr1=document.createElement("tr");
var tr2=document.createElement("tr");
var tr1td1=document.createElement("td");
tr1td1.setAttribute("class","sip-combobox-shadow-tl");
var tr1td2=document.createElement("td");
tr1td2.setAttribute("class","sip-combobox-shadow-tr");
var tr2td1=document.createElement("td");
tr2td1.setAttribute("class","sip-combobox-shadow-bl");
var tr2td2=document.createElement("td");
tr2td2.setAttribute("class","sip-combobox-shadow-br");
var img1=this.createImgForShadovDiv(this.spacerImg,10,1,"","border:0");
var img2=this.createImgForShadovDiv(this.spacerImg,1,10,"","border:0");
var img3=this.createImgForShadovDiv(this.spacerImg,1,10,"","border:0");
var img4=this.createImgForShadovDiv(this.spacerImg,10,10,"","border:0");
shadowDiv.appendChild(table);
table.appendChild(tr1);
f;
table.appendChild(tr2);
tr1.appendChild(tr1td1);
tr1.appendChild(tr1td2);
tr2.appendChild(tr2td1);
tr2.appendChild(tr2td2);
tr1td1.appendChild(img1);
tr1td2.appendChild(img2);
tr2td1.appendChild(img3);
tr2td2.appendChild(img4);
this.listParent.appendChild(shadowDiv)
},createListPosition:function(){var position=document.createElement("div");
position.setAttribute("id",this.comboboxId+"listPosition");
position.setAttribute("class","sip-combobox-list-position");
var decoration=document.createElement("div");
decoration.setAttribute("id",this.comboboxId+"listDecoration");
decoration.setAttribute("class","sip-combobox-list-decoration");
this.list=document.createElement("div");
this.list.setAttribute("id",this.comboboxId+"list");
this.list.setAttribute("class","sip-combobox-list-scroll");
position.appendChild(decoration);
decoration.appendChild(this.list);
this.listParent.appendChild(position)
},createImgForShadovDiv:function(srcImg,widht,height,alt,style){var img=document.createElement("img");
img.setAttribute("src",srcImg);
img.setAttribute("width",widht);
img.setAttribute("height",height);
img.setAttribute("alt",alt);
img.setAttribute("style",style);
return img
},getList:function(){if(!this.list){this.createShadowDiv();
this.createListPosition()
}return this.list
},clonePosition:function(target,source,vOffset){var jqt=jQuery(target);
var jqs=jQuery(source);
var so=jqs.offset();
var hidden=(jqt.css("display")=="none");
var oldVisibility;
if(hidden){oldVisibility=jqt.css("visibility");
jqt.css("visibility","hidden").css("display","")
}var left=this.parseToPx(jqt.css("left"));
if(isNaN(left)){left=0;
jqt.css("left","0px")
}var top=this.parseToPx(jqt.css("top"));
if(isNaN(top)){top=0;
jqt.css("top","0px")
}var to=jqt.offset();
if(hidden){jqt.css("display","none").css("visibility",oldVisibility)
}jqt.css({left:(so.left-to.left+left)+"px",top:(so.top-to.top+top+vOffset)+"px"})
}};
Richfaces.SipComboBoxList.getElemXY=function(elem){var x=elem.offsetLeft;
var y=elem.offsetTop;
for(var parent=elem.offsetParent;
parent;
parent=parent.offsetParent){x+=parent.offsetLeft;
y+=parent.offsetTop
}return{left:x,top:y}
}

TabPanel=Class.create();
TabPanel.isTabActive=function(tab){if(tab){return Element.hasClassName(tab,"containerActive")||Element.hasClassName(tab,"sip-static-pnl-tabContainerActive")
}return false
};
TabPanel.CLASSES={STATIC:{ARROWLEFT:"sip-static-pnl-arrowLeft",ARROWRIGHT:"sip-static-pnl-arrowRight",ACTIVETAB:"sip-static-pnl-tabContainerActive",INACTIVETAB:"sip-static-pnl-tabContainerInactive"},DYNAMIC:{ARROWLEFT:"arrowLeft",ARROWRIGHT:"arrowRight",ACTIVETAB:"containerActive",INACTIVETAB:"containerInActive"}};
TabPanel.findByType=function(value,type){return TabPanel.CLASSES[type.toUpperCase()][value]
};
TabPanel.IMAGES={CLOSE:"spn_close_btn.png",LONG_RUNNING:"spn_ico_tab_process.gif"};
TabPanel.STATIC="static";
TabPanel.DYNAMIC="dynamic";
Object.extend(TabPanel.prototype,{initialize:function(id,containerId,selectedTabName,maxTabWidth,ontabchange,ontabclosed,tabsInfo,type,addNewTabScript,highlightStyleClass){this.id=id;
this.containerId=containerId;
this.maxTabWidth=(maxTabWidth>700)?700:maxTabWidth;
this.ontabchange=ontabchange;
this.ontabclosed=ontabclosed;
this.selectedTabName=selectedTabName;
this.type=type;
this.highlightStyleClass=highlightStyleClass;
this.addNewTabScript=addNewTabScript;
this.element=$(this.id);
this.tabs=$(this.id+":tabs");
this.tabsContainer=$(this.id+":tabsContainer");
this.tabsInfo=tabsInfo;
this.left=$(this.id+":left");
this.right=$(this.id+":right");
this.content=$(this.id+":content");
this.rightTop=$(this.id+":rightTop");
this.contentContainer=$(this.id+":contentContainer");
this.tabListHeaderDiv=$(this.id+":tabListHeaderDiv");
this.tabListContentDiv=$(this.id+":tabListContentDiv");
this.tabListLeftDiv=$(this.id+":tabListLeft");
this.tabListDiv=$(this.id+":tabListDiv");
var regex=new RegExp(/url\((.+)\)+/);
var reg=regex.exec(this.left.getStyle("background-image"));
if(reg&&reg["1"]){var src=reg["1"];
src=src.replace('"',"");
this.srcPrefix=PNGFIX.getImagePrefix(src);
this.srcPostfix=PNGFIX.initPostFix()
}this.tabs2Delete=new Array();
this.longRunningTabs=new Hash();
this.tabListVisible=false;
this.maxLabelWidth=null;
window.setTimeout(this.init.bind(this),100);
Event.observe(window,"resize",this.onresize.bind(this));
this.element.component=this;
this["rich:destructor"]="destroy"
},destroy:function(){Event.stopObserving(window,"resize",this.onresize);
if(this.scroller){this.scroller.unbindControls(this.left);
this.scroller.unbindControls(this.right);
delete this.scroller
}this.finishSwitchMode();
delete this.tabsInfo;
delete this.tabs2Delete;
this.tabListHeaderDiv=null;
this.tabListContentDiv=null;
this.tabListLeftDiv=null;
this.tabListDiv=null;
this.left=null;
this.right=null;
this.tabsContainer=null;
this.tabs=null;
this.content=null;
this.contentBg=null;
this.contentContainer=null;
this.element.component=null;
this.element=null
},isReady:function(){return this.tabs.getWidth()!=0
},init:function(){if(this.tabsInfo&&this.tabsInfo.length==0){return 
}if(!this.isReady()){clearTimeout(this.initTimeout);
this.initTimeout=window.setTimeout(new Function("","var d = $('"+this.id+"'); if(d && d.component && d.component.init) { d.component.init(); }"),200);
return 
}this.collectTabsInfo(this.tabsInfo);
this.initTabList();
this.initScrolling();
this.tabs.style.visibility="visible";
this.content.style.visibility="visible";
if(this.activeTabId){this.showContent($(this.getTabId(this.activeTabId)))
}A4J.AJAX.AddListener({onafterajax:function(req,event,data){if(!$(this.id)&&this.eventKeyPress){Event.stopObserving(document,"keydown",this.eventKeyPress)
}}.bind(this)})
},isDynamic:function(){return this.type==TabPanel.DYNAMIC
},collectTabsInfo:function(tabsInfo){this.tabsInfo=new Array();
var tabs=this.tabs.firstChild.childNodes;
for(var i=0;
i<tabs.length;
i++){if(tabs[i].id.indexOf(":header")!=-1){var tabHeader=tabs[i];
this.collectTabInfo(tabHeader,tabsInfo[tabHeader.id])
}}this.tabsInfo=tabs
},collectTabInfo:function(tabHeader,info){var componentId=info["0"];
var id=tabHeader.id;
tabHeader.name=info["1"];
if(tabHeader.name==this.selectedTabName){this.activeTabId=id
}tabHeader.title="";
tabHeader.componentId=componentId;
tabHeader.ontabenter=info["5"];
tabHeader.ontableave=info["4"];
if(info["3"]){this.longRunningTabs.set(componentId,true)
}this.initTabWidth(tabHeader);
Event.observe(tabHeader,"mouseover",new Function("$('"+this.id+"').component.initTitle(this);"))
},getLeftDiv:function(header){if(this.isDynamic()){return header.firstChild.firstChild
}else{return header.firstChild
}},getRightDiv:function(header){return header.firstChild.lastChild
},getLabelDiv:function(header){if(this.isDynamic()){return this.getLeftDiv(header).firstChild
}else{return this.getLeftDiv(header)
}},getLinkDiv:function(header){if(!this.isDynamic()){return header
}return header.firstChild
},calculateMaxLabelWidth:function(tab){var maxTabWidth=this.maxTabWidth;
if(!maxTabWidth){return 
}var left=this.getLeftDiv(tab);
var right=this.getRightDiv(tab);
var label=this.getLabelDiv(tab);
this.getOffsets(label);
if(this.isDynamic()){this.maxLabelWidth=maxTabWidth-Richfaces.getComputedStyleSize(left,"padding-left")-Richfaces.getComputedStyleSize(right,"width")-Richfaces.getComputedStyleSize(label,"margin-right")
}else{this.maxLabelWidth=maxTabWidth-Richfaces.getComputedStyleSize(right,"width")
}},getOffsets:function(label){if(!this.offsets){var active=$(this.activeTabId);
if(!active&&!label){return 
}var label=label||this.getLabelDiv(active);
this.offsets={"label":{"marginRight":Richfaces.getComputedStyleSize(label,"margin-right")||Richfaces.getComputedStyleSize(label,"marginRight"),"paddingLeft":Richfaces.getComputedStyleSize(label,"padding-left")||Richfaces.getComputedStyleSize(label,"paddingLeft"),"paddingRight":Richfaces.getComputedStyleSize(label,"padding-right")||Richfaces.getComputedStyleSize(label,"paddingRight")},"tabs":{"marginLeft":Richfaces.getComputedStyleSize(this.tabs,"margin-left")||Richfaces.getComputedStyleSize(this.tabs,"marginLeft")}}
}return this.offsets
},initTitle:function(elt){var label=this.getLabelDiv(elt);
if(label.cutOff&&label.origText){elt.title=label.origText
}else{elt.title=""
}},_initScrolling:function(){if(this.scroller.isScrollNeed()){this.scroller.bindControl(this.right,hScroller.CONTROL.FORWARD);
this.scroller.bindControl(this.left,hScroller.CONTROL.BACK);
this.right.style.visibility="visible";
this.left.style.visibility="visible";
this.tabListHeaderDiv.style.visibility="visible";
var actualWidth=this.getScrollWidth();
if(!this.isDynamic()){this.element.className="sip-static-pnl-searchContainer"
}else{this.tabListHeaderDiv.style.visibility="visible"
}if(this.tabsContainer&&this.tabsContainer.firstChild){this.tabsContainer.firstChild.style.width=actualWidth+"px"
}this.onleft(false);
this.onright(true)
}else{this.right.style.visibility="hidden";
this.left.style.visibility="hidden";
this.tabListHeaderDiv.style.visibility="hidden";
if(this.tabsContainer&&this.tabsContainer.firstChild){this.tabsContainer.firstChild.style.width="100%"
}if(!this.isDynamic()){this.element.className="sip-static-pnl-searchContainerNoScroll"
}}this.scroller.init(true);
var active=(this.activeTabId)?$(this.activeTabId):null;
if(active){this.scroller.activateTargetItem(null,active)
}},initScrolling:function(){if(this.scroller){this.scroller.unbindControls(this.left);
this.scroller.unbindControls(this.right);
delete this.scroller
}var widthCorrection=0;
this.tabs.getScrollWidth=this.getScrollWidth.bind(this);
this.scroller=new hScroller(this.id,this.tabs,this.tabsInfo,{"onleft":function(enabled){this.onleft(enabled)
}.bind(this),"onright":function(enabled){this.onright(enabled)
}.bind(this)},widthCorrection);
this._initScrolling()
},getScrollWidth:function(){var marginLeft=this.getOffsets()?this.getOffsets().tabs.marginLeft:(Richfaces.getComputedStyleSize(this.tabs,"margin-left")||Richfaces.getComputedStyleSize(this.tabs,"marginLeft"));
return this.tabsContainer.offsetWidth-marginLeft-this.tabListHeaderDiv.getWidth()-this.right.getWidth()+2
},onresize:function(){if(this.scroller&&!this.scroller.initialized){return 
}clearTimeout(this.onResizeTimeoutId);
if(this.tabsContainer&&this.tabsContainer.firstChild){this.tabsContainer.firstChild.style.width="100%"
}this.onResizeTimeoutId=setTimeout(function(){if(this.scroller){this._initScrolling()
}}.bind(this),2000)
},onleft:function(enabled){this.left.className=TabPanel.findByType("ARROWLEFT",this.type)+(enabled?"":"Dis")
},onright:function(enabled){this.right.className=TabPanel.findByType("ARROWRIGHT",this.type)+(enabled?"":"Dis")
},initTabWidth:function(tab){if(!this.maxLabelWidth){this.calculateMaxLabelWidth(tab)
}var maxLabelWidth=this.maxLabelWidth;
var active=TabPanel.isTabActive(tab);
var label=this.getLabelDiv(tab);
label.origText=label.origText||(label.textContent||label.innerText);
label.origWidthActive=label.origWidthActive||label.scrollWidth;
var cutOff=false;
if(label.origWidthActive>this.maxLabelWidth){cutOff=true
}if(cutOff){if(!Prototype.Browser.IE){this.cutOffLabel(label,label.origWidthActive)
}else{label.cutOff=true
}}this.setActualLabelWidth(label,(cutOff)?this.maxLabelWidth:label.offsetWidth);
if(this.isDynamic()){label.className=TabPanel.isTabActive(tab)?"spn_sr_tabpanel_contentActive":"spn_sr_tabpanel_content"
}else{label.className="sip-static-pnl-left"
}label.style.overflow="hidden"
},adjustTabWidth:function(tab){var label=this.getLabelDiv(tab);
var active=TabPanel.isTabActive(tab);
this.cutOffLabel(label,active?label.origWidthActive:label.origWidthInactive)
},setActualLabelWidth:function(label,width){if(!this.isDynamic()){width=width-this.getOffsets(label).label.paddingLeft-this.getOffsets(label).label.paddingRight
}label.style.width=width+"px"
},cutOffLabel:function(label,origWidth){if(!this.maxLabelWidth){return 
}var k=(this.maxLabelWidth/origWidth);
var l=label.origText.length;
var text;
if(k<1){l=l*k-"...".length;
if(l>0){text=label.origText.substring(0,l)+"...";
label.cutOff=true
}}else{text=label.origText
}if(label.textContent){var c=this.findTextContentHolder(label,label.textContent);
if(c&&text){c.textContent=text
}}},findTextContentHolder:function(label,_text){if(label.firstChild&&label.firstChild.data&&label.firstChild.data==_text){return label
}var children=label.getElementsByTagName("*");
for(var i=0;
i<children.length;
i++){var c=children[i];
if(c&&c.innerHTML&&c.innerHTML==_text){return c.parentNode
}}},_closeTab:function(id,event){var tab=$(id);
this.removeFromTabList(id);
this.deleteTab(tab);
this.deleteTabContent(tab);
this.initScrolling();
this.scroller.correctScroll()
},deleteTabContent:function(tab){if(!tab){return 
}var contentTab=$(tab.componentId);
if(contentTab){if(window.RichFaces&&window.RichFaces.Memory){window.RichFaces.Memory.clean(contentTab,true)
}contentTab.innerHTML=""
}},_findNextTab:function(id){if(this.activeTabId!=null&&id!=this.activeTabId){return null
}var n=this.findTabNumber(id);
var next;
if(n>0){next=n-1
}else{if(this.tabsInfo.length>0){next=1
}}return this.tabsInfo[next]
},closeTab:function(id,event,options){Event.stop(event);
var tab=$(id);
if(this.longRunningTabs.get(tab.componentId)){return 
}var result=true;
if(this.ontabclosed){new Function("event","current",this.ontabclosed)(event,tab.name)
}if(result){var next=this._findNextTab(id);
options["parameters"][this.id+":close"]=tab.name;
options["parameters"][tab.componentId]=null;
if(next){options["parameters"][this.id+":tabName"]=next.name;
options["parameters"][next.componentId]=next.componentId
}options["oncomplete"]=function(){this._closeTab(id,event);
if(next&&this.onTabChange(event,next)){this.switchTabs(next,options)
}}.bind(this)
}this.loadTab(event,options);
return false
},selectTab:function(tab,active){if(!tab){return false
}var a=this.getLinkDiv(tab);
if(active){if(a){a.className="activeTabLink"
}tab.className=TabPanel.findByType("ACTIVETAB",this.type)
}else{if(a){a.className="activeTabLink"
}tab.className=TabPanel.findByType("INACTIVETAB",this.type)
}var label=this.getLabelDiv(tab);
if(this.isDynamic()){label.className=active?"spn_sr_tabpanel_contentActive":"spn_sr_tabpanel_content"
}if(active&&tab.ontabenter){this.fireEvent(tab.ontabenter)
}},fireEvent:function(handler,ev){var e=ev;
if(handler){if(typeof (handler)=="function"){handler(e)
}else{new Function("event",handler)(e)
}}},findTabNumber:function(id){for(var i=0;
i<this.tabsInfo.length;
i++){if(this.tabsInfo[i]&&id==this.tabsInfo[i].id){return i
}}return null
},switchTabs:function(env,options){if(!env){return 
}var id=env.id;
this.selectTab($(this.activeTabId),false);
this.selectTab(env,true);
var old=this.activeTabId?$(this.getTabId(this.activeTabId)):null;
if(old){old.style.display="none"
}var tab=$(this.getTabId(id));
if(tab){this.showContent(tab)
}if(old){this.deleteTabContent($(this.activeTabId))
}this.activeTabId=id;
this.activateTabListItem(id);
this.onloaded(env)
},showContent:function(tab){if(this.isDynamic()&&tab){tab.style.visibility="hidden";
tab.style.display="";
this._coverTabPanelContent(this.longRunningTabs.get(tab.id),tab);
tab.style.visibility="visible"
}else{if(tab){this.contentContainer.style.visibility="visible";
tab.style.display="";
if(this.scroller&&!this.scroller.isScrollNeed()){this.rightTop.className="sip-static-pnl-bg-right-top"
}else{this.rightTop.className="sip-static-pnl-bg-right-top-noround"
}}}},getTabId:function(id){return id=id.substring(0,id.lastIndexOf(":"))
},_onTabClick:function(){if(!this.callbackParams){return 
}var event=this.callbackParams.event;
var env=this.callbackParams.env;
var options=this.callbackParams.options;
if(TabPanel.isTabActive(env)){var c=$(env.componentId);
if(c&&!Element.visible(c)){this.showContent(c)
}this.finishSwitchMode();
return false
}if(!this.onTabChange(event,env)){return false
}options["oncomplete"]=function(){this.switchTabs(env,options)
}.bind(this);
this.loadTab(event,options);
if(env.ontableave){this.fireEvent(env.ontableave,event)
}this.callbackParams=null
},onTabClick:function(event,env,options){this.callbackParams={};
this.callbackParams.event=A4J.AJAX.CloneObject(event,false);
this.callbackParams.env=env;
this.callbackParams.options=options;
this.scroller.activateTargetItem(this._onTabClick.bind(this),env);
return false
},onloaded:function(tab){this.deleteTabs();
this.finishSwitchMode()
},deleteTabs:function(){if(this.tabs2Delete&&this.tabs2Delete.each){this.tabs2Delete.each(function(id){this.deleteTabContent($(id))
}.bind(this))
}this.tabs2Delete=new Array()
},loadTab:function(event,options){if(!this.form){this.form=A4J.AJAX.locateForm($(this.id))
}var formId=(this.form)?this.form.id:null;
A4J.AJAX.Submit(this.containerId,formId,event,options)
},deleteTab:function(tab){Event.stopObserving(tab,"mouseover");
tab.parentNode.removeChild(tab)
},onTabChange:function(event,tab){if(this.ontabchange){return this.ontabchange()
}return true
},initTabList:function(){var tabs=this.tabsInfo;
var tabListHeader=this.tabListHeaderDiv;
var tabListContent=this.tabListContentDiv;
var tabList=this.tabListDiv;
if(tabs){for(var i=0;
i<tabs.length;
i++){this.addTabListItem(tabs[i])
}Event.observe(tabListHeader,"click",this.showHideTabList.bindAsEventListener(this));
Event.observe(tabList,"mouseout",this.hideTabList.bindAsEventListener(this))
}},addTabListItem:function(tab){var id=tab.id;
var tabLabel=this.getLabelDiv(tab);
var divTag=document.createElement("div");
Event.observe(divTag,"click",new Function("event","this.selectTabInList(event, '"+id+"')").bind(this));
Event.observe(divTag,"mouseover",this.tabListItemMouseOverListener.bindAsEventListener(this));
Event.observe(divTag,"mouseout",this.tabListItemMouseOutListener.bindAsEventListener(this));
if(TabPanel.isTabActive(tab)){divTag.className="tabListItemActive"
}else{divTag.className="tabListItemNormal"
}divTag.id=id+":tabListItem";
divTag.innerHTML=tabLabel.innerHTML;
divTag.tab=tab;
this.tabListContentDiv.appendChild(divTag)
},selectTabInList:function(e,tabId){this.hideTabList();
this.tabListItemMouseOutListener(e);
var tab=$(tabId);
if(tab.onclick){tab.onclick()
}return false
},initTabListPosition:function(){var contentHeight=this.content.offsetHeight;
var tabListHeight=this.tabListContentDiv.scrollHeight;
var height=(tabListHeight>contentHeight)?contentHeight-5:tabListHeight;
height=height+"px";
this.tabListContentDiv.style.height=height;
this.tabListLeftDiv.style.height=height
},showHideTabList:function(e){this.initTabListPosition();
if(this.tabListVisible){this.hideTabList()
}else{this.tabListDiv.style.visibility="visible";
this.tabListVisible=true
}},hideTabList:function(e){this.tabListDiv.style.visibility="hidden";
this.tabListVisible=false
},tabListItemMouseOverListener:function(e){if(e&&e.stopPropagation){e.stopPropagation()
}var item=e.target;
if(item&&item.tab){var tab=item.tab;
if(!TabPanel.isTabActive(tab)){item.className="tabListItemHovered"
}}},tabListItemMouseOutListener:function(e){if(e&&e.stopPropagation){e.stopPropagation()
}var item=e.target;
if(item&&item.tab){var tab=item.tab;
if(!TabPanel.isTabActive(tab)){item.className="tabListItemNormal"
}}},activateTabListItem:function(tabId){var tabListItemId="";
tabListItemId+=tabId;
tabListItemId+=":tabListItem";
var tabListContent=this.tabListContentDiv;
if(tabListContent){for(var i=0;
i<tabListContent.childNodes.length;
i++){var el=tabListContent.childNodes[i];
if(el.tagName.toLowerCase()=="div"){if(el.id==tabListItemId){el.className="tabListItemActive"
}else{el.className="tabListItemNormal"
}}}}},removeFromTabList:function(id){var tabListItem=$(id+":tabListItem");
Event.stopObserving(tabListItem,"click");
Event.stopObserving(tabListItem,"mouseover");
Event.stopObserving(tabListItem,"mouseout");
tabListItem.parentNode.removeChild(tabListItem)
},findTabByName:function(name){for(var i=0;
i<this.tabsInfo.length;
i++){var tab=this.tabsInfo[i];
if(tab&&tab.name==name){return tab
}}},setLongRunning:function(tabName){if(!this.isDynamic()){return 
}var tab=this.findTabByName(tabName);
if(tab){this.longRunningTabs.set(tab.componentId,true);
var img=$(tab.id+"Close");
if(img){img.src=this.srcPrefix+TabPanel.IMAGES.LONG_RUNNING+this.srcPostfix
}if(TabPanel.isTabActive(tab)){this._coverTabPanelContent(true,$(tab.componentId))
}}},releseLongRunning:function(tabName){if(!this.isDynamic()){return 
}var tab=this.findTabByName(tabName);
if(tab){this.longRunningTabs.unset(tab.componentId);
var img=$(tab.id+"Close");
if(img){img.src=this.srcPrefix+TabPanel.IMAGES.CLOSE+this.srcPostfix
}if(TabPanel.isTabActive(tab)&&this.contentCoverage){this._coverTabPanelContent(false)
}}if(TabPanel.isTabActive(tab)){this.showContent($(tab.componentId))
}},_coverTabPanelContent:function(show,tab){if(show){if(!this.contentCoverage){var c=document.createElement("div");
c.className="tabContentCoverage";
if(tab){c.style.height=tab.offsetHeight+"px"
}this.contentCoverage=c;
Event.observe(c,"focus",function(e){return false
});
this.content.appendChild(c)
}}else{if(this.contentCoverage){this.contentCoverage.parentNode.removeChild(this.contentCoverage);
this.contentCoverage=null
}}},activateFirstTab:function(e){var tab=this.tabsInfo.first();
if(tab&&tab.onclick){tab.onclick(e)
}},getClientIdPrefix:function(){return this.getTabId(this.id)+":"
},addNewTabs:function(e,args){if(!args||(args&&!args.tabsId)){return 
}var tabsIds="";
var param="";
var toDelete;
this.addNewTabScript(param)
},onNewTabLoaded:function(clientIds,infos){var active;
for(var i=0;
i<clientIds.length;
i++){var tabId=clientIds[i];
var info=infos[i];
var headerId=tabId+":header";
var header=$(headerId);
if(header){Element.show(header)
}this.collectTabInfo(header,info);
this.addTabListItem(header);
active=header
}this.scroller.items=this.tabs.firstChild.childNodes;
this.switchTabs(active);
this._initScrolling()
},creatNewTabs:function(tabsId){for(var i=0;
i<tabsId.length;
i++){var tabId=tabsId[i];
var headerId=tabId+":header";
if(!$(headerId)){var header=document.createElement("div");
Element.hide(header);
header.id=headerId;
this.tabs.firstChild.appendChild(header)
}var content=document.createElement("div");
Element.hide(content);
content.id=tabId;
this.content.appendChild(content)
}},startSwitchMode:function(){var active=$(this.activeTabId);
if(this.switchMode||!active){return 
}$(active).click();
this.highlightTabId=this.activeTabId;
this.highlightTab(active,true);
this.eventKeyPress=this.processKeyDown.bindAsEventListener(this);
Event.observe(document,"keydown",this.eventKeyPress);
this.eventLostFocus=this.processLostFocus.bindAsEventListener(this);
Event.observe(document,"click",this.eventLostFocus);
this.eventPreventLostFocus=this.processPreventLostFocus.bindAsEventListener(this);
Event.observe(this.element,"click",this.eventPreventLostFocus);
this.switchMode=true;
this.inFocus=true;
this.switcherLocked=false
},removeKeyListeners:function(){Event.stopObserving(document,"keydown",this.eventKeyPress);
Event.stopObserving(document,"click",this.eventLostFocus);
Event.stopObserving(this.element,"click",this.eventPreventLostFocus)
},processPreventLostFocus:function(){this.inFocus=true;
this.preventLostFocus=true
},processLostFocus:function(){if(!this.preventLostFocus){this.lostFocus();
this.finishSwitchMode()
}else{this.preventLostFocus=false
}},finishSwitchMode:function(){if(this.switchMode){this.lostFocus();
this.removeKeyListeners();
this.switchMode=false;
var active=$(this.highlightTabId);
if(active){this.highlightTab(active,false)
}this.highlightTabId=null
}},lostFocus:function(){this.inFocus=false
},processKeyDown:function(event){switch(event.keyCode||event.charCode){case Event.KEY_LEFT:if(this.inFocus&&!this.scroller.scrollMutex){var active=$(this.highlightTabId);
var prev=active?active.previousSibling:null;
if(active&&prev){this.highlightTab(active,false);
this.highlightTab(prev,true);
this.highlightTabId=prev.id
}}break;
case Event.KEY_RIGHT:if(this.inFocus&&!this.scroller.scrollMutex){var active=$(this.highlightTabId);
var next=active?active.nextSibling:null;
if(active&&next){this.highlightTab(active,false);
this.highlightTab(next,true);
this.highlightTabId=next.id
}}break;
case 13:if(this.inFocus&&!this.scroller.scrollMutex){var active=$(this.highlightTabId);
if(active&&active.onclick){active.onclick(event)
}}break;
case Event.KEY_TAB:this.finishSwitchMode();
break;
case Event.KEY_ESC:this.finishSwitchMode();
break
}},highlightTab:function(tab,f){var cssClass=this.isDynamic()?(TabPanel.isTabActive(tab)?"containerActiveSelected":"containerInActiveSelected"):(TabPanel.isTabActive(tab)?"sip-static-pnl-tabContainerActiveSelected":"sip-static-pnl-tabContainerInactiveSelected");
if(f){this.scroller.activateTargetItem(function(){$(tab).addClassName(cssClass)
},tab)
}else{$(tab).removeClassName(cssClass)
}}})

SipTooltip=Class.create({initialize:function(tooltipId){this["rich:destructor"]="destroy";
if($(tooltipId)){this.tooltipId=tooltipId
}},destructor:function(){this.tooltipId=null
},hide:function(event){if($(this.tooltipId)){$(this.tooltipId).style.display="none"
}},showExt:function(tooltipId,oEvent,text){if(tooltipId){var tooltipInnerDiv=$(tooltipId+"content");
if(tooltipInnerDiv&&text){tooltipInnerDiv.innerHTML=text;
var target=oEvent.srcElement?oEvent.srcElement:oEvent.target;
if(target){Event.observe(target,"mouseout",this.hide.bindAsEventListener(this))
}$(tooltipId).component.show(oEvent)
}}},show:function(oEvent,text){this.showExt(this.tooltipId,oEvent,text)
}})

ToolBar=Class.create();
ToolBar.prototype={initialize:function(id,isDisabled,imgSrc,groupsNumbers,isHighlighted){this["rich:destructor"]="destroy";
this.id=id;
this.isDisabled=isDisabled;
this.isHighlighted=isHighlighted;
this.element=$(this.id);
this.element.component=this;
this.groupsNumbers=groupsNumbers;
this.initHandlers();
this.srcPrefix=PNGFIX.getImagePrefix(imgSrc);
this.pngfix();
Utils.execOnLoad(function(){if(this.isDisabled){this.disable()
}else{this.disableGroups()
}}.bind(this),Utils.Condition.ElementPresent(id),200)
},destroy:function(){this.isDisabled=null;
this.isHighlighted=null;
if(this.element){this.element.stopObserving();
if(this.element.component){this.element.component=null
}this.element=null
}this.srcPrefix=null
},pngfix:function(){if(!isIE()){return 
}},disable:function(){this.element.style.position="relative";
var tr=$(this.id).getElementsBySelector("tr")[0];
if(tr.offsetWidth==0){Event.observe(window,"load",this.disable.bind(this));
return 
}glassDiv=document.createElement("div");
glassDiv.style.width=(tr.offsetWidth-20)+"px";
glassDiv.style.height=tr.offsetHeight+"px";
glassDiv.style.left=(tr.offsetLeft+10)+"px";
glassDiv.style.top=tr.offsetTop+"px";
glassDiv.style.position="absolute";
glassDiv.style.opacity=0.4;
glassDiv.style.filter="alpha(opacity=40)";
if(Prototype.Browser.IE){glassDiv.style.height="26px";
glassDiv.style.backgroundPositionY="-2px";
glassDiv.style.top="2px"
}glassDiv.className="bg";
$(this.id).insert(glassDiv);
this.stopObservingFocus()
},disableGroup:function(group){var div=document.createElement("div");
div.style.position="absolute";
div.style.zIndex=1;
var d=document.createElement("div");
d.style.width=group.offsetWidth+"px";
d.style.height=group.offsetHeight+"px";
if(Prototype.Browser.IE){d.style.backgroundPositionY="-2px";
d.style.height="26px"
}d.className="bg siptoolbar_disgroup";
div.appendChild(d);
group.insertBefore(div,group.firstChild)
},disableGroups:function(){var tr=$(this.id).getElementsBySelector("tr")[0];
if(tr.offsetWidth==0){Event.observe(window,"load",this.disableGroups.bind(this));
return 
}if(this.isDisabled||!this.groupsNumbers){return false
}var groups=$(this.id).getElementsByClassName("group");
if(groups){for(var i=0;
i<groups.length;
i++){if(this.groupsNumbers[i]){this.disableGroup($(groups[i]))
}}}},initHandlers:function(){var tds=$(this.id).getElementsByClassName("group");
for(i=0;
i<tds.length;
i++){(function(i,me){if(!me.groupsNumbers[i]){tds[i].observe("mouseover",function(){me.active(tds[i],true)
});
tds[i].observe("mouseout",function(){me.active(tds[i],false)
})
}})(i,this)
}},stopObservingFocus:function(){var tds=$(this.id).getElementsBySelector("td");
for(i=1;
i<tds.length-1;
i++){if(Element.hasClassName(tds[i],"group")){tds[i].stopObserving("mouseover");
tds[i].stopObserving("mouseout")
}}},active:function(elem,actived){if($(this.isHighlighted)){elem.className=(actived)?"group active":"highlightedGroup"
}else{elem.className=(actived)?"group active":"group"
}if(isIE()){}}}

VerticalScroller=Class.create();
Object.extend(VerticalScroller.prototype,{noScroll:false,initialize:function(id,submitFunction,currentPage,imageSrc){this.id=id;
this.submitFunction=submitFunction;
this.tabs=$(id+":tabs");
this.tabs.scrollTop=0;
this.up=$(id+":up");
this.down=$(id+":down");
this.inner=$(id+":inner");
this["rich:destructor"]="destroy";
this.srcPrefix=PNGFIX.getImagePrefix(imageSrc);
this.items=this.tabs.getElementsByTagName("div");
this.currentPage=currentPage;
currentPage--;
this.initScrolling();
PNGFIX.pngfixElements(this.inner);
if(currentPage>=0&&this.items&&this.items.length>currentPage){this.currentItem=$(this.items[currentPage])
}$(this.id).component=this
},destroy:function(){$(this.id).component=null;
this.currentItem=null;
delete this.scroller;
this.items=null;
this.inner=null;
this.down.stopObserving();
this.down=null;
this.up.stopObserving();
this.up=null;
this.tabs=null
},onscroll:function(event){this.callbackParams={};
this.callbackParams.event=A4J.AJAX.CloneObject(event,false);
this.callbackParams.page=event.memo.page;
this.scroller.activateTargetItem(this._onscroll.bind(this),event.memo.page)
},_onscroll:function(){if(!this.callbackParams){return false
}var event=this.callbackParams.event;
var target=event.target;
if(!this.isCurrentPage(target)){this.submitFunction(event);
this.selectPage(this.currentItem,false);
this.selectPage(target,true)
}},selectPage:function(env,current){if(current){this.changeClassName(env,"tabInActive","tabActive");
this.currentItem=env
}else{this.changeClassName(env,"tabActive","tabInActive")
}if(isIE()){PNGFIX.pngChangeFilter(this.id,env,(current)?"tab-active.png":"tab-inactive.png","20px",this.srcPrefix)
}},isCurrentPage:function(env){return $(env).hasClassName("tabActive")
},ontop:function(notIsOnTop){if(this.noScroll){return 
}if(notIsOnTop){this.changeClassName(this.up,"arrowTopInActive","arrowTopActive")
}else{this.changeClassName(this.up,"arrowTopActive","arrowTopInActive")
}if(isIE()){PNGFIX.pngChangeFilter(this.id,this.up,(notIsOnTop)?"arrow-top-active.png":"arrow-top-inactive.png","16px",this.srcPrefix)
}},onbottom:function(notIsOnBottom){if(this.noScroll){return 
}if(notIsOnBottom){this.changeClassName(this.down,"arrowBottomInActive","arrowBottomActive")
}else{this.changeClassName(this.down,"arrowBottomActive","arrowBottomInActive")
}if(isIE()){PNGFIX.pngChangeFilter(this.id,this.down,(notIsOnBottom)?"arrow-bottom-active.png":"arrow-bottom-inactive.png","16px",this.srcPrefix)
}},changeClassName:function(env,old,newClass){if(env.hasClassName(old)){env.removeClassName(old)
}env.addClassName(newClass)
},initScrolling:function(){this.scroller=new vScroller(this.id,this.tabs,this.items,{"ontop":function(notIsOnTop){this.ontop(notIsOnTop)
}.bind(this),"onbottom":function(notIsOnBottom){this.onbottom(notIsOnBottom)
}.bind(this)});
if(this.scroller.isScrollNeed()){this.noScroll=false;
this.scroller.bindControl(this.down,vScroller.CONTROL.FORWARD);
this.scroller.bindControl(this.up,vScroller.CONTROL.BACK);
this.inner.className="verticalTabs"
}else{this.noScroll=true;
this.scroller.unbindControls(this.down);
this.scroller.unbindControls(this.up);
this.inner.className="verticalTabsNoScroll";
if(isIE()){this.down.style.backgroundImage="";
this.down.style.filter=undefined;
PNGFIX._pngfix(this.down)
}}window.setTimeout(function(){this.scroller.activateTargetItem(null,this.currentPage)
}.bind(this),300)
}})

vScroller=Class.create();
vScroller.CONTROL={};
vScroller.CONTROL.FORWARD="1";
vScroller.CONTROL.BACK="2";
vScroller.SCROLLING_STEP=10;
vScroller.SCROLLING_DELAY=200;
vScroller.SCROLLING_SPEED=50;
vScroller.INC_VALUE=9;
Object.extend(vScroller.prototype,{scrollingStep:vScroller.SCROLLING_STEP,scrollMutex:false,initialize:function(id,target,items,handlers){this.id=id;
this.target=target;
this.items=items;
this.handlers=handlers;
this["rich:destructor"]="destroy";
this.initialized=false
},destroy:function(){this.target=null;
this.items=null;
this.handlers=null
},init:function(){this.targetHeight=this.target.getHeight();
if(this.targetHeight==0){this.targetHeight=Richfaces.getComputedStyleSize(this.target,this.isAllIE()?"marginTop":"margin-top");
this.targetHeight+=vScroller.INC_VALUE
}this.calculateScrollHeight();
this.calculateScrollPosition();
this.initialized=true
},isAllIE:function(){return/MSIE (5\.5|6|7).+Win/.test(navigator.userAgent)
},isScrollNeed:function(){this.targetHeight=this.target.getHeight();
if(this.targetHeight==0){this.targetHeight=Richfaces.getComputedStyleSize(this.target,this.isAllIE()?"marginTop":"margin-top");
this.targetHeight+=vScroller.INC_VALUE
}this.calculateScrollHeight();
this.calculateScrollPosition();
return(this.targetHeight<this.scrollHeight)
},calculateScrollHeight:function(){var w=0;
for(var i=0;
i<this.items.length;
i++){if(!this.items[i]){continue
}var item=$(this.items[i]);
item.start=w;
w+=item.offsetHeight;
w+=Richfaces.getComputedStyleSize(item,this.isAllIE()?"marginTop":"margin-top");
w+=Richfaces.getComputedStyleSize(item,this.isAllIE()?"marginBottom":"margin-bottom");
item.end=w
}this.scrollHeight=w
},calculateScrollPosition:function(){var l=this.target.scrollTop;
var r=l+this.targetHeight;
this.top=0;
for(var i=0;
i<this.items.length;
i++){if(!this.items[i]){continue
}if(this.items[i].start<=l){this.top=i
}if(this.items[i].start<=r){this.bottom=i
}}if(l==0&&this.handlers["ontop"]){this.handlers["ontop"](false)
}else{if(this.handlers["ontop"]){this.handlers["ontop"](true)
}}if(Richfaces.browser.isFF3){r+=3
}if(r==this.scrollHeight&&this.handlers["onbottom"]){this.handlers["onbottom"](false)
}else{if(this.handlers["onbottom"]){this.handlers["onbottom"](true)
}}},forward:function(){var l=this.target.scrollTop;
var r=l+this.targetHeight;
if(r<this.scrollHeight){var item=this.items[this.bottom];
var end;
if(item.end>r){end=item.end-this.targetHeight
}else{if(this.bottom+1<this.items.length){end=this.items[this.bottom+1].end-this.targetHeight
}}this._scroll(end)
}},back:function(){var l=this.target.scrollTop;
var r=l+this.targetHeight;
if(l>0){var item=this.items[this.top];
var start;
if(item.start<l){start=item.start
}else{if(this.top-1>=0){start=this.items[this.top-1].start
}}this._scroll(start)
}},_scroll:function(p){if(this.scrollMutex||Object.isUndefined(p)){return 
}this.scrollMutex=true;
if(this.scrollInterval){return 
}if(p>this.target.scrollTop){this.scrollInterval=window.setInterval(function(){this._scrollF(p)
}.bind(this),vScroller.SCROLLING_SPEED)
}else{this.scrollInterval=window.setInterval(function(){this._scrollB(p)
}.bind(this),vScroller.SCROLLING_SPEED)
}},_scrollF:function(end){var i=this.target.scrollTop;
i=i+this.scrollingStep;
if(i>=end){this.target.scrollTop=end;
this.calculateScrollPosition();
this._finish();
return 
}else{this.target.scrollTop=i
}},_scrollB:function(start){var i=this.target.scrollTop;
i=i-this.scrollingStep;
if(i<=start){this.target.scrollTop=start;
this.calculateScrollPosition();
this._finish();
return 
}else{this.target.scrollTop=i
}},_finish:function(){if(this.scrollInterval){window.clearInterval(this.scrollInterval)
}this.scrollInterval=null;
this.scrollMutex=false;
this.scrollingStep=vScroller.SCROLLING_STEP;
if(this.callback){this.callback()
}},_doScrolling:function(forward){var scrollTop=this.target.scrollTop;
if(forward){if(scrollTop+this.scrollingStep<this.scrollHeight-this.targetHeight){this.target.scrollTop=scrollTop+this.scrollingStep
}else{this.target.scrollTop=this.scrollHeight-this.targetHeight
}}else{if(scrollTop-this.scrollingStep>0){this.target.scrollTop=scrollTop-this.scrollingStep
}else{this.target.scrollTop=0
}}},fireScrolling:function(forward){if(this.intervalId){return 
}if(forward){this.intervalId=window.setInterval(function(){this._doScrolling(true)
}.bind(this),vScroller.SCROLLING_SPEED)
}else{this.intervalId=window.setInterval(function(){this._doScrolling(false)
}.bind(this),vScroller.SCROLLING_SPEED)
}},startScrolling:function(e,forward){if(!this.initialized){this.init()
}if(this.scrollMutex||this.timeoutId){return 
}this.scrollMutex=true;
this.timeoutId=window.setTimeout(function(){this.fireScrolling(forward)
}.bind(this),vScroller.SCROLLING_DELAY)
},endScrolling:function(forward){window.clearTimeout(this.timeoutId);
window.clearInterval(this.intervalId);
this.intervalId=null;
this.timeoutId=null;
this.calculateScrollPosition();
this.scrollMutex=false
},bindControl:function(env,controlType){this.unbindControls(env,controlType);
var isForward=(vScroller.CONTROL.FORWARD==controlType);
var control=isForward?this.forward:this.back;
Event.observe(env,"click",control.bindAsEventListener(this));
Event.observe(env,"mousedown",function(e){this.startScrolling(e,isForward)
}.bind(this));
Event.observe(env,"mouseup",function(){this.endScrolling(isForward)
}.bind(this));
Event.observe(env,"mouseout",function(){this.endScrolling(isForward)
}.bind(this));
Event.observe(env,"drag",function(){return false
})
},unbindControls:function(env,controlType){Event.stopObserving(env,"click");
Event.stopObserving(env,"mousedown");
Event.stopObserving(env,"mouseup");
Event.stopObserving(env,"mouseout");
Event.stopObserving(env,"drag")
},activateTargetItem:function(callback,n){if(!this.initialized){this.init()
}this.callback=callback;
n--;
var start=this.items[n].start;
var end=this.items[n].end;
var l=this.target.scrollTop;
var r=l+this.targetHeight;
if(start<l){this.scrollingStep=(l-start)/5>30?(l-start)/5:30;
this._scroll(start)
}else{if(end>r){this.scrollingStep=(end-r)/5>30?(end-r)/5:30;
this._scroll(end-this.targetHeight)
}else{if(this.callback){this.callback();
this.callback=null
}}}}})

