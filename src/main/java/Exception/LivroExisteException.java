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
public class LivroExisteException extends Exception{
    public LivroExisteException(String message){
        JOptionPane.showMessageDialog(null, "Erro o Cadastro! Já existe livro com este ID no sistema", "Cadastro de livros", 1);
    }
}
