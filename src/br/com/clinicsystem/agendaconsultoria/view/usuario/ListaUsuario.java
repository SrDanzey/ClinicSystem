package br.com.clinicsystem.agendaconsultoria.view.usuario;

import br.com.clinicsystem.agendaconsultoria.core.entity.UsuarioEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.UsuarioService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaUsuario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField fieldIDFiltro;
	private JTextField fieldNomeFiltro;
	private JTextField fieldLoginFiltro;
	private JTextField fieldEmailFiltro;
	private ArrayList<UsuarioEntity> usuarioEntities = new ArrayList<UsuarioEntity>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaUsuario frame = new ListaUsuario();
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
	public ListaUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroUsuario cadastroUsuario = new CadastroUsuario();
				cadastroUsuario.setVisible(true);
				dispose();
			}
		});
		
		JButton btnDeletar = new JButton("DELETAR");
		btnDeletar.setEnabled(false);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEntity usuarioSelecionado = usuarioEntities.get(table.getSelectedRow());
				int confirm = JOptionPane.showConfirmDialog(null,
						"Voce realmente deseja deletar o usuario com o codigo "+ usuarioSelecionado.getId());
				if (confirm == 0){
					try {
						new UsuarioService().excluirUsuario(usuarioSelecionado.getId());
						popularTabela();
					} catch (NegocioException ex) {
						JOptionPane.showConfirmDialog(null,ex.getMensagemErro());
					}
				}
			}
		});
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEntity usuarioSelecionado = usuarioEntities.get(table.getSelectedRow());
				CadastroUsuario cadastroUsuario = new CadastroUsuario();
				cadastroUsuario.CarregarUsuarioId(usuarioSelecionado.getId());
				cadastroUsuario.setVisible(true);
				dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Filtro de Pesuisa");
		
		JLabel lblNewLabel_1 = new JLabel("id");
		
		fieldIDFiltro = new JTextField();
		fieldIDFiltro.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("nome");
		
		fieldNomeFiltro = new JTextField();
		fieldNomeFiltro.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("login");
		
		fieldLoginFiltro = new JTextField();
		fieldLoginFiltro.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("email");
		
		fieldEmailFiltro = new JTextField();
		fieldEmailFiltro.setColumns(10);
		
		JButton btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioEntity usuarioEntity = new UsuarioEntity();
				usuarioEntity.setNome(fieldNomeFiltro.getText());
				usuarioEntity.setLogin(fieldLoginFiltro.getText());
				usuarioEntity.setEmail(fieldEmailFiltro.getText());
				try {
					if (!fieldIDFiltro.getText().equals("")){
						Long id = Long.parseLong(fieldIDFiltro.getText());
						usuarioEntity.setId(id);
					}
				} catch (Exception ex){
					JOptionPane.showMessageDialog(null, "O valor no campo codigo precisa ser numerico!");
				}

				popularTabelaFiltrada(usuarioEntity);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 710, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addGap(33)
								.addComponent(btnDeletar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldIDFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldNomeFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(fieldLoginFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(fieldEmailFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnPesquisar))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(fieldIDFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(fieldNomeFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(fieldLoginFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(fieldEmailFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnPesquisar)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeletar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnDeletar.setEnabled(true);
				btnEditar.setEnabled(true);

			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nome", "login", "senha", "email"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		popularTabela();
	}

	private void popularTabela(){
		try {
			usuarioEntities = new UsuarioService().listarUsuario();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (UsuarioEntity usuarioEntity: usuarioEntities) {
				model.addRow(new Object[] {
						usuarioEntity.getId(),
						usuarioEntity.getNome(),
						usuarioEntity.getLogin(),
						usuarioEntity.getSenha(),
						usuarioEntity.getEmail()
				});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar dados do banco" + e.getMensagemErro());
		}
	}


	private void popularTabelaFiltrada(UsuarioEntity user){
		try {
			usuarioEntities = new UsuarioService().buscaUsuarioFiltrado(user);
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (UsuarioEntity usuarioEntity: usuarioEntities) {
				model.addRow(new Object[] {
						usuarioEntity.getId(),
						usuarioEntity.getNome(),
						usuarioEntity.getLogin(),
						usuarioEntity.getSenha(),
						usuarioEntity.getEmail()
				});
			}
		} catch (NegocioException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar dados do banco" + e.getMensagemErro());
		}
	}
}
