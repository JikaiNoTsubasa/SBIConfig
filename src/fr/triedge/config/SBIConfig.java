package fr.triedge.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class SBIConfig<K,V> {

	private File configPath;
	private HashMap<K, V> map = new HashMap<>();
	
	public SBIConfig(File configPath) {
		super();
		this.configPath = configPath;
	}
	
	public SBIConfig(String configPath) {
		this(new File(configPath));
	}
	
	public void add(K key, V value) {
		map.put(key, value);
	}
	
	public V get(K key) {
		return map.get(key);
	}
	
	public void saveConfig() throws IOException {
		if (getConfigPath() == null)
			throw new IOException("Config path is null");
		FileWriter w = new FileWriter(getConfigPath());
		for (Entry<K, V> e : map.entrySet()) {
			w.write(e.getKey()+"="+e.getValue()+"\r\n");
		}
		w.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadConfig() throws IOException {
		if (getConfigPath() == null)
			throw new IOException("Config path is null");
		Scanner scan = new Scanner(getConfigPath());
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line != null && line != "") {
				String key = line.split("=")[0];
				String val = line.split("=")[1];
				map.put((K)key, (V)val);
			}
		}
		scan.close();
	}

	public File getConfigPath() {
		return configPath;
	}

	public void setConfigPath(File configPath) {
		this.configPath = configPath;
	}
}
