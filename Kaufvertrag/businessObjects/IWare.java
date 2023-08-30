package Kaufvertrag.businessObjects;

import java.util.List;

public interface IWare {
    public long getId();
    public String getBezeichnung();
    public void setBezeichnung(String bezeichnung);
    public String getBeschreibung();
    public void setBeschreibung(String beschreibung);
    public double getPreis();
    public void setPreis(double preis);
    public List<String> getBesonderheiten();
    public List<String> getMaengel();
}
