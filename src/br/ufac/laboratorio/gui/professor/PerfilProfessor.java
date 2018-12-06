package br.ufac.laboratorio.gui.professor;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class PerfilProfessor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PerfilProfessor(Professor professor, Conexao cnx) {



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setIcon(new ImageIcon(PerfilProfessor.class.getResource("/br/ufac/laboratorio/gui/images/icon_user.gif")));
		mnPerfil.setMnemonic('F');
		menuBar.add(mnPerfil);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Editar Informações");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarProfessor ep = new EditarProfessor(professor, cnx);

				//dispose();

				ep.setVisible(true);
			}
		});
		mnPerfil.add(mntmNewMenuItem);

		JMenu mnReservas = new JMenu("Reservas");
		mnReservas.setIcon(new ImageIcon(PerfilProfessor.class.getResource("/br/ufac/laboratorio/gui/images/interface_dialog.gif")));
		mnReservas.setMnemonic('R');
		menuBar.add(mnReservas);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Analisar Reservas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaliseReservaProf arp = new AnaliseReservaProf(professor, cnx);

				

				arp.setVisible(true);
			}
		});
		mnReservas.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Reservar Laboratorio");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaLaboratorio rl = new ReservaLaboratorio(cnx, professor);
			
				rl.setVisible(true);
			}
		});
		mnReservas.add(mntmNewMenuItem_2);
		
		JMenu mnHorarios = new JMenu("Horarios");
		mnHorarios.setMnemonic('h');
		mnHorarios.setIcon(new ImageIcon(PerfilProfessor.class.getResource("/br/ufac/laboratorio/gui/images/icon_clock.gif")));
		menuBar.add(mnHorarios);
		
		JMenuItem mntmVerificarHorarios = new JMenuItem("Verificar horarios");
		mntmVerificarHorarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaHorariosProf lhp = new ListaHorariosProf(cnx);
				
				lhp.setVisible(true);
			}
		});
		mnHorarios.add(mntmVerificarHorarios);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblProfessor = new JLabel("PROFESSOR");
		lblProfessor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSairProf = new JButton("SAIR");
		btnSairProf.setIcon(new ImageIcon(PerfilProfessor.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnSairProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSairProf){
					try {
						cnx.desconecte();
					} catch (DataBaseNotConnectedException | DataBaseGenericException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Desconectar", JOptionPane.ERROR_MESSAGE);
					}
					dispose();

					
				}

			}
		});

		Icon imagemUfac = new ImageIcon(getClass().getResource("../images/ufac.png"));
		JLabel lblImagem = new JLabel(imagemUfac);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PerfilProfessor.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
				//Alterar o nome do professor que esta na amostra
				JLabel lblNomeProfessor = new JLabel(professor.getNome());
				panel_1.add(lblNomeProfessor);
				lblNomeProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		
		JLabel label_1 = new JLabel((String) null);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		panel_1.add(label_1);





		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
							.addComponent(lblProfessor))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 492, Short.MAX_VALUE)
							.addComponent(btnSairProf))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(95)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProfessor)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSairProf)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
