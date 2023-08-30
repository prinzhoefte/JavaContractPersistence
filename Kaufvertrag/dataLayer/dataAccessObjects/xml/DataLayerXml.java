package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerXml implements IDataLayer {

    @Override
    public IDao<IVertragspartner, String> getDaoVertragspartner() {
        IDao vertragspartnerDaoXml = new VertragspartnerDaoXml();
        
        return vertragspartnerDaoXml;
    }

    @Override
    public IDao<IWare, Long> getDaoWare() {
        IDao wareDaoXml = new WareDaoXml();

        return wareDaoXml;
    }
    
}
