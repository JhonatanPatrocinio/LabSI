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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAdministrador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdministrador frame = new MenuAdministrador();
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
	public MenuAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnAnaliseDeReservas = new JButton("Analise de Reservas");
		btnAnaliseDeReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnAnaliseDeReservas){

					AnaliseReservas ar = new AnaliseReservas();

					dispose();

					ar.setVisible(true);
				}
				
				
			}
		});
		
		JButton btnListaDeReservas = new JButton("Lista de Reservas");
		btnListaDeReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnListaDeReservas){

					ListaReservas lr = new ListaReservas();

					dispose();

					lr.setVisible(true);
				}
				
				
			}
		});
		
		JButton btnCadastrarLaboratorio = new JButton("Cadastrar Laboratorio");
		btnCadastrarLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnCadastrarLaboratorio){

					CadastroLaboratorio cl = new CadastroLaboratorio();

					dispose();

					cl.setVisible(true);
				}
				
				
			}
		});
		
		JButton btnCadastrarCentro = new JButton("Cadastrar Centro");
		btnCadastrarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(e.getSource()==btnCadastrarCentro){

					CadastroCentro cc = new CadastroCentro();

					dispose();

					cc.setVisible(true);
				}
			
			}
		});
		
		JButton btnEditarLaboratorio = new JButton("Editar Laboratorio");
		btnEditarLaboratorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnEditarLaboratorio){

					EditarLaboratorio el = new EditarLaboratorio();

					dispose();

					el.setVisible(true);
				}
				
			}
		});
		
		JButton btnEditarCentro = new JButton("Editar Centro");
		btnEditarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnEditarCentro){

					EditarCentro ec = new EditarCentro();

					dispose();

					ec.setVisible(true);
				}
				
			}
		});
		
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(423, Short.MAX_VALUE)
					.addComponent(lblAdministrador)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCadastrarCentro, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAnaliseDeReservas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnListaDeReservas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCadastrarLaboratorio, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnEditarCentro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEditarLaboratorio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(137, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSair)
					.addContainerGap(367, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnaliseDeReservas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnListaDeReservas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarLaboratorio)
						.addComponent(btnEditarLaboratorio))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarCentro)
						.addComponent(btnEditarCentro))
					.addPreferredGap(ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
					.addComponent(btnSair)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}
