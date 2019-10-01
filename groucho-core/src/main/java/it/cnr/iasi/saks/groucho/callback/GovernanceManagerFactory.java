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
package it.cnr.iasi.saks.groucho.callback;

import it.cnr.iasi.saks.groucho.config.PropertyUtil;

public class GovernanceManagerFactory {
	
	private static GovernanceManagerFactory gmFactory = null;

	private static AbstractGovernanceManager gm = null;

	private GovernanceManagerFactory() {
		String gmClassName = PropertyUtil.getInstance().getProperty(PropertyUtil.GOVERNANCE_MANAGER_CLASS_LABEL);
		if ((gmClassName == null) || (gmClassName.isEmpty())){
			gm = new SimpleGovernanceManager();			
		}else{
			Class<?> gmClass;
			try {
//				gmClass = Class.forName(gmClassName);
				gmClass = Class.forName(gmClassName, true, ClassLoader.getSystemClassLoader());
				gm = (AbstractGovernanceManager) gmClass.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				gm = new SimpleGovernanceManager();			
			}
		}
	}

	public synchronized static GovernanceManagerFactory getInstance() {
		if (gmFactory == null) {
			gmFactory = new GovernanceManagerFactory();
		}
		return gmFactory;
	}

	public AbstractGovernanceManager getGovernanceManager() {
		return gm;
	}

	public ThreadHarness getThreadHarness() {
		return gm;
	}
}
