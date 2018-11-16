package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import java.awt.Label;

public class PerfilProfessor extends JFrame {
	
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public PerfilProfessor(Professor professor, Conexao cnx) {

		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setMnemonic('F');
		menuBar.add(mnPerfil);

		JButton btnEditarInforProf = new JButton("Editar Informações");
		mnPerfil.add(btnEditarInforProf);
		btnEditarInforProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnEditarInforProf){

					EditarProfessor ep = new EditarProfessor(professor, cnx);

					//dispose();

					ep.setVisible(true);
				}

			}
		});

		JMenu mnReservas = new JMenu("Reservas");
		mnReservas.setMnemonic('R');
		menuBar.add(mnReservas);

		JButton btnVerificarReservas = new JButton("Verificar Reservas");
		mnReservas.add(btnVerificarReservas);

		JButton btnReservarLaboratorio = new JButton("Reservar Laboratorio");
		mnReservas.add(btnReservarLaboratorio);
		btnReservarLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnReservarLaboratorio){

					ReservaLaboratorio rl = new ReservaLaboratorio();

					//dispose();

					rl.setVisible(true);
				}

			}
		});
		btnVerificarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnVerificarReservas){

					ListaHorariosProf lhp = new ListaHorariosProf();

					//dispose();

					lhp.setVisible(true);
				}
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));

		JLabel lblProfessor = new JLabel("PROFESSOR");
		lblProfessor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSairProf = new JButton("< SAIR");
		btnSairProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSairProf){
					try {
						cnx.desconecte();
					} catch (DataBaseNotConnectedException | DataBaseGenericException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Editar", JOptionPane.ERROR_MESSAGE);
					}
					TelaInicial ti = new TelaInicial();

					dispose();

					ti.setVisible(true);
				}

			}
		});
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon("/Users/tiagoprata/eclipse-workspace/Tesi1Job/img/ufac.png"));
		

		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSairProf, Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addGap(99)
							.addComponent(lblProfessor)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(152, Short.MAX_VALUE)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProfessor))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnSairProf)
					.addGap(6))
		);

		//Alterar o nome do professor que esta na amostra
		JLabel lblNomeProfessor = new JLabel(professor.getNome());
		panel.add(lblNomeProfessor);
		lblNomeProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}


}
