package Kaufvertrag.dataLayer.dataAccessObjects;

public class DataLayerManager {
    private DataLayerManager instance;
    private String persistenceTyp;

    private DataLayerManager() {
    }

    public DataLayerManager getInstance(){
        return instance;
    }

    public IDataLayer getDataLayer(){
        return null;
    }

    private String readPersistenceTyp(){
        return persistenceTyp;
    }




}
