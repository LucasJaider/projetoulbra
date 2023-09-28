package br.ulbra.model;

import javax.swing.Icon;

/**
 *
 * @author aluno.saolucas
 */
public class Usuario extends Pessoa{
    private String dataNasc;
    private String senha;
   

    

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

   

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
   

    public String toString(){
        return "Usuario{" + "pkUsuario" + getPk() + ", nomeUsu= " + getNome()
                + ", emailUsu=" + getEmail() + ", dataNascUsu=" + getDataNasc() 
                + ", ativoUsu=" + getAtivo() + ", senhaUsu=" + getSenha() + '}';
                
                }
    
}
