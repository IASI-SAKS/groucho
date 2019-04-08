package it.cnr.iasi.saks.groucho.common;

public class StateCarverFactory {

	private static StateCarver CARVER = null;
	
	public static final String STATE_CARVER_CLASS_PROPERTY_LABEL = "STATE_CARVER_CLASS";
	
	protected StateCarverFactory(){
		
	}
	
	private static void retrieveUserDefinedStateCarver() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		String scClassName = System.getProperty(STATE_CARVER_CLASS_PROPERTY_LABEL);
		if (scClassName != null){
			Class<?> clazz = Class.forName(scClassName, false, ClassLoader.getSystemClassLoader());
			CARVER = (StateCarver) clazz.newInstance();
		}
	}
	public static synchronized StateCarver getStateCarver(){
		if (CARVER == null){
			try {
				retrieveUserDefinedStateCarver();
				CARVER = new DefaultStateCarver(context)
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return CARVER;
		
	}
}
