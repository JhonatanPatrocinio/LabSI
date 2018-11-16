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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarLaboratorio extends JDialog {

	private JPanel contentPane;
	private JTable tabEditarLab;
	private JTextField tfEditarLab;

	
	/**
	 * Create the frame.
	 */
	public EditarLaboratorio() {
		
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lbEditarLab = new JLabel("EDITAR LABORATORIO");
		lbEditarLab.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		tabEditarLab = new JTable();
		
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
		
		JLabel lblNome = new JLabel("NOME");
		
		tfEditarLab = new JTextField();
		tfEditarLab.setColumns(40);
		
		JButton btnSalvarLab = new JButton("Salvar");
		btnSalvarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(363, Short.MAX_VALUE)
					.addComponent(lblAdministrador)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabEditarLab, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(8, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVoltar)
					.addGap(122)
					.addComponent(btnSalvarLab)
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(btnExcluir))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(97)
					.addComponent(lblNome)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tfEditarLab, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(96, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(133)
					.addComponent(lbEditarLab)
					.addContainerGap(135, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(44)
					.addComponent(lbEditarLab)
					.addGap(65)
					.addComponent(tabEditarLab, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tfEditarLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSalvarLab))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnExcluir)
								.addComponent(btnVoltar))))
					.addGap(8))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
