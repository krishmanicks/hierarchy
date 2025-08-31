package com.zoho.hierarchy.services;

import com.adventnet.mfw.message.Messenger;
import com.adventnet.mfw.service.Service;
import com.adventnet.persistence.DataObject;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public class HierarchyService implements Service {
    private static final Logger LOG = Logger.getLogger(HierarchyService.class.getName());

    @Override
    public void create(DataObject dataObject) throws Exception {

    }

    @Override
    public void start() throws Exception {
        LOG.info(() -> "Starting <Hierarchy> service"); //No I18N

        ServiceLoader<HierarchyApplication> serviceLoader = ServiceLoader.load(HierarchyApplication.class);
        Iterator<HierarchyApplication> serviceIterator = serviceLoader.iterator();
        if (!serviceIterator.hasNext()) {
            // No implementations found!
            // This shouldn't ever happen, however, if it does,
            // it is most probably be due to our app build packaging error.
            throw new Exception("No implementation of " + //No I18N
                    HierarchyApplication.class.getCanonicalName() + " found in the classpath"); //No I18N
        }

        // use the first provider found
        HierarchyApplication application = serviceIterator.next();

        // subscribe for cold start dataspace creation
        Messenger.subscribe("startupNotification", application, true, null); //No I18N

        application.begin();
    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }
}
