package domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private int productNummer;// Max 10 chars
    private String naam;// Max 30 chars
    private String beschrijving;// Max 512 chars
    private double prijs;// Max length 16, precision 2

    private List<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();

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
        if (naam.length() > 30) {
            naam = naam.substring(0, 30);
        };
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        if (beschrijving == null) return;
        if (beschrijving.length() > 512) {
            beschrijving = beschrijving.substring(0, 512);
        };
        this.beschrijving = beschrijving;
    }

    public BigDecimal getPrijs() {
        return BigDecimal.valueOf(prijs);
    }

    public void setPrijs(BigDecimal prijs) {
        if (prijs == null) return;
        if (prijs.toString().length() > 17) return;// Include precision point + max length
        this.prijs = prijs.doubleValue();
    }

    public List<OvChipkaart> getOvChipKaarten() {
        return ovChipkaarten;
    }

    // How could I add voegToe & verwijder here and prevent recursiveness?

    public void setOvChipKaarten(List<OvChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public boolean equals(Object object) {
        // Instanceof handles null
        if (!(object instanceof Product)) return false;

        Product p = (Product) object;

        if (getProductNummer() != p.getProductNummer()) return false;

        if (!Objects.equals(getNaam(), p.getNaam())) return false;

        if (!Objects.equals(getBeschrijving(), p.getBeschrijving())) return false;

        if (!Objects.equals(getPrijs(), p.getPrijs())) return false;

        if (!Objects.equals(getOvChipKaarten(), p.getOvChipKaarten())) return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Product met productnummer: ");
        sb.append(getProductNummer());
        sb.append(" heeft de naam: ");
        sb.append(getNaam());
        sb.append(" en de beschrijving:\n ");
        sb.append(getBeschrijving());
        sb.append("\n en heeft de prijs: ");
        sb.append(String.format("%.2f", getPrijs()));

        ArrayList<OvChipkaart> ovChipKaarten = new ArrayList<OvChipkaart>(getOvChipKaarten());
        if (ovChipKaarten.isEmpty()) {
            sb.append("en geen product(en) ");
        } else {
            sb.append("met producten: ");
            for (OvChipkaart ock : ovChipKaarten) {
                sb.append(ock.toString());
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
