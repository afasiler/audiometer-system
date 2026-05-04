package edu.ankara.audiometer.serial;

public final class SerialCommand {
    public static final String PLAY_PREFIX = "PLAY;";

    private SerialCommand() {
    }

    public static String playTone(int frequencyHz, int dbLevel, String ear) {
        return PLAY_PREFIX + "FREQ=" + frequencyHz + ";DB=" + dbLevel + ";EAR=" + ear;
    }
}
