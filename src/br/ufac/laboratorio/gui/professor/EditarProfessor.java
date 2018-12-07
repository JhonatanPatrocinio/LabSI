package br.ufac.laboratorio.gui.professor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.CentroLogic;
import br.ufac.laboratorio.logic.ProfessorLogic;

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

public class EditarProfessor extends JDialog {

	private JPanel contentPane;
	private JTextField tfEditarMatriculaProf;
	private JTextField tfEditarNomeProf;
	private JTextField tfEditarLoginProf;
	private JPasswordField jpfEditarSenhaProf;
	private JPasswordField jpfEditarConfSenhaProf;
	private CentroLogic cl;
	private JTextField tfEmail;
	private JTextField tfTelefone;
	private ProfessorLogic pl;

	/**
	 * Create the frame.
	 */
	public EditarProfessor(Professor professor, Conexao cnx) {
		this.cl = new CentroLogic(cnx);
		this.pl = new ProfessorLogic(cnx);
		setBounds(100, 100, 500, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);

		JLabel lblMatricula = new JLabel("MATRICULA");

		JLabel lblNome = new JLabel("NOME");

		JLabel lblLogin = new JLabel("LOGIN");

		JLabel lblCentro = new JLabel("CENTRO");

		JLabel lblSenha = new JLabel("SENHA");

		JLabel lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");

		tfEditarMatriculaProf = new JTextField();
		tfEditarMatriculaProf.setEnabled(false);
		tfEditarMatriculaProf.setEditable(false);
		tfEditarMatriculaProf.setColumns(12);
		tfEditarMatriculaProf.setText(professor.getMatricula());

		tfEditarNomeProf = new JTextField();
		tfEditarNomeProf.setColumns(50);
		tfEditarNomeProf.setText(professor.getNome());

		tfEditarLoginProf = new JTextField();
		tfEditarLoginProf.setEnabled(false);
		tfEditarLoginProf.setEditable(false);
		tfEditarLoginProf.setColumns(20);
		tfEditarLoginProf.setText(professor.getLogin().getLogin());

		JComboBox cbEditarCentroProf = new JComboBox();
		Centro centro = null;
		try {
			centro = cl.getCentroId(professor.getCentro().getId());
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), 
					"Falha no Centro", JOptionPane.ERROR_MESSAGE);
		}
		cbEditarCentroProf.setEnabled(false);
		DefaultComboBoxModel<?> modelo= (DefaultComboBoxModel) cbEditarCentroProf.getModel();
		String str = (String) centro.getSigla()+" -"+ " "+ centro.getNome();
		cbEditarCentroProf.addItem(str);
		JLabel lblEditarProfessor = new JLabel("EDITAR PROFESSOR");
		lblEditarProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 30));

		JButton btnEditarProf = new JButton("Salvar");
		btnEditarProf.setIcon(new ImageIcon(EditarProfessor.class.getResource("/br/ufac/laboratorio/gui/images/Save16.gif")));
		btnEditarProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnEditarProf){
					if(jpfEditarSenhaProf.getText().equals(jpfEditarConfSenhaProf.getText())) {								
						Professor prof = null;
						try {
							pl.updProfessor(professor.getId(),professor.getMatricula(), tfEditarNomeProf.getText(), tfEmail.getText(), 
									tfTelefone.getText(), professor.getLogin().getLogin(), 
									jpfEditarSenhaProf.getText(), 2);					
							try {
								prof = pl.getProfessorId(professor.getId());
								PerfilProfessor pp = new PerfilProfessor(prof, cnx);
								dispose();
								pp.setVisible(true);
							} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException
									| EntityLoginNotExistException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), 
										"Falha ao Buscar Professor", JOptionPane.ERROR_MESSAGE);
							}
							JOptionPane.showMessageDialog(null, " Editado! ");
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DataBaseGenericException
								| DataBaseNotConnectedException | InvalidFieldException
								| EntityNotExistException | EntityLoginNotExistException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha ao Editar", JOptionPane.ERROR_MESSAGE);
							jpfEditarConfSenhaProf.setText("");
							jpfEditarSenhaProf.setText("");
							
							
						}
					} else {
						JOptionPane.showMessageDialog(null, "Senhas Diferentes", 
								"Falha ao Editar", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
				

		jpfEditarSenhaProf = new JPasswordField();

		jpfEditarConfSenhaProf = new JPasswordField();

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(EditarProfessor.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnVoltar){

					//PerfilProfessor pp = new PerfilProfessor(professor, cnx);

					dispose();

					//pp.setVisible(true);
				}

			}
		});
		
		JLabel lblEmail = new JLabel("EMAIL");
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setText(professor.getEmail());
		
		JLabel lblNewLabel = new JLabel("TELEFONE");
		
		tfTelefone = new JTextField();
		tfTelefone.setColumns(10);
		tfTelefone.setText(professor.getTelefone());
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EditarProfessor.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVoltar)
					.addGap(147)
					.addComponent(btnEditarProf)
					.addGap(207))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCentro)
						.addComponent(lblMatricula)
						.addComponent(lblNome)
						.addComponent(lblEmail)
						.addComponent(lblNewLabel)
						.addComponent(lblConfirmarSenha)
						.addComponent(lblSenha)
						.addComponent(lblLogin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tfEditarLoginProf, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(tfTelefone, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(tfEditarNomeProf, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(tfEditarMatriculaProf, 218, 218, Short.MAX_VALUE)
						.addComponent(cbEditarCentroProf, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jpfEditarSenhaProf, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
						.addComponent(jpfEditarConfSenhaProf, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
					.addGap(79))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(97, Short.MAX_VALUE)
					.addComponent(lblEditarProfessor)
					.addGap(88))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(lblEditarProfessor)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMatricula)
						.addComponent(tfEditarMatriculaProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbEditarCentroProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCentro))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tfEditarNomeProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(tfTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfEditarLoginProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(jpfEditarSenhaProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(jpfEditarConfSenhaProf, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVoltar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditarProf)
							.addGap(20))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
