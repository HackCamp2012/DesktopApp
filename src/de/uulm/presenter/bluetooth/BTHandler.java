package de.uulm.presenter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.StreamConnection;

import de.uulm.presenter.io.IODevice;
import de.uulm.presenter.io.IODeviceImpl;
import de.uulm.presenter.io.IORemote;
import de.uulm.presenter.io.IORemoteImpl;

public class BTHandler implements Runnable{
	private StreamConnection con;
	private boolean listening=true;
	private InputStream is;
	private OutputStream os;
	public BTHandler(){
		
		
	}
	public void init(StreamConnection con) throws IOException {
		this.con=con;
		this.is=con.openInputStream();
		this.os=con.openOutputStream();
		
	}
	
	@Override
	public void run() {
		init();
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
		
		shutdown();
		
	}
	
	public void init(){
		
	}
	
	public void shutdown(){
		
	}
	
	public void recv(byte[] b){
		System.out.println("rev1");
	}

	public void send(byte[] b){
        try {
			os.write(b);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}

	
	
}
