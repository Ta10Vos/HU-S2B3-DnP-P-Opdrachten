package infra.dao;

import domain.*;

import java.sql.*;
import java.util.List;

public class OvChipkaartDaoPsql implements IOvChipkaartDao {
    private Connection connection;
    private IProductDao pDao;

    public OvChipkaartDaoPsql(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean save(OvChipkaart ovChipkaart) throws SQLException {
    }

    @Override
    public boolean update(OvChipkaart ovChipkaart) throws SQLException {
    }

    @Override
    public boolean delete(OvChipkaart ovChipkaart) throws SQLException {
    }

    @Override
    public OvChipkaart findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<OvChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        return null;
    }

    @Override
    public List<OvChipkaart> findAll() throws SQLException {
        return null;
    }

    public void setProductDao(IProductDao productDao) {
        this.pDao = productDao;
    }
}
