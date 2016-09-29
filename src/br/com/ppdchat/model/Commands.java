package br.com.ppdchat.model;

/**
 * Created by Roland on 9/28/16.
 */
public enum Commands {
    SYMBOL("/", null, null),

    HELP("help","h","Mostra todos os comandos disponíveis."),

    JOIN_ROOM("join", "j", "Entrar numa sala. Se não existir, nova sala será criada."),

    QUIT("quit", "q", "Sai da sala atual. Se não estiver em nenhuma sala sai do chat"),

    ROOM_LIST("roomlist", "rl", "Lista todas as salas."),

    USER_LIST("userlist", "ul", "Lista todos os usuários na sala atual sala."),

    PRIVATE_MESSAGE("private", "pvt", "Mandar mensagem privada para um usuário da sala atual."),

    ;


    private final String command;
    private final String shortened;
    private final String description;


    Commands(String cmd, String shortened, String description) {
        this.command= cmd;
        this.shortened = shortened;
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
}
