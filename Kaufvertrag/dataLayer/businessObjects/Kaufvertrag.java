package Kaufvertrag.dataLayer.businessObjects;
import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IKaufvertrag;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;

public class Kaufvertrag implements IKaufvertrag {
    private IVertragspartner verkaeufer;
    private IVertragspartner kaeufer;
    private IWare ware;
    String zahlungsModalitaeten;

    public Kaufvertrag(IVertragspartner kaeufer, IVertragspartner verkaeufer, IWare ware){
        this.kaeufer = kaeufer;
        this.verkaeufer = verkaeufer;
        this.ware = ware;
    }

    public Vertragspartner getKaeufer() {
        return kaeufer;
    }
    public Vertragspartner getVerkaeufer() {
        return verkaeufer;
    }
    public Ware getWare() {
        return ware;
    }

    public void setKaeufer(Vertragspartner kaeufer) {
        this.kaeufer = kaeufer;
    }
    public void setWare(Ware ware){
        this.ware = ware;
    }

    public String getZahlungsModalitaeten() {
        return zahlungsModalitaeten;
    }

    public void setZahlungsModalitaeten(String zahlungsModalitaeten) {
        this.zahlungsModalitaeten = zahlungsModalitaeten;
    }

    @Override
    public String toString() {
        return "Vertragspartner," +
                "Name," + "Strasse," +
                "PLZ," + "Ort," + "\n" + "Verkaeufer," + verkaeufer + "\n" + "Kaeufer," + kaeufer
                + "\n" + "Ware," + ware;
    }
}