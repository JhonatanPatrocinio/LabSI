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

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Aluno;
import br.ufac.laboratorio.gui.TelaInicial;
import br.ufac.laboratorio.gui.professor.EditarProfessor;
import br.ufac.laboratorio.gui.professor.ListaHorariosProf;

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
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class PerfilAluno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public PerfilAluno(Aluno al, Conexao cnx) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setMnemonic('P');
		menuBar.add(mnPerfil);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Editar Informações");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarAluno ea = new EditarAluno(al, cnx);

				//dispose();

				ea.setVisible(true);
			}
		});
		mnPerfil.add(mntmNewMenuItem);

//		JButton btnEditarInforAlu = new JButton("Editar Informações");
//		btnEditarInforAlu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				if(e.getSource()==btnEditarInforAlu){
//					
//					EditarAluno ea = new EditarAluno(al, cnx);
//					
//					//dispose();
//					
//					ea.setVisible(true);
//				}
//			}
//			
//		});
//		mnPerfil.add(btnEditarInforAlu);

		JMenu mnHorarios = new JMenu("Horarios");
		mnHorarios.setMnemonic('H');
		menuBar.add(mnHorarios);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Verificar Horários");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaHorarios lh = new ListaHorarios();

				//dispose();

				lh.setVisible(true);
			}
		});
		mnHorarios.add(mntmNewMenuItem_1);

//		JButton btnVerificarHorarios = new JButton("Verificar Horarios");
//		mnHorarios.add(btnVerificarHorarios);
//		btnVerificarHorarios.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				if(e.getSource()==btnVerificarHorarios){
//
//					ListaHorarios lh = new ListaHorarios();
//
//					//dispose();
//
//					lh.setVisible(true);
//				}
//			}
//
//
//		});
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


		Icon imagemUfac = new ImageIcon(getClass().getResource("../images/ufac.png"));
		JLabel lblImagem = new JLabel(imagemUfac);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
							.addComponent(lblAluno))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(399, Short.MAX_VALUE)
							.addComponent(btnSairAlu)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addGap(33)
								.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
							.addComponent(lblAluno))
						.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addComponent(btnSairAlu)
						.addContainerGap())
			);

		JLabel lblNomeAluno = new JLabel(al.getNome());
		panel.add(lblNomeAluno);
		lblNomeAluno.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
