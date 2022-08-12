package br.com.clinicsystem.agendaconsultoria.core.DAO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.conexaoSQL.ConexaoMySQL;
import br.com.clinicsystem.agendaconsultoria.core.entity.MedicoEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicoDAO {
    public String salvarMedico(MedicoEntity medico) throws NegocioException {

        String sql = "INSERT INTO medico(nome) VALUES (?)";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,medico.getNome());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível cadastrar o médico");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "O medico foi cadastrado com sucesso!";
    }


    public ArrayList<MedicoEntity> listarMedico() throws NegocioException {

        String sql = "SELECT id, nome FROM medico";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<MedicoEntity> medicoEntities = new ArrayList<MedicoEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                MedicoEntity medicoEntity = new MedicoEntity();
                medicoEntity.setId(resultSet.getLong("id"));
                medicoEntity.setNome(resultSet.getString("nome"));

                medicoEntities.add(medicoEntity);

            }
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu algum problema na listagem de medicos!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return medicoEntities;
    }


    public void deletarMedico(Long idMedico) throws NegocioException {

        String sql = "DELETE FROM medico WHERE id=?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idMedico);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível deletar o médico!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public MedicoEntity buscarMedico(Long idMedico) throws NegocioException {

        String sql = "SELECT id, nome FROM medico WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        MedicoEntity medicoEntity = new MedicoEntity();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idMedico);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                medicoEntity.setId(resultSet.getLong("id"));
                medicoEntity.setNome(resultSet.getString("nome"));
            }

        } catch (SQLException e) {
            throw new NegocioException("Não foi possível encontrar o medico");
        } finally {
            if (preparedStatement != null && resultSet != null){
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        return medicoEntity;
    }

    public String alterarMedico(MedicoEntity medico) throws NegocioException {

        String sql = "UPDATE medico SET nome = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setLong(2, medico.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("O medico foi atualizado com sucesso!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "Os dados foram atualizados com sucesso!";
    }


}
