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
                if (this.selectionManager) {
                    this.update(true);
                }

			     if(options.hoverRowClass) {

                     var hoverHandler = function (e) {
                         Event.observe(e, Richfaces.browser.isIE ? 'mouseenter' : 'mouseover', function () {
                             Element.addClassName(table.component.tableB.rows[e.rowIndex], options.hoverRowClass);
                             Element.addClassName(table.component.tableFB.rows[e.rowIndex], options.hoverRowClass);
                         });
                         Event.observe(e, Richfaces.browser.isIE ? 'mouseleave' : 'mouseout', function () {
                             Element.removeClassName(table.component.tableB.rows[e.rowIndex], options.hoverRowClass);
                             Element.removeClassName(table.component.tableFB.rows[e.rowIndex], options.hoverRowClass);
                         });
                     };

                     $A(table.component.tableB.rows).each(hoverHandler);
                     $A(table.component.tableFB.rows).each(hoverHandler);
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

    setColumnWidth: function (columnIndex, newWidth) {
        if (columnIndex >= this.getColumnsNumber) {
            return false;
        }

        if ( Richfaces.browser.isIE && !newWidth) {
            newWidth = null
        }
        this.getColumns()[columnIndex].width = newWidth;
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


		var headerRowHeight = tableH.find('tr.rich-extdt-subheader:first th').height();
		if (this.options.headerHeight && this.options.headerHeight>0){
            headerRowHeight = this.options.headerHeight;
		}
		var tableFhRowHeight = tableFH.find('tr.rich-extdt-subheader:first th').height();

		if (tableFhRowHeight > headerRowHeight) {
			headerRowHeight = tableFhRowHeight;
		}
		
		tableH.find('tr.rich-extdt-subheader:first th').css({height: headerRowHeight + 'px'});
		tableFH.find('tr.rich-extdt-subheader:first th').css({height: headerRowHeight + 'px'});
		
		if (headerRowsCount > 1) {
			var filterRowHeight = tableH.find('tr.rich-extdt-subheader:last th').height();
			var tableFhRowHeight = tableFH.find('tr.rich-extdt-subheader:last th').height(); 
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
	jQuery(this.tableFB).css('table-layout', 'fixed');
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
