if (!window.Richfaces) window.Richfaces = {};
Richfaces.SipComboBoxList = Class.create();
Richfaces.SipComboBoxList.prototype = {

    initialize: function (spacerImg, comboboxId, listId, parentListId, selectFirstOnUpdate, filterNewValues, classes, width, height, itemsText, onlistcall, onlistclose, fieldId, shadowId, decorationId, showDelay, hideDelay, countOfItems, isSipMode) {

        this.list = document.getElementById(listId);
        this.listParent = $(parentListId);
        this.listParentContainer = this.listParent.parentNode;
        this.iframe = null;
        this.fieldElem = document.getElementById(fieldId);
        this.itemsText = itemsText;
        LOG.debug("itemsText = " + itemsText);
        this.shadowElem = document.getElementById(shadowId);
        this.shadowId = shadowId;

        this.onlistcall = onlistcall;
        this.onlistclose = onlistclose;
        this.comboboxId = comboboxId;
        this.spacerImg = spacerImg;
        this.itemsCount = (countOfItems ? countOfItems : 0);
//		alert("isSipMode= "+isSipMode);
        this.isSiperianMode = isSipMode;
        if (this.onlistcall) {
            this.listParent.observe("rich:onlistcall", this.onlistcall);
        }

        if (this.onlistclose) {
            this.listParent.observe("rich:onlistclose", this.onlistclose);
        }

        this.selectFirstOnUpdate = selectFirstOnUpdate;
        this.filterNewValues = filterNewValues;

        this.isList = false;

        this.defaultRowsAmount = 15;

        this.selectedItem = null;
        this.activeItem = null;

        this.showDelay = showDelay;
        this.hideDelay = hideDelay;
        this.classes = classes;
        this.width = width;
        if (!width) {
            this.width = 240;
        }
        this.height = height;
        this.initDimensions();
        this.scrollElements = null;
        this.eventOnScroll = this.eventOnScroll.bindAsEventListener(this);
        this.isListOpen = false;
        this.isOpenedByClick = false;
        this.defaultValue = this.fieldElem.value;
        this.defaultValue2 = "";

    },

    initDimensions: function () {
        this.listParent.style.visibility = "hidden";
        this.listParent.style.display = "";

        var el = this.listParent;
        this.LAYOUT_BORDER_V = Richfaces.getBorderWidth(el, "tb");
        this.LAYOUT_BORDER_H = Richfaces.getBorderWidth(el, "lr");
        this.LAYOUT_PADDING_V = Richfaces.getPaddingWidth(el, "tb");
        this.LAYOUT_PADDING_H = Richfaces.getPaddingWidth(el, "lr");

        this.listParent.style.display = "none";
        this.listParent.style.visibility = "visible";
    },

    createDefaultList: function () {
        var items = new Array();
        for (var i = 0; i < this.itemsText.length; i++) {
            items.push(this.createItem(this.itemsText[i], this.classes.item.normal));
        }
        this.createNewList(items);
    },

    getItems: function () {
        return this.getList().childNodes;
    },

    showWithDelay: function () {
        this.show();
        /*setTimeout(function(){
         this.show();
         }.bind(this), this.showDelay);*/
    },

    moveAndResize: function(){
        var pos = Position.cumulativeOffset(this.fieldElem);
        this.fieldDimensions = {};
        this.fieldDimensions.left = pos[0];
        this.fieldDimensions.top = pos[1];
        this.fieldDimensions.height = this.fieldElem.parentNode.offsetHeight;
        this.setSize();
        this.setPosition(this.fieldDimensions.top, this.fieldDimensions.left, this.fieldDimensions.height);
    },

    show: function () {
        this.defaultValue = this.fieldElem.value;
        var pos = Position.cumulativeOffset(this.fieldElem);
        this.fieldDimensions = {};
        this.fieldDimensions.left = pos[0];
        this.fieldDimensions.top = pos[1];

        this.fieldDimensions.height = this.fieldElem.parentNode.offsetHeight;

        this.listParent.style.visibility = "hidden";
        this.listParent.style.display = "";
        this.setSize();
        this.listParent.style.display = "none";
        this.listParent.style.visibility = "visible";

        //attach list to the document body
        this.injectListToBody(this.listParent);

        this.setPosition(this.fieldDimensions.top, this.fieldDimensions.left, this.fieldDimensions.height);
        LOG.debug("siperianMode = " + this.isSiperianMode);
        if (!this.isOpenedByClick && this.selectedItem /*&& !this.isSiperianMode*/) {
            //was created new item list, so necessary to recreate selectedItem
            LOG.debug("show() - doSelectItem -" + this.selectedItem);
            this.doSelectItem(this.findItemByDOMNode(this.selectedItem));
        }


        var items = this.getItems();
        if (items.length != 0) {
            LOG.debug("show() - items length = " + items.length)
            if (this.iframe) {
                Element.show(this.iframe);
            }
            this.listParent.style.display = "";
            if (!this.isOpenedByClick && this.selectFirstOnUpdate && this.isSiperianMode) {
                LOG.debug("show - ths.selectFirstOnUpdate");
                if (this.selectedItem) {
                    this.doActiveItem(this.selectedItem);
                } else {
                    this.doActiveItem(items[0]);
                }
            }
        }

        this.listParent.fire("rich:onlistcall", {});
        Richfaces.removeScrollEventHandlers(this.scrollElements, this.eventOnScroll);
        this.scrollElements = Richfaces.setupScrollEventHandlers(this.listParentContainer.parentNode, this.eventOnScroll);
        this.isListOpen = true;
    },

    injectListToBody: function (listElement) {
        if (!this.listInjected) {
            var parent = listElement.parentNode;
            var child = document.body.insertBefore(parent.removeChild(listElement), null);
            if (Richfaces.browser.isIE6 && this.iframe) {
                document.body.insertBefore(parent.removeChild(this.iframe), child);
            }
            this.listInjected = true;
        }
    },

    outjectListFromBody: function (parentElement, listElement) {
        if (this.listInjected) {
            var child = parentElement.appendChild(document.body.removeChild(listElement));
            if (Richfaces.browser.isIE6 && this.iframe) {
                parentElement.insertBefore(document.body.removeChild(this.iframe), child);
            }
            this.listInjected = false;
        }
    },

    hideWithDelay: function () {
        /*setTimeout(function(){
         this.hide();
         }.bind(this), this.hideDelay);*/
        this.hide();
        this.listParent.fire("rich:onlistclose", {});
    },

    hide: function () {
        Richfaces.removeScrollEventHandlers(this.scrollElements, this.eventOnScroll);
        this.outjectListFromBody(this.listParentContainer, this.listParent);
        this.resetState();
        if (this.iframe) {
            Element.hide(this.iframe);
            //this.iframe.style.display= "none";
        }

        var component = this.listParent.parentNode;
        component.style.position = "static";
        component.style.zIndex = 0;

        //this.listParent.style.display = "none";
        Element.hide(this.listParent);
        this.isListOpen = false;
        this.isOpenedByClick = false;
//		this.defaultValue2="";
    },

    eventOnScroll: function (e) {
        this.hideWithDelay();
    },

    visible: function () {
        return this.listParent.visible();
    },

    setSize: function () {
        var height = this.height;

        var currentItemsHeight;
        var rowsAmount;


        var item = jQuery(this.list).find('span:not(:empty):first');
        var actItPars = 0;
        if (item) {
            var itemHeight = 0;
            if(item[0]){
                itemHeight = item[0].offsetHeight;
            }
            rowsAmount = this.getItems().length;
            currentItemsHeight = itemHeight * rowsAmount;
            if (this.height) {
                if (parseInt(this.height) > currentItemsHeight) {
                    height = currentItemsHeight;
                }
            } else {
                if (rowsAmount < this.defaultRowsAmount) {
                    height = currentItemsHeight;
                } else {
                    height = itemHeight * this.defaultRowsAmount;
                }
            }
            if (Prototype.Browser.IE) {
                height = parseInt(height) + this.LAYOUT_BORDER_V + this.LAYOUT_PADDING_V;
                this.listParent.style.maxHeight = parseInt(height) + 'px';

            }
            height = parseInt(height) + "px";
            this.getList().style.height = height;


            if (!this.shadowElem) {
                this.shadowElem = document.getElementById(this.shadowId);
            }
            if (this.shadowElem) {
                if (!Richfaces.browser.isIE6) {
                    // shadow offset
                    this.shadowElem.style.width = (parseInt(this.width) + 10) + "px";
                    this.shadowElem.style.height = parseInt(height) > 0 ?(parseInt(height) + 10) + "px": "0";
                } else {
                    this.shadowElem.style.visibility = "hidden";
                }
            }
            if (this.iframe) {
                this.iframe.style.height = height;
            }
            this.setWidth(this.width);
        }
    },

    setWidth: function (width) {
        if (!width) {
            width = this.getCurrentWidth();
        }
        var positionElem = this.listParent.childNodes[1];
        var combobox = this.listParent.parentNode;
        var correction = parseInt(width) - Richfaces.getBorderWidth(positionElem.firstChild, "lr") - Richfaces.getPaddingWidth(positionElem.firstChild, "lr") + "px";
        this.getList().style.width = correction;
        if (this.iframe) {
            this.iframe.style.width = correction;
        }
    },

    getCurrentWidth: function () {
        return $(this.comboboxId).firstChild.offsetWidth;
    },

    setPosition: function (fieldTop, fieldLeft, fieldHeight) {
        var component = this.listParent.parentNode;
        component.style.zIndex = 2;

        var docHeight = Richfaces.getDocumentHeight();
        var comBottom = fieldTop + fieldHeight;

        var listHeight = parseInt(this.getList().style.height);
        if (this.getList().parentNode) {
            listHeight += Richfaces.getBorderWidth(this.getList().parentNode, "tb");
        }

        var topPosition = comBottom;

        var showPoint = fieldHeight;
        if (parseInt(listHeight) > (docHeight - comBottom)) {
            if (topPosition > (docHeight - comBottom)) {
                showPoint = -parseInt(listHeight);

            }
        }

        this.clonePosition(this.listParent, this.fieldElem, showPoint);
        if (this.iframe) {
            this.clonePosition(this.iframe, this.fieldElem, showPoint);
        }
    },

    scrolling: function (event) {
        var increment;
        var scrollElem = this.getList();
        var listTop = Richfaces.SipComboBoxList.getElemXY(scrollElem).top;
        var scrollTop = scrollElem.scrollTop;
        var itemTop = Richfaces.SipComboBoxList.getElemXY(this.activeItem).top;

        if ((event.keyCode == Event.KEY_UP) || (event.keyCode == 33)) {
            increment = (itemTop - scrollTop) - listTop;
            if (increment < 0) {
                scrollElem.scrollTop += increment;
            }
        } else if ((event.keyCode == Event.KEY_DOWN) || (event.keyCode == 34)) {
            var itemBottom = itemTop + this.activeItem.offsetHeight;
            var increment = (itemBottom - scrollTop) - (listTop + scrollElem.clientHeight);
            if (increment > 0) {
                scrollElem.scrollTop += increment;
            }
        }
        Event.stop(event);
    },

    scrollingUpToItem: function (item) {
        var scrollElem = this.getList();
        var increment = (Richfaces.SipComboBoxList.getElemXY(item).top - scrollElem.scrollTop) - Richfaces.SipComboBoxList.getElemXY(scrollElem).top;
        scrollElem.scrollTop += increment;
    },

    /* items library*/
    doActiveItem: function (item) {
        if (this.activeItem) {
            this.doNormalItem(this.activeItem);
        }

        this.activeItem = item;

        this.changeItem(item, this.classes.item.selected);
    },

    doNormalItem: function (item) {
        this.activeItem = null;
        this.changeItem(item, this.classes.item.normal);
    },

    doSelectItem: function (item) {
        this.selectedItem = item;
    },

    changeItem: function (item, className) {
        item.className = className;
    },
    moveNotActiveItem: function (event, currentValue) {
        LOG.debug("moveNotActiveItem - event - " + event.keyCode);
        var curItems = this.itemsText;
        LOG.debug("this.selectedItem =" + this.selectedItem);
        LOG.debug("currentItems length " + curItems.length);
        if (curItems != null && curItems.length != 0) {
            if (event.keyCode == Event.KEY_UP) {
                LOG.debug("before do active item - ");
                this.moveItem(event, this.createSelectedItemAsDomElement(currentValue));
            } else if (event.keyCode == Event.KEY_DOWN) {
                this.moveItem(event, this.createSelectedItemAsDomElement(currentValue));
            }
        } else if (currentValue == '' && curItems != null && curItems.length != 0) {
            this.selectedItem = this.createSelectedItemAsDomElement(curItems[0]);
            this.fieldElem.value = curItems[0];
//				this.moveItem(event,  this.createSelectedItemAsDomElement(this.fieldElem.value));
        }

    },
    moveItem: function (event, item) {
        LOG.debug("moveItem - item=" + item.innerHTML);
        var arrayLength = this.itemsText.length;
        for (var i = 0; i < arrayLength; i++) {
            if (this.itemsText[i] == item.innerHTML) {
                var realOffset = 0;
                if (event.keyCode == Event.KEY_UP) {
                    realOffset = i - 1;
                    if (realOffset < 0) {
                        realOffset = 0;
                    }
                    this.fieldElem.value = this.itemsText[realOffset];
                } else if (event.keyCode == Event.KEY_DOWN) {
                    realOffset = i + 1;
                    if (realOffset >= arrayLength) {
                        realOffset = arrayLength - 1;
                    }
                    this.fieldElem.value = this.itemsText[realOffset];
                }
                this.selectedItem = this.createSelectedItemAsDomElement(this.fieldElem.value)
                this.doActiveItem(this.selectedItem);
                LOG.debug("After selected item = " + this.selectedItem.innerHTML);
            }
        }
    },
    createSelectedItemAsDomElement: function (value) {
        LOG.debug("createSelectedItemAsDomElement with " + value + "passed");
        var selectedItem = document.createElement("span");
        selectedItem.innerHTML = value;
        selectedItem.setAttribute("class", this.classes.item.normal);
        return selectedItem;
    },
    getSelected: function () {
        var sel = jQuery(this.list).find('.sip-combobox-item-selected');
        return sel.length == 1 ? sel.get(0) : null;
    },
    moveActiveItem: function (event) {
        var item = this.activeItem;
        LOG.debug("moveActiveItem ");
        if (event.keyCode == Event.KEY_UP) {
            LOG.debug("moveActiveItem - KEY_UP,current selected item = " + this.selectedItem);
            if (!this.activeItem) {
                LOG.debug("moveActiveItem - !this.activeItem =" + this.activeItem);
                if (!this.selectFirstOnUpdate) {
                    var curItems = this.getItems();
                    if (curItems != null && curItems.length != 0) {
                        this.doActiveItem(curItems[curItems.length - 1]);
                        this.scrollingUpToItem(curItems[curItems.length - 1]);
                    }
                }
                return;
            }
            var prevItem = item.previousSibling;
            LOG.debug("prevItem = " + prevItem);
            if (prevItem) {
                LOG.debug("prevItem succ = " + prevItem);
                this.itemsRearrangement(item, prevItem);
            }
        } else if (event.keyCode == Event.KEY_DOWN) {
            LOG.debug("moveActiveItem - KEY_DOWN,this.activeItem= " + this.activeItem);
            if (!this.activeItem) {
                if (!this.selectFirstOnUpdate) {
                    var curItems = this.getItems();
                    LOG.debug("moveActiveItem - KEY_DOWN,curItems.length = " + curItems.length);
                    if (curItems != null && curItems.length != 0) {
                        this.doActiveItem(curItems[0]);
                        this.scrollingUpToItem(curItems[0]);
                    }
                }
                return;
            }
            var nextItem = item.nextSibling;
            LOG.debug("moveActiveItem - KEY_DOWN,nextItem= " + nextItem);
            if (nextItem) {
                LOG.debug("moveActiveItem - KEY_DOWN,nextItem= " + nextItem + " before this.itemsRearrangement");
                this.itemsRearrangement(item, nextItem);
            } else if (this.isSiperianMode) {
                var curItems = this.getItems();
                LOG.debug("moveActiveItem - KEY_DOWN,curItems.length = " + curItems.length);
                if (curItems != null && curItems.length != 0) {
                    this.doActiveItem(curItems[0]);
                    this.scrollingUpToItem(curItems[0]);
                }
            }
        }
        this.scrolling(event);
    },

    itemsRearrangement: function (item, newItem) {
        this.doActiveItem(newItem);
    },

    resetState: function () {
        if (this.filterNewValues) {
            var tempList = this.list.cloneNode(false);
            this.listParent.childNodes[1].firstChild.replaceChild(tempList, this.list);
            this.list = $(tempList.id);
        } else {
            if (this.activeItem) {
                this.doNormalItem(this.activeItem);
            }
        }
        this.activeItem = null;
        this.isList = false;
    },

    dataFilter2: function (text, excludeItemAsText) {
        this.createNewList(this.dataFilterAndRemoveFirstFoundItem(text, excludeItemAsText));
    },
    dataFilter: function (text) {
        var items = this.getFilteredItems(text);
        var len = (items != null ? items.length : 0);
        this.createNewList(items);
        return len;

    },
    dataFilterAndRemoveFirstFoundItem: function (text, excludeItemAsText) {
        var items = new Array();
        LOG.debug("getFilteredItems : - itemsText = " + this.itemsText);
        for (var i = 0; i < this.itemsText.length; i++) {
            var itText = this.itemsText[i];
            if (itText.toLowerCase() == excludeItemAsText.toLowerCase()) {
                continue;
            }
            if (itText.substr(0, text.length).toLowerCase() == text.toLowerCase()) { //FIXME: to optimaize
                items.push(this.crateHighlightItem(itText, text, this.classes.item.normal));
            }
        }
//		items.shift();
        return items;

    },
    getFilteredItems: function (text) {
        var items = new Array();
        LOG.debug("getFilteredItems : - itemsText = " + this.itemsText);
        for (var i = 0; i < this.itemsText.length; i++) {
            var itText = this.itemsText[i];
            if (itText.substr(0, text.length).toLowerCase() == text.toLowerCase()) { //FIXME: to optimaize
                items.push(this.createItem(itText, this.classes.item.normal));
            }
        }
//		items.shift();
        return items;
    },

    findItemByDOMNode: function (node) {
        var substr = node.innerHTML.unescapeHTML();
        return this.findItemBySubstr(substr);
    },

    findItemBySubstr: function (substr) {
        var items = this.getItems();
        for (var i = 0; i < items.length; i++) {
            var item = items[i]
            var itText = item.innerHTML.unescapeHTML();
            if (itText.substr(0, substr.length).toLowerCase() == substr.toLowerCase()) { //FIXME: to optimaize
                item.innerHTML = itText != '' ? itText : '<br/>';
                LOG.debug("Before return " + item.innerHTML);
                return item;
            }
        }
    },

    createNewList: function (items) {
        //FIX for FF
        if (this.selectedItem) {
            var node = this.selectedItem;
        }
        this.getList().innerHTML = items.join("");
        //was created new item list, so necessary to recreate selectedItem

        if (this.selectedItem) {
            var item = this.findItemByDOMNode(node);
            if (item) {
                this.doSelectItem(item);
            }
        }
    },

    createItem: function (text, className) {
        var escapedText = text.escapeHTML();
        if ('' == escapedText) {
            escapedText = '<br/>';
        }
        return "<span class=\"" + className + "\">" + escapedText + "</span>";
    },

    crateHighlightItem: function (text, highligh, className) {
        var escapedText = text.escapeHTML();
        var otherText = escapedText.substring(highligh.length, escapedText.length);
        var hl = escapedText.substring(0, highligh.length);
        return "<span class=\"" + className + "\"><span style=\"font-weight:bold\">" + hl + "</span>" + otherText + "</span>";
    },

    createIframe: function (parentElem, width, comboboxId, classes) {
        var iframe = document.createElement("iframe");

        iframe.id = "iframe" + comboboxId;

        iframe.style.display = "none";
        iframe.style.position = "absolute";
        iframe.frameBorder = "0";
        iframe.scrolling = "no";
        iframe.src = "javascript:''";

        iframe.style.width = width;


        iframe.className = classes;


        parentElem.insertBefore(iframe, parentElem.firstChild);
        this.iframe = document.getElementById(iframe.id);
    },

    PX_REGEX: /px$/,

    parseToPx: function (value) {
        var v = value.strip();
        if (this.PX_REGEX.test(v)) {
            try {
                return parseFloat(v.replace(this.PX_REGEX, ""));
            } catch (e) {

            }
        }

        return NaN;
    },


    createImgForShadovDiv: function (srcImg, widht, height, alt, style) {
        var img = document.createElement("img");
        img.setAttribute('src', srcImg);
        img.setAttribute('width', widht);
        img.setAttribute('height', height);
        img.setAttribute('alt', alt);
        img.setAttribute('style', style);
        return img;
    },
    getList: function () {
        if(!this.list){
            var shadowDiv = document.getElementById(this.shadowId);
            if(this.itemsCount < 3){
                shadowDiv.setAttribute('style',"display:none;");
            }
        }
        return this.list;
    },
    clonePosition: function (target, source, vOffset) {
        var jqt = jQuery(target);
        var jqs = jQuery(source);
        var so = jqs.offset();

        var hidden = (jqt.css('display') == 'none');
        var oldVisibility;

        if (hidden) {
            oldVisibility = jqt.css('visibility');
            jqt.css('visibility', 'hidden').css('display', '');
        }

        var left = this.parseToPx(jqt.css('left'));
        if (isNaN(left)) {
            left = 0;
            jqt.css('left', '0px');
        }

        var top = this.parseToPx(jqt.css('top'));
        if (isNaN(top)) {
            top = 0;
            jqt.css('top', '0px');
        }

        var to = jqt.offset();

        if (hidden) {
            jqt.css('display', 'none').css('visibility', oldVisibility);
        }

        // set position
        jqt.css({
            left: (so.left - to.left + left) + 'px',
            top: (so.top - to.top + top + vOffset) + 'px'
        });
    }
}

Richfaces.SipComboBoxList.getElemXY = function (elem) {

    var x = elem.offsetLeft;
    var y = elem.offsetTop;


    for (var parent = elem.offsetParent; parent; parent = parent.offsetParent) {
        x += parent.offsetLeft;
        y += parent.offsetTop;
    }

    return {left: x, top: y};
}
