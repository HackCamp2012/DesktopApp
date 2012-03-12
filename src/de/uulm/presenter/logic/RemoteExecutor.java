package de.uulm.presenter.logic;




public class RemoteExecutor extends AuthenticationHandler {
	
	
	@Override
	public void recvMessage(Object o) {
		System.out.println(o);
	}

	
	
}
