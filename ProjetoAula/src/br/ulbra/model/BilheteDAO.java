/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import br.ulbra.ulties.Ulties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author teste
 */
public class BilheteDAO {
    private GerenciadorConexao gerenciador;
    public BilheteDAO(){
        this.gerenciador = GerenciadorConexao.getInstancia();
    }
    
        public boolean adicionarBilhete(Bilhete r){
            String sql = "INSERT into TBBILHETE (placa, numero, tarifa)"
                + "VALUES (?,?,?)";
        try {
          //  byte[] iconBytes = Ulties.iconToBytes(r.getImagem());
            
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, r.getPlaca());
           
            stmt.setInt(2, r.getNumero());
         
            stmt.setInt(3, r.getTarifa());
            //stmt.setBytes(6, iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Bilhete inserido com sucesso !");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
       }// catch (IOException e) {
       //     JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        //}
        return false;
    }
    
}
