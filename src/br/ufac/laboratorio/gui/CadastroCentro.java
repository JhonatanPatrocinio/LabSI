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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroCentro extends JDialog {

	private JPanel contentPane;
	private JTextField tfNomeCentro;
	private JTextField tfSiglaCadas;

	
	/**
	 * Create the frame.
	 */
	public CadastroCentro() {
		
		setBounds(100, 100, 450, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblCadastroCentro = new JLabel("CADASTRO CENTRO");
		lblCadastroCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNome = new JLabel("NOME");
		
		tfNomeCentro = new JTextField();
		tfNomeCentro.setColumns(40);
		
		JButton btnCadastrarCentro = new JButton("Cadastrar");
		
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
		
		JLabel lblSigla = new JLabel("SIGLA");
		
		tfSiglaCadas = new JTextField();
		tfSiglaCadas.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(319, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSigla)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfSiglaCadas)
								.addComponent(tfNomeCentro, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 132, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addContainerGap(353, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(173, Short.MAX_VALUE)
					.addComponent(btnCadastrarCentro)
					.addGap(163))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(115)
					.addComponent(lblCadastroCentro)
					.addContainerGap(127, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(13)
					.addComponent(lblCadastroCentro)
					.addGap(94)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSigla)
						.addComponent(tfSiglaCadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome)
								.addComponent(tfNomeCentro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
							.addComponent(btnCadastrarCentro)
							.addGap(32))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
							.addComponent(btnVoltar))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
