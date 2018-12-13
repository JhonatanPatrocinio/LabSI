package br.ufac.laboratorio.gui.curso;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.CursoLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastroCurso extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfCodigoCurso;
	private JTextField tfNomeCurso;
	private CursoLogic cl;

	public CadastroCurso(Conexao cnx) {
		cl= new CursoLogic(cnx);
		setBounds(100, 100, 450, 310);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(CadastroCurso.class.getResource("/br/ufac/laboratorio/gui/images/list_users.gif")));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnCadastrar) {
					try {
						cl.addCurso(Integer.parseInt(tfCodigoCurso.getText()), tfNomeCurso.getText());
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
						dispose();
					}catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityAlreadyExistException | InvalidFieldException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
						tfCodigoCurso.setText("");
						tfNomeCurso.setText("");
					}
				}
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(CadastroCurso.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){
				
				dispose();
				
			}
			}
		});
		
		JLabel lblCodigo = new JLabel("CODIGO");
		
		JLabel lblNome = new JLabel("NOME");
		
		tfCodigoCurso = new JTextField();
		tfCodigoCurso.setColumns(10);
		
		tfNomeCurso = new JTextField();
		tfNomeCurso.setColumns(100);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblCadastrarCurso = new JLabel("CADASTRAR CURSO");
		lblCadastrarCurso.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadastroCurso.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
					.addComponent(lblAdministrador))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(108)
					.addComponent(lblCadastrarCurso)
					.addContainerGap(137, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(lblCodigo))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfNomeCurso, 0, 0, Short.MAX_VALUE)
						.addComponent(tfCodigoCurso, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addGap(88)
					.addComponent(btnCadastrar)
					.addContainerGap(189, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblAdministrador)
							.addGap(43)
							.addComponent(lblCadastrarCurso)
							.addGap(38)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCodigo)
								.addComponent(tfCodigoCurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome)
								.addComponent(tfNomeCurso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addGap(40)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnCadastrar))
					.addGap(70))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
