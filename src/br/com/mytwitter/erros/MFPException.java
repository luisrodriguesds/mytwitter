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
public class MFPException extends Exception{

    public MFPException() {
        super("A mensagem deve conter entre 1 a 140 caracteres");
    }
    
}
