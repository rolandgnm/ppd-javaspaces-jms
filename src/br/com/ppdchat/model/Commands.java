package br.com.ppdchat.model;

/**
 * Created by Roland on 9/28/16.
 */
public enum Commands {

    HELP("help","h","Ajuda","Mostra todos os comandos disponíveis."),

    JOIN_ROOM("join", "j","Entrar em Sala", "Entrar numa sala já existente. Se não existir, nova sala será criada."),

    QUIT("quit", "q", "Sair...", "Sai da sala atual. Se não estiver em nenhuma sala sai do chat"),

    ROOM_LIST("roomlist", "rl","Listar Salas", "Lista todas as salas."),

    USER_LIST("userlist", "ul","Listar Usuários",  "Lista todos os usuários na sala atual sala."),

    PRIVATE_MESSAGE("private", "pvt","Mensagem privada", "Mandar mensagem privada para um usuário da sala."),

    ;

    public static final char SYMBOL = '/';
    public static final String CLIENT_PROPRERTY = "command";

    private final String command;
    private final String shortened;
    private final String translation;
    private final String description;


    Commands(String cmd, String shortened, String translation, String description) {
        this.command= cmd;
        this.shortened = shortened;
        this.translation = translation;
        this.description = description;
    }

    @Override
    public String toString() {
        return  command;
    }

    public String getShortened() {
        return shortened;
    }

    public String getDescription() {
        return description;
    }

    public String getTranslation() {return translation; }

}
