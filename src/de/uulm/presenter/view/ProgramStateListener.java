package de.uulm.presenter.view;

public interface ProgramStateListener {
	public void serverListening();
	public void serverStopped();
	public void serverConnected();
}
