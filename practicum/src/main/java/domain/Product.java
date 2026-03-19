package domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productNummer;// Max 10 chars
    private String naam;// Max 30 chars
    private String beschrijving;// Max 512 chars
    private BigDecimal prijs;// Max length 16, precision 2

    private List<OvChipkaart> ovChipKaarten = new ArrayList<OvChipkaart>();

    public Product() {}

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null) return;
        if (naam.length() > 30) return;
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        if (beschrijving == null) return;
        if (beschrijving.length() > 512) return;
        this.beschrijving = beschrijving;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        if (prijs == null) return;
        if (prijs.toString().length() > 17) return;// Include precision point + max length
        this.prijs = prijs;
    }

    public List<OvChipkaart> getOvChipKaarten() {
        return ovChipKaarten;
    }

    public void setOvChipKaarten(List<OvChipkaart> ovChipKaarten) {
        this.ovChipKaarten = ovChipKaarten;
    }
}
