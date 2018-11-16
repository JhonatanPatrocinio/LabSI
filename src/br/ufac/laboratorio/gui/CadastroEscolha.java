package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroEscolha extends JFrame {

	private JPanel contentPane;
	//private Conexao cnx;

	/**
	 * Create the frame.
	 */
	public CadastroEscolha(Conexao cnx) {
//		this.cnx = cnx;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCadastro = new JLabel("CADASTRO");
		lblCadastro.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		JButton btnAluno = new JButton("ALUNO");
		btnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnAluno){
					CadastroAluno ca = new CadastroAluno();

					dispose();

					ca.setVisible(true);
				}
			}


		});

		JButton btnProfessor = new JButton("PROFESSOR");
		btnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnProfessor){
					CadastroProfessor cp = new CadastroProfessor(cnx);

					dispose();

					cp.setVisible(true);
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addGap(19)
						.addComponent(btnAluno, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
						.addComponent(btnProfessor, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
						.addGap(17))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(161)
						.addComponent(lblCadastro)
						.addContainerGap(167, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblCadastro)
						.addGap(73)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnProfessor, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAluno, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);
	}

}
