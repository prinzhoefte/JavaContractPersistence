package Kaufvertrag.dataLayer.dataAccessObjects;

import java.util.Scanner;

import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.dataLayer.dataAccessObjects.xml.DataLayerXml;
import Kaufvertrag.presentationLayer.exceptions.DaoException;

/**
 * This class manages the selection and creation of the data access layer based on the chosen persistence type (xml or sqlite).
 */
public class DataLayerManager {
    private static final DataLayerManager instance = new DataLayerManager();
    private String persistenceType;

    private DataLayerManager() {
        System.out.println("DataLayerManager created...");
        readPersistenceType();
    }

    /**
     * Get the instance of DataLayerManager (Singleton pattern).
     *
     * @return The DataLayerManager instance.
     */
    public static DataLayerManager getInstance() {
        return instance;
    }

    /**
     * Get the appropriate data access layer based on the chosen persistence type.
     *
     * @return An instance of IDataLayer for the chosen persistence type.
     * @throws DaoException If an invalid or unsupported persistence type is selected.
     */
    public IDataLayer getDataLayer(boolean usingSQL) throws DaoException {
        switch (persistenceType) {
            case "sqlite":
                return new DataLayerSqlite();
            
            case "xml":
                return new DataLayerXml();

            default:
                throw new DaoException("No valid persistenceType");
        }
    }

    /**
     * Read and set the persistence type from user input.
     *
     * @return The chosen persistence type.
     */
    private String readPersistenceType() {
        String persistenceType = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Choose Persistence Type: XML or SQLite");
            persistenceType = scanner.nextLine();
        } while (!persistenceType.equalsIgnoreCase("xml") && !persistenceType.equalsIgnoreCase("sqlite"));

        scanner.close();

        return this.persistenceType = persistenceType;
    }
}
