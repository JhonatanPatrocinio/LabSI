package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;

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

public class PerfilProfessor extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerfilProfessor frame = new PerfilProfessor(null, null);
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
	public PerfilProfessor(Professor professor, Conexao cnx) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		JButton btnEditarInforProf = new JButton("Editar Informações");
		btnEditarInforProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnEditarInforProf){

					EditarProfessor ep = new EditarProfessor(professor, cnx);

					dispose();

					ep.setVisible(true);
				}
				
			}
		});
		
		JButton btnReservarLaboratorio = new JButton("Reservar Laboratorio");
		btnReservarLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnReservarLaboratorio){

					ReservaLaboratorio rl = new ReservaLaboratorio();

					dispose();

					rl.setVisible(true);
				}
				
			}
		});
		
		JButton btnVerificarReservas = new JButton("Verificar Reservas");
		btnVerificarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnVerificarReservas){

					ListaHorariosProf lhp = new ListaHorariosProf();

					dispose();

					lhp.setVisible(true);
				}
			}
		});
		
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
							.addComponent(lblProfessor))
						.addComponent(btnEditarInforProf)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnReservarLaboratorio))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVerificarReservas))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSairProf)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditarInforProf)
							.addGap(164)
							.addComponent(btnReservarLaboratorio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVerificarReservas))
						.addComponent(lblProfessor))
					.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
					.addComponent(btnSairProf))
		);
		
		JLabel lblNomeProfessor = new JLabel(professor.getNome());
		panel.add(lblNomeProfessor);
		lblNomeProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
