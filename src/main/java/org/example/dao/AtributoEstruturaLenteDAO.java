package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.AtributoEstruturaLente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtributoEstruturaLenteDAO extends ConnectionDB {

    private static final String TABLE_NAME = "atributos_estrutura_lente";
    private static final String INSERT_ATRIBUTO_ESTRUTURA_LENTE = "INSERT INTO " + TABLE_NAME + " (descricao, lado_olho) VALUES (?, ?)";
    private static final String SELECT_ATRIBUTO_ESTRUTURA_LENTE_BY_ID = "SELECT id, descricao, lado_olho FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_ATRIBUTO_ESTRUTURA_LENTE = "SELECT id, descricao, lado_olho FROM " + TABLE_NAME;
    private static final String DELETE_ATRIBUTO_ESTRUTURA_LENTE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_ATRIBUTO_ESTRUTURA_LENTE = "UPDATE " + TABLE_NAME + " SET descricao = ?, lado_olho = ? WHERE id = ?";
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

    public void insertAtributoEstruturaLente(AtributoEstruturaLente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_ATRIBUTO_ESTRUTURA_LENTE)) {
            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setString(2, entity.getLadoOlho());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public AtributoEstruturaLente selectAtributoEstruturaLente(int id) {
        AtributoEstruturaLente entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ATRIBUTO_ESTRUTURA_LENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String ladoOlho = rs.getString("lado_olho");
                entity = new AtributoEstruturaLente(id, descricao, ladoOlho);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<AtributoEstruturaLente> selectAllAtributoEstruturaLente() {
        List<AtributoEstruturaLente> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_ATRIBUTO_ESTRUTURA_LENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                String ladoOlho = rs.getString("lado_olho");
                entities.add(new AtributoEstruturaLente(id, descricao, ladoOlho));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public void deleteAtributoEstruturaLente(int id) {
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_ATRIBUTO_ESTRUTURA_LENTE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAtributoEstruturaLente(AtributoEstruturaLente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_ATRIBUTO_ESTRUTURA_LENTE)) {
            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setString(2, entity.getLadoOlho());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
