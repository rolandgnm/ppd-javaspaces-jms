package br.com.ppdchat.view;

/**
 * Created by Roland on 9/29/16.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.ppdchat.model.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author Roland
 */
public class ChatView extends javax.swing.JFrame {

    private String USERNAME;
    private String ROOMNAME;
    private final String NO_ROOM = "Nenhuma sala";

    /**
     * Creates new form ChatView
     *
     * @param commandMenuListener
     * @param inputListener
     * @param username
     */
    public ChatView(ActionListener commandMenuListener, ActionListener inputListener, String username) {
        USERNAME = username.toString();
        ROOMNAME = NO_ROOM;
        initComponents();

        addMenuListeners(commandMenuListener);
        addInputListener(inputListener);
    }

    private void addInputListener(ActionListener inputListener) {
        textInput.addActionListener(inputListener);
        btnSend.addActionListener(inputListener);
    }

    private void addMenuListeners(ActionListener commandMenuListener) {
        for (Component menuItem : commandMenu.getMenuComponents()) {
            if (menuItem instanceof JMenuItem)
                ((JMenuItem) menuItem).addActionListener(commandMenuListener);
        }

    }

    public void setVisible() {
            /* Create and display the form */
        SwingUtilities.invokeLater(() -> setVisible(true));

    }

    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        lbRoomName = new javax.swing.JLabel();
        consolePanel = new javax.swing.JScrollPane();
        chatConsole = new javax.swing.JTextArea();
        southPanel = new javax.swing.JPanel();
        textInput = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        commandMenu = new javax.swing.JMenu();
        miHelp = new javax.swing.JMenuItem();
        miRoomList = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miJoin = new javax.swing.JMenuItem();
        miUserList = new javax.swing.JMenuItem();
        miPrivate = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        miQuit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 400));

        /**
         * Room name panel
         */

        titlePanel.setBackground(new java.awt.Color(41, 128, 185));

        lbRoomName.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lbRoomName.setForeground(new java.awt.Color(255, 255, 255));
        lbRoomName.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 2, 0));
        updateTitle();
        titlePanel.add(lbRoomName);

        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);


        /**
         * Chat console
         */
        chatConsole.setColumns(20);
        chatConsole.setLineWrap(true);
        chatConsole.setRows(5);
        chatConsole.setEditable(false);
        chatConsole.setFocusable(false);
        consolePanel.setViewportView(chatConsole);

        getContentPane().add(consolePanel, java.awt.BorderLayout.CENTER);

        southPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        southPanel.setLayout(new java.awt.BorderLayout());
        southPanel.add(textInput, java.awt.BorderLayout.CENTER);

        btnSend.setText("Enviar");

        //TODO INPUT ACTION LISTENER
        textInput.addActionListener(null);
        btnSend.addActionListener(null);

        southPanel.add(btnSend, java.awt.BorderLayout.EAST);

        getContentPane().add(southPanel, java.awt.BorderLayout.PAGE_END);

        /**
         * Menu
         */
        commandMenu.setText("Comandos");

        miHelp.setText(Commands.HELP + "\t-\t" + Commands.HELP.getTranslation());
        miRoomList.setText(Commands.ROOM_LIST + "\t-\t" + Commands.ROOM_LIST.getTranslation());
        miJoin.setText(Commands.JOIN_ROOM + "\t-\t" + Commands.JOIN_ROOM.getTranslation());
        miUserList.setText(Commands.USER_IN_ROOM + "\t-\t" + Commands.USER_IN_ROOM.getTranslation());
        miPrivate.setText(Commands.PRIVATE_MESSAGE + "\t-\t" + Commands.PRIVATE_MESSAGE.getTranslation());
        miQuit.setText(Commands.QUIT_ROOM + "\t-\t" + Commands.QUIT_ROOM.getTranslation());

        miHelp.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.HELP);
        miRoomList.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.ROOM_LIST);
        miJoin.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.JOIN_ROOM);
        miUserList.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.USER_IN_ROOM);
        miPrivate.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.PRIVATE_MESSAGE);
        miQuit.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.QUIT_ROOM);


        //TODO COMMAND LISTENER
//        miHelp.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                miHelpActionPerformed(evt);
//            }
//        });

//        miRoomList.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                miRoomListActionPerformed(evt);
//            }
//        });

//        miJoin.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                miJoinActionPerformed(evt);
//            }
//        });

//        miUserList.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                miUserListActionPerformed(evt);
//            }
//        });

//        miPrivate.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                miPrivateActionPerformed(evt);
//            }
//        });

//        miQuit.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                miQuitActionPerformed(evt);
//            }
//        });


        commandMenu.add(miHelp);
        commandMenu.add(miRoomList);
        commandMenu.add(jSeparator1);
        commandMenu.add(miJoin);
        commandMenu.add(miUserList);
        commandMenu.add(miPrivate);
        commandMenu.add(jSeparator2);
        commandMenu.add(miQuit);

        menuBar.add(commandMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>

    private void updateTitle() {
        lbRoomName.setText(USERNAME + " @ " + ROOMNAME);
    }

    private javax.swing.JButton btnSend;
    private javax.swing.JTextArea chatConsole;
    private javax.swing.JMenu commandMenu;
    private javax.swing.JScrollPane consolePanel;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miHelp;
    private javax.swing.JMenuItem miJoin;
    private javax.swing.JMenuItem miPrivate;
    private javax.swing.JMenuItem miQuit;
    private javax.swing.JMenuItem miRoomList;
    private javax.swing.JMenuItem miUserList;
    private javax.swing.JPanel southPanel;
    private javax.swing.JTextField textInput;
    private JPanel titlePanel;
    private JLabel lbRoomName;


    public String getInputText() {
        return this.textInput.getText().trim();
    }

    public void putTextToConsole(String newLine) {
        chatConsole.append("\n");
        chatConsole.append(newLine);
    }

    public void clearInput() {
        textInput.setText(null);
    }

    public void setRoomName(String name) {
        this.ROOMNAME = name;
        updateTitle();
    }

    public void setUsername(String username) {
        this.USERNAME = username;
        updateTitle();

    }

    public void putMessage(String sender, String message, Boolean privacy) {
        putTextToConsole("@" + sender +
                (privacy ? "(privado)" : "")
                + ": " + message);
    }

    public void putUserListToConsole(String roomName, List<String> onlineUsers, List<String> awayUsers) {
        int size, i;
        String output = "";
        output = output.concat("> " +
                "Presentes na sala " + roomName + ": \n");

        if (onlineUsers.size() > 1) {
            output = output.concat("\tOnline:\n");
            size = onlineUsers.size();
            i = 0;
            for (String uname : onlineUsers) {
                output = output.concat(uname +
                        (i != (size - 1) ? ", " : ".\n"));
                i++;
            }
        } else {
            output = output.concat("Voce esta sozinho na sala!\n");
        }

        if (awayUsers.size() > 0) {
            output = output.concat("\tAusentes:\n");
            size = onlineUsers.size();
            i = 0;

            for (String uname : onlineUsers) {
                output = output.concat(uname +
                        (i != (size - 1) ? ", " : ".\n"));
                i++;
            }
        }


        putTextToConsole(output);

    }


    //TODO COMPLETAR PRINT LISTA DE ROOM
    public void putRoomListToConsole(List<String> roomNames) {
        int size = roomNames.size(),
            i=0;
        String output;

        if (!roomNames.isEmpty()) {
            output = "";
            output = output.concat("> " +
                    "Lista de salas: \n    ");

            for (String rname : roomNames) {
                output = output.concat(rname +
                        (i != (size - 1) ? ", " : "\n"));
                i++;
            }

        } else {
            output = "> Nenhuma sala criada ate o momento! Crie uma!";

        }

        putTextToConsole(output);
    }

    public void clearConsole() {
        chatConsole.setText("");
    }


    // End of variables declaration
}
