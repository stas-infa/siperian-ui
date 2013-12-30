<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>  
    <head>
        <title>
            <h:outputText value="#{msg['demo.testPageTitle']}">
            </h:outputText>
        </title>

    </head>

<body>
    <h:form>
	    <a4j:commandButton value="#{msg['table.back']}"
	        action="index"
	    >
	    </a4j:commandButton>
    </h:form>
</body>
</html>
</f:view>