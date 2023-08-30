package Kaufvertrag.dataLayer.dataAccessObjects;

import java.util.Scanner;

import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.dataLayer.dataAccessObjects.xml.DataLayerXml;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

public class DataLayerManager {
    private static final DataLayerManager instance = new DataLayerManager();
    private String persistenceType;

    private DataLayerManager() {
        System.out.println("Datalayermanager gebildet ...");
        readPersistenceType();
    }

    public DataLayerManager getInstance(){
        return instance;
    }

    public IDataLayer getDataLayer() throws DaoException {
        switch (persistenceType) {
            case "sqlite":
                return new DataLayerSqlite();
                break;
            
            case "xml":
                return new DataLayerXml();

            default:
                throw new DaoException("No valid persistenceType");
        }
    }

    private String readPersistenceType() {

        String persistenceType = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Choose Persistence Type: XML or SQLite");
            persistenceType = scanner.nextLine();
        } while(persistenceType.equalsIgnoreCase("xml") || persistenceType.equalsIgnoreCase("sqlite"));

        scanner.close();

        return this.persistenceType = persistenceType;
    }

}
