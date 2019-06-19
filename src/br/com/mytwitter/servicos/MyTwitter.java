/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mytwitter.servicos;

import br.com.mytwitter.erros.MFPException;
import br.com.mytwitter.erros.PDException;
import br.com.mytwitter.erros.PEException;
import br.com.mytwitter.erros.PIException;
import br.com.mytwitter.erros.SIException;
import br.com.mytwitter.interfaces.IRepositorioUsuario;
import br.com.mytwitter.interfaces.ITwitter;
import br.com.mytwitter.usuarios.Perfil;
import br.com.mytwitter.usuarios.PerfilDAO;
import java.util.Vector;

/**
 *
 * @author luisi
 */
public class MyTwitter implements ITwitter {

    IRepositorioUsuario repositorio;

    public MyTwitter(IRepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void criarPerfil(Perfil usuario) {
        try {
            checkPerfilEx(usuario.getUsuario());
            repositorio.cadastrar(usuario);
        } catch (PEException e) {
            System.out.println("Esse perfil já existe");
        }
    }

    @Override
    public void cancelarPerfil(String usuario) {
        Perfil user = repositorio.buscar(usuario);
        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                user.setStatus(false);
                repositorio.atualizar(user);
            } catch (PDException e) {
                System.out.println("Perfil com status inativo");
            }
        } catch (PIException e) {
            System.out.println("Peril inexistente");
        }
    }

    @Override
    public void tweetar(String usuario, String mensagem) {
        Tweet t = new Tweet();
        Perfil user = repositorio.buscar(usuario);

        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                try {
                    checkMensagem(mensagem);
                    //Codigo
                    //Colocar o tweet na timeline do usuario
                    t.setUsuario(usuario);
                    t.setTweet(mensagem);
                    user.addTweet(t);

                    //Colocar tweet na timeline dos seus seguidores
                    Vector<Perfil> s = user.getSeguidores();
                    if (!s.isEmpty()) {
                        for (Perfil s1 : s) {
                            if (s1.isStatus()) {
                                s1.addTweet(t);
                            }
                        }
                    }
                    repositorio.atualizar(user); //realmente funciona!!
                } catch (MFPException e) {
                    System.out.println("A mensagem deve conter entre 1 a 140 caracteres");
                }
            } catch (PDException e) {
                System.out.println("Perfil com status inativo");
            }
        } catch (PIException e) {
            System.out.println("Peril inexistente");
        }
    }

    @Override
    public Vector<Tweet> tweets(String usuario) {
        Perfil user = repositorio.buscar(usuario);
        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                //Codigo
                Vector<Tweet> t = new Vector<Tweet>();
                Vector<Tweet> tu = new Vector<Tweet>();
                t = user.getTimeline();
                if (!t.isEmpty()) {
                    for (Tweet t1 : t) {
                        if (t1.getUsuario().equals(usuario)) {
                            tu.add(t1);
                        }
                    }
                }
                return ((!tu.isEmpty()) ? tu : null);
            } catch (PDException e) {
                System.out.println("Perfil com status inativo");
            }
        } catch (PIException e) {
            System.out.println("Peril inexistente");
        }
        return null;
    }

    @Override
    public Vector<Tweet> timeline(String usuario) {
        Perfil user = repositorio.buscar(usuario);
        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                //Codigo
                Vector<Tweet> t = new Vector<Tweet>();
                t = user.getTimeline();
                return ((!t.isEmpty()) ? t : null);
            } catch (PDException e) {
                System.out.println("Perfil com status inativo");
            }
        } catch (PIException e) {
            System.out.println("Peril inexistente");
        }
        return null;
    }

    @Override
    public void seguir(String seguidor, String seguido) {
        Perfil sr, so;
        sr = repositorio.buscar(seguidor);
        so = repositorio.buscar(seguido);

        try {
            checkPerfilIn(seguidor);
            checkPerfilIn(seguido);
            try {
                checkPerfilStatus(seguidor);
                checkPerfilStatus(seguido);
                //Checar nomes iguais
                try {
                    checkNomes(seguidor, seguido);
                    //Codigo
                    //Verificar se já segue
                    so.addSeguidor(sr);
                    sr.addSeguido(so);
                    repositorio.atualizar(so);
                    repositorio.atualizar(sr);
                } catch (SIException e) {
                    System.out.println("Seguidor não pode seguir ele mesmo");
                }
            } catch (PDException e) {
                System.out.println("Perfil de seguidor ou seguido está inativo");
            }
        } catch (PIException e) {
            System.out.println("Perfil de seguidor ou seguido nao existe");
        }
    }

    @Override
    public int numeroSeguidores(String usuario) {
        Perfil user = repositorio.buscar(usuario);

        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                //Código
                Vector<Perfil> s = user.getSeguidores();
                int numSeg = 0;
                for (Perfil s1 : s) {
                    if (s1.isStatus()) {
                        numSeg++;
                    }
                }
                return numSeg;
            } catch (PDException e) {
                System.out.println("Perfil está inativo");
            }
        } catch (PIException e) {
            System.out.println("Perfil nao existe");
        }
        return 0;
    }
    
    public int numeroSeguidos(String usuario) {
        Perfil user = repositorio.buscar(usuario);

        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                //Código
                Vector<Perfil> s = user.getSeguidos();
                int numSeg = 0;
                for (Perfil s1 : s) {
                    if (s1.isStatus()) {
                        numSeg++;
                    }
                }
                return numSeg;
            } catch (PDException e) {
                System.out.println("Perfil está inativo");
            }
        } catch (PIException e) {
            System.out.println("Perfil nao existe");
        }
        return 0;
    }

    @Override
    public Vector<Perfil> seguidores(String usuario) {
        Perfil user = repositorio.buscar(usuario);

        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                //Código
                Vector<Perfil> p = user.getSeguidores();
                Vector<Perfil> srs = new Vector<Perfil>();

                if (!p.isEmpty()) {
                    for (Perfil p1 : p) {
                        if (p1.isStatus()) {
                            srs.add(p1);
                        }
                    }
                    return ((!srs.isEmpty()) ? srs : null);
                } else {
                    return null;
                }
            } catch (PDException e) {
                System.out.println("Perfil está inativo");
            }
        } catch (PIException e) {
            System.out.println("Perfil nao existe");
        }
        return null;
    }

    @Override
    public Vector<Perfil> seguidos(String usuario) {
        Perfil user = repositorio.buscar(usuario);

        try {
            checkPerfilIn(usuario);
            try {
                checkPerfilStatus(usuario);
                //Código
                Vector<Perfil> p = user.getSeguidos();
                Vector<Perfil> srs = new Vector<Perfil>();

                if (!p.isEmpty()) {
                    for (Perfil p1 : p) {
                        if (p1.isStatus()) {
                            srs.add(p1);
                        }
                    }
                    return ((!srs.isEmpty()) ? srs : null);
                } else {
                    return null;
                }
            } catch (PDException e) {
                System.out.println("Perfil está inativo");
            }
        } catch (PIException e) {
            System.out.println("Perfil está inativo");
        }
        return null;
    }

    private void checkPerfilEx(String usuario) throws PEException {
        if (repositorio.buscar(usuario) != null) {
            throw new PEException();
        }
    }

    private void checkPerfilIn(String usuario) throws PIException {
        if (repositorio.buscar(usuario) == null) {
            throw new PIException();
        }
    }

    private void checkPerfilStatus(String usuario) throws PDException {
        Perfil p = repositorio.buscar(usuario);
        if (!p.isStatus()) {
            throw new PDException();
        }
    }

    private void checkMensagem(String mensagem) throws MFPException {
        if (mensagem.isEmpty() || mensagem.length() > 140) {
            throw new MFPException();
        }
    }

    private void checkNomes(String n1, String n2) throws SIException {
        if (n1.equals(n2)) {
            throw new SIException();
        }
    }
}
