package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;

public interface IDataLayer {
    public IDao<IVertragspartner, String> getDaoVertragspartner();
    public IDao<IWare, Long> getDaoWare();
}
