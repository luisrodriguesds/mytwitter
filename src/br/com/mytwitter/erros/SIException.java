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
public class SIException extends Exception{

    public SIException() {
        super("Seguidor não pode seguir ele mesmo");
    }
    
}
