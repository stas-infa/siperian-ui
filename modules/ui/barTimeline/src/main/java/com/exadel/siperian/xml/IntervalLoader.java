package com.exadel.siperian.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.exadel.siperian.model.Interval;
import com.exadel.siperian.model.impl.IntervalImpl;

public class IntervalLoader {

	public static List<Interval> loadIntervals() throws Exception {
		List<Interval> intervals = new ArrayList<Interval>();
		
		InputStream xmlFile = IntervalLoader.class.getResourceAsStream(
				"/com/exadel/siperian/renderkit/html/xml/intervals.xml");
		
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
				.newInstance();
		Document document = null;

		DocumentBuilder builder = documentFactory.newDocumentBuilder();
		document = builder.parse(xmlFile);
		Element intervalsElement = (Element) document.getElementsByTagName(
				"intervals").item(0);

		if (intervalsElement != null) {
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
		}
		
		
		return intervals;

	}
	
}
