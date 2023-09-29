/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.model.Bilhete;
import br.ulbra.model.BilheteDAO;
import br.ulbra.model.Rotativo;
import br.ulbra.model.RotativoDAO;
import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;

/**
 *
 * @author teste
 */
public class BilheteController {
    private BilheteDAO bilheteDAO;
    public BilheteController(){
        bilheteDAO = new BilheteDAO();
    }
    
    public boolean adicionarBilhete(Bilhete r){
       return bilheteDAO.adicionarBilhete(r);
          
    }
    
  
    
    
}
