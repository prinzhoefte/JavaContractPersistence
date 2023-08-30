package Kaufvertrag.businessObjects;

import java.util.List;

public interface IWare {
    public long getId();
    public String getBezeichnung();
    public void setBezeichnung(String bezeichnung);
    public String getBeschreibung();
    public void setBeschreibung(String beschreibung);
    public String getPreis();
    public void setPreis(String preis);
    public List<String> getBesonderheiten();
    public List<String> getMaengel();
}
