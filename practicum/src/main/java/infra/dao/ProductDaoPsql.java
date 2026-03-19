package infra.dao;

import domain.*;

import java.sql.*;
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
                " (product_nummer, naam, beschrijving, prijs) " +
                "VALUES (?, ?, ?, ?);";
        return false;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        String sql = "UPDATE product " +
                "SET " +
                "naam=?, " +
                "beschrijving=?, " +
                "prijs=?, " +
                "WHERE product_nummer=?;";
        return false;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        String sql = "DELETE FROM product " +
                "WHERE product_nummer=?;";
        return false;
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
        return null;
    }

    @Override
    public List<Product> findByOvChipkaart(OvChipkaart ovChipkaart) throws SQLException {
//        String sql = "SELECT " +
//                "product_nummer, " +
//                "naam, " +
//                "beschrijving, " +
//                "prijs " +
//                "FROM product " +
//                "WHERE product_nummer=?;";
        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT " +
                "product_nummer, " +
                "naam, " +
                "beschrijving, " +
                "prijs " +
                "FROM product;";
        return null;
    }
}
