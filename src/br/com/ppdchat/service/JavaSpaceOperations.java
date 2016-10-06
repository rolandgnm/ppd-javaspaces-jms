package br.com.ppdchat.service;

import br.com.ppdchat.model.entries.Message;
import br.com.ppdchat.model.entries.Room;

import java.util.List;

/**
 * Created by Roland on 9/29/16.
 */
public interface JavaSpaceOperations {
    List<String> getRoomList();

    Room getRoomByName(String name);

    void sendMessage(Message message);


}
