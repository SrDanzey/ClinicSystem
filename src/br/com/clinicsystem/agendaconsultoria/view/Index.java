package br.com.clinicsystem.agendaconsultoria.view;

import br.com.clinicsystem.agendaconsultoria.view.agenda.ListaAgenda;
import br.com.clinicsystem.agendaconsultoria.view.clinica.ListaClinica;
import br.com.clinicsystem.agendaconsultoria.view.clinicaMedico.ListaClinicaMedico;
import br.com.clinicsystem.agendaconsultoria.view.medico.ListaMedico;
import br.com.clinicsystem.agendaconsultoria.view.paciente.ListaPaciente;
import br.com.clinicsystem.agendaconsultoria.view.usuario.ListaUsuario;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=51,19
	 */
	private final JLabel ClinicSystem = new JLabel("Consultoria de Clinica");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
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
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 418);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Administra\u00E7\u00E3o");
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAgenda = new JMenuItem("Agenda");
		mntmAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaAgenda listaAgenda = new ListaAgenda();
				listaAgenda.setVisible(true);
			}
		});
		mnNewMenu.add(mntmAgenda);
		
		JMenuItem mntmClinicaMedico = new JMenuItem("Clinica e Medico");
		mntmClinicaMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClinicaMedico listaClinicaMedico = new ListaClinicaMedico();
				listaClinicaMedico.setVisible(true);
			}
		});
		mnNewMenu.add(mntmClinicaMedico);
		
		JMenuItem mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPaciente listaPaciente = new ListaPaciente();
				listaPaciente.setVisible(true);
			}
		});
		mnNewMenu.add(mntmPaciente);
		
		JMenuItem mntmMedico = new JMenuItem("Medico");
		mntmMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaMedico listaMedico = new ListaMedico();
				listaMedico.setVisible(true);
			}
		});
		mnNewMenu.add(mntmMedico);
		
		JMenuItem mntmClinica = new JMenuItem("Clinica");
		mntmClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClinica listaClinica = new ListaClinica();
				listaClinica.setVisible(true);
			}
		});
		mnNewMenu.add(mntmMedico);

		

		mnNewMenu.add(mntmClinica);
		
		JMenuItem mntmUsuario = new JMenuItem("Usuario");
		mntmUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaUsuario listaUsuario = new ListaUsuario();
				listaUsuario.setVisible(true);
			}
		});
		mnNewMenu.add(mntmUsuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("BEM VINDO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
		
		JLabel lblNewLabel_1 = new JLabel("CLINICSYSTEM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 70));
		
		JLabel lblNewLabel_2 = new JLabel("\u00C1rea Administrativa");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(119, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(95))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(190, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(179))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(209)
					.addComponent(lblNewLabel_2)
					.addContainerGap(216, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(26)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
