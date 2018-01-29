package org.arpit.java2blog.revListner;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.event.rule.BeforeMatchFiredEvent;

public class CustomAgendaEventListener extends DefaultAgendaEventListener {

	public void beforeMatchFired(BeforeMatchFiredEvent event) {
		//System.out.println("\nRules fired: \n" + event.getMatch().getRule().getName());
	}

}
