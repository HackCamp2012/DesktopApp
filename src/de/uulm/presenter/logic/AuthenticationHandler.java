package de.uulm.presenter.logic;

import javax.swing.JOptionPane;

import de.uulm.presenter.auth.Authentication;
import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;

public class AuthenticationHandler implements IORemote{

	private ProtocolState state;
	private int challenge=0;
	public AuthenticationHandler() {
		state = ProtocolState.UNAUTHORIZED;
	}
	
	@Override
	public void recv(Object o) {
		if (state==ProtocolState.UNAUTHORIZED){
			if ((challenge+"").equals(o)){
				state=ProtocolState.AUTHORIZED;
				System.out.println("auth token accepted");
			}else{
				IORemoteImpl.getRemoteDevice().kickDevices();
				System.out.println("auth token rejected");
			}
		}else{
			recvMessage(o);
		}
	}
	
	public void recvMessage(Object o){
		
	}

	@Override
	public void init() {
		challenge=Authentication.generateChallenge();
		JOptionPane.showMessageDialog(null, "Confirm the Security Token: \n "+challenge);
		
	}

}

enum ProtocolState{
	UNAUTHORIZED,AUTHORIZED
}
