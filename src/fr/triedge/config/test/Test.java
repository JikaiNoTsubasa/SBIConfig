package fr.triedge.config.test;

import java.io.IOException;

import fr.triedge.config.SBIConfig;

public class Test {

	public static void main(String[] args) {
		SBIConfig<String, String> c = new SBIConfig<String,String>("test/conf");
		c.add("fr.triedge.conf.thread.count", "5");
		try {
			c.saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
