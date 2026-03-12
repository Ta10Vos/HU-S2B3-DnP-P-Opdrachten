package domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reiziger {
    private int id = -1;// max 10 chars
    private String voorletters = "";// max 10 chars
    private String tussenvoegsel = null;// max 10 chars
    private String achternaam = "";// max 255 chars
    private Date geboortedatum = null;

    private domain.Adres adres;
    private List<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();

    public Reiziger() {}

    public int getReizigerId() {
        return id;
    }

    public void setReizigerId(int reizigerId) {
        id = reizigerId;
    }

    public String getVoorletters() { return voorletters; }

    public void setVoorletters(String voorletters) {
        if (voorletters == null) return;
        if (voorletters.length() > 10) {
            voorletters = voorletters.substring(0, 10);
        }
        this.voorletters = voorletters.trim();
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        if (tussenvoegsel == null) {
            tussenvoegsel = "";
        } else if (voorletters.length() > 10) {
            voorletters = voorletters.substring(0, 10);
        }
        this.tussenvoegsel = tussenvoegsel.trim();
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        if (achternaam == null) return;
        if (achternaam.length() > 255) {
            achternaam = achternaam.substring(0, 255);
        }
        this.achternaam = achternaam.trim();
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public domain.Adres getAdres() {
        return this.adres;
    }

    public void setAdres(domain.Adres adres) {
        this.adres = adres;
    }

    public List<OvChipkaart> getOvChipkaart() {
        return ovChipkaarten;
    }

    public void setOvChipkaart(List<OvChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public boolean equals(Object object) {
        // Instanceof handles null
        if (!(object instanceof Reiziger)) return false;

        Reiziger rzg = (Reiziger) object;

        if (getReizigerId() != rzg.getReizigerId()) return false;

        if (!getVoorletters().equals(rzg.getVoorletters())) return false;

        if (!Objects.equals(getTussenvoegsel(), rzg.getTussenvoegsel())) return false;

        if (!getAchternaam().equals(rzg.getAchternaam())) return false;

        if (!Objects.equals(getGeboortedatum(), rzg.getGeboortedatum())) return false;

        if (!Objects.equals(getAdres(), rzg.getAdres())) return false;

        if (!Objects.equals(getOvChipkaart(), rzg.getOvChipkaart())) return false;

        return true;
    }

    public String toString(boolean ignoreAdres, boolean ignoreOvChipkaarten) {
        StringBuilder sb = new StringBuilder();

        List<OvChipkaart> ovChipkaarten = getOvChipkaart();

        sb.append("Student ");
        sb.append(getVoorletters());

        if (!getTussenvoegsel().isEmpty()) {
            sb.append(" ");
            sb.append(getTussenvoegsel());
        }

        sb.append(" ");
        sb.append(getAchternaam());

        if (!(getGeboortedatum() == null)) {
            sb.append(" is geboren op ");
            sb.append(getGeboortedatum().toString());
        }

        if (!ignoreAdres && !(getAdres() == null)) {
            sb.append(" woont op: ");
            sb.append(getAdres().toString());
        }

        if (!ignoreOvChipkaarten && ovChipkaarten != null && !ovChipkaarten.isEmpty()) {
            sb.append(" en heeft ov-chipkaarten: \n");

            for(OvChipkaart ovChip : getOvChipkaart()) {
                sb.append(ovChip.toString());
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(false, false);
    }
}
