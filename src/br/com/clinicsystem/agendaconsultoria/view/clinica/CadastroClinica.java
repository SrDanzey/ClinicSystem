package br.com.clinicsystem.agendaconsultoria.view.clinica;

import br.com.clinicsystem.agendaconsultoria.core.clinica.ClinicaEntity;
import br.com.clinicsystem.agendaconsultoria.core.clinica.ClinicaService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;
import br.com.clinicsystem.agendaconsultoria.view.clinicaMedico.ListaClinicaMedico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CadastroClinica extends JFrame {

	private JPanel contentPane;
	private JTextField fieldId;
	private JTextField fieldNome;
	private JTextField fieldEndereco;
	private JTextField fieldTelefone;
	private JLabel title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClinica frame = new CadastroClinica();
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
	public CadastroClinica() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		title = new JLabel("Cadastrar Clinica");
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		
		JLabel lblNewLabel_3 = new JLabel("Endere\u00E7o");
		
		JLabel lblNewLabel_4 = new JLabel("Telefone");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClinicaEntity clinicaEntity = new ClinicaEntity();
				ClinicaService clinicaService = new ClinicaService();

				clinicaEntity.setNome(fieldNome.getText());
				clinicaEntity.setEndereco(fieldEndereco.getText());
				clinicaEntity.setTelefone(fieldTelefone.getText());

				String mensagem = null;

				try {
					if (fieldId.getText().equals("")) {
						mensagem = clinicaService.salvarClinica(clinicaEntity);
					}else {
						clinicaEntity.setId(Long.parseLong(fieldId.getText()));
						mensagem = clinicaService.alterarClinica(clinicaEntity);
					}
				} catch (NegocioException ex) {
					ex.printStackTrace();
				}

				limpaTela();
				JOptionPane.showMessageDialog(null, mensagem);
				
				ListaClinica listaClinica = new ListaClinica();
				listaClinica.setVisible(true);
				dispose();
			}
		});
		
		fieldId = new JTextField();
		fieldId.setEnabled(false);
		fieldId.setEditable(false);
		fieldId.setColumns(10);
		
		fieldNome = new JTextField();
		fieldNome.setColumns(10);
		
		fieldEndereco = new JTextField();
		fieldEndereco.setColumns(10);
		
		fieldTelefone = new JTextField();
		fieldTelefone.setColumns(10);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClinica listaClinica = new ListaClinica();
				listaClinica.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(160)
							.addComponent(title))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCadastrar)
							.addGap(18)
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(169, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(fieldEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4)
								.addComponent(fieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void limpaTela(){
		fieldId.setText("");
		fieldNome.setText("");
		fieldEndereco.setText("");
		fieldTelefone.setText("");
	}

	public void carregarClinicaPorID(Long idClinica){
		try {
			ClinicaEntity clinicaEncontrada = new ClinicaService().buscarClinica(idClinica);

			if (clinicaEncontrada == null){
				JOptionPane.showMessageDialog(null,
						"A clinica nÃ£o foi localizada", "Erro", JOptionPane.ERROR_MESSAGE);
			} else{
				fieldId.setText(""+clinicaEncontrada.getId());
				fieldNome.setText(clinicaEncontrada.getNome());
				fieldEndereco.setText(clinicaEncontrada.getEndereco());
				fieldTelefone.setText(clinicaEncontrada.getTelefone());
			}
			
			
			title.setText("Alteração de Clinica");
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemErro(), "erro", JOptionPane.ERROR_MESSAGE);
		}
	}

}
