package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.EspecialidadeMedica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeMedicaDAO extends ConnectionDB {

    private static final String TABLE_NAME = "especialidades_medicas";
    private static final String INSERT_ESPECIALIDADE_MEDICA = "INSERT INTO " + TABLE_NAME + " (observacao, dt_conclusao, id_especialidade, id_medico) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ESPECIALIDADE_MEDICA_BY_ID = "SELECT id, observacao, dt_conclusao, id_especialidade, id_medico FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_ESPECIALIDADE_MEDICA = "SELECT id, observacao, dt_conclusao, id_especialidade, id_medico FROM " + TABLE_NAME;
    private static final String DELETE_ESPECIALIDADE_MEDICA = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_ESPECIALIDADE_MEDICA = "UPDATE " + TABLE_NAME + " SET observacao = ?, dt_conclusao = ?, id_especialidade = ?, id_medico = ? WHERE id = ?";
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

    public void insertEspecialidadeMedica(EspecialidadeMedica entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_ESPECIALIDADE_MEDICA)) {
            preparedStatement.setString(1, entity.getObservacao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDtConclusao()));
            preparedStatement.setInt(3, entity.getEspecialidadeID());
            preparedStatement.setInt(4, entity.getMedicoID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public EspecialidadeMedica selectEspecialidadeMedica(int id) {
        EspecialidadeMedica entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ESPECIALIDADE_MEDICA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String observacao = rs.getString("observacao");
                LocalDate dtConclusao = rs.getDate("dt_conclusao").toLocalDate();
                Integer especialidadeId = rs.getInt("id_especialidade");
                Integer medicoId = rs.getInt("id_medico");
                entity = new EspecialidadeMedica(id, observacao, dtConclusao, especialidadeId, medicoId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<EspecialidadeMedica> selectAllEspecialidadeMedica() {
        List<EspecialidadeMedica> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_ESPECIALIDADE_MEDICA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String observacao = rs.getString("observacao");
                LocalDate dtConclusao = rs.getDate("dt_conclusao").toLocalDate();
                Integer especialidadeId = rs.getInt("id_especialidade");
                Integer medicoId = rs.getInt("id_medico");
                entities.add(new EspecialidadeMedica(id, observacao, dtConclusao, especialidadeId, medicoId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public void deleteEspecialidadeMedica(int id) {
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_ESPECIALIDADE_MEDICA)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEspecialidadeMedica(EspecialidadeMedica entity) {
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_ESPECIALIDADE_MEDICA)) {
            preparedStatement.setString(1, entity.getObservacao());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDtConclusao()));
            preparedStatement.setInt(3, entity.getEspecialidadeID());
            preparedStatement.setInt(4, entity.getMedicoID());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
