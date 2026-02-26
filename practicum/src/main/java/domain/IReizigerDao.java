package domain;

import infra.dao.OvChipkaartDaoPsql;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IReizigerDao {
    boolean save(Reiziger reiziger) throws SQLException;
    boolean update(Reiziger reiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
    Reiziger findById(int id) throws SQLException;

    List<Reiziger> findByGeboorteDatum(Date date) throws SQLException;

    List<Reiziger> findAll() throws SQLException;
}
