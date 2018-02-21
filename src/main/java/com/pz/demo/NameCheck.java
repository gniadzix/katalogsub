package com.pz.demo;

import com.pz.demo.Exceptions.SwearException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class NameCheck {
    public void ifSwear(String word) throws SwearException {
        File XMLFile = new File("src/main/resources/dictionary");
        DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbF.newDocumentBuilder();
            Document doc = db.parse(XMLFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("word");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) node;
                    if (word.equals(el.getElementsByTagName("name").item(0).getTextContent()))
                        throw new SwearException();
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}