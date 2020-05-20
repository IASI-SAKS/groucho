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
package it.cnr.iasi.saks.grouchoTestingStuff;

import java.util.List;

import it.cnr.iasi.saks.groucho.callback.AbstractGovernanceManager;
import it.cnr.iasi.saks.groucho.callback.GovernanceManagerFactory;
import it.cnr.iasi.saks.groucho.common.Context;

public class SimpleContextTestDriverInvivoTest {
	
	public void validateContextInvivoTest(Context c){
		AbstractGovernanceManager gm = GovernanceManagerFactory.getInstance().getGovernanceManager();
		String gmClassName = gm.getClass().getCanonicalName();
		String refs = "";
		for (Object o : c.getOtherReferencesInContext()) {
			refs = refs + o.toString()+"; ";
		}
		System.out.println("This Invivo Test will reports the classname of the linked Governance Manager: " + gmClassName + "and the actual parameters: <"+refs+">, but it also validate if the Context is correctely received!");
		
		SimpleContextTestDriver obj = (SimpleContextTestDriver) c.getInstrumentedObject();
		List<Object> lst = c.getOtherReferencesInContext();

		int i = (int) lst.get(0);
		boolean b = (boolean) lst.get(1);
		float f = (float) lst.get(2);
		long l = (long) lst.get(3);
		double d = (double) lst.get(4);
		char car = (char) lst.get(5);
		byte b1 = (byte) lst.get(6);
		short s = (short) lst.get(7);
		SimpleClass mySimpleObject = (SimpleClass) lst.get(8);
		
		obj.validateParams(i, b, f, l, d, car, b1, s, mySimpleObject);		
	}
}
