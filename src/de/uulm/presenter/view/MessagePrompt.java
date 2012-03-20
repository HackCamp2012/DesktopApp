package de.uulm.presenter.view;


import java.awt.Color;
import java.awt.Container;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;

public class MessagePrompt extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -5275209864826449112L;
	
	 
	private final JLabel authString;
	private final JLabel authInfo; 
	private final JLabel mainInfo;
	
	private final int UNIT = 40;
	
	private final JButton ok;
	
	private static final MessagePrompt instance = new MessagePrompt();
	
	public static final MessagePrompt getInstance(){
		return instance;
	}
	
	private MessagePrompt() {
		setAlwaysOnTop(true);
		ok = new JButton("ok");
		authString = new JLabel();
		authInfo = new JLabel("Authenticate your phone:");
		mainInfo = new JLabel();
		setTitle("Presenter");
		setResizable(false);
		setSize(10*UNIT, 5*UNIT);
		setLocationRelativeTo(null);
		applyComponents();
		ok.addActionListener(this);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				System.out.println("exit");
				System.exit(0);
			};
				
			
		});
		
	}
	
	private void applyComponents(){
		Container c = getContentPane();
		
		c.setLayout(null);
		
		
		
		ok.setBounds(UNIT*7, UNIT*3, UNIT*2, UNIT*1);
		c.add(ok);
		
		
		authString.setHorizontalAlignment(SwingConstants.CENTER);
		authString.setFont(new Font("arial",Font.BOLD,40));
		authString.setBounds(UNIT*1, UNIT*1, UNIT*8, UNIT*2);
		c.add(authString);
		
		
		authInfo.setBounds(UNIT*1, UNIT*0, UNIT*7, UNIT*1);
		c.add(authInfo);
		
		mainInfo.setFont(new Font("arial",Font.BOLD,20));
		mainInfo.setBounds(1*UNIT, 1*UNIT, 7*UNIT, 2*UNIT);
		mainInfo.setVerticalAlignment(SwingConstants.TOP);
		mainInfo.setHorizontalAlignment(SwingConstants.CENTER);
		
		c.add(mainInfo);
		
		
		
	}
	
	public void showAuth(int auth){
		String authS= ""+auth;
		String formattedAuthString = authS.substring(0,2)+" "+authS.substring(2,5)+" "+authS.substring(5);
		authString.setText(formattedAuthString);
		authString.setVisible(true);
		setVisible(true);
		authInfo.setVisible(true);
		ok.setVisible(false);
		mainInfo.setVisible(false);
		setSubTitle("Authentication");
	}
	
	public void setSubTitle(String s){
		this.setTitle("Presenter - "+s);
	}
	public void showInfoMessage(String message, String head){
		ok.setVisible(false);
		authString.setVisible(false);
		authInfo.setVisible(false);
		setVisible(true);
		setSubTitle(head);
		mainInfo.setText(message);
		mainInfo.setVisible(true);
	}
	
	public void showConfirmMessage(String message, String head){
		ok.setVisible(true);
		authString.setVisible(false);
		authInfo.setVisible(false);
		setVisible(true);
		setSubTitle(head);
		mainInfo.setText(message);
		mainInfo.setVisible(true);
		
	}
	
	
	
	public void authSuccess(){
		showConfirmMessage("Authentication successful", "Authentication");
		
	}
	
	public void authFailed(){
		showConfirmMessage("Authentication failed", "Authentication");
		
	}
	
	public void connectionLost(){
		showConfirmMessage("Connection lost", "Connection Error");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		
	}

	
}
