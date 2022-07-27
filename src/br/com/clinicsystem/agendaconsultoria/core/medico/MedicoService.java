package br.com.clinicsystem.agendaconsultoria.core.medico;

import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class MedicoService {

    public String salvarMedico(MedicoEntity medico) throws NegocioException {
        MedicoBO medicoBO = new MedicoBO();
        return medicoBO.salvarMedico(medico);
    }

    public ArrayList<MedicoEntity> listarMedico() throws NegocioException {
        MedicoBO medicoBO = new MedicoBO();
        return medicoBO.listarMedico();
    }

    public void deletarMedico(Long idMedico) throws NegocioException {
        MedicoBO medicoBO = new MedicoBO();
        medicoBO.deletarMedico(idMedico);
    }

    public MedicoEntity buscarMedico(Long idMedico) throws NegocioException {
        MedicoBO medicoBO = new MedicoBO();
        return medicoBO.buscarMedico(idMedico);
    }


    public String alterarMedico(MedicoEntity medico) throws NegocioException {
        MedicoBO medicoBO = new MedicoBO();
        return medicoBO.alterarMedico(medico);
    }

}
