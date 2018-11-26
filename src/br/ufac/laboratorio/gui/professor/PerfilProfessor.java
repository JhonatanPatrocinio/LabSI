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

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPerfil = new JMenu("Perfil");
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
		mnReservas.setMnemonic('R');
		menuBar.add(mnReservas);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Analisar Reservas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaHorariosProf lhp = new ListaHorariosProf();

				//dispose();

				lhp.setVisible(true);
			}
		});
		mnReservas.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Reservar Laboratorio");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReservaLaboratorio rl = new ReservaLaboratorio(cnx, professor);
				//dispose();
				rl.setVisible(true);
			}
		});
		mnReservas.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(null);

		JLabel lblProfessor = new JLabel("PROFESSOR");
		lblProfessor.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSairProf = new JButton("SAIR");
		btnSairProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSairProf){
					try {
						cnx.desconecte();
					} catch (DataBaseNotConnectedException | DataBaseGenericException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Editar", JOptionPane.ERROR_MESSAGE);
					}
					dispose();

					
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
							.addComponent(lblProfessor))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(399, Short.MAX_VALUE)
							.addComponent(btnSairProf)))
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
						.addComponent(lblProfessor))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnSairProf)
					.addContainerGap())
		);

		//Alterar o nome do professor que esta na amostra
		JLabel lblNomeProfessor = new JLabel(professor.getNome());
		panel.add(lblNomeProfessor);
		lblNomeProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
