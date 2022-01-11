package com.example.demo;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Color;


public class AppView implements AppViewIF {
	
	private JFrame frame;
	private JPanel cards; 
	private JTextField textField_Nome;
	private JTable table;
	private JButton btnLogin;
	private JPasswordField passwordField;
	private JPanel panel;
	private JButton btnScheda;
	private JButton btnAggiornata;


	public AppView() {
		initialize();
		frame.pack();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame("AppView");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JPanel card_Login = new JPanel();
        card_Login.setLayout(new GridLayout(5, 2, 0, 0));
        
        JLabel lblNewLabel = new JLabel("Nome");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card_Login.add(lblNewLabel);
        
        textField_Nome = new JTextField();
        textField_Nome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField_Nome.setHorizontalAlignment(SwingConstants.CENTER);
        card_Login.add(textField_Nome);
        textField_Nome.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Password");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        card_Login.add(lblNewLabel_1);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        card_Login.add(passwordField);
        
        btnLogin = new JButton("Accedi");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        card_Login.add(btnLogin);
        
        JPanel card_Scheda = new JPanel();
        card_Scheda.setLayout(new BoxLayout(card_Scheda, BoxLayout.Y_AXIS));
        
        JLabel lblSchedaFitness = new JLabel("Scheda Fitness");
        lblSchedaFitness.setBackground(Color.ORANGE);
        lblSchedaFitness.setVerticalAlignment(SwingConstants.TOP);
        lblSchedaFitness.setHorizontalAlignment(SwingConstants.LEFT);
        lblSchedaFitness.setFont(new Font("Dialog", Font.BOLD, 20));
        card_Scheda.add(lblSchedaFitness);
        
        table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		String headers[] = new String[] {"Nome Esercizio", "Tipologia"};
		table.setModel(new DefaultTableModel(headers,0));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
        
        JScrollPane scrollPane = new JScrollPane(table);
        card_Scheda.add(scrollPane);
        
        cards = new JPanel(new CardLayout());
        cards.add(card_Login, "schermata login");
        cards.add(card_Scheda, "schermata scheda");
        
        panel = new JPanel();
        card_Scheda.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        btnScheda = new JButton("Scheda Originale");
        btnScheda.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnScheda.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(btnScheda);
        
        btnAggiornata = new JButton("Scheda Aggiornata");
        btnAggiornata.setFont(new Font("Tahoma", Font.BOLD, 20));

        panel.add(btnAggiornata);
        
        frame.getContentPane().add(cards, BorderLayout.CENTER);
	}
	
	@Override
	public void addListenerAccedi(ActionListener a) {
		btnLogin.addActionListener(a);
	}
	@Override
	public void addListenerSchedaOriginale(ActionListener a) {
		btnScheda.addActionListener(a);
	}
	@Override
	public void addListenerSchedaAggiornata(ActionListener a) {
		btnAggiornata.addActionListener(a);
	}
	
	@Override
	public void switchPanel(String s) {
		CardLayout cl = (CardLayout)cards.getLayout();
		cl.show(cards, s);
	}
	@Override
	public void loginErrato() { textField_Nome.setText("Credenziali Errate"); }
	
	@Override
	public String getNome() { return textField_Nome.getText();}
	
	@Override
	@SuppressWarnings("deprecation")
	public String getPassword() { return passwordField.getText();}
	
	void updateTabella(List<Macchinario> lista) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); //resetta la tabella
		
		for (Iterator<Macchinario> iterator = lista.iterator(); iterator.hasNext();) {
			Macchinario m = (Macchinario) iterator.next();
			model.addRow(new String[]{m.getNome(), m.getTipo()});
		}
	}
}
