package br.com.clinicsystem.agendaconsultoria.view.paciente;

import br.com.clinicsystem.agendaconsultoria.core.entity.PacienteEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.PacienteService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaPaciente extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<PacienteEntity> pacienteEntities = new ArrayList<PacienteEntity>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaPaciente frame = new ListaPaciente();
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
	public ListaPaciente() {
		setTitle("Paciente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 634, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Lista de Pacientes");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 35));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnExcluir = new JButton("DELETAR");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacienteEntity pacienteSelecionado = pacienteEntities.get(table.getSelectedRow());
				int confirm = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja excluir este paciente?"+ pacienteSelecionado.getNome());

				if (confirm == 0){
					try {
						new PacienteService().deletarPaciente(pacienteSelecionado.getId());
						popularTabela();
					} catch (NegocioException ex) {
						JOptionPane.showMessageDialog(null,ex.getMensagemErro());
					}
				}
			}
		});

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PacienteEntity pacienteSelecionado = pacienteEntities.get(table.getSelectedRow());
				CadastroPaciente cadastroPaciente = new CadastroPaciente();
				cadastroPaciente.carregarPacientePorID(pacienteSelecionado.getId());
				cadastroPaciente.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPaciente cadastroPaciente = new CadastroPaciente();
				cadastroPaciente.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 567, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
									.addComponent(lblNewLabel)))
							.addGap(18)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CPF", "Sintoma" }));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		popularTabela();
	}

	public void popularTabela(){
		try {
			PacienteService pacienteService = new PacienteService();
			pacienteEntities = pacienteService.listarPaciente();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (PacienteEntity pacienteEntity :pacienteEntities) {
				model.addRow(new Object[]{
						pacienteEntity.getId(),
						pacienteEntity.getNome(),
						pacienteEntity.getCpf(),
						pacienteEntity.getSintoma()
				});
			}

		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
}
