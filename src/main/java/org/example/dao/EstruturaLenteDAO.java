package org.example.dao;

import org.example.config.ConnectionDB;
import org.example.model.EstruturaLente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstruturaLenteDAO extends ConnectionDB {

    private static final String TABLE_NAME = "estruturas_lentes";
    private static final String INSERT_ESTRUTURA_LENTE_SQL = "INSERT INTO " + TABLE_NAME + " (tipo_correcao, distancia_pupilar, id_receita_oculos) VALUES (?, ?, ?)";
    private static final String SELECT_ESTRUTURA_LENTE_BY_ID = "SELECT id, tipo_correcao, distancia_pupilar, id_receita_oculos FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL_ESTRUTURA_LENTE = "SELECT id, tipo_correcao, distancia_pupilar, id_receita_oculos FROM " + TABLE_NAME;
    private static final String DELETE_ESTRUTURA_LENTE_SQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String UPDATE_ESTRUTURA_LENTE_SQL = "UPDATE " + TABLE_NAME + " SET tipo_correcao = ?, distancia_pupilar = ?, id_receita_oculos = ? WHERE id = ?";
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

    public void insertEstruturaLente(EstruturaLente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(INSERT_ESTRUTURA_LENTE_SQL)) {
            preparedStatement.setString(1, entity.getTipoCorrecao());
            preparedStatement.setDouble(2, entity.getDistanciaPupilar());
            preparedStatement.setInt(3, entity.getReceitaOculosId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public EstruturaLente selectEstruturaLente(int id) {
        EstruturaLente entity = null;
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ESTRUTURA_LENTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String tipoCorrecao = rs.getString("tipo_correcao");
                Integer distanciaPupilar = rs.getInt("distancia_pupilar");
                Integer receitaOculosId = rs.getInt("id_receita_oculos");
                entity = new EstruturaLente(id, tipoCorrecao, distanciaPupilar, receitaOculosId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public List<EstruturaLente> selectAllEstruturaLente() {
        List<EstruturaLente> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareSQL(SELECT_ALL_ESTRUTURA_LENTE)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String tipoCorrecao = rs.getString("tipo_correcao");
                Integer distanciaPupilar = rs.getInt("distancia_pupilar");
                Integer receitaOculosId = rs.getInt("id_receita_oculos");
                entities.add(new EstruturaLente(id, tipoCorrecao, distanciaPupilar, receitaOculosId));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    public boolean deleteEstruturaLente(int id) {
        boolean rowDeleted = false;
        try (PreparedStatement preparedStatement = prepareSQL(DELETE_ESTRUTURA_LENTE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public void updateEstruturaLente(EstruturaLente entity) {
        try (PreparedStatement preparedStatement = prepareSQL(UPDATE_ESTRUTURA_LENTE_SQL)) {
            preparedStatement.setString(1, entity.getTipoCorrecao());
            preparedStatement.setDouble(2, entity.getDistanciaPupilar());
            preparedStatement.setInt(3, entity.getReceitaOculosId());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
