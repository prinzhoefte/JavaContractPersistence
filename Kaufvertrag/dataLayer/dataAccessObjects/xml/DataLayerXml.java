package Kaufvertrag.dataLayer.dataAccessObjects.xml;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerXml implements IDataLayer {

    @Override
    public IDao<IVertragspartner, String> getDaoVertragspartner() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDaoVertragspartner'");
    }

    @Override
    public IDao<IWare, Long> getDaoWare() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDaoWare'");
    }
    
}
