/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.awt.geom.Line2D;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
*
* @author Alan Lee 
*/

public class XMLEn {

	public static boolean encodeXML(NewJFrame a, String filePath) {

		ArrayList<LineObject> ListofLines = a.getLineContainer();
		ArrayList<SkeletonObject> structures = a.structures;

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Sketch");
			doc.appendChild(rootElement);
			
			// segment elements
			Element Segments = doc.createElement("Vectors");
			rootElement.appendChild(Segments);

			// Line elements
			Element Lines = doc.createElement("Lines");
			Segments.appendChild(Lines);

			for (Line2D e : ListofLines) {

				// Line elements
				Element LineSeg = doc.createElement("LineSeg");
				Lines.appendChild(LineSeg);
				// line elements
				LineSeg.setAttribute("FX", e.getX1() + "");
				LineSeg.setAttribute("FY", e.getY1() + "");
				LineSeg.setAttribute("SX", e.getX2() + "");
				LineSeg.setAttribute("SY", e.getY2() + "");

			}

			// Dimension elements
			Element Dimension = doc.createElement("Dimension");
			rootElement.appendChild(Dimension);

			// Dimension Attrbs.
			Dimension.setAttribute("Height", a.originalFileDimension.height + "");
			Dimension.setAttribute("Width", a.originalFileDimension.width + "");

			for (SkeletonObject struc : structures){
				// Add structure object
				Element skeletonObject = doc.createElement("Structure");
				skeletonObject.setAttribute("Name", struc.getName());
				skeletonObject.setAttribute("Area", struc.calculateArea() + "");
				rootElement.appendChild(skeletonObject);
				ArrayList<LineObject> lines = struc.getLines();
				// segment elements
				Element SegmentsO = doc.createElement("Vectors");
				skeletonObject.appendChild(SegmentsO);

				// Line elements
				Element LinesO = doc.createElement("Lines");
				SegmentsO.appendChild(LinesO);
				for (LineObject line : lines){
					// Line elements
					Element LineSeg = doc.createElement("LineSeg");
					LinesO.appendChild(LineSeg);
					// line elements
					LineSeg.setAttribute("FX", line.getX1() + "");
					LineSeg.setAttribute("FY", line.getY1() + "");
					LineSeg.setAttribute("SX", line.getX2() + "");
					LineSeg.setAttribute("SY", line.getY2() + "");
				}
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath + ".xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return true;
	}

}
