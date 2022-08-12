package br.com.clinicsystem.agendaconsultoria.view.agenda;

import br.com.clinicsystem.agendaconsultoria.core.entity.AgendaEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.AgendaService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListaAgenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<AgendaEntity> agendaEntities = new ArrayList<AgendaEntity>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaAgenda frame = new ListaAgenda();
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
	public ListaAgenda() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Lista de Agendamentos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgendaEntity agendaSelecionada = agendaEntities.get(table.getSelectedRow());
				int confirm = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja excluir este agendamento?"+ agendaSelecionada.getId());
				if (confirm == 0){
					try {
						new AgendaService().deletarAgenda(agendaSelecionada.getId());
						popularTabela();
					} catch (NegocioException ex) {
						JOptionPane.showMessageDialog(null,ex.getMensagemErro());
					}
				}
			}
		});
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgendaEntity agendaSelecionada = agendaEntities.get(table.getSelectedRow());
				CadastroAgenda cadastroAgenda = new CadastroAgenda();
				cadastroAgenda.carregarAgendaPorID(agendaSelecionada.getId());
				cadastroAgenda.setVisible(true);
				dispose();
			}
		});
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroAgenda cadastroAgenda = new CadastroAgenda();
				cadastroAgenda.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
									.addComponent(lblNewLabel)))
							.addGap(18)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addGap(21))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Horario", "ID do Paciente", "ID da clinica e mdico"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		popularTabela();
	}

	public void popularTabela(){
		try {
			AgendaService agendaService = new AgendaService();
			agendaEntities = agendaService.listarAgenda();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (AgendaEntity agendaEntity: agendaEntities) {
				model.addRow(new Object[]{
						agendaEntity.getId(),
						agendaEntity.getHorario(),
						agendaEntity.getFk_idPaciente(),
						agendaEntity.getFk_idClinicaMedico()
				});
			}
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
}

