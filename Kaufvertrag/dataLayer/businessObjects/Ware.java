package Kaufvertrag.dataLayer.businessObjects;
import Kaufvertrag.businessObjects.IWare;
import java.util.List;

public class Ware implements IWare{
    private long id;
    private String bezeichnung;
    private String beschreibung;
    private String preis;
    List<String> besonderheiten;
    List<String> maengel;

    public Ware(String bezeichnung, String preis) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

    public void setId(long id) {
        this.id = id; 
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public List<String> getBesonderheiten() {
        return besonderheiten;
    }

    public List<String> getMaengel() {
        return maengel;
    }

    @Override
    public String toString() {
        return  bezeichnung + "," +
                beschreibung + "," +
                preis + "," +
                besonderheiten + "," +
                maengel;
    }
}