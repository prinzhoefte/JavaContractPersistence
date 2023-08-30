package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.util.List;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;

public class VertragsPartnerDaoSqlite<IVertragspartner, String> implements IDao<IVertragspartner, String> {

    @Override
    public IVertragspartner create() {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void create(IVertragspartner objectToInsert) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public IVertragspartner read(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public List<IVertragspartner> readAll() {
        throw new UnsupportedOperationException("Unimplemented method 'readAll'");
    }

    @Override
    public void update(IVertragspartner objectToUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
