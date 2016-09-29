package br.com.ppdchat.service;

import br.com.ppdchat.model.Message;
import br.com.ppdchat.model.Room;

import java.util.List;

/**
 * Created by Roland on 9/29/16.
 */
public interface JavaSpaceOperations {
    List<String> getRoomList();

    Room getRoomByName(String name);

    void sendMessage(Message message);


}
