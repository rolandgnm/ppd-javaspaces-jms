package br.com.ppdchat.service;

import br.com.ppdchat.model.Times;
import br.com.ppdchat.model.entries.Room;
import br.com.ppdchat.model.entries.RoomList;
import br.com.ppdchat.model.entries.UserList;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Roland on 9/28/16.
 */
public class JavaSpaceService {

    private final Lookup finder;
    private final JavaSpace space;

    public JavaSpaceService() throws Exception {
        try {
            System.out.println("Procurando pelo servico JavaSpace...");
            finder = new Lookup(JavaSpace.class);
            space = (JavaSpace) finder.getService();

            if (space == null) {
                System.out
                        .println("O servico JavaSpace nao foi encontrado.");
                throw new Exception("O servico JavaSpace nao foi encontrado.");
                //System.exit(-1);
            }
            System.out.println("JavaSpace > Conexão OK!");
        } catch (RemoteException e) {
            throw e;
        }
    }

    public void setUsername(String username) throws Exception {
        /**
         * Testa se nome já existe se existir manda Exception com msg;
         * Senão cria;
         * escreve de volta!
         */

        UserList uList = (UserList) takeEntryByTemplate(new UserList());

        //Trata ausência de tupla
        if (uList == null) {
            uList = (UserList) forceTake(new UserList());
            if (uList == null)
                throw new Exception("Serviço não preparado para uso. Favor executar classe StartupService");
        }

        // Nome ja exite
        if (uList.names.contains(username.toLowerCase())) {
            write(uList, Long.MAX_VALUE);
            throw new Exception(username + " já Existe! Tente um novo nome.");
        } else
            uList.names.add(username);

        write(uList, Long.MAX_VALUE);
    }

    private Entry forceTake(Entry template) throws Exception {
        Entry taken = null;
        for (int iterate = 0; iterate < Times.TAKE_TRIES; iterate++) {
            taken = takeEntryByTemplate(template);
            System.out.println(template.getClass().getName() + "retirada!");
            if (taken != null)
                break;
        }
        return taken;
    }


    public Room joinRoom(String roomName, String username) throws Exception {
        //todo write back
        RoomList roomList = (RoomList) forceTake(new RoomList());
        Room room = (Room) takeEntryByTemplate(new Room(roomName));

        if (room == null) {
            room = (Room) forceTake(new Room(roomName));
            if (room == null) {
                room = new Room(roomName);
                room.onlineUsers = new ArrayList<>();
                room.awayUsers = new ArrayList<>();
            }
        }

        if (!roomList.names.contains(room.name))
            roomList.names.add(room.name);

        if (!room.onlineUsers.contains(username))
            room.onlineUsers.add(username);

        if (room.awayUsers.contains(username))
            room.awayUsers.remove(username);


        write(roomList, Long.MAX_VALUE);

        return room;
    }


    public void write(Entry entry, Long timeout) throws Exception {
        try {
            space.write(entry, null, timeout);
            System.out.println("-> Inserindo tupla: " + entry.getClass().getName());
        } catch (TransactionException | RemoteException e) {
            e.printStackTrace();
            throw new Exception("Erro ao escrever " + entry.getClass().getName() + " no espaço");
        }
    }

    public Entry takeEntryByTemplate(Entry template) throws Exception {

        try {
            Entry entry = space.take(template, null, Times.WAIT.getTime());
            if (entry != null) {
                System.out.println("<- Retirando tupla " + template.getClass().getName());
                return entry;
            } else {
                return null;
            }

        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
            e.printStackTrace();
            throw new Exception("! Erro ao retirar " + template.getClass().getName());
        }


    }

    public Entry readEntryByTemplate(Entry template) throws Exception {

        try {
            System.out.println("< Lendo tupla " + template.getClass().getName());
            return space.read(template, null, Times.WAIT.getTime());

        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
            e.printStackTrace();
            throw new Exception("! Erro ao retirar " + template.getClass().getName());
        }


    }
}
