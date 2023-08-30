package Kaufvertrag.dataLayer.dataAccessObjects;

import java.util.List;

public interface IDao <T,K> {
    public T create();
    public void create(T objectToInsert);
    public T read(K id);
    public List<T> readAll();
    public void update(T objectToUpdate);
    public void delete(K id);
}