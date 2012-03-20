package de.uulm.presenter.remote;

import de.uulm.presenter.io.IORemoteImpl;

public class RemoteDevice {
	public static synchronized void ack(){
		
	}
	public static synchronized void authOK(){
		IORemoteImpl.getRemoteDevice().send("AUTHOK");
	}
	public static synchronized void authReject(){
		IORemoteImpl.getRemoteDevice().send("AUTHREJECT");
	}
}
