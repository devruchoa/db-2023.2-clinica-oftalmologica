package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends ConnectionDB {

    private static final String TABLE_NAME = "medicos";
    private static final String INSERT_MEDICO_SQL = "INSERT INTO " + TABLE_NAME + " (nome, crm) VALUES (?, ?)";
    private static final String SELECT_MEDICO_BY_ID = "SELECT id, nome, crm FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_MEDICO = "SELECT id, nome, crm FROM " + TABLE_NAME;
    private static final String DELETE_MEDICO_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_MEDICO_SQL = "UPDATE " + TABLE_NAME + " SET nome = ?, crm = ? WHERE id = ?";
    private static final String MEDICO_COM_MAIS_PACIENTES_SQL = "SELECT m.id, m.nome, m.crm, count(1) as total FROM " + TABLE_NAME + " m " +
            "INNER JOIN consultas_medicas c ON c.id_medico = m.id " +
            "GROUP BY m.id, m.nome, m.crm " +
            "ORDER BY total DESC " +
            "LIMIT 1";
    private static final String MEDICO_COM_MENOS_PACIENTES_SQL = "SELECT m.id, m.nome, m.crm, count(1) as total FROM " + TABLE_NAME + " m " +
            "INNER JOIN consultas_medicas c ON c.id_medico = m.id " +
            "GROUP BY m.id, m.nome, m.crm " +
            "ORDER BY total ASC " +
            "LIMIT 1";
    private static final String MEDICO_COM_CONSULTAS_MAIS_RECENTE_SQL = "SELECT m.id, m.nome, m.crm, c.dt_consulta FROM " + TABLE_NAME + " m " +
            "INNER JOIN consultas_medicas c ON c.id_medico = m.id " +
            "ORDER BY c.dt_consulta DESC " +
            "LIMIT 1";
    private static final String TOTAL = "SELECT count(1) FROM " + TABLE_NAME;

    public int count() {
        int count = 0;
        try (PreparedStatement preparedStatement = prepareSQL(TOTAL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int insertMedico(Medico entity) {
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_MEDICO_SQL)) {
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getCrm());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public Medico selectMedico(int id) {
        Medico entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_MEDICO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");
                entity = new Medico(id, nome, crm);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<Medico> selectAllMedico() {
        List<Medico> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_MEDICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");
                entities.add(new Medico(id, nome, crm));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public int deleteMedico(int id) {
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_MEDICO_SQL)) {
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public int updateMedico(Medico entity) {
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_MEDICO_SQL)) {
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getCrm());
            preparedStatement.setInt(3, entity.getId());
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }

    public Medico medicoComMaisPacientes() {
        Medico entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(MEDICO_COM_MAIS_PACIENTES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");
                entity = new Medico(id, nome, crm);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public Medico medicoComMenosPacientes() {
        Medico entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(MEDICO_COM_MENOS_PACIENTES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");
                entity = new Medico(id, nome, crm);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public Medico medicoComConsultasMaisRecente() {
        Medico entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(MEDICO_COM_CONSULTAS_MAIS_RECENTE_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String crm = rs.getString("crm");
                entity = new Medico(id, nome, crm);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
}
