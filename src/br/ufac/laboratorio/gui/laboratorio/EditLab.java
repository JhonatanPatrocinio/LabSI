package br.ufac.laboratorio.gui.laboratorio;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Laboratorio;
import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.logic.CentroLogic;
import br.ufac.laboratorio.logic.LaboratorioLogic;
import javax.swing.ImageIcon;

public class EditLab extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfComputadorLab;
	private JTextField tfNomeLab;
	private JButton btnVoltar;
	private JButton btnSalvar;
	private LaboratorioLogic llc;
	private CentroLogic cl;

//	public static void main(String[] args) {
//		try {
//			EditLab dialog = new EditLab();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	@SuppressWarnings({"unused", "rawtypes", "unchecked"})
	public EditLab(Laboratorio lab, Conexao cnx) {
		this.llc = new LaboratorioLogic(cnx);
		this.cl = new CentroLogic(cnx);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		this.setModal(true);
		tfComputadorLab = new JTextField();
		tfComputadorLab.setColumns(10);
		tfComputadorLab.setText(Integer.toString(lab.getComputadores()));
		JLabel label = new JLabel("COMPUTADORES");
		JLabel label_1 = new JLabel("CENTRO");
		
		JComboBox cbCentroLab = new JComboBox();
		List<Centro> centros = new ArrayList<>();
		DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) cbCentroLab.getModel();
		try {
			centros = cl.getCentros();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2.getMessage(), 
					"Falha no Centro", JOptionPane.ERROR_MESSAGE);
		}
		String str = null;
		for(int i = 0; i < centros.size(); i++) {
			if(centros.get(i).getSigla().equals(lab.getCentro().getSigla())) {
				str = (String) centros.get(i).getSigla()+" -"+ " "+ centros.get(i).getNome();
				cbCentroLab.addItem(str);
				cbCentroLab.setSelectedIndex(i);
			} else {
				str = (String) centros.get(i).getSigla()+" -"+ " "+ centros.get(i).getNome();
				cbCentroLab.addItem(str);				
			}
		}
		
		tfNomeLab = new JTextField();
		tfNomeLab.setColumns(40);
		tfNomeLab.setText(lab.getNome());
		JLabel label_2 = new JLabel("NOME");
		JLabel lbEditLab = new JLabel("EDITAR LABORATORIO");
		lbEditLab.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		{
			btnVoltar = new JButton("Voltar");
			btnVoltar.setIcon(new ImageIcon(EditLab.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		{
			btnSalvar = new JButton("Salvar");
			btnSalvar.setIcon(new ImageIcon(EditLab.class.getResource("/br/ufac/laboratorio/gui/images/Save16.gif")));
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == btnSalvar) {
						String s = cbCentroLab.getSelectedItem().toString();
						String [] s2 = s.split(" ");
						Centro centro= null;
						try {
							centro = cl.getCentroSigla(s2[0]);
							llc.updLaboratorio(lab.getId(), tfNomeLab.getText(), centro.getId(), Integer.parseInt(tfComputadorLab.getText()));
							
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso! ");
							EditarLaboratorio el = new EditarLaboratorio(cnx);
							dispose();
							el.setVisible(true);
						} catch (DataBaseGenericException | DataBaseNotConnectedException |
								InvalidFieldException | EntityNotExistException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), 
									"Falha ao Atualizar Laboratório", JOptionPane.ERROR_MESSAGE);
							
						}
					}
				}
			});
			btnSalvar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSalvar);
		}
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(EditLab.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfComputadorLab, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lbEditLab, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
									.addGap(64))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(tfNomeLab, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
										.addComponent(cbCentroLab, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
									.addGap(60))))))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
					.addComponent(lblAdministrador))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblAdministrador, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbEditLab, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfNomeLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbCentroLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(tfComputadorLab, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSalvar)
						.addComponent(btnVoltar))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
