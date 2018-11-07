package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;
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

public class PerfilAluno extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilAluno frame = new PerfilAluno();
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
	public PerfilAluno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));

		JButton btnEditarInforAlu = new JButton("Editar Informações");
		btnEditarInforAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnEditarInforAlu){

					EditarAluno ea = new EditarAluno();

					dispose();

					ea.setVisible(true);
				}
			}

		});

		JButton btnVerificarHorarios = new JButton("Verificar Horarios");
		btnVerificarHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnVerificarHorarios){

					ListaHorarios lh = new ListaHorarios();

					dispose();

					lh.setVisible(true);
				}
			}


		});

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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
										.addComponent(lblAluno))
								.addComponent(btnEditarInforAlu)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnSairAlu))
								.addComponent(btnVerificarHorarios))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnEditarInforAlu))
								.addComponent(lblAluno))
						.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
						.addComponent(btnVerificarHorarios)
						.addGap(119)
						.addComponent(btnSairAlu))
				);

		JLabel lblNomeAluno = new JLabel("Nome Aluno");
		panel.add(lblNomeAluno);
		lblNomeAluno.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
