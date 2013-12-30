<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://siperian.exadel.com/extDataTable" prefix="extdt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <html>  
    <head>
        <title>
            <h:outputText value="#{msg['demo.title']}">
            </h:outputText>
        </title>
        <style type="text/css">
        	
        	.redBackground {
        		background-color: red;
        	}
        
            .leftColumn {
                width: 50%;
                height: 100%;
            }
            
            .rich-filter-input {
                width: 80%;
            }
            
            .rightColumn {
                width: 50%;
                height: 100%;
            }
            
            table {
                cell-padding: 10;
                cell-spacing: 10;
            }
            
            #mainPanel {
                width: 100%;
                height: 100%;
            }
        
            #leftPanel {
                width: 100%;
                height: 100%;
            }
            
            #rightPanel {
                width: 100%;
                height: 100%;
            }
        </style>
       <script type="text/javascript">

        function setCaretToEnd (e) {

            var control = $((e.target ? e.target : e.srcElement).id);

            if (control.createTextRange) {

                var range = control.createTextRange();

                range.collapse(false);

                range.select();

            }

            else if (control.setSelectionRange) {

                control.focus();

                var length = control.value.length;

                control.setSelectionRange(length, length);

            }

            control.selectionStart = control.selectionEnd = control.value.length;

        } 



    </script>

     
    </head>

<body>
    <h:panelGrid style="table-layout: fixed;" columns="2" id="mainPanel" columnClasses="leftColumn, rightColumn">
    <rich:spacer></rich:spacer>
    <rich:spacer></rich:spacer>
    <rich:panel id="leftPanel">
    <h:form id="form1">
        <extdt:extendedDataTable id="demoTable"
        		binding="#{extendedDataTableBean.extendedDataTable}"
                value="#{extendedDataTableBean.dataModel}"
                var="patient"
                style="margin: 0 auto;"
                rows="#{extendedDataTableControlBean.rowsNumber}"
                width="#{extendedDataTableControlBean.width}"
                height="#{extendedDataTableControlBean.height}"
                selectedClass="dataTableSelectedRow"
                footerClass="demo-footer"
                sortMode="#{extendedDataTableControlBean.sortMode}"
                selectionMode="#{extendedDataTableControlBean.selectionMode}"
                selection="#{extendedDataTableBean.selection}"
                rowKeyVar="rkvar"
                tableState="#{extendedDataTableBean.tableState}"
                scrollable="#{extendedDataTableControlBean.scrollable}"
                groupingColumn="firstName"
                hoverRowClass="redBackground">
            <rich:column id="firstName"
                    headerClass="dataTableHeader"
                    width="25%"
                    label="#{msg['patient.firstName']}"
                    sortable="true"
                    sortBy="#{patient.firstName}"
                    sortOrder="DESCENDING"
                    filterBy="#{patient.firstName}"
                    filterEvent="onkeyup">
                 <f:attribute name="_ignoreDupResponses" value="true" />
                 <f:attribute name="_requestDelay" value="500" />
                 <f:attribute name="_oncomplete" value="setCaretToEnd(event);" />
                <f:facet name="header">
                    <h:outputText value="#{msg['patient.firstName']}" />
                </f:facet>
                <h:outputText id="text" value="#{patient.firstName}" >
                    <rich:toolTip for="text" >
                        <h:outputText value="sasasasasasa" />
                    </rich:toolTip>
                </h:outputText>
            </rich:column>
            <rich:column id="lastName"
                    headerClass="dataTableHeader"
                    width="25%"
                    label="#{msg['patient.lastName']}"
                    sortable="true"
                    sortBy="#{patient.lastName}"
                    filterBy="#{patient.lastName}"
                    filterEvent="onkeyup">
                 <f:attribute name="_ignoreDupResponses" value="true" />
                 <f:attribute name="_requestDelay" value="500" />
                 <f:attribute name="_oncomplete" value="setCaretToEnd(event);" />
                <f:facet name="header">
                    <h:outputText value="#{msg['patient.lastName']}" />
                </f:facet>
                <h:outputText value="#{patient.lastName}" />
            </rich:column>
            <rich:column id="admissionDate"
                    headerClass="dataTableHeader"
                    width="50%"
                    label="#{msg['patient.admissionDate']}"
                    sortable="true"
                    sortBy="#{patient.admissionDate}">
                <f:facet name="header">
                    <h:outputText value="#{msg['patient.admissionDate']}" />
                </f:facet>
                <h:outputText value="#{patient.admissionDate}" />
            </rich:column>
    <a4j:support event="onselectionchange" action="#{extendedDataTableBean.takeSelection}" reRender="selectedPatients">
    </a4j:support>
        </extdt:extendedDataTable>
        <rich:datascroller
            style="width: #{extendedDataTableControlBean.width}"
            rendered="#{extendedDataTableControlBean.paginated}"
            for="demoTable">
        </rich:datascroller>
        </h:form>
    </rich:panel>
    <rich:panel id="rightPanel">
    <h:form>
        <h:panelGrid columns="2">
            <h:outputLabel value="#{msg['table.width']}" for="tableWidthInput" />
            <h:inputText 
                id="tableWidthInput" 
                value="#{extendedDataTableControlBean.width}" />
            <h:outputLabel value="#{msg['table.height']}" for="tableHeightInput" />
            <h:inputText 
                id="tableHeightInput" 
                value="#{extendedDataTableControlBean.height}" />
            <h:outputLabel value="#{msg['table.patientsNumber']}" for="tablePatientsNumberInput" />
            <h:inputText 
                id="tablePatientsNumberInput" 
                value="#{extendedDataTableBean.patientsNumber}" />
            <h:outputLabel value="#{msg['table.sortMode']}" for="tableSortModeSelect" />
            <h:selectOneMenu
                    id="tableSortModeSelect"
                    value="#{extendedDataTableControlBean.sortMode}"
                >
                <f:selectItems value="#{extendedDataTableControlBean.sortModeSelectItems}"/>
            </h:selectOneMenu>
            <h:outputLabel value="#{msg['table.selectionMode']}" for="tableSelectionModeSelect" />
            <h:selectOneMenu
                    id="tableSelectionModeSelect"
                    value="#{extendedDataTableControlBean.selectionMode}"
                >
                <f:selectItems value="#{extendedDataTableControlBean.selectionModeSelectItems}"/>
            </h:selectOneMenu>
            <h:outputLabel value="#{msg['table.paginated']}" for="paginatedChecxbox" />
            <h:selectBooleanCheckbox 
                id="paginatedCheckbox"
                value="#{extendedDataTableControlBean.paginated}" />
                
            <h:outputLabel value="#{msg['table.scrollable']}" for="scrollableChecxbox" />
            <h:selectBooleanCheckbox 
                id="scrollableCheckbox"
                value="#{extendedDataTableControlBean.scrollable}" />
            <h:outputLabel value="#{msg['table.rowsNumber']}" for="tableRowsNumberInput" />
            <h:inputText 
                id="tableRowsNumberInput" 
                value="#{extendedDataTableControlBean.rowsNumber}" />
        </h:panelGrid>
        <a4j:commandButton value="#{msg['table.update']}" reRender="mainPanel">
        </a4j:commandButton>
        <a4j:commandButton value="#{msg['table.testComponentState']}"
                action="#{extendedDataTableBean.clearState}"
            >
        </a4j:commandButton><br /><br />
        <rich:panel>
        	<f:facet name="header">
        		<h:outputText value="Columns visibility" />
        	</f:facet>
        	<a4j:region>
        		<h:selectBooleanCheckbox value="#{extendedDataTableBean.firtstNameColumnVisible}" id="fnVisible">
        			<h:outputLabel value="#{msg['patient.firstName']}" for="fnVisible" />
        		</h:selectBooleanCheckbox>
        		<h:selectBooleanCheckbox value="#{extendedDataTableBean.lastNameColumnVisible}" id="lnVisible">
        			<h:outputLabel value="#{msg['patient.lastName']}" for="lnVisible" />
        		</h:selectBooleanCheckbox>
        		<h:selectBooleanCheckbox value="#{extendedDataTableBean.dateColumnVisible}" id="dateVisible">
        			<h:outputLabel value="#{msg['patient.admissionDate']}" for="dateVisible" />
        		</h:selectBooleanCheckbox>
        		<br /><br />
        		<a4j:commandButton value="#{msg['table.apply.btn']}" 
        			action="#{extendedDataTableBean.applyColumnsVisibility}" reRender="mainPanel" />
        	</a4j:region>
        </rich:panel>
        
        <rich:panel>
        	<f:facet name="header">
        		<h:outputText value="Columns order" />
        	</f:facet>
        		<h:selectOneMenu value="#{extendedDataTableBean.columnsOrder}">
        			<f:selectItems value="#{extendedDataTableBean.possibleOrders}" />
        		</h:selectOneMenu>
        		<br /><br />
        		<a4j:commandButton value="#{msg['table.apply.btn']}" 
        			action="#{extendedDataTableBean.applyColumnsOrder}" reRender="mainPanel" />
        </rich:panel>
        
        <rich:spacer height="5px" width="100%">
        </rich:spacer>
        <rich:dataTable id="selectedPatients" value="#{extendedDataTableBean.selectedItems}" var="selectedPatient">
            <f:facet name="header">
                <h:outputText value="#{msg['table.selectedPatients']}" />
            </f:facet>
            <rich:column id="selectedFirstName"
                    headerClass="dataTableHeader"
                    label="#{msg['patient.firstName']}">
                <f:facet name="header">
                    <h:outputText value="#{msg['patient.firstName']}" />
                </f:facet>
                <h:outputText value="#{selectedPatient.firstName}" />
            </rich:column>
            <rich:column id="selectedLastName"
                    headerClass="dataTableHeader"
                    label="#{msg['patient.lastName']}">
                <f:facet name="header">
                    <h:outputText value="#{msg['patient.lastName']}" />
                </f:facet>
                <h:outputText value="#{selectedPatient.lastName}" />
            </rich:column>
        </rich:dataTable>
        <rich:messages>
        </rich:messages>
        
         <br />
        <br />
        <br />
        <h:outputLink value="cellContextMenuExample.jsf">Cell level context menu example</h:outputLink>
         <br />
        <h:outputLink value="rowContextMenuExample.jsf">Row level context menu example</h:outputLink>
        <br/>
        <h:outputLink value="cellFormattingExample.jsf">Cell Formatting Example</h:outputLink>
    </h:form>
    </rich:panel>
    </h:panelGrid>
</body>
</html>
</f:view>