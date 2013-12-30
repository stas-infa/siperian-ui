function sipSelectTable(tblRoot) {
    var c = $(tblRoot.id);
    if (c && c.component && c.component.selectionManager) {
        c.component.selectionManager.inFocus = true;
        // We set focus
        if (c.component.selectionManager.activeRow!=null){
            var rowIndex = c.component.selectionManager.activeRow;
            if (rowIndex==-1){
                var list = tblRoot.getElementsByTagName('a');
                if (list.length > 0) {
                    list[0].focus();
                }
            }
            else{
                if (ClientUILib.isIE)rowIndex++;
                var rows = c.component.selectionManager.getRows();
                if (rowIndex>rows.length)rowIndex=0;
                rows[rowIndex].click();
            }
        }
        else{
            var list = tblRoot.getElementsByTagName('a');
            if (list.length > 0) {
                list[0].focus();
            }
        }
    }
}
