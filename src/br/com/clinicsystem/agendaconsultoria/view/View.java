package br.com.clinicsystem.agendaconsultoria.view;

import br.com.clinicsystem.agendaconsultoria.core.paciente.PacienteEntity;
import br.com.clinicsystem.agendaconsultoria.core.paciente.PacienteService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.sql.SQLException;
import java.util.ArrayList;

public class View {

    public static void main(String[] args) throws SQLException, NegocioException {
        PacienteEntity pacienteEnty = new PacienteEntity();
        PacienteService pacienteService = new PacienteService();
        ArrayList<PacienteEntity> pacienteEntities = new ArrayList<PacienteEntity>();


//        pacienteEnty.setNome("João Gonsalves Silver");
//        pacienteEnty.setCpf("321.654.127-21");
//        pacienteEnty.setSintoma("Dor no estomago;Dor no dedo");
//
//  Salvar pacientes
//        try {
//            pacienteService.salvarPaciente(pacienteEnty);
//        } catch (NegocioException e) {
//            e.printStackTrace();
//        }

//  Listar pacientes

//        try {
//            pacienteEntities = pacienteService.listarPaciente();
//            for (PacienteEntity pacienteEntity: pacienteEntities) {
//                System.out.println(pacienteEntity.getNome());
//            }
//        } catch (NegocioException e) {
//            e.printStackTrace();
//        }

//  Deletar Paciente

//        try {
//            pacienteService.deletarPaciente(1L);
//        } catch (NegocioException e) {
//            e.printStackTrace();
//        }

//  Buscar por ID

        PacienteEntity teste = null;
        teste = pacienteService.buscarPorPaciente(3L);
        System.out.println(teste.getNome());

//  Alterando dados

        pacienteEnty.setId(2L);
        pacienteEnty.setNome("Douglas Guimarães dos Santos");
        pacienteEnty.setCpf("421.763.364-43");
        pacienteEnty.setSintoma("Não sei, não sei");

        System.out.println(pacienteService.alterarPaciente(pacienteEnty));
    }

}
