package Kaufvertrag;
import Kaufvertrag.businessObjects.*;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Kaufvertrag;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.*;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

public class Programm {
    public static void main(String[] args) throws RuntimeException {
        try {
            //Datenzugriffsschicht-Manager initialisieren
            DataLayerManager dataLayerManager = DataLayerManager.getInstance();
            IDataLayer dataLayer = dataLayerManager.getDataLayer();

            //Vertragspartner erstellen
            IVertragspartner seller = new Vertragspartner("Max", "Mustermann");
            IVertragspartner buyer = new Vertragspartner("Anna", "Mustermann");
            seller.setAusweisNr("73567");
            buyer.setAusweisNr("73642");

            //Adresse erstellen
            IAdresse sellerAddress = new Adresse("Musterstraße", "123", "12345", "Musterstadt");
            IAdresse buyerAddress = new Adresse("Käuferweg", "456", "54321", "Käuferstadt");

            //Vertragspartner mit Addresse Verknüpfen
            seller.setAdresse(sellerAddress);
            buyer.setAdresse(buyerAddress);

            //Kaufvertrag erstellen
            IWare item = new Ware("Beispielware", "100.00");
            IKaufvertrag contract = new Kaufvertrag(buyer, seller, item);
            contract.setZahlungsModalitaet("Barzahlung");
            item.setId(0);

            //DAOs abrufen und Daten speichern
            IDao<IVertragspartner, String> vertragspartnerStringIDao = dataLayer.getDaoVertragspartner();
            IDao<IWare, Long> wareLongIDao = dataLayer.getDaoWare();

            //Daten in Doas speichern
            vertragspartnerStringIDao.create(seller);
            vertragspartnerStringIDao.create(buyer);
            wareLongIDao.create(item);

            //Item ID auslesen
            long itemId = item.getId();

            //Daten auslesen und anzeigen
            System.out.println("Kaufvertragsdetails: ");
            System.out.println(contract.toString());

            System.out.println("\nVerkäuferdetails: ");
            IVertragspartner retrievedSeller = vertragspartnerStringIDao.read(seller.getNachname());
            System.out.println(retrievedSeller.toString());

            System.out.println("\nGekaufte Ware: ");
            IWare retrievedItem = wareLongIDao.read(itemId);
            System.out.println(retrievedItem.toString());

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }
}
