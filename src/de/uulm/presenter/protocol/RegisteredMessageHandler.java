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
	public synchronized void init() {
		IORemoteImpl.getRemoteDevice().addIODevice(this);
		this.addMessageListener(IORemoteImpl.getRemoteDevice());
		IORemoteImpl.getRemoteDevice().init();
		super.init();
	}
	
	@Override
	public synchronized void shutdown() {
		IORemoteImpl.getRemoteDevice().removeIODevice(this);
		this.removeMessageListener(IORemoteImpl.getRemoteDevice());
		super.shutdown();
	}
	
	
		
}
