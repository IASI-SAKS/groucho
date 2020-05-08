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
package it.cnr.iasi.saks.groucho.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.util.Iterator;
import java.util.List;

import it.cnr.iasi.saks.groucho.config.PropertyUtil;

public abstract class AbstractClassTranformer implements ClassFileTransformer {

	protected boolean isDisabled(String className) {
		return (this.isClassNameIgnoredByCrochet(className) || this.isLocallyIgnored(className));
	}

	protected boolean isLocallyIgnored(String className) {
		boolean exitus = className.startsWith("java/");
		exitus = exitus || className.startsWith("sun/");
		exitus = exitus || className.startsWith("it/cnr/iasi/saks/groucho/");
		exitus = exitus || className.startsWith("org/junit/");
		exitus = exitus || className.startsWith("junit/framework");
		exitus = exitus || className.startsWith("org/apache/maven/");
		exitus = exitus || className.startsWith("org/eclipse/jdt/internal/junit/");
		
		if (!exitus) {
			List<String> excludeClassesList = PropertyUtil.getInstance().getClassesToExcludeDuringTransformation();
			for (Iterator<String> iterator = excludeClassesList.iterator(); iterator.hasNext() && !exitus;) {
				String classToExclude = (String) iterator.next();
				exitus = exitus || className.startsWith(classToExclude);

			}
		}
		return exitus;
	}

	protected boolean isClassNameIgnoredByCrochet(String className) {
		 boolean exitus = net.jonbell.crij.instrument.Instrumenter.isIgnoredClass(className);
		 exitus = exitus || net.jonbell.crij.instrument.Instrumenter.isIgnoredClassWithDummyMethods(className);
		 return exitus;
	}
	
}
