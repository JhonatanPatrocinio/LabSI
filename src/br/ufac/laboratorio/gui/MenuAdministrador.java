package br.ufac.laboratorio.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.gui.centro.CadastroCentro;
import br.ufac.laboratorio.gui.centro.MenuEditarCentro;
import br.ufac.laboratorio.gui.laboratorio.CadastroLaboratorio;
import br.ufac.laboratorio.gui.laboratorio.EditarLaboratorio;
import br.ufac.laboratorio.gui.reserva.AnaliseReservas;
import br.ufac.laboratorio.gui.reserva.ListaReservas;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class MenuAdministrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuAdministrador frame = new MenuAdministrador(cnx);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//

	/**
	 * Create the frame.
	 */
	public MenuAdministrador(Conexao cnx) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCentro = new JMenu();
		mnCentro.setMnemonic('C');
		mnCentro.setText("Centro");
		menuBar.add(mnCentro);
		
		JMenuItem mntmCadastrarCentro = new JMenuItem("Cadastrar Centro");
		mntmCadastrarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCentro ce = new CadastroCentro(cnx);
				ce.setVisible(true);
			}
		});
		mnCentro.add(mntmCadastrarCentro);
		
		JMenuItem mntmEditarCentro = new JMenuItem("Listar Centros");
		mntmEditarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEditarCentro mec = new MenuEditarCentro(cnx);
				mec.setVisible(true);
			}
		});
		mnCentro.add(mntmEditarCentro);

		JMenu mnReservas = new JMenu("Reservas");
		mnReservas.setMnemonic('R');
		menuBar.add(mnReservas);
		
		JMenuItem mntmAnalise = new JMenuItem("Analisar Reservas");
		mntmAnalise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaliseReservas ar = new AnaliseReservas(cnx);
				ar.setVisible(true);
			}
		});
		mnReservas.add(mntmAnalise);
		
		JMenuItem mntmListarReservas = new JMenuItem("Listar Reservas");
		mntmListarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaReservas lr = new ListaReservas(cnx);
				lr.setVisible(true);
			}
		});
		mnReservas.add(mntmListarReservas);

		JMenu mnLaboratorio = new JMenu("Laboratorio");
		mnLaboratorio.setMnemonic('L');
		menuBar.add(mnLaboratorio);
		
		JMenuItem mntmCadastrarLaboratrio = new JMenuItem("Cadastrar Laboratório");
		mntmCadastrarLaboratrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroLaboratorio cl = new CadastroLaboratorio(cnx);
				cl.setVisible(true);
			}
		});
		mnLaboratorio.add(mntmCadastrarLaboratrio);
		
		JMenuItem mntmListarLaboratrios = new JMenuItem("Listar Laboratórios");
		mntmListarLaboratrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarLaboratorio el = new EditarLaboratorio(cnx);
				el.setVisible(true);
			}
		});
		mnLaboratorio.add(mntmListarLaboratrios);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSair){

					try {
						cnx.desconecte();
					} catch (DataBaseNotConnectedException | DataBaseGenericException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Desconectar", JOptionPane.ERROR_MESSAGE);
					}
					dispose();
				}
				
			}
		});

		
		
		Icon imagemUfac = new ImageIcon(getClass().getResource("images/ufac.png"));
		JLabel lblImagem = new JLabel(imagemUfac);
		
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(582, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAdministrador)
						.addComponent(btnSair))
					.addGap(16))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(156)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(27)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(btnSair)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
