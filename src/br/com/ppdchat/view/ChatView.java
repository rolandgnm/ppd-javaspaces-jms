package br.com.ppdchat.view;

/**
 * Created by Roland on 9/29/16.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.ppdchat.controller.ChatController;
import br.com.ppdchat.model.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Roland
 */
public class ChatView extends javax.swing.JFrame {

    /**
     * Creates new form ChatView
     *
     * @param commandMenuListener
     * @param inputListener
     */
    public ChatView(ActionListener commandMenuListener, ActionListener inputListener) {
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

    public void setViewTitle(String title) {
        this.setTitle(title);
    }

    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        roomName = new javax.swing.JLabel();
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

        roomName.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        roomName.setForeground(new java.awt.Color(255, 255, 255));
        roomName.setText("Chat");
        roomName.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 2, 0));
        titlePanel.add(roomName);

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

        miHelp.setText(Commands.SYMBOL + Commands.HELP + "\t-\t" + Commands.HELP.getTranslation());
        miRoomList.setText(Commands.SYMBOL + Commands.ROOM_LIST + "\t-\t" + Commands.ROOM_LIST.getTranslation());
        miJoin.setText(Commands.SYMBOL + Commands.JOIN_ROOM + "\t-\t" + Commands.JOIN_ROOM.getTranslation());
        miUserList.setText(Commands.SYMBOL + Commands.USER_LIST + "\t-\t" + Commands.USER_LIST.getTranslation());
        miPrivate.setText(Commands.SYMBOL + Commands.PRIVATE_MESSAGE + "\t-\t" + Commands.PRIVATE_MESSAGE.getTranslation());
        miQuit.setText(Commands.SYMBOL + Commands.QUIT + "\t-\t" + Commands.QUIT.getTranslation());

        miHelp.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.HELP);
        miRoomList.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.ROOM_LIST);
        miJoin.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.JOIN_ROOM);
        miUserList.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.USER_LIST);
        miPrivate.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.PRIVATE_MESSAGE);
        miQuit.putClientProperty(Commands.CLIENT_PROPRERTY, Commands.QUIT);


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
    private JLabel roomName;


    public String getInputText() {
        return this.textInput.getText();
    }

    public void putTextToConsole(String newLine) {
        chatConsole.append("\n");
        chatConsole.append(newLine);
    }

    public void clearInput() {
        textInput.setText(null);
    }

    public void setRoomName(String name) {
        roomName.setText(name);
    }

    // End of variables declaration
}
