/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mytwitter.usuarios;

import br.com.mytwitter.servicos.Tweet;
import java.util.Vector;

/**
 *
 * @author luisi
 */
abstract public class Perfil {

    private String usuario;
    private Vector<Perfil> seguidos;
    private Vector<Perfil> seguidores;
    private Vector<Tweet> timeline;
    boolean status;

    public Perfil(String usuario) {
        this.usuario = usuario;
        this.seguidos = new Vector<Perfil>();
        this.seguidores = new Vector<Perfil>();
        this.timeline = new Vector<Tweet>();
        this.status = true;
    }

    public void addSeguido(Perfil usuario) {
        this.seguidos.add(usuario);
    }

    public void addSeguidor(Perfil usuario) {
        this.seguidores.add(usuario);
    }

    public void addTweet(Tweet t) {
        this.timeline.add(t);
    }

    public Vector<Tweet> getTimeline() {
        return timeline;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Vector<Perfil> getSeguidos() {
        return seguidos;
    }

    public Vector<Perfil> getSeguidores() {
        return seguidores;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
