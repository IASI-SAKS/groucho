package it.cnr.iasi.saks.groucho.callback;

public class ThreadHarnessCallbackInvoker {
	public static void invokeCallback_IncThread(){
		ThreadHarness callback = GovernanceManagerFactory.getInstance().getThreadHarness();
		callback.incThreads();
	}

	public static void invokeCallback_decThread(){
		ThreadHarness callback = GovernanceManagerFactory.getInstance().getThreadHarness();
		callback.decThreads();
	}

	public static void invokeCallback_checkInTheConstructors(){
		ThreadHarness callback = GovernanceManagerFactory.getInstance().getThreadHarness();
		try {
			callback.enableEnactmentInvivoTestingSession();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
