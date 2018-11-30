package br.ufac.laboratorio.gui.aluno;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;

public class ListarAluno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListarAluno dialog = new ListarAluno();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ListarAluno(Conexao cnx) {
		setBounds(100, 100, 578, 451);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		JLabel lblAlunos = new JLabel("ALUNOS");
		lblAlunos.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JScrollPane spAlunos = new JScrollPane();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListarAluno.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
					.addComponent(lblAlunos)
					.addGap(131)
					.addComponent(lblAdministrador))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(12)
					.addComponent(spAlunos, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
					.addGap(12))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdministrador)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAlunos))))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(spAlunos, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		spAlunos.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.setIcon(new ImageIcon(ListarAluno.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dispose();
					
				}
			});
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnVoltar)
						.addContainerGap(504, Short.MAX_VALUE))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVoltar))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
