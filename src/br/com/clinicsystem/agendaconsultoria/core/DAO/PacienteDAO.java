package br.com.clinicsystem.agendaconsultoria.core.DAO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.conexaoSQL.ConexaoMySQL;
import br.com.clinicsystem.agendaconsultoria.core.entity.PacienteEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteDAO {

    public String salvarPaciente(PacienteEntity paciente) throws NegocioException {

        String sql = "INSERT INTO paciente(nome, cpf, sintoma) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,paciente.getNome());
            preparedStatement.setString(2,paciente.getCpf());
            preparedStatement.setString(3,paciente.getSintoma());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Erro do cadastrar paciente");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return "Paciente foi cadastrado com sucesso";
    }


    public ArrayList<PacienteEntity> listarPaciente() throws NegocioException {

        String sql = "SELECT id, nome, cpf, sintoma FROM paciente";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<PacienteEntity> pacienteEntities = new ArrayList<PacienteEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                PacienteEntity pacienteEntity = new PacienteEntity();
                pacienteEntity.setId(resultSet.getLong("id"));
                pacienteEntity.setNome(resultSet.getString("nome"));
                pacienteEntity.setCpf(resultSet.getString("cpf"));
                pacienteEntity.setSintoma(resultSet.getString("sintoma"));

                pacienteEntities.add(pacienteEntity);
            }
        } catch (SQLException e) {
            throw new NegocioException("Erro na busca dos pacientes");
        } finally {
           if (preparedStatement != null && resultSet != null) {
               try {
                   preparedStatement.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
        }

        return pacienteEntities;

    }


    public void deletarPaciente(Long idPaciente ) throws NegocioException{

        String sql = "DELETE FROM paciente WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1,idPaciente);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível deletar o paciente");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public PacienteEntity buscarPorPaciente(Long idPaciente) throws NegocioException {

        String sql = "SELECT id, nome, cpf, sintoma FROM paciente WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PacienteEntity pacienteEntity = new PacienteEntity();

        try {

            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1,idPaciente);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                pacienteEntity.setId(resultSet.getLong("id"));
                pacienteEntity.setNome(resultSet.getString("nome"));
                pacienteEntity.setCpf(resultSet.getString("cpf"));
                pacienteEntity.setSintoma(resultSet.getString("sintoma"));
            }

        } catch (SQLException e) {
            throw new NegocioException("Não foi possível encontrar o paciente");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return  pacienteEntity;

    }

    public String alterarPaciente(PacienteEntity paciente) throws NegocioException {

        String sql = "UPDATE paciente SET nome = ?, cpf = ?, sintoma = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2,paciente.getCpf());
            preparedStatement.setString(3,paciente.getSintoma());
            preparedStatement.setLong(4,paciente.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível fazer a alteração do paciente");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return "O usuário foi atualizado com sucesso!";
    }
}
