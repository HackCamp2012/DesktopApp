package de.uulm.presenter.logic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import org.json.simple.JSONObject;




public class RemoteExecutor extends RemoteProtocol{
	private Robot robot;
	private int screenW;
	private int screenH;
	public RemoteExecutor() {
		try {
			robot = new Robot();
			
			screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
			screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyEvent(String event, long keyCode) {
		if (event.equalsIgnoreCase(RemoteProtocolKeys.PRESS)){
			robot.delay(100);
			robot.keyPress((int)keyCode);
			robot.delay(100);
			robot.keyRelease((int)keyCode);
			
		}else if (event.equalsIgnoreCase(DOWN)){
			robot.delay(100);
			robot.keyPress((int)keyCode);
		}else if (event.equalsIgnoreCase(UP)){
			robot.delay(100);
			robot.keyRelease((int)keyCode);
		}
	
	}
	
	@Override
	public void mouseEvent(String action, double x, double y) {
		int xx = (int)(x*screenW);
		int yy = (int)(y*screenH);
		
		robot.mouseMove(xx, yy);
		
		if (action.equalsIgnoreCase(CLICK)){
			robot.mousePress(MouseEvent.BUTTON1);
			robot.mouseRelease(MouseEvent.BUTTON1);
		}else if (action.equalsIgnoreCase(DBLCLICK)){
			robot.mousePress(MouseEvent.BUTTON1);
			robot.mouseRelease(MouseEvent.BUTTON1);
			robot.mousePress(MouseEvent.BUTTON1);
			robot.mouseRelease(MouseEvent.BUTTON1);
		}else if (action.equalsIgnoreCase(RCLICK)){
			robot.mousePress(MouseEvent.BUTTON2);
			robot.mouseRelease(MouseEvent.BUTTON2);
		}else if (action.equalsIgnoreCase(MCLICK)){
			robot.mousePress(MouseEvent.BUTTON3);
			robot.mouseRelease(MouseEvent.BUTTON3);
		}
		
	}
	

	
	
}
