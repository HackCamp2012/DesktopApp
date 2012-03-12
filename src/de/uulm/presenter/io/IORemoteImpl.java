package de.uulm.presenter.io;

import java.util.Vector;

import com.intel.bluetooth.NotImplementedError;


public class IORemoteImpl implements IORemote{

	private final Vector<IODevice> iodev;
	private final Vector<IORemote> iorem;
	
	private static final IORemoteImpl instance = new IORemoteImpl();
	
	public static IORemoteImpl getRemoteDevice(){
		return IORemoteImpl.instance;
	}
	
	private IORemoteImpl() {
		iodev = new Vector<IODevice>();
		iorem = new Vector<IORemote>();
	}
	public void addIODevice(IODevice c){
		iodev.add(c);
	}
	public void removeIODevice(IODevice c){
		iodev.remove(c);
		
	}
	
	public void addIORemote(IORemote c){
		iorem.add(c);
	}
	public void removeIORemote(IORemote c){
		iorem.remove(c);
		
	}

	public void send(Object o){
		for (IODevice d:iodev){
			d.sendObject(o);
		}
	}
	

	public void kickDevices(){
		for (IODevice d:iodev){
			d.stop();
		}
	}

	@Override
	public void recv(Object o) {
		for (IORemote r:iorem){
			r.recv(o);
		}
		
	}

	@Override
	public void init() {
		for (IORemote r:iorem){
			r.init();
		}
		
	}
	
	
	
}
