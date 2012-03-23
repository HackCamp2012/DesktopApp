package de.uulm.presenter.protocol;

import java.io.IOException;

import javax.swing.JOptionPane;

import de.uulm.presenter.auth.Authentication;
import de.uulm.presenter.control.Main;
import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;
import de.uulm.presenter.remote.RemoteDevice;
import de.uulm.presenter.view.MessagePrompt;

public class AuthenticationProtocolExtension extends RegisteredMessageHandler implements IODevice{

	private ProtocolState state;
	private int challenge=0;
	private final MessagePrompt authPrompt;
	
	public AuthenticationProtocolExtension() throws IOException {
		state = ProtocolState.UNAUTHORIZED;
		authPrompt = MessagePrompt.getInstance();
	}
	
	@Override
	public void aMessage(Object o) {
		//System.out.println("raw message: "+(String) o);
		
		if (state==ProtocolState.UNAUTHORIZED){
//			if ((challenge+"").equals((String)o)){
//				state=ProtocolState.AUTHORIZED;
//				authPrompt.authSuccess();
//				Main.control.stateServerConnected();
//			}else{
//				IORemoteImpl.getRemoteDevice().kickDevices();
//				authPrompt.authFailed();
//			}
		}else{
			for (IORemote r:remoteDevices){
				r.aMessage(o);
			}
		}
	}
	

	@Override
	public void init() {
		super.init();
		//challenge=Authentication.generateChallenge();
		//JOptionPane.showMessageDialog(null, "Type: "+challenge+" in your phone to confirm security authentication", "Presenter BT guard", JOptionPane.INFORMATION_MESSAGE);
		//authPrompt.showAuth(challenge);
		
		
		boolean b = MessagePrompt.getInstance().showAskMessage("An external device is trying to connect to your phone.", "Connection attempt","allow","deny");
		if (b){
			state=ProtocolState.AUTHORIZED;
			RemoteDevice.authOK();
			Main.control.stateServerConnected();
		}else{
			RemoteDevice.authReject();
			IORemoteImpl.getRemoteDevice().kickDevices();
		}
	}
	@Override
	public synchronized void shutdown() {
		//authPrompt.connectionLost();
		if (state==ProtocolState.AUTHORIZED){
			MessagePrompt.getInstance().showConfirmMessage("Connection lost", "Connection");
		}
		super.shutdown();
	}
}

enum ProtocolState{
	UNAUTHORIZED,AUTHORIZED
}
