package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Login;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.logic.LoginLogic;
import br.ufac.laboratorio.logic.ProfessorLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField jpfSenha;
	private LoginLogic loginLogic;
	private ProfessorLogic pl;
	static Conexao cnx;

	/**
	 * Launch the application.
	 */
	static final String DB_URL = "jdbc:mysql://localhost/laboratorio?useSSL=false";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
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
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("SISTEMA DE  GERENCIAMENTO DE LABORATORIO");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		cnx = new Conexao();
		try {
			cnx.conecte(DB_URL, "root", "1995");
		} catch (DataBaseAlreadyConnectedException | 
				AccessDeniedForUserException | 
				DataBaseGenericException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha na Conexao", JOptionPane.ERROR_MESSAGE);
		}	
		
		loginLogic = new LoginLogic(cnx);
		pl = new ProfessorLogic(cnx);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = null;
				Login login1 = null;
				
				try {
					login1 = new Login(tfLogin.getText() , jpfSenha.getText(), 0);
				} catch (NoSuchAlgorithmException | UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					login = loginLogic.getLogin(login1.getLogin());					
				} catch (DataBaseGenericException |
						EntityLoginNotExistException | DataBaseNotConnectedException | EntityNotExistException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Falha no Login", JOptionPane.ERROR_MESSAGE);
				}
				
				if(login1.getLogin().equals(login.getLogin()) && login1.getSenha().equals(login.getSenha())) {
						
					if(login.getTipo() == 1) {
						MenuAdministrador ma = new MenuAdministrador();
						dispose();
						ma.setVisible(true);						
					} else if (login.getTipo() == 2) {
						Professor professor = null;
						try {
							professor = pl.getProfessorIdSenha(login.getId());
						} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException
								| EntityLoginNotExistException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha no Login", JOptionPane.ERROR_MESSAGE);
						}
						
						PerfilProfessor pp = new PerfilProfessor(professor, cnx);
						dispose();
						pp.setVisible(true);
					} else if (login.getTipo() == 3) {
						PerfilAluno pa = new PerfilAluno();
						dispose();
						pa.setVisible(true);
					}
					
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Senha Errada", "Falha no Login" ,JOptionPane.ERROR_MESSAGE);
					jpfSenha.setText("");
				}
				
				
			}
		});
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnCadastrar){
					CadastroEscolha ce = new CadastroEscolha(cnx);
					
					dispose();
					
					ce.setVisible(true);
				}
				
				
			}
		});
		
		JLabel lblLogin = new JLabel("LOGIN");
		
		JLabel lblSenha = new JLabel("SENHA");
		
		tfLogin = new JTextField();
		tfLogin.setColumns(40);
		
		jpfSenha = new JPasswordField();
		jpfSenha.setColumns(40);
		
		JLabel lblUfac = new JLabel("UNIVERSIDADE FEDERAL DO ACRE");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(88)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSenha)
								.addComponent(lblLogin))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jpfSenha, 0, 0, Short.MAX_VALUE)
								.addComponent(tfLogin, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(168)
							.addComponent(btnEntrar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(156)
							.addComponent(btnCadastrar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(105)
							.addComponent(lblUfac)))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUfac)
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(tfLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(jpfSenha, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnEntrar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCadastrar)
					.addGap(17))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
