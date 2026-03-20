package infra.dao;

import domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoPsql implements IProductDao {
    private Connection connection;
    private IOvChipkaartDao ockDao;

    public ProductDaoPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Product product) throws SQLException {
        String sql = "INSERT INTO product " +
                "(product_nummer, naam, beschrijving, prijs) " +
                "VALUES (?, ?, ?, ?);";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, product.getProductNummer());
        pst.setString(2, product.getNaam());
        pst.setString(3, product.getBeschrijving());
        pst.setBigDecimal(4, product.getPrijs());

        boolean result = pst.executeUpdate() > 0;
        pst.close();

        // Add relations
        result = saveOvChipkaartProduct(product) && result;

        return result;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        String sql = "UPDATE product " +
                "SET " +
                "naam=?, " +
                "beschrijving=?, " +
                "prijs=? " +
                "WHERE product_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setString(1, product.getNaam());
        pst.setString(2, product.getBeschrijving());
        pst.setBigDecimal(3, product.getPrijs());
        pst.setInt(4, product.getProductNummer());

        boolean result = pst.executeUpdate() > 0;
        pst.close();

        // Update relations (removes and saves the relations)
        result = updateOvChipkaartProduct(product) && result;

        return result;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        // Delete relations
        boolean result = deleteOvChipkaartProduct(product);

        String sql = "DELETE FROM product " +
                "WHERE product_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, product.getProductNummer());

        result = pst.executeUpdate() > 0 && result;
        pst.close();

        return result;
    }

    @Override
    public Product findById(int id) throws SQLException {
        String sql = "SELECT " +
                "product_nummer, " +
                "naam, " +
                "beschrijving, " +
                "prijs " +
                "FROM product " +
                "WHERE product_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, id);

        if (!pst.execute()) return null;

        ResultSet rs = pst.executeQuery();

        if (!rs.next()) return null;

        Product p = new Product();

        p.setProductNummer(rs.getInt("product_nummer"));
        p.setNaam(rs.getString("naam"));
        p.setBeschrijving(rs.getString("beschrijving"));
        p.setPrijs(rs.getBigDecimal("prijs"));

        rs.close();
        pst.close();

        return p;
    }

    @Override
    public List<Product> findByOvChipkaart(OvChipkaart ovChipkaart) throws SQLException {
        String sql = "SELECT " +
                "p.product_nummer, " +
                "p.naam, " +
                "p.beschrijving, " +
                "p.prijs " +
                "FROM product AS p " +
                "INNER JOIN ov_chipkaart_product AS ocp " +
                "ON ocp.product_nummer = p.product_nummer " +
                "WHERE ocp.kaart_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, ovChipkaart.getKaartNummer());

        if (!pst.execute()) return null;

        ResultSet rs = pst.executeQuery();
        ArrayList<Product> producten = new ArrayList<Product>();

        while (rs.next()) {
            Product p = new Product();

            p.setProductNummer(rs.getInt("product_nummer"));
            p.setNaam(rs.getString("naam"));
            p.setBeschrijving(rs.getString("beschrijving"));
            p.setPrijs(rs.getBigDecimal("prijs"));

            producten.add(p);
        }

        rs.close();
        pst.close();

        return producten;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT " +
                "product_nummer, " +
                "naam, " +
                "beschrijving, " +
                "prijs " +
                "FROM product;";

        PreparedStatement pst = connection.prepareStatement(sql);

        if (!pst.execute()) return null;

        ResultSet rs = pst.executeQuery();
        ArrayList<Product> producten = new ArrayList<Product>();

        while (rs.next()) {
            Product p = new Product();

            p.setProductNummer(rs.getInt("product_nummer"));
            p.setNaam(rs.getString("naam"));
            p.setBeschrijving(rs.getString("beschrijving"));
            p.setPrijs(rs.getBigDecimal("prijs"));

            producten.add(p);
        }

        rs.close();
        pst.close();

        return producten;
    }

    /* ---
        CRUD for the OvChipkaart_product relation
       --- */
    private boolean saveOvChipkaartProduct(Product product) throws SQLException {
        List<OvChipkaart> ockList = product.getOvChipKaarten();

        if (ockList.isEmpty()) return true;// No need to save if there's no relations

        String sql = "INSERT INTO ov_chipkaart_product " +
                "(kaart_nummer, product_nummer) " +
                "VALUES (?, ?);";

        PreparedStatement pst = connection.prepareStatement(sql);

        for (OvChipkaart ock : ockList) {
            pst.setInt(1, ock.getKaartNummer());
            pst.setInt(2, product.getProductNummer());

            pst.addBatch();
        }

        // Make sure all INSERTS have been executed properly
        boolean result = pst.executeUpdate() >= ockList.size();
        pst.close();

        return result;
    }

    private boolean updateOvChipkaartProduct(Product product) throws SQLException {
        // Delete all existing relations with product
        boolean result = deleteOvChipkaartProduct(product);

        // Add all existing relations back.
        result = saveOvChipkaartProduct(product) && result;

        return result;
    }

    private boolean deleteOvChipkaartProduct(Product product) throws SQLException {
        // Delete ALL relations of current Product and its ovChip
        String sql = "DELETE FROM ov_chipkaart_product " +
                "WHERE product_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, product.getProductNummer());

        boolean result = pst.executeUpdate() > 0;
        pst.close();

        return result;
    }

    public void setOvChipkaartDao(IOvChipkaartDao ovChipkaartDaoPsql) {
        this.ockDao = ovChipkaartDaoPsql;
    }
}
