package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.presentationLayer.exceptions.DaoException;
import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VertragspartnerDaoXml implements IDao<IVertragspartner, String>
{
    private static final String FILEPATH = "vertraege.xml";

    @Override
    public void create(IVertragspartner objectToInsert) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement(); // Get the root element

            // Create a new "vertragspartner" element
            Element vertragspartnerElement = doc.createElement("vertragspartner");
            
            // Create and set attributes for the "vertragspartner" element
            vertragspartnerElement.setAttribute("id", objectToInsert.getAusweisNr());
            vertragspartnerElement.setAttribute("vorname", objectToInsert.getVorname());
            vertragspartnerElement.setAttribute("nachname", objectToInsert.getNachname());
    
            // Append the "vertragspartner" element to the root
            root.appendChild(vertragspartnerElement);
    
            // Write the updated document back to the XML file
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("Error in VertragspartnerDaoXml#create(IVertragspartner objectToInsert).");
        }
    }

    // Read a "Vertragspartner" by its ID
    @Override
    public IVertragspartner read(String id) {
        Document doc;
        Vertragspartner vertragspartner = null;
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList vertragspartnerList = root.getElementsByTagName("vertragspartner");
            
            for (int i = 0; i < vertragspartnerList.getLength(); i++) {
                Element vertragspartnerElement = (Element) vertragspartnerList.item(i);
                String partnerId = vertragspartnerElement.getAttribute("id");
                
                if (partnerId.equals(id)) {
                    String vorname = vertragspartnerElement.getAttribute("vorname");
                    String nachname = vertragspartnerElement.getAttribute("nachname");
                    vertragspartner = new Vertragspartner(vorname, nachname);
                    vertragspartner.setAusweisNr(partnerId);
                    break; // Found the matching vertragspartner, exit the loop
                }
            }
        } catch (DaoException e) {
            System.out.println("Error in VertragspartnerDaoXml#read(String id).");
        }
        return vertragspartner;
    }

    // List all "Vertragspartner"
    @Override
    public List<IVertragspartner> readAll() {
        Document doc;
        List<IVertragspartner> vertragspartnerListe = new ArrayList<>();
        try {
            doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList vertragspartnerElements = root.getElementsByTagName("vertragspartner");
            
            for (int i = 0; i < vertragspartnerElements.getLength(); i++) {
                Element vertragspartnerElement = (Element) vertragspartnerElements.item(i);
                String vorname = vertragspartnerElement.getAttribute("vorname");
                String nachname = vertragspartnerElement.getAttribute("nachname");
                vertragspartnerListe.add(new Vertragspartner(vorname, nachname));
            }
        } catch (DaoException e) {
            System.out.println("Error in VertragspartnerDaoXml#readAll().");
            return null;
        }
        return vertragspartnerListe;
    }

    // Update a "Vertragspartner" by id (AusweisNr)
    @Override
    public void update(IVertragspartner objectToUpdate) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList vertragspartnerElements = root.getElementsByTagName("vertragspartner");
            
            for (int i = 0; i < vertragspartnerElements.getLength(); i++) {
                Element vertragspartnerElement = (Element) vertragspartnerElements.item(i);
                String id = vertragspartnerElement.getAttribute("id");
                
                if (id.equals(objectToUpdate.getAusweisNr())) {
                    // Update "vorname" and "nachname" attributes
                    vertragspartnerElement.setAttribute("vorname", objectToUpdate.getVorname());
                    vertragspartnerElement.setAttribute("nachname", objectToUpdate.getNachname());
                    break; // Updated the matching vertragspartner, exit the loop
                }
            }
            
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("Error in VertragspartnerDaoXml#update(IVertragspartner objectToUpdate).");
        }
    }

    // Delete a "Vertragspartner" by id (AusweisNr)
    @Override
    public void delete(String id) {
        try {
            Document doc = ServiceXml.getDocument(FILEPATH);
            Element root = doc.getDocumentElement();
            NodeList vertragspartnerElements = root.getElementsByTagName("vertragspartner");
            
            for (int i = 0; i < vertragspartnerElements.getLength(); i++) {
                Element vertragspartnerElement = (Element) vertragspartnerElements.item(i);
                String partnerId = vertragspartnerElement.getAttribute("id");
                
                if (partnerId.equals(id)) {
                    root.removeChild(vertragspartnerElement);
                    break; // Removed the matching vertragspartner, exit the loop
                }
            }
            
            ServiceXml.writeToXML(doc, new FileOutputStream(FILEPATH));
        } catch (IOException | DaoException ex) {
            System.out.println("Error in VertragspartnerDaoXml#delete(String id)");
        }
    }

    // Dont need that method but it is required by the interface
    @Override
    public IVertragspartner create() {
        throw new UnsupportedOperationException("Unimplemented method 'create()'");
    }
}
