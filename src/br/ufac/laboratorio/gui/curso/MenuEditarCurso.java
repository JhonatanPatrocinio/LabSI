package br.ufac.laboratorio.gui.curso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Curso;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.logic.CentroLogic;
import br.ufac.laboratorio.logic.CursoLogic;

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
import javax.swing.ImageIcon;
import java.awt.Font;

public class MenuEditarCurso extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CursoLogic cl;
	JButton btnEditar;
	private JTable table;
	private JButton btnVoltar;
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
	
	public List<Curso> carregaDados(){
		List<Curso> cursos = new ArrayList<>();
		try {
			cursos = cl.getCursos();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Centro", JOptionPane.ERROR_MESSAGE);
		}
		
		return cursos;
	}

	/**
	 * Create the dialog.
	 */
	public MenuEditarCurso(Conexao cnx) {
		this.cl = new CursoLogic(cnx);
		setBounds(100, 100, 545, 398);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		this.setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCentros = new JLabel("CURSOS");
		lblCentros.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JScrollPane scrollPane = new JScrollPane();
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MenuEditarCurso.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
							.addComponent(lblCentros)
							.addGap(133)
							.addComponent(lblAdministrador))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdministrador)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblCentros)))
					.addGap(12)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
					.addContainerGap())
		);
		{
			table = new JTable(new CursoTableModel(carregaDados()));
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				this.btnEditar = new JButton("Editar");
				btnEditar.setIcon(new ImageIcon(MenuEditarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Edit16.gif")));
		
				btnEditar.setEnabled(true);
				btnEditar.addActionListener(new ActionListener() {
					private Curso curso;

					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == btnEditar) {
							curso = null;
							int id = (int) table.getValueAt(table.getSelectedRow(), 0);
							try {
								curso = cl.getCursoId(id);
							} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), 
										"Falha ao Buscar Centro", JOptionPane.ERROR_MESSAGE);
							}
							EditarCurso ec = new EditarCurso(curso, cnx);
							
							dispose();
							
							ec.setVisible(true);
							
						}
						
					}
				});
				btnEditar.setActionCommand("OK");
				getRootPane().setDefaultButton(btnEditar);
			}
			{
				btnVoltar = new JButton("Voltar");
				btnVoltar.setIcon(new ImageIcon(MenuEditarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
				btnVoltar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
						
					}
				});
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnVoltar)
						.addGap(328)
						.addComponent(btnEditar)
						.addContainerGap())
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEditar)
							.addComponent(btnVoltar))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
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
