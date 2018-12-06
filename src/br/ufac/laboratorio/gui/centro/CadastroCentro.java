package br.ufac.laboratorio.gui.centro;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.CentroLogic;

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
import javax.swing.ImageIcon;

public class CadastroCentro extends JDialog {

	private JPanel contentPane;
	private JTextField tfNomeCentro;
	private JTextField tfSiglaCadas;
	private CentroLogic cl;
	
	/**
	 * Create the frame.
	 */
	public CadastroCentro(Conexao cnx) {
		cl = new CentroLogic(cnx);
		setBounds(100, 100, 450, 307);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblCadastroCentro = new JLabel("CADASTRAR CENTRO");
		lblCadastroCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNome = new JLabel("NOME");
		
		tfNomeCentro = new JTextField();
		tfNomeCentro.setColumns(40);
		
		JButton btnCadastrarCentro = new JButton("Cadastrar");
		btnCadastrarCentro.setIcon(new ImageIcon(CadastroCentro.class.getResource("/br/ufac/laboratorio/gui/images/Edit16.gif")));
		btnCadastrarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnCadastrarCentro) {
					try {
						cl.addCentro(tfSiglaCadas.getText(), tfNomeCentro.getText());
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
						dispose();
					}catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityAlreadyExistException | InvalidFieldException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
						tfSiglaCadas.setText("");
						tfNomeCentro.setText("");
					}
				}
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(CadastroCentro.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//MenuAdministrador ma = new MenuAdministrador();

					dispose();

					//ma.setVisible(true);
				}
				
			}
		});
		
		JLabel lblSigla = new JLabel("SIGLA");
		
		tfSiglaCadas = new JTextField();
		tfSiglaCadas.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadastroCentro.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(6, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(110)
							.addComponent(lblCadastroCentro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(263)
							.addComponent(lblAdministrador))))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(85)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSigla)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfSiglaCadas)
						.addComponent(tfNomeCentro, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(124, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addGap(86)
					.addComponent(btnCadastrarCentro)
					.addContainerGap(191, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdministrador)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(lblCadastroCentro)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSigla)
						.addComponent(tfSiglaCadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(tfNomeCentro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarCentro)
						.addComponent(btnVoltar))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
