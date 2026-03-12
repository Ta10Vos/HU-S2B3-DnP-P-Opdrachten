package infra.dao;

import domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OvChipkaartDaoPsql implements IOvChipkaartDao {
    private Connection connection;
    private IProductDao pDao;

    public OvChipkaartDaoPsql(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean save(OvChipkaart ovChipkaart) throws SQLException {
        String sql = "INSERT INT0 " +
                "ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement pst = connection.prepareStatement(sql);

        int rzgId = ovChipkaart.getReiziger().getReizigerId();

        pst.setInt(1, ovChipkaart.getKaartNummer());
        pst.setDate(2, ovChipkaart.getGeldigTot());
//        pst.setLong(3, ovChipkaart.getKlasse());
        pst.setBigDecimal(4, ovChipkaart.getSaldo());
        pst.setInt(5, rzgId);

        // Check if at least 1 row action was executed
        boolean result = pst.executeUpdate() > 0;

        pst.close();
        return result;
    }

    @Override
    public boolean update(OvChipkaart ovChipkaart) throws SQLException {
        String sql = "UPDATE ov_chipkaart " +
                "SET" +
                "geldig_tot=?, " +
                "klasse=?, " +
                "saldo=?, " +
                "reiziger_id=? " +
                "WHERE kaart_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        int rzgId = ovChipkaart.getReiziger().getReizigerId();

        pst.setDate(1, ovChipkaart.getGeldigTot());
//        pst.setLong(2, ovChipkaart.getKlasse());
        pst.setBigDecimal(3, ovChipkaart.getSaldo());
        pst.setInt(4, rzgId);
        pst.setInt(5, ovChipkaart.getKaartNummer());

        // Check if at least 1 row action was executed
        boolean result = pst.executeUpdate() > 0;

        pst.close();
        return result;
    }

    @Override
    public boolean delete(OvChipkaart ovChipkaart) throws SQLException {
        String sql = "DELETE FROM ov_chipkaart " +
                "WHERE kaart_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, ovChipkaart.getKaartNummer());

        boolean result = pst.executeUpdate() > 0;

        pst.close();
        return result;
    }

    @Override
    public OvChipkaart findById(int id) throws SQLException {
        String sql = "SELECT " +
                "kaart_nummer, " +
                "geldig_tot, " +
                "klasse, " +
                "saldo, " +
                "reiziger_id " +
                "FROM ov_chipkaart " +
                "WHERE kaart_nummer=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, id);

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();

        if (!rs.next()) return null;

        OvChipkaart ock = new OvChipkaart();

        ock.setKaartNummer(rs.getInt("kaart_nummer"));
        ock.setGeldigTot(rs.getDate("geldig_tot"));
//        ock.setKlasse(rs.getInt("klasse"));
        ock.setSaldo(rs.getBigDecimal("saldo"));

        rs.close();
        pst.close();
        return ock;
    }

    @Override
    public List<OvChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        String sql = "SELECT " +
                "kaart_nummer, " +
                "geldig_tot, " +
                "klasse, " +
                "saldo, " +
                "reiziger_id " +
                "FROM ov_chipkaart " +
                "WHERE reiziger_id=?;";

        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setInt(1, reiziger.getReizigerId());

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();
        List<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();

        while(rs.next()) {
            OvChipkaart ock = new OvChipkaart();

            ock.setKaartNummer(rs.getInt("kaart_nummer"));
            ock.setGeldigTot(rs.getDate("geldig_tot"));
//            ock.setKlasse(rs.getInt("klasse"));
            ock.setSaldo(rs.getBigDecimal("saldo"));

            ovChipkaarten.add(ock);
        }

        rs.close();
        pst.close();
        return ovChipkaarten;
    }

    @Override
    public List<OvChipkaart> findAll() throws SQLException {
        String sql = "SELECT " +
                "kaart_nummer, " +
                "geldig_tot, " +
                "klasse, " +
                "saldo, " +
                "reiziger_id " +
                "FROM ov_chipkaart;";

        PreparedStatement pst = connection.prepareStatement(sql);

        if (!pst.execute()) return null;

        ResultSet rs = pst.getResultSet();
        List<OvChipkaart> ovChipkaarten = new ArrayList<OvChipkaart>();

        while(rs.next()) {
            OvChipkaart ock = new OvChipkaart();

            ock.setKaartNummer(rs.getInt("kaart_nummer"));
            ock.setGeldigTot(rs.getDate("geldig_tot"));
//            ock.setKlasse(rs.getInt("klasse"));
            ock.setSaldo(rs.getBigDecimal("saldo"));

            ovChipkaarten.add(ock);
        }

        rs.close();
        pst.close();
        return ovChipkaarten;
    }

    public void setProductDao(IProductDao productDao) {
        this.pDao = productDao;
    }
}
