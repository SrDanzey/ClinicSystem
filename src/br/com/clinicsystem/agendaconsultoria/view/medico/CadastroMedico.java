package br.com.clinicsystem.agendaconsultoria.view.medico;

import br.com.clinicsystem.agendaconsultoria.core.entity.MedicoEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.MedicoService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroMedico extends JFrame {

	private JPanel contentPane;
	private JTextField fieldNome;
	private JTextField fieldID;
	private JLabel title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMedico frame = new CadastroMedico();
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
	public CadastroMedico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		title = new JLabel("Medico");
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		
		fieldNome = new JTextField();
		fieldNome.setColumns(10);
		
		fieldID = new JTextField();
		fieldID.setEnabled(false);
		fieldID.setEditable(false);
		fieldID.setColumns(10);
		
		JButton btnCadastrar = new JButton("SALVAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MedicoEntity medicoEntity = new MedicoEntity();
				MedicoService medicoService = new MedicoService();

				medicoEntity.setNome(fieldNome.getText());
				String mensagem = null;

				try {
					if (fieldID.getText().equals("")) {
						mensagem = medicoService.salvarMedico(medicoEntity);
					} else{
						medicoEntity.setId(Long.parseLong(fieldID.getText()));
						mensagem = medicoService.alterarMedico(medicoEntity);
					}
				} catch (NegocioException ex) {
					ex.printStackTrace();
				}
				limpaCampo();
				JOptionPane.showMessageDialog(null, mensagem);
				
				ListaMedico listaMedico = new ListaMedico();
				listaMedico.setVisible(true);
				dispose();
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaMedico listaMedico = new ListaMedico();
				listaMedico.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(133)
							.addComponent(title))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCadastrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void limpaCampo(){
		fieldID.setText("");
		fieldNome.setText("");
	}

	public void carregarMedicoPorID(Long idMedico){
		try {
			MedicoEntity medicoEncontrado = new MedicoService().buscarMedico(idMedico);

			if (medicoEncontrado == null){
				JOptionPane.showMessageDialog(null,
						"O medico não foi localizado", "erro", JOptionPane.ERROR_MESSAGE);
			}else {
				fieldID.setText(""+medicoEncontrado.getId());
				fieldNome.setText(medicoEncontrado.getNome());
			}
			
			title.setText("Altera��o de Medico");
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
}
