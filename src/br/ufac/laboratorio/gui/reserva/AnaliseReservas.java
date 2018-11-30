package br.ufac.laboratorio.gui.reserva;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Reserva;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.logic.ReservaLogic;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
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
import javax.swing.ImageIcon;

public class AnaliseReservas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ReservaLogic rl;
	JRadioButton rdbtnAceitar;
	JRadioButton rdbtnRecusar;
	private ButtonGroup bg = new ButtonGroup();
	
	JButton btnSalvarAnalise;
	private JLabel label;
	/**
	 * Create the frame.
	 */
	public List<Reserva> carregaDados(){
		List<Reserva> reservas = new ArrayList<>();
		try {
			reservas = rl.getReservasPorStatus(0);
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
	public AnaliseReservas(Conexao cnx) {
		this.rl = new ReservaLogic(cnx);
		setBounds(100, 100, 750, 452);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblAdministrador = new JLabel("ADMINISTRADOR");
		lblAdministrador.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JLabel lblAnaliseDeReservas = new JLabel("ANALISE DE RESERVAS");
		lblAnaliseDeReservas.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		this.btnSalvarAnalise = new JButton("Salvar Analise");
		btnSalvarAnalise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = null;
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				try {
					reserva = rl.getReservaId(id);
				}catch (DataBaseGenericException | DataBaseNotConnectedException | 
						EntityNotExistException | EntityLoginNotExistException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), 
							"Falha ao Buscar Reserva", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
				if(rdbtnAceitar.isSelected()) {
					reserva.setStatus(1);
					try {
						rl.updReserva(reserva);
						JOptionPane.showMessageDialog(null, " Salvo! ");
						recarregaTabela();
					}catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityNotExistException | EntityLoginNotExistException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Atualizar Reserva", JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				} else {
					reserva.setStatus(2);
					try {
						rl.updReserva(reserva);
						JOptionPane.showMessageDialog(null, " Salvo! ");
						recarregaTabela();
					}catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityNotExistException | EntityLoginNotExistException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha ao Atualizar Reserva", JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				}
			}
		});
		btnSalvarAnalise.setIcon(new ImageIcon(AnaliseReservas.class.getResource("/br/ufac/laboratorio/gui/images/Save16.gif")));
		btnSalvarAnalise.setEnabled(false);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(AnaliseReservas.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//MenuAdministrador ma = new MenuAdministrador();

					dispose();

					//ma.setVisible(true);
				}
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		this.rdbtnRecusar = new JRadioButton("Recusar");
		this.rdbtnAceitar = new JRadioButton("Aceitar");
		bg.add(rdbtnAceitar);
		bg.add(rdbtnRecusar);
		rdbtnAceitar.setEnabled(false);
		rdbtnRecusar.setEnabled(false);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(AnaliseReservas.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAnaliseDeReservas)
							.addGap(132)
							.addComponent(lblAdministrador)))
					.addGap(15))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED, 556, Short.MAX_VALUE)
					.addComponent(btnSalvarAnalise)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(276)
					.addComponent(rdbtnAceitar)
					.addGap(18)
					.addComponent(rdbtnRecusar)
					.addContainerGap(289, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblAdministrador))
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addGap(33))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAnaliseDeReservas)
							.addGap(18)))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnVoltar)
								.addComponent(btnSalvarAnalise)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnRecusar)
								.addComponent(rdbtnAceitar)))))
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
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	
	class HabilitarBtnEdicao extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (table.getSelectedRow() >= 0) {
				rdbtnAceitar.setEnabled(true);
				rdbtnRecusar.setEnabled(true);
				btnSalvarAnalise.setEnabled(true);
			}else {
				rdbtnAceitar.setEnabled(false);
				rdbtnRecusar.setEnabled(false);
				btnSalvarAnalise.setEnabled(false);
			}

		}
	}
}
