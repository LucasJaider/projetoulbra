/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno.saolucas
 */
public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController(){
        usuarioDAO = new UsuarioDAO();
    }
    
     public boolean autenticar(String email,String senha){
         if(usuarioDAO.autenticar(email, senha)){
             return true;
         }else {
             JOptionPane.showMessageDialog(null,"Usuario ou senha incorreta");
             return false;
         }
     }
     
     
     public boolean adicionarUsuario(Usuario u){
       return usuarioDAO.adicionarUsuario(u);
          
     }
    
     public List <Usuario> readForDesc(String desc){
         return usuarioDAO.readForDesc(desc);
     }
     
     public Usuario readForPk(int pk){
         return usuarioDAO.readForPk(pk);
     }
     
     public boolean alterarUsuario(Usuario u) {
         return usuarioDAO.alterarUsuario(u);
     }
     
    
     
}
