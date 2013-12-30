Richfaces.setupScrollEventHandlers = function(element, handler) {
	
	var elements = []
	
	element = element.parentNode;
	while (element && element!=window.document.body)
	{
		if (element.offsetWidth!=element.scrollWidth || element.offsetHeight!=element.scrollHeight)
		{
			elements.push(element);
			Event.observe(element, "scroll", handler, false);
		}
		element = element.parentNode;
	}
	elements.push(window);
	Event.observe(window, "scroll", handler, false);
	return elements;
};
	function oncontextMenuStopHandler(eventObject){
		//alert('oncontextmenu');
		if(eventObject) {
			if (eventObject.stopPropagation) eventObject.stopPropagation();
			if (eventObject.preventDefault) eventObject.preventDefault();
			if (eventObject.preventCapture) eventObject.preventCapture();
	   		if (eventObject.preventBubble) eventObject.preventBubble();
		}
		return false;
	}

	if(document.attachEvent){
		document.attachEvent('oncontextmenu',oncontextMenuStopHandler);
		window.attachEvent('onscroll',SipSingleCalendar.singleCalendarOnScrollHandler);
	}else if(document.addEventListener){
		document.addEventListener('contextmenu',oncontextMenuStopHandler,false);
		document.addEventListener('scroll',SipSingleCalendar.singleCalendarOnScrollHandler,false);
	}

