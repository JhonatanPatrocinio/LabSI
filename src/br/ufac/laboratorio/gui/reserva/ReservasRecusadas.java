package br.ufac.laboratorio.gui.reserva;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Reserva;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.gui.reserva.ListaReservas.HabilitarBtnEdicao;
import br.ufac.laboratorio.logic.ReservaLogic;

public class ReservasRecusadas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
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

	public ReservasRecusadas(Conexao cnx) {
		this.rl = new ReservaLogic(cnx);
		setBounds(100, 100, 900, 451);
		setLocationRelativeTo(null);
		//setResizable(false);
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblReservas = new JLabel("RESERVAS RECUSADAS");
		lblReservas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		JScrollPane spAlunos = new JScrollPane();

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListaReservas.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(262)
							.addComponent(lblReservas))
						.addComponent(spAlunos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReservas))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(spAlunos, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
		spAlunos.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton btnVoltar = new JButton("Voltar");
			btnVoltar.setIcon(new ImageIcon(ListaReservas.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
			btnVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					dispose();

				}
			});
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnVoltar)
						.addContainerGap(504, Short.MAX_VALUE))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(btnVoltar)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	} //FIM CONSTRUCTOR
	class HabilitarBtnEdicao extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (table.getSelectedRow() >= 0) {

			}else {
				;
			}

		}
	}
}


