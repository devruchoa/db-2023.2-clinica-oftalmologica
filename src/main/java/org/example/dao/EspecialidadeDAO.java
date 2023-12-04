package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.Especialidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO extends ConnectionDB {

    private static final String TABLE_NAME = "especialidades";
    private static final String INSERT_ESPECIALIDADE_SQL = "INSERT INTO " + TABLE_NAME + " (descricao, conselho) VALUES (?, ?)";
    private static final String SELECT_ESPECIALIDADE_BY_ID = "SELECT id, descricao, conselho FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_ESPECIALIDADE = "SELECT id, descricao, conselho FROM " + TABLE_NAME;
    private static final String DELETE_ESPECIALIDADE_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_ESPECIALIDADE_SQL = "UPDATE " + TABLE_NAME + " SET descricao = ?, conselho = ? WHERE id = ?";
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

    public void insertEspecialidade(Especialidade entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_ESPECIALIDADE_SQL)) {
            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setString(2, entity.getConselho());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Especialidade selectEspecialidade(int id) {
        Especialidade entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ESPECIALIDADE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String conselho = rs.getString("conselho");
                entity = new Especialidade(id, descricao, conselho);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<Especialidade> selectAllEspecialidades() {
        List<Especialidade> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_ESPECIALIDADE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                String conselho = rs.getString("conselho");
                entities.add(new Especialidade(id, descricao, conselho));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public boolean deleteEspecialidade(int id) {
        boolean rowDeleted;
        try (PreparedStatement statement = prepareSQL(DELETE_ESPECIALIDADE_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateEspecialidade(Especialidade entity) {
        boolean rowUpdated;
        try (PreparedStatement statement = prepareSQL(UPDATE_ESPECIALIDADE_SQL)) {
            statement.setString(1, entity.getDescricao());
            statement.setString(2, entity.getConselho());
            statement.setInt(3, entity.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
