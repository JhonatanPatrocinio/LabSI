package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.LaboratorioLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroLaboratorio extends JDialog{

	private JPanel contentPane;
	private JTextField tdNomeLab;
	private LaboratorioLogic llc;
	

	/**
	 * Create the frame.
	 */
	public CadastroLaboratorio(Conexao cnx) {
		this.llc = new LaboratorioLogic(cnx);
		setBounds(100, 100, 450, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblCadastroLaboratorio = new JLabel("CADASTRO LABORATORIO");
		lblCadastroLaboratorio.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNome = new JLabel("NOME");
		
		tdNomeLab = new JTextField();
		tdNomeLab.setColumns(40);
		
		JButton btnCadastrarLab = new JButton("Cadastrar");
		btnCadastrarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnCadastrarLab) {
					try {
						llc.addLaboratorio(tdNomeLab.getText());
						
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
						dispose();
					} catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityAlreadyExistException | InvalidFieldException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//MenuAdministrador ma = new MenuAdministrador();

					dispose();

					//ma.setVisible(true);
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(373, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tdNomeLab, 0, 0, Short.MAX_VALUE))
								.addComponent(lblCadastroLaboratorio, Alignment.LEADING)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(157)
							.addComponent(btnCadastrarLab))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVoltar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCadastroLaboratorio)
					.addGap(125)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(tdNomeLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCadastrarLab)
							.addGap(31))
						.addComponent(btnVoltar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
