package com.GroutFit.Model;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ProfileTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(. class)
  .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void login() {
    }

    @Test
    public void saveSizes() {
    }

    @Test
    public void toString() {
    }
}
