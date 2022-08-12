package br.com.clinicsystem.agendaconsultoria.view.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.entity.ClinicaMedicoEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.ClinicaMedicoService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroClinicaMedico extends JFrame {

	private JPanel contentPane;
	private JTextField fieldFK_IDclinica;
	private JTextField fieldFK_IDmedico;
	private JTextField fieldID;
	private JLabel title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClinicaMedico frame = new CadastroClinicaMedico();
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
	public CadastroClinicaMedico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		title = new JLabel("Relacao entre Clinica e medico");
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		
		JLabel lblNewLabel_2 = new JLabel("ID medico");
		
		JLabel lblNewLabel_3 = new JLabel("ID Clinica");
		
		fieldFK_IDclinica = new JTextField();
		fieldFK_IDclinica.setColumns(10);
		
		fieldFK_IDmedico = new JTextField();
		fieldFK_IDmedico.setColumns(10);
		
		fieldID = new JTextField();
		fieldID.setEnabled(false);
		fieldID.setEditable(false);
		fieldID.setColumns(10);
		
		JButton btnCadastrar = new JButton("SALVAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClinicaMedicoEntity clinicaMedico = new ClinicaMedicoEntity();
				ClinicaMedicoService clinicaMedicoService = new ClinicaMedicoService();

				clinicaMedico.setFk_idMedico(Long.parseLong(fieldFK_IDmedico.getText()));
				clinicaMedico.setFk_idClinica(Long.parseLong(fieldFK_IDclinica.getText()));

				String mensagem = null;

				try {
					if (fieldID.getText().equals("")) {
						mensagem = clinicaMedicoService.salvarClinicaMedico(clinicaMedico);
					} else{
						clinicaMedico.setId(Long.parseLong(fieldID.getText()));
						mensagem = clinicaMedicoService.alterarClinicaMedico(clinicaMedico);
					}
				} catch (NegocioException ex) {
					ex.printStackTrace();
				}
				limpaCampo();
				JOptionPane.showMessageDialog(null, mensagem);

				ListaClinicaMedico listaClinicaMedico = new ListaClinicaMedico();
				listaClinicaMedico.setVisible(true);
				dispose();
				
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClinicaMedico listaClinicaMedico = new ListaClinicaMedico();
				listaClinicaMedico.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(59, Short.MAX_VALUE)
					.addComponent(title)
					.addGap(102))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(382, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fieldFK_IDmedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(346, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(fieldFK_IDclinica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(349, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCadastrar)
					.addGap(18)
					.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(329, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldFK_IDmedico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(fieldFK_IDclinica, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void limpaCampo(){
		fieldID.setText("");
		fieldFK_IDmedico.setText("");
		fieldFK_IDclinica.setText("");
	}

	public void carregarClinicaMedicoPorID(Long idClinicaMedico){

		try {
			ClinicaMedicoEntity clinicaMedico = new ClinicaMedicoService().buscarClinicaMedico(idClinicaMedico);

			if (clinicaMedico == null){
				JOptionPane.showMessageDialog(null,
						"Essa relação não foi localizado", "erro", JOptionPane.ERROR_MESSAGE);
			} else{
				fieldID.setText(""+clinicaMedico.getId());
				fieldFK_IDclinica.setText(""+clinicaMedico.getFk_idClinica());
				fieldFK_IDmedico.setText(""+clinicaMedico.getFk_idMedico());
			}
			title.setText("Alteran��o rela��o de Clinica e Medico");
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemErro(), "erro", JOptionPane.ERROR_MESSAGE);
		}

	}
}
