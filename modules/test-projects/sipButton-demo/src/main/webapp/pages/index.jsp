<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://siperian.exadel.com/b" prefix="b"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
		<f:view>
			<h:form>
				<table border="1px" cellspacing="0" cellpadding="3">
					<thead style="font-size: 13px; color: green; font-style: italic;">
						<tr>
							<th>Action</th>
							<th>ActionListener</th>
							<th>Value Submit</th>
						</tr>
					</thead>
					<tr>
						<td><h:outputText id="action" value="#{bean.action}"/></td>
						<td><h:outputText id="listener" value="#{bean.actionListener}"/></td>
						<td><h:inputText id="value" value="#{bean.value}"/></td>
					</tr>
				</table>
				<h:panelGrid columns="2" border="1">
					<b:button value="Siperian Button" actionListener="#{bean.actionListener}" action="#{bean.action}"/>
					<h:outputText value="JSF Siperian Button" />
					<b:button value="Siperian Button" disabled="true" actionListener="#{bean.actionListener}" action="#{bean.action}" />
					<h:outputText value="Disabled JSF Siperian Button" />
					<b:ajaxButton value="Siperian Ajax Button" actionListener="#{bean.actionListener}" action="#{bean.action}" reRender="action,listener,value" />
					<h:outputText value="Ajax Siperian Button" />
					<b:ajaxButton value="Siperian Ajax Button" disabled="true" actionListener="#{bean.actionListener}" action="#{bean.action}" reRender="action,listener,value" />
					<h:outputText value="Disabled Ajax Siperian Button" />
				</h:panelGrid>	
			</h:form>
		</f:view>
	</body>	
</html>  
