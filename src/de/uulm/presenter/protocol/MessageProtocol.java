package de.uulm.presenter.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.Vector;



import de.uulm.presenter.bluetooth.BTHandler;
import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IORemote;

public  class MessageProtocol extends BTHandler implements IODevice{
	protected Vector<IORemote> remoteDevices = new Vector<IORemote>();
	private StringBuffer message;
	private final char SEPARATOR = '|'; 
	public MessageProtocol() throws IOException {
		message = new StringBuffer();
		remoteDevices = new Vector<IORemote>();
		
	}
	@Override
	public void recv(byte[] b,int cnt) {
		String s = new String(b,0,cnt);
		this.recvString(s);		
	}

	private void recvString(String s){
		int msgSeparatorAt=s.indexOf(SEPARATOR);
		
		if (msgSeparatorAt==-1){
			//no separator found = message not yet done
			message.append(s);
			return;
		}
		
		message.append(s,0,msgSeparatorAt);
		this.aMessage(message.toString());
		message.setLength(0);
		recvString(s.substring(msgSeparatorAt+1));
	}
	
	public void aMessage(Object o){
		System.out.println("message: "+o);
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
		String s = ((String) o)+"|";
		send(s.getBytes());		
	}



}



