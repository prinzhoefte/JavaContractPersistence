package Kaufvertrag.businessObjects;

public interface IKaufvertrag {
    public IVertragspartner getVerkaeufer();
    public void setVerkaeufer(IVertragspartner verkaeufer);
    public IVertragspartner getKaeufer();
    public void setKaeufer(IVertragspartner kaeufer);
    public IWare getWare();
    public void setWare(IWare ware);
    public String getZahlungsModalitaet();
    public void setZahlungsModalitaet(String zahlungsModalitaet);
}
