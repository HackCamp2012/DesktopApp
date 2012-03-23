package de.uulm.presenter.view;


import java.awt.Color;
import java.awt.Container;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import javax.swing.SwingConstants;

public class MessagePrompt extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -5275209864826449112L;
	
	 
	private final JLabel authString;
	private final JLabel authInfo; 
	private final JTextArea mainInfo;
	
	private final int UNIT = 40;
	
	private final JButton ok;
	private final JButton no;
	private boolean yes=false;
	private static final MessagePrompt instance = new MessagePrompt();
	
	public static final MessagePrompt getInstance(){
		return instance;
	}
	
	private MessagePrompt() {
		setAlwaysOnTop(true);
		ok = new JButton("ok");
		no = new JButton("no");
		authString = new JLabel();
		authInfo = new JLabel("Authenticate your phone:");
		mainInfo = new JTextArea();
		mainInfo.setWrapStyleWord(true);
		mainInfo.setLineWrap(true);
		mainInfo.setBackground(new Color(1,true));
		setTitle("Presenter");
		setResizable(false);
		setSize(10*UNIT, 5*UNIT);
		setLocationRelativeTo(null);
		applyComponents();
		ok.addActionListener(this);
		no.addActionListener(this);
		no.setVisible(false);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				MessagePrompt.getInstance().setVisible(false);
			};
				
			
		});
		
	}
	
	private void applyComponents(){
		Container c = getContentPane();
		
		c.setLayout(null);
		
		
		
		ok.setBounds(UNIT*7, UNIT*3, UNIT*2, UNIT*1);
		c.add(ok);
		
		no.setBounds(UNIT*1, UNIT*3, UNIT*2, UNIT*1);
		c.add(no);
		
		
		authString.setHorizontalAlignment(SwingConstants.CENTER);
		authString.setFont(new Font("arial",Font.BOLD,40));
		authString.setBounds(UNIT*1, UNIT*1, UNIT*8, UNIT*2);
		c.add(authString);
		
		
		authInfo.setBounds(UNIT*1, UNIT*0, UNIT*7, UNIT*1);
		c.add(authInfo);
		
		mainInfo.setFont(new Font("arial",Font.BOLD,20));
		mainInfo.setBounds(1*UNIT, 1*UNIT, 8*UNIT, 3*UNIT);
		//mainInfo.(SwingConstants.TOP);
		//mainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		c.add(mainInfo);
		
		
		
	}
	
	public synchronized boolean showAskMessage(String message,String head,String yes, String no){
		ok.setText(yes);
		this.no.setText(no);
		
		showConfirmMessage(message,head);
		this.no.setVisible(true);
		ok.setVisible(true);
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.yes;
	}
	
	public void setSubTitle(String s){
		this.setTitle("Presenter - "+s);
	}
	public void showInfoMessage(String message, String head){
		ok.setVisible(false);
		this.no.setVisible(false);
		authString.setVisible(false);
		authInfo.setVisible(false);
		setVisible(true);
		setSubTitle(head);
		mainInfo.setText(message);
		mainInfo.setVisible(true);
	}
	
	public void showConfirmMessage(String message, String head){
		ok.setVisible(true);
		this.no.setVisible(false);
		authString.setVisible(false);
		authInfo.setVisible(false);
		setVisible(true);
		setSubTitle(head);
		mainInfo.setText(message);
		mainInfo.setVisible(true);
		
	}
	
	
	
	
	
	
	

	@Override
	public synchronized void  actionPerformed(ActionEvent e) {
		if (no.isVisible()){
			yes = (e.getSource() == ok);
		}else{
			yes = false;
		}
		this.notifyAll();
		setVisible(false);
		ok.setText("ok");
		no.setText("no");
		no.setVisible(false);
		ok.setVisible(false);
		
	}

	
}
