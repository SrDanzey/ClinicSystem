package br.com.clinicsystem.agendaconsultoria.view.paciente;

import br.com.clinicsystem.agendaconsultoria.core.paciente.PacienteEntity;
import br.com.clinicsystem.agendaconsultoria.core.paciente.PacienteService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField fieldId;
	private JTextField fieldNome;
	private JTextField fieldCPF;
	private JTextField fieldSintoma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPaciente frame = new CadastroPaciente();
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
	public CadastroPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Cadastro de Paciente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNewLabel_1 = new JLabel("ID");

		JLabel lblNewLabel_2 = new JLabel("Nome");

		JLabel lblNewLabel_3 = new JLabel("CPF");

		JLabel lblNewLabel_4 = new JLabel("Sintoma");

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PacienteEntity pacienteEntity = new PacienteEntity();
				PacienteService pacienteService = new PacienteService();

				pacienteEntity.setNome(fieldNome.getText());
				pacienteEntity.setCpf(fieldCPF.getText());
				pacienteEntity.setSintoma(fieldSintoma.getText());

				String mensagem = null;
				try {
					if (fieldId.getText().equals("")) {
						mensagem = pacienteService.salvarPaciente(pacienteEntity);
					} else{
						pacienteEntity.setId(Long.parseLong(fieldId.getText()));
						mensagem = pacienteService.alterarPaciente(pacienteEntity);
					}
				} catch (NegocioException ex) {
					ex.printStackTrace();
				}

				limpaCampo();
				JOptionPane.showMessageDialog(null, mensagem);

				ListaPaciente listaPaciente = new ListaPaciente();
				listaPaciente.setVisible(true);
				dispose();
			}
		});

		fieldId = new JTextField();
		fieldId.setEnabled(false);
		fieldId.setEditable(false);
		fieldId.setColumns(10);

		fieldNome = new JTextField();
		fieldNome.setColumns(10);

		fieldCPF = new JTextField();
		fieldCPF.setColumns(10);

		fieldSintoma = new JTextField();
		fieldSintoma.setColumns(10);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPaciente listaPaciente = new ListaPaciente();
				listaPaciente.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldSintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCadastrar)
							.addGap(18)
							.addComponent(btnVoltar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(143)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(127, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(fieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(fieldSintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar)
						.addComponent(btnVoltar))
					.addGap(28))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void limpaCampo(){
		fieldId.setText("");
		fieldNome.setText("");
		fieldCPF.setText("");
		fieldSintoma.setText("");
	}

	public void carregarPacientePorID(Long idPaciente){

		try {
			PacienteEntity pacienteEncontrado = new PacienteService().buscarPorPaciente(idPaciente);

			if (pacienteEncontrado == null){
				JOptionPane.showMessageDialog(null,
						"O paciente não foi localizado", "erro", JOptionPane.ERROR_MESSAGE);
			} else{
				fieldId.setText(""+pacienteEncontrado.getId());
				fieldNome.setText(pacienteEncontrado.getNome());
				fieldCPF.setText(pacienteEncontrado.getCpf());
				fieldSintoma.setText(pacienteEncontrado.getSintoma());
			}

		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemErro(), "erro", JOptionPane.ERROR_MESSAGE);
		}

	}
}
