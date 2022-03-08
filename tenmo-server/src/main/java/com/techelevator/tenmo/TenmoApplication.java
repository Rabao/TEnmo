package com.techelevator.tenmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the server class.
 *
 * @author Techelevator
 *
 */

@SpringBootApplication
public class TenmoApplication {

    /**
     * This main method uses the SpringApplication class to bootstrap and launch the Application.
     *
     * @param args Contains the supplied command-line arguments as an array of String objects.
     *
     */

    public static void main(String[] args) {
        SpringApplication.run(TenmoApplication.class, args);
    }

}
