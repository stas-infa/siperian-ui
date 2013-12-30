<!doctype html public "-//w3c//dtd html 4.0 transitional//en">

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://siperian.exadel.com/sip" prefix="sip" %>
<html>
	<body>
		<f:view>
			<h:form>
				<table border="1" cellpadding="0" cellspacing="0">
					<tr><td>
						<sip:verticalTabPanel tabsCount="5" 
											  value="#{bean.tabIndex}"
											  reRender="curTab"/>
					</td>
					<td style="valign: center; padding-left: 50px; padding-right: 50px;">
						<a4j:include viewId="view.jsp"></a4j:include>
					</td>
					</tr>
				</table><br/>
				<h:outputText id="curTab" value="Current tab : #{bean.tabIndex}"></h:outputText>
			</h:form>
		</f:view>
	</body>	
</html>  
