package Kaufvertrag.dataLayer.businessObjects;
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

    public IVertragspartner getKaeufer() {
        return kaeufer;
    }

    public IVertragspartner getVerkaeufer() {
        return verkaeufer;
    }

    public void setVerkaeufer(IVertragspartner verkaeufer) {
        this.verkaeufer = verkaeufer;
    }

    public IWare getWare() {
        return ware;
    }

    public void setKaeufer(IVertragspartner kaeufer) {
        this.kaeufer = kaeufer;
    }

    public void setWare(IWare ware){
        this.ware = ware;
    }

    public String getZahlungsModalitaet() {
        return zahlungsModalitaeten;
    }

    public void setZahlungsModalitaet(String zahlungsModalitaeten) {
        this.zahlungsModalitaeten = zahlungsModalitaeten;
    }

    @Override
    public String toString() {
        return "Kaufvertrag" +
                "\nVerkaeufer: " + verkaeufer +
                "\nKaeufer: " + kaeufer +
                "\nWare: " + ware +
                "\nZahlungsModalitaeten: '" + zahlungsModalitaeten + '\'';
    };
}