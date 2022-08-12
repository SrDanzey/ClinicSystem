package br.com.clinicsystem.agendaconsultoria.core.DAO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.conexaoSQL.ConexaoMySQL;
import br.com.clinicsystem.agendaconsultoria.core.entity.AgendaEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgendaDAO {
    public String salvarAgenda(AgendaEntity agenda) throws NegocioException {

        String sql = "INSERT INTO agenda(horario, fk_idClinica_medico, fk_idPaciente) VALUES (?,?,?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, agenda.getHorario());
            preparedStatement.setLong(2, agenda.getFk_idClinicaMedico());
            preparedStatement.setLong(3, agenda.getFk_idPaciente());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new NegocioException("Não foi possível agendar a consulta!");
        } finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "O agendamento foi realizado com sucesso";
    }

    public ArrayList<AgendaEntity> listarAgenda() throws NegocioException {

        String sql = "SELECT id, horario, fk_idClinica_medico, fk_idPaciente FROM agenda";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<AgendaEntity> agendaEntities = new ArrayList<AgendaEntity>();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                AgendaEntity agendaEntity = new AgendaEntity();
                agendaEntity.setId(resultSet.getLong("id"));
                agendaEntity.setHorario(resultSet.getString("horario"));
                agendaEntity.setFk_idClinicaMedico(resultSet.getLong("fk_idClinica_Medico"));
                agendaEntity.setFk_idPaciente(resultSet.getLong("fk_idPaciente"));

                agendaEntities.add(agendaEntity);
            }
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um problema na hora da listagem dos horário");
        } finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return agendaEntities;
    }

    public void deletarAgenda(Long idAgenda) throws NegocioException {

        String sql = "DELETE FROM agenda WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idAgenda);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu algum problema na hora do cancelamento da agenda");
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

    public AgendaEntity buscarAgenda(Long idAgenda) throws NegocioException {

        String sql = "SELECT id, horario, fk_idClinica_Medico, fk_idPaciente FROM agenda WHERE id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AgendaEntity agendaEntity = new AgendaEntity();

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, idAgenda);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                agendaEntity.setId(resultSet.getLong("id"));
                agendaEntity.setHorario(resultSet.getString("horario"));
                agendaEntity.setFk_idClinicaMedico(resultSet.getLong("fk_idClinica_Medico"));
                agendaEntity.setFk_idPaciente(resultSet.getLong("fk_idPaciente"));
            }
        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um problema na busca do agedamento!");
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

        return agendaEntity;
    }

    public String alterarAgenda(AgendaEntity agenda) throws NegocioException {

        String sql = "UPDATE agenda SET horario = ?, fk_idClinica_Medico = ?, fk_idPaciente = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = ConexaoMySQL.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, agenda.getHorario());
            preparedStatement.setLong(2, agenda.getFk_idClinicaMedico());
            preparedStatement.setLong(3, agenda.getFk_idPaciente());
            preparedStatement.setLong(4, agenda.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new NegocioException("Ocorreu um erro na edição do agedamento!");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return "O agedamento foi atualizado com sucesso!";
    }
}
