package de.uulm.presenter.control;

import java.io.IOException;
import java.util.Observable;

import java.util.Vector;

import de.uulm.presenter.bluetooth.BTServer;
import de.uulm.presenter.exceptions.ServerAlreadyStartedException;
import de.uulm.presenter.view.InfoMessageListener;


public class Control extends Observable{
	private final Vector<InfoMessageListener> infoMsgListeners;
	private final BTServer server;
	public Control() {
		infoMsgListeners = new Vector<InfoMessageListener>();
		server = new BTServer();
	}
	public void exit(){
		System.exit(0);
	}
	
	public void addInfoMessageListener(InfoMessageListener l){
		infoMsgListeners.add(l);
	}

	public void listen(){
		try {
			server.init();
		} catch (IOException e) {
			errorMessage("could not init server");
			e.printStackTrace();
		} catch (ServerAlreadyStartedException e) {
			errorMessage("Server already started");
			e.printStackTrace();
		}
		
		server.listen();
		infoMessage("server listening");
		
	}
	
	private void infoMessage(String msg){
		final String title = "info";
		for (InfoMessageListener l: infoMsgListeners){
			l.showInfoMessage(msg, title);
		}
	}
	private void errorMessage(String msg){
		final String title = "error";
		for (InfoMessageListener l: infoMsgListeners){
			l.showErrorMessage(msg, title);
		}
	}
}
