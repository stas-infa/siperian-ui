SipTooltip= Class.create({
	initialize:function(tooltipId){
	this["rich:destructor"] = "destroy";
		if($(tooltipId)){
			this.tooltipId = tooltipId;
		}
	},
	destructor:function(){
		this.tooltipId = null;
	},
	hide:function(event){
		if($(this.tooltipId)){
			$(this.tooltipId).style.display='none';
		}
	},
	showExt:function(tooltipId,oEvent,text){
		if(tooltipId){
			var tooltipInnerDiv = $(tooltipId+'content');
			
			if(tooltipInnerDiv && text){
				tooltipInnerDiv.innerHTML = text;
				var target = oEvent.srcElement ? oEvent.srcElement : oEvent.target;
	
				if(target){
					
					Event.observe(target, 'mouseout', this.hide.bindAsEventListener(this));
				}	
				$(tooltipId).component.show(oEvent);		
			}
		}
	},
	
	show:function(oEvent,text){
		this.showExt(this.tooltipId,oEvent,text);
	}
	
});