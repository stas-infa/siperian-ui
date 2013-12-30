/* Class taken from ScrollableDataTable - unmodified */
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
		
		
		this.eventResetSelection = this.resetSelection.bindAsEventListener(this);
		Event.observe(this.gridElement, "click", this.eventResetSelection);
		Event.observe(this.gridElement2, "click", this.eventResetSelection);
		

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
		Event.stopObserving(this.gridElement, "click", this.eventResetSelection);
		Event.stopObserving(this.gridElement2, "click", this.eventResetSelection);
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
		if (event.shiftKey && !ctrlKey && !bSingleSelection && !event.altKey) {
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
		
		var activeEl = document.activeElement;
        if (activeEl) {
            var tagName = activeEl.nodeName.toLowerCase();
            if ( tagName == "textarea" || tagName == "input" ) {
            	return;
            }
        }
		
        if (window.getSelection){
			window.getSelection().removeAllRanges();
		} else if (document.selection){
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
