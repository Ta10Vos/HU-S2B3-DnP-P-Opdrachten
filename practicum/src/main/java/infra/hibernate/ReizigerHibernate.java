package infra.hibernate;

import domain.IReizigerDao;
import domain.Reiziger;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ReizigerHibernate implements IReizigerDao {

    private EntityManager entityManager;

    public ReizigerHibernate(EntityManager entityManager) {

    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        return false;
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Reiziger> findByGeboorteDatum(Date date) {
        return null;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        return null;
    }
}
