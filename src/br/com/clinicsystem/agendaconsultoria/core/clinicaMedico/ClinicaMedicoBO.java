package br.com.clinicsystem.agendaconsultoria.core.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

public class ClinicaMedicoBO {
    public String salvarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException {
        ClinicaMedicoDAO clinicaMedicoDAO = new ClinicaMedicoDAO();
        return clinicaMedicoDAO.salvarClinicaMedico(clinicaMedico);

    }
}
