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
        	.redHighlight{
        		color:red;
        	}
        	
        	.greenHighlight{
        		color:green;
        	}   	
        	
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
    </head>

<body>
    <h:panelGrid style="table-layout: fixed;" columns="2" id="mainPanel" columnClasses="leftColumn, rightColumn">
    <rich:spacer></rich:spacer>
    <rich:spacer></rich:spacer>
    <rich:panel id="leftPanel">
    <h:form id="form1">
        <extdt:extendedDataTable 
        		id="demoTablePaging"     
        		binding="#{extendedDataTableTaskBean.extendedDataTable}"
                value="#{extendedDataTableTaskBean.dataModel}"
                var="task"
                style="margin: 0 auto;"
                rows="20"
                width="500px"
                height="500px"
                sortMode="single"
                selectedClass="dataTableSelectedRow"
                footerClass="demo-footer"          
                rowKeyVar="rkvar"
                hoverRowClass="redBackground">
            <rich:column id="id" 
                    headerClass="dataTableHeader"
                    width="25%"
                    label="id">
                <f:facet name="header">
                    <h:outputText value="id" />
                </f:facet>
                <h:outputText value="#{task.id}" />
            </rich:column>  
                          
            <rich:column id="priority" 
                    headerClass="dataTableHeader"
                    width="25%"
                    label="priority"
                    sortable="true"
                    sortBy="#{task.priority}"
                    filterBy="#{task.priority}"
                    filterEvent="onkeyup">                    
                <f:facet name="header">
                    <h:outputText value="priority" />
                </f:facet>
                <h:outputText value="#{task.priority}" />
            </rich:column>
            <rich:column id="subject"
                    headerClass="dataTableHeader"
                    width="25%"
                    label="subject"
					sortable="true"
                    sortBy="#{task.subject}"
                    filterBy="#{task.subject}"
                    filterEvent="onkeyup">
                <f:facet name="header">
                    <h:outputText value="subject" />
                </f:facet>
                <h:outputText value="#{task.subject}" />
            </rich:column>
            <rich:column id="status"
                    headerClass="dataTableHeader"
                    width="50%"
                    label="status">
                <f:facet name="header">
                    <h:outputText value="status" />
                </f:facet>
                <h:outputText value="#{task.status}" styleClass="#{extendedDataTableTaskBean.highlightColors[task.status]}"/>
            </rich:column>
            <f:facet name="footer">
            	<rich:datascroller 
        				pagesVar="pages"
        				
        				pageIndexVar="pageIndex"
            			style="width: 500px"
            			id="datascroller"            
            			for="demoTablePaging">
            		<f:facet name="pages">
                		<h:panelGroup>
                			<h:outputText value="Page "/>
                    		<h:inputText id="pagerInput" value="#{pageIndex}" style="width:20px"/>
                    		<h:outputText value=" of  #{pages} "/>
                    		<h:outputLink value="#" onclick="Event.fire(this, 'rich:datascroller:onscroll', {'page': $('form1:demoTablePaging:pagerInput').value});">
	                    		<h:outputText value="Go"/>
    	                	</h:outputLink>
        	        	</h:panelGroup>
            		</f:facet>
        		</rich:datascroller>            
            </f:facet>
        </extdt:extendedDataTable>
        </h:form>
    </rich:panel>       
    </h:panelGrid>
</body>
</html>
</f:view>