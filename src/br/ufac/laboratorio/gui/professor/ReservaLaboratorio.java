package br.ufac.laboratorio.gui.professor;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import br.ufac.laboratorio.db.Conexao;
import br.ufac.laboratorio.entity.Centro;
import br.ufac.laboratorio.entity.Laboratorio;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.entity.Reserva;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.LaboratorioLogic;
import br.ufac.laboratorio.logic.ReservaLogic;
import javax.swing.ImageIcon;

public class ReservaLaboratorio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LaboratorioLogic lc;
	private ReservaLogic rl;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReservaLaboratorio frame = new ReservaLaboratorio();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public String formataHora(String hora, String minuto) {
		return  ""+ hora +":"+ minuto +""; 
	}
	

	/**
	 * Create the frame.
	 */
	public ReservaLaboratorio(Conexao cnx, Professor professor) {
		this.lc = new LaboratorioLogic(cnx);
		this.rl = new ReservaLogic(cnx);
		setBounds(100, 100, 500, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblReservaLaboratorio = new JLabel("RESERVA LABORATORIO");
		lblReservaLaboratorio.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		
		JLabel lblLaboratorio = new JLabel("LABORATORIO");
		
		JComboBox cbLaboratorios = new JComboBox();
		List<Laboratorio> laboratorios = new ArrayList<>();
		DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) cbLaboratorios.getModel();
		try {
			laboratorios = lc.getLaboratorios();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException | EntityNotExistException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2.getMessage(), 
					"Falha ao Buscar Laboratorios", JOptionPane.ERROR_MESSAGE);
		}
		String str = null;
		for(int i = 0; i < laboratorios.size(); i++) {
			
				str = (String) Integer.toString(laboratorios.get(i).getId()) +" -"+ " " + laboratorios.get(i).getNome();
				cbLaboratorios.addItem(str);			
		}
		cbLaboratorios.setSelectedIndex(-1);
		
		JLabel lblHorario = new JLabel("HORARIO");
		
		JLabel lblInicio = new JLabel("INICIO");
		
		JLabel lblTermino = new JLabel("TERMINO");
		
		JComboBox cbInicio1 = new JComboBox();
		cbInicio1.setModel(new DefaultComboBoxModel(new String[] {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		JComboBox cbInicio2 = new JComboBox();
		cbInicio2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JComboBox cbTermino1 = new JComboBox();
		cbTermino1.setModel(new DefaultComboBoxModel(new String[] {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		JComboBox cbTermino2 = new JComboBox();
		cbTermino2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JTextArea taObservacaoLab = new JTextArea();
		JDateChooser dateChooser = new JDateChooser();
		
		JLabel lblData = new JLabel("DATA");
		
		JButton btnSolicitarLab = new JButton("Solicitar");
		btnSolicitarLab.setIcon(new ImageIcon(ReservaLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/SendMail16.gif")));
		btnSolicitarLab.setEnabled(false);
		btnSolicitarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(ReservaLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//PerfilProfessor pp = new PerfilProfessor();

					dispose();

					//pp.setVisible(true);
				}
				
			}
		});
		
		JButton btnVerificarHorario = new JButton("Verificar Horario");
		btnVerificarHorario.setIcon(new ImageIcon(ReservaLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/Search16.gif")));
		btnVerificarHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int verifica = 0;
				Reserva reserva = null;
				if(cbLaboratorios.getSelectedIndex() != -1) {
					String s = cbLaboratorios.getSelectedItem().toString();
					String [] s2 = s.split(" ");
					Laboratorio lab = null;
					DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
					String data = dateFormat.format(dateChooser.getDate());
					String horaInic = formataHora(cbInicio1.getSelectedItem().toString(), cbInicio2.getSelectedItem().toString());
					String horaFim = formataHora(cbTermino1.getSelectedItem().toString(), cbTermino2.getSelectedItem().toString());
					try {
						lab = lc.getLaboratorioId(Integer.parseInt(s2[0]));
						rl.addReserva(professor, lab, data, horaInic, horaFim, 0, taObservacaoLab.getText());
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso! ");
						dispose();
					} catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityAlreadyExistException | InvalidFieldException | 
							EntityNotExistException | EntityLoginNotExistException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha no Cadastro da Reserva", JOptionPane.ERROR_MESSAGE);

					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um Centro! ");
				}
//				if(verifica == 0) {
//					JOptionPane.showMessageDialog(null, "Não existe reservas para esta data \n É possivel fazer a solicitação", "Reserva pode ser efetuada", JOptionPane.INFORMATION_MESSAGE);
//					btnSolicitarLab.setEnabled(true);
//				}else {
//					JOptionPane.showMessageDialog(null, "Este horario não esta disponivel para reserva! ", "Reserva não pode ser efetuada", JOptionPane.WARNING_MESSAGE);
//				
//				}
				
			}
		});
		
		JLabel lblDescricao = new JLabel("DESCRICAO");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReservaLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/GlabIcone.png")));
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLaboratorio))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(15)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTermino)
										.addComponent(lblInicio))
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(cbInicio1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cbTermino1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cbInicio2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cbTermino2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(cbLaboratorios, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVoltar)
							.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
							.addComponent(btnSolicitarLab)
							.addGap(86)
							.addComponent(btnVerificarHorario))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addComponent(taObservacaoLab, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(80)
					.addComponent(lblData)
					.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
					.addComponent(lblHorario)
					.addGap(129))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addComponent(lblReservaLaboratorio)
					.addGap(83))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(195)
					.addComponent(lblDescricao)
					.addContainerGap(222, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblReservaLaboratorio)
							.addGap(52)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbLaboratorios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLaboratorio)))
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHorario)
								.addComponent(lblData))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInicio)
								.addComponent(cbInicio1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbInicio2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTermino)
								.addComponent(cbTermino1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbTermino2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(55)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblDescricao)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(taObservacaoLab, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnVerificarHorario)
						.addComponent(btnSolicitarLab)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
