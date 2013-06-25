A4J.AJAX.PrepareQuery = function(containerId, formId, domEvt, options) {
	// Process listeners.
	for(var li = 0; li < A4J.AJAX._listeners.length; li++){
  	  	var listener = A4J.AJAX._listeners[li];
   	  	if(listener.onbeforeajax){
   	  		listener.onbeforeajax(formId,domEvt,options);
   	  	}
	}
    // First - run onsubmit event for client-side validation.
	LOG.debug("Query preparation for form '" + formId + "' requested");
//	var	form = A4J.AJAX.locateForm(event);
	var form = window.document.getElementById(formId);
	if( (!form || form.nodeName.toUpperCase() != "FORM") && domEvt ) {
		var srcElement = domEvt.target||domEvt.srcElement||null;
		if(srcElement){
			form = A4J.AJAX.locateForm(srcElement);
		};
	};
	// TODO - test for null of form object
    if(!options.submitByForm && form && form.onsubmit) {
		LOG.debug("Form have onsubmit function, call it" );
    	if( form.onsubmit() == false ){
    		return false;
    	};
    };
    var tosend = new A4J.Query(containerId, form);
    if (options.parameters.ajaxSingle && options.parameters['_sip_limit_request_flag']
   	 && !options.parameters['_sip_submit_regionsIds']) {
    	tosend.appendParameter(formId, formId);
    	
    	var v = A4J.AJAX.LocaleViewState(form); 
    	if (v) {
    		tosend.appendParameter(v.name, v.value);
    	}
    }else if(options.parameters['_sip_limit_request_flag']&&  
    		options.parameters['_sip_submit_regionsIds']!=void(0)){
    	//alert("custom method");
    	tosend.appendFormControls2(options.single,options.control,options.parameters['_sip_submit_regionsIds']);
    }else {
    	//alert("standart");
    	tosend.appendFormControls(options.single, options.control);
    }
	
    if(options.parameters){
    	tosend.appendParameters(options.parameters);
    }; 
    if(options.actionUrl){
    	tosend.setActionUrl(options.actionUrl);
    };
    

    return tosend;
};

A4J.Query.prototype.appendFormControls = function(hiddenOnly, actionControl){
	 	try {
	 	 var elems = this._form.elements;
	 	 if(elems){
		 var k = 0;
		   for ( k=0;k<elems.length;k++ ) {
		          var element=elems[k];
		          
		          //skip actionControl, we're going to add it later
		          if (element == actionControl) {
		        	  continue;
		          }
				  try {  
				    if(  !hiddenOnly || element.type == "hidden") {
		          		this.appendControl(element,false) ;
		            }
		   		  } catch( ee ) {
			        	 LOG.error("exception in building query ( append form control ) " + ee );
			      }
		    }
		  }
	 	} catch(e) {
	 		LOG.warn("Error with append form controls to query "+e)
	 	}
	 	
	 	if (actionControl) {
      		this.appendControl(actionControl, true);
	 	}
	};
A4J.Query.prototype.reset=function(){
	if(this.elements){
		this.elements = null;
		this.arrayCounter = 0;
	}
}

A4J.Query.prototype.fetchQueryElements = function(region){
	if(!this.elements){
		this.elements = new Array;
		
		this.arrayCounter = 0 ;
	
	}
	
	
	if(region!=void(0) && region!=null && region!="undefined"){

		var tmpElements = jQuery("input[id*='"+selector+"']");
		//alert("tmpElements="+tmpElements);
		if(tmpElements){
			for(var i = 0 ; i < tmpElements.length; i++){
				//alert("added element to the this.elements"+tmpElements[i]);
				this.elements[this.arrayCounter++] = tmpElements[i];
			}
		}
	}

};
A4J.Query.prototype.isInRegion = function(element,selector){
	if(element.id == "javax.faces.ViewState" || element.id == "" || element.id.indexOf(selector)!=-1){
		return true;
	}
	return false;
	
};
A4J.Query.prototype.appendParameters = function(parameters){
		for( k in parameters ){
 	 	  if(k != '_sip_limit_request_flag' && typeof Object.prototype[k] == 'undefined'){
 	 	    LOG.debug( "parameter " + k  + " with value "+parameters[k]);
		  	this.appendParameter(k,parameters[k]);
		  }
		}	
};
A4J.Query.prototype.appendFormControls2 = function(hiddenOnly, actionControl,ids){

	try {
	 	 
	 	 var idsArray = ids.split(",");
	 	 if(idsArray && idsArray.length > 0){
	 	 	for(var i = 0 ; i < idsArray.length ;i++){
	 	 		if(!$(idsArray[i])){
	 	 			continue;
	 	 		}
	 	 		var inputs = $(idsArray[i]).getElementsByTagName("input");
	 	 			if(inputs && inputs.length > 0 ){
	 	 				for( var z = 0 ; z < inputs.length; z++){
	 	 					var element = inputs[z];
	 	 					 if(  !hiddenOnly || element.type == "hidden") {
	 	 					 	 //alert("element with id="+element.id+" was added");
	 	 		 				 this.appendControl(element,false) ;
	 	 			 		}
	 	 				}
	 	 			}
	 	 	}
	 	 	if($("javax.faces.ViewState")){
	 	 		this.appendControl($("javax.faces.ViewState"),false);
	 	 	}
	 		if (actionControl) {
      			this.appendControl(actionControl, true);
	 		}
	 	 }else{
	 	 	this.appendFormControls(hiddenOnly,actionControl);
	 	 	
	 	 }
	}catch(e) {
	 		LOG.warn("Error with append form controls to query "+e);
	 }

	
};
A4J.AJAX.LocaleViewState = function (form) {
	var v = form.lastChild;
	if (v.name != 'javax.faces.ViewState') {
		v = v.previousSibling;
	}
	return v.name == 'javax.faces.ViewState' ? v : null;
};

A4J.AJAX.HEAD_CACHE = null;		

		A4J.AJAX.HEAD_CACHE_ITEM = function (href, src, mappedRole, elementRole) {
			this[href] = src;
			if (elementRole) {
				this[mappedRole] = elementRole;
			}
		};
		
		A4J.AJAX.XMLHttpRequest.prototype._initHeadCache = function (tag,href,mappedRole) {
			 A4J.AJAX.HEAD_CACHE = A4J.AJAX.HEAD_CACHE || {};	
			 A4J.AJAX.HEAD_CACHE[tag] = {};
			 A4J.AJAX.HEAD_CACHE[tag]['roleAnchors'] = {};
			 var oldscripts = document.getElementsByTagName(tag);
			 for (var j = 0; j < oldscripts.length; j++) {
			 		var oldscript = oldscripts[j];
					var oldRole = oldscript[mappedRole];
					var src = oldscript.getAttribute(href);
					src = this._noSessionHref(src);
					if (oldRole) {
						A4J.AJAX.HEAD_CACHE[tag]['roleAnchors'][oldRole] = oldscript;
					}
					A4J.AJAX.HEAD_CACHE[src] = new A4J.AJAX.HEAD_CACHE_ITEM(href, src, mappedRole, oldRole);
			 }
			 return A4J.AJAX.HEAD_CACHE;
		};
		
		A4J.AJAX.XMLHttpRequest.prototype._appendNewElements = function(tag,href,role,roles,attributes,mappings,callback){
		      var newscripts = this.getElementsByTagName(tag);
		      var mappedRole = (mappings && mappings[role]) || role;
		      var oldscripts = (A4J.AJAX.HEAD_CACHE && A4J.AJAX.HEAD_CACHE[tag]) ? A4J.AJAX.HEAD_CACHE : this._initHeadCache(tag,href,mappedRole);
	       	  //var oldscripts = document.getElementsByTagName(tag);
	       	  var elements = [];
        	  
//        	  var roleAnchors = {};
//			  if (roles) {
//	        	  var i = 0;
//	        	  
//   	  
//	        	  for(var j in oldscripts){
//	        		  if (!oldscripts[j] || !oldscripts[tag] || !oldscripts[j].src ) {
//	        			  continue;
//	        		  }
//					  var oldscript = oldscripts[j];
//					  var scriptRole = oldscript[mappedRole];
//	        	  
//					  for ( ; i < roles.length && roles[i] != scriptRole; i++) {
//						  roleAnchors[roles[i]] = oldscript;
//					  }
//					  
//					  if (i == roles.length) {
//						  break;
//					  }
//	        	  }
//			  }
        	  
        	  for(var i=0 ; i<newscripts.length;i++){
        	  	 var element = newscripts[i];
        	  	 var src = element.getAttribute(href);
        	  	 var elementRole;
        	  	 
        	  	 if (roles) {
        	  		 elementRole = element.getAttribute(role);
        	  	 }
        	  	 
        	  	 if(src){
        	  	 	var exist = false;
        	  	 	LOG.debug("<"+tag+"> in response with src="+src);
	  	  				exist = oldscripts[this._noSessionHref(src)];
       	  				if (role && exist) {
       	  						var oldRole = exist[mappedRole];
       	  						if ((!elementRole ^ !oldRole) || (elementRole && oldRole && elementRole != oldRole)) {
               	  					LOG.warn("Roles are different");
       	  						}
       	  				}
        	  		 if(!exist){
        	  		 	// var script = window.document.importNode(element,true); //
        	  		 	var head = document.getElementsByTagName("head")[0]||document.documentElement;
        	  		 	var script = document.createElement(tag);
        	  		 	script.setAttribute(href,src);
        	  		 	for(var j = 0 ; j < attributes.length; j++){
        	  		 		this._copyAttribute(element,script,attributes[j]);
        	  		 	}
        	  		 	
        	  		 	if (elementRole) {
        	  		 		script[mappedRole] = elementRole;
        	  		 	}

        	  		 	LOG.debug("append element to document");
        	  		 	
        	  		 	for ( var j = 0; j < A4J.AJAX._headTransformers.length; j++) {
        	  		 		A4J.AJAX._headTransformers[j](script);
						}
        	  		 	
        	  		 	var anchor = A4J.AJAX.HEAD_CACHE[tag]['roleAnchors'][elementRole]; //roleAnchors[elementRole];
        	  		 	if (anchor && anchor.parentNode) {
            	  		 	anchor.parentNode.insertBefore(script, anchor);
        	  		 	} else {
            	  		 	head.appendChild(script);
        	  		 	}
        	  		 	
        	  		 	src = this._noSessionHref(src);
        	  		 	A4J.AJAX.HEAD_CACHE[src] = new A4J.AJAX.HEAD_CACHE_ITEM(href, src, mappedRole, elementRole);;
        	  		 	
        	  		 	if (callback) {
        	  		 		callback(element,script);
        	  		 	}
        	  		 	if (tag!="link" || script.type.toLowerCase()=="text/javascript") elements.push(script);
        	  		 }     	  	 	
        	  	 }
        	  }
		return elements;
	};
	