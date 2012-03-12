package de.uulm.presenter.protocol;

import java.io.IOException;

import java.util.Vector;



import de.uulm.presenter.bluetooth.BTHandler;
import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;

public class RegisteredMessageHandler extends MessageProtocol implements IODevice{
	
	public RegisteredMessageHandler() throws IOException {
		super();
		
	}

	@Override
	public void init() {
		IORemoteImpl.getRemoteDevice().addIODevice(this);
		this.addRecvListener(IORemoteImpl.getRemoteDevice());
		//send("hello server");
	}
	
	@Override
	public void shutdown() {
		IORemoteImpl.getRemoteDevice().removeIODevice(this);
		this.removeRecvListener(IORemoteImpl.getRemoteDevice());
	}
	
	
		
}
