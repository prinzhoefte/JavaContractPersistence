package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import Kaufvertrag.presentationLayer.exceptions.DaoException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class ServiceXml
{
    public static Document getDocument(String filepath) throws DaoException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            File file = new File(filepath);
            if (file.exists()) {
                return docBuilder.parse(file);
            }
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("kaufvertrag");
            doc.appendChild(rootElement);
            return doc;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            throw new DaoException("There was an unexpected Exception in getDocument.");
        }
    }

    public static void writeToXML(Document doc, OutputStream output) throws DaoException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);

            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw new DaoException("There was an unexpected Exception in writeToXML.");
        }
        return;
    }
}
