package br.com.ppdchat.controller;

import br.com.ppdchat.model.Commands;
import br.com.ppdchat.service.JavaSpaceService;
import br.com.ppdchat.service.JmsService;
import br.com.ppdchat.utils.Utils;
import br.com.ppdchat.view.ChatView;
import br.com.ppdchat.view.UsernameDialogView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Roland on 9/28/16.
 */
public class ChatController {
    private static String INSTANCE_USERNAME;
    private static String CURRENT_ROOM;
    private JmsService jms;
    String currentRoom;
    private ChatView chatView;
    private UsernameDialogView usernameView;
    private JavaSpaceService space;

    public ChatController() {

        try {
            space = new JavaSpaceService();
            jms = new JmsService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.displayDialog(chatView, "Não foi possível conectar aos serviços de comunicação!");
            System.exit(-1);
        }


        usernameView = new UsernameDialogView(new UsernameDialogListener());
        usernameView.setVisible(true);


//        startChatView();
    }

    private void startChatView(String name) {
        chatView = new ChatView(new CommandMenuListener(), new InputListener(), name);
        chatView.setVisible();
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
            String input = chatView.getInputText();

            if(input.isEmpty())
                return;

            if(input.charAt(0) == Commands.SYMBOL)
//todo handle commad
            chatView.putTextToConsole(input);
            //TODO Teste
            chatView.setRoomName(input);
            chatView.clearInput();
        }
    }

    private class UsernameDialogListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setUsername();
        }

        private void setUsername() {
            String username = usernameView.getUsername();

            if (username.isEmpty()) {
                Utils.displayDialog(usernameView, "Insira um nome!");
                return;
            } else if ((username.split(" ").length > 1)) {
                Utils.displayDialog(usernameView, "Nome deve conter apenas uma palavra!");
                return;
            }

            try {
                space.setUsername(username);
            } catch (Exception e1) {
                Utils.displayDialog(usernameView, e1.getMessage());
                e1.printStackTrace();
                return;
            }

            INSTANCE_USERNAME = username;
            startChatView(username);

        }

    }

}
