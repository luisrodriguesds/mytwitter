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
public class PessoaJuridica extends Perfil {

    long cnpj;

    public PessoaJuridica(String usuario) {
        super(usuario);
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

}
