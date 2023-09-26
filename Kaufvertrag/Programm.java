package Kaufvertrag;
import Kaufvertrag.businessObjects.*;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.*;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

import javax.swing.*;
import java.util.*;

public class Programm {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> wareAtrtibute = new ArrayList<>();
    static ArrayList<String> vertragspartnerAtribute = new ArrayList<>();
    static String invalidText = "Die Eingabe war nicht richtig. Bitte geben Sie gültige Eingaben ein. ";
    static boolean usingSQL = false; // true = SQL, false = XML
    public static void main(String[] args) throws RuntimeException {
        try {
            // Created DataLayerManager and DataLayer
            AttributeHinzufuegen();
            DataLayerManager dataLayerManager = DataLayerManager.getInstance();
            IDataLayer dataLayer = dataLayerManager.getDataLayer(usingSQL);
            String userInput;
            while(true) {
                System.out.println("Was möchten Sie tun?");
                System.out.println("[1] Erstellen");
                System.out.println("[2] Loeschen");
                System.out.println("[3] Veraendern");
                System.out.println("[4] Ausgeben");
                System.out.println("[5] Beenden");
                userInput = JOptionPane.showInputDialog("Eingabe");
                if(userInput.equals("1") || userInput.equals("2") || userInput.equals("3")  || userInput.equals("4") || userInput.equals("5")) {
                    handleMode(userInput, dataLayer);
                }else{
                    System.out.println("Ungültig. Bitte waehlen Sie eine der verfügbaren Optionen.");
                }
            }

        } catch (DaoException e) {
            // Thow RuntimeException if something went wrong
            throw new RuntimeException(e);
        }
    }

    private static void handleMode(String userInput, IDataLayer dataLayer) throws DaoException {
        userInput = userInput.toLowerCase();
        try{
            switch(userInput){
                case "1":
                    erstellen(dataLayer);
                    break;
                case "2":
                    loeschen(dataLayer);
                    break;
                case "3":
                    updaten(dataLayer);
                    break; 
                case "4": 
                    ausgeben(dataLayer);
                    break;
                case "5":
                    System.exit(0);
                default: throw new DaoException(invalidText + "(" + userInput  + ")");
            }
        }catch (DaoException e){
            System.out.println(e.getMessage());
        }
    }

    private static void ausgeben(IDataLayer dataLayer) throws DaoException {
        System.out.println("Was möchten Sie ausgeben?");
        System.out.println("[1] Ware");
        System.out.println("[2] Vertragspartner");

        String modus = JOptionPane.showInputDialog("Eingabe");

        while(!modus.equals("1") && !modus.equals("2")){
            System.out.println(invalidText);
            modus = JOptionPane.showInputDialog("Eingabe");
        }

        if(modus.equals("1")) {
            IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
            System.out.println("Soll ein einzelner Eintrag ausgegeben werden? [ja/nein]");
            String modus2 = JOptionPane.showInputDialog("Eingabe [ja/nein]");
            if (modus2.equals("ja")) {
                System.out.println("Bitte geben Sie die ID an: ");
                String id = JOptionPane.showInputDialog("Eingabe ID");
                System.out.println(wareDao.read(Long.valueOf(id)));
            } else if (modus2.equals("nein")) {
                eintraegeAuslesen("1", dataLayer);
            } else {
                System.out.println(invalidText);
            }
        }
            if(modus.equals("2")){
                IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
                System.out.println("Soll ein einzelner Eintrag ausgegeben werden? [ja/nein]");
                String modus2 = JOptionPane.showInputDialog("Eingabe [ja/nein]");
                if(modus2.equals("ja")){
                    System.out.println("Bitte geben Sie die ID an: ");
                    String id = JOptionPane.showInputDialog("Eingabe ID");
                    System.out.println(vertragspartnerDao.read(id));
                }else if(modus2.equals("nein")){
                    eintraegeAuslesen("2", dataLayer);
                }else{
                    System.out.println(invalidText);
                }
            }
    }

    private static void updaten(IDataLayer dataLayer) throws DaoException {
        System.out.println("Was moechten Sie veraendern?");
        System.out.println("[1] Ware");
        System.out.println("[2] Vertragspartner");

        String modus = null;
        ArrayList<String> s = new ArrayList<>();
        boolean go = true;

        //Auswahl in Schleife --> was soll geaendert werden
        while(go){
            modus = JOptionPane.showInputDialog("Eingabe");
            switch (modus){
                case "1":
                    s = wareAtrtibute;
                    eintraegeAuslesen("1", dataLayer);
                    go = false;
                    break;
                case "2":
                    s = vertragspartnerAtribute;
                    eintraegeAuslesen("2", dataLayer);
                    go = false;
                    break;
                default:
                    System.out.println(invalidText);
            }
        }

        System.out.println("Bei welchem Eintrag soll etwas geändert werden? Geben Sie die ID ein");
        String id = JOptionPane.showInputDialog("ID Eingabe");
        String modus2;
        String newVal;

        while(true){
            System.out.println("Was soll veraendert werden?");
            for(int i = 0; i < s.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + s.get(i));
            }
            System.out.println("[" + (s.size() + 1) + " ] Exit");
                modus2 = JOptionPane.showInputDialog("Eingabe");
                //Exit
                if(modus2.equals(String.valueOf(s.size() + 1))){
                    break;
                }else if(Integer.parseInt(modus2) < 1 || Integer.parseInt(modus2) > s.size()){
                    throw new DaoException(invalidText + "(" + modus2 + ")");
                }else{
                    System.out.println("Bitte geben Sie den neuen Wert für " + s.get(Integer.parseInt(modus2) - 1) + "an");
                    newVal = JOptionPane.showInputDialog("Eingabe");
                }
                if(modus.equals("1")){
                    IDao<IWare, Long> wareDao = dataLayer.getDaoWare();

                    wareDao.update(wareDao.read(Long.valueOf(id)));
                }else if(modus.equals("2")){
                    IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();

                    vertragspartnerDao.update(vertragspartnerDao.read(id));
                }else{
                    System.out.println("Ungültig");
                }


        }
    }

    private static void loeschen(IDataLayer dataLayer) throws DaoException {
        System.out.println("Was moechten Sie loeschen?");
        System.out.println("[1] Ware");
        System.out.println("[2] Vertragspartner");

        String modus = "";
        boolean go = true;

        // Auswahl in einer Schleife --> was soll gelöscht werden
        while(go){
            modus = JOptionPane.showInputDialog("Eingabe");
            switch (modus){
                case "1":
                    eintraegeAuslesen("1", dataLayer);
                    go = false; 
                    break;
                case "2":
                    eintraegeAuslesen("2", dataLayer);
                    go = false;
                    break;
                default:
                    System.out.println(invalidText);
            }
        }
        System.out.println("Bitte geben Sie die ID des Eintrags an, den Sie loeschen wollen: ");
        String id = JOptionPane.showInputDialog("Eingabe");
        
        if(modus.equals("1")){
            IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
            wareDao.delete(Long.valueOf(id));
        }else if(modus.equals("2")){
            IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
            vertragspartnerDao.delete(id);
        }
    }

    private static void eintraegeAuslesen(String s, IDataLayer dataLayer) throws DaoException {
        if(s.equals("1")){
            IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
            List<IWare> alleEintraege = wareDao.readAll();
            for(IWare ware : alleEintraege) {
                System.out.println(ware);
            }
        }else if(s.equals("2")){
            IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
            List<IVertragspartner> alleEintraege = vertragspartnerDao.readAll(); 
            for(IVertragspartner vertragspartner : alleEintraege){
                System.out.println(vertragspartner);
            }
        }else{
            System.out.println("Ungueltig");
        }
    }

    private static void erstellen(IDataLayer dataLayer) throws DaoException {
        System.out.println("Was moechten Sie erstellen?");
        System.out.println("[1] Ware");
        System.out.println("[2] Vertragspartner");

        String modus = "";
        ArrayList<String> s  = new ArrayList<>();
        //Auswal in einer Schleife --> was soll erstellt werden
        boolean go = true;
        while(go){
            modus = JOptionPane.showInputDialog("Eingabe");
            if(modus.equals("1")){
                s = wareAtrtibute;
                go = false;
            }else if(modus.equals("2")){
                s = vertragspartnerAtribute;
                go = false;
            }else{
                System.out.println(invalidText);
            }
        }

        ArrayList<String> eingabe = new ArrayList<>();
        for (String s2 : s){
            System.out.println("Gebe den Wert für '" + s2 + "' ein:");
            eingabe.add(JOptionPane.showInputDialog("Eingabe: " + s2));
        }
        if(modus.equals("1")){
            IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
            Ware ware = new Ware(eingabe.get(1),eingabe.get(3));
            ware.setBeschreibung(eingabe.get(2));
            ware.setBesonderheiten(eingabe.get(4));
            ware.setMaengel(eingabe.get(5));
            wareDao.create(ware);
        }else if(modus.equals("2")){
            IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
            Vertragspartner vertragspartner = new Vertragspartner(eingabe.get(1), eingabe.get(2));
            vertragspartner.setAusweisNr(eingabe.get(0));
            vertragspartner.setAdresse(new Adresse(eingabe.get(3), eingabe.get(4), eingabe.get(5), eingabe.get(6)));
            vertragspartnerDao.create(vertragspartner);
        }
    }

    private static void AttributeHinzufuegen() {
        wareAtrtibute.add("ID");
        wareAtrtibute.add("Bezeichnung");
        wareAtrtibute.add("Beschreibung");
        wareAtrtibute.add("Preis");
        wareAtrtibute.add("Besonderheiten");
        wareAtrtibute.add("Maengel");

        vertragspartnerAtribute.add("AusweisNr");
        vertragspartnerAtribute.add("Vornname");
        vertragspartnerAtribute.add("Nachname");
        vertragspartnerAtribute.add("Strasse");
        vertragspartnerAtribute.add("Hausnummer");
        vertragspartnerAtribute.add("PLZ");
        vertragspartnerAtribute.add("Ort");
    }
}
