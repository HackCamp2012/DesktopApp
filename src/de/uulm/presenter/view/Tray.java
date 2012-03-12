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

import de.uulm.presenter.control.Control;
import de.uulm.presenter.control.Main;
import de.uulm.presenter.exceptions.TrayIconNotSupportedError;

public class Tray implements ActionListener, InfoMessageListener,Observer,ProgramStateListener{
	
	private final Image trayimggreen = Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("../res/TrayIcongreen.png"));
	private final Image trayimgred = Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("../res/TrayIconred.png"));
	private final Image trayimgyellow = Toolkit.getDefaultToolkit().getImage(Tray.class.getResource("../res/TrayIconyellow.png"));
	private final TrayIcon trayIcon = new TrayIcon(trayimgred,"presenter");
	private final Control control;
	public Tray(Control c) throws AWTException, TrayIconNotSupportedError {
		this.control=c;
		if (SystemTray.isSupported()){
			SystemTray stray = SystemTray.getSystemTray();
			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(this);
			stray.add(trayIcon);
			
			final PopupMenu menu = new PopupMenu();
			
			MenuItem about = new MenuItem("about");
			MenuItem exit = new MenuItem("exit");
			MenuItem listen = new MenuItem("listen");
			
			about.addActionListener(this);
			about.setActionCommand("tray_about");
			
			exit.addActionListener(this);
			exit.setActionCommand("tray_exit");
			
			listen.addActionListener(this);
			listen.setActionCommand("tray_listen");
			
			menu.add(about);
			menu.add(exit);
			menu.add(listen);
			
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
		
		
	}


	@Override
	public void serverListening() {
		trayIcon.setImage(trayimgyellow);
	}


	@Override
	public void serverStopped() {
		trayIcon.setImage(trayimgred);
		
	}


	@Override
	public void serverConnected() {
		trayIcon.setImage(trayimggreen);
		
	}
	
	
}

