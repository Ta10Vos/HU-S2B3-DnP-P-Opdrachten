package infra.dao;

import domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReizigerDaoPsql implements IReizigerDao {

    private Connection connection;
    private IOvChipkaartDao ockDao;
    private IAdresDao aDao;

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

        Adres adr = reiziger.getAdres();
        if (adr != null) {
            aDao.save(adr);
        }

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

        Reiziger oldRzg = this.findById(reiziger.getReizigerId());

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, reiziger.getVoorletters());
        pst.setString(2, reiziger.getTussenvoegsel());
        pst.setString(3, reiziger.getAchternaam());
        pst.setDate(4, reiziger.getGeboortedatum());
        pst.setInt(5, reiziger.getReizigerId());

        Adres adr = reiziger.getAdres();
        Adres oldAdr = oldRzg.getAdres();

        if (!Objects.equals(adr, oldAdr)) {
            if (oldAdr != null) {
                aDao.delete(oldAdr);
            }
            if (adr != null) {
                aDao.save(adr);
            }
        }

        boolean result = pst.execute();

        pst.close();
        return result;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        // Delete reiziger with id param
        String sql = "DELETE FROM reiziger " +
                "WHERE reiziger_id=?;";

        boolean result = true;

        Adres adr = reiziger.getAdres();
        // Delete adres
        if (adr != null) {
            result = aDao.delete(adr);
        }

        // Only delete reiziger if relations have been successfully removed
        if (result) {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, reiziger.getReizigerId());

            result = pst.execute();
            pst.close();
        }

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
        r.setAdres(aDao.findByReiziger(r));

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
            r.setAdres(aDao.findByReiziger(r));

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
            r.setAdres(aDao.findByReiziger(r));

            reizigers.add(r);
        }

        pst.close();
        return reizigers;
    }

    public void setAdresDao(IAdresDao adresDaoPsql) {
        this.aDao = adresDaoPsql;
    }

    public void setOvChipkaartDao(IOvChipkaartDao ovChipkaartDaoPsql) {

    }
}
