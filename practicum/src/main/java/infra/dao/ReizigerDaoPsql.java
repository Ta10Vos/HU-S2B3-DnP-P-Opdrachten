package infra.dao;

import domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDaoPsql implements IReizigerDao {

    private Connection connection;
    private IOvChipkaartDao ovChipkaartDao;
    private IAdresDao adresDao;

    public ReizigerDaoPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        // Create new reiziger with params
        String sql = "INSERT INTO " +
                "reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, reiziger.getReizigerId());
        pst.setString(2, reiziger.getVoorletters());
        pst.setString(3, reiziger.getTussenvoegsel());
        pst.setString(4, reiziger.getAchternaam());
        pst.setDate(5, reiziger.getGeboortedatum());

        boolean result = pst.execute();

        pst.close();
        return result;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        // Update reiziger with params
        String sql = "UPDATE " +
                "reiziger " +
                "SET voorletters=?, " +
                "tussenvoegsel=?, " +
                "achternaam=?, " +
                "geboortedatum=? " +
                "WHERE reiziger_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, reiziger.getVoorletters());
        pst.setString(2, reiziger.getTussenvoegsel());
        pst.setString(3, reiziger.getAchternaam());
        pst.setDate(4, reiziger.getGeboortedatum());
        pst.setInt(5, reiziger.getReizigerId());

        boolean result = pst.execute();

        pst.close();
        return result;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        // Delete reiziger with id param
        String sql = "DELETE FROM reiziger " +
                "WHERE reiziger_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, reiziger.getReizigerId());

        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        // Make sure we only fetch 1 reiziger with id param
        String sql = "SELECT * FROM reiziger " +
                "WHERE reiziger_id=? " +
                "LIMIT 1;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);

        // Check if execution went well before getting the results
        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        // If there's no results, return null.
        if (!rs.next()) return null;

        Reiziger r = new Reiziger();

        r.setReizigerId(rs.getInt("reiziger_id"));
        r.setVoorletters(rs.getString("voorletters"));
        r.setTussenvoegsel(rs.getString("tussenvoegsel"));
        r.setAchternaam(rs.getString("achternaam"));
        r.setGeboortedatum(rs.getDate("geboortedatum"));

        pst.close();
        return r;
    }

    @Override
    public List<Reiziger> findByGeboorteDatum(Date date) throws SQLException {
        // Make sure we only fetch 1 reiziger with id param
        String sql = "SELECT * FROM reiziger " +
                "WHERE geboortedatum=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setDate(1, date);

        // Check if execution went well before getting the results
        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();
        ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();

        // Loop through results
        while (rs.next()) {
            Reiziger r = new Reiziger();

            r.setReizigerId(rs.getInt("reiziger_id"));
            r.setVoorletters(rs.getString("voorletters"));
            r.setTussenvoegsel(rs.getString("tussenvoegsel"));
            r.setAchternaam(rs.getString("achternaam"));
            r.setGeboortedatum(rs.getDate("geboortedatum"));

            reizigers.add(r);
        }

        pst.close();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        // Select all reizigers
        String sql = "SELECT * FROM reiziger;";

        PreparedStatement pst = connection.prepareStatement(sql);

        // Check if execution went well before getting the results
        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();
        ArrayList<Reiziger> reizigers = new ArrayList<Reiziger>();

        // Loop through results
        while (rs.next()) {
            Reiziger r = new Reiziger();

            r.setReizigerId(rs.getInt("reiziger_id"));
            r.setVoorletters(rs.getString("voorletters"));
            r.setTussenvoegsel(rs.getString("tussenvoegsel"));
            r.setAchternaam(rs.getString("achternaam"));
            r.setGeboortedatum(rs.getDate("geboortedatum"));

            reizigers.add(r);
        }

        pst.close();
        return reizigers;
    }

    public void setAdresDao(IAdresDao adresDaoPsql) {

    }

    public void setOvChipkaartDao(IOvChipkaartDao ovChipkaartDaoPsql) {

    }
}
