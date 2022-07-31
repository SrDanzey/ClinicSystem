package br.com.clinicsystem.agendaconsultoria.view.clinicaMedico;

import br.com.clinicsystem.agendaconsultoria.core.clinicaMedico.ClinicaMedicoDAO;
import br.com.clinicsystem.agendaconsultoria.core.clinicaMedico.ClinicaMedicoEntity;
import br.com.clinicsystem.agendaconsultoria.core.clinicaMedico.ClinicaMedicoService;
import br.com.clinicsystem.agendaconsultoria.core.paciente.PacienteService;
import br.com.clinicsystem.agendaconsultoria.core.validacao.exception.NegocioException;
import br.com.clinicsystem.agendaconsultoria.view.medico.CadastroMedico;

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaClinicaMedico extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ArrayList<ClinicaMedicoEntity> clinicaMedicoEntities = new ArrayList<ClinicaMedicoEntity>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaClinicaMedico frame = new ListaClinicaMedico();
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
	public ListaClinicaMedico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Rela\u00E7\u00E3o entre Clinica e medico");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClinicaMedicoEntity clinicaMedico = clinicaMedicoEntities.get(table.getSelectedRow());
				int confirm = JOptionPane.showConfirmDialog(null,
						"Você realmente deseja excluir esta relação?"+ clinicaMedico.getId());

				if (confirm == 0){
					try {
						new ClinicaMedicoService().deletarClinicaMedico(clinicaMedico.getId());
						populartabela();
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
				ClinicaMedicoEntity clinicaMedico = clinicaMedicoEntities.get(table.getSelectedRow());
				CadastroClinicaMedico cadastroClinicaMedico = new CadastroClinicaMedico();
				cadastroClinicaMedico.carregarClinicaMedicoPorID(clinicaMedico.getId());
				cadastroClinicaMedico.setVisible(true);
				dispose();
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setEnabled(false);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClinicaMedico cadastroClinicaMedico = new CadastroClinicaMedico();
				cadastroClinicaMedico.setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(89))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(278)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
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
				"ID", "ID Clinica", "ID Medico"
			}
		));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		populartabela();
	}

	public void populartabela(){
		try {
			ClinicaMedicoService clinicaMedicoService = new ClinicaMedicoService();
			clinicaMedicoEntities = clinicaMedicoService.listarClinicaMedico();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.getDataVector().removeAllElements();

			for (ClinicaMedicoEntity clinicaMedico: clinicaMedicoEntities) {
				model.addRow(new Object[]{
						clinicaMedico.getId(),
						clinicaMedico.getFk_idClinica(),
						clinicaMedico.getFk_idMedico()
				});
			}

		} catch (NegocioException e) {
			e.printStackTrace();
		}


	}
}
