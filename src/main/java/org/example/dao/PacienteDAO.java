package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends ConnectionDB {

    private static final String TABLE_NAME = "pacientes";
    private static final String INSERT_PACIENTE_SQL = "INSERT INTO " + TABLE_NAME + " (nome, cpf, dt_nascimento) VALUES (?, ?, ?)";
    private static final String SELECT_PACIENTE_BY_ID = "SELECT id, nome, cpf, dt_nascimento FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_PACIENTE = "SELECT id, nome, cpf, dt_nascimento FROM " + TABLE_NAME;
    private static final String DELETE_PACIENTE_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_PACIENTE_SQL = "UPDATE " + TABLE_NAME + " SET nome = ?, cpf = ?, dt_nascimento = ? WHERE id = ?";
    private static final String PACIENTE_MAIS_VELHO_COM_CONSULTA_SQL = "SELECT p.id, p.nome, p.cpf, p.dt_nascimento FROM " + TABLE_NAME + " p " +
            "INNER JOIN consultas_medicas c ON c.id_paciente = p.id " +
            "ORDER BY p.dt_nascimento ASC " +
            "LIMIT 1";
    private static final String PACIENTE_MAIS_NOVO_COM_CONSULTA_SQL = "SELECT p.id, p.nome, p.cpf, p.dt_nascimento FROM " + TABLE_NAME + " p " +
            "INNER JOIN consultas_medicas c ON c.id_paciente = p.id " +
            "ORDER BY p.dt_nascimento DESC " +
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

    public void insertPaciente(Paciente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_PACIENTE_SQL)) {
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getCpf());
            preparedStatement.setDate(3, java.sql.Date.valueOf(entity.getDataNascimento()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente selectPaciente(int id) {
        Paciente entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_PACIENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                LocalDate dataNascimento = rs.getDate("dt_nascimento").toLocalDate();
                entity = new Paciente(id, nome, cpf, dataNascimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<Paciente> selectAllPaciente() {
        List<Paciente> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_PACIENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                LocalDate dataNascimento = rs.getDate("dt_nascimento").toLocalDate();
                entities.add(new Paciente(id, nome, cpf, dataNascimento));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public void deletePaciente(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_PACIENTE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePaciente(Paciente entity) {
        boolean rowUpdated = false;
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_PACIENTE_SQL)) {
            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getCpf());
            preparedStatement.setDate(3, java.sql.Date.valueOf(entity.getDataNascimento()));
            preparedStatement.setInt(4, entity.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente selectPacienteMaisVelhoComConsulta() {
        Paciente entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(PACIENTE_MAIS_VELHO_COM_CONSULTA_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                LocalDate dataNascimento = rs.getDate("dt_nascimento").toLocalDate();
                entity = new Paciente(id, nome, cpf, dataNascimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public Paciente selectPacienteMaisNovoComConsulta() {
        Paciente entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(PACIENTE_MAIS_NOVO_COM_CONSULTA_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                LocalDate dataNascimento = rs.getDate("dt_nascimento").toLocalDate();
                entity = new Paciente(id, nome, cpf, dataNascimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
}
