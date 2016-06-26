package com.movieland.util;

import com.movieland.cache.CacheService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Service
public class CacheJMXControl implements CacheJMXControlMBean, InitializingBean {

    @Autowired
    private CacheService cacheService;

    @Override
    public void invalidateAllCaches() {
        cacheService.invalidateAllCaches();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String objectName = this.getClass().getPackage().getName() + ":type=" + this.getClass().getSimpleName();
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName mbeanName = new ObjectName(objectName);
        server.registerMBean(this, mbeanName);
    }
}
