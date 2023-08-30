package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.presentationLayer.exceptions.DaoException;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WareDaoXml implements IDao<IWare, Long>
{
    private static final String FILEPATH = "dataLayer/dataAccessObjects/xml/ware.xml";

    @Override
    public IWare create() {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("ware");
            Element nodeID = doc.createElement("id");
            nodeID.setIdAttribute("", true);

            Element bezeichnung = doc.createElement("bezeichnung");
            bezeichnung.setNodeValue("");
            nodeID.appendChild(bezeichnung);

            Element preis = doc.createElement("preis");
            preis.setNodeValue("");
            nodeID.appendChild(preis);

            root.appendChild(nodeID);
            Ware ware = new Ware(bezeichnung.getNodeValue(), preis.getNodeValue());
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
            return ware;
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in WareDaoXml#create().");
        }
        return null;
    }

    @Override
    public void create(IWare objectToInsert) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("ware");
            Element nodeID = doc.createElement("id");
            nodeID.setIdAttribute("", true);

            Element bezeichnung = doc.createElement("bezeichnung");
            bezeichnung.setNodeValue(objectToInsert.getBeschreibung());
            nodeID.appendChild(bezeichnung);

            Element preis = doc.createElement("preis");
            preis.setNodeValue(objectToInsert.getPreis());
            nodeID.appendChild(preis);

            root.appendChild(nodeID);
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in WareDaoXml#create(IWare objectToInsert).");
        }
    }

    @Override
    public IWare read(Long id) {
        Document doc;
        Ware ware = null;
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("ware");
            Element nodeID = root.getOwnerDocument().getElementById(id.toString());
            ware = new Ware(nodeID.getElementsByTagName("bezeichnung").item(0).getNodeValue(), nodeID.getElementsByTagName("preis").item(0).getNodeValue());    
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return ware;
    }

    @Override
    public List<IWare> readAll() {
        Document doc;
        List<IWare> warenListe = new ArrayList<>();
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("ware");
            NodeList waren = root.getElementsByTagName("id");
            for (int i = 0; i < waren.getLength(); i++)
            {
                NodeList childs = waren.item(i).getChildNodes();
                warenListe.add(new Ware(childs.item(0).getNodeValue(), childs.item(1).getNodeValue()));
            }
        } catch (DaoException e) {
            System.out.println("There was an unexpected Exception in WareDaoXml#readAll().");
        }
        return warenListe;
    }

    @Override
    public void update(IWare objectToUpdate) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getElementById("ware");
            Element nodeID = root.getOwnerDocument().getElementById("");

            Node bezeichnung = nodeID.getElementsByTagName("bezeichnung").item(0);
            bezeichnung.setNodeValue(objectToUpdate.getBezeichnung());

            Node preis = nodeID.getElementsByTagName("preis").item(0);
            preis.setNodeValue(objectToUpdate.getPreis());

            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("There was an unexpected Exception in WareDaoXml#update(IWare objectToUpdate).");
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
            System.out.println("There was an unexpected Exception in WareDaoXml#delete(Long id).");
        }
    }
}
