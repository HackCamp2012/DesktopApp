package de.uulm.presenter.protocol;

import java.io.IOException;

import javax.swing.JOptionPane;

import de.uulm.presenter.auth.Authentication;
import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;

public class AuthenticationProtocolExtension extends RegisteredMessageHandler implements IODevice{

	private ProtocolState state;
	private int challenge=0;
	public AuthenticationProtocolExtension() throws IOException {
		state = ProtocolState.UNAUTHORIZED;
	}
	
	@Override
	public void aMessage(Object o) {
		if (state==ProtocolState.UNAUTHORIZED){
			if ((challenge+"").equals((String)o)){
			//if (("Hello server").equals((String)o)){
				state=ProtocolState.AUTHORIZED;
				System.out.println("auth token accepted");
			}else{
				IORemoteImpl.getRemoteDevice().kickDevices();
				System.out.println("auth token rejected");
			}
		}else{
			for (IORemote r:remoteDevices){
				r.aMessage(o);
			}
		}
	}
	

	@Override
	public void init() {
		super.init();
		challenge=Authentication.generateChallenge();
		JOptionPane.showMessageDialog(null, "Type: "+challenge+" in your phone to confirm security authentication", "Presenter BT guard", JOptionPane.INFORMATION_MESSAGE);
		
	}

}

enum ProtocolState{
	UNAUTHORIZED,AUTHORIZED
}
