/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mytwitter.interfaces;

import br.com.mytwitter.erros.UJCException;
import br.com.mytwitter.usuarios.Perfil;

/**
 *
 * @author luisi
 */
public interface IRepositorioUsuario {
    public void cadastrar(Perfil usuario);
    public Perfil buscar(String usuario);
    public void atualizar(Perfil usuario);
}
