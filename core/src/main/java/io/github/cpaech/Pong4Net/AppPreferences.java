package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppPreferences {

    public static final String PREFS_NAME = "Pong4Net";
    public static final String CLIENT_HOST = "client.host";
    public static final String CLIENT_PORT = "client.port";
    public static final String SERVER_HOST = "server.host";
    public static final String SERVER_PORT = "server.port";

    private static AppPreferences appPreferences = null;

    public static AppPreferences getAppPreferences() {
        if (appPreferences == null) {
            AppPreferences.appPreferences = new AppPreferences();
        }
        // Ensure that the singleton instance is returned
        return AppPreferences.appPreferences;
    }

    protected Preferences getPrefs() {
	    return Gdx.app.getPreferences(PREFS_NAME);
    }

    public String getClientHost() {
        return getPrefs().getString(CLIENT_HOST, "localhost");
    }

    public void setClientHost(String host) {
        getPrefs().putString(CLIENT_HOST, host);
        getPrefs().flush();
    }

    public int getClientPort() {
        return getPrefs().getInteger(CLIENT_PORT, 12345);
    }

    public void setClientPort(int port) {
        getPrefs().putInteger(CLIENT_PORT, port);
        getPrefs().flush();
    }

    public String getServerHost() {
        return getPrefs().getString(SERVER_HOST, "localhost");
    }

    public void setServerHost(String host) {
        getPrefs().putString(SERVER_HOST, host);
        getPrefs().flush();
    }

    public int getServerPort() {
        return getPrefs().getInteger(SERVER_PORT, 12345);
    }

    public void setServerPort(int port) {
        getPrefs().putInteger(SERVER_PORT, port);
        getPrefs().flush();
    }

    public void setPlayerHighScore(String playerName, int score) {
        getPrefs().putInteger(playerName + ".highscore", score);
        getPrefs().flush();
    }

    public int getPlayerHighScore(String playerName) {
        return getPrefs().getInteger(playerName + ".highscore", 0);
    }

    public void resetPreferences() {
        getPrefs().clear();
        getPrefs().flush();
    }
}
