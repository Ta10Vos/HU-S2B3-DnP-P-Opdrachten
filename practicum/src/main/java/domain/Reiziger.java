package domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

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
        this.voorletters = voorletters.trim();
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        if (tussenvoegsel == null) {
            tussenvoegsel = "";
        }
        this.tussenvoegsel = tussenvoegsel.trim();
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
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
        return null;
    }

    public void setOvChipkaart(List<OvChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public String toString() {
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

        if (!(getAdres() == null)) {
            sb.append(" woont op: ");
            sb.append(getAdres().toString());
        }

        if (!ovChipkaarten.isEmpty()) {
            sb.append(" en heeft ov-chipkaarten: \n");

            for(OvChipkaart ovChip : getOvChipkaart()) {
                sb.append(ovChip.toString());
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
