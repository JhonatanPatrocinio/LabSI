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

public class ListaReservas extends JDialog{

	private JPanel contentPane;
	private JTable tabListaReservas;

	
	/**
	 * Create the frame.
	 */
	public ListaReservas() {

		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblListaDeReservas = new JLabel("LISTA DE RESERVAS");
		lblListaDeReservas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		tabListaReservas = new JTable();
		
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(363, Short.MAX_VALUE)
					.addComponent(lblAdministrador)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabListaReservas, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(8, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(btnVoltar)
					.addContainerGap(409, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(lblListaDeReservas)
					.addGap(147))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(42)
					.addComponent(lblListaDeReservas)
					.addGap(67)
					.addComponent(tabListaReservas, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(btnVoltar)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
