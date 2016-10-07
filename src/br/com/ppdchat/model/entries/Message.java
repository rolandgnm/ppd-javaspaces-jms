package br.com.ppdchat.model.entries;

import net.jini.core.entry.Entry;

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
}