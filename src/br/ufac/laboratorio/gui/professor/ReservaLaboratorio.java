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
import br.ufac.laboratorio.entity.Laboratorio;
import br.ufac.laboratorio.entity.Professor;
import br.ufac.laboratorio.exception.DataBaseGenericException;
import br.ufac.laboratorio.exception.DataBaseNotConnectedException;
import br.ufac.laboratorio.exception.DataExistException;
import br.ufac.laboratorio.exception.EntityAlreadyExistException;
import br.ufac.laboratorio.exception.EntityLoginNotExistException;
import br.ufac.laboratorio.exception.EntityNotExistException;
import br.ufac.laboratorio.exception.EntityTableIsEmptyException;
import br.ufac.laboratorio.exception.InvalidFieldException;
import br.ufac.laboratorio.logic.LaboratorioLogic;
import br.ufac.laboratorio.logic.ReservaLogic;
import javax.swing.ImageIcon;

public class ReservaLaboratorio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LaboratorioLogic lc;
	private ReservaLogic rl;
	private JComboBox cbInicio1;
	private JComboBox cbTermino1;
	DefaultComboBoxModel modeloHoraInicio, modeloHoraTermino;
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

	private String formataHora(String hora, String minuto) {
		return  ""+ hora +":"+ minuto +""; 
	}
	@SuppressWarnings({"unused", "rawtypes", "unchecked"})
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
		
//Preenchendo ComboBox do Laboratório
		JComboBox cbLaboratorios = new JComboBox();
		List<Laboratorio> laboratorios = new ArrayList<>();
		DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel) cbLaboratorios.getModel();
		try {
			laboratorios = lc.getLaboratorios();
		} catch (DataBaseGenericException | DataBaseNotConnectedException | EntityTableIsEmptyException | EntityNotExistException e2) {
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
		
//ComboBox "Dinâmico"
		String [] hor = {"07","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21","22"};
		modeloHoraInicio = new DefaultComboBoxModel(hor);
		cbInicio1.setModel(modeloHoraInicio);
		JComboBox cbTermino1 = new JComboBox();
		cbInicio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] sete = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] oito = {"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] nove = {"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] dez = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] onze = {"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] doze = {"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] treze = {"14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] quat = {"15", "16", "17", "18", "19", "20", "21", "22", "23"};
				String [] quinze = {"16", "17", "18", "19", "20", "21", "22", "23"};
				String [] dezesseis = {"17", "18", "19", "20", "21", "22", "23"};
				String [] dezessete = {"18", "19", "20", "21", "22", "23"};
				String [] dezoito = {"19", "20", "21", "22", "23"};
				String [] dezenove = {"20", "21", "22", "23"};
				String [] vinte = {"21", "22", "23"};
				String [] vinteum = {"22", "23"};
				String [] vintedois = {"23"};
				String horario = cbInicio1.getSelectedItem().toString();
				if(horario.equals("07")) {
					modeloHoraTermino = new DefaultComboBoxModel(sete);
					cbTermino1.setModel(modeloHoraTermino);
				} else if(horario.equals("08")) {
					modeloHoraTermino = new DefaultComboBoxModel(oito);
					cbTermino1.setModel(modeloHoraTermino);
				} else if(horario.equals("09")) {
					modeloHoraTermino = new DefaultComboBoxModel(nove);
					cbTermino1.setModel(modeloHoraTermino);
				} else if(horario.equals("10")) {
					modeloHoraTermino = new DefaultComboBoxModel(dez);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("11")) {
					modeloHoraTermino = new DefaultComboBoxModel(onze);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("12")) {
					modeloHoraTermino = new DefaultComboBoxModel(doze);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("13")) {
					modeloHoraTermino = new DefaultComboBoxModel(treze);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("14")) {
					modeloHoraTermino = new DefaultComboBoxModel(quat);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("15")) {
					modeloHoraTermino = new DefaultComboBoxModel(quinze);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("16")) {
					modeloHoraTermino = new DefaultComboBoxModel(dezesseis);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("17")) {
					modeloHoraTermino = new DefaultComboBoxModel(dezessete);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("18")) {
					modeloHoraTermino = new DefaultComboBoxModel(dezoito);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("19")) {
					modeloHoraTermino = new DefaultComboBoxModel(dezenove);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("20")) {
					modeloHoraTermino = new DefaultComboBoxModel(vinte);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("21")) {
					modeloHoraTermino = new DefaultComboBoxModel(vinteum);
					cbTermino1.setModel(modeloHoraTermino);
				}else if(horario.equals("22")) {
					modeloHoraTermino = new DefaultComboBoxModel(vintedois);
					cbTermino1.setModel(modeloHoraTermino);
				}
			}
		});
		//cbInicio1.setModel(new DefaultComboBoxModel(new String[] {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		JComboBox cbInicio2 = new JComboBox();
		cbInicio2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		//cbTermino1.setVisible(false);
		//if(cbInicio2.getSelectedIndex()=! 0)
		//cbTermino1.setModel(new DefaultComboBoxModel(new String[] {"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		JComboBox cbTermino2 = new JComboBox();
		cbTermino2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		JTextArea taObservacaoLab = new JTextArea();
		JDateChooser dateChooser = new JDateChooser("dd/MM/yyyy","##/##/####",'_');
		JLabel lblData = new JLabel("DATA");
		JButton btnSolicitarLab = new JButton("Solicitar");
		btnSolicitarLab.setIcon(new ImageIcon(ReservaLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/SendMail16.gif")));
		btnSolicitarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
						JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso! ");
						dispose();
					} catch (DataBaseGenericException | DataBaseNotConnectedException |
							EntityAlreadyExistException | InvalidFieldException | 
							EntityNotExistException | EntityLoginNotExistException | DataExistException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), 
								"Falha no Cadastro da Reserva", JOptionPane.ERROR_MESSAGE);

					}
				} else
					JOptionPane.showMessageDialog(null, "Selecione um Laboratório! ");
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(ReservaLaboratorio.class.getResource("/br/ufac/laboratorio/gui/images/Undo16.gif")));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnVoltar)
					dispose();
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
										.addGap(211))
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
								.addComponent(btnSolicitarLab)))
				);
		contentPane.setLayout(gl_contentPane);

	}

}
