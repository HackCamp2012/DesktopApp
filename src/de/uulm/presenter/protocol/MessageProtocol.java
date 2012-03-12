package de.uulm.presenter.protocol;

import java.io.IOException;

import java.util.Vector;



import de.uulm.presenter.bluetooth.BTHandler;
import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IORemote;

public class MessageProtocol extends BTHandler implements IODevice{
	private Vector<IORemote> remoteDevices = new Vector<IORemote>();
	
	public MessageProtocol() throws IOException {
		
		remoteDevices = new Vector<IORemote>();
		
	}
	@Override
	public void recv(byte[] b) {
		
		for (IORemote r:remoteDevices){
		
			r.recv(new String(b,0,b.length));
		}
	}
	
	@Override
	public void addRecvListener(IORemote r) {
		remoteDevices.add(r);
		
	}

	@Override
	public void removeRecvListener(IORemote r) {
		remoteDevices.remove(r);
		
	}


	
	public void sendObject(Object o) {
		String s = (String) o;
		send(s.getBytes());		
	}


	
}
