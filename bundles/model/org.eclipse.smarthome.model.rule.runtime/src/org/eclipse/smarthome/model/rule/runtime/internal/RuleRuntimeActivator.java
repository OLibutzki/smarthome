package org.eclipse.smarthome.model.rule.runtime.internal;

import org.eclipse.smarthome.model.rule.RulesStandaloneSetup;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleRuntimeActivator implements BundleActivator {

	private final static Logger logger = LoggerFactory.getLogger(RuleRuntimeActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		RulesStandaloneSetup.doSetup();
		logger.debug("Registered 'rule' configuration parser");	
	}

	@Override
	public void stop(BundleContext context) throws Exception {

	}

}
