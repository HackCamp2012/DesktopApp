package de.uulm.presenter.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

import de.uulm.presenter.exceptions.ServerAlreadyStartedException;


public class BTServer implements RemoteHCIService, Runnable{
	
	private boolean active=true;
	private StreamConnection con;
	
	private ArrayBlockingQueue<Runnable> queue;
	private ThreadPoolExecutor threadPool; 
	private boolean alreadyStarted = false;
	public void init() throws IOException, ServerAlreadyStartedException{
		if (alreadyStarted){
			throw new ServerAlreadyStartedException();
		}
		queue = new ArrayBlockingQueue<Runnable>(10);
		threadPool = new ThreadPoolExecutor(1,1,10,TimeUnit.SECONDS,queue);
		LocalDevice.getLocalDevice().setDiscoverable(DiscoveryAgent.GIAC);
		alreadyStarted=true;
		
	}
	
	public void listen(){
		
		Thread listenThread = new Thread(this);
		listenThread.start();
	}
	
	public void stop(){
		threadPool.shutdown();
	}

	@Override
	public void run() {
		StreamConnectionNotifier service;
		try {
			service = (StreamConnectionNotifier) Connector.open( "btspp://localhost:" + SERVICEUUID + ";name="+NAME );
			while (active){
				try {
					con = (StreamConnection) service.acceptAndOpen();
					System.out.println("Connection Received");
					threadPool.execute(new BTHandler(con));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
}
