package Kaufvertrag.dataLayer.dataAccessObjects;

import java.util.List;

import Kaufvertrag.presentationLayer.exceptions.DaoException;

public interface IDao <T,K> {
    public T create();
    public void create(T objectToInsert) throws DaoException;
    public T read(K id) throws DaoException;
    public List<T> readAll() throws DaoException;
    public void update(T objectToUpdate) throws DaoException;
    public void delete(K id) throws DaoException;
}