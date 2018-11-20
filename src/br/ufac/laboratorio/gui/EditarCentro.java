package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.logic.CentroLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditarCentro extends JDialog{

	private JPanel contentPane;
	private JTable tabEditarCentro;
	private JTextField tfEditarCentro;
	private JTextField tfSigla;
	private CentroLogic cl;
//	private DefaultTableModel tableModel = new DefaultTableModel();
	

	/**
	 * Create the frame.
	 */
	
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
					"Falha no Cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
		return centros;
	}
	
	public EditarCentro(Conexao cnx) {
		this.cl = new CentroLogic(cnx);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lbEditarCentro = new JLabel("EDITAR CENTRO");
		lbEditarCentro.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		tabEditarCentro = new JTable();
		tabEditarCentro.setModel(new CentroTableModel(carregaDados()));
		tabEditarCentro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		tableModel.addColumn("ID");
//		tableModel.addColumn("SIGLA");
//		tableModel.addColumn("NOME");
//		carregaDados(tableModel);
		tabEditarCentro.getColumnModel().getColumn(0).setPreferredWidth(5);
		tabEditarCentro.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabEditarCentro.getColumnModel().getColumn(2).setPreferredWidth(350);
		scrollPane.setViewportView(tabEditarCentro);
		
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
		
		JLabel lblNome = new JLabel("NOME");
		
		tfEditarCentro = new JTextField();
		tfEditarCentro.setColumns(40);
		
		JButton btnSalvarCentro = new JButton("Salvar");
		btnSalvarCentro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		JLabel lblSigla = new JLabel("SIGLA");
		
		tfSigla = new JTextField();
		tfSigla.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(369, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(164)
							.addComponent(lbEditarCentro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabEditarCentro, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVoltar)
							.addGap(118)
							.addComponent(btnSalvarCentro)
							.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
							.addComponent(btnExcluir))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(78)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNome)
								.addComponent(lblSigla))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfSigla)
								.addComponent(tfEditarCentro, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAdministrador)
					.addGap(23)
					.addComponent(lbEditarCentro)
					.addGap(18)
					.addComponent(tabEditarCentro, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSigla)
						.addComponent(tfSigla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNome)
						.addComponent(tfEditarCentro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnSalvarCentro)
						.addComponent(btnExcluir))
					.addGap(52))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
