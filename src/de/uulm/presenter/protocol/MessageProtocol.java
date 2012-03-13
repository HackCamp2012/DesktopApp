package de.uulm.presenter.protocol;

import java.io.IOException;

import java.util.Vector;



import de.uulm.presenter.bluetooth.BTHandler;
import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IORemote;

public abstract class MessageProtocol extends BTHandler implements IODevice{
	protected Vector<IORemote> remoteDevices = new Vector<IORemote>();
	
	public MessageProtocol() throws IOException {
		
		remoteDevices = new Vector<IORemote>();
		
	}
	@Override
	public void recv(byte[] b,int cnt) {
		this.aMessage(new String(b,0,cnt));
	}
	
	public void aMessage(Object o){
		
	}
	
	@Override
	public void addMessageListener(IORemote r) {
		remoteDevices.add(r);
		
	}

	@Override
	public void removeMessageListener(IORemote r) {
		remoteDevices.remove(r);
		
	}


	
	public void sendObject(Object o) {
		String s = (String) o;
		send(s.getBytes());		
	}


	
}
