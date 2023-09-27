package Kaufvertrag;

import Kaufvertrag.businessObjects.*;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.*;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

public class Programm {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws DaoException {
        DataLayerManager dataLayerManager = DataLayerManager.getInstance();
        IDataLayer dataLayer = dataLayerManager.getDataLayer();

        while (true) {
            System.out.println("Choose action:");
            System.out.println("1: Create");
            System.out.println("2: Read");
            System.out.println("3: Update");
            System.out.println("4: Delete");
            System.out.println("5: Exit");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    // Handle Create action
                    create(dataLayer);
                    break;
                case 2:
                    // Handle Read action
                    read(dataLayer);
                    break;
                case 3:
                    // Handle Update action
                    update(dataLayer);
                    break;
                case 4:
                    // Handle Delete action
                    delete(dataLayer);
                    break;
                case 5:
                    // Handle Exit action
                    System.out.println("Programm terminated...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
                    break;
            }
        }
    }

    private static void create(IDataLayer dataLayer) throws DaoException {
        //Clear screen
        System.out.println("\033[H\033[2J");
        System.out.println("Choose object to create:");
        System.out.println("1: Vertragspartner");
        System.out.println("2: Ware");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (action) {
            case 1:
                // Handle Create Vertragspartner action
                IVertragspartner vertragspartner = new Vertragspartner();
                vertragspartner = getVertragspartner(vertragspartner);
                IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
                vertragspartnerDao.create(vertragspartner);
                System.out.println("\033[H\033[2J");
                break;
            case 2:
                // Handle Create Ware action
                Ware ware = new Ware();
                ware = getWare(ware);
                IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
                wareDao.create(ware);
                System.out.println("\033[H\033[2J");
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                break;
        }
    }

    private static void read(IDataLayer dataLayer) throws DaoException {
        //Clear screen
        System.out.println("\033[H\033[2J");
        System.out.println("Choose object to read:");
        System.out.println("1: Vertragspartner");
        System.out.println("2: Ware");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (action) {
            case 1:
                // Select if read one or all
                System.out.println("\033[H\033[2J");
                System.out.println("Choose read action:");
                System.out.println("1: Read one Vertragspartner");
                System.out.println("2: Read all Vertragspartner");
                action = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(action == 1) {
                    System.out.println("Enter Ausweisnummer:");
                    String ausweisnummer = scanner.nextLine();
                    IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
                    IVertragspartner vertragspartner = vertragspartnerDao.read(ausweisnummer);
                    if (vertragspartner == null) {
                        System.out.println("\033[H\033[2J");
                        System.out.println("Vertragspartner with Ausweisnummer " + ausweisnummer + " not found.");
                    } else {
                        System.out.println("\033[H\033[2J");
                        System.out.println(vertragspartner);
                    }
                } else {
                    IDao<IVertragspartner, String> vertragspartnerDao = dataLayer.getDaoVertragspartner();
                    List<IVertragspartner> vertragspartnerList = vertragspartnerDao.readAll();
                    if (vertragspartnerList == null) {
                        System.out.println("\033[H\033[2J");
                        System.out.println("No Vertragspartner found.");
                    } else {
                        System.out.println("\033[H\033[2J");
                        for (IVertragspartner vertragspartner : vertragspartnerList) {
                            System.out.println(vertragspartner);
                        }
                    }
                }
                break;
            case 2:
                System.out.println("\033[H\033[2J");
                System.out.println("Choose read action:");
                System.out.println("1: Read one Ware");
                System.out.println("2: Read all Ware");
                action = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(action == 1) {
                    System.out.println("Enter ArtikelNr:");
                    long id = scanner.nextLong();
                    IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
                    IWare ware = wareDao.read(id);
                    if (ware == null) {
                        System.out.println("\033[H\033[2J");
                        System.out.println("Ware with ArtikelNr " + id + " not found.");
                    } else {
                        System.out.println("\033[H\033[2J");
                        System.out.println(ware);
                    }
                } else {
                    IDao<IWare, Long> wareDao = dataLayer.getDaoWare();
                    List<IWare> wareList = wareDao.readAll();
                    if (wareList == null) {
                        System.out.println("\033[H\033[2J");
                        System.out.println("No Ware found.");
                    } else {
                        System.out.println("\033[H\033[2J");
                        for (IWare ware : wareList) {
                            System.out.println(ware);
                        }
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                break;
        }
    }

    private static void update(IDataLayer dataLayer) throws DaoException {
        //Clear screen
        System.out.println("\033[H\033[2J");
        System.out.println("Choose object to update:");
        System.out.println("1: Vertragspartner");
        System.out.println("2: Ware");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (action) {
            case 1:
                // Handle Update Vertragspartner action
                System.out.println("Enter Ausweisnummer of Vertragspartner to update:");
                String ausweisnummer = scanner.nextLine();
                IVertragspartner vertragspartner = dataLayer.getDaoVertragspartner().read(ausweisnummer);
                if (vertragspartner == null) {
                    System.out.println("\033[H\033[2J");
                    System.out.println("Vertragspartner with Ausweisnummer " + ausweisnummer + " does not exist.");
                    break;
                }
                vertragspartner = getVertragspartner(vertragspartner);
                dataLayer.getDaoVertragspartner().update(vertragspartner);
                break;
            case 2:
                // Handle Update Ware action
                System.out.println("Enter ArtikelNr of Ware to update:");
                long id = scanner.nextLong();
                Ware ware = (Ware) dataLayer.getDaoWare().read(id);
                if (ware == null) {
                    System.out.println("\033[H\033[2J");
                    System.out.println("Ware with ArtikelNr " + id + " does not exist.");
                    break;
                }
                ware = getWare(ware);
                dataLayer.getDaoWare().update(ware);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                break;
        }
    }

    private static void delete(IDataLayer dataLayer) throws DaoException {
        //Clear screen
        System.out.println("\033[H\033[2J");
        System.out.println("Choose object to delete:");
        System.out.println("1: Vertragspartner");
        System.out.println("2: Ware");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (action) {
            case 1:
                // Handle Delete Vertragspartner action
                System.out.println("Enter Ausweisnummer of Vertragspartner to delete:");
                String ausweisnummer = scanner.nextLine();
                dataLayer.getDaoVertragspartner().delete(ausweisnummer);
                break;
            case 2:
                // Handle Delete Ware action
                System.out.println("Enter ArtikelNr of Ware to delete:");
                long id = scanner.nextLong();
                dataLayer.getDaoWare().delete(id);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
                break;
        }
    }

    private static IVertragspartner getVertragspartner(IVertragspartner vertragspartner) {
        System.out.println("Enter Ausweisnummer:");
        String ausweisnummer = scanner.nextLine();
        System.out.println("Enter Vorname:");
        String vorname = scanner.nextLine();
        System.out.println("Enter Nachname:");
        String nachname = scanner.nextLine();
        System.out.println("Enter Strasse:");
        String strasse = scanner.nextLine();
        System.out.println("Enter Hausnummer:");
        String hausnummer = scanner.nextLine();
        System.out.println("Enter Postleitzahl:");
        String postleitzahl = scanner.nextLine();
        System.out.println("Enter Ort:");
        String ort = scanner.nextLine();
        IAdresse adresse = new Adresse(strasse, hausnummer, postleitzahl, ort);
        vertragspartner.setAusweisNr(ausweisnummer);
        vertragspartner.setVorname(vorname);
        vertragspartner.setNachname(nachname);
        vertragspartner.setAdresse(adresse);
        return vertragspartner;
    }

    private static Ware getWare(Ware ware) {
        System.out.println("Enter ArtikelNr:");
        String id = scanner.nextLine();
        long idLong = Long.parseLong(id);
        System.out.println("Enter Bezeichnung:");
        String bezeichnung = scanner.nextLine();
        System.out.println("Enter Beschreibung:");
        String beschreibung = scanner.nextLine();
        System.out.println("Enter Preis:");
        String preis = scanner.nextLine();
        System.out.println("Enter Besonderheiten:");
        List<String> besonderheiten = enterList();
        System.out.println("Enter Mängel:");
        List<String> maengel = enterList();
        ware.setId(idLong);
        ware.setPreis(preis);
        ware.setBezeichnung(bezeichnung);
        ware.setBeschreibung(beschreibung);
        ware.setBesonderheiten(besonderheiten);
        ware.setMaengel(maengel);
        return ware;
    }

    private static List<String> enterList() {
        List<String> list = new java.util.ArrayList<String>();
        while (true) {
            System.out.println("Enter next item or press enter to finish:");
            String item = scanner.nextLine();
            if (item.isEmpty()) {
                break;
            }
            list.add(item);
        }
        return list;
    }
}
