package com.zoho.hierarchy.services;

import com.adventnet.mfw.message.MessageListener;
import com.zoho.conf.Configuration;

import java.util.logging.Logger;

public abstract class HierarchyApplication implements MessageListener {

    private static final Logger LOG = Logger.getLogger(HierarchyApplication.class.getName());
    private static final String GRID_TYPE = "sas.grid.type"; //NO I18N
    private static final int GRID_TYPE_MASTER = 1;

    public static void initialize() {
        LOG.info(() -> "Initializing Hierarchy application"); // No I18N
    }

    protected HierarchyApplication() {
    }

    protected abstract void beforeInitialization() throws Exception;

    void begin() throws Exception {
        try {
            beforeInitialization();
            initializeApplication();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void initializeApplication() throws Exception {

    }
}
