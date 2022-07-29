package br.com.clinicsystem.agendaconsultoria.core.agenda;

import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class AgendaService {

    public String salvarAgenda(AgendaEntity agenda) throws NegocioException {
        AgendaBO agendaBO = new AgendaBO();
        return agendaBO.salvarAgenda(agenda);
    }

    public ArrayList<AgendaEntity> listarAgenda() throws NegocioException {
        AgendaBO agendaBO = new AgendaBO();
        return agendaBO.listarAgenda();
    }

    public void deletarAgenda(Long idAgenda) throws NegocioException {
        AgendaBO agendaBO = new AgendaBO();
        agendaBO.deletarAgenda(idAgenda);
    }

    public AgendaEntity buscarAgenda(Long idAgenda) throws NegocioException {
        AgendaBO agendaBO = new AgendaBO();
        return agendaBO.buscarAgenda(idAgenda);
    }

    public String alterarAgenda(AgendaEntity agenda) throws NegocioException {
        AgendaBO agendaBO = new AgendaBO();
        return agendaBO.alterarAgenda(agenda);

    }


}
