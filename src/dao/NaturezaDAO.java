/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import ConectaBanco.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Natureza;

/**
 *
 * @author edson
 */
public class NaturezaDAO {

    private final ConexaoBD conex;

    public NaturezaDAO() {
        conex = new ConexaoBD();
    }

    public List<Natureza> listarNaturezas() {
        conex.conexao();
        List<Natureza> naturezas = new ArrayList<>();

        String sql = "SELECT id_natureza,id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, "
                + "usuario_natureza, stnat FROM natureza";

        try (PreparedStatement stmt = conex.preparaSql(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Natureza natureza = new Natureza();
                natureza.setId_natureza(resultado.getInt("id_natureza"));
                natureza.setId_referencia(resultado.getInt("id_referencianatureza"));
                natureza.setTipo_natureza(resultado.getString("tipo_natureza"));
                natureza.setDesc_natureza(resultado.getString("desc_natureza"));
                natureza.setRegistro_natureza(resultado.getString("registro_natureza"));
                natureza.setUsuario_natureza(resultado.getString("usuario_natureza"));
                natureza.setStatus_natureza(resultado.getInt("stnat"));

                naturezas.add(natureza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conex.desconecta();
        }

        return naturezas;
    }

    public List<Natureza> listarNaturezasAtivas() {
        conex.conexao();
        List<Natureza> naturezas = new ArrayList<>();

        String sql = "SELECT id_natureza,id_referencianatureza, tipo_natureza, desc_natureza, registro_natureza, "
                + "usuario_natureza, stnat FROM natureza WHERE stnat=1 ";

        try (PreparedStatement stmt = conex.preparaSql(sql)) {
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Natureza natureza = new Natureza();
                natureza.setId_natureza(resultado.getInt("id_natureza"));
                natureza.setId_referencia(resultado.getInt("id_referencianatureza"));
                natureza.setTipo_natureza(resultado.getString("tipo_natureza"));
                natureza.setDesc_natureza(resultado.getString("desc_natureza"));
                natureza.setRegistro_natureza(resultado.getString("registro_natureza"));
                natureza.setUsuario_natureza(resultado.getString("usuario_natureza"));
                natureza.setStatus_natureza(resultado.getInt("stnat"));

                naturezas.add(natureza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conex.desconecta();
        }

        return naturezas;
    }
}
