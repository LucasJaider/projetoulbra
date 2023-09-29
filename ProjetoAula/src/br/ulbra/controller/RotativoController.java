/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.controller;

import br.ulbra.model.Rotativo;
import br.ulbra.model.RotativoDAO;
import br.ulbra.model.Usuario;
import br.ulbra.model.UsuarioDAO;

/**
 *
 * @author teste
 */
public class RotativoController {
    private RotativoDAO rotativoDAO;
    public RotativoController(){
        rotativoDAO = new RotativoDAO();
    }
    
    public boolean adicionarRotativo(Rotativo r){
       return rotativoDAO.adicionarRotativo(r);
          
    }
    
    public boolean buscarBilhete (String placa, int numero){
        return rotativoDAO.buscarBilhete(placa, numero);
    }
    
    
}
