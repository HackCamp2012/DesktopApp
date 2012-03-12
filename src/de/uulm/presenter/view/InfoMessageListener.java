package de.uulm.presenter.view;



public interface InfoMessageListener {
	public void showInfoMessage(String msg, String title);
	public void showErrorMessage(String msg, String title);
	public void showWarningMessage(String msg, String title);
	public void showMessage(String msg, String title);
}
