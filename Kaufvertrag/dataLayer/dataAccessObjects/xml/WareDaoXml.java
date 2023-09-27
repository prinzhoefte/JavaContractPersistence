package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.Formatter;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.presentationLayer.exceptions.DaoException;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WareDaoXml implements IDao<IWare, Long> {

    private static final String FILEPATH = "vertraege.xml";

    // Create a new "ware" element and append it to the root
    @Override
    public void create(IWare objectToInsert) throws DaoException {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            // Get the root element
            Element root = doc.getDocumentElement();

            // Check if ware already exists
            NodeList wareList = root.getElementsByTagName("ware");
            for (int i = 0; i < wareList.getLength(); i++) {
                Element wareElement = (Element) wareList.item(i);
                String wareId = wareElement.getAttribute("id");
                if (wareId.equals(String.valueOf(objectToInsert.getId()))) {
                    throw new DaoException("Ware already exists");
                }
            }

            // Create a new "ware" element
            Element wareElement = doc.createElement("ware");
            
            // Create and set attributes for the "ware" element
            wareElement.setAttribute("id", String.valueOf(objectToInsert.getId()));
            wareElement.setAttribute("bezeichnung", objectToInsert.getBezeichnung());
            wareElement.setAttribute("beschreibung", objectToInsert.getBeschreibung());
            wareElement.setAttribute("preis", objectToInsert.getPreis());
            wareElement.setAttribute("besonderheiten", Formatter.ListToString(objectToInsert.getBesonderheiten()));
            wareElement.setAttribute("maengel", Formatter.ListToString(objectToInsert.getMaengel()));
    
            // Append the "ware" element to the root
            root.appendChild(wareElement);
    
            // Write the updated document back to the XML file
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("Error in WareDaoXml#create(IWare objectToInsert).");
        }
    }

    // Read a "ware" element from the XML file by its ID 
    @Override
    public IWare read(Long id) {
        Document doc;
        Ware ware = null;
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList wareList = root.getElementsByTagName("ware");
            
            for (int i = 0; i < wareList.getLength(); i++) {
                Element wareElement = (Element) wareList.item(i);
                String wareId = wareElement.getAttribute("id");
                
                if (wareId.equals(id.toString())) {

                    String bezeichnung = wareElement.getAttribute("bezeichnung");
                    String beschreibung = wareElement.getAttribute("beschreibung");
                    String preis = wareElement.getAttribute("preis");
                    String besonderheiten = wareElement.getAttribute("besonderheiten");
                    String maengel = wareElement.getAttribute("maengel");

                    ware = new Ware(bezeichnung, preis);

                    ware.setBeschreibung(beschreibung);
                    ware.setBesonderheiten(besonderheiten);
                    ware.setMaengel(maengel);

                    break; // Found the matching ware, exit the loop
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return ware;
    }

    // Read all "ware" elements from the XML file
    @Override
    public List<IWare> readAll() {
        Document doc;
        List<IWare> warenListe = new ArrayList<>();
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList wareElements = root.getElementsByTagName("ware");
            
            for (int i = 0; i < wareElements.getLength(); i++) {

                Element wareElement = (Element) wareElements.item(i);
                String bezeichnung = wareElement.getAttribute("bezeichnung");
                String beschreibung = wareElement.getAttribute("beschreibung");
                String preis = wareElement.getAttribute("preis");
                String besonderheiten = wareElement.getAttribute("besonderheiten");
                String maengel = wareElement.getAttribute("maengel");

                Ware ware = new Ware(bezeichnung, preis);

                ware.setBeschreibung(beschreibung);
                ware.setBesonderheiten(besonderheiten);
                ware.setMaengel(maengel);

                warenListe.add(ware);
            }
        } catch (DaoException e) {
            System.out.println("Error in WareDaoXml#readAll().");
        }
        return warenListe;
    }

    // Update a "ware" element by its id 
    @Override
    public void update(IWare objectToUpdate) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList wareElements = root.getElementsByTagName("ware");
            
            for (int i = 0; i < wareElements.getLength(); i++) {
                Element wareElement = (Element) wareElements.item(i);
                String id = wareElement.getAttribute("id");
                
                if (id.equals(String.valueOf(objectToUpdate.getId()))) {

                    // Update attributes
                    wareElement.setAttribute("bezeichnung", objectToUpdate.getBezeichnung());
                    wareElement.setAttribute("beschreibung", objectToUpdate.getBeschreibung());
                    wareElement.setAttribute("preis", objectToUpdate.getPreis());
                    wareElement.setAttribute("besonderheiten", Formatter.ListToString(objectToUpdate.getBesonderheiten()));
                    wareElement.setAttribute("maengel", Formatter.ListToString(objectToUpdate.getMaengel()));

                    break; // Updated the matching ware, exit the loop
                }
            }
            
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("Error in WareDaoXml#update(IWare objectToUpdate).");
        }
    }

    // Delete a "ware" element by its id 
    @Override
    public void delete(Long id) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList wareElements = root.getElementsByTagName("ware");
            
            for (int i = 0; i < wareElements.getLength(); i++) {
                Element wareElement = (Element) wareElements.item(i);
                String wareId = wareElement.getAttribute("id");
                
                if (wareId.equals(id.toString())) {
                    root.removeChild(wareElement);
                    break; // Removed the matching ware, exit the loop
                }
            }
            
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("Error in WareDaoXml#delete(Long id).");
        }
    }
    
    @Override
    public IWare create() {
        return new Ware(null, null);
    }
}
