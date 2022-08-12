package br.com.clinicsystem.agendaconsultoria.core.service;

import br.com.clinicsystem.agendaconsultoria.core.BO.UsuarioBO;
import br.com.clinicsystem.agendaconsultoria.core.entity.UsuarioEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class UsuarioService {

    public String salvarUsuario(UsuarioEntity user) throws NegocioException {
        UsuarioBO userBO = new UsuarioBO();
        return userBO.salvarUsuario(user);
    }

    public ArrayList<UsuarioEntity> listarUsuario() throws NegocioException {
        return new UsuarioBO().listarUsuario();
    }

    public void excluirUsuario(Long idUser) throws NegocioException {
        new UsuarioBO().excluirUsuario(idUser);
    }

    public UsuarioEntity buscarUsuarioId(Long idUser) throws NegocioException {
        return new UsuarioBO().BuscarUsuarioId(idUser);
    }

    public String AlterarUsuario(UsuarioEntity user) throws NegocioException{
        return new UsuarioBO().AlterarUsuario(user);
    }

    public ArrayList<UsuarioEntity> buscaUsuarioFiltrado(UsuarioEntity user) throws NegocioException {
        UsuarioBO usuarioBO = new UsuarioBO();
        return  usuarioBO.buscaUsuarioFiltrado(user);
    }


    public UsuarioEntity autenticar(String login, String senha) throws NegocioException {
        UsuarioBO usuarioBO = new UsuarioBO();
        return usuarioBO.autenticar(login, senha);

    }



    public UsuarioEntity buscarUsuarioPorLogin(String login) throws NegocioException {
        UsuarioBO usuarioBO = new UsuarioBO();
        return usuarioBO.buscarUsuarioPorLogin(login);
    }

}
