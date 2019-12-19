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
package it.cnr.iasi.saks.groucho.lsh.jep;

import java.io.File;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import jep.SubInterpreter;

public class LSHInvivoJep implements StateObserver {

	private final Object lock = new Object();

	private Interpreter jepInterpreter;

	private static final String LSH_PY_SCRIPT = "py/lshinvivo.py";
	private static final String LSH_PY_SCRIPT_RESULT = "run_invivo";

	private static final String LSH_BUCKET_FILE = "bucket.data";
	private static final String LSH_COUNT_FILE = "count.data";

	public LSHInvivoJep() throws LSHException {
		this.attachJEP();
	}

	@Override
	public boolean isStateUnknown(String carvedState) throws LSHException {
		String[] params = { "foo-thisInRequiredBy-py/lshinvivo.py", carvedState };
		boolean runInvivoFlag = false;

		synchronized (lock) {
			try {
				this.configurePyArgv(params);

				this.jepInterpreter.runScript(LSH_PY_SCRIPT);

				runInvivoFlag = this.jepInterpreter.getValue(LSH_PY_SCRIPT_RESULT, Integer.class) != 0;
			} catch (JepException e) {
				LSHException lshEx = new LSHException(e.getMessage(), e.getCause());
				throw lshEx;
			}
		}

		// TODO Auto-generated method stub
		return runInvivoFlag;
	}

	@Override
	public void markState(String carvedState) throws LSHException {
		this.isStateUnknown(carvedState);
	}

	@Override
	public void resetStateObserver() throws LSHException {
		this.detachJEP();
				
		File f = new File(LSH_BUCKET_FILE);
		f.delete();
		f = new File(LSH_COUNT_FILE);
		f.delete();

		this.attachJEP();

	}

	private void attachJEP() throws LSHException {
		synchronized (lock) {
			try {
				this.jepInterpreter = new SubInterpreter();
//				this.jepInterpreter = new SharedInterpreter();
			} catch (JepException e) {
				LSHException lshEx = new LSHException(e.getMessage(), e.getCause());
				throw lshEx;
			}			
		}
	}

	public void detachJEP() throws LSHException {
		synchronized (lock) {
			try {
				if (this.jepInterpreter != null) {
					this.jepInterpreter.close();
					this.jepInterpreter = null;
				}
			} catch (JepException e) {
				LSHException lshEx = new LSHException(e.getMessage(), e.getCause());
				throw lshEx;
			}
		}
	}

	private void configurePyArgv(String params[]) throws JepException {
		String pythonArgvAsString = "[";

		for (String string : params) {
			String tmp = ",'" + string + "'";
			pythonArgvAsString += tmp;
		}
		pythonArgvAsString = pythonArgvAsString.replaceAll("\\[,'", "['");
		pythonArgvAsString += "]";

		this.jepInterpreter.eval("import sys");
		this.jepInterpreter.eval("sys.argv = " + pythonArgvAsString);
		this.jepInterpreter.eval("argv = " + pythonArgvAsString);
	}

}
