package infra.dao;

import domain.Adres;
import domain.IAdresDao;
import domain.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDaoPsql implements IAdresDao {
    private Connection connection;

    public AdresDaoPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Adres adres) throws SQLException {
        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        String sql = "DELETE FROM adres " +
                "WHERE id=?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, adres.getAdresId());

        boolean result = pst.execute();
        pst.close();
        return result;
    }

    @Override
    public Adres findById(int id) throws SQLException {
        String sql = "SELECT * FROM adres " +
                "WHERE id=?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, id);

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return null;

        // logic

        pst.close();
        return null;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        String sql = "SELECT * FROM adres" +
                "WHERE reiziger_id=?";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, reiziger.getReizigerId());

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return null;

        // logic

        pst.close();
        return null;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        String sql = "SELECT * FROM adres";

        PreparedStatement pst = connection.prepareStatement(sql);

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return new ArrayList<Adres>();

        // logic

        pst.close();
        return null;
    }
}
