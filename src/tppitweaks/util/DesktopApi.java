package tppitweaks.util;

import java.awt.Desktop;
import java.net.URI;

public class DesktopApi {

    public static boolean browse(URI uri) {

        if (browseDESKTOP(uri)) return true;

        return false;
    }

    private static boolean browseDESKTOP(URI uri) {

        logOut("Trying to use Desktop.getDesktop().browse() with " + uri.toString());
        try {
            if (!Desktop.isDesktopSupported()) {
                logErr("Platform is not supported.");
                return false;
            }

            if (!Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                logErr("BORWSE is not supported.");
                return false;
            }

            Desktop.getDesktop().browse(uri);

            return true;
        } catch (Throwable t) {
            logErr("Error using desktop browse.", t);
            return false;
        }
    }

    private static void logErr(String msg, Throwable t) {
        System.err.println(msg);
        t.printStackTrace();
    }

    private static void logErr(String msg) {
        System.err.println(msg);
    }

    private static void logOut(String msg) {
        System.out.println(msg);
    }
}