ExtendedDataTable.DataTable.initSimpleDropZone = function (table, context, colId, dropZonePosition) {
		var clientId = table.id;
		colId = colId.substring(colId.lastIndexOf(':') + 1);
		var formId = context.formId;
		var containerId = context.containerId;
		var actionUrl = context.actionUrl;
		var dZone = new DnD.ExtSimpleDropZone(clientId + '_hdrop_' + colId + dropZonePosition,{'acceptedTypes':'COLUMN_ORDER_' + clientId,'dndParams':'{}'});
   		dZone.drop = function (event, drag) {
      			var dragParams = drag.getParameters();
				var source = dragParams['dragSourceId'];
				var cId = clientId +  ':' + colId;
  				if (source != (cId + ":dnd_drag_script")){
					var options = {};
					options.parameters = {};
					options.parameters[cId + ':dnd_drop_script_' + dropZonePosition] = cId + ':dnd_drop_script_' + dropZonePosition;
					options.actionUrl = actionUrl;
					options.similarityGroupingId = cId;
					options.parameters['dropTargetId'] = cId + ':dnd_drop_script_' + dropZonePosition;
						
					Object.extend(options.parameters, dragParams);
					table.preSendAjaxRequest();
					var dzOptions = this.getDropzoneOptions(); 
					if (dzOptions.ondrop) { 
						if (!dzOptions.ondrop.call(this, event))
		 					return; 
					};
       				A4J.AJAX.Submit(containerId,formId,event,options);
        		}
        	
        	};
        	
        return dZone;
};

ExtendedDataTable.DataTable.initDraggable = function (table, colId, label) {
	var clientId = table.id;
	var cId = colId;
	colId = colId.substring(colId.lastIndexOf(':') + 1);
	
	var options = {};
	options.dragIndicator = clientId + ':dataTable_indicator';
	options.dragType = 'COLUMN_ORDER_' + clientId;
	options.dndParams = '{\"label\":\"'+label+'\"}';
	options.parameters = {};
	options.parameters.dragSourceId = cId + ':dnd_drag_script';
	options.parameters[cId + ':dnd_drag_script'] = cId + ':dnd_drag_script'; 
	
	var draggable = new DnD.ExtSimpleDraggable(cId, options);
	return draggable;
};

ExtendedDataTable.DataTable.header = Class.create(ClientUI.common.box.Box, {
	// constructor
	initialize: function($super, elementId, extDt) {
		this.extDt = extDt;
		this.extDtId = this.extDt.id;
		$super(elementId,extDt,true);
		
		//register events
        this.eventSepClick = this.OnSepClick.bindAsEventListener(this);
        this.eventSepMouseDown = this.OnSepMouseDown.bindAsEventListener(this);
		this.eventSepMouseMove = this.OnSepMouseMove.bindAsEventListener(this);
        this.eventSepMouseUp = this.OnSepMouseUp.bindAsEventListener(this);
        
        if (this.extDt.options.isSingleCanHideTrueAvailable) {
        	this.eventHeaderCellMouseOver = this.OnHeaderCellMouseOver.bindAsEventListener(this);
			this.eventHeaderCellMouseOut = this.OnHeaderCellMouseOut.bindAsEventListener(this);
		
			var showMenuFct = this.extDt.options.showMenuFunction;
			if (showMenuFct) {
				this.showMenuFct = showMenuFct;
				this.menuImageMouseDown = this.OnMenuImageMouseDown.bindAsEventListener(this);
			};
		};
		
		this.createControl(elementId);
	},
	
	OnHeaderCellMouseOver: function(event) {
	   	  var el = this.extDt._findParentElement(event, "th");
	   	  var menuDiv = document.getElementById(el.id+"header:menuDiv");
	   	  menuDiv.className = "extdt-menu-div-on";
	},

    getCaption: function() {
       return this.caption;
    },
	
	getCaptionHeight: function() {
	   var caption = this.getCaption();
	   if (caption) {
	       return caption.getHeight();
	   }else{
	       return 0;
	   }
	},
	
    OnHeaderCellMouseOut: function(event) {
       var el = this.extDt._findParentElement(event, "th");
       var menuDiv = document.getElementById(el.id+"header:menuDiv");
       menuDiv.className = "extdt-menu-div-out";
    },
    
    removeListeners: function() {
        var columnCells = this.getColumnCells();
        var l = columnCells.length;
        for (var i = 0; i< l-1; i++) {
            var headerChild = columnCells[i];
            var headerChildChildren = headerChild.childNodes;
            var sepSpan = headerChildChildren[1];
            var menuImage = headerChildChildren[4];
            
            if(document.getElementById(menuImage)) {
            	Utils.DOM.Event.removeListeners(menuImage);
            }
            
            Utils.DOM.Event.removeListeners(sepSpan);
        };
    },
	
	getVisibleWidth: function() {
		var sum = 0;
		var l = this.getColumnsNumber();
		for(var i = 0; i < l-1; i++){
			sum += this.getColumnWidth(i);
		}
		return sum;
	},		
	createControl: function(elementId) {
		if(!elementId) {
			errMsg = "Invalid id specified for ExtendedDataTableGridHeader.";
			throw(errMsg);
		}

		if(!this.parseTemplate(elementId)) {
			errMsg = "TODO insert commnet about header structure here";
			throw(errMsg);
		}
	},
	
	
	
	parseTemplate: function(template) {
		if(!template) {
			return false;
		}
		
		var element = this.getElement(); 
		this.headerRow = new ClientUI.common.box.Box(this.extDtId +":headerRow",element,true);
		this.filterRow = new ClientUI.common.box.Box(this.extDtId +":filterRow",element,true);
		this.caption = new ClientUI.common.box.Box(this.extDtId +":caption",element,true);

		var tableElement = jQuery(this.extDt.table.element); 
		this.cols = tableElement.find('thead col').get();
        this.columnsNumber = this.cols.length;
		this.columnCells = tableElement.find('thead table thead tr.extdt-subheader th').get();
		return true;
	},
	getColumns: function() {
		return this.cols;
	},
    getColumn: function(index) {
        if (this.isValidColumnNumber(index)) {
            return this.cols[index];
        }else{
            return null;
        }
    },
	getColumnCells: function() {
		return this.columnCells;
	},
	getColumnsNumber: function() {
		return this.columnsNumber;
	},

    setColumnWidth: function (columnIndex, newWidth) {
        if (columnIndex >= this.getColumnsNumber()) {
            return false;
        }

        if ( Richfaces.browser.isIE && !newWidth) {
            newWidth = null
        }
        this.getColumns()[columnIndex].width = newWidth;
    },
	
	isValidColumnNumber: function(columnNumber) {
        return ((columnNumber < this.columnsNumber) && (columnNumber >=0));
	},
	
	getColumnWidth: function(columnNumber) {
		if (this.isValidColumnNumber(columnNumber)) {
			var col = this.getColumnCells()[columnNumber];
			return col.offsetWidth;
		}else{
			return null;
		}
	},
	
	isColumnWidthPercentage: function(columnNumber) {
        if (this.isValidColumnNumber(columnNumber)) {
            var col = this.getColumns()[columnNumber];
            return /\d+%/.test(col.width);
        }else{
            return null;
        }
	}, 
	
	getHeightWithoutFacets: function() {
		return this.headerRow.getHeight() + this.filterRow.getHeight();
	},
	
	getTotalHeight: function() {
        var ret = this.headerRow.getHeight() + this.filterRow.getHeight();
        if (this.caption) {
            ret += this.caption.getHeight();
        }
        return ret;
	},
	
	OnMenuImageMouseDown: function(event) {
			var menuId = this.extDtId + ":menu";
			var menu = $(menuId);
			if(menu && menu.component){
				menu.component.hide(event);
				menu.component.show(event);
			}
			
		Event.stop(event);
	},
	
	initDragAndDropSupport: function (table, context, colId, label) {
		ExtendedDataTable.DataTable.initDraggable(table, colId, label);	
		ExtendedDataTable.DataTable.initSimpleDropZone(table, context, colId, 'left');
		ExtendedDataTable.DataTable.initSimpleDropZone(table, context, colId, 'right');
	},
		
	adjustSeparators: function(table, context) {
		var columnCells = this.getColumnCells();
		var l = columnCells.length;
		var headerRowHeight = this.headerRow.getHeight();
	    var headerRowY = this.headerRow.getY();

	    for (var i=0; i<l-1; i++) {
			var headerChild = $(columnCells[i]);
			var headerNextChild = $(columnCells[i+1]);
			var headerChildChildren = headerChild.childNodes;
			var labelDiv = $(headerChildChildren[0]);
			var sepSpan = $(headerChildChildren[1]);
			sepSpan.columnIndex = i;
			var sd = sepSpan.getWidth()/2 + 1;
			var dropSpanLeft = $(headerChildChildren[2]);
			var dropSpanRight = $(headerChildChildren[3]);
			var menuImage = $(headerChildChildren[4]);
			var label = labelDiv.getAttribute('label');
			
			if (table.dragContext) {
				this.initDragAndDropSupport(table, table.dragContext, headerChild.id, label);
			}
			
			var newOffsetLeft = headerNextChild.offsetLeft;
			var currentOffsetLeft = headerChild.offsetLeft;
			var currentWidth = headerChild.offsetWidth;

			var spanLeft = newOffsetLeft - sd;
			var rowHeight = this.extDt.headerRowHeight;
			
			sepSpan.setStyle({height: rowHeight+'px',
			                     top: headerRowY+'px',
			                    left: currentOffsetLeft+currentWidth+ (-2) + 'px'});

			if(menuImage) {
				menuImage.setStyle({ top: headerRowY + 'px',
	                left: (currentOffsetLeft+currentWidth-menuImage.offsetWidth - 1)+'px'});
	    	}

			var w = headerChild.getWidth();

			dropSpanLeft.setStyle({  top: headerRowY+'px',
			                         left: (currentOffsetLeft) +'px',
			                       height: rowHeight+'px',
			                        width: (w/2)+'px'});

			dropSpanRight.setStyle({    top: headerRowY+'px',
			                           left: (currentOffsetLeft + w/2) +'px',
			                         height: rowHeight+'px',
			                          width: (w/2)+'px'});
	
		}
		this.lastColWidth = this.extDt.getColumnWidth(this.getColumnsNumber()-1);
		if (Richfaces.browser.isIE){
			this.lastColWidth -= 15;
		}
		
		var div1= document.createElement("span");
		if(ClientUILib.isSafari) {
			div1.innerHTML="safari-must-have-something-inserted-to-redraw-table";
		}
		headerChild.appendChild(div1);
		headerChild.removeChild(div1);
		this._redrawTable(this.extDt.tableB);
	},
	
	OnSepClick: function(event) {
		Event.stop(event);
		this.dragColumnInfo.mouseDown = false;
	},
	
	OnSepMouseDown: function(event) {
		Event.stop(event);
		this.dragColumnInfo = {
			srcElement: Event.element(event),
			dragStarted: false,
			mouseDown: true,
			startX: Event.pointerX(event),
			originalX: 0
		};
		var srcElement = this.dragColumnInfo.srcElement;
		this.maxDelta = this.getColumnWidth(this.getColumnsNumber()-1);
		this.maxDelta -= this.extDt.getScrollbarWidth();
        if (ClientUILib.isOpera) {
            this.maxDelta -= 1;
        };
        if (Richfaces.browser.isIE) {
        	this.maxDelta -= 3;
        }
		this.minDelta = this.minColumnWidth - this.getColumnWidth(srcElement.columnIndex);
		
		if (this.extDt.options.horizontalScrolling) {
			this.minDelta = -this.getColumnWidth(srcElement.columnIndex) + 20;;
			this.maxDelta = 9999;
		}
		
		Event.observe(document, 'mousemove', this.eventSepMouseMove, true);
		Event.observe(document, 'mouseup', this.eventSepMouseUp, true);
		
	},
	
	_showSplitter: function(index) {
		if(!this.columnSplitter) {
			this._createSplitter();
		}

		var el = jQuery(this.dragColumnInfo.srcElement.parentNode);
		var pos = el.offsetParent().position().left + el.position().left + el.width();
		pos += 6; //6 stands for width of the separatorSpan
		this.dragColumnInfo.originalX = pos;
		this.columnSplitter.show();
		this.columnSplitter.setHeight(this.extDt.scrollingDiv.getHeight()+
			this.getHeightWithoutFacets()
		);
		this.columnSplitter.moveTo(pos, this.headerRow.getY());
	},
	_hideSplitter: function() {
		if(this.columnSplitter) {
			this.columnSplitter.hide();
		}
	},
	_createSplitter: function() {
		this.columnSplitter = new ClientUI.common.box.Box(this.extDtId +":cs", this.extDt.grid);
		this.columnSplitter.makeAbsolute();
		this.columnSplitter.setWidth(this.minColumnWidth);
	},	
	
	_getColumnIndex: function (el) {
		return jQuery(this.cols).index(el);
	},
	
	OnSepMouseUp: function(event) {
	    this._hideSplitter();
		Event.stop(event);
		Event.stopObserving(document, 'mousemove', this.eventSepMouseMove);
		Event.stopObserving(document, 'mouseup', this.eventSepMouseUp);
		if(this.dragColumnInfo && this.dragColumnInfo.dragStarted) {

			this.dragColumnInfo.dragStarted = false;
			this.dragColumnInfo.mouseDown = false;

			var delta = Event.pointerX(event) - 
				this.dragColumnInfo.startX;
			if (delta < this.minDelta) {
				delta = this.minDelta;
			}
			if (delta > this.maxDelta) {
				delta = this.maxDelta;
			}
			var columnIndex = this.dragColumnInfo.srcElement.columnIndex;
			var newWidth = this.getColumnWidth(columnIndex) + delta;
			var colsNumber = this.getColumnsNumber();
			
			this.extDt.setColumnWidth(columnIndex, newWidth);
			this.setColumnWidth(columnIndex,newWidth);
			this.extDt.updateLayout();
			if (this.extDt.onColumnResize){
				//set properly value to this.columnWidths
				this.extDt.columnWidths = "";
				for (i=0; i<this.columnsNumber; i++){
					var w = this.getColumnWidth(i);
					this.extDt.columnWidths += "" + w + ";";
					this.extDt.onColumnResizeHandler(i, w);
				}//for
				this.extDt.onColumnResize(event, this.extDt.columnWidths);
			}
		}
	},
	
	OnSepMouseMove: function(event) {
		if(this.dragColumnInfo && this.dragColumnInfo.mouseDown) {
			if(!this.dragColumnInfo.dragStarted && this.extDt.options.allowColumnResize) {
				this.dragColumnInfo.dragStarted = true;
				this._showSplitter(this.dragColumnInfo.srcElement.columnIndex);
			}
			var delta = Event.pointerX(event) - 
				this.dragColumnInfo.startX
			if (delta < this.minDelta) {
				delta = this.minDelta;
			}
			if (delta > this.maxDelta) {
				delta = this.maxDelta;
			}
			var x = this.dragColumnInfo.originalX + delta;
			var finalX = x - this.minColumnWidth - 6 //6 stands for sep span width;
			this.columnSplitter.moveToX(finalX); 				
			Event.stop(event);
		}
	},
    _redrawTable: function(table) {
        if(ClientUILib.isOpera) {
    	    table.hide(); //this is for opera < 9.5
    	}    
        
        var tr = table.insertRow(0);
        
        if(ClientUILib.isSafari) {
	       	var coLength = this.getColumns().length;
	       	for(var i = 0 ; i < coLength ; i ++){
	       		var td = tr.insertCell(i);
	      	  	td.setAttribute("colspan", 5);
	        	td.innerHTML = "safari-must-have-something-inserted-to-redraw-table";
	       	}
        }
        
        table.deleteRow(tr.rowIndex);
        table.show();
	}			
});