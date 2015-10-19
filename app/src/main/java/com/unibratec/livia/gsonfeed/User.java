package com.unibratec.livia.gsonfeed;

import java.io.Serializable;

/**
 * Created by Livia on 18/10/2015.
 */
public class User implements Serializable {
    String id;
    String name;
    String username;

    public User(String id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
