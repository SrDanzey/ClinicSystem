package br.com.clinicsystem.agendaconsultoria.view.clinica;

import br.com.clinicsystem.agendaconsultoria.core.entity.ClinicaEntity;
import br.com.clinicsystem.agendaconsultoria.core.service.ClinicaService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListaClinica extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<ClinicaEntity> clinicaEntities = new ArrayList<ClinicaEntity>();
	private JLabel title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaClinica frame = new ListaClinica();
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
	public ListaClinica() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 681, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JButton btnExcluir = new JButton("DELETAR");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClinicaEntity clinicaSelecionada = clinicaEntities.get(table.getSelectedRow());
				int confirm = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja excluir esta clinica?"+ clinicaSelecionada.getNome());

				if (confirm == 0){
					try {
						new ClinicaService().deletarClinica(clinicaSelecionada.getId());
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
				ClinicaEntity clinicaSelecionada = clinicaEntities.get(table.getSelectedRow());
				CadastroClinica cadastroClinica = new CadastroClinica();
				cadastroClinica.carregarClinicaPorID(clinicaSelecionada.getId());
				cadastroClinica.setVisible(true);
				dispose();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		title = new JLabel("Listas das clinicas");
		title.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClinica cadastroClinica = new CadastroClinica();
				cadastroClinica.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(title)
							.addGap(188))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
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
				"ID", "Nome", "Endere\u00E7o", "Telefone"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		popularTabela();
	}

	public void popularTabela(){
		try {
			ClinicaService clinicaService = new ClinicaService();
			clinicaEntities = clinicaService.listarClinica();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (ClinicaEntity clinicaEntity: clinicaEntities) {
				model.addRow(new Object[]{
						clinicaEntity.getId(),
						clinicaEntity.getNome(),
						clinicaEntity.getEndereco(),
						clinicaEntity.getTelefone()
				});
			}
			
			title.setText("Atualizando Clinica");
		} catch (NegocioException e) {
			e.printStackTrace();
		}

	}

}
