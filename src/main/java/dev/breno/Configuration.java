package dev.breno;

public final class Configuration {

    private static int width = 320;
    private static int height = 240;
    private static int scale = 2;
    private static int offside = 16;
    private static int framesPerSeconds = 60;

    public static int getStaticWidth() {
        return width*scale;
    }

    public static int getStaticHeight() {
        return height*scale;
    }

    public static int getStaticHeightWithOffside() {
        return getStaticHeight() - offside;
    }
    public static int getStaticWidthWithOffside() {
        return getStaticWidth() - offside;
    }

    public static int getOffside() {
        return offside;
    }

    public static int getFPS() {
        return framesPerSeconds;
    }

}
