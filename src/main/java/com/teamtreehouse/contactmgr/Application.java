package com.teamtreehouse.contactmgr;

import com.teamtreehouse.contactmgr.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Application {
    // Hold a reusable reference to a SessionFactory since we need only one
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        // Create a standardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("Michel","Stroobants")
                .withEmail("micheljr.stroobants@gmail.com")
                .withPhone(32475233993L)
                .build();
        // Open a session
        Session session = sessionFactory.openSession();
        // Begin a transaction
        session.beginTransaction();
        // Use the session to save the contact
        session.save(contact);
        // Commit the transaction
        session.getTransaction().commit();
        // Close session
        session.close();
    }
}
