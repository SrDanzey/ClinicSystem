package br.com.clinicsystem.agendaconsultoria.core.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.DAO.ConexaoMySQL;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClinicaMedicoDAO
{
    public String salvarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException {

        String sql = "INSERT INTO clinicaMedico(fk_idMedico, fk_idClinica) VALUES (?,?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, clinicaMedico.getFk_idMedico());
            preparedStatement.setLong(2,clinicaMedico.getFk_idClinica());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu algum problema no salvamento da relação");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "A relação de clinica e medico foi salvo com sucesso!";
    }


    public ArrayList<ClinicaMedicoEntity> listarMedicoClinica() throws NegocioException {

        String sql = "SELECT id, fk_idMedico, fk_idClinica FROM clinicaMedico";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<ClinicaMedicoEntity> clinicaMedicoEntities = new ArrayList<ClinicaMedicoEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ClinicaMedicoEntity clinicaMedicoEntity = new ClinicaMedicoEntity();

                clinicaMedicoEntity.setId(resultSet.getLong(1));
                clinicaMedicoEntity.setFk_idMedico(resultSet.getLong(2));
                clinicaMedicoEntity.setFk_idClinica(resultSet.getLong(3));

                clinicaMedicoEntities.add(clinicaMedicoEntity);
            }

        } catch (SQLException e) {
            throw new NegocioException("Ocorreu algum problema da listagem da relação de clinica e medico");
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

        return clinicaMedicoEntities;
    }


    public void deletarClinicaMedico(Long idMedicoClinica) throws NegocioException {

        String sql = "DELETE FROM clinicaMedico WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idMedicoClinica);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um problemo no excluir");
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


    public ClinicaMedicoEntity buscarClinicaMedico(Long idMedicoClinica){

        String sql = "SELECT id, fk_idMedico, fk_idClinica FROM clinicaMedico WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClinicaMedicoEntity clinicaMedico = new ClinicaMedicoEntity();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
