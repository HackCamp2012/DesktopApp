package de.uulm.presenter.io;

import java.util.Vector;

import com.intel.bluetooth.NotImplementedError;

public class IODeviceImpl implements IODevice{
	
	private final Vector<IORemote> remoteDevices;
	
	public IODeviceImpl() {
		remoteDevices = new Vector<IORemote>();
	}
	@Override
	public void addRecvListener(IORemote r) {
		remoteDevices.add(r);
		
	}

	@Override
	public void removeRecvListener(IORemote r) {
		remoteDevices.remove(r);
		
	}


	@Override
	public void sendObject(Object o) {
		throw new NotImplementedError();
		
	}
	@Override
	public void stop() {
		throw new NotImplementedError();
		
	}

	
}
