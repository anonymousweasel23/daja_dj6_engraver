package com.engraver.ui;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;

public class PropertiesHelper {
	
	public static final String DEFAULT_PORT = "port.default";
	public static final String LAST_OPENED_DIR = "path.last.opened";
	public static final String LAST_SAVED_DIR = "path.last.saved";

	private final ReentrantLock lock = new ReentrantLock();
	private Properties props = null;
	private String appConfigPath = null;

	public Properties get() throws IOException {
		
		try {
			lock.lock();
			if (props == null) {
				String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
				appConfigPath = rootPath + "app.properties";
				props = new Properties();
				
				Path pp = Paths.get(appConfigPath);
				if (Files.notExists(pp)) {
					Files.createFile(pp);
					props.load(new FileInputStream(appConfigPath));
					save();
				} else
					props.load(new FileInputStream(appConfigPath));
			}
			return props;
		} finally {
			lock.unlock();
		}
	}
	
	public boolean save() throws IOException {
		try {
			lock.lock();
			if (props == null)
				throw new IllegalStateException("");
			props.store(new FileWriter(appConfigPath), "store to properties file");
			return true;
		} finally {
			lock.unlock();
		}
	}
	
	public String getProperty(final String propertyKey) {
		try {
			return get().getProperty(propertyKey);
		} catch (IOException e) {
			return null;
		}
	}

	public boolean setProperty(final String key, final String value) {
		if (key == null || value == null)
			throw new IllegalArgumentException();
		try {
			get().setProperty(key,value);
			save();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean clearProperty(final String propertyKey) {
		try {
			get().remove(propertyKey);
			save();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
