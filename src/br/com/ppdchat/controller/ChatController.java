package br.com.ppdchat.controller;

import br.com.ppdchat.view.ChatView;

/**
 * Created by Roland on 9/28/16.
 */
public class ChatController {
    String currentRoom;
    ChatView view;

    public ChatController() {
        view = new ChatView();
        view.setVisible();


    }
}
