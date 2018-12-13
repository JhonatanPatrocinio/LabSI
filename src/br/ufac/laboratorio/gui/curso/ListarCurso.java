package br.ufac.laboratorio.gui.curso;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Curso;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.gui.centro.CentroTableModel;
import br.ufac.laboratorio.gui.centro.EditarCentro;
import br.ufac.laboratorio.gui.curso.MenuEditarCurso.HabilitarBtnEdicao;
import br.ufac.laboratorio.logic.CentroLogic;
import br.ufac.laboratorio.logic.CursoLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;

public class ListarCurso extends JDialog {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	JButton btnEditar;
	private CursoLogic cl;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListarCurso dialog = new ListarCurso();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public List<Curso> carregaDados(){
		List<Curso> cursos = new ArrayList<>();
		try {
			cursos = cl.getCursos();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Curso", JOptionPane.ERROR_MESSAGE);
		}
		
		return cursos;
	}

	
	public ListarCurso(Conexao cnx) {
		
		this.cl = new CursoLogic(cnx);
		setBounds(100, 100, 550, 443);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 281, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 355, Short.MAX_VALUE)
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel panel = new JPanel();
		
		this.btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(ListarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Edit16.gif")));
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnEditar) {
					Curso curso = null;
					int id = (int) table.getValueAt(table.getSelectedRow(), 0);
					try {
						curso = cl.getCursoId(id);
					} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityNotExistException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Buscar Curso", JOptionPane.ERROR_MESSAGE);
					}
					EditarCurso ec = new EditarCurso(curso, cnx);
					
					dispose();
					
					ec.setVisible(true);
					
				}
				
			}
		});
			
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(ListarCurso.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
					.addComponent(btnEditar)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(btnVoltar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblCursos = new JLabel("CURSOS");
		lblCursos.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListarCurso.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
							.addComponent(lblCursos)
							.addGap(127)
							.addComponent(lblAdministrador)
							.addGap(11))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAdministrador)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCursos))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		
		table = new JTable();
		table = new JTable(new CursoTableModel(carregaDados()));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.addMouseListener(new HabilitarBtnEdicao());
		scrollPane.setViewportView(table);
	
		
		
		
		getContentPane().setLayout(groupLayout);
	}
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
