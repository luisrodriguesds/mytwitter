/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mytwitter.usuarios;

/**
 *
 * @author luisi
 */
public class PessoaFisica extends Perfil {

    long cpf;

    public PessoaFisica(String usuario) {
        super(usuario);
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

}
