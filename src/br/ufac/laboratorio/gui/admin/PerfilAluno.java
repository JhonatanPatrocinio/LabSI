package br.ufac.laboratorio.gui.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Aluno;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.gui.aluno.EditarAluno;
import br.ufac.laboratorio.gui.aluno.ListaHorarios;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class PerfilAluno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PerfilAluno(Aluno al, Conexao cnx) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setIcon(new ImageIcon(PerfilAluno.class.getResource("/br/ufac/laboratorio/gui/images/icon_user.gif")));
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
		JMenu mnHorarios = new JMenu("Horarios");
		mnHorarios.setIcon(new ImageIcon(PerfilAluno.class.getResource("/br/ufac/laboratorio/gui/images/icon_clock.gif")));
		mnHorarios.setMnemonic('H');
		menuBar.add(mnHorarios);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Verificar Horários");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaHorarios lh = new ListaHorarios(cnx);
				//dispose();
				lh.setVisible(true);
			}
		});
		mnHorarios.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));

		JLabel lblAluno = new JLabel("ALUNO");
		lblAluno.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnSairAlu = new JButton("SAIR");
		btnSairAlu.setIcon(new ImageIcon(PerfilAluno.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnSairAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSairAlu){
					if(e.getSource()==btnSairAlu){
						try {
							cnx.desconecte();
						} catch (DataBaseNotConnectedException | DataBaseGenericException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha ao Desconectar", JOptionPane.ERROR_MESSAGE);
						}
						dispose();
					}
				}

			}
		});


		Icon imagemUfac = new ImageIcon(getClass().getResource("../images/ufac.png"));
		JLabel lblImagem = new JLabel(imagemUfac);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PerfilAluno.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(101)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
							.addComponent(lblAluno))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 368, Short.MAX_VALUE)
							.addComponent(btnSairAlu)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addComponent(lblImagem, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(lblAluno))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSairAlu)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

		JLabel lblNomeAluno = new JLabel(al.getNome());
		panel.add(lblNomeAluno);
		lblNomeAluno.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		contentPane.setLayout(gl_contentPane);
	}
}
