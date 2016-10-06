package br.com.ppdchat.service;

import br.com.ppdchat.model.entries.Message;
import br.com.ppdchat.model.entries.Room;
import br.com.ppdchat.model.entries.RoomList;
import br.com.ppdchat.model.entries.UserList;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Roland on 10/5/16.
 */
public class StartupService {

    public static final int WAIT_TIME = 3 * 1000;
    private static Lookup finder;
    private static JavaSpace space;

    /**
     * Esse serviço Adiciona ao espaço listas vazias
     * para o início do funcionamento do serviço de chat.
     *
     * É recomendado executar este servico logo apos iniciar
     * o espaço!
     *
     * É normal receber stack trace no console!
     */


    public static void main(String[] args) {
        try {
            /**
             * Inicializa conexao ao JavaSpace
             */
            System.out.println("Procurando pelo servico JavaSpace...");
            finder = new Lookup(JavaSpace.class);
            space = (JavaSpace) finder.getService();

            if (space == null) {
                System.out
                        .println("O servico JavaSpace nao foi encontrado.");
                throw new Exception("O servico JavaSpace nao foi encontrado.");
                //System.exit(-1);
            }
            System.out.println("O servico JavaSpace foi encontrado.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        clearLists();
        initializeSpace();

    }

    private static void clearLists() {
        RoomList roomListTempl = new RoomList();
        UserList userListTempl = new UserList();
        Room     roomTempl = new Room();
        Message messageTempl = new Message();


        /**
         * Remove RoomLists
         */
        while (true) {
            RoomList roomList = null;
            try {
                roomList = (RoomList) space.take(roomListTempl, null, WAIT_TIME);
            } catch (UnusableEntryException | InterruptedException | RemoteException | TransactionException e) {
                e.printStackTrace();
                System.out.println("Problema ao remover RoomList!");
            }
            if (roomList == null) {
                System.out
                        .println("Nao ha tupla do tipo RoomList...");
                break;
            }
            System.out.println("Removendo RoomList!");
        }


        /**
         * Remove Userlist
         */
        while (true) {
            UserList userList = null;
            try {
                userList = (UserList) space.take(userListTempl, null, WAIT_TIME);
            } catch (UnusableEntryException | InterruptedException | RemoteException | TransactionException e) {
                e.printStackTrace();
                System.out.println("Problema ao remover UserList!");

            }
            if (userList == null) {
                System.out
                        .println("Nao ha tupla do tipo UserList...");
                break;
            }
            System.out.println("Removendo lista de usuarios!");
        }

        /**
         * Remove Userlist
         */
        while (true) {
            Room room = null;
            try {
                room = (Room) space.take(roomTempl, null, WAIT_TIME);

            } catch (UnusableEntryException | InterruptedException | RemoteException | TransactionException e) {
                e.printStackTrace();
                System.out.println("Problema ao remover Room!");

            }
            if (room == null) {
                System.out
                        .println("Nao ha tupla do tipo Room...");
                break;
            }
            System.out.println("Removendo sala!");
        }

        /**
         * Remove Userlist
         */
        while (true) {
            Message message = null;
            try {
                message = (Message) space.take(messageTempl, null, WAIT_TIME);

            } catch (UnusableEntryException | InterruptedException | RemoteException | TransactionException e) {
                e.printStackTrace();
                System.out.println("Problema ao remover Message!");

            }
            if (message == null) {
                System.out
                        .println("Nao ha tupla do tipo Message...");
                break;
            }
            System.out.println("Removendo Message!");
        }

    }

    private static void initializeSpace() {
        RoomList roomList = new RoomList();
        roomList.names = new ArrayList<>();

        UserList userList = new UserList();
        userList.names = new ArrayList<>();


        try {

            space.write(roomList, null, Long.MAX_VALUE);
            space.write(userList, null, Long.MAX_VALUE);

        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}
