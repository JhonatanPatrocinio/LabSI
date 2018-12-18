package br.ufac.laboratorio.gui.centro;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.logic.CentroLogic;
import javax.swing.ImageIcon;

public class EditarCentro extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEditarCentro;
	private JTextField tfSigla;
	private CentroLogic cl;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditarCentro frame = new EditarCentro();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	
	//ESSA PARTE DO CODIGO ESTAVA NO QUE EU PEGUEI 
	
	/*	public void carregaDados(DefaultTableModel model) {//Depois da aula.
	model.setNumRows(0);//zerar a lista do modelo
	List<Centro> centros = new ArrayList<>();
		try {
			centros = cl.getCentros();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
		for(Centro centro : centros) {
			model.addRow(new Object[] {
					centro.getId(),
					centro.getSigla(),
					centro.getNome()
			});
		}
	
}*/

	
	/**
	 * Create the frame.
	 */
	public EditarCentro(Centro centro, Conexao cnx) {
		this.cl = new CentroLogic(cnx);
		setBounds(100, 100, 500, 328);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lbEditarCentro = new JLabel("EDITAR CENTRO");
		lbEditarCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(EditarCentro.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnVoltar){

					MenuEditarCentro mec = new MenuEditarCentro(cnx);

					dispose();

					mec.setVisible(true);
				}
			}
		});
		
		JLabel lblNome = new JLabel("NOME");
		tfEditarCentro = new JTextField();
		tfEditarCentro.setColumns(40);	
		JButton btnSalvarCentro = new JButton("Salvar");
		btnSalvarCentro.setIcon(new ImageIcon(EditarCentro.class.getResource("/br/ufac/laboratorio/gui/images/Save16.gif")));
		btnSalvarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cl.updCentro(tfSigla.getText(), tfEditarCentro.getText());
					JOptionPane.showMessageDialog(null, " Editado! ");
					MenuEditarCentro mec = new MenuEditarCentro(cnx);
					dispose();
					mec.setVisible(true);
				} catch (DataBaseGenericException | DataBaseNotConnectedException | InvalidFieldException |
						EntityNotExistException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Falha ao Atualizar Centro", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
		JLabel lblSigla = new JLabel("SIGLA");	
		tfSigla = new JTextField();
		tfSigla.setColumns(10);
		tfSigla.setText(centro.getSigla());
		tfSigla.setEditable(false);
		tfEditarCentro.setText(centro.getNome());
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EditarCentro.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(164)
							.addComponent(lbEditarCentro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome)
								.addComponent(lblSigla))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfSigla)
								.addComponent(tfEditarCentro, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addGap(131)
							.addComponent(btnSalvarCentro)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblAdministrador)
							.addGap(23)
							.addComponent(lbEditarCentro)
							.addGap(71)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSigla)
								.addComponent(tfSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome)
								.addComponent(tfEditarCentro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvarCentro)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
