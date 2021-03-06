package br.com.ppdchat.controller;

/**
 * Created by Roland on 9/29/16.
 */
public interface ChatOperations {
    void printHelp();

    void joinRoom();

    void listRooms();

    void listRoomUsers();

    void sendMessage();

    void sendPrivateMessage();

    void quitRoomOrSystem();

}
