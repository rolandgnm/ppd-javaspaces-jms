package br.com.ppdchat.service;

import br.com.ppdchat.model.Times;
import br.com.ppdchat.model.entries.UserList;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

import java.rmi.RemoteException;

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
            uList = (UserList) forceTake(new UserList(), Times.TAKE_TRIES);
            if (uList == null)
                throw new Exception("Serviço não preparado para uso. Favor executar classe StartupService");
        }

        // Nome ja exite
        if (uList.names.contains(username)) {
            write(uList);
            throw new Exception(username + " já Existe! Tente um novo nome.");
        } else
            uList.names.add(username);

        write(uList);
    }

    private Entry forceTake(Entry template, int i) throws Exception {
        Entry taken = null;
        for (int iterate = 0; iterate < i; iterate++) {
            taken = takeEntryByTemplate(template);
            if (taken != null)
                break;
        }
        return taken;
    }

    private void write(Entry entry) throws Exception {
        try {
            space.write(entry, null, Long.MAX_VALUE);
        } catch (TransactionException | RemoteException e) {
            e.printStackTrace();
            throw new Exception("Erro ao escrever no espaço");
        }
    }

    private Entry takeEntryByTemplate(Entry template) throws Exception {

        try {

            return space.take(template, null, Times.WAIT.getTime());

        } catch (UnusableEntryException | TransactionException | InterruptedException | RemoteException e) {
            e.printStackTrace();
            throw new Exception("Erro ao retirar objeto do espaço");
        }


    }
}
