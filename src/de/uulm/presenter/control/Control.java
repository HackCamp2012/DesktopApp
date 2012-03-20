package de.uulm.presenter.control;

import java.io.IOException;
import java.util.Observable;

import java.util.Vector;

import de.uulm.presenter.bluetooth.BTServer;
import de.uulm.presenter.exceptions.ServerAlreadyStartedException;
import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;
import de.uulm.presenter.logic.RemoteExecutor;
import de.uulm.presenter.protocol.AuthenticationProtocolExtension;
import de.uulm.presenter.protocol.MessageProtocol;
import de.uulm.presenter.protocol.RegisteredMessageHandler;
import de.uulm.presenter.view.InfoMessageListener;
import de.uulm.presenter.view.ProgramStateListener;


public class Control extends Observable{
	private final Vector<InfoMessageListener> infoMsgListeners;
	private final Vector<ProgramStateListener> programStateListeners;
	
	private final BTServer server;
	private final RemoteExecutor remoteEx;
	public Control() {
		infoMsgListeners = new Vector<InfoMessageListener>();
		programStateListeners= new Vector<ProgramStateListener>();
		server = new BTServer(AuthenticationProtocolExtension.class);
		remoteEx=new RemoteExecutor();
		IORemoteImpl.getRemoteDevice().addIORemote(remoteEx);
		
		
	}
	public void exit(){
		System.exit(0);
	}
	
	public void addInfoMessageListener(InfoMessageListener l){
		infoMsgListeners.add(l);
	}

	public void addProgramStateListener(ProgramStateListener l){
		programStateListeners.add(l);
	}
	
	public void listen(){
		try {
			server.init();
		} catch (IOException e) {
			errorMessage("could not init Server.\nMaybe bluetooth is not enabled.");
			e.printStackTrace();
		} catch (ServerAlreadyStartedException e) {
			errorMessage("Server already started");
			e.printStackTrace();
		}
		
		server.listen();
		
		
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
	

	public void stateServerListening(){
		for (ProgramStateListener l:programStateListeners){
			l.serverListening();
		}
	}
	public void stateServerConnected(){
		for (ProgramStateListener l:programStateListeners){
			l.serverConnected();
		}
	}
	public void stateServerStopped(){
		for (ProgramStateListener l:programStateListeners){
			l.serverStopped();
		}
	}
	

}
