package br.com.clinicsystem.agendaconsultoria.view.usuario;

import br.com.clinicsystem.agendaconsultoria.core.BO.UsuarioBO;
import br.com.clinicsystem.agendaconsultoria.core.entity.UsuarioEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.UsuarioService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField fieldID;
	private JTextField fieldNome;
	private JTextField fieldLogin;
	private JTextField fieldEmail;
	private JPasswordField fieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
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
	public CadastroUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		
		JLabel lblNewLabel_4 = new JLabel("Senha");

		JLabel lblNewLabel_5 = new JLabel("E-mail");

		fieldID = new JTextField();
		fieldID.setEnabled(false);
		fieldID.setEditable(false);
		fieldID.setColumns(10);
		
		fieldNome = new JTextField();
		fieldNome.setColumns(10);
		
		fieldLogin = new JTextField();
		fieldLogin.setColumns(10);

		fieldEmail = new JTextField();
		fieldEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("SALVAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				UsuarioEntity usuarioEntity = new UsuarioEntity();
				usuarioEntity.setNome(fieldNome.getText());
				usuarioEntity.setLogin(fieldLogin.getText());
				usuarioEntity.setSenha(new String(fieldSenha.getPassword()));
				usuarioEntity.setEmail(fieldEmail.getText());

				String mensagem = null;

				try {
					if (fieldID.getText().equals("")) {
						mensagem = new UsuarioService().salvarUsuario(usuarioEntity);
					} else {
						usuarioEntity.setId(Long.parseLong(fieldID.getText()));
						mensagem = new UsuarioBO().AlterarUsuario(usuarioEntity);
					}

					limparCampos();
					JOptionPane.showMessageDialog(null, mensagem);
					ListaUsuario listaUsuario = new ListaUsuario();
					listaUsuario.setVisible(true);
					dispose();
				} catch (NegocioException ex) {
					JOptionPane.showMessageDialog(null, ex.getMensagemErro()+ "Erro ao cadastrar usuário");
				}

			}
		});


		
		fieldSenha = new JPasswordField();
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaUsuario listaUsuario = new ListaUsuario();

				listaUsuario.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(btnCadastrar)
							.addGap(18)
							.addComponent(btnVoltar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(fieldSenha))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(fieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(201)
							.addComponent(lblNewLabel)))
					.addContainerGap(205, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3)
						.addComponent(fieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(fieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(fieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnCadastrar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void limparCampos(){
		fieldID.setText("");
		fieldNome.setText("");
		fieldLogin.setText("");
		fieldSenha.setText("");
		fieldEmail.setText("");
	}

	public void CarregarUsuarioId(Long idUser){
		try {
			UsuarioEntity usuarioEntity = new UsuarioService().buscarUsuarioId(idUser);

			if (usuarioEntity == null){
				JOptionPane.showMessageDialog(null, "O usuário não foi encontrado Erro " + JOptionPane.ERROR_MESSAGE);
			} else{
				fieldID.setText(""+usuarioEntity.getId());
				fieldNome.setText(usuarioEntity.getNome());
				fieldLogin.setText(usuarioEntity.getLogin());
				fieldSenha.setText(usuarioEntity.getSenha());
				fieldEmail.setText(usuarioEntity.getEmail());
			}

		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, e.getMensagemErro() + "Erro " + JOptionPane.ERROR_MESSAGE);
		}
	}
}
