package Kaufvertrag.dataLayer.dataAccessObjects;

public class DataLayerManager {
    private static final DataLayerManager instance = new DataLayerManager();
    private String persistenceType;

    private DataLayerManager() {
        System.out.println("Datalayermanager gebildet ...")
    }

    public DataLayerManager getInstance(){
        return instance;
    }

    public IDataLayer getDataLayer(){
        switch (this.persistenceType) {
            case "sqlite":
                return new DataLayerSqlite();
                break;
            
            case "xml":
                return new DataLayerXml();

            default:
                throw new DaoException("No valid persistenceType");
        }
    }

    private String readPersistenceTyp(String persistenceType){ //Eigentlich mit dem Gui verknüpft
        this.persistenceType = persistenceType;
    }

}
