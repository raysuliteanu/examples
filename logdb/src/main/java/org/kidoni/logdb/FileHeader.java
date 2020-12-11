package org.kidoni.logdb;

record FileHeader(String type, String version) {
    public static final String TYPE = "LOGDB";
    public static final String VERSION = "VER1";

    public static FileHeader createHeader() {
        return new FileHeader(TYPE, VERSION);
    }
}
