package domain;

public class Adres {
    private int id = -1;// max 10
    private String postcode = "";// max 10
    private String huisnummer = "";// max 10
    private String straat = "";// max 255
    private String woonplaats = "";// max 255

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
        if (postcode == null) return;
        if (postcode.length() > 10) {
            postcode = postcode.substring(0, 10);
        }
        this.postcode = postcode;
    }

    public String getHuisnummer() { return huisnummer; }

    /** Set the huisnummer with the max of 10 chars */
    public void setHuisnummer(String huisnummer) {
        if (huisnummer == null) return;
        if (huisnummer.length() > 10) {
            huisnummer = huisnummer.substring(0, 10);
        }
        this.huisnummer = huisnummer;
    }

    public String getStraat() { return straat; }

    /** Set the straat with the max of 255 chars */
    public void setStraat(String straat) {
        if (straat == null) return;
        if (straat.length() > 255) {
            straat = straat.substring(0, 255);
        }
        this.straat = straat;
    }

    public String getWoonplaats() { return woonplaats; }

    /** Set the woonplaats with the max of 255 chars */
    public void setWoonplaats(String woonplaats) {
        if (woonplaats == null) return;
        if (woonplaats.length() > 255) {
            woonplaats = woonplaats.substring(0, 255);
        }
        this.woonplaats = woonplaats;
    }

    public Reiziger getReiziger() { return reiziger; }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public boolean equals(Object object) {
        // Instanceof handles null
        if (!(object instanceof Adres)) return false;

        Adres adr = (Adres) object;

        if (getAdresId() != adr.getAdresId()) return false;

        if (!getPostcode().equals(adr.getPostcode())) return false;

        if (!getHuisnummer().equals(adr.getHuisnummer())) return false;

        if (!getStraat().equals(adr.getStraat())) return false;

        if (!getWoonplaats().equals(adr.getWoonplaats())) return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Adres(");
        sb.append(getAdresId());
        sb.append(") ");
        sb.append(getStraat());
        sb.append(" ");
        sb.append(getHuisnummer());
        sb.append(", ");
        sb.append(getWoonplaats());
        sb.append(" ");
        sb.append(getPostcode());
        sb.append(". [ReizigerId=");

        Reiziger rzg = getReiziger();
        // Safety null-check for reiziger
        // Even though DB guarantees it's not null, it can still be null if it's not persisted yet.
        if (rzg == null) {
            sb.append("null");
        } else {
            sb.append(rzg.getReizigerId());
        }

        sb.append("]");

        return sb.toString();
    }
}
