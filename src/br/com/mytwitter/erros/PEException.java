/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
uma	 exceção	 de	 perfil	
existente
 */
package br.com.mytwitter.erros;

/**
 *
 * @author luisi
 */
public class PEException extends Exception{

    public PEException() {
        super("Esse perfil já existe");
    }
    
}
