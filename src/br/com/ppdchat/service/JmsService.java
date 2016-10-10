package br.com.ppdchat.service;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by Roland on 9/28/16.
 */
public class JmsService {


    private final String initConFac = "org.exolab.jms.jndi.InitialContextFactory";
    private final String url = "tcp://localhost:3035/";
    private final String conFac = "ConnectionFactory";
    private final Hashtable<String, String> properties;
    private Context context;

    public JmsService() throws JMSException, NamingException {
        properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, initConFac);
        properties.put(Context.PROVIDER_URL, url);

        try {
            context = new InitialContext(properties);

            QueueConnectionFactory qfactory = (QueueConnectionFactory) context.lookup(conFac);

            QueueConnection qconnection = qfactory.createQueueConnection();
            QueueSession qsession = qconnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);



        } catch (NamingException | JMSException e) {
            e.printStackTrace();
            throw e;
        }

        System.out.println("JMS > Conexao OK!");
    }

    //prepara


}
