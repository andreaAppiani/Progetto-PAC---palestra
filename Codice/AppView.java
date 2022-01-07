package com.example.demo;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class AppView extends JFrame{

	private JFrame frame;
	private JPanel panel;
	private JButton pulsante;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	public AppView() {
		initialize();
		frame.setVisible(true);
	}
	// Codice scritto in automatico da WindowBuilder
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		pulsante = new JButton("New button");
		panel.add(pulsante);
		
		label1 = new JLabel("New label");
		panel.add(label1);
		
		label2 = new JLabel("Skandre gaming");
		panel.add(label2);
		
		label3 = new JLabel("New label");
		panel.add(label3);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addListener(ActionListener a) {
		pulsante.addActionListener(a);
	}
	
	// Riceve la lista di Macchinari dal Controller e scrive le loro info nelle "TextLabel" dell'interfaccia
	public void update(List<Macchinario> l) {
		label1.setText(l.get(0).toString());
		label2.setText(l.get(1).toString());
		label3.setText(l.get(2).toString());
	}

}
