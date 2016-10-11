package br.com.ppdchat.model.entries;

import net.jini.core.entry.Entry;

import java.rmi.MarshalException;
import java.util.List;

/**
 * Created by Roland on 9/28/16.
 */

/**
 * Always use take()
 */

public class Message implements Entry {
    public String room;
    public String receiver;
    public String sender;
    public String message;
    public Boolean privacy;

    public Message() {
    }

    public Message(String room, String receiver, String sender, String message, Boolean privacy) {
        this.room = room;
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
        this.privacy = privacy;
    }
}