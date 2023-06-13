public class Kaufvertrag {
    private Vertragspartner verkaeufer;
    private Vertragspartner kaeufer;
    private Ware ware;
    String zahlungsModalitaeten;

    public Kaufvertrag(Vertragspartner kaeufer, Vertragspartner verkaeufer, Ware ware){
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