package br.com.clinicsystem.agendaconsultoria.core.DAO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.conexaoSQL.ConexaoMySQL;
import br.com.clinicsystem.agendaconsultoria.core.entity.UsuarioEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    public String salvarUsuario(UsuarioEntity user) throws NegocioException {
        String sql = "INSERT INTO usuario(nome,login,senha,email) VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getSenha());
            preparedStatement.setString(4, user.getEmail());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NegocioException("Erro ao cadastrar usuario");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return "Usuário cadastrado com sucesso";
    }

    public ArrayList<UsuarioEntity> listarUsuario() throws NegocioException {

        String sql = "SELECT id, nome, login, senha, email FROM usuario";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<UsuarioEntity> usuarioEntities = new ArrayList<UsuarioEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UsuarioEntity usuarioEntity = new UsuarioEntity();
                usuarioEntity.setId(resultSet.getLong("id"));
                usuarioEntity.setNome(resultSet.getString("nome"));
                usuarioEntity.setLogin(resultSet.getString("login"));
                usuarioEntity.setSenha(resultSet.getString("senha"));
                usuarioEntity.setEmail(resultSet.getString("email"));
                usuarioEntities.add(usuarioEntity);
            }


        } catch (SQLException e) {
            throw new NegocioException("Erro de listar usuario");
        } finally {
            if (preparedStatement != null && resultSet != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return usuarioEntities;
    }

    public void excluirUsuario(Long idUser) throws NegocioException{

        String sql = "DELETE FROM usuario WHERE id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idUser);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível exluir o usuário");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public UsuarioEntity BuscarUsuarioId(Long idUser) throws NegocioException {

        String sql = "SELECT id, nome, login, senha, email FROM usuario WHERE id = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idUser);
            UsuarioEntity usuarioEntity = null;

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuarioEntity = new UsuarioEntity();
                usuarioEntity.setId(resultSet.getLong("id"));
                usuarioEntity.setNome(resultSet.getString("nome"));
                usuarioEntity.setLogin(resultSet.getString("login"));
                usuarioEntity.setSenha(resultSet.getString("senha"));
                usuarioEntity.setEmail(resultSet.getString("email"));
            }

            return usuarioEntity;

        } catch (SQLException e) {
            throw new NegocioException("Houve um erro na busca do usuario");
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String AlterarUsuario(UsuarioEntity user) throws NegocioException {

        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, email = ? WHERE id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getNome());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getSenha());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um erro na atualização do usuario");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "O usuário foi atualizado com sucesso";

    }

    public ArrayList<UsuarioEntity> buscaUsuarioFiltrado(UsuarioEntity user) throws NegocioException {

        String sql = "SELECT id, nome, login, senha, email FROM usuario";

        boolean adicionaWhere = true;

        if (user != null){
            if (user.getId() != null){
                adicionaWhere = false;
                sql += " WHERE ";
                sql += " id = ? ";
            }
            if (user.getNome() !=null && !user.getNome().equals("")){
                if (adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                } else{
                    sql += " AND ";
                }

                sql += " nome LIKE ? ";
            }

            if (user.getLogin() !=null && !user.getLogin().equals("")){
                if (adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                } else{
                    sql += " AND ";
                }

                sql += " login LIKE ? ";
            }

            if (user.getEmail() !=null && !user.getEmail().equals("")){
                if (adicionaWhere){
                    sql += " WHERE ";
                    adicionaWhere = false;
                } else{
                    sql += " AND ";
                }

                sql += " email LIKE ? ";
            }

        }

        System.out.println(sql);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<UsuarioEntity> usuarioEntities = new ArrayList<UsuarioEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);

            int indice = 0;
            if (user != null){
                if (user.getId() != null){
                    indice += 1;
                    preparedStatement.setLong(indice, user.getId());
                }

                if (user.getNome() != null && !user.getNome().equals("")){
                    indice += 1;
                    preparedStatement.setString(indice, user.getNome()+ "%");
                }

                if (user.getLogin() != null && !user.getLogin().equals("")){
                    indice += 1;
                    preparedStatement.setString(indice, user.getLogin()+ "%");
                }

                if (user.getEmail() != null && !user.getEmail().equals("")){
                    indice += 1;
                    preparedStatement.setString(indice, user.getEmail()+ "%");
                }
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                UsuarioEntity usuarioEntity = new UsuarioEntity();
                usuarioEntity.setId(resultSet.getLong("id"));
                usuarioEntity.setNome(resultSet.getString("nome"));
                usuarioEntity.setLogin(resultSet.getString("login"));
                usuarioEntity.setSenha(resultSet.getString("senha"));
                usuarioEntity.setEmail(resultSet.getString("email"));

                usuarioEntities.add(usuarioEntity);
            }
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um erro na busca filtrada!");
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return usuarioEntities;
    }

    public UsuarioEntity autenticar(String login, String senha) throws NegocioException {

        String sql = "SELECT id, nome, login, senha, email FROM usuario WHERE login = ? AND senha = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UsuarioEntity usuarioEntity = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,senha);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuarioEntity = new UsuarioEntity();
                usuarioEntity.setId(resultSet.getLong("id"));
                usuarioEntity.setNome(resultSet.getString("nome"));
                usuarioEntity.setLogin(resultSet.getString("login"));
                usuarioEntity.setSenha(resultSet.getString("senha"));
                usuarioEntity.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um erro na autenticação");
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return usuarioEntity;
    }

    public UsuarioEntity buscarUsuarioPorLogin(String login) throws NegocioException {

        String sql = "SELECT id, nome, login, senha, email FROM usuario WHERE login = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UsuarioEntity usuarioEntity = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,login);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                usuarioEntity = new UsuarioEntity();

                usuarioEntity.setId(resultSet.getLong("id"));
                usuarioEntity.setNome(resultSet.getString("nome"));
                usuarioEntity.setLogin(resultSet.getString("login"));
                usuarioEntity.setSenha(resultSet.getString("senha"));
                usuarioEntity.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            throw new NegocioException("O login já está sendo utilizado");
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return usuarioEntity;
    }

}
