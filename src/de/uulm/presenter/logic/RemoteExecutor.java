package de.uulm.presenter.logic;


import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;

public class RemoteExecutor implements IORemote {

	
	@Override
	public void recv(Object o) {
		System.out.println("recv "+o);
		System.out.println(o instanceof String);
		IORemoteImpl.getRemoteDevice().send("> "+o);
	}

	
	
}
