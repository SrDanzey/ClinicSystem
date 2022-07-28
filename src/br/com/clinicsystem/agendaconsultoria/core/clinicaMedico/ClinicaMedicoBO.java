package br.com.clinicsystem.agendaconsultoria.core.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class ClinicaMedicoBO {
    public String salvarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException {
        ClinicaMedicoDAO clinicaMedicoDAO = new ClinicaMedicoDAO();
        return clinicaMedicoDAO.salvarClinicaMedico(clinicaMedico);

    }

    public ArrayList<ClinicaMedicoEntity> listarClinicaMedico() throws NegocioException {
        ClinicaMedicoDAO clinicaMedicoDAO = new ClinicaMedicoDAO();
        return clinicaMedicoDAO.listarClinicaMedico();
    }

    public void deletarClinicaMedico(Long idClinicaMedico) throws NegocioException {
        ClinicaMedicoDAO clinicaMedicoDAO = new ClinicaMedicoDAO();
        clinicaMedicoDAO.deletarClinicaMedico(idClinicaMedico);
    }


    public ClinicaMedicoEntity buscarClinicaMedico(Long idMedicoClinica) throws NegocioException {
        ClinicaMedicoDAO clinicaMedicoDAO = new ClinicaMedicoDAO();
        return clinicaMedicoDAO.buscarClinicaMedico(idMedicoClinica);
    }

    public String alterarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException {
        ClinicaMedicoDAO clinicaMedicoDAO = new ClinicaMedicoDAO();
        return clinicaMedicoDAO.alterarClinicaMedico(clinicaMedico);
    }
}
