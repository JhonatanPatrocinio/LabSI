package br.ufac.laboratorio.gui.aluno;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

public class EditarAluno extends JDialog {

	private JPanel contentPane;
	private JTextField tdEditarAlu;
	private JTextField tfEditarNomeAlu;
	private JTextField tfEditarLoginAlu;
	private JPasswordField jpfEditarSenhaAlu;
	private JPasswordField jpfEditarConfSenhaAlu;
	private CursoLogic cl;
	private AlunoLogic aluL;

	/**
	 * Create the frame.
	 */
	public EditarAluno(Aluno al, Conexao cnx) {
		this.cl = new CursoLogic(cnx);
		this.aluL = new AlunoLogic(cnx);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
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
//		cbEditarCursoAlu.setModel(new DefaultComboBoxModel(new String[] {"ABI - Ciências Sociais", "Bacharelado em Ciências Biológicas", "Bacharelado em Ciências Econômicas", "Bacharelado em Ciências Sociais", "Bacharelado em Ciências Sociais", "Bacharelado em Ciências Sociais com Habilitação em Antropologia", "Bacharelado em Ciências Sociais com Habilitação em Ciências Políticas", "Bacharelado em Ciências Sociais com Habilitação em Sociologia", "Bacharelado em Comunicação Social com habilitação em Jornalismo", "Bacharelado em Comunicação Social com Habilitação em Jornalismo", "Bacharelado em Direito", "Bacharelado em Direito", "Bacharelado em Direito", "Bacharelado em Educação Física", "Bacharelado em Enfermagem", "Bacharelado em Enfermagem", "Bacharelado em Engenharia Agronômica", "Bacharelado em Engenharia Agronômica", "Bacharelado em Engenharia Civil", "Bacharelado em Engenharia Elétrica", "Bacharelado em Engenharia Florestal", "Bacharelado em Engenharia Florestal", "Bacharelado em Geografia", "Bacharelado em História", "Bacharelado em Jornalismo", "Bacharelado em Medicina Veterinária", "Bacharelado em Nutrição", "Bacharelado em Psicologia", "Bacharelado em Psicologia com Ênfase em Avaliação Psicológica", "Bacharelado em Psicologia com Ênfase em Psicologia Social e Políticas Públicas", "Bacharelado em Saúde Coletiva", "Bacharelado em Sistemas de Informação", "Especialização em Enfermagem Obstétrica", "Especialização em História e Cultura Afro-Brasileira e Africana", "Especialização em História e Cultura Afro-Brasileira e Africana", "Especialização UNIAFRO: Política de Promoção da Igualdade Racial na Escola", "Especialização UNIAFRO: Política de Promoção da Igualdade Racial na Escola", "Licenciatura em Artes Cênicas: Teatro", "Licenciatura em ciências sociais", "Licenciatura em Ciências Biológicas", "Licenciatura em Ciências Biológicas", "Licenciatura em Educação Física", "Licenciatura em Filosofia", "Licenciatura em Física", "Licenciatura em Geografia", "Licenciatura em História", "Licenciatura em História", "Licenciatura em Letras Espanhol", "Licenciatura em Letras Espanhol", "Licenciatura em Letras Francês", "Licenciatura em Letras Inglês", "Licenciatura em Letras Inglês", "Licenciatura em Letras Libras", "Licenciatura em Letras Português", "Licenciatura em Letras Português", "Licenciatura em Letras Português/Espanhol", "Licenciatura em Matemática", "Licenciatura em Matemática na Modalidade à Distância", "Licenciatura em Música", "Licenciatura em Pedagogia", "Licenciatura em Pedagogia", "Licenciatura em Química", "Licenciatura Indígena", "Medicina", "Pós-Graduação Lato Sensu em Atenção Primária a Saúde", "Pós-Graduação Lato Sensu em Ciências da Religião", "Pós-Graduação Lato Sensu em Comunicação e Política", "Pós-Graduação Lato Sensu em Coordenação Pedagógica", "Pós-Graduação Lato Sensu em Desenvolvimento de Software e Infraestrutura para Internet", "Pós-Graduação Lato Sensu em Desenvolvimento de Software e Infraestrutura para Internet", "Pós-Graduação Lato Sensu em Economia Regional e Políticas Públicas", "Pós-Graduação Lato Sensu em Gestão da Segurança Pública e Direitos Humanos", "Pós-Graduação Lato Sensu em Gestão Escolar", "Pós-Graduação Lato Sensu em Língua Portuguesa", "Pós-Graduação Lato Sensu em Ontologia, Conhecimento e Linguagem na História da Filosofia", "Pós-Graduação Lato Sensu em Saúde Pública", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Programa de Pós-graduação Stricto Sensu em nível de Mestrado em Desenvolvimento Regional.", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Produção Vegetal", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Biodiversidade e Biotecnologia - Rede Bionorte", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Sanidade e Produção Animal Sustentável na Amazônia Ocidental", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Saúde Coletiva", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ciência Florestal", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ciência, Inovação e Tecnologia para a Amazônia", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ciências da Saúde na Amazônia Ocidental", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ecologia e Manejo de Recursos Naturais", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Educação", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Letras: Linguagem e Identidade", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Produção Vegetal", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Sanidade e Produção Animal Sustentável na Amazônia Ocidental", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Saúde Coletiva", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Física", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Ciências e Matemática", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Ciências e Matemática", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Ciências e Matemática", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Letras", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Matemática", "Residência em Enfermagem Obstétrica", "Residência Multiprofissional com Ênfase em Terapia Intensiva", "Residência Multiprofissional Integrada em Saúde da Família e Comunidade"}));
		
		JLabel lblEditarAluno = new JLabel("EDITAR ALUNO");
		lblEditarAluno.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		
		JButton btnEditarAlu = new JButton("Salvar");
		btnEditarAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
//				String Vazia = "";
//				
//				if(e.getSource()==btnEditarAlu){
//					if(tfEditarLoginAlu.getText().compareTo(Vazia)== 0 || tfEditarNomeAlu.getText().compareTo(Vazia)== 0 || jpfEditarSenhaAlu.getText().compareTo(Vazia)== 0 || jpfEditarConfSenhaAlu.getText().compareTo(Vazia)== 0 ) {
//						JOptionPane.showMessageDialog(null, "Confira todas as informações! ");
//						
//					}else 
//						
//					if(jpfEditarSenhaAlu.getText().compareTo(jpfEditarConfSenhaAlu.getText()) !=0) {
//						JOptionPane.showMessageDialog(null, "Confira a senha ");
//					}
//					
//				else {
//						JOptionPane.showMessageDialog(null, "Edição realizada com sucesso! ");
//						//PerfilAluno pa = new PerfilAluno();
//
//						dispose();
//
//						//pa.setVisible(true);
//					}
//				}
//				
//			}
//		});
				if(e.getSource()==btnEditarAlu){
					if(jpfEditarSenhaAlu.getText().equals(jpfEditarConfSenhaAlu.getText())) {
												
						Aluno alu = null;
						try {
							aluL.updAluno(al.getId(),al.getMatricula(), tfEditarNomeAlu.getText(),
									al.getLogin().getLogin(), 
									jpfEditarSenhaAlu.getText(), 3);
							
							
							try {
								alu = aluL.getAluno(al.getId());
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
//		jpfEditarSenhaAlu.setColumns(20);
		
		jpfEditarConfSenhaAlu = new JPasswordField();
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//PerfilAluno pa = new PerfilAluno();

					dispose();

					//pa.setVisible(true);
				}
				
			}
		});
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
							.addGap(121)
							.addComponent(btnEditarAlu)))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
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
						.addComponent(jpfEditarSenhaAlu, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmarSenha)
						.addComponent(jpfEditarConfSenhaAlu, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnEditarAlu)
							.addGap(20))))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
