package de.uulm.presenter.io;



public interface IODevice {
	public void addRecvListener(IORemote r);
	public void removeRecvListener(IORemote r);
	public void stop();
	
	public void sendObject(Object o);

}
