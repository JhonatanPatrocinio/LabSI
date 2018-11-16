package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class MenuAdministrador extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public MenuAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCentro = new JMenu("Centro");
		mnCentro.setMnemonic('C');
		menuBar.add(mnCentro);
		
		JButton btnCadastrarCentro = new JButton("Cadastrar Centro");
		mnCentro.add(btnCadastrarCentro);
		
		JButton btnEditarCentro = new JButton("Editar Centro");
		mnCentro.add(btnEditarCentro);
		
		JMenu mnReservas = new JMenu("Reservas");
		mnReservas.setMnemonic('R');
		menuBar.add(mnReservas);
		
		JButton btnAnaliseDeReservas = new JButton("Analise de Reservas");
		mnReservas.add(btnAnaliseDeReservas);
		
		JButton btnListaDeReservas = new JButton("Lista de Reservas");
		mnReservas.add(btnListaDeReservas);
		
		JMenu mnLaboratorio = new JMenu("Laboratorio");
		mnLaboratorio.setMnemonic('L');
		menuBar.add(mnLaboratorio);
		
		JButton btnCadastrarLaboratorio = new JButton("Cadastrar Laboratorio");
		mnLaboratorio.add(btnCadastrarLaboratorio);
		
		JButton btnEditarLaboratorio = new JButton("Editar Laboratorio");
		mnLaboratorio.add(btnEditarLaboratorio);
		btnEditarLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnEditarLaboratorio){

					EditarLaboratorio el = new EditarLaboratorio();

					//dispose();

					el.setVisible(true);
				}
				
			}
		});
		btnCadastrarLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnCadastrarLaboratorio){

					CadastroLaboratorio cl = new CadastroLaboratorio();

					//dispose();

					cl.setVisible(true);
				}
				
				
			}
		});
		btnListaDeReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnListaDeReservas){

					ListaReservas lr = new ListaReservas();

					//dispose();

					lr.setVisible(true);
				}
				
				
			}
		});
		btnAnaliseDeReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnAnaliseDeReservas){

					AnaliseReservas ar = new AnaliseReservas();

					//dispose();

					ar.setVisible(true);
				}
				
				
			}
		});
		btnEditarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnEditarCentro){

					EditarCentro ec = new EditarCentro();

					//dispose();

					ec.setVisible(true);
				}
				
			}
		});
		btnCadastrarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource()==btnCadastrarCentro){

					CadastroCentro cc = new CadastroCentro();

					//dispose();

					cc.setVisible(true);
				}
			
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnSair){

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
					.addContainerGap(262, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAdministrador)
								.addComponent(btnSair))
							.addGap(16))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
							.addGap(54))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(18)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addComponent(btnSair)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
