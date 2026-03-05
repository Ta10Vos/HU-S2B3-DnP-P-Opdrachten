package domain;

public class Adres {
    private int id;// max 10
    private String postcode;// max 10
    private String huisnummer;// max 10
    private String straat;// max 255
    private String woonplaats;// max 255

    private Reiziger reiziger;

    public int getAdresId() {
        return id;
    }

    public void setAdresId(int adresId) {
        this.id = adresId;
    }

    public String getPostcode() {
        return postcode;
    }

    /** Set the postcode with the max of 10 chars */
    public void setPostcode(String postcode) {
        if (postcode.length() > 10) return;
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return postcode;
    }

    /** Set the huisnummer with the max of 10 chars */
    public void setHuisnummer(String huisnummer) {
        if (huisnummer.length() > 10) return;
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    /** Set the straat with the max of 255 chars */
    public void setStraat(String straat) {
        if (straat.length() > 255) return;
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    /** Set the woonplaats with the max of 255 chars */
    public void setWoonplaats(String woonplaats) {
        if (woonplaats.length() > 255) return;
        this.woonplaats = woonplaats;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }
}
