/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

import javax.swing.JOptionPane;

/**
 *
 * @author gutyr
 */
public class UsuarioExisteException extends Exception{
    public UsuarioExisteException(){
        JOptionPane.showMessageDialog(null, "Erro no Cadastro! Já existe Usuario com este RG no sistema", "Cadastro de Usuarios", 1);
    }
}
