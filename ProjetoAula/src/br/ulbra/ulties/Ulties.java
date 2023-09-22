/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.ulties;

import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author aluno.saolucas
 */
public class Ulties {
    public static int salvarBoolean(boolean valor){
        if (valor = true)
            return 1;
        else
            return 0;
    }
    
    public static Icon fileParaIcon(File file) {
        ImageIcon icon = new ImageIcon (file.getAbsolutePath());
        return icon;
        
    }
    
    public static ImageIcon redimencionarIcon(Icon originalIcon,
            int largura, int altura) {
        Image imagemOriginal = ((ImageIcon) originalIcon).getImage();
        Image novaImagem = imagemOriginal.getScaledInstance(
                largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(novaImagem);
            }
    
    
}
