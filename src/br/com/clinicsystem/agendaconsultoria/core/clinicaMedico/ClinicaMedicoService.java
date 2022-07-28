package br.com.clinicsystem.agendaconsultoria.core.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.clinica.ClinicaBO;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class ClinicaMedicoService {

    public String salvarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException {
        ClinicaMedicoBO clinicaMedicoBO = new ClinicaMedicoBO();
        return clinicaMedicoBO.salvarClinicaMedico(clinicaMedico);

    }

    public ArrayList<ClinicaMedicoEntity> listarClinicaMedico() throws NegocioException {
       ClinicaMedicoBO clinicaMedicoBO = new ClinicaMedicoBO();
       return clinicaMedicoBO.listarClinicaMedico();
    }

    public void deletarClinicaMedico(Long idClinicaMedico) throws NegocioException {
        ClinicaMedicoBO clinicaMedicoBO = new ClinicaMedicoBO();
        clinicaMedicoBO.deletarClinicaMedico(idClinicaMedico);
    }

    public ClinicaMedicoEntity buscarClinicaMedico(Long idMedicoClinica) throws NegocioException {
        ClinicaMedicoBO clinicaMedicoBO = new ClinicaMedicoBO();
        return clinicaMedicoBO.buscarClinicaMedico(idMedicoClinica);

    }

    public String alterarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException{
        ClinicaMedicoBO clinicaMedicoBO = new ClinicaMedicoBO();
        return clinicaMedicoBO.alterarClinicaMedico(clinicaMedico);
    }
}
