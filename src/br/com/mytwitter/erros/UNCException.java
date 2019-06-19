/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mytwitter.erros;

/**
 *
 * @author luisi
 */
public class UNCException extends Exception{
    private String nome;
    
    public UNCException() {
        super("Esse perfil não foi encontrado na base de dados");
    }
    
    public String getNome(){
        return nome;
    }
}
