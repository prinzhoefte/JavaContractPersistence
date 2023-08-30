package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.presentationLayer.exceptions.DaoException;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VertragspartnerDaoXml implements IDao<IVertragspartner, Long>
{
    private static final String FILEPATH = "dataLayer/dataAccessObjects/xml/vertragspartner.xml";

    @Override
    public IVertragspartner create() {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("vertragspartner");
            Element nodeID = doc.createElement("id");
            nodeID.setIdAttribute("", true);

            Element vorname = doc.createElement("vorname");
            vorname.setNodeValue("");
            nodeID.appendChild(vorname);

            Element nachname = doc.createElement("nachname");
            nachname.setNodeValue("");
            nodeID.appendChild(nachname);

            root.appendChild(nodeID);
            Vertragspartner vertragspartner = new Vertragspartner(vorname.getNodeValue(), nachname.getNodeValue());
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
            return vertragspartner;
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#create().");
        }
        return null;
    }

    @Override
    public void create(IVertragspartner objectToInsert) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("vertragspartner");
            Element nodeID = doc.createElement("id");
            //get the id here pls.
            nodeID.setIdAttribute("", true);

            Element vorname = doc.createElement("vorname");
            vorname.setNodeValue(objectToInsert.getVorname());
            nodeID.appendChild(vorname);

            Element nachname = doc.createElement("nachname");
            nachname.setNodeValue(objectToInsert.getNachname());
            nodeID.appendChild(nachname);

            root.appendChild(nodeID);
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#create(IVertragspartner objectToInsert).");
        }
    }

    @Override
    public IVertragspartner read(Long id) {
        Document doc;
        Vertragspartner vertragspartner = null;
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("vertragspartner");
            Element nodeID = root.getOwnerDocument().getElementById(id.toString());
            vertragspartner = new Vertragspartner(nodeID.getElementsByTagName("vorname").item(0).getNodeValue(), nodeID.getElementsByTagName("nachname").item(0).getNodeValue());    
        } catch (DaoException e) {
            System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#read(Long id).");
        }
        return vertragspartner;
    }

    @Override
    public List<IVertragspartner> readAll() {
        Document doc;
        List<IVertragspartner> vertragspartnerListe = null;
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("vertragspartner");
            vertragspartnerListe = new ArrayList<>();
            NodeList vertragspartner = root.getElementsByTagName("id");
            for (int i = 0; i < vertragspartner.getLength(); i++)
            {
                NodeList childs = vertragspartner.item(i).getChildNodes();
                vertragspartnerListe.add(new Vertragspartner(childs.item(0).getNodeValue(), childs.item(1).getNodeValue()));
            }
        } catch (DaoException e) {
            System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#readAll().");
            return null;
        }
        return vertragspartnerListe;
    }

    @Override
    public void update(IVertragspartner objectToUpdate) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("vertragspartner");
            //get the id here pls.
            Element nodeID = root.getOwnerDocument().getElementById("");

            Node vorname = nodeID.getElementsByTagName("vorname").item(0);
            vorname.setNodeValue(objectToUpdate.getVorname());

            Node nachname = nodeID.getElementsByTagName("nachname").item(0);
            nachname.setNodeValue(objectToUpdate.getNachname());

            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#update(IVertragspartner objectToUpdate).");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("vertragspartner");
            Element nodeID = root.getOwnerDocument().getElementById(id.toString());
            root.removeChild(nodeID);
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in VertragspartnerDaoXml#delete(Long id).");
        }
    }
}
