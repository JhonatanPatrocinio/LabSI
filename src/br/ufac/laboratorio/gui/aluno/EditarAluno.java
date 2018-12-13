package br.ufac.laboratorio.gui.aluno;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Aluno;
import br.ufac.laboratorio.entity.Curso;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.AlunoLogic;
import br.ufac.laboratorio.logic.CursoLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class EditarAluno extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tdEditarAlu;
	private JTextField tfEditarNomeAlu;
	private JTextField tfEditarLoginAlu;
	private JPasswordField jpfEditarSenhaAlu;
	private JPasswordField jpfEditarConfSenhaAlu;
	private CursoLogic cl;
	private AlunoLogic aluL;

	@SuppressWarnings({"unused", "rawtypes", "unchecked"})
	public EditarAluno(Aluno al, Conexao cnx) {
		this.cl = new CursoLogic(cnx);
		this.aluL = new AlunoLogic(cnx);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		JLabel lblMatricula = new JLabel("MATRICULA");
		JLabel lblNome = new JLabel("NOME");
		JLabel lblLogin = new JLabel("LOGIN");
		JLabel lblCurso = new JLabel("CURSO");
		JLabel lblSenha = new JLabel("SENHA");
		JLabel lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");
		tdEditarAlu = new JTextField();
		tdEditarAlu.setEditable(false);
		tdEditarAlu.setEnabled(false);
		tdEditarAlu.setColumns(12);
		tdEditarAlu.setText(al.getMatricula());
		
		tfEditarNomeAlu = new JTextField();
		tfEditarNomeAlu.setColumns(50);
		tfEditarNomeAlu.setText(al.getNome());
		
		tfEditarLoginAlu = new JTextField();
		tfEditarLoginAlu.setEnabled(false);
		tfEditarLoginAlu.setEditable(false);
		tfEditarLoginAlu.setColumns(20);
		tfEditarLoginAlu.setText(al.getLogin().getLogin());
		
		JComboBox cbEditarCursoAlu = new JComboBox();
		Curso curso = null;
		try {
			curso = cl.getCursoId(al.getCurso().getId());
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), 
					"Falha no Curso", JOptionPane.ERROR_MESSAGE);
		}
		cbEditarCursoAlu.setEnabled(false);
		DefaultComboBoxModel<?> modelo= (DefaultComboBoxModel) cbEditarCursoAlu.getModel();
		String str = Integer.toString(curso.getCod())+" -"+ " "+ curso.getNome();
		cbEditarCursoAlu.addItem(str);		
		JLabel lblEditarAluno = new JLabel("EDITAR ALUNO");
		lblEditarAluno.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		
		JButton btnEditarAlu = new JButton("Salvar");
		btnEditarAlu.setIcon(new ImageIcon(EditarAluno.class.getResource("/br/ufac/laboratorio/gui/images/Save16.gif")));
		btnEditarAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnEditarAlu){
					if(new String (jpfEditarSenhaAlu.getPassword()).equals(new String (jpfEditarConfSenhaAlu.getPassword()))) {					
						Aluno alu = null;
						try {
							aluL.updAluno(al.getId(),al.getMatricula(), tfEditarNomeAlu.getText(),
									al.getLogin().getLogin(), 
									new String (jpfEditarSenhaAlu.getPassword()), 3);
							try {
								alu = aluL.getAlunoId(al.getId());
								PerfilAluno pa = new PerfilAluno(alu, cnx);
								dispose();
								pa.setVisible(true);
							} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException
									| EntityLoginNotExistException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), 
										"Falha ao Editar2", JOptionPane.ERROR_MESSAGE);
							}
							JOptionPane.showMessageDialog(null, " Editado! ");
							
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DataBaseGenericException
								| DataBaseNotConnectedException | InvalidFieldException
								| EntityNotExistException | EntityLoginNotExistException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha ao Editar", JOptionPane.ERROR_MESSAGE);
							jpfEditarConfSenhaAlu.setText("");
							jpfEditarSenhaAlu.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Senhas Diferentes", 
								"Falha ao Editar", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		jpfEditarSenhaAlu = new JPasswordField();		
		jpfEditarConfSenhaAlu = new JPasswordField();
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(EditarAluno.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnVoltar) dispose();			
			}
		});
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EditarAluno.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(jpfEditarConfSenhaAlu, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
								.addComponent(jpfEditarSenhaAlu, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(tdEditarAlu)
									.addComponent(tfEditarNomeAlu, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
									.addComponent(tfEditarLoginAlu, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
									.addComponent(cbEditarCursoAlu, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(123)
							.addComponent(lblEditarAluno))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVoltar)
							.addGap(132)
							.addComponent(btnEditarAlu))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addComponent(lblEditarAluno)
							.addGap(97)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(tdEditarAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMatricula))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurso)
								.addComponent(cbEditarCursoAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(tfEditarNomeAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLogin)
								.addComponent(tfEditarLoginAlu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSenha)
								.addComponent(jpfEditarSenhaAlu, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(jpfEditarConfSenhaAlu, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditarAlu)
							.addGap(19))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
