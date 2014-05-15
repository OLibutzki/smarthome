/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschränkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.model.persistence.runtime.internal;

import org.eclipse.smarthome.model.persistence.PersistenceStandaloneSetup;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;


public class PersistenceRuntimeActivator implements BundleActivator {

	private final static Logger logger = LoggerFactory.getLogger(PersistenceRuntimeActivator.class);

	private static PersistenceRuntimeActivator INSTANCE;
	
	private Injector injector;
	
	public void start(BundleContext context) throws Exception {
		INSTANCE = this;	
		getInjector();
	}

	public void stop(BundleContext context) throws Exception {
		injector = null;
		INSTANCE = null;
	}
	
	public static PersistenceRuntimeActivator getInstance() {
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
		Injector injector = new PersistenceStandaloneSetup().createInjectorAndDoEMFRegistration();
		logger.debug("Registered 'persistence' configuration parser");
		return injector;
	}
}