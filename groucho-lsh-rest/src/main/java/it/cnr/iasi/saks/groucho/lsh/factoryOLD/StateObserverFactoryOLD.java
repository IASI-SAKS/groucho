package it.cnr.iasi.saks.groucho.lsh.factoryOLD;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.config.StateObserverConfig;
import it.cnr.iasi.saks.groucho.lsh.util.StateObserverLSH_InsideUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StateObserverFactoryOLD {
    ApplicationContext appctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    ApplicationContext ctx = new AnnotationConfigApplicationContext(StateObserverConfig.class);

    public StateObserver getStateObserver() {
        return ctx.getBean(StateObserver.class);
    }

    public StateObserver getStateObserverLSH() {
        return (StateObserverLSH_InsideUtil) appctx.getBean("stateObserverLSH");
    }
}
