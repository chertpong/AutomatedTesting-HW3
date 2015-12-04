package camt.se494.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Homework1Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework1Application.class, args);
        DBManager dbManager = new DBManager();
        dbManager.start();
    }
}

class DBManager extends Thread {
    public void run() {
        System.setProperty("java.awt.headless", "false");
        org.hsqldb.util.DatabaseManagerSwing.main(new String[]{
                "--url", "jdbc:hsqldb:mem:testdb", "--noexit"
        });
    }
}
