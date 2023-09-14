package Kaufvertrag.businessObjects;

public interface IVertragspartner {
    public String getAusweisNr();
    public void setAusweisNr(String ausweisNr);
    public String getVorname();
    public void setVorname(String vorname);
    public String getNachname();
    public void setNachname(String nachname);
    public IAdresse getAdresse();
    public void setAdresse(IAdresse adresse);

}
