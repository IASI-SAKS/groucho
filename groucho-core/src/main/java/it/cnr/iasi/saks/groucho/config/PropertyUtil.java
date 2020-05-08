/* 
 * This file is part of the GROUCHO project.
 * 
 * GROUCHO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GROUCHO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GROUCHO.  If not, see <https://www.gnu.org/licenses/>
 *
 */
package it.cnr.iasi.saks.groucho.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyUtil {

	public static final String GOVERNANCE_MANAGER_CLASS_LABEL = "groucho.governance.manager.class";
	public static final String CONFIG_FILE_LOCATION_LABEL = "groucho.config.file.location";
	public static final String TRANFORMER_DISABLED_ON_CLASSES_LABEL = "groucho.transformer.disable.classesList";
	
	public static final String LAB_INSTRUMENT_MODEL_JSON_FILE_LABEL = "groucho.lab.intrument.jsonFile";

	private static final String CLASS_LIST_SEPARATOR = ",";

	protected static PropertyUtil INSTANCE = null;

	private Properties properties;

	protected PropertyUtil() {
		this.properties = new Properties();
		try {
			String configFileLocation = System.getProperty(CONFIG_FILE_LOCATION_LABEL);
			if (configFileLocation != null){
////				ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//				ClassLoader classloader = ClassLoader.getSystemClassLoader();
//				InputStream s = classloader.getResourceAsStream(configFileLocation);
				InputStream s = new FileInputStream(configFileLocation);
				this.properties.load(s);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static PropertyUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PropertyUtil();
		}
		return INSTANCE;
	}

	public String getProperty(String key) {
		return this.properties.getProperty(key);
	}

	public String getProperty(String key, String defalutValue) {
		return this.properties.getProperty(key, defalutValue);
	}

	public List<String> getClassesToExcludeDuringTransformation() {		
		List<String> result = new ArrayList<String>();
		
		String csvList = this.properties.getProperty(TRANFORMER_DISABLED_ON_CLASSES_LABEL);
		
		if ((csvList != null) && (!csvList.isEmpty())){
			String bbb = csvList.replaceAll("\\s","");
			String[] lst = bbb.split(CLASS_LIST_SEPARATOR);
			if (lst != null){
				for (int i = 0; i < lst.length; i++) {
					String tmp = lst[i].replace('.', '/') + "/"; 
					result.add(tmp);
				}
			}
		}	
		return result;
	}

	protected void setProperty(String key, String value) {
		this.properties.setProperty(key, value);
	}

}
