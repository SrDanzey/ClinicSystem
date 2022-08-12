package br.com.clinicsystem.agendaconsultoria.core.BO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.UsuarioDAO;
import br.com.clinicsystem.agendaconsultoria.core.entity.UsuarioEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class UsuarioBO {

    public String salvarUsuario(UsuarioEntity user) throws NegocioException {

        validarUsuario(user);

        UsuarioDAO userDAO = new UsuarioDAO();
        return userDAO.salvarUsuario(user);
    }

    public ArrayList<UsuarioEntity> listarUsuario() throws NegocioException {
        return new UsuarioDAO().listarUsuario();
    }


    public void excluirUsuario(Long idUser) throws NegocioException {
        new UsuarioDAO().excluirUsuario(idUser);
    }

    public UsuarioEntity BuscarUsuarioId(Long idUser) throws NegocioException {
        return new UsuarioDAO().BuscarUsuarioId(idUser);
    }

    public String AlterarUsuario(UsuarioEntity user) throws NegocioException {
        validarUsuario(user);

        return new UsuarioDAO().AlterarUsuario(user);
    }

    public ArrayList<UsuarioEntity> buscaUsuarioFiltrado(UsuarioEntity user) throws NegocioException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscaUsuarioFiltrado(user);
    }

    public UsuarioEntity autenticar(String login, String senha) throws NegocioException {

        if (login.equals("admin") && senha.equals("admin")){
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setNome("ADM do Sistema");
            usuarioEntity.setLogin("admin");
            usuarioEntity.setSenha("admin");
            return usuarioEntity;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.autenticar(login, senha);
    }

    public UsuarioEntity buscarUsuarioPorLogin(String login) throws NegocioException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarUsuarioPorLogin(login);
    }


    private String validarUsuario(UsuarioEntity user) throws NegocioException {
        if (!user.getEmail().contains("@")){
            throw new NegocioException("O formato do e-mail está invalado, por favor verificar!");
        }

        if (user.getNome() == null || user.getNome().equals("")){
            return "O nome do usuário está vazio!";
        }

        return null;
    }



}
