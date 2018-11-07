package br.ufac.laboratorio.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.CentroLogic;
import br.ufac.laboratorio.logic.LoginLogic;
import br.ufac.laboratorio.logic.ProfessorLogic;

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
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CadastroProfessor extends JFrame {

	private JPanel contentPane;
	private JTextField tfMatriculaProf;
	private JTextField tfNomeProf;
	private JTextField tfLoginProf;
	private JPasswordField jpfSenhaProf;
	private JPasswordField jpfConfSenhaProf;
	private ProfessorLogic pl;
	private LoginLogic loginLogic;
	private CentroLogic cl;
	private JTextField tfEmailProf;
	private JTextField tfTelefoneProf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProfessor frame = new CadastroProfessor(TelaInicial.cnx);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroProfessor(Conexao cnx) {
		this.pl = new ProfessorLogic(cnx);
		this.loginLogic = new LoginLogic(cnx);
		this.cl = new CentroLogic(cnx);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblMatricula = new JLabel("MATRICULA");

		JLabel lblNome = new JLabel("NOME");

		JLabel lblLogin = new JLabel("LOGIN");

		JLabel lblCentro = new JLabel("CENTRO");

		JLabel lblSenha = new JLabel("SENHA");

		JLabel lblConfirmarSenha = new JLabel("CONFIRMAR SENHA");

		tfMatriculaProf = new JTextField();
		tfMatriculaProf.setColumns(12);

		tfNomeProf = new JTextField();
		tfNomeProf.setColumns(50);

		tfLoginProf = new JTextField();
		tfLoginProf.setColumns(20);
		//Pega os Dados do Centro e Insere no ComboBox
		JComboBox cbCentroProf = new JComboBox();
		List<Centro> centros = new ArrayList<>();
		DefaultComboBoxModel<?> modelo= (DefaultComboBoxModel) cbCentroProf.getModel();
		try {
			centros = cl.getCentros();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2.getMessage(), 
					"Falha no Centro", JOptionPane.ERROR_MESSAGE);
		}
		String str = null;
		for(int i = 0; i < centros.size(); i++) {
			str = (String) centros.get(i).getSigla()+" -"+ " "+ centros.get(i).getNome();
			cbCentroProf.addItem(str);
		}
		cbCentroProf.setSelectedIndex(-1);

		JLabel lblCadastroProfessor = new JLabel("CADASTRO PROFESSOR");
		lblCadastroProfessor.setFont(new Font("Lucida Grande", Font.BOLD, 30));

		JButton btnCadastrarProf = new JButton("Cadastrar");
		btnCadastrarProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnCadastrarProf){
					if(jpfSenhaProf.getText().equals(jpfConfSenhaProf.getText())) {
						String s = cbCentroProf.getSelectedItem().toString();
						String [] s2 = s.split(" ");
												

						try {
							pl.addProfessor(tfMatriculaProf.getText(), tfNomeProf.getText(), tfEmailProf.getText(), 
									tfTelefoneProf.getText(), s2[0], tfLoginProf.getText(), 
									jpfSenhaProf.getText(), 2);
							JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
							cnx.desconecte();
							TelaInicial ti = new TelaInicial();

							dispose();

							ti.setVisible(true);
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DataBaseGenericException
								| DataBaseNotConnectedException | EntityAlreadyExistException | InvalidFieldException
								| EntityNotExistException | EntityLoginNotExistException
								| EntityLoginAlreadyExistException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
							jpfConfSenhaProf.setText("");
							jpfSenhaProf.setText("");
							tfLoginProf.setText("");
							
						}

					} else {
						JOptionPane.showMessageDialog(null, "Senhas Diferentes", 
								"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
					}





				}
			}
		});

		jpfSenhaProf = new JPasswordField();
		jpfSenhaProf.setColumns(20);

		jpfConfSenhaProf = new JPasswordField();
		jpfConfSenhaProf.setColumns(20);

		JLabel lblEmail = new JLabel("EMAIL");

		tfEmailProf = new JTextField();
		tfEmailProf.setColumns(10);

		JLabel lblTelefone = new JLabel("TELEFONE");

		tfTelefoneProf = new JTextField();
		tfTelefoneProf.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(29, Short.MAX_VALUE)
						.addComponent(lblCadastroProfessor)
						.addGap(61))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(49)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEmail)
								.addComponent(lblMatricula)
								.addComponent(lblConfirmarSenha)
								.addComponent(lblSenha)
								.addComponent(lblLogin)
								.addComponent(lblNome)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTelefone)
										.addComponent(lblCentro)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(tfTelefoneProf, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(tfNomeProf, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(tfEmailProf, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(cbCentroProf, 0, 207, Short.MAX_VALUE)
								.addComponent(tfLoginProf, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(btnCadastrarProf)
								.addComponent(tfMatriculaProf, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(jpfSenhaProf, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(jpfConfSenhaProf, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
						.addGap(85))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(28)
						.addComponent(lblCadastroProfessor)
						.addGap(59)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMatricula)
								.addComponent(tfMatriculaProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCentro)
								.addComponent(cbCentroProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(tfEmailProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addComponent(tfNomeProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(tfTelefoneProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfLoginProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLogin))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jpfSenhaProf, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSenha))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(jpfConfSenhaProf, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
										.addComponent(btnCadastrarProf)
										.addGap(20))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblConfirmarSenha)
										.addContainerGap())))
				);
		contentPane.setLayout(gl_contentPane);
	}
}
