package de.uulm.presenter.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AuthPrompt extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = -5275209864826449112L;
	
	 
	private final JLabel authString;
	private final JLabel info; 
	
	private final String INFOMESSAGE = "<html><font size=20px>Waiting for Authentication</font><br>Type the Authentication Key in your Phone to connect</html>";
	private final String SUCCESSMESSAGE = "Authentication successful";
	private final String FAILEDMESSAGE = "Authentication failed";
	private final String CONNECTIONLOST = "Connection lost";
	
	private final JButton ok;
	public AuthPrompt() {
		setAlwaysOnTop(true);
		ok = new JButton("ok");
		authString = new JLabel();
		info = new JLabel();
		setTitle("Authentication");
		setSize(800, 400);
		setLocationRelativeTo(null);
		applyComponents();
		ok.addActionListener(this);
		
	}
	
	private void applyComponents(){
		Container c = getContentPane();
		BorderLayout b = new BorderLayout();
		c.setLayout(b);
		c.add(info, BorderLayout.NORTH);
		c.add(authString, BorderLayout.CENTER);
		c.add(ok,BorderLayout.SOUTH);
		
	}
	
	public void showAuth(int auth){
		info.setText(INFOMESSAGE);
		authString.setText(auth+"");
		authString.setVisible(true);
		setVisible(true);
		ok.setVisible(false);
	}
	
	public void authSuccess(){
		authString.setVisible(false);
		info.setText(SUCCESSMESSAGE);
		ok.setVisible(true);
	}
	
	public void authFailed(){
		authString.setVisible(false);
		info.setText(FAILEDMESSAGE);
		ok.setVisible(true);
	}
	
	public void connectionLost(){
		authString.setVisible(false);
		info.setText(CONNECTIONLOST);
		ok.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		
	}
}
