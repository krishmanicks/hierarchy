package com.zoho.hierarchy.services;
import java.util.logging.Logger;

public class HierarchyWebApplication extends HierarchyApplication {
    private static final Logger LOG = Logger.getLogger(HierarchyWebApplication.class.getName());

    @Override
    protected void beforeInitialization() throws Exception {
        LOG.info(() -> "Starting Hierarchy Web AppGroup"); //No I18N
    }

    @Override
    public void onMessage(Object o) {
        LOG.info(() -> "Hierarchy cold start detected! All service level common spaces are going be registered now."); //No I18N
    }
}
