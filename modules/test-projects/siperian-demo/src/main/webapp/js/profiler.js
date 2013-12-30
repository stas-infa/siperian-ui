JsProfiler = new Object();

JsProfiler.traceIndex = -1;

JsProfiler.getTracePrefix = function() {

    var prefix = ""
    for(var i = 0; i < JsProfiler.traceIndex; i++) {
        prefix += "&nbsp;&nbsp;";
    }
    if(JsProfiler.traceIndex > 0) {
        prefix += "'-"
    }
    return prefix;
}

JsProfiler.profile = function(label, base) {
    //increment trace index
    JsProfiler.traceIndex++;
        
    var args = $A(arguments);
    args.splice(0, 2);

    var start = new Date().getTime();
    var result = base.apply(this, args);
    var end = new Date().getTime();

    //if((end - start) > 1)
        jstracer.write(JsProfiler.getTracePrefix() + label + ': ' + (end - start) + ' ms');

    //decrement trace index
    JsProfiler.traceIndex--;
    
    return result;
}

JsProfiler.profileFunction = function(name) {
    try {
        eval(name + " = " + name + ".wrap(JsProfiler.profile.curry('" + name + "'));");
    } catch(e) {
        jstracer.write("Error trying to profile function " + name,
                        jstracer.MessageType.ERROR);
    }
}

JsProfiler.profileClass = function(ClassName) {
    var classPrototype = ClassName + ".prototype";
      try {
        var prototypeObj = eval(classPrototype);
        for(var func in prototypeObj) {
            var fullName = classPrototype + "." + func;
             try {
                eval(fullName + " = " + fullName  + ".wrap(JsProfiler.profile.curry('" + fullName + "'));");
                } catch(e){
                    jstracer.write("Error trying to profile function " + name + " in class " + ClassName,
                                    jstracer.MessageType.ERROR);
                }
        }
        }catch(e){}
}

//JsProfiler.profileClass("ExtendedDataTable.DataTable.header");
JsProfiler.profileClass("ExtendedDataTable.DataTable");
JsProfiler.profileClass("ExtendedDataTable.Selection");
JsProfiler.profileClass("ExtendedDataTable.SelectionManager");
JsProfiler.profileClass("ExtendedDataTable.Range");

JsProfiler.profileClass("TabPanel");

JsProfiler.profileFunction("TabPanel.findByType");
JsProfiler.profileFunction("TabPanel.isTabActive");

//JsProfiler.profileFunction("Utils.execOnLoad");