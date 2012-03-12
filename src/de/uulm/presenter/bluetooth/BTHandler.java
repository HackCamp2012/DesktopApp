package de.uulm.presenter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.StreamConnection;

public class BTHandler implements Runnable{
	private final StreamConnection con;
	private boolean listening=true;
	private InputStream is;
	private OutputStream os;
	public BTHandler(StreamConnection con) throws IOException {
		this.con=con;
		this.is=con.openInputStream();
		this.os=con.openOutputStream();
		this.send("hello client".getBytes());
	}
	@Override
	public void run() {
		while (listening){
			byte[] recv= new byte[1024];
        	try {
				if (is.read(recv)==-1){
					break;
				}
			this.recv(recv);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}
	
	public void recv(byte[] b){
		String s = new String(b,0,b.length);
    	System.out.println("recv: "+s);
	}
	public void send(byte[] b) throws IOException{
        os.write(b);
        os.flush();
	}

}
