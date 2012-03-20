package de.uulm.presenter.logic;





import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import de.uulm.presenter.io.IORemote;

public class RemoteProtocol implements IORemote,RemoteProtocolKeys{
	private final JSONParser parser;
	public RemoteProtocol() {
		parser = new JSONParser();
	}
	@Override
	public void aMessage(Object o) {
		System.out.println("o: "+o);
		
		try {
			JSONObject json = (JSONObject)parser.parse((String)o);
			
			String type  = (String) json.get("type");
			
			if (KEY.equalsIgnoreCase(type)){
				String event = (String) json.get("event");
				long keyCode = (Long)json.get("keycode");
				this.keyEvent(event, keyCode);
			}else{
				//mouse
				String action = (String)json.get("action");
				double x =(Double) json.get("x");
				double y = (Double) json.get("y");
				mouseEvent(action, x, y);
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void init() {
		
	}


	public void keyEvent(String event,long keyCode){
		
	}
	
	public void mouseEvent(String action,double x, double y){
		
	}
}
