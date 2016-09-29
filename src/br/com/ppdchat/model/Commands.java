package br.com.ppdchat.model;

/**
 * Created by Roland on 9/28/16.
 */
public enum Commands {
    SYMBOL("/"),

    HELP("help"),
    HELP_SHORT("h"),

    JOIN_ROOM("join"),
    JOIN_ROOM_SHORT("j"),

    QUIT("quit"),
    QUIT_SHORT("q"),

    ROOM_LIST("roomlist"),
    ROOM_LIST_SHORT("rl"),

    USER_LIST("userlist"),
    USER_LIST_SHORT("ul"),

    PRIVATE_MESSAGE("private"),
    PRIVATE_MESSAGE_SHORT("pvt")

    ;


    private String command;

    Commands(String cmd) {
        command = cmd;
    }

    @Override
    public String toString() {
        return command;
    }
}
