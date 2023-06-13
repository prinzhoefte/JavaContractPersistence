public class Adresse {
    private String strasse;
    private String hausNr;
    private String plz;
    private String ort;

    public Adresse(String strasse, String hausNr, String plz, String ort) {
        this.strasse = strasse;
        this.hausNr = hausNr;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausNr() {
        return hausNr;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausNr(String hausNr) {
        this.hausNr = hausNr;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    @Override
    public String toString() {
        return strasse + " " + hausNr + "," + plz + "," + ort;
    }
}