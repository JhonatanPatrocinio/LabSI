package br.ufac.laboratorio.gui.aluno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;

import br.ufac.laboratorio.gui.TelaInicial;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class PerfilAluno extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public PerfilAluno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setMnemonic('P');
		menuBar.add(mnPerfil);

		JButton btnEditarInforAlu = new JButton("Editar Informações");
		mnPerfil.add(btnEditarInforAlu);

		JMenu mnHorarios = new JMenu("Horarios");
		mnHorarios.setMnemonic('H');
		menuBar.add(mnHorarios);

		JButton btnVerificarHorarios = new JButton("Verificar Horarios");
		mnHorarios.add(btnVerificarHorarios);
		btnVerificarHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnVerificarHorarios){

					ListaHorarios lh = new ListaHorarios();

					//dispose();

					lh.setVisible(true);
				}
			}


		});
		btnEditarInforAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnEditarInforAlu){

					EditarAluno ea = new EditarAluno();

					//dispose();

					ea.setVisible(true);
				}
			}

		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));

		JLabel lblAluno = new JLabel("ALUNO");
		lblAluno.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSairAlu = new JButton("< SAIR");
		btnSairAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSairAlu){

					TelaInicial ti = new TelaInicial();

					dispose();

					ti.setVisible(true);
				}

			}
		});


		Icon imagemUfac = new ImageIcon(getClass().getResource("images/ufac.png"));
		JLabel lblImagem = new JLabel(imagemUfac);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
										.addComponent(lblAluno)
										.addContainerGap())
								.addComponent(btnSairAlu, Alignment.TRAILING)))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(91)
						.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(106, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAluno)
										.addPreferredGap(ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
										.addComponent(btnSairAlu))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(63, Short.MAX_VALUE))))
				);

		JLabel lblNomeAluno = new JLabel("Nome Aluno");
		panel.add(lblNomeAluno);
		lblNomeAluno.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
