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
public class PJSException extends Exception{

    public PJSException() {
        super("Este perfil já segue o perfil do seguidor");
    }
   
}
