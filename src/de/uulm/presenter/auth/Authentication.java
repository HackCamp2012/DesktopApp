package de.uulm.presenter.auth;

import java.util.Random;

public class Authentication {
	public static int generateChallenge(){
		Random rnd = new Random();
		return rnd.nextInt(8999999)+1000000;
	}
}


