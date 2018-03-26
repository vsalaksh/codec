package com.tools.formatter.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLStringFormatter {

	/**
	 *
	 * @param unformattedXml
	 *            - XML String
	 * @return Properly formatted XML String
	 */
	public static String formatXMLString(String unformattedXml) {
		try {

			Source xmlInput = new StreamSource(new StringReader(unformattedXml));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static void main(String[] args) throws Exception {
		String book = "<?xml version=\"1.0\"?><catalog><book id=\"bk101\"><author><author1>Gambardella, Matthew</author1><author2>Lakshmanan</author2></author><title>XML Developers Guide</title><genre>Computer</genre><price>44.95</price><publish_date>2000-10-01</publish_date><description>An in-depth look at creating applications with XML.</description></book><book id=\"bk102\"><author>Ralls, Kim</author><title>Midnight Rain</title><genre>Fantasy</genre><price>5.95</price><publish_date>2000-12-16</publish_date><description>A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world.</description></book></catalog>";
		System.out.println(formatXMLString(book));
	}

}
