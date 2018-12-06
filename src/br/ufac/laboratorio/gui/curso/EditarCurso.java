package br.ufac.laboratorio.gui.curso;

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
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class EditarCurso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodigoCursoEdit;
	private JTextField tfNomeCursoEdit;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			EditarCurso dialog = new EditarCurso();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public EditarCurso(Conexao cnx) {
		setBounds(100, 100, 450, 270);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(EditarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Save16.gif")));
		btnSalvar.setEnabled(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(EditarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		
		JLabel lblCodigo = new JLabel("CODIGO");
		
		JLabel lblNome = new JLabel("NOME");
		
		tfCodigoCursoEdit = new JTextField();
		tfCodigoCursoEdit.setColumns(10);
		
		tfNomeCursoEdit = new JTextField();
		tfNomeCursoEdit.setColumns(100);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblCadastrarCurso = new JLabel("CADASTRAR CURSO");
		lblCadastrarCurso.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EditarCurso.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
					.addComponent(lblAdministrador))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(116)
					.addComponent(lblCadastrarCurso)
					.addContainerGap(129, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(69)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(lblCodigo))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfNomeCursoEdit, 0, 0, Short.MAX_VALUE)
						.addComponent(tfCodigoCursoEdit, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(107, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(btnVoltar)
					.addGap(115)
					.addComponent(btnSalvar)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdministrador)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblCadastrarCurso)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCodigo)
						.addComponent(tfCodigoCursoEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(tfNomeCursoEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvar))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
