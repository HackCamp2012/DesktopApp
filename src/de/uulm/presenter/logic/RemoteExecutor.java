package de.uulm.presenter.logic;

import de.uulm.presenter.io.IORemote;




public class RemoteExecutor implements IORemote{
	
	
	
	

	@Override
	public void aMessage(Object o) {
		System.out.println(o);
	}

	@Override
	public void init() {
		
	}

	

	
	
}
