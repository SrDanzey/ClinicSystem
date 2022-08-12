package br.com.clinicsystem.agendaconsultoria.view.medico;

import br.com.clinicsystem.agendaconsultoria.core.entity.MedicoEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.MedicoService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaMedico extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<MedicoEntity> medicoEntities = new ArrayList<MedicoEntity>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaMedico frame = new ListaMedico();
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
	public ListaMedico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MedicoEntity medicoSelecionado = medicoEntities.get(table.getSelectedRow());
				int confirm = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja excluir este medico?"+ medicoSelecionado.getNome());
				if (confirm == 0 ){
					try {
						new MedicoService().deletarMedico(medicoSelecionado.getId());
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
				MedicoEntity medicoSelecionado = medicoEntities.get(table.getSelectedRow());
				CadastroMedico cadastroMedico = new CadastroMedico();
				cadastroMedico.carregarMedicoPorID(medicoSelecionado.getId());
				cadastroMedico.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel = new JLabel("Lista de Medicos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroMedico cadastroMedico = new CadastroMedico();
				cadastroMedico.setVisible(true);
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
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(283)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(185, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(182))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
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
				"ID", "Nome"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		popularTabela();
	}

	public void popularTabela(){
		try {
			MedicoService medicoService = new MedicoService();
			medicoEntities = medicoService.listarMedico();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (MedicoEntity medicoEntity: medicoEntities) {
				model.addRow(new Object[]{
						medicoEntity.getId(),
						medicoEntity.getNome()
				});
			}

		} catch (NegocioException e) {
			e.printStackTrace();
		}

	}

}
