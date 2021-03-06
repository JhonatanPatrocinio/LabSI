package br.ufac.laboratorio.gui.professor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.gui.TelaInicial;
import br.ufac.laboratorio.logic.*;


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
import javax.swing.ImageIcon;

public class CadastroProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfMatriculaProf;
	private JTextField tfNomeProf;
	private JTextField tfLoginProf;
	private JPasswordField jpfSenhaProf;
	private JPasswordField jpfConfSenhaProf;
	private ProfessorLogic pl;
	private CentroLogic cl;
	private JTextField tfEmailProf;
	private JTextField tfTelefoneProf;

	@SuppressWarnings({"unused", "rawtypes", "unchecked"})
	public CadastroProfessor(Conexao cnx) {
		this.pl = new ProfessorLogic(cnx);
		this.cl = new CentroLogic(cnx);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
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
		btnCadastrarProf.setIcon(new ImageIcon(CadastroProfessor.class.getResource("/br/ufac/laboratorio/gui/images/ComposeMail16.gif")));
		btnCadastrarProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnCadastrarProf){
					if(new String (jpfSenhaProf.getPassword()).equals(new String (jpfConfSenhaProf.getPassword()))) {
						String s = cbCentroProf.getSelectedItem().toString();
						String [] s2 = s.split(" ");
												

						try {
							pl.addProfessor(tfMatriculaProf.getText(), tfNomeProf.getText(), tfEmailProf.getText(), 
									tfTelefoneProf.getText(), s2[0], tfLoginProf.getText(), 
									new String (jpfSenhaProf.getPassword()), 2);
							JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
							cnx.desconecte();
							TelaInicial ti = new TelaInicial();

							dispose();

							ti.setVisible(true);
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException | DataBaseGenericException
								| DataBaseNotConnectedException | EntityAlreadyExistException | InvalidFieldException
								| EntityNotExistException | EntityLoginNotExistException
								| EntityLoginAlreadyExistException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha no Cadastro Professor", JOptionPane.ERROR_MESSAGE);
							jpfConfSenhaProf.setText("");
							jpfSenhaProf.setText("");
							tfLoginProf.setText("");
							
						}

					} else {
						JOptionPane.showMessageDialog(null, "Senhas Diferentes", 
								"Falha no Cadastro Professor", JOptionPane.ERROR_MESSAGE);
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadastroProfessor.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSenha)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(49)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblEmail)
											.addComponent(lblNome)
											.addComponent(lblCentro)
											.addComponent(lblMatricula)
											.addComponent(lblLogin)
											.addComponent(lblTelefone)))
									.addComponent(lblConfirmarSenha)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(jpfConfSenhaProf, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
								.addComponent(jpfSenhaProf, 0, 0, Short.MAX_VALUE)
								.addComponent(tfLoginProf, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
								.addComponent(tfTelefoneProf, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
								.addComponent(tfNomeProf, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
								.addComponent(tfEmailProf, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
								.addComponent(cbCentroProf, 0, 288, Short.MAX_VALUE)
								.addComponent(tfMatriculaProf, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCadastroProfessor)))
					.addGap(61))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(198)
					.addComponent(btnCadastrarProf)
					.addContainerGap(203, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblCadastroProfessor)
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMatricula)
								.addComponent(tfMatriculaProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbCentroProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCentro))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfEmailProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfNomeProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(tfTelefoneProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(lblLogin))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tfLoginProf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jpfSenhaProf, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSenha)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(jpfConfSenhaProf, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addComponent(btnCadastrarProf)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
