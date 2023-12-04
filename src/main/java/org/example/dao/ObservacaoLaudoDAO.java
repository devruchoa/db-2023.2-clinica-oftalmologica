package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.ObservacaoLaudo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObservacaoLaudoDAO extends ConnectionDB {

    private static final String TABLE_NAME = "observacoes_laudos";
    private static final String INSERT_OBSERVACAO_LAUDO_SQL = "INSERT INTO " + TABLE_NAME + " (descricao, id_receita_oculos) VALUES (?, ?)";
    private static final String SELECT_OBSERVACAO_LAUDO_BY_ID = "SELECT id, descricao, id_receita_oculos FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_OBSERVACAO_LAUDO = "SELECT id, descricao, id_receita_oculos FROM " + TABLE_NAME;
    private static final String DELETE_OBSERVACAO_LAUDO_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_OBSERVACAO_LAUDO_SQL = "UPDATE " + TABLE_NAME + " SET descricao = ?, id_receita_oculos = ? WHERE id = ?";
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

    public void insertObservacaoLaudo(ObservacaoLaudo entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_OBSERVACAO_LAUDO_SQL)) {
            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setInt(2, entity.getReceitaOculosId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservacaoLaudo selectObservacaoLaudo(int id) {
        ObservacaoLaudo entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_OBSERVACAO_LAUDO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                Integer receitaOculosId = rs.getInt("id_receita_oculos");
                entity = new ObservacaoLaudo(id, descricao, receitaOculosId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<ObservacaoLaudo> selectAllObservacaoLaudo() {
        List<ObservacaoLaudo> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_OBSERVACAO_LAUDO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                Integer receitaOculosId = rs.getInt("id_receita_oculos");
                entities.add(new ObservacaoLaudo(id, descricao, receitaOculosId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public void deleteObservacaoLaudo(int id) {
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_OBSERVACAO_LAUDO_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateObservacaoLaudo(ObservacaoLaudo entity) {
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_OBSERVACAO_LAUDO_SQL)) {
            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setInt(2, entity.getReceitaOculosId());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
