package br.com.clinicsystem.agendaconsultoria.core.BO;

import br.com.clinicsystem.agendaconsultoria.core.DAO.MedicoDAO;
import br.com.clinicsystem.agendaconsultoria.core.entity.MedicoEntity;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.util.ArrayList;

public class MedicoBO {
    public String salvarMedico(MedicoEntity medico) throws NegocioException {
        MedicoDAO medicoDAO = new MedicoDAO();
        return medicoDAO.salvarMedico(medico);
    }

    public ArrayList<MedicoEntity> listarMedico() throws NegocioException {
        MedicoDAO medicoDAO = new MedicoDAO();
        return medicoDAO.listarMedico();
    }


    public void deletarMedico(Long idMedico) throws NegocioException {
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.deletarMedico(idMedico);
    }


    public MedicoEntity buscarMedico(Long idMedico) throws NegocioException {
        MedicoDAO medicoDAO = new MedicoDAO();
        return medicoDAO.buscarMedico(idMedico);
    }

    public String alterarMedico(MedicoEntity medico) throws NegocioException {
        MedicoDAO medicoDAO = new MedicoDAO();
        return medicoDAO.alterarMedico(medico);
    }
}
