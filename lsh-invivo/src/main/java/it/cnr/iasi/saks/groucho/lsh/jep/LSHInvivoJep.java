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

	private static final Object LOCK = new Object();

	private static Interpreter JEP_INTERPRETER = null;

	private static final String LSH_PY_SCRIPT = "py/lshinvivo.py";
	private static final String LSH_PY_SCRIPT_RESULT = "run_invivo";

	private static final String LSH_BUCKET_FILE = "bucket.data";
	private static final String LSH_COUNT_FILE = "count.data";

	public LSHInvivoJep() throws LSHException {
		this.attachJEP();
	}

	@Override
	public boolean isStateUnknown(String carvedState) throws LSHException {
		// Note that the parameter "foo-thisInRequiredBy-py/lshinvivo.py" is useless
		// it is introduced here only because the current implementation of
		// the python script: "lshinvivo.py" the first parameters is counted
		// as the name of the script when invoked by CLI. Thus it became useless 
		// in the current setup with JEP 
		String[] params = { "foo-thisInRequiredBy-py/lshinvivo.py", carvedState };
		boolean runInvivoFlag = false;

		synchronized (LOCK) {
			try {
				this.configurePyArgv(params);

				JEP_INTERPRETER.runScript(LSH_PY_SCRIPT);

				runInvivoFlag = JEP_INTERPRETER.getValue(LSH_PY_SCRIPT_RESULT, Integer.class) != 0;
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
		synchronized (LOCK) {
			try {
				if (JEP_INTERPRETER == null) {
//				this.jepInterpreter = new SubInterpreter();
					JEP_INTERPRETER = new SharedInterpreter();
					JEP_INTERPRETER.eval("import sys");
				}
			} catch (JepException e) {
				LSHException lshEx = new LSHException(e.getMessage(), e.getCause());
				throw lshEx;
			}			
		}
	}

	public void detachJEP() throws LSHException {
		synchronized (LOCK) {
			try {
				if (JEP_INTERPRETER != null) {
					JEP_INTERPRETER.close();
					JEP_INTERPRETER = null;
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

		JEP_INTERPRETER.eval("sys.argv = " + pythonArgvAsString);
		JEP_INTERPRETER.eval("argv = " + pythonArgvAsString);
	}

}
