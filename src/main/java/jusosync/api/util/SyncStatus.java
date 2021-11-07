package jusosync.api.util;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SyncStatus {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${syncPropPath}")
	private String propPath;
	
	private Properties loadProperties() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(propPath);
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(is);
		}
		
		return prop;
	}
	
	public String getSyncDate(String sysId) {
		Properties prop = loadProperties();
		
		String value = prop.getProperty(sysId);
		
		if(value == null) {
			value = "";
		}
		
		return value;
	}
	
	public void setSyncDate(String sysId, String date) {
		Properties prop = loadProperties();
		
		OutputStream os = null;
		prop.setProperty(sysId, date);
		
		try {
			os = new FileOutputStream(propPath);
			prop.store(os, null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(os);
		}
	}
	
	private static void close(Closeable c) {
		if(c == null) return;
		
		try {
			c.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
