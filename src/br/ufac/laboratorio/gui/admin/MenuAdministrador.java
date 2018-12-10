package br.ufac.laboratorio.gui.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.gui.centro.CadastroCentro;
import br.ufac.laboratorio.gui.centro.MenuEditarCentro;
import br.ufac.laboratorio.gui.curso.CadastroCurso;
import br.ufac.laboratorio.gui.curso.ListarCurso;
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
//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						MenuAdministrador frame = new MenuAdministrador(cnx);
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}
//	

	/**
	 * Create the frame.
	 */
	public MenuAdministrador(Conexao cnx) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setLocationRelativeTo(null);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCentro = new JMenu();
		mnCentro.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/Home16.gif")));
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

		JMenu mnCurso = new JMenu("Curso");
		mnCurso.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/Edit16.gif")));
		mnCurso.setMnemonic('o');
		menuBar.add(mnCurso);

		JMenuItem mntmCadastrarCurso = new JMenuItem("Cadastrar Curso");
		mntmCadastrarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCurso ca = new CadastroCurso();
				ca.setVisible(true);
			}
		});
		mnCurso.add(mntmCadastrarCurso);

		JMenuItem mntmListarCursos = new JMenuItem("Listar Cursos");
		mntmListarCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCurso lc = new ListarCurso(cnx);
				lc.setVisible(true);
			}
		});
		mnCurso.add(mntmListarCursos);

		JMenu mnLaboratorio = new JMenu("Laboratorio");
		mnLaboratorio.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/Host16.gif")));
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

		JMenu mnReservas = new JMenu("Reservas");
		mnReservas.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/interface_dialog.gif")));
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
		
		


		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/page_user_dark.gif")));
		mnUsuarios.setMnemonic('u');
		menuBar.add(mnUsuarios);

		JMenuItem mntmListarAlunos = new JMenuItem("Listar Alunos");
		mntmListarAlunos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarAluno la = new ListarAluno(cnx);
				la.setVisible(true);
			}
		});

		mnUsuarios.add(mntmListarAlunos);

		JMenuItem mntmListarProfessores = new JMenuItem("Listar Professores");
		mntmListarProfessores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProfessor lp = new ListarProfessor(cnx);
				lp.setVisible(true);
			}
		});
		mnUsuarios.add(mntmListarProfessores);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MenuAdministrador.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));



		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(618, Short.MAX_VALUE)
							.addComponent(btnSair)))
					.addGap(16))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(156)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(160, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAdministrador)
							.addGap(27)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
							.addComponent(btnSair))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
