package br.ufac.laboratorio.gui.laboratorio;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.CentroLogic;
import br.ufac.laboratorio.logic.LaboratorioLogic;

import javax.swing.DefaultComboBoxModel;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class CadastroLaboratorio extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNomeLab;
	private JTextField tfComputadores;
	private LaboratorioLogic llc;
	private CentroLogic cl;
	@SuppressWarnings({"unused", "rawtypes", "unchecked"})
	public CadastroLaboratorio(Conexao cnx) {
		this.llc = new LaboratorioLogic(cnx);
		this.cl = new CentroLogic(cnx);
		setBounds(100, 100, 428, 337);
		setLocationRelativeTo(null);
		setResizable(false);
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
		
		tfNomeLab = new JTextField();
		tfNomeLab.setColumns(40);
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(CadastroLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//MenuAdministrador ma = new MenuAdministrador();

					dispose();

					//ma.setVisible(true);
				}
				
			}
		});
		
		JLabel lblCentro = new JLabel("CENTRO");
		
		// EU COLOQUEI ESSE CODIGO DO COMBO BOX DO CENTROS, DEPOIS DA UMA OLHADA PRA VE SE TA CERTO.  TIAGO
		JComboBox cbCentro = new JComboBox();
		List<Centro> centros = new ArrayList<>();
		DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) cbCentro.getModel();
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
			cbCentro.addItem(str);
		}
		cbCentro.setSelectedIndex(-1);
		
		
		//NOVO ITEM, TEM QUE ADD NO BANCO
		JLabel lblComputadores = new JLabel("COMPUTADORES");
		
		tfComputadores = new JTextField();
		tfComputadores.setColumns(10);
		
		JButton btnCadastrarLab = new JButton("Cadastrar");
		btnCadastrarLab.setIcon(new ImageIcon(CadastroLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/ComposeMail16.gif")));
		btnCadastrarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnCadastrarLab) {
					if(cbCentro.getSelectedIndex() != -1) {
						String s = cbCentro.getSelectedItem().toString();
						String [] s2 = s.split(" ");
						Centro centro= null;
						try {
							centro = cl.getCentroSigla(s2[0]);
							llc.addLaboratorio(tfNomeLab.getText(), centro.getId(), Integer.parseInt(tfComputadores.getText()));

							JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
							dispose();
						} catch (DataBaseGenericException | DataBaseNotConnectedException |
								EntityAlreadyExistException | InvalidFieldException | EntityNotExistException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha no Cadastro do Laboratório", JOptionPane.ERROR_MESSAGE);

						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um Centro! ");
					}
				}

			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CadastroLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblComputadores)
								.addComponent(lblCentro)
								.addComponent(lblNome)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVoltar)))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(tfNomeLab, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addGap(15))
									.addComponent(cbCentro, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(tfComputadores, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 19, GroupLayout.PREFERRED_SIZE)))
							.addGap(134))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCadastrarLab)
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(71)
					.addComponent(lblCadastroLaboratorio)
					.addContainerGap(194, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
					.addComponent(lblAdministrador)
					.addGap(120))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAdministrador)
							.addGap(24)
							.addComponent(lblCadastroLaboratorio)
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(tfNomeLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCentro)
								.addComponent(cbCentro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComputadores)
						.addComponent(tfComputadores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCadastrarLab)
						.addComponent(btnVoltar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
