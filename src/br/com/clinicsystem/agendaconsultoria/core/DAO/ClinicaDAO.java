package br.com.clinicsystem.agendaconsultoria.core.DAO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.conexaoSQL.ConexaoMySQL;
import br.com.clinicsystem.agendaconsultoria.core.entity.ClinicaEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClinicaDAO {

    public String salvarClinica(ClinicaEntity clinica) throws NegocioException {

        String sql = "INSERT INTO clinica(nome,endereco,telefone) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,clinica.getNome());
            preparedStatement.setString(2,clinica.getEndereco());
            preparedStatement.setString(3,clinica.getTelefone());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new NegocioException("Ocorreu algum erro na hora do cadastro de clinica!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "A clinica foi cadastrada com sucesso!";
    }


    public ArrayList<ClinicaEntity> listarClinica() throws NegocioException {

        String sql = "SELECT id, nome, endereco, telefone FROM clinica";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ClinicaEntity> clinicaEntities = new ArrayList<ClinicaEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClinicaEntity clinica = new ClinicaEntity();
                clinica.setId(resultSet.getLong("id"));
                clinica.setNome(resultSet.getString("nome"));
                clinica.setEndereco(resultSet.getString("endereco"));
                clinica.setTelefone(resultSet.getString("telefone"));

                clinicaEntities.add(clinica);

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

        return clinicaEntities;
    }


    public void deletarClinica(Long idClinica) throws NegocioException {

        String sql = "DELETE FROM clinica WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idClinica);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível deltar a clinica!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public ClinicaEntity buscarClinica(Long idClinica) throws NegocioException {

        String sql = "SELECT id, nome, endereco, telefone FROM clinica WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClinicaEntity clinicaEntity = new ClinicaEntity();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1,idClinica);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                clinicaEntity.setId(resultSet.getLong("id"));
                clinicaEntity.setNome(resultSet.getString("nome"));
                clinicaEntity.setEndereco(resultSet.getString("endereco"));
                clinicaEntity.setTelefone(resultSet.getString("telefone"));
            }
        } catch (SQLException e) {
            throw new NegocioException("Não foi possível encontrar a clinica solicitada!");
        } finally {
            if (preparedStatement != null && preparedStatement != null){
                try {
                    preparedStatement.close();
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return clinicaEntity;
    }


    public String alterarClinica(ClinicaEntity clinica) throws NegocioException {

        String sql = "UPDATE clinica SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, clinica.getNome());
            preparedStatement.setString(2, clinica.getEndereco());
            preparedStatement.setString(3, clinica.getTelefone());
            preparedStatement.setLong(4, clinica.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new NegocioException("Não foi possível atualizar os dados da clinica!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "Os dados da clinica foram alterados com sucesso!";
    }
}
