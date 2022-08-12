package br.com.clinicsystem.agendaconsultoria.core.service;

import br.com.clinicsystem.agendaconsultoria.core.BO.ClinicaBO;
import br.com.clinicsystem.agendaconsultoria.core.entity.ClinicaEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class ClinicaService {

    public String salvarClinica(ClinicaEntity clinica) throws NegocioException {
        ClinicaBO clinicaBO = new ClinicaBO();
        return clinicaBO.salvarClinica(clinica);
    }

    public ArrayList<ClinicaEntity> listarClinica() throws NegocioException {
        ClinicaBO clinicaBO = new ClinicaBO();
        return clinicaBO.listarClinica();
    }

    public void deletarClinica(Long idClinica) throws NegocioException {
        ClinicaBO clinicaBO = new ClinicaBO();
        clinicaBO.deletarClinica(idClinica);
    }

    public ClinicaEntity buscarClinica(Long idClinica) throws NegocioException {
        ClinicaBO clinicaBO = new ClinicaBO();
        return clinicaBO.buscarClinica(idClinica);
    }

    public String alterarClinica(ClinicaEntity clinica) throws NegocioException {
        ClinicaBO clinicaBO = new ClinicaBO();
        return clinicaBO.alterarClinica(clinica);
    }
}
