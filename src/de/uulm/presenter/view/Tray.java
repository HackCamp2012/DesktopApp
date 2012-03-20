package de.uulm.presenter.view;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import de.uulm.presenter.control.Control;
import de.uulm.presenter.control.Main;
import de.uulm.presenter.exceptions.TrayIconNotSupportedError;

public class Tray implements ActionListener, InfoMessageListener,Observer,ProgramStateListener{
	
	private final Image trayimggreen = Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("../res/TrayIcongreen.png"));
	private final Image trayimgred = Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("../res/TrayIconred.png"));
	private final Image trayimgyellow = Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("../res/TrayIconyellow.png"));
	private final TrayIcon trayIcon = new TrayIcon(trayimgred,"presenter");
	private final PopupMenu menu;
	private final Control control;
	private final MenuItem restart;
	public Tray(Control c) throws AWTException, TrayIconNotSupportedError {
		this.control=c;
		if (SystemTray.isSupported()){
			SystemTray stray = SystemTray.getSystemTray();
			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(this);
			stray.add(trayIcon);
			
			menu = new PopupMenu();
			
			MenuItem about = new MenuItem("about");
			MenuItem exit = new MenuItem("exit");
			
			restart = new MenuItem("restart");
			
			about.addActionListener(this);
			about.setActionCommand("tray_about");
			
			exit.addActionListener(this);
			exit.setActionCommand("tray_exit");
			
			restart.addActionListener(this);
			restart.setActionCommand("tray_listen");
			
			menu.add(about);
			menu.add(exit);
			
			
			trayIcon.setPopupMenu(menu);
			
		}else{
			throw new TrayIconNotSupportedError();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("tray_exit")){
			control.exit();
		}else if (event.getActionCommand().equals("tray_listen")){
			control.listen();
		}
		
	}
	
	@Override
	public void showInfoMessage(String msg, String title){
		trayIcon.displayMessage(title, msg, TrayIcon.MessageType.INFO);
		
	}
	
	@Override
	public void showErrorMessage(String msg, String title){
		trayIcon.displayMessage(title, msg, TrayIcon.MessageType.ERROR);
	}

	@Override
	public void showWarningMessage(String msg, String title) {
		trayIcon.displayMessage(title, msg, TrayIcon.MessageType.WARNING);
	}

	@Override
	public void showMessage(String msg, String title) {
		trayIcon.displayMessage(title, msg, TrayIcon.MessageType.NONE);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
		if (Control.HIDERESTART.equals(arg1)){
			menu.remove(restart);
		}else if (Control.SHOWRESTART.equals(arg1)){
			menu.add(restart);
		}
		
	}


	@Override
	public void serverListening() {
		trayIcon.setImage(trayimgyellow);
		trayIcon.setToolTip("Presenter - ready");
	}


	@Override
	public void serverStopped() {
		trayIcon.setImage(trayimgred);
		trayIcon.setToolTip("Presenter - stopped");
		
	}


	@Override
	public void serverConnected() {
		trayIcon.setImage(trayimggreen);
		trayIcon.setToolTip("Presenter - connected");
		
	}
	
	
}

