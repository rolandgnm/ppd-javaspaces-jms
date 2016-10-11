package br.com.ppdchat.model.entries;

import net.jini.core.entry.Entry;

import java.util.List;

/**
 * Created by Roland on 9/28/16.
 */

/**
 * Always use take()
 */

public class Room implements Entry {
    public String name;
    public List<String> onlineUsers;
    public List<String> awayUsers;

    public Room() {
    }

    public Room(String name){
        this.name = name;
    }
}
