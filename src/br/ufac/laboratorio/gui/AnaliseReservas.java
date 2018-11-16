package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnaliseReservas extends JDialog {

	private JPanel contentPane;
	private JTable tabAnaliseReservas;



	/**
	 * Create the frame.
	 */
	public AnaliseReservas() {
		
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblAnaliseDeReservas = new JLabel("ANALISE DE RESERVAS");
		lblAnaliseDeReservas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		tabAnaliseReservas = new JTable();
		
		JRadioButton rdbtnAceitar = new JRadioButton("Aceitar");
		rdbtnAceitar.setEnabled(false);
		
		JRadioButton rdbtnRecusar = new JRadioButton("Recusar");
		rdbtnRecusar.setEnabled(false);
		
		JButton btnSalvarAnalise = new JButton("Salvar Analise");
		btnSalvarAnalise.setEnabled(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//MenuAdministrador ma = new MenuAdministrador();

					dispose();

					//ma.setVisible(true);
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(423, Short.MAX_VALUE)
					.addComponent(lblAdministrador)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(139)
					.addComponent(lblAnaliseDeReservas)
					.addContainerGap(290, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabAnaliseReservas, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVoltar)
					.addGap(76)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addComponent(btnSalvarAnalise))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnAceitar)
							.addGap(18)
							.addComponent(rdbtnRecusar)))
					.addContainerGap(158, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(41)
					.addComponent(lblAnaliseDeReservas)
					.addGap(68)
					.addComponent(tabAnaliseReservas, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnAceitar)
								.addComponent(rdbtnRecusar))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSalvarAnalise))
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
