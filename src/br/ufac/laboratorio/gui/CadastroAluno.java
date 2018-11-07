package br.ufac.laboratorio.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CadastroAluno extends JFrame {

	private JPanel contentPane;
	private JTextField tfMatriculaAlu;
	private JTextField tfNomeAlu;
	private JTextField tfLoginAlu;
	private JPasswordField jpfSenhaAlu;
	private JPasswordField jpfConfSenhaAlu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAluno frame = new CadastroAluno();
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
	public CadastroAluno() {
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
		cbCursoAlu.setModel(new DefaultComboBoxModel(new String[] {"ABI - Ciências Sociais", "Bacharelado em Ciências Biológicas", "Bacharelado em Ciências Econômicas", "Bacharelado em Ciências Sociais", "Bacharelado em Ciências Sociais", "Bacharelado em Ciências Sociais com Habilitação em Antropologia", "Bacharelado em Ciências Sociais com Habilitação em Ciências Políticas", "Bacharelado em Ciências Sociais com Habilitação em Sociologia", "Bacharelado em Comunicação Social com habilitação em Jornalismo", "Bacharelado em Comunicação Social com Habilitação em Jornalismo", "Bacharelado em Direito", "Bacharelado em Direito", "Bacharelado em Direito", "Bacharelado em Educação Física", "Bacharelado em Enfermagem", "Bacharelado em Enfermagem", "Bacharelado em Engenharia Agronômica", "Bacharelado em Engenharia Agronômica", "Bacharelado em Engenharia Civil", "Bacharelado em Engenharia Elétrica", "Bacharelado em Engenharia Florestal", "Bacharelado em Engenharia Florestal", "Bacharelado em Geografia", "Bacharelado em História", "Bacharelado em Jornalismo", "Bacharelado em Medicina Veterinária", "Bacharelado em Nutrição", "Bacharelado em Psicologia", "Bacharelado em Psicologia com Ênfase em Avaliação Psicológica", "Bacharelado em Psicologia com Ênfase em Psicologia Social e Políticas Públicas", "Bacharelado em Saúde Coletiva", "Bacharelado em Sistemas de Informação", "Especialização em Enfermagem Obstétrica", "Especialização em História e Cultura Afro-Brasileira e Africana", "Especialização em História e Cultura Afro-Brasileira e Africana", "Especialização UNIAFRO: Política de Promoção da Igualdade Racial na Escola", "Especialização UNIAFRO: Política de Promoção da Igualdade Racial na Escola", "Licenciatura em Artes Cênicas: Teatro", "Licenciatura em ciências sociais", "Licenciatura em Ciências Biológicas", "Licenciatura em Ciências Biológicas", "Licenciatura em Educação Física", "Licenciatura em Filosofia", "Licenciatura em Física", "Licenciatura em Geografia", "Licenciatura em História", "Licenciatura em História", "Licenciatura em Letras Espanhol", "Licenciatura em Letras Espanhol", "Licenciatura em Letras Francês", "Licenciatura em Letras Inglês", "Licenciatura em Letras Inglês", "Licenciatura em Letras Libras", "Licenciatura em Letras Português", "Licenciatura em Letras Português", "Licenciatura em Letras Português/Espanhol", "Licenciatura em Matemática", "Licenciatura em Matemática na Modalidade à Distância", "Licenciatura em Música", "Licenciatura em Pedagogia", "Licenciatura em Pedagogia", "Licenciatura em Química", "Licenciatura Indígena", "Medicina", "Pós-Graduação Lato Sensu em Atenção Primária a Saúde", "Pós-Graduação Lato Sensu em Ciências da Religião", "Pós-Graduação Lato Sensu em Comunicação e Política", "Pós-Graduação Lato Sensu em Coordenação Pedagógica", "Pós-Graduação Lato Sensu em Desenvolvimento de Software e Infraestrutura para Internet", "Pós-Graduação Lato Sensu em Desenvolvimento de Software e Infraestrutura para Internet", "Pós-Graduação Lato Sensu em Economia Regional e Políticas Públicas", "Pós-Graduação Lato Sensu em Gestão da Segurança Pública e Direitos Humanos", "Pós-Graduação Lato Sensu em Gestão Escolar", "Pós-Graduação Lato Sensu em Língua Portuguesa", "Pós-Graduação Lato Sensu em Ontologia, Conhecimento e Linguagem na História da Filosofia", "Pós-Graduação Lato Sensu em Saúde Pública", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Pós-Graduação Lato Sensu em Tecnologias da Informação e Comunicação", "Programa de Pós-graduação Stricto Sensu em nível de Mestrado em Desenvolvimento Regional.", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Produção Vegetal", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Biodiversidade e Biotecnologia - Rede Bionorte", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Sanidade e Produção Animal Sustentável na Amazônia Ocidental", "Programa de Pós-Graduação Stricto Sensu em nível de Doutorado em Saúde Coletiva", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ciência Florestal", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ciência, Inovação e Tecnologia para a Amazônia", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ciências da Saúde na Amazônia Ocidental", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Ecologia e Manejo de Recursos Naturais", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Educação", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Letras: Linguagem e Identidade", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Produção Vegetal", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Sanidade e Produção Animal Sustentável na Amazônia Ocidental", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado em Saúde Coletiva", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Física", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Ciências e Matemática", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Ciências e Matemática", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Ensino de Ciências e Matemática", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Letras", "Programa de Pós-Graduação Stricto Sensu em nível de Mestrado Profissional em Matemática", "Residência em Enfermagem Obstétrica", "Residência Multiprofissional com Ênfase em Terapia Intensiva", "Residência Multiprofissional Integrada em Saúde da Família e Comunidade"}));
		cbCursoAlu.setSelectedIndex(-1);
		
		JLabel lblCadastroAluno = new JLabel("CADASTRO ALUNO");
		lblCadastroAluno.setFont(new Font("Lucida Grande", Font.BOLD, 30));

		JButton btnCadastrarAlu = new JButton("Cadastrar");
		btnCadastrarAlu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Vazia = "";
				
				if(e.getSource()==btnCadastrarAlu){
					if(tfMatriculaAlu.getText().compareTo(Vazia)== 0 ||tfLoginAlu.getText().compareTo(Vazia)== 0 || tfNomeAlu.getText().compareTo(Vazia)== 0 || jpfSenhaAlu.getText().compareTo(Vazia)== 0 || jpfConfSenhaAlu.getText().compareTo(Vazia)== 0 || cbCursoAlu.getSelectedIndex()== -1 ) {
						JOptionPane.showMessageDialog(null, "Confira todas as informações! ");
						
					}else 
						
					if(jpfSenhaAlu.getText().compareTo(jpfConfSenhaAlu.getText()) !=0) {
						JOptionPane.showMessageDialog(null, "Confira a senha ");
					}
					
				else {
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
						PerfilAluno pa = new PerfilAluno();

						dispose();

						pa.setVisible(true);
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
