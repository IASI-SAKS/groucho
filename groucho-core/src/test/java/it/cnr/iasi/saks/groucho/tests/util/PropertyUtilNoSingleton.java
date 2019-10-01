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
package it.cnr.iasi.saks.groucho.tests.util;

import it.cnr.iasi.saks.groucho.config.PropertyUtil;

public class PropertyUtilNoSingleton extends PropertyUtil {

	protected PropertyUtilNoSingleton(){		
	}

//	@Override
	public synchronized static PropertyUtilNoSingleton getInstance(){
		PropertyUtilNoSingleton p = new PropertyUtilNoSingleton();
		INSTANCE = p;
		
		return p;
	}
	
	public void setProperty(String key, String value) {
		super.setProperty(key, value);
	}
}
