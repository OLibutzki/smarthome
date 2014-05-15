/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschränkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.model.script.runtime.internal;
import org.eclipse.smarthome.model.script.ScriptStandaloneSetup;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;


public class ScriptRuntimeActivator implements BundleActivator {

	private final static Logger logger = LoggerFactory.getLogger(ScriptRuntimeActivator.class);

	private static ScriptRuntimeActivator INSTANCE;
	
	private Injector injector;
	
	public void start(BundleContext context) throws Exception {
		INSTANCE = this;	
		getInjector();
	}

	public void stop(BundleContext context) throws Exception {
		injector = null;
		INSTANCE = null;
	}
	
	public static ScriptRuntimeActivator getInstance() {
		return INSTANCE;
	}
	
	public Injector getInjector() {
		synchronized (this) {
			if (injector == null) {
				injector = createInjector();
			}
			return injector;
		}
	}	
	
	protected Injector createInjector() {
		Injector injector = new ScriptStandaloneSetup().createInjectorAndDoEMFRegistration();
		logger.debug("Registered 'script' configuration parser");
		return injector;
	}

}