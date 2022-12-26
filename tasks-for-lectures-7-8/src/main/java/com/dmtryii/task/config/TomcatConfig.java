package com.dmtryii.task.config;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class TomcatConfig {
    public static void init() {
        Tomcat tomcat = new Tomcat();

        setPort(tomcat);
        setContext(tomcat);

        tomcat.enableNaming();
        tomcat.getConnector();

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }

        tomcat.getServer().await();
    }

    private static void setContext(Tomcat tomcat) {
        StandardContext standardContext = (StandardContext) tomcat.addWebapp("", new File("src/main/webapp/")
                .getAbsolutePath());
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(standardContext);
        resources.addPreResources(new DirResourceSet(resources, "src/main/webapp/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        standardContext.setResources(resources);
    }

    private static void setPort(Tomcat tomcat) {
        String port = System.getenv("PORT");

        if(port == null || port.isEmpty()) port = "8080";

        tomcat.setPort(Integer.valueOf(port));
    }
}
