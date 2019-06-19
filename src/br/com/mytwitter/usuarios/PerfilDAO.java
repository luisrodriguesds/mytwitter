/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mytwitter.usuarios;
import br.com.mytwitter.erros.UJCException;
import br.com.mytwitter.erros.UNCException;
import br.com.mytwitter.interfaces.IRepositorioUsuario;
import java.util.Vector;

/**
 *
 * @author luisi
 */
public class PerfilDAO implements IRepositorioUsuario {

    Vector<Perfil> p;

    public PerfilDAO() {
        p = new Vector<Perfil>();
    }

    @Override
    public void cadastrar(Perfil usuario) {
        try {
            buscaNome(usuario.getUsuario());
            p.add(usuario);
            System.out.println("Foi add o usuário: "+usuario.getUsuario());
        } catch (UJCException e) {
            System.err.println("Esse usuario ja existe");
        }
    }

    @Override
    public Perfil buscar(String usuario) {
        if (!p.isEmpty()) {
            for (Perfil p1 : p) {
                if (p1.getUsuario().equals(usuario)) {
                    return p1;
                }
            }
        }
        return null;
    }

    @Override
    public void atualizar(Perfil usuario) {
        try {
            buscaUsuario(usuario.getUsuario());
            for (Perfil p1 : p) {
                if (p1.getUsuario().equals(usuario.getUsuario())) {
                    p1 = usuario;
                }
            }
        } catch (UNCException e) {
            System.err.println("Usuário nao existe");
        }
    }

    private void buscaNome(String nome) throws UJCException {
        if (!p.isEmpty()) {
            for (Perfil p1 : p) {
                if (nome.equals(p1.getUsuario())) {
                    throw new UJCException();
                }
            }
        }
    }

    private void buscaUsuario(String nome) throws UNCException {
        if (!p.isEmpty()) {
            int i =0;
            for (Perfil p1 : p) {
                if (p1.getUsuario().equals(nome)) {
                    i++;
                }
            }
            if (i==0) {
                throw new UNCException();
            }
        } else {
            throw new UNCException();
        }
    }
//    public void seguir(Perfil sr, Perfil so) {
//        String sql1 = "INSERT INTO seguidos(usuario, usuario_seguido) VALUES(?, ?)";
//        String sql2 = "INSERT INTO seguidores(usuario, usuario_seguidor) VALUES(?, ?)";
//
//        try {
//            pst = conexao.prepareStatement(sql1);
//            pst.setString(1, sr.getUsuario());
//            pst.setString(2, so.getUsuario());
//            pst.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        try {
//            pst = conexao.prepareCall(sql2);
//            pst.setString(1, so.getUsuario());
//            pst.setString(2, sr.getUsuario());
//            pst.executeUpdate();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public int numeroSeguidores(String usuario) {
//        String sql = "SELECT u.usuario, s.usuario, u.status FROM seguidores as s, usuarios as u WHERE u.usuario = ? AND s.usuario = ? AND status = true";
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, usuario);
//            pst.setString(2, usuario);
//
//            rs = pst.executeQuery();
//            int i = 0;
//            while (rs.next()) {
//                i++;
//            }
//            return i;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return 0;
//    }
//
//    public Vector<Perfil> seguidores(String usuario) {
//        String sql = "SELECT s.usuario, s.usuario_seguidor, u.usuario, u.nome, u.senha, u.status FROM usuarios as u, seguidores as s WHERE s.usuario = ? AND s.usuario_seguidor = u.usuario AND u.status = true";
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, usuario);
//            rs = pst.executeQuery();
//            
//            Vector<Perfil> pv = new Vector<Perfil>();
//            
//            while (rs.next()) {                
//                Perfil p = new PessoaFisica(rs.getString("u.usuario"));
//                p.setNome(rs.getString("u.nome"));
//                p.setStatus((rs.getInt("u.status") == 1));
//                p.setSenha(rs.getString("u.senha"));
//                pv.add(p);
//            }
//            return (pv.isEmpty()) ? null : pv;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//    
//    public Vector<Perfil> seguidos(String usuario) {
//        String sql = "SELECT s.usuario, s.usuario_seguido, u.usuario, u.nome, u.senha, u.status FROM usuarios as u, seguidos as s WHERE s.usuario = ? AND s.usuario_seguido = u.usuario AND u.status = true";
//        try {
//            pst = conexao.prepareStatement(sql);
//            pst.setString(1, usuario);
//            rs = pst.executeQuery();
//            
//            Vector<Perfil> pv = new Vector<Perfil>();
//            
//            while (rs.next()) {                
//                Perfil p = new PessoaFisica(rs.getString("u.usuario"));
//                p.setNome(rs.getString("u.nome"));
//                p.setStatus((rs.getInt("u.status") == 1));
//                p.setSenha(rs.getString("u.senha"));
//                pv.add(p);
//            }
//            return (pv.isEmpty()) ? null : pv;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }

}
