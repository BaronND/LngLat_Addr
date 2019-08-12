package property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;

public class PropertyUtil {

//	private static final String DEFAULT_PROPERTY = "D:/1_projects/2_eclipse/HdshSpark2.1/src/" /* "conf/conf" */;
	private static final String DEFAULT_PROPERTY = "/home/hadoop" /* "conf/conf" */;

	private static final String PROPERTY_SUFIX = ".properties";

	private static final Map<String, PropertiesConfiguration> CONFIGURATIONS = new HashMap<String, PropertiesConfiguration>();

	private static PropertiesConfiguration getInstance(String name) {
		String fileName = getPropertyName(name);
		PropertiesConfiguration config = CONFIGURATIONS.get(fileName);
		if (config == null) {
			try {
				config = new PropertiesConfiguration(fileName);
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
				CONFIGURATIONS.put(fileName, config);
			} catch (ConfigurationException e) {
				throw new RuntimeException("cannot find property file for : " + name);
			}
		}
		return config;
	}

	public static String getProperties(String key, String string) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("inc.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props.getProperty(key);
	}
	public static String getProperties(String key) {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("inc.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props.getProperty(key);
	}
	
	private static String getPropertyName(String name) {
		if (StringUtils.isBlank(name)) {
			name = DEFAULT_PROPERTY;
		}
		return name.endsWith(PROPERTY_SUFIX) ? name : (name += PROPERTY_SUFIX);
	}

	public static PropertiesConfiguration getInstance() {
		return getInstance(null);
	}

	public static String getString(String key) {
		return getString(key, "inc.properties");
	}

	public static String getString(String key, String propertyInstance) {
		return getInstance(propertyInstance).getString(key);
	}

	public static int getInt(String key) {
		return getInt(key, DEFAULT_PROPERTY);
	}

	public static int getInt(String key, String propertyInstance) {
		String value = getInstance(propertyInstance).getString(key);
		return Integer.parseInt(value);
	}

	public static void main(String[] args) {
//		PropertyUtil test = new PropertyUtil();
//		System.out.print(test.getString("all_npmod"));
		// D:\1_projects\2_eclipse\HdshSpark2.1\src\table_name_config.properties
		System.out.print(PropertyUtil.getString("all_npmod","table_name_config"));
	}

}