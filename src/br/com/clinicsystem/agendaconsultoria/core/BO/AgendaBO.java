package br.com.clinicsystem.agendaconsultoria.core.BO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.AgendaDAO;
import br.com.clinicsystem.agendaconsultoria.core.entity.AgendaEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class AgendaBO {
    public String salvarAgenda(AgendaEntity agenda) throws NegocioException {
        AgendaDAO agendaDAO = new AgendaDAO();
        return agendaDAO.salvarAgenda(agenda);
    }

    public ArrayList<AgendaEntity> listarAgenda() throws NegocioException {
        AgendaDAO agendaDAO = new AgendaDAO();
        return agendaDAO.listarAgenda();

    }

    public void deletarAgenda(Long idAgenda) throws NegocioException {
        AgendaDAO agendaDAO = new AgendaDAO();
        agendaDAO.deletarAgenda(idAgenda);
    }

    public AgendaEntity buscarAgenda(Long idAgenda) throws NegocioException {
        AgendaDAO agendaDAO = new AgendaDAO();
        return agendaDAO.buscarAgenda(idAgenda);
    }

    public String alterarAgenda(AgendaEntity agenda) throws NegocioException {
        AgendaDAO agendaDAO = new AgendaDAO();
        return agendaDAO.alterarAgenda(agenda);

    }
}
