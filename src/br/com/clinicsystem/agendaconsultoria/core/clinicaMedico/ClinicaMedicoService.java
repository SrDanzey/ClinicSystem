package br.com.clinicsystem.agendaconsultoria.core.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.clinica.ClinicaBO;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

public class ClinicaMedicoService {

    public String salvarClinicaMedico(ClinicaMedicoEntity clinicaMedico) throws NegocioException {
        ClinicaMedicoBO clinicaMedicoBO = new ClinicaMedicoBO();
        return clinicaMedicoBO.salvarClinicaMedico(clinicaMedico);

    }

}
