package br.com.clinicsystem.agendaconsultoria.core.BO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.PacienteDAO;
import br.com.clinicsystem.agendaconsultoria.core.entity.PacienteEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class PacienteBO {

    public String salvarPaciente(PacienteEntity paciente) throws NegocioException {

        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.salvarPaciente(paciente);

    }


    public ArrayList<PacienteEntity> listarPaciente() throws NegocioException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.listarPaciente();

    }

    public void deletarPaciente(Long idPaciente) throws NegocioException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.deletarPaciente(idPaciente);
    }

    public PacienteEntity buscarPorPaciente(Long idPaciente) throws NegocioException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.buscarPorPaciente(idPaciente);
    }

    public String alterarPaciente(PacienteEntity paciente) throws NegocioException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.alterarPaciente(paciente);
    }
}
