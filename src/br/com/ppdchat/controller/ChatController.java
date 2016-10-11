package br.com.ppdchat.controller;

import br.com.ppdchat.model.Commands;
import br.com.ppdchat.model.Times;
import br.com.ppdchat.model.entries.Message;
import br.com.ppdchat.model.entries.Room;
import br.com.ppdchat.model.entries.RoomList;
import br.com.ppdchat.service.JavaSpaceService;
import br.com.ppdchat.service.JmsService;
import br.com.ppdchat.utils.Utils;
import br.com.ppdchat.view.ChatView;
import br.com.ppdchat.view.UsernameDialogView;
import net.jini.core.entry.Entry;


import javax.rmi.CORBA.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Roland on 9/28/16.
 */
public class ChatController {
    private static String INSTANCE_USERNAME;
    private String CURRENT_ROOM;
    private JmsService jms;
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
        usernameView.setVisible(false);
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

            if (input.isEmpty())
                return;

            if (input.charAt(0) == Commands.SYMBOL) {
                handleCommand(input);
            } else {
//                handleMessage(input);
            }

            //TODO Teste
//            chatView.putTextToConsole(input);
//            chatView.setRoomName(input);
//            chatView.clearInput();
        }
    }

    private void handleCommand(String input) {
        String[] split = input.split(" ");

        String inCommand = split[0];
        Commands comm;

        try {
            comm = Commands.fromString(inCommand);
        } catch (Exception e) {
            Utils.displayDialog(chatView, e.getMessage());
            return;
        }



        switch (comm) {
            case HELP:
                printHelp();
                break;
            case JOIN_ROOM:
                joinRoom(split[1]);
                break;
            case QUIT_ROOM:
                quitRoom();
                break;
            case ROOM_LIST:
                printRoomList();
                break;
            case USER_IN_ROOM:
                printUserList();
                break;
            case PRIVATE_MESSAGE:
                List<String> splitList = Arrays.asList(split);
                splitList.remove(0);
                splitList.remove(0);
                String msg = String.join(" ", splitList);
                sendPrivateMessage(split[1], msg);
        }

    }

    private void sendPrivateMessage(String receiver, String msg) {
        Room room = null;
        try {
            room = (Room) space.takeEntryByTemplate(new Room(CURRENT_ROOM));
        } catch (Exception e) {
            Utils.displayDialog(chatView, "Algo errado ao retirar do espaco a tupla desta sala!");
            e.printStackTrace();
        }

        if(!room.onlineUsers.contains(receiver) && !room.awayUsers.contains(receiver)) {
            Utils.displayDialog(chatView, "Nao existe usuario com este nome na sala!");
            return;
        }

        try {
            space.write(room, Long.MAX_VALUE);
            space.write(new Message(CURRENT_ROOM, receiver, INSTANCE_USERNAME, msg, true), Times.MESSAGE_EXPIRATION.getTime());
        } catch (Exception e) {
            //todo tratar exception
            e.printStackTrace();
        }


    }

    private void quitRoom() {
        long timeout;

        Room room = null;
        try {
            room = (Room) space.takeEntryByTemplate(new Room(CURRENT_ROOM));
        } catch (Exception e) {
            Utils.displayDialog(chatView, "Algo errado ao retirar do espaco a tupla desta sala!");
            e.printStackTrace();
        }

        room.onlineUsers.remove(INSTANCE_USERNAME);
        room.awayUsers.add(INSTANCE_USERNAME);

        if(room.onlineUsers.size() == 0)
            timeout = Times.ROOM_EXPIRATION.getTime();
        else
            timeout = Long.MAX_VALUE;

        try {
            space.write(room, timeout);
        } catch (Exception e) {
            Utils.displayDialog(chatView, "Problema ao devolver tupla da Sala para o espaco");
            e.printStackTrace();
        }

        CURRENT_ROOM = null;
    }

    private void printRoomList() {

        try {
            RoomList roomList = (RoomList) space.readEntryByTemplate(new RoomList());
            if (roomList != null)
                chatView.putRoomListToConsole(roomList.names);
            else
                chatView.putTextToConsole("> Nao foi possivel buscar lista de salas. Tente novamente!");

        } catch (Exception e) {
            Utils.displayDialog(chatView, "Problema ao trazer lista usuarios presentes na sala!");
            e.printStackTrace();
        }
    }

    private void joinRoom(String roomName) {

        if(CURRENT_ROOM != null)
            quitRoom();


        Room room;
        if (roomName.equals(CURRENT_ROOM)) {
            chatView.putTextToConsole("Voce ja esta na sala " + roomName + "!");
            return;
        }

        try {
            room = space.joinRoom(roomName, INSTANCE_USERNAME);
        } catch (Exception e) {
            Utils.displayDialog(chatView, e.getMessage());
            e.printStackTrace();
            return;
        }

        CURRENT_ROOM = roomName;
        chatView.setRoomName(roomName);
        chatView.putTextToConsole("> Voce entrou na sala " + roomName);

        chatView.putUserListToConsole(room.name, room.onlineUsers, room.awayUsers);


//TODO FAZER ISSO AO SAIR DA SALA: jms.createQueue(roomName, INSTANCE_USERNAME);


        try {
            space.write(room, Long.MAX_VALUE);
        } catch (Exception e) {
            Utils.displayDialog(chatView, "Nao foi possivel inserir tupla Room(" + room.name + ") de volta ao espaco.");
            e.printStackTrace();
        }

        notifyRoom(room.onlineUsers, room.awayUsers);
        chatView.clearConsole();
        listenToRoomMessages(roomName);

    }

//    private void handleMessage(String input) {

//    }


    private void notifyRoom(List<String> onlineUsers, List<String> awayUsers) {
        String msg = INSTANCE_USERNAME + " acabou de entrar na sala!";

        for (String onlineU : onlineUsers) {
            try {
                space.write(new Message(CURRENT_ROOM, onlineU, CURRENT_ROOM, msg, false), Times.MESSAGE_EXPIRATION.getTime());
            } catch (Exception e) {
                Utils.displayDialog(chatView, "Algo errado aconteceu ao notificar usuarios na sala!");
                e.printStackTrace();
            }
        }

        for (String awayU : awayUsers) {
            try {
                space.write(new Message(CURRENT_ROOM, awayU, CURRENT_ROOM, msg, false), Times.MESSAGE_EXPIRATION.getTime());
            } catch (Exception e) {
                Utils.displayDialog(chatView, "Algo errado aconteceu ao notificar usuarios na sala!");
                e.printStackTrace();
            }
        }


    }

    private void listenToRoomMessages(String room) {
        new Thread(() -> {
            while (room.equals(CURRENT_ROOM)) {
                try {

                    Message msg = (Message) space.takeEntryByTemplate(new Message(room, INSTANCE_USERNAME, null, null, null));
                    if (msg != null)
                        chatView.putMessage(msg.sender, msg.message, msg.privacy);

                } catch (Exception e) {
                    Utils.displayDialog(chatView, "Problema ao ler mensagem");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printUserList() {
        try {
            Room room = (Room) space.takeEntryByTemplate(new Room(CURRENT_ROOM));
            chatView.putUserListToConsole(room.name, room.onlineUsers, room.awayUsers);

        } catch (Exception e) {
            Utils.displayDialog(chatView, "Problema ao trazer lista usuarios presentes na sala!");
            e.printStackTrace();
        }

    }

    private void printHelp() {
        String output = "> ";

        output = output.concat("PPDChat: \n");

        for (Commands comm : Commands.values()) {
            output = output.concat("    " + comm.getDescription() + "\n");
        }

        output = output.concat("> Ex: \n" +
                "    /join ifce_ppd\n" +
                "    entrar para sala 'ifce_ppd.'\n\n");

        chatView.putTextToConsole(output);


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
            printHelp();

        }

    }

}
