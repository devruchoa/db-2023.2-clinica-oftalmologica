package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.EspecificacaoLente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecificacaoLenteDAO extends ConnectionDB {

    private static final String TABLE_NAME = "especificacoes_lente";
    private static final String INSERT_ESPECIFICACAO_LENTE = "INSERT INTO " + TABLE_NAME + " (valor, id_estrutura_lente, id_atributo_estrutura_lente) VALUES (?, ?, ?)";
    private static final String SELECT_ESPECIFICACAO_LENTE_BY_ID = "SELECT id, valor, id_estrutura_lente, id_atributo_estrutura_lente FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_ESPECIFICACAO_LENTE = "SELECT id, valor, id_estrutura_lente, id_atributo_estrutura_lente FROM " + TABLE_NAME;
    private static final String DELETE_ESPECIFICACAO_LENTE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_ESPECIFICACAO_LENTE = "UPDATE " + TABLE_NAME + " SET valor = ?, id_estrutura_lente = ?, id_atributo_estrutura_lente = ? WHERE id = ?";
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

    public void insertEspecificacaoLente(EspecificacaoLente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_ESPECIFICACAO_LENTE)) {
            preparedStatement.setFloat(1, entity.getValor());
            preparedStatement.setInt(2, entity.getEstruturaLenteId());
            preparedStatement.setInt(3, entity.getAtributoEstruturaLenteId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public EspecificacaoLente selectEspecificacaoLente(int id) {
        EspecificacaoLente entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ESPECIFICACAO_LENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Float valor = rs.getFloat("valor");
                Integer estruturaLenteId = rs.getInt("id_estrutura_lente");
                Integer atributoEstruturaLenteId = rs.getInt("id_atributo_estrutura_lente");
                entity = new EspecificacaoLente(id, valor, estruturaLenteId, atributoEstruturaLenteId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<EspecificacaoLente> selectAllEspecificacaoLente() {
        List<EspecificacaoLente> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_ESPECIFICACAO_LENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                Float valor = rs.getFloat("valor");
                Integer estruturaLenteId = rs.getInt("id_estrutura_lente");
                Integer atributoEstruturaLenteId = rs.getInt("id_atributo_estrutura_lente");
                entities.add(new EspecificacaoLente(id, valor, estruturaLenteId, atributoEstruturaLenteId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public void deleteEspecificacaoLente(int id) {
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_ESPECIFICACAO_LENTE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEspecificacaoLente(EspecificacaoLente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_ESPECIFICACAO_LENTE)) {
            preparedStatement.setFloat(1, entity.getValor());
            preparedStatement.setInt(2, entity.getEstruturaLenteId());
            preparedStatement.setInt(3, entity.getAtributoEstruturaLenteId());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
