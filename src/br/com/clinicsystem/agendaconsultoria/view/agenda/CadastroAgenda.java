package br.com.clinicsystem.agendaconsultoria.view.agenda;

import br.com.clinicsystem.agendaconsultoria.core.entity.AgendaEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.AgendaService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroAgenda extends JFrame {

	private JPanel contentPane;
	private JTextField fieldID;
	private JTextField fieldHorario;
	private JTextField fieldID_Paciente;
	private JTextField fieldID_ClinicaMedico;
	private JLabel title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAgenda frame = new CadastroAgenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroAgenda() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		title = new JLabel("Agendar Consultoria");
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel ID = new JLabel("ID");
		
		JLabel Horario = new JLabel("Horario");
		
		JLabel lblNewLabel_3 = new JLabel("Paciente");
		
		fieldID = new JTextField();
		fieldID.setEnabled(false);
		fieldID.setEditable(false);
		fieldID.setColumns(10);
		
		fieldHorario = new JTextField();
		fieldHorario.setColumns(10);
		
		fieldID_Paciente = new JTextField();
		fieldID_Paciente.setColumns(10);
		
		fieldID_ClinicaMedico = new JTextField();
		fieldID_ClinicaMedico.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Clinica e medico");
		
		JButton btnCadastrar = new JButton("SALVAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AgendaEntity agenda = new AgendaEntity();
				AgendaService agendaService = new AgendaService();

				agenda.setHorario(fieldHorario.getText());
				agenda.setFk_idPaciente(Long.parseLong(fieldID_Paciente.getText()));
				agenda.setFk_idClinicaMedico(Long.parseLong(fieldID_ClinicaMedico.getText()));

				String mensagem = null;

				try {
					if (fieldID.getText().equals("")) {
						mensagem = agendaService.salvarAgenda(agenda);
					} else{
						agenda.setId(Long.parseLong(fieldID.getText()));
						mensagem = agendaService.alterarAgenda(agenda);
					}
				} catch (NegocioException ex) {
					ex.printStackTrace();
				}

				limpaCampo();
				JOptionPane.showMessageDialog(null, mensagem);
				
				ListaAgenda listaAgenda = new ListaAgenda();
				listaAgenda.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaAgenda listaAgenda = new ListaAgenda();
				listaAgenda.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(ID)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Horario)
							.addGap(10)
							.addComponent(fieldHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldID_ClinicaMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldID_Paciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCadastrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(276, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(152, Short.MAX_VALUE)
					.addComponent(title)
					.addGap(140))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ID)
						.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fieldHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Horario))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(fieldID_Paciente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(fieldID_ClinicaMedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void limpaCampo(){
		fieldID.setText("");
		fieldHorario.setText("");
		fieldID_Paciente.setText("");
		fieldID_ClinicaMedico.setText("");
	}

	public void carregarAgendaPorID(Long idAgenda){

		try {
			AgendaEntity agendaEncontrada = new AgendaService().buscarAgenda(idAgenda);

			if (agendaEncontrada == null){
				JOptionPane.showMessageDialog(null,
						"O paciente não foi localizado", "erro", JOptionPane.ERROR_MESSAGE);
			} else {
				fieldID.setText(""+agendaEncontrada.getId());
				fieldHorario.setText(agendaEncontrada.getHorario());
				fieldID_Paciente.setText(""+agendaEncontrada.getFk_idPaciente());
				fieldID_ClinicaMedico.setText(""+agendaEncontrada.getFk_idClinicaMedico());
			}
			
			title.setText("Altera��o de agenda");

		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemErro(), "erro", JOptionPane.ERROR_MESSAGE);
		}


	}

}
