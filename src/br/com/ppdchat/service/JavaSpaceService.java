package br.com.ppdchat.service;

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
            System.out.println("O servico JavaSpace foi encontrado.");
        } catch (RemoteException e) {
            throw e;
        }
    }

    }