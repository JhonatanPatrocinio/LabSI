package br.ufac.laboratorio.gui.aluno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Curso;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.gui.TelaInicial;
import br.ufac.laboratorio.logic.AlunoLogic;
import br.ufac.laboratorio.logic.CursoLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CadastroAluno extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMatriculaAlu;
	private JTextField tfNomeAlu;
	private JTextField tfLoginAlu;
	private JPasswordField jpfSenhaAlu;
	private JPasswordField jpfConfSenhaAlu;
	
	private CursoLogic cl;
	private AlunoLogic al;

	
	/**
	 * Create the frame.
	 */
	public CadastroAluno(Conexao cnx) {
		this.cl = new CursoLogic(cnx);
		this.al = new AlunoLogic(cnx);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblMatricula = new JLabel("MATRICULA");

		JLabel lblNome = new JLabel("NOME");

		JLabel lblLogin = new JLabel("LOGIN");

		JLabel lblCurso = new JLabel("CURSO");

		JLabel lblSenha = new JLabel("SENHA");

		JLabel lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");

		tfMatriculaAlu = new JTextField();
		tfMatriculaAlu.setColumns(12);

		tfNomeAlu = new JTextField();
		tfNomeAlu.setColumns(50);

		tfLoginAlu = new JTextField();
		tfLoginAlu.setColumns(20);

		JComboBox cbCursoAlu = new JComboBox();
		
		List<Curso> cursos = new ArrayList<>();
		DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) cbCursoAlu.getModel();
		try {
			cursos = cl.getCursos();
		}catch(DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),
					"Falha no Curso", JOptionPane.ERROR_MESSAGE);
		}
		String str = null;
		for(int i = 0; i < cursos.size(); i++) {
			str = (String) Integer.toString(cursos.get(i).getCod())+" -"+ " "+ cursos.get(i).getNome();
			cbCursoAlu.addItem(str);
		}
		cbCursoAlu.setSelectedIndex(-1);
		
		JLabel lblCadastroAluno = new JLabel("CADASTRO ALUNO");
		lblCadastroAluno.setFont(new Font("Lucida Grande", Font.BOLD, 30));

		JButton btnCadastrarAlu = new JButton("Cadastrar");
		btnCadastrarAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnCadastrarAlu){	
					if(jpfSenhaAlu.getText().equals(jpfConfSenhaAlu.getText())) {
						String s = cbCursoAlu.getSelectedItem().toString();
						String [] s2 = s.split(" ");
						
						
						try {
							al.addAluno(tfMatriculaAlu.getText(), tfNomeAlu.getText(), Integer.parseInt(s2[0]), tfLoginAlu.getText(), 
								jpfSenhaAlu.getText(), 3);
							JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
							cnx.desconecte();
							TelaInicial ti = new TelaInicial();

							dispose();

							ti.setVisible(true);
						}catch(NoSuchAlgorithmException | UnsupportedEncodingException | DataBaseGenericException
								| DataBaseNotConnectedException | EntityAlreadyExistException | InvalidFieldException
								| EntityNotExistException | EntityLoginNotExistException
								| EntityLoginAlreadyExistException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha no Cadastro Aluno", JOptionPane.ERROR_MESSAGE);
							jpfConfSenhaAlu.setText("");
							jpfSenhaAlu.setText("");
							tfLoginAlu.setText("");
						
						}
				
					} else {
						JOptionPane.showMessageDialog(null, "Senhas Diferentes",
								"Falha no Cadastro Aluno", JOptionPane.ERROR_MESSAGE);
					
					}
				
				
			
				}


			}
		});
		
		jpfSenhaAlu = new JPasswordField();
		jpfSenhaAlu.setColumns(20);
		
		jpfConfSenhaAlu = new JPasswordField();
		jpfConfSenhaAlu.setColumns(20);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(107, Short.MAX_VALUE)
					.addComponent(lblCadastroAluno)
					.addGap(91))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblConfirmarSenha)
						.addComponent(lblMatricula)
						.addComponent(lblCurso)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNome)
							.addGap(2))
						.addComponent(lblLogin)
						.addComponent(lblSenha))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnCadastrarAlu)
						.addComponent(tfMatriculaAlu)
						.addComponent(tfNomeAlu, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(tfLoginAlu, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(cbCursoAlu, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(jpfSenhaAlu, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addComponent(jpfConfSenhaAlu, 0, 0, Short.MAX_VALUE))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addComponent(lblCadastroAluno)
					.addGap(98)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tfMatriculaAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMatricula))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurso)
						.addComponent(cbCursoAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tfNomeAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(tfLoginAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(jpfSenhaAlu, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(jpfConfSenhaAlu, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(btnCadastrarAlu)
					.addGap(20))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
