package org.example.DZ3;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Runner {
    public static void main(String[] args) throws InterruptedException {
        User user = new User("Yurii", "testme");
        User user1 = new User("Petr", "123456");
        Post post = new Post("Hello world!", user);
        Post post2 = new Post("armageddon comes", user1);
        InstDao instDao = new InstDao();
        instDao.addUser(user, user1);
        instDao.addPost(post, post2);
        instDao.addComment("The job is done", post2, user);
    }
}

