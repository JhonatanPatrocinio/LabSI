package br.ufac.laboratorio.gui.professor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.*;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.gui.reserva.ReservaTableModel;
import br.ufac.laboratorio.logic.ProfessorLogic;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListarProfessor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ProfessorLogic pl;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListarProfessor dialog = new ListarProfessor();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public List<Professor> carregaDados(){
		List<Professor> professores = new ArrayList<>();
		try {
			professores = pl.getProfessores();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException |
				EntityNotExistException | EntityLoginNotExistException e) {
			dispose();
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Reservas", JOptionPane.ERROR_MESSAGE);
		}
		
		return professores;
	}
	
	public ListarProfessor(Conexao cnx) {
		this.pl = new ProfessorLogic(cnx);
		setBounds(100, 100, 578, 451);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		JLabel lblProfessor = new JLabel("PROFESSORES");
		JScrollPane spProfessor = new JScrollPane();
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListarProfessor.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
							.addComponent(lblAdministrador))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(202)
							.addComponent(lblProfessor)
							.addContainerGap(216, Short.MAX_VALUE))))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(12)
					.addComponent(spProfessor, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
					.addGap(12))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAdministrador)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(16)
									.addComponent(lblProfessor)))
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spProfessor, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable(new ListarProfessorTableModel(carregaDados()));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(250);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spProfessor.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dispose();
					
				}
			});
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(btnVoltar)
						.addContainerGap(497, Short.MAX_VALUE))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVoltar))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

}
