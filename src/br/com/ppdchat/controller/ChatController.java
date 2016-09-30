package br.com.ppdchat.controller;

import br.com.ppdchat.service.JavaSpaceService;
import br.com.ppdchat.service.JmsService;
import br.com.ppdchat.utils.Utils;
import br.com.ppdchat.view.ChatView;

import javax.rmi.CORBA.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Roland on 9/28/16.
 */
public class ChatController {
    private JmsService jms;
    String currentRoom;
    private ChatView view;
    private JavaSpaceService space;

    public ChatController() {
        view = new ChatView(new CommandMenuListener(), new InputListener());

        try {
            space = new JavaSpaceService();
            jms = new JmsService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.displayDialog(view, e.getMessage());
        }


        view.setVisible();

    }

    private class CommandMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO Handle Actions properly
        }
    }

    private class InputListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO Handle Actions properly
            view.putTextToConsole(view.getInputText());
            //TODO Teste
            view.setRoomName(view.getInputText());
            view.clearInput();
        }
    }
}
