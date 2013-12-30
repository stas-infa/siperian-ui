package com.exadel.demo;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exadel.siperian.model.BarTimelineDataModel;
import com.exadel.siperian.model.Interval;
import com.exadel.siperian.model.Item;
import com.exadel.siperian.model.ItemType;
import com.exadel.siperian.model.SwimLine;
import com.exadel.siperian.model.impl.IntervalImpl;
import com.exadel.siperian.model.impl.ItemImpl;
import com.exadel.siperian.model.impl.ItemTypeImpl;
import com.exadel.siperian.model.impl.SwimLineImpl;

public class BarTimelineDataModelImpl implements BarTimelineDataModel{

	private static final long serialVersionUID = -812308792461332254L;
		
	private List<SwimLine> swimLines = new ArrayList<SwimLine>();
	
	private List<Item> items = new ArrayList<Item>();
	
	private List<ItemType> itemTypes = new ArrayList<ItemType>();;
	
	private List<Interval> intervals = new ArrayList<Interval>();;
	
	
	public BarTimelineDataModelImpl() {
		loadDataXml();
	}

	public List<Item> getItems() {
		return items;
	}

	public List<ItemType> getItemTypes() {
		return itemTypes;
	}

	public List<SwimLine> getSwimLines() {
		return swimLines;
	}

	public List<Interval> getIntervals() {
		return intervals;
	}
	
	public void loadDataXml() {
		
		try {
			
			File xmlFile = new File(BarTimelineDataModelImpl.class.getResource("data.xml").getFile());
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = documentFactory.newDocumentBuilder();
			
			Document document = builder.parse(xmlFile);
				
			Element itemsNodeElement = (Element) document.getElementsByTagName(
					"items").item(0);
			NodeList itemsChildNodeList = itemsNodeElement.getChildNodes();
			for (int j = 0; j < itemsChildNodeList.getLength(); j++) {
				if (itemsChildNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
					Element itemElem = (Element) itemsChildNodeList.item(j);
					ItemImpl item = new ItemImpl();
					item.setId(itemElem.getAttribute("id"));
					item.setType(itemElem.getAttribute("type"));
					item.setSwimLineId(itemElem.getAttribute("swimLineId"));
					item.setTooltip(itemElem.getElementsByTagName("tooltip")
							.item(0).getChildNodes().item(0).getNodeValue());
					String startDate = itemElem.getAttribute("startDate");
					if (startDate != null && !startDate.isEmpty()) {
						item.setStartDate(new Date(Long.parseLong(startDate)));
					}

					String endDate = itemElem.getAttribute("endDate");
					if (endDate != null && !endDate.isEmpty()) {
						item.setEndDate(new Date(Long.parseLong(endDate)));
					}
					items.add(item);
				}
			}

			Element itemTypesNodeElement = (Element) document
					.getElementsByTagName("itemTypes").item(0);
			NodeList itemsTypesChildNodeList = itemTypesNodeElement
					.getChildNodes();
			for (int j = 0; j < itemsTypesChildNodeList.getLength(); j++) {
				if (itemsTypesChildNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
					Element itemTypeElem = (Element) itemsTypesChildNodeList
							.item(j);
					ItemTypeImpl itemType = new ItemTypeImpl();
					itemType.setId(itemTypeElem.getAttribute("id"));
					itemType.setName(itemTypeElem.getAttribute("name"));
					itemType.setStyleClass(itemTypeElem
							.getAttribute("styleClass"));
					itemType.setSelectedStyleClass(itemTypeElem
							.getAttribute("selectedStyleClass"));
					itemType.setHoverStyleClass(itemTypeElem
							.getAttribute("hoverStyleClass"));
					itemType.setHighlightStyleClass(itemTypeElem
							.getAttribute("highlightStyleClass"));
					itemType.setTooltipStyleClass(itemTypeElem
							.getAttribute("tooltipStyleClass"));
					itemTypes.add(itemType);
				}
			}

			Element intervalsElement = (Element) document.getElementsByTagName(
					"intervals").item(0);
			NodeList intervalsNodeList = intervalsElement.getChildNodes();
			for (int j = 0; j < intervalsNodeList.getLength(); j++) {
				if (intervalsNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
					Element interNode = (Element) intervalsNodeList.item(j);
					IntervalImpl interval = new IntervalImpl();
					interval.setLength(interNode.getAttribute("length"));
					interval.setDatePattern(interNode
							.getAttribute("datePattern"));
					interval.setMarker(interNode.getAttribute("marker"));
					intervals.add(interval);
				}
			}

			Element swimLinesElement = (Element) document.getElementsByTagName(
					"swimLines").item(0);
			NodeList swimLinesNideList = swimLinesElement.getChildNodes();
			for (int j = 0; j < swimLinesNideList.getLength(); j++) {
				if (swimLinesNideList.item(j).getNodeType() == Node.ELEMENT_NODE) {
					Element swimLineElement = (Element) swimLinesNideList
							.item(j);
					SwimLineImpl swimLine = new SwimLineImpl();
					swimLine.setId(swimLineElement.getAttribute("id"));
					swimLine.setName(swimLineElement.getAttribute("name"));
					swimLine.setStyleClass(swimLineElement
							.getAttribute("styleClass"));
					swimLines.add(swimLine);
				}
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] args) {
		BarTimelineDataModelImpl model = new BarTimelineDataModelImpl();
		model.loadDataXml();
	 	System.out.println("end");
	}
	

}
