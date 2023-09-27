package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerSqlite implements IDataLayer {
    
    @Override
    public IDao<IVertragspartner, String> getDaoVertragspartner() {
        initDb();
        return new VertragsPartnerDaoSqlite();
    }

    @Override
    public IDao<IWare, Long> getDaoWare() {
        initDb();
        return new WareDaoSqlite();
    }

    private void initDb() {
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.checkTables();
    }
}
