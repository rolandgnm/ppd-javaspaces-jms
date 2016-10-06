package br.com.ppdchat.controller;

import br.com.ppdchat.service.JavaSpaceService;
import br.com.ppdchat.service.JmsService;
import br.com.ppdchat.utils.Utils;
import br.com.ppdchat.view.ChatView;
import br.com.ppdchat.view.NicknameDialogView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Roland on 9/28/16.
 */
public class ChatController {
    private JmsService jms;
    String currentRoom;
    private ChatView chatView;
    private NicknameDialogView nickChoiceView;
    private JavaSpaceService space;

    public ChatController() {

        try {
            space = new JavaSpaceService();
            jms = new JmsService();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Utils.displayDialog(chatView, "Não foi possível conectar aos serviços de comunicação!");
            System.exit(0);
        }



        nickChoiceView = new NicknameDialogView(new NicknameDialogListener());
        nickChoiceView.setVisible(true);


//        startChatView();
    }

    private void startChatView() {
        chatView = new ChatView(new CommandMenuListener(), new InputListener());
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
            //TODO Handle Actions properly
            chatView.putTextToConsole(chatView.getInputText());
            //TODO Teste
            chatView.setRoomName(chatView.getInputText());
            chatView.clearInput();
        }
    }

    private class NicknameDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nickname = nickChoiceView.getNickname();

            if(space.setNickname(nickname)){

            } else {
                Utils.displayDialog(nickChoiceView, "Nickname já existe!");
            }



        }
    }
}
