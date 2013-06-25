if (!window.ExtendedDataTable) window.ExtendedDataTable = {};

ExtendedDataTable.DataTable = Class.create({
	initialize : function(id, options, onlayoutupdate, dragContext, onkeydown) {
		this.id = id;
		var table = $(this.id);
		table.component = this;
		this["rich:destructor"] = "destroy";
		this.groups = [];
		this.dragContext = dragContext;
		this.onkeydownhandler = onkeydown;

		// register event handlers
		this.options = options;
		if (onlayoutupdate) {
			table.observe('rich:onlayoutupdate', onlayoutupdate);
		}
		this.selectionManager = new ExtendedDataTable.SelectionManager(options, this);
		this.forseSorting = this.options.forseSorting;
		this.scrollable = this.options.scrollable!=null ? this.options.scrollable : 'true';

		if (this.options.sortFunction) {
			this.sortFct = this.options.sortFunction;
			this.eventCellClicked = this.OnCellMouseClicked.bindAsEventListener(this);
		}
		this.onGroupToggleFct = this.options.onGroupToggleFunction;

		if (this.options.onColumnResize != null){
			this.onColumnResize = this.options.onColumnResize;
			this.columnWidths = "";
		}
		this.onColumnResizeFunctions = this.options.onColumnResizeFunctions;
		this.eventContainerResize = this.OnWindowResize.bindAsEventListener(this);
		this.eventGroupRowClicked = this.OnGroupRowMouseClicked.bindAsEventListener(this);
		Event.observe(window, "resize", this.eventContainerResize);

		this.minColumnWidth = this.options.minColumnWidth;
		
		Utils.execOnLoad(
			function(){
			     if(options.hoverRowClass) {
			     	$A(this.selectionManager.getRows()).each( function(e) {
                			Event.observe(e, Richfaces.browser.isIE ? 'mouseenter' : 'mouseover' , function() {
   	                	 		Element.addClassName(e, options.hoverRowClass);
                			});
                			Event.observe(e, Richfaces.browser.isIE ? 'mouseleave' : 'mouseout', function() {
                    			Element.removeClassName(e, options.hoverRowClass);
        	       	      	});
    	       		     });
			}
			if (this.selectionManager) { 
				this.update(true); 
			}
			
			this.initHorizonalScrollSynch();
			
			}.bind(this),
			Utils.Condition.ElementPresent(id+':od'), 100);
			
			
	},
	
	initHorizonalScrollSynch: function () {
		
		var synchTableId = this.options.synchronizeHorizonalScrolling;
		
		var extTable = $(synchTableId);
		var _this = this;
		if (extTable && extTable.component && extTable.component.scrollDiv) {
			ExtendedDataTable.DataTable.synchronizeHorizontalScrolling(_this, extTable.component);
		}
		
	},
	
	onColumnResizeHandler: function (colIndex, width) {
		if (this.onColumnResizeFunctions) {
			if (this.onColumnResizeFunctions[colIndex] && typeof(this.onColumnResizeFunctions[colIndex]) == 'function') {
				this.onColumnResizeFunctions[colIndex](width);
			}
			
		}
	},
	
	destroy: function() {
	
		if (this.tableB) {
        	var rows = this.tableB.rows;
        	rowCount = rows.length;
        	for(var i = 0; i < rowCount; i++) {
            	Utils.DOM.Event.removeListeners(rows[i]);
       		}
        }
	        //remove listeners
	        if (this.selectionManager){
	        	this.selectionManager.removeListeners();
	        }
	        if (this.header) {
	            this.header.removeListeners();
		        var headerChildren = this.header.getColumnCells();
		        l = headerChildren.length;
		        for (var i=0; i<l-1; i++) {
		            Utils.DOM.Event.removeListeners(headerChildren[i]);
		        };
	        }
	        if (this.groupRows) {
		        var l = this.groupRows.length;
		        for(var i = 0; i < l; i++) {        
		            Utils.DOM.Event.removeListeners(this.groupRows[i]);
		        }
	        } 
	        
	    if (this.scrollingDiv) {
	    	Event.stopObserving(this.scrollingDiv.element, 'scroll');
	    }
	    
	    if (this.scrollDiv) {
	    	Event.stopObserving(this.scrollDiv, 'scroll');
	    }
     	
        //null all references to DOM elements
        delete this.selectionManager;
        delete this.header;
        delete this.footer;
        
        $(this.id).component = null;
        this.table = null;
        this.splashScreen = null;
		this.mainDiv = null;
		this.outerDiv = null;
		this.tableB = null;
		this.tableFB = null;
		this.tableH = null;
		this.tableFH = null;
		this.fakeIeRow = null;
		this.fakeIeBodyRow = null;
		this.cols = null;
		this.scrollingDiv = null;
		this.scrollDiv = null;
		this.groupRows = null;
		this.groups = null;
		this.scrollPositionHolder = null;
		Event.stopObserving(window, 'resize', this.eventContainerResize);
	},
	
    /**
     * Changes the scroll position of the table to show row of specified index
     */
    showRow: function(rowIndex) {
        var row = $(this.id+":n:"+rowIndex);
        var offsetTop = this.tableB.offsetTop + row.offsetTop;
        var scrollTop = this.scrollingDiv.getElement().scrollTop;
        
        var dS = offsetTop - scrollTop;
        
        if (dS < 0) {
            this.scrollingDiv.getElement().scrollTop = scrollTop + dS;
        }else{
            var scrollDivHeight = this.scrollingDiv.getHeight();
            var rowHeight = row.getHeight();
            dS = dS + rowHeight - scrollDivHeight;
            if (dS > 0) {
                this.scrollingDiv.getElement().scrollTop = scrollTop + dS;
            }
        }        
	},
	
    setColumnWidth: function(columnIndex, newWidth) {
        if (columnIndex >= this.getColumnsNumber) {
            return false;
        }else{
        	if (!newWidth) {
 	           newWidth = null;
 	       }
            this.getColumns()[columnIndex].width = newWidth;
        }
    },
    	
	_findParentElement: function(event, element) {
		var el = null;
		if(ClientUILib.isSafari) {
			var targetCell = event.currentTarget;
			if(targetCell && targetCell.tagName.toLowerCase() == element) {
				el = targetCell;
			} else {
				var e = (event.target || event.srcElement);
				while((e != null) && (e.tagName.toLowerCase() != element) && (e != document)){
					e = e.parentNode;
				}//while
				if ((e) && (e.tagName.toLowerCase() == element)){
					el = e;
				}
			}
		} else {
			el = Event.findElement(event, element);
		}
		return el;
	},
	
	OnCellMouseClicked: function(event) {
	    if(Event.element(event).tagName.toLowerCase() != "th") {
		    //get column id
		    var el = this._findParentElement(event, "th");
		    var columnId = (el) ? el.id : null;
			
    		if (columnId && (columnId != "")){
	    		this.showSplashScreen();
		    	this.sortFct(event, columnId);
		    }
		    Event.stop(event);	
		}
	},
	
	preSendAjaxRequest: function(){
		//remove listeners
		//Event.stopObserving(window, 'resize', this.eventContainerResize);
		//Event.stopObserving(document, 'mousemove', this.header.eventSepMouseMove);
		//Event.stopObserving(document, 'mouseup', this.header.eventSepMouseUp);
		//show splash screen
		this.showSplashScreen();
	},
		
	showSplashScreen: function(){
        /**
            Opera 95 is not drawing additional
            element, and so I am commenting this out.
        if (ClientUILib.isOpera) {
            this.mainDiv.setStyle({display:'none'});
        }
        */
        //this.table.setStyle({visibility:'hidden'});
        var splshscr = this.splashScreen;
        splshscr.className = 'extdt-ss-vsbl';
	},
	
	hideSplashScreen: function(){
        /**
            Opera 95 is not drawing additional
            element, and so I am commenting this out.
        if (ClientUILib.isOpera) {
            this.mainDiv.setStyle({display:''});
        }
        */
		//this.table.setStyle({visibility:''});
		this.splashScreen.className = 'extdt-ss-hdn';
	},	
	
	OnWindowResize: function(event) {
		
		if (this.table) {
			this.updateLayout();
			return;
		}
		
		
		if(this.scrollingDivElement) {
	    	var scrollingDivElement = this.scrollingDiv.getElement();
			var tableWidth = jQuery(scrollingDivElement).parent().width();
			
			var frozenWidth = this._getTableWidth(this.tableFB);
			var scrollableWidth = tableWidth - frozenWidth;
			var realWidth = this._getTableWidth(this.tableB);
			
			
			if (realWidth > scrollableWidth && this.options.horizontalScrolling) {
				jScrollDiv = jQuery(this.scrollDiv);
				jScrollDiv.css('display', 'block');
				jScrollDiv.find('span').css('width', tableWidth + (realWidth - scrollableWidth));
				jScrollDiv.css('height', '17px');
			}else {
				jQuery(this.scrollDiv).css('display', 'none');
			}
		}

	},
	
	getColumnsNumber: function() {
		return this.columnsNumber;
	},
	getColWidth: function(columnNumber) {
		
	},
	getColumns: function() {
		return this.cols;
	},
	OnGroupRowMouseClicked: function(event) {
		
		var groupRow = this._findParentElement(event, "tr");
		var bExpanded = !(groupRow.getAttribute('expanded') == 'true');
		var sExpanded = bExpanded ? 'true' : 'false';
		var groupIndex = parseInt(groupRow.getAttribute('groupindex'));
		if (this.onGroupToggleFct){
			this.onGroupToggleFct(event, groupIndex);
		}
		groupRow.setAttribute('expanded', sExpanded);
		var imageDiv = groupRow.firstChild.firstChild.firstChild;
		this.toggleImageSource(imageDiv);
		this.setGroupExpanded(groupIndex, bExpanded);
		Event.stop(event);
	},
	
	toggleImageSource: function(imageDiv) {
		var src = imageDiv.getAttribute('src');
		var alternateSrc = imageDiv.getAttribute('alternatesrc');
		imageDiv.setAttribute('src', alternateSrc);
		imageDiv.setAttribute('alternatesrc', src);		
	},
	
    getColumnWidth: function(columnNumber) {
        if ((columnNumber < this.getColumnsNumber()) && (columnNumber >=0)) {
            var col = this.getColumns()[columnNumber];
            if (col.offsetWidth != null) {
                if (ClientUILib.isOpera) {
                    return parseInt(col.width);
                }else{
                    return col.offsetWidth;
                }
            }else{
                return parseInt(col.width);
            }
        }else{
            return null;
        }
    },
	
	setGroupExpanded: function(iGroupIndex, bValue) {
		var group = this.groups[iGroupIndex];
		
		var sVisibility;
		var sBorder;
		var sEmptyCells;
		
		if (bValue) {
			sVisibility = '';
			sBorderStyle = '';
		}else{
			sVisibility = 'none';
			sBorderStyle = 'none';
		}
		var size = group.size();
		for (var i=0; i<size; i++) {
			group[i].style.display = sVisibility;
			if (Richfaces.browser.isIE){
				//prevent IE from showing borders of cells
				//which parents have been hidden :|
				var cells = group[i].childNodes;
				var l = cells.length;
				for (var j = 0; j < l; j++) {
					cells[j].style.borderStyle = sBorderStyle;
				}
			}
		}
	},
	
	createControls: function() {
		var id = this.id;
		this.table = this.table || new ClientUI.common.box.Box(this.id +":tu",null,true);
		var table = this.table;
		this.splashScreen = $(this.id+":splashscreen");
		this.mainDiv = this.mainDiv || new ClientUI.common.box.Box(this.id,null,true);
		this.outerDiv = this.outerDiv || new ClientUI.common.box.Box(this.id +":od",null,true);
		this.tableB = $(this.id +":n");
		this.tableFB = $(this.id +":fn");
		this.tableH = $(this.id +":h");
		this.tableFH = $(this.id +":fh");
		//this.tableF = $(this.id +":f");
		this.fakeIeRow = jQuery(('#' + this.id +":fakeIeRow").replace(/\:/g, '\\:'));
		this.fakeIeBodyRow = jQuery(('#' + this.id +":body:fakeIeRow").replace(/\:/g, '\\:'));
		this.header = this.header || new ExtendedDataTable.DataTable.header(this.id +":header",this);
		this.header.minColumnWidth = this.minColumnWidth;
		//var colgroup = jQuery(this.table.element).find('tbody colgroup').get();
		this.cols = jQuery(this.table.element).find('tbody col').get();
		this.columnsNumber = this.cols.length;
		this.scrollingDiv = this.scrollingDiv || new ClientUI.common.box.Box(this.id +":sd",null,true);
		this.scrollDiv = $(this.id + ':scroll');
		this.groupRows = [];
		//var tfoot = table.getElement().getElementsByTagName('tfoot');
        this.footer = $(this.id +":footer");
		//this.footer = this.footer || new ExtendedDataTable.DataTable.header(this.id +":footer",this);
		if (ClientUILib.isOpera) {
			//no overflow-x nor overflow-y in Opera
			this.scrollingDiv.setStyle({overflow: 'scroll',
				width: this.mainDiv.getWidth()
			});
			this.table.setStyle({width: this.mainDiv.getWidth()});
		};

		var i = 0;
		var groupRow = $(id+':group-row:'+i);
		while (groupRow != null) {
			this.groupRows[i] = groupRow;
			Utils.DOM.Event.removeListeners(groupRow);
			Utils.DOM.Event.observe(groupRow,'click',this.eventGroupRowClicked);
			i++;
			groupRow = $(id+':group-row:'+i);
		}
		
		this.initGroupRows();
	},
	getScrollbarWidth: function() {
		var sd = this.scrollingDiv.getElement();
		return sd.offsetWidth - sd.clientWidth;
	},
	validateColumnsWidth: function(columns,excessWidth) { 
		var endIndex = columns.length-1;
        var i= endIndex - 1;
        while ((i >= 0 ) && (excessWidth > 0)) {
            if (Richfaces.browser.isIE) {
                var colWidth = parseInt(this.getColumns()[i].width) - 1;
            }else{
                var colWidth = this.header.getColumnWidth(i);
            }
            var spareWidth = Math.max(colWidth - this.minColumnWidth, this.minColumnWidth);
            var dW;
            if (spareWidth >= excessWidth) {
                dW = excessWidth; 
            }else{
                dW = spareWidth;
            }
            this.setColumnWidth(i, colWidth - dW);
            this.header.setColumnWidth(i, colWidth - dW);
            excessWidth -= dW;
            i--;
		/*
			var col = columns[columns.length-i-1];
			var spareWidth = col.offsetWidth - this.minColumnWidth;
			if (spareWidth > excessWidth) {
				col.width = col.offsetWidth - excessWidth;
				columns[endIndex - i].width = col.width;
				excessWidth = 0;
			}else{
				col.width = col.offsetWidth - spareWidth;
				columns[endIndex - i].width = col.width;
				excessWidth -= spareWidth;
			}
		*/
		}
	},
	
	getFooterHeight: function() {
        if (this.footer) {
            return this.footer.getHeight();
        }else{
            return 0;
        }
	},
	
	updateHeight: function () {
		if (this.scrollable=='false'){
			this.scrollingDiv.setStyle({height : (this.tableB.scrollHeight + 'px')});
		}
	},
	
	initHeaderHeight : function () {
		var tableH = jQuery(this.tableH);
		var tableFH = jQuery(this.tableFH); 
		
		var headerRowsCount = tableH.find('tr.rich-extdt-subheader').length;
		var tableFhHeaderRowsCount = tableFH.find('tr.rich-extdt-subheader'); 
		if (tableFhHeaderRowsCount.length > headerRowsCount) {
			headerRowsCount = tableFhHeaderRowsCount.length;
		}
		
		var headerRowHeight = tableH.find('tr.rich-extdt-subheader:first').height();
		var tableFhRowHeight = tableFH.find('tr.rich-extdt-subheader:first').height(); 
		if (tableFhRowHeight > headerRowHeight) {
			headerRowHeight = tableFhRowHeight;
		}
		
		tableH.find('tr.rich-extdt-subheader:first th').css({height: headerRowHeight + 'px'});
		tableFH.find('tr.rich-extdt-subheader:first th').css({height: headerRowHeight + 'px'});
		
		if (headerRowsCount > 1) {
			var filterRowHeight = tableH.find('tr.rich-extdt-subheader:last').height();
			var tableFhRowHeight = tableFH.find('tr.rich-extdt-subheader:last').height(); 
			if (tableFhRowHeight > filterRowHeight) {
				filterRowHeight = tableFhRowHeight;
			}
			
			tableH.find('tr.rich-extdt-subheader:last th').css({height: filterRowHeight + 'px'});
			tableFH.find('tr.rich-extdt-subheader:last th').css({height: filterRowHeight + 'px'});
		}
		
		var h = this.tableH.offsetHeight;
		if (this.tableFH.offsetHeight > h) {
			h = this.tableFH.offsetHeight;
		}
		
		return h;
	},
	
	updateLayout: function() {
	    ClientUILib.log(ClientUILogger.INFO, "updateLayout");
	    
	    this.showSplashScreen();

	    var mainDivWidth = this.mainDiv.getWidth();
	    var maxAllowedWidth = mainDivWidth;
	    var scrollingDivElement = this.scrollingDiv.getElement();
	    
	    if (!this.headerRowHeight) {

		    this.headerRowHeight = this.initHeaderHeight();
		    jQuery(this.table.element).find('thead tr th div:first').css('height', this.headerRowHeight);
	    }
	     
	    //jQuery(this.table.element).find('thead tr th div:first').css('height', this.headerRowHeight + 'px');
	    //jQuery(this.tableH).find('tr th:first, tr td:first').height(this.headerRowHeight);
	    //jQuery(this.tableFH).find('tr th:first, tr td:first').height(this.headerRowHeight);
	    
	    if (scrollingDivElement.style.height == 'auto') {
	    	var h = this.tableB.scrollHeight;
	    	scrollingDivElement.style.height = h + 'px';
	    }
	    
	    var wrapperDiv = jQuery(scrollingDivElement).find('div:first');
	    if (wrapperDiv.css('position') == 'relative') {
	    	wrapperDiv.css('position', 'absolute').find('div.wrapper').css('position', 'absolute');
	    	jQuery(this.tableFH).parent().parent().css('position', 'absolute').find('div.wrapper').css('position', 'absolute');
	    	//$(this.id + ':innerd').style.position = 'absolute';
	    }

	    if (this.scrollable=='true'){
	        var mainDivHeight = this.mainDiv.getHeight();
		    //height of the table filled with the data
		    var dataTableHeight;
		    // cululative height of the table elements: header footer caption
		    var nonDataElementsHeight = this.headerRowHeight + this.getFooterHeight() + this.header.getCaptionHeight();
		    //if srollable attribute is false we set the height of the main div and sroll div to the size of the data
			dataTableHeight = mainDivHeight - nonDataElementsHeight ;
			this.scrollingDiv.setStyle({height: dataTableHeight +'px'});
			var scrollbarWidth = scrollingDivElement.offsetWidth - scrollingDivElement.clientWidth;
            maxAllowedWidth = mainDivWidth - scrollbarWidth;
            //this.scrollingDiv.setStyle({width : this.outerDiv.getWidth() + "px"});
		}
	    
	   
		var cols = this.getColumns();
		var headerChildren = this.header.getColumnCells();

		var columnsNumber = this.getColumnsNumber();
		var totalWidth = 0;

	if ( !Richfaces.browser.isIE || true) {
        for (var i=0; i < columnsNumber-1; i++) {
        	var width;
            if (this.header.isColumnWidthPercentage(i)) {
                //change percents into pixels
                var val = this.header.getColumn(i).width;
                val = parseInt(val)/100;
                width = parseInt(maxAllowedWidth*val);
                //totalWidth += width;
                this.header.setColumnWidth(i, width);
                this.setColumnWidth(i, width);
            }
            totalWidth += parseInt(this.header.getColumn(i).width);
        }
        
        var visibleHeaderWidth = this.header.getVisibleWidth();
        var excessWidth = visibleHeaderWidth - maxAllowedWidth - 1;
		if (excessWidth > 0 && !this.options.horizontalScrolling) {
			this.validateColumnsWidth(cols,excessWidth);
		};
		
	};
	
	var tableWidth = jQuery(scrollingDivElement).parent().width();
	

	
	var lastCol = cols[columnsNumber-1];
	var lastColWidth = (tableWidth > totalWidth ? tableWidth - totalWidth : 0);
	
		
	if (true) {
		this.setColumnWidth(columnsNumber-1, lastColWidth);
		this.header.setColumnWidth(columnsNumber-1, lastColWidth);
	
	}/*else {
		this.setColumnWidth(columnsNumber-1, 0);
		this.header.setColumnWidth(columnsNumber-1, 0)
	}*/
	
	var frozenWidth = this._getTableWidth(this.tableFB);
	var scrollableWidth = tableWidth - frozenWidth;
	var realWidth = this._getTableWidth(this.tableB);
	
	if (realWidth > scrollableWidth && this.options.horizontalScrolling) {
		jScrollDiv = jQuery(this.scrollDiv);
		jScrollDiv.css('display', 'block');
		jScrollDiv.find('span').css('width', tableWidth + (realWidth - scrollableWidth));
		//var scrollHeight = this.scrollDiv.offsetHeight - this.scrollDiv.clientHeight;
		jScrollDiv.css('height', '17px');
		var scrollHeight = jScrollDiv.height();
		var contentHeight = jQuery(scrollingDivElement).height();
		scrollingDivElement.style.height = (contentHeight - (this.scrollable == 'false' ? 0 : scrollHeight) )+'px';
	}else {
		/*var scrollHeight = this.scrollDiv.offsetHeight;
		if (this.scrollable != 'true') {
			scrollingDivElement.style.height = (parseInt(scrollingDivElement.style.height) - scrollHeight) + 'px';
		}*/
		//this.scrollDiv.style.left = 0;
		jQuery(this.scrollDiv).css('display', 'none');
	}
	
	
	
	this.tableFH.style.width = this.tableFB.style.width = frozenWidth + 'px';
	if (frozenWidth == 0) {
		this.tableFH.parentNode.style.display = 'none';
	}
	this.tableH.style.width =  this._getTableWidth(this.tableH) + 'px';;
	this.tableB.style.width = realWidth + 'px';
	
	this.tableB.parentNode._left = this.tableH.parentNode._left = frozenWidth;
	
	this.tableB.parentNode.style.left = this.tableH.parentNode.style.left =  (this.tableB.parentNode._left - this.scrollDiv.scrollLeft) + 'px';
	
	
		for (var i=0; i < columnsNumber-1; i++) {
			if(this.options.isSingleCanHideTrueAvailable){
				var headerChild = headerChildren[i];
				Utils.DOM.Event.removeListeners(headerChild);
            	Utils.DOM.Event.observe(headerChild,'mouseover',this.header.eventHeaderCellMouseOver);
           	    Utils.DOM.Event.observe(headerChild,'mouseout',this.header.eventHeaderCellMouseOut);
			}
		};
		this.header.adjustSeparators(this);
		
		/*if (this.scrollable) {
			this.scrollPositionHolder = $(this.id + 'scrollPos');
			if (this.scrollingDiv) {
				if (this.scrollingDiv.element.scrollHeight > this.options.scrollPos) {
					this.scrollingDiv.element.scrollTop = this.options.scrollPos;
				}
			}
		}*/
		
		this.hideSplashScreen();
		
		if (this.scrollable && this.scrollingDiv) {
			Event.observe(this.scrollingDiv.element, 'scroll', this.onScroll.bindAsEventListener(this));
			Event.observe(this.scrollDiv, 'scroll', this.onScroll.bindAsEventListener(this));
		}
		
		this.mainDiv.element.fire("rich:onlayoutupdate", {});

	},
	
	initGroupRows: function () {
		var _this = this;
		
		jQuery('img.groupImg', this.scrollingDiv.element).click(function(e) {
			var src = this.src;
			var doExpand = src.indexOf('plusIcon.gif') != -1;
			
			var ajaxMode = _this.options.groupExpandAjax == 'true';
			
			var trId = jQuery(this).parents('tr.extdt-root-row:first').attr('id');
			var rowCounter = parseInt(trId.substring(trId.indexOf('n:') + 2));
		
			if (ajaxMode && !this.loaded && doExpand) {
				
				var a = this;
				var oncomplete = function () {
					_this._addGroupRows(a);
					_this._toggleGroupRows(a);
				};
				
				
				if (!$(_this.is + 'subRowsDiv')) {
					var d = jQuery("<div id='"+ _this.id +"subRowsDiv' style='display: none'></div>");
					jQuery(_this.mainDiv.element).append(d);
				}
				
				_this.onGroupToggleFct(e, this.id, rowCounter, doExpand, ajaxMode, oncomplete);
			}else {
				_this._toggleGroupRows(this);
				_this.onGroupToggleFct(e, this.id, rowCounter, doExpand, false, null);
			}
		
		});
	},
	
	_addGroupRows : function (a) {
		var rootTr = jQuery(a).parents('tr:first')[0];
		var frozenCol = jQuery(this.tableFH).find('thead tr:first th').length;
		var rowIndex = rootTr.rowIndex;
		var _this = this;
		jQuery($(this.id + 'subRowsDiv')).find('tr').each(function (i, tr) {
			if (!jQuery(tr).hasClass('extdt-fakeierow') && !jQuery(tr).hasClass('extdt-root-row')) {
				var newTr = jQuery(tr.cloneNode(false));
				
				var tds = jQuery(tr).find('td');
				
				for (var f=0; f<frozenCol;f++) {
					newTr.append(tds[f]);
				}
				if (f > 0) {
					newTr.insertAfter(jQuery(_this.tableFB.rows[rowIndex]));
					newTr = jQuery(tr.cloneNode(false));
				}
				
				for (var f=frozenCol; f<tds.length;f++) {
					newTr.append(tds[f]);
				}

							
				newTr.insertAfter(jQuery(_this.tableB.rows[rowIndex++]));
			}
		});
	},
	
	_toggleGroupRow : function (root, doExpand) {
		var next = root;
		var rows = [];
		while (true) {
			var next = next.nextSibling;
			if (next && next.tagName.toLowerCase() == 'tr' && next.className.indexOf('extdt-root-row') == -1) {
				rows.push(next.rowIndex);
			}else {
				break;
			}
		}
		
		var toggelRow = function(table, index, doExpand) {
			if (table.rows.length > index) {
				var r = jQuery(table.rows[index]);
				if (doExpand) {
					r.find('td.extdt-cell').show();
					r.show();
				}else {
					r.find('td.extdt-cell').hide();
					r.hide();
				}
			}
		};
		
		for (var i=0;i<rows.length;i++) {
			toggelRow(this.tableB, rows[i], doExpand);
			toggelRow(this.tableFB, rows[i], doExpand);
		}
		
		this._applyGroupStyles(root.rowIndex, next ? next.rowIndex : null, doExpand);
		
	},
	
	_applyGroupStyles : function (rootIndex, nextRootIndex, doExpand) {
		this._applyGroupStyle(this.tableB, rootIndex, nextRootIndex, doExpand);
		this._applyGroupStyle(this.tableFB, rootIndex, nextRootIndex, doExpand);
	},
	
	_applyGroupStyle : function (table, rootIndex, nextIndex, doExpand) {
		if (doExpand) {
			jQuery(table.rows[rootIndex]).addClass('extdt-root-row-border');
			jQuery(table.rows[rootIndex]).addClass('extdt-root-row-expanded');
			if (nextIndex && nextIndex < table.rows.length) {
				jQuery(table.rows[nextIndex]).addClass('extdt-root-row-border');
			}
		}else {
			jQuery(table.rows[rootIndex]).removeClass('extdt-root-row-border');
			jQuery(table.rows[rootIndex]).removeClass('extdt-root-row-expanded');
			if (nextIndex && nextIndex < table.rows.length) {
				jQuery(table.rows[nextIndex]).removeClass('extdt-root-row-border');
			}
		}
	},
	
	_toggleGroupRows : function (el) {
		var src = el.src;
		var doExpand = src.indexOf('plusIcon.gif') != -1;
		this._toggleGroupRow(jQuery(el).parents('tr:first')[0], doExpand);
		
		if (doExpand) {
			src = src.replace('plusIcon.gif', 'minusIcon.gif');
		}else {
			src = src.replace('minusIcon.gif', 'plusIcon.gif');
		}
		el.src = src;
		this.updateHeight();
		el.loaded = true;
	},
	
	_getTableWidth: function (t) {
		var w = 0;
		jQuery(t).find('col').each(function (i,o ) {
			if (o.width) {
				w += parseInt(o.width);
			}
		});
		return w;
	},
	
	onScroll: function (e) {
		
	
		if (this.scrollDiv && this.options.horizontalScrolling) {
			var headerScrolling = this.tableH.parentNode;
			this.tableB.parentNode.style.left = (this.tableB.parentNode._left - this.scrollDiv.scrollLeft) + 'px';
			this.tableH.parentNode.style.left = (this.tableH.parentNode._left - this.scrollDiv.scrollLeft) + 'px';
		}
		
				
		if (this.scrollPositionHolder && this.scrollingDiv) {
			this.scrollPositionHolder.value = this.scrollingDiv.element.scrollTop;
			if (this.options.onScrollFunction && e) {
				this.options.onScrollFunction(e);
			}
		}
	},
	
    update: function(refreshEvents) {
        this.createControls();
        if ( !Richfaces.browser.isIE) {
        	
            if (this.fakeIeRow) {
            	this.fakeIeRow.remove();
            	this.fakeIeRow = null;
			}
			if (this.fakeIeBodyRow) {
				this.fakeIeBodyRow.remove();
				this.fakeIeBodyRow = null;
			}
		};
		this.selectionManager.refreshEvents();
		this.updateLayout();
		this.selectionManager.restoreState();
	},
	updateAfterSorting:function(refreshEvents){
		if(this.forseSorting){
			this.form = A4J.AJAX.locateForm($(this.id));
			var formId = (this.form) ? this.form.id : null; 
			var vId = this.id;
			//var options = {'parameters':{"ajaxSingle":vId}};
		//	A4J.AJAX.Submit(this.id, formId, refreshEvents, null);
			var value = $(this.id+"forseSorting").value;
			if(new Number(value) == 0){
				$(this.id+"forseSorting").click();
			}
		}
	},
	
	onEditingComplete: function (event) {
		
		if (this.selectionManager.cellSelection && this.selectionManager.cellSelection.editingCell) {
			var cell = this.selectionManager.cellSelection.editingCell;

			var rowIndex = cell.id.match(/:([\d]*[_]*[\d]*):/)[1];
			var columnId = cell.id.match(/[^:]*$/);
			
			if (!this.selectionManager.cellSelection.isChanged()) {
				this.selectionManager.cellSelection.cancelEditing(event);
				Event.stop(event);
				return;
			}
			
			var _this = this;
			
			var onbeforedomupdate = function (request, response, data) {
				if (data && data.errors) {
					var coId = _this.id + cell.id.match(/:[^:]*$/);
					if (_this.options.editing[coId] && _this.options.editing[coId].onError) {
						_this.options.editing[coId].onError(data.errors);
					}
				}else {
					_this.selectionManager.cellSelection.cancelEditing(event);
				}
			};
			
			var oncomplete = function (request, response, data) {
				if (!data || !data.errors) {
					
					var nextEditable = _this.selectionManager.cellSelection.nextEditable;
					
					_this.options.modifiedData = true;
					//_this.selectionManager.cellSelection.selectedCell = null;
					
					if (!nextEditable) {
						_this.selectionManager.cellSelection.selectCurrentCell($(cell.id), event, true);
					}
					
					if (nextEditable) {
						_this.selectionManager.cellSelection.edit(nextEditable, event);
					}
				}
			};
			
			Event.stop(event);
			
			this.options.editAction(event, rowIndex, columnId, onbeforedomupdate, oncomplete);
		}
	}
});

ExtendedDataTable.DataTable.synchronizeHorizontalScrolling = function () {
	
	var elements = null;
	
	for (var i = 0; i<arguments.length; i++) {
		if (!elements) {
			elements = jQuery($(arguments[i].scrollDiv.id));
		}else {
			elements = elements.add($(arguments[i].scrollDiv.id));
		}
	}
	
	elements.unbind('mousedown');
	elements.unbind('scroll');
	
	var synchronizeScroll = function (el1, el2) {
		var percent = el1.scrollLeft / (el1.scrollWidth - el1.offsetWidth);
		el2.scrollLeft = (el2.scrollWidth - el2.offsetWidth) * percent;
	};
	
	var onScroll = function () {
		var _this = this;
		elements.each(function (i, o) {
			if (_this != o) {
				synchronizeScroll(_this, o);
			}
		});
	};
	
	if (elements && elements.length > 1) {
		elements.mousedown(function () {
			var _this = this;
			
			elements.each(function (i, o) {
				jQuery(o).unbind('scroll', onScroll);
			});
			jQuery(this).scroll(onScroll);	
		});
	}
	
};
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
	
	setColumnWidth: function(columnIndex, newWidth) {
	   if (columnIndex >= this.getColumnsNumber()) {
	       return false;
	   }else{
	       if (!newWidth) {
	           newWidth = null;
	       }
	       this.getColumns()[columnIndex].width = newWidth;
	   }
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
});/* Class taken from ScrollableDataTable - unmodified */
ExtendedDataTable.CellSelection = Class.create({
	initialize: function(table, allowCellSelection,cellSelectionClass,cellStateInput,tables){
		this.allowCellSelection = allowCellSelection;
		this.cellSelectionClass = cellSelectionClass;
		this.table = table;
		this.tables = tables;
		this.stateInput = $(cellStateInput);
		this.tableId = cellStateInput.split(/cellState$/)[0];
		this.selectedCell = null;
		this.cellIndex = -1;
		this.rowIndex = -1;
		this.restoreState();
		this.editors = {};
		this.cellContent = null;
		this.editingCell = null;
	},
	

	click: function(e) {
		var el = jQuery(e.target);
		if (!el.is(":input, option")) {
			var td = el.is(".rich-extdt-cell") ? el[0] : el.parents(".rich-extdt-cell:first")[0];
			if (this.editingCell == td) {
				return;
			}
			this.selectCurrentCell(td);
			this.edit(td, e);
		}
	},
	
	bindClick: function() {
		var _this = this;
		jQuery(this.tables[1]).parents("div[id]:first").click(function (e) {
			_this.click(e);
		});
	},
	
	reset:function(){
		this.selectedCell = null;
		this.cellIndex = -1;
		this.rowIndex = -1;
		this.saveState();
	},
	restoreState:function(){
		if(this.isAllowCellSelection() && this.tables[1]){
			var e = document.getElementById(this.tableId + ":" + this.stateInput.value);
			this.selectCurrentCell(e);
		}
	},
	saveState:function(){
		if(this.isAllowCellSelection()){
			if(this.stateInput){
				this.stateInput.value  = this.selectedCell ? this.selectedCell.id.match(/[^:]*:[^:]*$/) : "";
			}
		}
	},
	isAllowCellSelection:function(){
		return this.allowCellSelection;
	},
	
	
	cancelEditing: function (event) {
		if (this.editingCell) {
			var td = jQuery(this.editingCell);
			var columnId = this.tableId + this.editingCell.id.match(/:[^:]*$/);

			var editDiv = td.find(".extdt-cell-edit");
			var col = jQuery(document.getElementById(columnId)); 
			
			var ie = col.find(".rich-extdt-ie");
			var editor = editDiv.find('.rich-extdt-ie-editor');
			
			this._moveElement(ie.find('.rich-extdt-ie-content div:first'), editDiv);
			this._moveElement(editor, ie);
			
			if (this.table.options.editing[columnId] && this.table.options.editing[columnId].onEdited)  {
				this.table.options.editing[columnId].onEdited(event);
			}
			
			jQuery(this.editingCell).find('.extdt-cell-modified-img').show();
			
			this.editingCell = null;
			
		}
	},
	
	createValueHash : function () {
		var columnId = this.tableId + this.editingCell.id.match(/:[^:]*$/);
		var col = jQuery(document.getElementById(columnId)); 
		var ie = col.find(".rich-extdt-ie")[0];
		ie._value = this.getValueHash();
	},
	
	getValueHash: function () {
		if (!this.editingCell) {
			return null;
		}
		
		var v = '';
		jQuery(this.editingCell).find('input,select').each(function (i, o) {
			v += jQuery(o).val();
		});
		return v;
	},
	
	isChanged: function () {		
		if (!this.editingCell) {
			return false;
		}
		
		var columnId = this.tableId + this.editingCell.id.match(/:[^:]*$/);
		var col = jQuery(document.getElementById(columnId)); 
		var ie = col.find(".rich-extdt-ie")[0];
		return this.getValueHash() != ie._value;
	},
	
	popEditor: function (columnId, td) {
		var cellDiv = td.children(".extdt-cell-div");
		var editWrapper = cellDiv.find('.extdt-cell-edit');
		var col = jQuery(document.getElementById(columnId)); 
		var ie = col.find(".rich-extdt-ie");

		if (!ie.length) {
			return null;
		}
		
		if (editWrapper.length == 0) {
			cellDiv.wrapInner("<div class='extdt-cell-edit'><div></div></div>");
			editWrapper = cellDiv.find(".extdt-cell-edit");
		}
		
		var text = editWrapper.find('div:first');
		var editor = ie.find('.rich-extdt-ie-editor:first');
		var firstInput = editor.find('input:visible:first, select:visible:first');
		
		if (firstInput.length) {
			firstInput.val('');
		}
		
		this._moveElement(text, ie.find('.rich-extdt-ie-content').html(''));
		this._moveElement(editor, editWrapper);
		
		return text.text();
		
	},
	
	_moveElement: function (el, parent) {
		if (el.length) {
			el = el[0];
		}
		if (parent.length) {
			parent = parent[0];
		}
		parent.appendChild(el);
	},
	
	edit: function (tdObj, event) {
		var jQueryTd = jQuery(tdObj);
		
		if (jQueryTd.hasClass("extdt-group-cell") || jQueryTd.hasClass("extdt-empty-cell")) { 
			return;
		}
		
		if (jQueryTd.hasClass("extdt-cell-not-editable")) {
			if (this.editingCell) {
				this.table.onEditingComplete(event);
			}
			return;
		}
		
		if (this.editingCell == tdObj) {
			return;
		}
		
		var columnId = this.tableId + this.selectedCell.id.match(/:[^:]*$/);
		
		if (this.editingCell && this.isChanged(columnId, this.editingCell)) {
			this.nextEditable = tdObj;
			this.table.onEditingComplete(event);
			return;
		}else if (this.editingCell) {
			this.cancelEditing(event);
		}
		
		this.nextEditable = null;
		
		var text = this.popEditor(columnId, jQueryTd);
		
		if (jQueryTd.length) {
			jQueryTd.find('.extdt-cell-modified-img').hide();
		}
		
		if (true) {
			this.editingCell = tdObj;
			var _this = this;
			window.setTimeout(function () {
				if (_this.table.options.editing[columnId] && _this.table.options.editing[columnId].onEdit)  {
					_this.table.options.editing[columnId].onEdit(event, text);
				}
				
				var firstInput = jQuery(_this.editingCell).find('input:visible:first, select:visible:first');
				firstInput.blur().focus().val(text);
				_this.createValueHash();
			}, 0);
		}
	},
	selectCurrentCell:function(tdObj, event, force){
		if (!tdObj) {
			return;
		}
		
		var jQueryTd = jQuery(tdObj);
		if (jQueryTd.is("td:not(.extdt-group-cell, .extdt-empty-cell)")) {
			
			if(this.selectedCell){
				var lastId = this.selectedCell.id;
				this.unselectCell(this.selectedCell);
			}
		
			tdObj.className=tdObj.className+" "+this.cellSelectionClass;
			this.selectedCell = tdObj;
			this.setCellIndex();
			this.saveState();
		}
	},
	setCellIndex:function(){
		var parentTr = this.selectedCell.parentNode;
		this.rowIndex = parentTr.sectionRowIndex;
		var table = parentTr.parentNode.parentNode;
		this.cellIndex = this.selectedCell.cellIndex;
		if (table == this.tables[1]) {
			this.cellIndex += this.tables[1].tBodies[0].rows[0].cells.length;
		}
	},
	unselectCell:function(tdObj){
		if(tdObj){
			tdObj.className = tdObj.className.replace(" "+this.cellSelectionClass,"");
		}
	},
	getSelectedCell: function () {
		if (this.selectedCell && !this.selectedCell.parentNode) {
			this.selectedCell = $(this.selectedCell.id);
		}
		return this.selectedCell;
	},
	moveLeft:function(ev){
		if (this.editingCell) {
			return;
		}
		
		this.selectedCell = this.getSelectedCell();
		
		if(this.selectedCell && this.cellIndex){
			if (this.selectedCell.cellIndex) {
				this.selectCurrentCell(this.selectedCell.parentNode.cells[this.selectedCell.cellIndex - 1]);
			} else {
				var rows = this.tables[0].tBodies[0].rows;
				if(rows.length) {
					var cells = rows[this.rowIndex].cells;
					this.selectCurrentCell(cells[cells.length -1]);
				}
			}
		}
	},
	moveRight:function(ev){
		if (this.editingCell) {
			return;
		}
		
		this.selectedCell = this.getSelectedCell();
		
		if(this.selectedCell){
			var parentTr = this.selectedCell.parentNode;
			if (this.selectedCell.cellIndex < parentTr.cells.length -1) {
				this.selectCurrentCell(parentTr.cells[this.selectedCell.cellIndex + 1]);
			} else if (this.tables[0] == parentTr.parentNode.parentNode) {
				this.selectCurrentCell(this.tables[1].tBodies[0].rows[this.rowIndex].cells[0]);
			}
		}
	},
	moveUp:function(event,nextRowIndex){
		this.upOrDown(event,nextRowIndex);
		
	},
	upOrDown:function(event,nextRowIndex){
		if (this.editingCell) {
			return;
		}
		
		this.selectedCell = this.getSelectedCell();
		
		if(this.selectedCell){
			var rows = this.selectedCell.parentNode.parentNode.rows;
			var rIndex = Richfaces.browser.isIE ? nextRowIndex +1 : nextRowIndex;
			var row  = rows[rIndex];
			if(row && row.cells){
				var cells = row.cells;
				this.selectCurrentCell(cells[this.selectedCell.cellIndex]);
			}	
		}
	},
	
	moveDown:function(event,nextRowIndex){
		this.upOrDown(event,nextRowIndex);
	}
});
ExtendedDataTable.Selection = Class.create({
	initialize: function() {
		this.ranges = [];
	},

	addId: function(id) {
		id = parseInt(id);
		if(this.isSelectedId(id))
			return;
		var i = 0;
		while(i < this.ranges.length && id >= this.ranges[i++].indexes[1]);
		i--;
		if(this.ranges[i-1] && id==(this.ranges[i-1].indexes[1]+1) ) {
			if(id==(this.ranges[i].indexes[0]-1)) {
				this.ranges[i-1].indexes[1] = this.ranges[i].indexes[1];
				this.removeRange(i);			
			} else {
				this.ranges[i-1].indexes[1]++;			
			}
		} else {
			if(this.ranges[i]){
				if(this.ranges[i] && id==(this.ranges[i].indexes[0]-1)) {
					this.ranges[i].indexes[0]--;			
				} else {
					if(id==(this.ranges[i].indexes[1]+1)){
						this.ranges[i].indexes[1]++;			
					} else {
						if(id<this.ranges[i].indexes[1]){
							this.addRange(i, new ExtendedDataTable.Range(id, id));					
						} else {
							this.addRange(i + 1, new ExtendedDataTable.Range(id, id));					
						}
					}
				}	
			} else {
				this.addRange(i, new ExtendedDataTable.Range(id, id));					
			}	
		}
        this.selectionIsChanged = true;
    },

	addRange: function(index, range) {
		var i = this.ranges.push(range) - 2;
		if(index >= 0) {
			while(i>=index)
				this.ranges[i+1] = this.ranges[i--];
			this.ranges[i+1] = range;
		}
	},

	removeRange: function(index) {
		var i = index + 1;
		while(i!=this.ranges.length)
			this.ranges[i-1] = this.ranges[i++];
		this.ranges.pop();
	},

	isSelectedId: function(id) {
		var i = 0;
		while(i < this.ranges.length && id >= this.ranges[i].indexes[0]) {
			if(id >= this.ranges[i].indexes[0] && id <= this.ranges[i].indexes[1]) {
				return true;
			} else {
				i++;
			}
		}
		return false;
	},

	getSelectedIdsQuantity: function() {
		var number = 0;
		var l = this.ranges.length;
		for (var i = 0; i < l; i++) {
			number+= this.ranges[i].size();
		}
		return number;
	},
	
	size: function () {
		return this.getSelectedIdsQuantity();
	},
	
	removeId: function(id) {
		id = parseInt(id);
		if(!this.isSelectedId(id))
			return;
		var i = 0;
		while(i < this.ranges.length && id > this.ranges[i++].indexes[1]);
		i--;
		if(this.ranges[i]) {
			if(id==(this.ranges[i].indexes[1]) ) {
				if(id==(this.ranges[i].indexes[0])){
					this.removeRange(i);			
				} else {
					this.ranges[i].indexes[1]--;			
				}
			} else {
				if(id==(this.ranges[i].indexes[0])){
					this.ranges[i].indexes[0]++;			
				} else {
				this.addRange(i+1, new ExtendedDataTable.Range(id+1, this.ranges[i].indexes[1]));			
				this.ranges[i].indexes[1] = id-1;
				}
			}
		}
        this.selectionIsChanged = true;
    },

	getState: function() {
		var s = this.clone();
		return {
			size: function() {
					return s.size();
			},
			
			each: function(iterator) {
				s.each(iterator);
  			},
			
			isSelected: function(id) {
				return s.isSelectedId(id);
  			}
		};
	},

	clone: function() {
		var ret =  Object.extend(new Object(),this);
		var l = ret.ranges.length;
		ret.ranges = new Array(l);
		for (var i = 0; i < l; i++) {
			ret.ranges[i] = this.ranges[i].clone();
		}		
 		return ret;
 	},

	each: function(iterator) {
		var l = this.ranges.length;
		for (var i = 0; i < l; i++) {
			this.ranges[i].each(iterator);					
		}
 	},
  	
  	getRanges: function() {
		return this.ranges;
	},

	setRanges: function(ranges) {
		this.ranges = ranges;
	},
	
	initRanges: function(rangeStrRArray) {
		if(rangeStrRArray.length == 0) {
			this.ranges = [];
			return;
		}
		this.ranges = new Array(rangeStrRArray.length);
		var indexStrRArray;
		var l = this.ranges.length;
		for(var i = 0; i < l; i++) {
			indexStrRArray = rangeStrRArray[i].split(",");
			this.ranges[i] = new ExtendedDataTable.Range(parseInt(indexStrRArray[0]), parseInt(indexStrRArray[1]));
		}
		
	}, 

	inspectRanges: function() {
		var ranges = this.getRanges();
		var ret = "";
		ranges.each( function(r) { ret += r.inspect(); } );
		return ret;
	} 
});


/* Class taken from ScrollableDataTable - unmodified */
ExtendedDataTable.Range = Class.create({
	initialize: function(startIndex, endIndex) {
		this.indexes = [startIndex, endIndex];
	},

	inspect: function() {
		return this.indexes[0] + "," + this.indexes[1] + ";";
	},
	toString: function() {
		return this.inspect();
	},
	
	size: function() {
		return this.indexes[1] - this.indexes[0] + 1;;
	},
	
	each: function(iterator) {
		var j = this.indexes[0];
		while(j <= this.indexes[1]) {
      		iterator(j++);					
		}
  	},
	
	clone: function() {
		var ret = Object.extend(new Object(),this);
		ret.indexes = this.indexes.clone();
		return ret;
  	}
});

/* Modified class from ScrollableDataTable */
ExtendedDataTable.SelectionManager = Class.create({
	initialize: function(options, owner) {
		this.dataTable = owner;
		this.dataTableElement = document.getElementById(this.dataTable.id);
		this.options = options;
		this.selectionFlag;
		this.firstIndex;
		this.activeRow = -1;
		var element = options.gridId;
		this.gridElement = document.getElementById(element + ":n");
		this.gridElement2 = document.getElementById(element + ":fn");
		//this.gridElement = $(this.prefix + ':sd');
		
		this.prefix = options.gridId;
		this.selection = new ExtendedDataTable.Selection();
		this.cellSelection = new ExtendedDataTable.CellSelection(owner, this.options.allowCellSelection,this.options.selectedCellClass,owner.id+'cellState', [this.gridElement2, this.gridElement]);

		this.inputElement = options.selectionInput;
		this.onselectionchange = options.onselectionchange;
		this.onRowContextMenu = options.onRowContextMenu;
		this.selectedClass = options.selectedClass;
		this.activeClass = options.activeClass;
		

	},
	
	isNotEmptyGrid: function (grid) {
		return jQuery(grid).find('colgroup col:first').length > 0;
	},
	
	refreshEvents: function() {
		if (this.isNotEmptyGrid(this.gridElement2)) {
			this.rowCount = this.gridElement2.tBodies[0].rows.length;
		}else {
			this.rowCount = this.gridElement.tBodies[0].rows.length;
		}
		//ClientUILib.isIE && this.rowCount--;
		if (this.cellSelection.isAllowCellSelection()) {
			this.cellSelection.bindClick();
		} else {
			this.setListeners();
		}
		if(this.options.selectionMode != "none" || this.cellSelection.isAllowCellSelection()) {
			this.eventKeyPress = this.processKeyDown.bindAsEventListener(this);
			Event.observe(document, "keydown", this.eventKeyPress);
		}

		if (!this.eventKeyPressAjaxListener) {
			this.eventKeyPressAjaxListener = new A4J.AJAX.Listener(function(req, event, data) {
				if(!$(this.prefix + ":n")) {
					Event.stopObserving(document, "keydown", this.eventKeyPress);		
				}
			}.bind(this));
			A4J.AJAX.AddListener(this.eventKeyPressAjaxListener);
		}
		
		if (document.selection) {
			this.eventResetSelection = this.resetSelection.bindAsEventListener(this);
			Event.observe(this.gridElement, "click", this.eventResetSelection);
			Event.observe(this.gridElement2, "click", this.eventResetSelection);
		}

		this.eventLostFocus = this.processLostFocus.bindAsEventListener(this);
		Event.observe(document, "click", this.eventLostFocus);

		this.eventPreventLostFocus = this.processPreventLostFocus.bindAsEventListener(this);
		Event.observe(this.gridElement, "click", this.eventPreventLostFocus);
		Event.observe(this.gridElement2, "click", this.eventPreventLostFocus);
		
		this.eventPreventEditingLostFocus = this.processPreventEditingLostFocus.bindAsEventListener(this);
		this.eventLostEditingFocus = this.processEditingLostFocus.bindAsEventListener(this);
		
		Event.observe(this.dataTableElement, "click", this.eventPreventEditingLostFocus);
		Event.observe(document, "click", this.eventLostEditingFocus);
		
	},

	restoreState: function() {
		this.selectionFlag = null;
		var selStrAr = $(this.inputElement).value.split(";");
		var activeRow = NaN;
		while (selStrAr.length != 0 && selStrAr[selStrAr.length - 1].indexOf(",") == -1 &&
			isNaN(activeRow = Number(selStrAr.pop())));
		if (!isNaN(activeRow)) {
			this.setActiveRow(activeRow);
		}
		this.selection.initRanges(selStrAr);
		var i = 0;
		var j;
		while(i < this.selection.ranges.length) {
			j = this.selection.ranges[i].indexes[0];
			while(j <= this.selection.ranges[i].indexes[1]) {
				this.addRowToSelection(j);
				this.setActiveRow(j);
				//var nElement = $(this.prefix + ":n:" + j);
				//Element.addClassName(nElement, "extdt-row-selected rich-sdt-row-selected " + this.selectedClass);
				j++;
			}
			i++;
		}
		this.oldState = this.selection.getState();
	},
	
	setListeners: function() {
		if (this.isNotEmptyGrid(this.gridElement2)) {
			this._setListeners($(this.prefix + ":ftb").rows);
		}
		
		if (this.isNotEmptyGrid(this.gridElement)) {
			this._setListeners($(this.prefix + ":tb").rows);
		}
	},
	
	_setListeners: function(nrows) {
		//May need optimization by attaching listeners to whole rows instead of all cells
		//var nrows = $(this.prefix + ":n").rows;
		var rowIndex;
		var groupingExists = $(this.prefix + ":group-row:0") != null;
		if(!groupingExists) { //simple listener binding
			if(this.options.selectionMode != "none") {
				for(var i = 0; i < this.rowCount; i++) {
                    var arr = nrows[i].id.split(":");
                    rowIndex = Number(arr[arr.length-1]);
                   	this.addListener(nrows[i], rowIndex);
				}
			}
		} else { //extended listener binding with grouping
			var groupRow;
			var lastGroupId = 0;
			var bGroupExpanded = true;
			var bHideFirstRow = false;
			if (Richfaces.browser.isIE) {
				//hide first fake ie row to ensure proper rendering
				bHideFirstRow = true;
			};
			var groupId;
			var groupItems = [];
			var groupItem = 0;
			var groups = [];
			var groupRows = this.dataTable.groupRows;
			for(var i = 0; i < this.rowCount; i++) {
				tempo = nrows[i].id.split(this.prefix)[1];
				var tempArr = tempo.split(":")
				groupRow = tempArr[1] == "group-row";
				groupId = Number(tempArr[2]);
				if(groupRow) {
					groups[lastGroupId] = groupItems;
					bGroupExpanded = (groupRows[groupId].getAttribute('expanded') == 'true');
					var textSpan = groupRows[lastGroupId].lastChild.lastChild;
					var txtNode = document.createTextNode("(" + groupItems.size() + ")");
					if (textSpan.lastChild) {
						textSpan.replaceChild(txtNode, textSpan.lastChild);
					}else{
						textSpan.appendChild(txtNode);
					}
					groupItem = 0;
					groupItems = [];
					lastGroupId = groupId;
				} else {
					if(this.options.selectionMode != "none") {
                        var arr = nrows[i].id.split(":");
                        rowIndex = Number(arr[arr.length-1]);
                        this.addListener(nrows[i], rowIndex);
					}
					groupItems[groupItem++] = nrows[i];
					if ( (i==0) && (bHideFirstRow) ) {
					   
					}
					if (!bGroupExpanded) {
						nrows[i].style.display = 'none';
						if ((Richfaces.browser.isIE) && (i!=0)){
							//prevent IE from showing borders of cells
							//which parents have been hidden :|
							var cells = nrows[i].childNodes;
							var l = cells.length;
							for (var j = 0; j < l; j++) {
								cells[j].style.borderStyle = 'none';
							}
						}						
					}
				}
			}
			groups[lastGroupId] = groupItems;
			var textSpan = groupRows[lastGroupId].lastChild.lastChild;
			var txtNode = document.createTextNode("(" + groupItems.size() + ")");
			if (textSpan.lastChild) {
				textSpan.replaceChild(txtNode, textSpan.lastChild);
			}else{
				textSpan.appendChild(txtNode);
			}			
			this.dataTable.groups = groups;
		}
	},
	/*
		Modification: instead of providing listener for each cell,
		one is provided for a whole row
	*/
	addListener: function(tr, rowIndex) {
		if (tr) {
			var listener = this.processClick.bindAsEventListener(this, rowIndex);
			Utils.DOM.Event.removeListeners(tr);
			Utils.DOM.Event.observe(tr, "click", listener);
			Utils.DOM.Event.observe(tr, "contextmenu",
			    function(event){
			    this.selectionFlag = "x";
			        if(this.selection.isSelectedId(rowIndex)) {
			        	if (this.onRowContextMenu) {
			         	   this.onRowContextMenu(event);
			        	}
			            return;
			          }
			        this.setSelection([rowIndex, rowIndex]);
			        if (this.onRowContextMenu) {
			        	this.onRowContextMenu(event)
			        }
		            this.selectionChanged(event);
			    }.bind(this));
			    
			}
	},
	
	removeListeners: function(){
		if (this.eventKeyPressAjaxListener) {
			A4J.AJAX.removeListener(this.eventKeyPressAjaxListener);
		}
		
		Event.stopObserving(document, "keydown", this.eventKeyPress);
		if (document.selection) {
			Event.stopObserving(this.gridElement, "click", this.eventResetSelection);
			Event.stopObserving(this.gridElement2, "click", this.eventResetSelection);
		}
		Event.stopObserving(document, "click", this.eventLostFocus);
		Event.stopObserving(document, "click", this.eventLostEditingFocus);
		Event.stopObserving(this.gridElement, "click", this.eventPreventLostFocus);
		Event.stopObserving(this.gridElement2, "click", this.eventPreventLostFocus);
		Event.stopObserving(this.dataTableElement, "click", this.eventPreventEditingLostFocus);
		if(this.options.selectionMode != "none") {
			
			this._getRows(this.prefix + ":n").each(function(i, o) {
				var nrows = o.rows;
				var rowCount = o.nrows;
				for(var i = 0; i < rowCount; i++) {
					Utils.DOM.Event.removeListeners(nrows[i]);	
				}
			});
			
			
		}
	},
	processPreventLostFocus: function() {
		this.inFocus = true;
		this.preventLostFocus = true;
	},
	processPreventEditingLostFocus: function() {
		this.preventEditingLostFocus = true;
	},
	processEditingLostFocus: function(e) {
		if (!this.preventEditingLostFocus && (e.data != 'editing')) {
			if (this.cellSelection.editingCell) {
				this.dataTable.onEditingComplete(e);
			}
		}else {
			this.preventEditingLostFocus = false;
		}
	},
	processLostFocus: function(e) {
		if (!this.preventLostFocus) {
			this.lostFocus();
		} else {
			this.preventLostFocus = false;
		}
	},

	lostFocus: function() {
		this.inFocus = false;
	},
    isMac:function(){
        //alert("Is Mac OS : " + /Mac/.test(navigator.appVersion))
        return /Mac/.test(navigator.appVersion);
	},
	processKeyDown: function(event) {
		//alert('processKeyDown!');
		if (this.inFocus) {
			if (this.dataTable && this.dataTable.onkeydownhandler && typeof(this.dataTable.onkeydownhandler) == 'function') {
				this.dataTable.onkeydownhandler(event);	
			}

		}
		
		var element = document.getElementById(this.prefix + ":tb");
		var rows = element.rows;
 
		if (rows && rows.length > 0) {
			if(!event.shiftKey) {
				this.shiftRow = null;
			}		
			var range, rowIndex;
			var activeRow = this.cellSelection.isAllowCellSelection()
				? this.cellSelection.rowIndex - (Richfaces.browser.isIE ? 1 : 0) : this.activeRow;
			var noDefault = false;
			var rowCount = this.rowCount - (Richfaces.browser.isIE ? 1 : 0);
			this.firstIndex = Number(rows[Richfaces.browser.isIE ? 1 : 0].id.match(/\d*$/));
            //Command key for MacOS and Ctrl for others
            var ctrlKey = this.isMac() ? event.metaKey : event.ctrlKey;
			switch (event.keyCode || event.charCode) {
				case Event.KEY_UP:
					if (this.inFocus && activeRow != null) {
						if(this.firstIndex != activeRow) {
							rowIndex = (this.rowCount + activeRow - 1) % this.rowCount;		
							while (rowIndex != this.firstIndex && this._getRows(this.prefix + ":n:" + rowIndex).css("display") == "none") {
								rowIndex = (this.rowCount + rowIndex - 1) % this.rowCount;
							}
							if (this._getRows(this.prefix + ":n:" + rowIndex).css("display") != "none") {
								if (this.cellSelection.isAllowCellSelection() || (!ctrlKey && !event.shiftKey)) {
									this.selectionFlag = "x";
									range = [rowIndex, rowIndex];
									if(this.cellSelection.isAllowCellSelection()) {
										this.cellSelection.moveUp(event,rowIndex);
									} else {
										this.setSelection(range);		
									}
								} else if (!ctrlKey && event.shiftKey
								             && this.options.selectionMode == "multi") {
									if(!this.shiftRow) {
										this.shiftRow = this.activeRow;
									}
									if(this.shiftRow >= this.activeRow) {
										this.addRowToSelection(rowIndex);						
									} else {
										this.removeRowFromSelection(activeRow);						
									}
								}
								noDefault = true;
								this.setActiveRow(rowIndex);
							}
						}
					}
					break;
				case Event.KEY_DOWN:
					if (this.inFocus && activeRow != null) {
						rowIndex = (activeRow + 1) % rowCount;		
						while (rowIndex != this.firstIndex && this._getRows(this.prefix + ":n:" + rowIndex).css("display") == "none") {
							rowIndex = (rowIndex + 1) % this.rowCount;
						}
						if(this.firstIndex != rowIndex && this.rowCount > rowIndex) {
							if (this.cellSelection.isAllowCellSelection() || (!ctrlKey && !event.shiftKey)) {
								this.selectionFlag = "x";
								range = [rowIndex, rowIndex];
								if(this.cellSelection.isAllowCellSelection()) {
									this.cellSelection.moveDown(event,rowIndex);
								} else {
									this.setSelection(range);		
								}
							} else if (!ctrlKey && event.shiftKey
							             && this.options.selectionMode == "multi") {
								if(!this.shiftRow) {
									this.shiftRow = this.activeRow;
								}
								if(this.shiftRow <= this.activeRow) {
									this.addRowToSelection(rowIndex);						
								} else {
									this.removeRowFromSelection(activeRow);						
								}
							}
							noDefault = true;
							this.setActiveRow(rowIndex);
						} 
					}
					break;
				case Event.KEY_LEFT:{
					if (this.inFocus) {
						this.cellSelection.moveLeft(event);
					}
					break;	
				}
				case Event.KEY_RIGHT:{
					if (this.inFocus) {
						this.cellSelection.moveRight(event);
					}
					break;
				}
                // Ctrl-A and Command-A for MacOS
				case 65: case 97:
					if (this.inFocus && ctrlKey) {
						this.selectionFlag = "a";
						for (var i = 0; i <  this.rowCount; i++) {
							this.addRowToSelection(i);
						}
						noDefault = true;
					}
					break;
				case Event.KEY_TAB:
					this.lostFocus();
					break;
				case Event.KEY_RETURN:
					if (this.inFocus && this.cellSelection.isAllowCellSelection() && this.cellSelection.selectedCell) {
						this.cellSelection.edit(this.cellSelection.selectedCell, event);
					}
					break;
				case Event.KEY_ESC: 
					if (this.cellSelection.isAllowCellSelection() && this.cellSelection.selectedCell) {
						this.cellSelection.cancelEditing(event);
					}
					break;
			}
			if (noDefault) {
				if (!this.cellSelection.isAllowCellSelection()) {
					this.dataTable.showRow(this.activeRow);
					this.selectionChanged(event);
				}
				if (event.preventBubble) event.preventBubble();
				Event.stop(event);
			}
		}
	},
	
	/*
		Modified method:
		Component supports three selection modes:
		none - no selection allowed
		single - only one row can be selected at a time (no ctrl or shift)
		multi - normal full-featured selection mode
	*/
	processClick: function(event, rowIndex) {
		
		if (this.options.selectionMode == "none") {
			return;
		}

		var bSingleSelection = this.options.selectionMode == "single";

		if(!event.shiftKey) {
			this.shiftRow = null;
		}		
		var range;	
		
        //Command key for MacOS and Ctrl for others
        var ctrlKey = this.isMac() ? event.metaKey : event.ctrlKey;
		if ( event.shiftKey && !ctrlKey && !bSingleSelection && !event.altKey) {
            var arr = $(this.prefix + ":n").rows[0].id.split(":");
            this.firstIndex = Number(arr[arr.length-1]);
			this.selectionFlag = "x";
			if(!this.shiftRow) {
				this.shiftRow = this.activeRow;
			}
			this.startRow = this.shiftRow;
			if (((this.startRow <= rowIndex) && (this.firstIndex <= this.startRow || rowIndex < this.firstIndex))
				|| (this.startRow > rowIndex && this.firstIndex < this.startRow && rowIndex <= this.firstIndex)) {
				this.endRow = rowIndex;
			} else {
				this.endRow = this.startRow;
				this.startRow = rowIndex;
			}
			if(this.startRow > this.endRow) { //bugfix
				//without this selection of first row with shift was broken
				var t = this.startRow;
				this.startRow = this.endRow;
				this.endRow = t;
			}
			range = [this.startRow, this.endRow];
			this.setSelection(range);
			this.selectionChanged(event);
			return;
		} else if (!event.shiftKey &&  ctrlKey && !event.altKey) {
			if (this.selection.isSelectedId(rowIndex)) {
				this.removeRowFromSelection(rowIndex);
			} else {
				if (!bSingleSelection || this.selection.size() == 0) {
					this.addRowToSelection(rowIndex);
				}
			}
		} else  if (!event.shiftKey && !ctrlKey && !event.altKey) {
			this.selectionFlag = "x";
			range = [rowIndex, rowIndex];
			this.setSelection(range);	
			//this.cellSelection.upOrDown(event,rowIndex);	
		}
		this.setActiveRow(rowIndex);
		if (event.shiftKey) {
			if (window.getSelection) {
				window.getSelection().removeAllRanges();
			} else if (document.selection) {
				document.selection.empty();
			}
		}
		this.selectionChanged(event);
	},
	
	selectionChanged: function(event) {
		$(this.inputElement).value = this.selection.inspectRanges() + this.activeRow + ";" + (this.selectionFlag ? this.selectionFlag : "") ;
		var state = this.selection.getState();			
		event.oldSelection = this.oldState;
		event.newSelection = state;
		if(this.onselectionchange && this.selection.selectionIsChanged) this.onselectionchange(event);
        this.selection.selectionIsChanged = false;
        this.oldState = state;
	},

	setShiftRow: function(event) {
		if(event.shiftKey) {
			if(!this.shiftRow) {
				this.shiftRow = this.activeRow;
			}
		} else {
			this.shiftRow = null;		
		}	
	},
	
	setSelection: function(range) {
		var l = this.selection.ranges.length;
		for(var i = l - 1;i >= 0; i--) {
			var selection = this.selection.ranges[i].indexes;
			if(selection.inspect() == range.inspect()) {
				continue;
			}
			var j = selection[0];
			for(j;j <= selection[1];j++) {
				this.removeRowFromSelection(j);
			}
		}
		if(range[0] == range[1]) {
		  this.addRowToSelection(range[0]);
		  return;
		}
		var i = range[0];
		range[1] = (range[1] + 1) % this.rowCount;
		//fix bug: https://jira.exadel.com/browse/SC-280
		if(range[1] == 0){
			range[1] = this.rowCount -1;
			this.addRowToSelection(this.rowCount-1);
		}
		while (i != range[1]) {
			this.addRowToSelection(i);
			i = (i + 1) % this.rowCount;	
		}
	},
	
	resetSelection: function(e) {
		if(e.shiftKey) {
			document.selection.empty();
		}
	},
	
	addRowToSelection: function(rowIndex) {
		this.selection.addId(rowIndex);
		var _this = this;
		this._getRows(this.prefix + ":n:" + rowIndex).each(function(i,o) {
			_this._addRowToSelection(o, rowIndex);
		});
	},
	
	_addRowToSelection: function(nElement, rowIndex) {
		nElement.addClassName("extdt-row-selected");
		nElement.addClassName("rich-sdt-row-selected");
		nElement.addClassName(this.selectedClass);
	},
	
	removeRowFromSelection: function (rowIndex) {
		this.selection.removeId(rowIndex);
		var _this = this;
		this._getRows(this.prefix + ":n:" + rowIndex).each(function(i,o) {
			_this._removeRowFromSelection(o, rowIndex);
		});
		
	},

	_removeRowFromSelection: function(nElement, rowIndex) {
		nElement.removeClassName("extdt-row-selected");
		nElement.removeClassName("rich-sdt-row-selected");
		nElement.removeClassName(this.selectedClass);
	},
	
	setActiveRow: function (rowIndex) {
		var _this = this;
		this._getRows(this.prefix + ":n:" + rowIndex).each(function(i,o) {
			_this._setActiveRow(o, rowIndex);
		});
		this.activeRow = rowIndex;
	},

	_setActiveRow: function(nElement, rowIndex) {
		if(this.activeRow != null) {
			nElement.removeClassName("extdt-row-active");
			nElement.removeClassName("rich-sdt-row-active");
			nElement.removeClassName(this.activeClass);
		}
		nElement.addClassName("extdt-row-active");
		nElement.addClassName("rich-sdt-row-active");
		nElement.addClassName(this.activeClass);
	},
	
	_getRows: function (id) {
		var r1;
		var r2;
		
		if(this.dataTable && this.dataTable.tableFB) {
			r1 = this.dataTable.tableFB.down("tbody").childElements().find(function(n) { return n.id == id});
		}
		
		if(this.dataTable && this.dataTable.tableB) {
			r2 = this.dataTable.tableB.down("tbody").childElements().find(function(n) { return n.id == id});
		}
		
		var r = [];
		
		if(r1) {
			r.push(r1);
		}
		
		if(r2) {
			r.push(r2);
		}
		
		return jQuery(r);
	},
	getRows : function() {
		return $(this.prefix + ":n").rows;
	}
});
function mouseClickH(obj){
	obj.style.backgroundColor='green';
}
