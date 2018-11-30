package br.ufac.laboratorio.gui.curso;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ListarCurso extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListarCurso dialog = new ListarCurso();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ListarCurso(Conexao cnx) {
		
		
		setBounds(100, 100, 494, 429);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 281, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 355, Short.MAX_VALUE)
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel panel = new JPanel();
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(ListarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Edit16.gif")));
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(ListarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
					.addComponent(btnEditar)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnVoltar)))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblCursos = new JLabel("CURSOS");
		lblCursos.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListarCurso.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
							.addComponent(lblCursos)
							.addGap(106)
							.addComponent(lblAdministrador))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblAdministrador))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblCursos)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		getContentPane().setLayout(groupLayout);
	}
}
