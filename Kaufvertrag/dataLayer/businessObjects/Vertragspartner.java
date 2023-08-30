package Kaufvertrag.dataLayer.businessObjects;
import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;

public class Vertragspartner implements IVertragspartner{
    private String vorname;
    private String nachname;
    private String ausweisNr;
    private IAdresse adresse;

    public Vertragspartner(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getAusweisNr() {
        return ausweisNr;
    }

    public IAdresse getAdresse() {
        return adresse;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAusweisNr(String ausweisNr) {
        this.ausweisNr = ausweisNr;
    }

    public void setAdresse(IAdresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return nachname + "; " + vorname + "," + adresse;
    }
}