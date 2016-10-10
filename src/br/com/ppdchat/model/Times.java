package br.com.ppdchat.model;

/**
 * Created by Roland on 9/28/16.
 */
public enum Times {
    MESSAGE_EXPIRATION(1000 * 60 * 5),
    ROOM_EXPIRATION(1000 * 60 * 10),
    WAIT(300);

    public static int TAKE_TRIES = 3;
    private final long time;

    Times(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }


}
