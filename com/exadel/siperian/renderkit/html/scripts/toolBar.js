ToolBar = Class.create();
ToolBar.prototype = {
	initialize: function(id, isDisabled, imgSrc, groupsNumbers, isHighlighted) {
		this["rich:destructor"] = "destroy";
		this.id = id;
		this.isDisabled = isDisabled;
		this.isHighlighted = isHighlighted;
		this.element = $(this.id);
		
		//TODO nick - memory leaks in IE - see another files for description
		this.element.component = this;
		this.groupsNumbers = groupsNumbers;
		this.initHandlers();
		this.srcPrefix = PNGFIX.getImagePrefix(imgSrc);
		this.pngfix();
		
		Utils.execOnLoad(
			function(){
			    if (this.isDisabled) {
			    	this.disable();
			    }else {
			    	this.disableGroups();
			    }
			}.bind(this),
			Utils.Condition.ElementPresent(id), 200);
	},
	
	destroy:function(){
	
		this.isDisabled = null;
		this.isHighlighted = null;
		if(this.element){
			this.element.stopObserving();
			if(this.element.component){
				this.element.component = null;
			}
			this.element = null;
		}
		this.srcPrefix = null;
		
	},
	pngfix: function () {
		if (!isIE()) {
			return;	
		}
		//PNGFIX.pngfixElement(this.element, true);
	},

	disable: function() {
		this.element.style.position = 'relative';
		var tr = $(this.id).getElementsBySelector('tr')[0];
		if (tr.offsetWidth == 0) {
			Event.observe(window, 'load',this.disable.bind(this));
			return;
		}

		//TODO nick - all this stuff should be in CSS file
    	glassDiv = document.createElement("div");
		glassDiv.style.width = (tr.offsetWidth - 20) + "px";
    	glassDiv.style.height = tr.offsetHeight + "px";
    	glassDiv.style.left = (tr.offsetLeft + 10) + "px";
    	glassDiv.style.top = tr.offsetTop + "px";
    	glassDiv.style.position = "absolute";
   		glassDiv.style.opacity = .4;
    	glassDiv.style.filter = "alpha(opacity=40)";
    	if(Prototype.Browser.IE) {
    		//TODO nick - dirty and unstable code involving knowledge of browser specific behavior
    		glassDiv.style.height = '26px';
    		glassDiv.style.backgroundPositionY = '-2px';
    		glassDiv.style.top = '2px';
    	}
    	
    	//TODO nick - rename bg class
    	glassDiv.className = "bg";
    	$(this.id).insert(glassDiv);
    	this.stopObservingFocus();
	},
	
	disableGroup: function (group) {
	
		var div = document.createElement("div");
		
		div.style.position = 'absolute';
		div.style.zIndex = 1;
		
		var d = document.createElement("div");
		d.style.width = group.offsetWidth + "px";
    	d.style.height = group.offsetHeight + "px";
    	if (Prototype.Browser.IE) {
    		d.style.backgroundPositionY = '-2px';
    		d.style.height = '26px';
    	}
    	d.className = 'bg siptoolbar_disgroup';
   	
    	div.appendChild(d);
    	group.insertBefore(div, group.firstChild);
	},
	
	disableGroups: function () {

		var tr = $(this.id).getElementsBySelector('tr')[0];
		if (tr.offsetWidth == 0) {
			Event.observe(window, 'load',this.disableGroups.bind(this));
			return;
		}
	
		if(this.isDisabled || !this.groupsNumbers) {
			return false;
		}
		
		var groups = $(this.id).getElementsByClassName('group');
		if (groups) {
			for (var i=0; i < groups.length; i++) {
				if (this.groupsNumbers[i]) {
					this.disableGroup($(groups[i]));
				}
			}
		}
	},

	initHandlers: function() {
		//TODO nick - what if user will insert table as item?
		var tds = $(this.id).getElementsByClassName('group');
		for(i = 0; i < tds.length; i++) {
			(function(i, me) {
			if(!me.groupsNumbers[i]) {
				tds[i].observe("mouseover", function() {
					me.active(tds[i], true);
				});
				tds[i].observe("mouseout", function() {
					me.active(tds[i], false);
				});
			}
			})(i, this);
		}
	},

	stopObservingFocus: function () {
		var tds = $(this.id).getElementsBySelector('td');
		for(i = 1; i < tds.length - 1; i++) {
			if(Element.hasClassName(tds[i], "group")) {
				tds[i].stopObserving('mouseover');
				tds[i].stopObserving('mouseout');
			}
		}
	},

	active: function(elem, actived) {
	   if ($(this.isHighlighted)) {
	   		elem.className = (actived) ? 'group active' : 'highlightedGroup';	
	   } else {
	   		elem.className = (actived) ? 'group active' : 'group';
	   }
	   	if (isIE()) {
	   		//TODO nick - externalize these values
			//PNGFIX.pngChangeFilter(this.id, elem, (actived) ? 'toolbar_bg_ac.png' : 'toolbar_bg.png', '30px', this.srcPrefix);
		}
	}
}