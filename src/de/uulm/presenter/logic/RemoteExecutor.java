package de.uulm.presenter.logic;

import org.json.simple.JSONObject;




public class RemoteExecutor extends RemoteProtocol{
	
	@Override
	public void keyEvent(String event, long keyCode) {
		System.out.println("key");
		System.out.println(" "+keyCode);
		System.out.println(" "+event);
	
	}
	
	@Override
	public void mouseEvent(String action, double x, double y) {
		System.out.println("mouse Event");
		System.out.println(" "+x);
		System.out.println(" "+y);
	}
	

	
	
}
