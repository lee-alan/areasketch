/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javaapplication2.NewJFrame.StartPoint;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEEAL
 */
public class XMLDe {

	public static void main(String path[]) {
		System.out.println("main");
		try {

			File fXmlFile = new File("C:\\Users\\gurramra\\Desktop\\file.xml");
			//Thread.sleep(2000);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Structure");

			System.out.println("----------------------------");
			NewJFrame newFrame = new NewJFrame();
			System.out.println("3:" + System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println("4:" + System.currentTimeMillis());
			//newFrame.new StartPoint(250, 250);
			for (int temp = 0; temp < nList.getLength(); temp++) {

				System.out.println(nList.getLength());

				Node nNode = nList.item(temp);
				String className = nNode.getAttributes().getNamedItem("Name").getTextContent();

				System.out.println("\nCurrent Element :" + className);
				// Get All Line Objects
				List<LineObject> lines = new ArrayList<LineObject>();
				NodeList vectors = nNode.getFirstChild().getChildNodes();
				for (int i = 0; i < vectors.getLength(); i++){
					Node currentNode = vectors.item(i);
					
					String tagName = currentNode.getNodeName();
					if (tagName.equals("Lines")){
						NodeList lineSeg = currentNode.getChildNodes();
						for (int j = 0; j < lineSeg.getLength(); j++){
							Element eElement = (Element) lineSeg.item(j);
							lines.add(new LineObject(Float.parseFloat(eElement.getAttribute("FX")), Float.parseFloat(eElement.getAttribute("FY")), Float.parseFloat(eElement.getAttribute("SX")), Float.parseFloat(eElement.getAttribute("SY"))));
							System.out.println(
									"Line x Coordinate : " + eElement.getAttribute("SY") + " , " + eElement.getAttribute("FY")
											+ " , " + eElement.getAttribute("FX") + " , " + eElement.getAttribute("SX"));
						}
					}
					
				}
				
				System.out.println(System.currentTimeMillis());
				SkeletonObject object = (SkeletonObject) Class.forName("javaapplication2." + className)
						.getConstructor(NewJFrame.class, StartPoint.class).newInstance(newFrame, newFrame.new StartPoint(lines.get(0).x1, lines.get(0).y1));
				//Thread.sleep(10000);
				object.drawFromXml(lines);
				newFrame.g2.drawLine(0, 0, 400, 400);
				newFrame.structures.add(object);
				newFrame.DrawAll();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void openXML(String path) {

		try {

			File fXmlFile = new File(path);
			//Thread.sleep(2000);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Structure");

			System.out.println("----------------------------");
			NewJFrame newFrame = new NewJFrame();
			Thread.sleep(500);
			//newFrame.new StartPoint(250, 250);
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				String className = nNode.getAttributes().getNamedItem("Name").getTextContent();

				System.out.println("\nCurrent Element :" + className);
				// Get All Line Objects
				List<LineObject> lines = new ArrayList<LineObject>();
				NodeList vectors = nNode.getFirstChild().getChildNodes();
				for (int i = 0; i < vectors.getLength(); i++){
					Node currentNode = vectors.item(i);
					
					String tagName = currentNode.getNodeName();
					if (tagName.equals("Lines")){
						NodeList lineSeg = currentNode.getChildNodes();
						for (int j = 0; j < lineSeg.getLength(); j++){
							Element eElement = (Element) lineSeg.item(j);
							lines.add(new LineObject(Float.parseFloat(eElement.getAttribute("FX")), Float.parseFloat(eElement.getAttribute("FY")), Float.parseFloat(eElement.getAttribute("SX")), Float.parseFloat(eElement.getAttribute("SY"))));
							System.out.println(
									"Line x Coordinate : " + eElement.getAttribute("SY") + " , " + eElement.getAttribute("FY")
											+ " , " + eElement.getAttribute("FX") + " , " + eElement.getAttribute("SX"));
						}
					}
					
				}
				SkeletonObject object = (SkeletonObject) Class.forName("javaapplication2." + className)
						.getConstructor(NewJFrame.class, StartPoint.class).newInstance(newFrame, newFrame.new StartPoint(lines.get(0).x1, lines.get(0).y1));
				object.drawFromXml(lines);
				System.out.println("DONE");
				newFrame.structures.add(object);
				newFrame.DrawAll();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void decodeXML() {
		try {
			File fXmlFile = new File("C:\\Users\\gurramra\\Desktop\\file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
