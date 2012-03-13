package de.uulm.presenter.io;



public interface IODevice {
	public void stop();
	public void sendObject(Object o);
	public void addMessageListener(IORemote r);
	public void removeMessageListener(IORemote r);
}
