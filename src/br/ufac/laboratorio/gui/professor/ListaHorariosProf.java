package br.ufac.laboratorio.gui.professor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.gui.aluno.ListaHorarios;
import br.ufac.laboratorio.logic.ProfessorLogic;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ListaHorariosProf extends JDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ProfessorLogic pl;

	 
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
	
	public ListaHorariosProf(Conexao cnx) {
		this.pl = new ProfessorLogic(cnx);
		setBounds(100, 100, 600, 392);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblhorario = new JLabel("HORARIOS");
		lblhorario.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(ListaHorarios.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnVoltar){

					//PerfilAluno pa = new PerfilAluno();

					dispose();

					//pa.setVisible(true);
				}
				
			}
		});
		
		JScrollPane spProf = new JScrollPane();
		
		JLabel lblProfessor = new JLabel("PROFESSOR");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListaHorariosProf.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(211)
							.addComponent(lblhorario)
							.addPreferredGap(ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
							.addComponent(lblProfessor))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(btnVoltar))
								.addComponent(spProf, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProfessor)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblhorario))
					.addGap(18)
					.addComponent(spProf, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(btnVoltar)
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
		spProf.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
