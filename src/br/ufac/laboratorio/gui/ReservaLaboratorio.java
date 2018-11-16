package br.ufac.laboratorio.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservaLaboratorio extends JDialog {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public ReservaLaboratorio() {
		
		setBounds(100, 100, 500, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		JLabel lblReservaLaboratorio = new JLabel("RESERVA LABORATORIO");
		lblReservaLaboratorio.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		
		JLabel lblLaboratorio = new JLabel("LABORATORIO");
		
		JComboBox cbLaboratorios = new JComboBox();
		cbLaboratorios.setModel(new DefaultComboBoxModel(new String[] {"LABORATORIO 1 - SISTEMAS DE INFORMAÇÃO", "LABORATORIO 2 - SISTEMAS DE INFORMAÇÃO", "LABORATORIO 3 - ENGENHARIA ELETRICA", "LABORATORIO 4 - MATEMATICA"}));
		
		JLabel lblHorario = new JLabel("HORARIO");
		
		JLabel lblInicio = new JLabel("INICIO");
		
		JLabel lblTermino = new JLabel("TERMINO");
		
		JComboBox cbInicio1 = new JComboBox();
		cbInicio1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		JComboBox cbInicio2 = new JComboBox();
		cbInicio2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JComboBox cbTermino1 = new JComboBox();
		cbTermino1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		
		JComboBox cbTermino2 = new JComboBox();
		cbTermino2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		
		JTextArea taObservacaoLab = new JTextArea();
		
		JButton btnSolicitarLab = new JButton("Solicitar");
		btnSolicitarLab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnVoltar){

					//PerfilProfessor pp = new PerfilProfessor();

					dispose();

					//pp.setVisible(true);
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap(258, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTermino)
										.addComponent(lblInicio))
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(cbInicio1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(cbTermino1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(89)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblHorario)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblLaboratorio)
											.addGap(18)
											.addComponent(cbLaboratorios, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblReservaLaboratorio))))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbInicio2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cbTermino2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addComponent(taObservacaoLab, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVoltar)
					.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
					.addComponent(btnSolicitarLab)
					.addGap(185))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblReservaLaboratorio)
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbLaboratorios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLaboratorio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblHorario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInicio)
						.addComponent(cbInicio1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbInicio2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTermino)
						.addComponent(cbTermino1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbTermino2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(taObservacaoLab, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSolicitarLab)
						.addComponent(btnVoltar)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
