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
public class RotativoDAO {
    private GerenciadorConexao gerenciador;
    public RotativoDAO(){
        this.gerenciador = GerenciadorConexao.getInstancia();
    }
    
        public boolean adicionarRotativo(Rotativo r){
        String sql = "INSERT into TBROTATIVO (placa, local, numero, pais, vaga, regular)"
                + "VALUES (?,?,?,?,?, ?)";
        try {
          //  byte[] iconBytes = Ulties.iconToBytes(r.getImagem());
            
            PreparedStatement stmt = gerenciador.getConexao().prepareStatement(sql);
            stmt.setString(1, r.getPlaca());
            stmt.setString(2, r.getLocal());
            stmt.setInt(3, r.getNumero());
            stmt.setInt(4, r.getPais());
            stmt.setInt(5, r.getVaga());
            stmt.setInt(6, r.getRegular());
            //stmt.setBytes(6, iconBytes);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Carro:" + r + " inserido com sucesso !");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
       }// catch (IOException e) {
       //     JOptionPane.showMessageDialog(null, "ERRO: " + e.getMessage());
        //}
        return false;
    }
    
   public boolean buscarBilhete (String placa, int numero) {
        String sql = "SELECT * FROM tbbilhete WHERE placa LIKE ?, numero = ?";
        GerenciadorConexao gerenciador = GerenciadorConexao.getInstancia();
        Connection con = gerenciador.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,placa);
            stmt.setInt(2,numero);
            
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                              
                return true;
                
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            GerenciadorConexao.closeConnection(con, stmt, rs);
        }
        return false;
    }
    
}
