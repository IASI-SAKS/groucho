package it.cnr.iasi.saks.groucho.callback;

public class GovernanceManagerFactory {

	private static GovernanceManagerFactory gmFactory = null;

	private GovernanceManagerFactory() {
	}

	public synchronized static GovernanceManagerFactory getInstance() {
		if (gmFactory == null) {
			gmFactory = new GovernanceManagerFactory();
		}
		return gmFactory;
	}

	public AbstractGovernanceManager getGovernanceManager() {
		return new SimpleGovernanceManager();
	}

}
