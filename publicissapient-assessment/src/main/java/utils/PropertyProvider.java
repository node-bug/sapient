package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProvider {

	public Properties getPropertyFile() {
		InputStream input = null;
		try {
			input = new FileInputStream(new File("src/test/resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
