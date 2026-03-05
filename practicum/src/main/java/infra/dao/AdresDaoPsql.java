package infra.dao;

import domain.Adres;
import domain.IAdresDao;
import domain.IReizigerDao;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDaoPsql implements IAdresDao {
    private Connection connection;
    private IReizigerDao rdao;

    public AdresDaoPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        String sql = "INSERT INTO " +
                "adres(adres_id, postcode, huisnummer, straat, woonplaats) " +
                "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, adres.getAdresId());
        pst.setString(2, adres.getPostcode());
        pst.setString(3, adres.getHuisnummer());
        pst.setString(4, adres.getStraat());
        pst.setString(5, adres.getWoonplaats());

        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        String sql = "UPDATE adres " +
                "SET " +
                "postcode=?, " +
                "huisnummer=?, " +
                "straat=?, " +
                "woonplaats=? " +
                "WHERE adres_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, adres.getPostcode());
        pst.setString(2, adres.getHuisnummer());
        pst.setString(3, adres.getStraat());
        pst.setString(4, adres.getWoonplaats());
        pst.setInt(5, adres.getAdresId());

        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        String sql = "DELETE FROM adres " +
                "WHERE adres_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, adres.getAdresId());

        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public Adres findById(int id) throws SQLException {
        String sql = "SELECT * FROM adres " +
                "WHERE adres_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return null;

        Adres a = new Adres();
        a.setAdresId(rs.getInt("adres_id"));
        a.setPostcode(rs.getString("postcode"));
        a.setHuisnummer(rs.getString("huisnummer"));
        a.setStraat(rs.getString("straat"));
        a.setWoonplaats(rs.getString("woonplaats"));

        pst.close();
        return a;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        String sql = "SELECT * FROM adres " +
                "WHERE reiziger_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        if (reiziger == null) {
            pst.setNull(1, Types.INTEGER);
        } else {
            pst.setInt(1, reiziger.getReizigerId());
        }

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return null;

        Adres a = new Adres();
        a.setAdresId(rs.getInt("adres_id"));
        a.setPostcode(rs.getString("postcode"));
        a.setHuisnummer(rs.getString("huisnummer"));
        a.setStraat(rs.getString("straat"));
        a.setWoonplaats(rs.getString("woonplaats"));

        pst.close();
        return a;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        String sql = "SELECT * FROM adres ";

        PreparedStatement pst = connection.prepareStatement(sql);

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return new ArrayList<Adres>();

        ArrayList<Adres> adressen = new ArrayList<Adres>();

        while (rs.next()) {
            Adres a = new Adres();
            a.setAdresId(rs.getInt("adres_id"));
            a.setPostcode(rs.getString("postcode"));
            a.setHuisnummer(rs.getString("huisnummer"));
            a.setStraat(rs.getString("straat"));
            a.setWoonplaats(rs.getString("woonplaats"));
            adressen.add(a);
        }

        pst.close();
        return adressen;
    }
}
