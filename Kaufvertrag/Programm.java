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
            // Created DataLayerManager and DataLayer
            DataLayerManager dataLayerManager = DataLayerManager.getInstance();
            IDataLayer dataLayer = dataLayerManager.getDataLayer();

            // Created "Vertragspartner" objects for seller and buyer
            IVertragspartner seller = new Vertragspartner("Max", "Mustermann");
            IVertragspartner buyer = new Vertragspartner("Anna", "Mustermann");
            seller.setAusweisNr("73567");
            buyer.setAusweisNr("73642");

            // Created "Adresse" objects for seller and buyer
            IAdresse sellerAddress = new Adresse("Musterstrasse", "123", "12345", "Musterstadt");
            IAdresse buyerAddress = new Adresse("Kaeuferweg", "456", "54321", "Kaeuferstadt");

            // Set addresses for seller and buyer
            seller.setAdresse(sellerAddress);
            buyer.setAdresse(buyerAddress);

            // Created "Ware" object
            IWare item = new Ware("Beispielware", "100.00");
            IKaufvertrag contract = new Kaufvertrag(buyer, seller, item);
            contract.setZahlungsModalitaet("Barzahlung");
            item.setId(0);

            // Created "Dao" objects for seller, buyer and item
            IDao<IVertragspartner, String> vertragspartnerStringIDao = dataLayer.getDaoVertragspartner();
            IDao<IWare, Long> wareLongIDao = dataLayer.getDaoWare();

            // Saved seller, buyer and item to database/xml file
            vertragspartnerStringIDao.create(seller);
            vertragspartnerStringIDao.create(buyer);
            wareLongIDao.create(item);

            long itemId = item.getId();

            // Print out infos and details to check if everything worked
            System.out.println("Kaufvertragsdetails: ");
            System.out.println(contract.toString());

            System.out.println("\nVerkaeuferdetails: ");
            IVertragspartner retrievedSeller = vertragspartnerStringIDao.read(seller.getAusweisNr());
            System.out.println(retrievedSeller.toString());

            System.out.println("\nGekaufte Ware: ");
            IWare retrievedItem = wareLongIDao.read(itemId);
            System.out.println(retrievedItem.toString());

            System.out.println("\nAlle Vertragspartner: ");
            System.out.println(vertragspartnerStringIDao.readAll());
            System.out.println("\nAlle Waren: ");
            System.out.println(wareLongIDao.readAll());

        } catch (DaoException e) {
            // Thow RuntimeException if something went wrong
            throw new RuntimeException(e);
        }
    }
}
