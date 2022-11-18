package org.example.DZ3;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;


public class InstDao implements Closeable {
    private SessionFactory sessionFactory;
    private Session session;

    public InstDao() {
        try {
            sessionFactory = HibernateConfig.createSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String name, String password) {
        session.beginTransaction();
        User user = new User(name, password);
        session.save(user);
        session.getTransaction().commit();
    }

    public void addUser(User... users) {
        session.beginTransaction();
        Arrays.stream(users).forEach(user -> session.saveOrUpdate(user));
        session.getTransaction().commit();
    }


    public void addPost(String text, User user) {
        session.beginTransaction();
        Post post = new Post(text, user);
        session.saveOrUpdate(post);
        session.getTransaction().commit();

    }

    public void addPost(Post... posts) {
        session.beginTransaction();
        Arrays.stream(posts).forEach(post -> session.saveOrUpdate(post));
        session.getTransaction().commit();

    }

    public void addComment(String text, Post post, User user) {
        session.beginTransaction();
        Comment comment = new Comment(text, post, user);
        session.saveOrUpdate(comment);
        session.getTransaction().commit();
        session.close();
    }

    public void addPost(Comment... comments) {
        session.clear();
        session.beginTransaction();
        Arrays.stream(comments).forEach(comment -> session.saveOrUpdate(comment));
        session.getTransaction().commit();
    }


        @Override
        public void close () throws IOException {
            if (session.isOpen()) {
                session.close();
            }
            if (!sessionFactory.isClosed()) {
                sessionFactory.close();
            }
        }
}
