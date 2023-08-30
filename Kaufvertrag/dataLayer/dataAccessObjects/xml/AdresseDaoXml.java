package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Kaufvertrag.dataLayer.dataAccessObjects.xml.XMLManager.*;

public class AdresseDaoXml implements IDao<IAdresse, Long>
{
  /************************************************************************************/
  /************************************************************************************/
  /** xml sollte wie folgt aussehen:                                                 **/
  /** xml-datei                                                                      **/
  /** root-node adresse                                                              **/
  /**     node id                                                                    **/
  /**         node strasse                                                           **/
  /**         node hausnummer                                                        **/
  /**         node plz                                                               **/
  /**         node ort                                                               **/
  /**     node id2                                                                   **/
  /**         node strasse2                                                          **/
  /**         node hausnummer2                                                       **/
  /**         node plz2                                                              **/
  /**         node ort2                                                              **/
  /************************************************************************************/
  /************************************************************************************/
  private static final String FILEPATH = "Berufsschule_Lernfeld05_08_Projects/src/main/java/Kaufvertrag/Kaufvertrag/XML/Adresse.xml";

  @Override
  public IAdresse create()
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      Element root = doc.getElementById("adresse");
      Element nodeID = doc.createElement("id");
      //get the id here pls.
      nodeID.setIdAttribute("", true);

      Element strasse = doc.createElement("strasse");
      strasse.setNodeValue("");
      nodeID.appendChild(strasse);

      Element hausnummer = doc.createElement("hausnummer");
      hausnummer.setNodeValue("");
      nodeID.appendChild(hausnummer);

      Element plz = doc.createElement("postleitzahl");
      plz.setNodeValue("");
      nodeID.appendChild(plz);

      Element ort = doc.createElement("ort");
      ort.setNodeValue("");
      nodeID.appendChild(ort);

      root.appendChild(nodeID);
      Adresse adresse = new Adresse(strasse.getNodeValue(), hausnummer.getNodeValue(), plz.getNodeValue(), ort.getNodeValue());
      writeToXML(doc, new FileOutputStream(FILEPATH));
      return adresse;
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#create().");
    }
    return null;
  }

  @Override
  public void create(IAdresse objectToInsert)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      Element root = doc.getElementById("adresse");
      Element nodeID = doc.createElement("id");
      //get the id here pls.
      nodeID.setIdAttribute("", true);

      Element strasse = doc.createElement("strasse");
      strasse.setNodeValue(objectToInsert.getStrasse());
      nodeID.appendChild(strasse);

      Element hausnummer = doc.createElement("hausnummer");
      hausnummer.setNodeValue(objectToInsert.getHausNr());
      nodeID.appendChild(hausnummer);

      Element plz = doc.createElement("postleitzahl");
      plz.setNodeValue(objectToInsert.getPlz());
      nodeID.appendChild(plz);

      Element ort = doc.createElement("ort");
      ort.setNodeValue(objectToInsert.getOrt());
      nodeID.appendChild(ort);

      root.appendChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#create(IAdresse objectToInsert).");
    }
  }

  @Override
  public IAdresse read(Long id)
  {
    Document doc = getDocument(FILEPATH);
    Element root = doc.getElementById("adresse");
    Element nodeID = root.getOwnerDocument().getElementById(id.toString());
    Adresse adresse = new Adresse(nodeID.getElementsByTagName("strasse").item(0).getNodeValue(), nodeID.getElementsByTagName("hausnummer").item(0).getNodeValue(), nodeID.getElementsByTagName("postleitzahl").item(0).getNodeValue(), nodeID.getElementsByTagName("ort").item(0).getNodeValue());
    return adresse;
  }

  @Override
  public List<IAdresse> readAll()
  {
    Document doc = getDocument(FILEPATH);
    Element root = doc.getElementById("adresse");
    List<IAdresse> adressListe = new ArrayList<>();
    NodeList adressen = root.getElementsByTagName("id");
    for (int i = 0; i < adressen.getLength(); i++)
    {
      NodeList childs = adressen.item(i).getChildNodes();
      adressListe.add(new Adresse(childs.item(0).getNodeValue(), childs.item(1).getNodeValue(), childs.item(2).getNodeValue(), childs.item(3).getNodeValue()));
    }
    return adressListe;
  }

  @Override
  public void update(IAdresse objectToUpdate)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      Element root = doc.getElementById("adresse");
      //get the id here pls.
      Element nodeID = root.getOwnerDocument().getElementById("");

      Node strasse = nodeID.getElementsByTagName("strasse").item(0);
      strasse.setNodeValue(objectToUpdate.getStrasse());

      Node hausnummer = nodeID.getElementsByTagName("hausnummer").item(0);
      hausnummer.setNodeValue(objectToUpdate.getHausNr());

      Node plz = nodeID.getElementsByTagName("postleitzahl").item(0);
      plz.setNodeValue(objectToUpdate.getPlz());

      Node ort = nodeID.getElementsByTagName("ort").item(0);
      ort.setNodeValue(objectToUpdate.getOrt());

      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
    catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#update(IAdresse objectToUpdate).");
    }
  }

  @Override
  public void delete(Long id)
  {
    try
    {
      Document doc = getDocument(FILEPATH);
      Element root = doc.getElementById("adresse");
      Element nodeID = root.getOwnerDocument().getElementById(id.toString());
      root.removeChild(nodeID);
      writeToXML(doc, new FileOutputStream(FILEPATH));
    }
      catch (IOException ex)
    {
      System.out.println("There was an unexpected Exception in AdresseDaoXml#delete(Long id).");
    }
  }
}
