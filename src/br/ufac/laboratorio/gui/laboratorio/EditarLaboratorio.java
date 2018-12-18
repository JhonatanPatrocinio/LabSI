package br.ufac.laboratorio.gui.laboratorio;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Laboratorio;
import br.ufac.laboratorio.exception.*;
import br.ufac.laboratorio.logic.LaboratorioLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

public class EditarLaboratorio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LaboratorioLogic lc;
	private JTable table;
	JButton btnSalvarLab;
	
	public List<Laboratorio> carregaDados(){
		List<Laboratorio> laboratorios = new ArrayList<>();
		try {
			laboratorios = lc.getLaboratorios();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Laboratorios", JOptionPane.ERROR_MESSAGE);
		} catch (EntityTableIsEmptyException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Laboratorios", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return laboratorios;
	}
	
	public EditarLaboratorio(Conexao cnx) {
		this.lc = new LaboratorioLogic(cnx);
		setBounds(100, 100, 623, 382);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lbEditarLab = new JLabel("LABORATORIOS");
		lbEditarLab.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(EditarLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnVoltar){

					//MenuAdministrador ma = new MenuAdministrador();

					dispose();

					//ma.setVisible(true);
				}
			}
		});
		
		this.btnSalvarLab = new JButton("Editar");
		btnSalvarLab.setIcon(new ImageIcon(EditarLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/Edit16.gif")));
		btnSalvarLab.setEnabled(false);
		btnSalvarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Laboratorio lab = null;
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				try {
					lab = lc.getLaboratorioId(id);
				} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Falha ao Buscar Laboratorio", JOptionPane.ERROR_MESSAGE);
				}
				EditLab ela = new EditLab(lab, cnx);

				dispose();

				ela.setVisible(true);
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(EditarLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addPreferredGap(ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
							.addComponent(btnSalvarLab))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
							.addComponent(lbEditarLab)
							.addGap(127)
							.addComponent(lblAdministrador)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAdministrador)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbEditarLab)))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvarLab)))
		);
		
		table = new JTable(new LaboratorioTableModel(carregaDados()));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new HabilitarBtnEdicao());
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	} // FIM CONSTRUTOR
	
	class HabilitarBtnEdicao extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (table.getSelectedRow() >= 0) {
				btnSalvarLab.setEnabled(true);
			}else {
				btnSalvarLab.setEnabled(false);
			}

		}
	}
}
