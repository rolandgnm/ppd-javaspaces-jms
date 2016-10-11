package br.com.ppdchat.model;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by Roland on 9/28/16.
 */
public enum Commands {

    HELP("help", "h", "Ajuda", "Mostra todos os comandos disponíveis."),

    JOIN_ROOM("join", "j", "Entrar em Sala", "Entrar numa sala já existente. Se não existir, nova sala será criada."),

    QUIT_ROOM("quit", "q", "Sair...", "Sai da sala atual."),

    ROOM_LIST("roomlist", "rl", "Listar Salas", "Lista todas as salas."),

    USER_IN_ROOM("userlist", "ul", "Listar Usuários", "Lista todos os usuários na sala atual sala."),

    PRIVATE_MESSAGE("private", "pvt", "Mensagem privada", "Mandar mensagem privada para um usuário da sala."),;

    public static final char SYMBOL = '/';
    public static final String CLIENT_PROPRERTY = "command";

    private final String command;
    private final String shortened;
    private final String translation;
    private final String description;


    Commands(String cmd, String shortened, String translation, String description) {
        this.command = cmd;
        this.shortened = shortened;
        this.translation = translation;
        this.description = description;
    }

    @Override
    public String toString() {
//        return command;
        return String.valueOf(SYMBOL) + command;
    }

    public String getShortened() {
        return String.valueOf(SYMBOL) + shortened;
    }

    public String getDescription() {
        return SYMBOL + command +" (" + shortened + "):    " + description;
    }

    public String getTranslation() {
        return translation;
    }

    public static Commands fromString(String text) throws Exception {
        if (text != null) {
            for (Commands c : Commands.values()) {
                if (text.equalsIgnoreCase(c.toString()) ||
                        text.equalsIgnoreCase(c.getShortened())) {
                    return c;
                }
            }
        } else {
            throw new Exception("Comando null!");
        }
        throw new Exception("Comando " + text + " não existe!");
    }

}
