package domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OvChipkaart {
    private int kaartNummer;// Max length 10
    private Date geldigTot;
    private int klasse;// Max length 1
    private BigDecimal saldo;// Max precision 16, scale 2

    private Reiziger reiziger;
    private List<Product> producten = new ArrayList<Product>();

    public OvChipkaart() {}

    public int getKaartNummer() { return kaartNummer; }

    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() { return geldigTot; }

    public void setGeldigTot(Date geldigTot) {
        if (geldigTot == null) return;
        this.geldigTot = geldigTot;
    }

    public BigInteger getKlasse() { return BigInteger.valueOf(klasse); }

    public void setKlasse(BigInteger klasse) {
        if (klasse == null) return;
        if (klasse.toString().length() > 1) return;
        this.klasse = klasse.intValue();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        if (saldo == null) return;
        if (saldo.precision() > 16) return;// Make sure its not longer than precision
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    /**
     * Adds a product from this ovChipkaart
     */
    public boolean voegToeProduct(Product product) {
        if (product == null) return false;

        // Only add if it hasn't been added yet
        if (!this.producten.contains(product)) {
            this.producten.add(product);
            return product.voegToeOvChipkaart(this);
        }

        return false;
    }

    /**
     * Removes a product from this ovChipkaart
     */
    public boolean verwijderProduct(Product product) {
        if (product == null) return false;

        // Product does not exist, cannot be removed.
        if (!this.producten.contains(product)) return false;

        if (this.producten.remove(product)) {
            return product.verwijderOvChipkaart(this);
        }

        return false;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    @Override
    public boolean equals(Object object) {
        // Instanceof handles null
        if (!(object instanceof OvChipkaart)) return false;

        OvChipkaart ock = (OvChipkaart) object;

        if (getKaartNummer() != ock.getKaartNummer()) return false;

        if (!Objects.equals(getGeldigTot(), ock.getGeldigTot())) return false;

        if (!Objects.equals(getKlasse(), ock.getKlasse())) return false;

        if (!Objects.equals(getSaldo(), ock.getSaldo())) return false;

        if (!Objects.equals(getReiziger(), ock.getReiziger())) return false;

        if (!Objects.equals(getProducten(), ock.getProducten())) return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Ov Chipkaart met kaartnummer: ");
        sb.append(getKaartNummer());
        sb.append(" is geldig tot ");
        sb.append(getGeldigTot());
        sb.append(" in klasse ");
        sb.append(getKlasse());
        sb.append(" met een saldo van €");
        sb.append(String.format("%.2f", getSaldo()));
        sb.append(" [ReizigerId=");

        Reiziger reiziger = getReiziger();
        if (reiziger == null) {
            sb.append("null");
        } else {
            sb.append(reiziger.getReizigerId());
        }
        sb.append("] ");

        ArrayList<Product> producten = new ArrayList<Product>(getProducten());
        if (producten.isEmpty()) {
            sb.append("en geen product(en) ");
        } else {
            sb.append("met producten: [");
            for (Product p : producten) {
                sb.append(p.getProductNummer());
                sb.append(",");
            }

            sb.append("]");
        }

        return sb.toString();
    }
}
