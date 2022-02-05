package com.example.demo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class DashboardView implements DashboardViewIF {

	private JFrame frame;
	private JTable table;

	public static DashboardView view;
	public static DashboardView getView() {
		view = new DashboardView(); return view;
	}
	private DashboardView() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setForeground(Color.ORANGE);
		frame.setTitle("Dashboard");
		frame.setBounds(100, 100, 383, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Stato dei macchinari:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(lblNewLabel);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		String headers[] = new String[] {"Macchinario", "Gruppo Muscolare", "Stato"};
		table.setModel(new DefaultTableModel(headers,0));
		
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		table.setBounds(41, 110, 260, 238);
		table.setRowHeight(20);
		//frame.getContentPane().add(table);
		//table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane);
	}
	
	public void update(List<Macchinario> lista) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); //resetta la tabella
		
		for (Iterator<Macchinario> iterator = lista.iterator(); iterator.hasNext();) {
			Macchinario m = (Macchinario) iterator.next();
			model.addRow(new String[]{m.getNome(), m.getTipo(), m.getStato()});
		}
	}
}
