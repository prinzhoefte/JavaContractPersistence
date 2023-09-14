package Kaufvertrag.dataLayer.businessObjects;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Ware implements IWare{

    private long id;
    private String bezeichnung;
    private String beschreibung;
    private String preis;
    List<String> besonderheiten = new ArrayList<>();
    List<String> maengel = new ArrayList<>();

    public Ware(String bezeichnung, String preis) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.beschreibung = "";
        this.besonderheiten.add("");
        this.maengel.add("");
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
        return "Ware{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", preis='" + preis + '\'' +
                ", besonderheiten=" + besonderheiten +
                ", maengel=" + maengel +
                '}';
    }

    /*
     * 
     * own created methodes to reduce repetitions in code
     * 
     */
    public void setBesonderheiten(List<String> besonderheiten) {
        
        for(int i = 0; i < besonderheiten.size(); i++) {
            String besonderheit = besonderheiten.get(i);
            this.besonderheiten.add(besonderheit);
        }
    }

    public void setBesonderheiten(String besonderheitenRaw) {

        List<String> besonderheiten = Formatter.StringToList(besonderheitenRaw);
        
        for(int i = 0; i < besonderheiten.size(); i++) {
            String besonderheit = besonderheiten.get(i);
            this.besonderheiten.add(besonderheit);
        }
    }

    public void setMaengel(List<String> maengel) {
        
        for(int i = 0; i < maengel.size(); i++) {
            String mangel = maengel.get(i);
            this.maengel.add(mangel);
        }
    }

    public void setMaengel(String maengelRaw) {

        List<String> maengel = Formatter.StringToList(maengelRaw);
        
        for(int i = 0; i < maengel.size(); i++) {
            String mangel = maengel.get(i);
            this.maengel.add(mangel);
        }
    }
}