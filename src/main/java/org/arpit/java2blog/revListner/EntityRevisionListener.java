package org.arpit.java2blog.revListner;


import org.hibernate.envers.RevisionListener;

public class EntityRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        System.out.println("New revision is created: " + o);
    }
}
