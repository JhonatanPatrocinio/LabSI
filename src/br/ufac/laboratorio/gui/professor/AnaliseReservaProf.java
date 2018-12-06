package br.ufac.laboratorio.gui.professor;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.entity.Reserva;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.gui.aluno.ListaHorarios;
import br.ufac.laboratorio.gui.reserva.ReservaTableModel;
import br.ufac.laboratorio.logic.ReservaLogic;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AnaliseReservaProf extends JDialog {

	
	/* 
	 *                         		ESSA CLASSE É ONDE VAI SER LISTADO APENAS A RESERVAS FEITAS POR ESSE PROFESSOR
	 * 								
	 * 									NELA É SO PRA TER OS DADOS E A SITUACAO DA ANALISE
	 * 
	 * 
	 * 
	 * */
	
	
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ReservaLogic rl;
	
	public List<Reserva> carregaDados(int idProf){
		List<Reserva> reservas = new ArrayList<>();
		try {
			reservas = rl.getReservasPorProfessor(idProf);
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException |
				EntityNotExistException | EntityLoginNotExistException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Reservas", JOptionPane.ERROR_MESSAGE);
		}
		
		return reservas;
	}
	public AnaliseReservaProf(Professor professor, Conexao cnx) {
		this.rl = new ReservaLogic(cnx);
		setBounds(100, 100, 700, 392);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lbSituacaoReserva = new JLabel("ANALISE RESERVA");
		lbSituacaoReserva.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
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
		
		JScrollPane spAnaliseProf = new JScrollPane();
		
		JLabel lblProfessor = new JLabel("PROFESSOR");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AnaliseReservaProf.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVoltar)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(181)
							.addComponent(lbSituacaoReserva)
							.addPreferredGap(ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
							.addComponent(lblProfessor))
						.addComponent(spAnaliseProf, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProfessor)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbSituacaoReserva)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
					.addGap(12)
					.addComponent(spAnaliseProf, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVoltar))
		);
		
		table = new JTable(new ReservaTableModel(carregaDados(professor.getId())));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(55);
		table.getColumnModel().getColumn(5).setPreferredWidth(400);
		table.getColumnModel().getColumn(6).setPreferredWidth(15);
		table.getColumnModel().getColumn(7).setPreferredWidth(15);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		spAnaliseProf.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
