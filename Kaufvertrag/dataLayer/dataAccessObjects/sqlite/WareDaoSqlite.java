package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.util.List;

import Kaufvertrag.dataLayer.dataAccessObjects.IDao;

public class WareDaoSqlite<IWare, Long> implements IDao<IWare, Long> {

    @Override
    public IWare create() {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void create(IWare objectToInsert) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public IWare read(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public List<IWare> readAll() {
        throw new UnsupportedOperationException("Unimplemented method 'readAll'");
    }

    @Override
    public void update(IWare objectToUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
