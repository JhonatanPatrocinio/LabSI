package br.ufac.laboratorio.gui.centro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.logic.CentroLogic;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

public class MenuEditarCentro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CentroLogic cl;
	JButton btnEditar;
	private JTable table;
//	private DefaultTableModel tableModel = new DefaultTableModel();	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			MenuEditarCentro dialog = new MenuEditarCentro();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
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
	
	public List<Centro> carregaDados(){
		List<Centro> centros = new ArrayList<>();
		try {
			centros = cl.getCentros();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Centro", JOptionPane.ERROR_MESSAGE);
		}
		
		return centros;
	}

	/**
	 * Create the dialog.
	 */
	public MenuEditarCentro(Conexao cnx) {
		this.cl = new CentroLogic(cnx);
		setBounds(100, 100, 545, 398);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		this.setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCentros = new JLabel("CENTROS");
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(238)
							.addComponent(lblCentros))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addComponent(lblCentros)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		{
			table = new JTable(new CentroTableModel(carregaDados()));
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(15);
			table.getColumnModel().getColumn(1).setPreferredWidth(55);
			table.getColumnModel().getColumn(2).setPreferredWidth(400);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.addMouseListener(new HabilitarBtnEdicao());
			scrollPane.setViewportView(table);
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.btnEditar = new JButton("Editar");
				btnEditar.setEnabled(false);
				btnEditar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == btnEditar) {
							Centro centro = null;
							int id = (int) table.getValueAt(table.getSelectedRow(), 0);
							try {
								centro = cl.getCentroId(id);
							} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), 
										"Falha ao Buscar Centro", JOptionPane.ERROR_MESSAGE);
							}
							EditarCentro ec = new EditarCentro(centro, cnx);
							
							dispose();
							
							ec.setVisible(true);
							
						}
						
					}
				});
				btnEditar.setActionCommand("OK");
				buttonPane.add(btnEditar);
				getRootPane().setDefaultButton(btnEditar);
			}
			{
				JButton btnVoltar = new JButton("Voltar");
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
					}
				});
				
				buttonPane.add(btnVoltar);
			}
		}
	} //FIM CONSTRUTOR
	
	class HabilitarBtnEdicao extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (table.getSelectedRow() >= 0) {
				btnEditar.setEnabled(true);
			}else {
				btnEditar.setEnabled(false);
			}

		}
	}
}
