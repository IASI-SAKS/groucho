package it.cnr.iasi.saks.groucho.lsh.config;

import it.cnr.iasi.saks.groucho.lsh.StateObserver;
import it.cnr.iasi.saks.groucho.lsh.exceptions.LSHException;
import it.cnr.iasi.saks.groucho.lsh.jep.LSHInvivoJep;
import it.cnr.iasi.saks.groucho.lsh.util.StateObserverLSH;
import org.springframework.context.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Configuration
public class StateObserverConfig {
    @Bean
    public StateObserver stateObserver() throws LSHException {
        return new LSHInvivoJep();
    }

    @Bean
    public StateObserverLSH stateObserverLSH() throws LSHException {
        throw new NotImplementedException();
//        return new LSHInvivoJep();
    }
}
