package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.ConsultaMedica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicaDAO extends ConnectionDB {

    private static final String TABLE_NAME = "consultas_medicas";
    private static final String INSERT_CONSULTA_MEDICA_SQL = "INSERT INTO " + TABLE_NAME + " (assinatura, dt_consulta, id_paciente, id_medico) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CONSULTA_MEDICA_BY_ID = "SELECT id, assinatura, dt_consulta, id_paciente, id_medico FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_CONSULTA_MEDICA = "SELECT id, assinatura, dt_consulta, id_paciente, id_medico FROM " + TABLE_NAME;
    private static final String DELETE_CONSULTA_MEDICA_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_CONSULTA_MEDICA_SQL = "UPDATE " + TABLE_NAME + " SET assinatura = ?, dt_consulta = ?, id_paciente = ?, id_medico = ? WHERE id = ?";
    private static final String CONSULTA_MEDICA_MAIS_ANTIGA_SQL = "SELECT cm.id, cm.assinatura, cm.dt_consulta, cm.id_paciente, cm.id_medico FROM " + TABLE_NAME + " cm " +
            "INNER JOIN pacientes p ON p.id = cm.id_paciente " +
            "ORDER BY cm.dt_consulta ASC " +
            "LIMIT 1";
    private static final String CONSULTA_MEDICA_MAIS_RECENTE_SQL = "SELECT cm.id, cm.assinatura, cm.dt_consulta, cm.id_paciente, cm.id_medico FROM " + TABLE_NAME + " cm " +
            "INNER JOIN pacientes p ON p.id = cm.id_paciente " +
            "ORDER BY cm.dt_consulta DESC " +
            "LIMIT 1";
    private static final String CONSULTA_MEDICA_MAIS_ANTIGA_POR_PACIENTE_SQL = "SELECT cm.id, cm.assinatura, cm.dt_consulta, cm.id_paciente, cm.id_medico FROM " + TABLE_NAME + " cm " +
            "INNER JOIN pacientes p ON p.id = cm.id_paciente " +
            "WHERE cm.id_paciente = ? " +
            "ORDER BY cm.dt_consulta ASC " +
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

    public void insertConsultaMedica(ConsultaMedica entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_CONSULTA_MEDICA_SQL)) {
            preparedStatement.setString(1, entity.getAssinatura());
            preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDataConsulta()));
            preparedStatement.setInt(3, entity.getPacienteId());
            preparedStatement.setInt(4, entity.getMedicoId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ConsultaMedica selectConsultaMedica(int id) {
        ConsultaMedica entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_CONSULTA_MEDICA_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String assinatura = rs.getString("assinatura");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                int pacienteId = rs.getInt("id_paciente");
                int medicoId = rs.getInt("id_medico");
                entity = new ConsultaMedica(id, assinatura, dataConsulta, pacienteId, medicoId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<ConsultaMedica> selectAllConsultaMedica() {
        List<ConsultaMedica> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_CONSULTA_MEDICA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String assinatura = rs.getString("assinatura");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                int pacienteId = rs.getInt("id_paciente");
                int medicoId = rs.getInt("id_medico");
                entities.add(new ConsultaMedica(id, assinatura, dataConsulta, pacienteId, medicoId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public boolean deleteConsultaMedica(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_CONSULTA_MEDICA_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateConsultaMedica(ConsultaMedica entity) {
        boolean rowUpdated = false;
        try (PreparedStatement statement = prepareSQL(UPDATE_CONSULTA_MEDICA_SQL)) {
            statement.setString(1, entity.getAssinatura());
            statement.setDate(2, java.sql.Date.valueOf(entity.getDataConsulta()));
            statement.setInt(3, entity.getPacienteId());
            statement.setInt(4, entity.getMedicoId());
            statement.setInt(5, entity.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }

    public ConsultaMedica consultaMedicaMaisAntiga() {
        ConsultaMedica entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(CONSULTA_MEDICA_MAIS_ANTIGA_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String assinatura = rs.getString("assinatura");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                int pacienteId = rs.getInt("id_paciente");
                int medicoId = rs.getInt("id_medico");
                entity = new ConsultaMedica(id, assinatura, dataConsulta, pacienteId, medicoId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public ConsultaMedica consultaMedicaMaisRecente() {
        ConsultaMedica entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(CONSULTA_MEDICA_MAIS_RECENTE_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String assinatura = rs.getString("assinatura");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                int pacienteId = rs.getInt("id_paciente");
                int medicoId = rs.getInt("id_medico");
                entity = new ConsultaMedica(id, assinatura, dataConsulta, pacienteId, medicoId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public ConsultaMedica consultaMedicaMaisAntigaPorPaciente(int idPaciente) {
        ConsultaMedica entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(CONSULTA_MEDICA_MAIS_ANTIGA_POR_PACIENTE_SQL)) {
            preparedStatement.setInt(1, idPaciente);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String assinatura = rs.getString("assinatura");
                LocalDate dataConsulta = rs.getDate("dt_consulta").toLocalDate();
                int pacienteId = rs.getInt("id_paciente");
                int medicoId = rs.getInt("id_medico");
                entity = new ConsultaMedica(id, assinatura, dataConsulta, pacienteId, medicoId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
}
