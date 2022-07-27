package br.com.clinicsystem.agendaconsultoria.core.paciente;

import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteService {

    public String salvarPaciente(PacienteEntity paciente) throws NegocioException {

        PacienteBO pacienteBO = new PacienteBO();
        return pacienteBO.salvarPaciente(paciente);

    }

    public ArrayList<PacienteEntity> listarPaciente() throws NegocioException {

        PacienteBO pacienteBO = new PacienteBO();
        return pacienteBO.listarPaciente();

    }

    public void deletarPaciente(Long idPaciente) throws NegocioException {
        PacienteBO pacienteBO = new PacienteBO();
        pacienteBO.deletarPaciente(idPaciente);
    }

    public PacienteEntity buscarPorPaciente(Long idPaciente) throws NegocioException {
        PacienteBO pacienteBO = new PacienteBO();
        return pacienteBO.buscarPorPaciente(idPaciente);
    }

    public String alterarPaciente(PacienteEntity paciente) throws NegocioException {
        PacienteBO pacienteBO = new PacienteBO();
        return pacienteBO.alterarPaciente(paciente);
    }


}
