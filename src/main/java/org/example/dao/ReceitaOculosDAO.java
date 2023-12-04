package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.ReceitaOculos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReceitaOculosDAO extends ConnectionDB {

    private static final String TABLE_NAME = "receitas_oculos";
    private static final String INSERT_RECEITA_OCULOS_SQL = "INSERT INTO " + TABLE_NAME + " (detalhamento, dt_consulta, id_consulta_medica) VALUES (?, ?, ?)";
    private static final String SELECT_RECEITA_OCULOS_BY_ID = "SELECT id, detalhamento, dt_consulta, id_consulta_medica FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_RECEITA_OCULOS = "SELECT id, detalhamento, dt_consulta, id_consulta_medica FROM " + TABLE_NAME;
    private static final String DELETE_RECEITA_OCULOS_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_RECEITA_OCULOS_SQL = "UPDATE " + TABLE_NAME + " SET detalhamento = ?, dt_consulta = ?, id_consulta_medica = ? WHERE id = ?";
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

    public void insertReceitaOculos(ReceitaOculos entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_RECEITA_OCULOS_SQL)) {
            preparedStatement.setString(1, entity.getDetalhamento());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDataConsulta()));
            preparedStatement.setInt(3, entity.getIdConsultaMedica());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ReceitaOculos selectReceitaOculos(int id) {
        ReceitaOculos entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_RECEITA_OCULOS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String detalhamento = rs.getString("detalhamento");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                Integer idConsultaMedica = rs.getInt("id_consulta_medica");
                entity = new ReceitaOculos(id, detalhamento, dataConsulta, idConsultaMedica);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<ReceitaOculos> selectAllReceitaOculos() {
        List<ReceitaOculos> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_RECEITA_OCULOS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String detalhamento = rs.getString("detalhamento");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                Integer idConsultaMedica = rs.getInt("id_consulta_medica");
                entities.add(new ReceitaOculos(id, detalhamento, dataConsulta, idConsultaMedica));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public boolean deleteReceitaOculos(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_RECEITA_OCULOS_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateReceitaOculos(ReceitaOculos entity) {
        boolean rowUpdated = false;
        try (PreparedStatement statement = prepareSQL(UPDATE_RECEITA_OCULOS_SQL)) {
            statement.setString(1, entity.getDetalhamento());
            statement.setDate(2, java.sql.Date.valueOf(entity.getDataConsulta()));
            statement.setInt(3, entity.getIdConsultaMedica());
            statement.setInt(5, entity.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }
}
