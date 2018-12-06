package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.gui.aluno.CadastroAluno;
import br.ufac.laboratorio.gui.professor.CadastroProfessor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastroEscolha extends JFrame {

	private JPanel contentPane;
	//private Conexao cnx;

	/**
	 * Create the frame.
	 */
	public CadastroEscolha(Conexao cnx) {
//		this.cnx = cnx;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 383);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCadastro = new JLabel("CADASTRO");
		lblCadastro.setFont(new Font("Lucida Grande", Font.PLAIN, 30));

		JButton btnAluno = new JButton("ALUNO");
		btnAluno.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnAluno.setIcon(new ImageIcon(CadastroEscolha.class.getResource("/br/ufac/laboratorio/gui/images/page_boy.gif")));
		btnAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnAluno){
					CadastroAluno ca = new CadastroAluno(cnx);

					dispose();

					ca.setVisible(true);
				}
			}


		});

		JButton btnProfessor = new JButton("PROFESSOR");
		btnProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnProfessor.setIcon(new ImageIcon(CadastroEscolha.class.getResource("/br/ufac/laboratorio/gui/images/icon_user.gif")));
		btnProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnProfessor){
					CadastroProfessor cp = new CadastroProfessor(cnx);

					dispose();

					cp.setVisible(true);
				}
				
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadastroEscolha.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(label)
					.addGap(102)
					.addComponent(lblCadastro)
					.addContainerGap(176, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addComponent(btnAluno, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnProfessor, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCadastro))
						.addComponent(label))
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProfessor, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAluno, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
					.addGap(97))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
