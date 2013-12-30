<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://siperian.exadel.com/t" prefix="t"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<f:view>
		<h:form>
		    <t:toolBar id="normal" height="34" itemSeparator="line" width="300px">
            	        <t:toolBarGroup>
                            <h:graphicImage id="new" value="/images/toolbar_new_ic.png" />
                            <h:outputLabel value="New" for="new" />
                        </t:toolBarGroup>
                        <t:toolBarGroup>                       
                            <h:graphicImage id="edit" value="/images/toolbar_edit_ic.png" />
                            <h:outputLabel value="Edit" for="edit" />
                        </t:toolBarGroup>
                        <t:toolBarGroup>                       
                            <h:graphicImage id="delete" value="/images/toolbar_delete_ic.png" />
                            <h:outputLabel value="Delete" for="delete" />
                        </t:toolBarGroup>
            </t:toolBar>
            <t:toolBar id="disabled" height="34" itemSeparator="line" width="300px" disabled="true">
            	        <t:toolBarGroup>
                            <h:graphicImage value="/images/toolbar_new_ic.png" />
                            <h:commandLink value="New"/>
                        </t:toolBarGroup>
                        <t:toolBarGroup>                      
                            <h:graphicImage value="/images/toolbar_edit_ic.png" />
                            <h:commandLink value="Edit"/>
                        </t:toolBarGroup>
                        <t:toolBarGroup>
                            <h:graphicImage value="/images/toolbar_delete_ic.png" />
                            <h:commandLink value="Delete"/>
                        </t:toolBarGroup>
            </t:toolBar>
            </h:form>
		</f:view>
	</body>	
</html>  
