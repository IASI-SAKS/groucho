package it.cnr.iasi.saks.groucho.callback;

public class GovernanceManagerFactory {

	private static GovernanceManagerFactory gmFactory = null;

	private static AbstractGovernanceManager gm = null;

	private GovernanceManagerFactory() {
		gm = new SimpleGovernanceManager();
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
