package br.com.clinicsystem.agendaconsultoria.core.BO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.ClinicaDAO;
import br.com.clinicsystem.agendaconsultoria.core.entity.ClinicaEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class ClinicaBO {
    public String salvarClinica(ClinicaEntity clinica) throws NegocioException {
        ClinicaDAO clinicaDAO = new ClinicaDAO();
        return clinicaDAO.salvarClinica(clinica);
    }

    public ArrayList<ClinicaEntity> listarClinica() throws NegocioException {
        ClinicaDAO clinicaDAO = new ClinicaDAO();
        return clinicaDAO.listarClinica();
    }

    public void deletarClinica(Long idClinica) throws NegocioException {
        ClinicaDAO clinicaDAO = new ClinicaDAO();
        clinicaDAO.deletarClinica(idClinica);
    }

    public ClinicaEntity buscarClinica(Long idClinica) throws NegocioException {
        ClinicaDAO clinicaDAO = new ClinicaDAO();
        return clinicaDAO.buscarClinica(idClinica);
    }

    public String alterarClinica(ClinicaEntity clinica) throws NegocioException {
        ClinicaDAO clinicaDAO = new ClinicaDAO();
        return clinicaDAO.alterarClinica(clinica);
    }
}
