package br.ufac.laboratorio.gui;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroCentro extends JFrame {

	private JPanel contentPane;
	private JTextField tdNomeCentro;
	private JTextField textField;


	public CadastroCentro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblCadastroCentro = new JLabel("CADASTRO CENTRO");
		lblCadastroCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNome = new JLabel("NOME");
		
		tdNomeCentro = new JTextField();
		tdNomeCentro.setColumns(40);
		
		JButton btnCadastrarCentro = new JButton("Cadastrar");
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					MenuAdministrador ma = new MenuAdministrador();

					dispose();

					ma.setVisible(true);
				}
				
			}
		});
		
		JLabel lblSigla = new JLabel("SIGLA");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(317, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(100)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(lblSigla, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(tdNomeCentro, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addContainerGap(351, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(172, Short.MAX_VALUE)
					.addComponent(btnCadastrarCentro)
					.addGap(163))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(115)
					.addComponent(lblCadastroCentro)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(13)
					.addComponent(lblCadastroCentro)
					.addGap(81)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSigla)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome)
								.addComponent(tdNomeCentro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
							.addComponent(btnCadastrarCentro)
							.addGap(32))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
							.addComponent(btnVoltar))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
