package br.ufac.laboratorio.gui.aluno;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Reserva;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.gui.reserva.ReservaTableModel;

import br.ufac.laboratorio.logic.ReservaLogic;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class ListaHorarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ReservaLogic rl;
	
	public List<Reserva> carregaDados(){
		List<Reserva> reservas = new ArrayList<>();
		try {
			reservas = rl.getReservas();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException |
				EntityNotExistException | EntityLoginNotExistException e) {
			dispose();
			JOptionPane.showMessageDialog(null, e.getMessage(), 
					"Falha ao Buscar Reservas", JOptionPane.ERROR_MESSAGE);
		}
		
		return reservas;
	}
	public void recarregaTabela(){
		table.setModel((new ReservaTableModel(carregaDados())));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(250);
		table.getColumnModel().getColumn(7).setPreferredWidth(300);
	}

	
	public ListaHorarios(Conexao cnx) {
		this.rl = new ReservaLogic(cnx);
		setBounds(100, 100, 900, 392);
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
		
		JScrollPane spHorarios = new JScrollPane();
		
		JLabel lblAluno = new JLabel("ALUNO");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListaHorarios.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVoltar)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGap(318)
								.addComponent(lblhorario)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAluno))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(spHorarios, GroupLayout.PREFERRED_SIZE, 876, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAluno)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblhorario)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(spHorarios, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVoltar))
		);
		table = new JTable(new ReservaTableModel(carregaDados()));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(250);
		table.getColumnModel().getColumn(7).setPreferredWidth(300);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new HabilitarBtnEdicao());
		spHorarios.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	class HabilitarBtnEdicao extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (table.getSelectedRow() >= 0) {

			}else {
;
			}

		}
	}
}
