package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * This is the thread that will be used to accept incoming connections by the {@link ServerController}.
 * 
 * @author cpaech
 *
 */
public class IncomingConnections extends Thread{
    /**
     * Reference to the {@link ServerController} which is used to accept incoming connections
     * and store them in the {@link ServerController#clients} list.
     */
    ServerController serverController;
    /**
     * This is the constructor for the {@link IncomingConnections} thread. It takes a {@link ServerController} as a parameter.
     * 
     * @param sController {@link ServerController} passed by the program entry in {@link Main}
     */
    public IncomingConnections(ServerController sController)
    {
        serverController = sController;

    }

    /**
     * This method is called when the thread is started. It will accept incoming connections and add them to the {@link ServerController#clients} list.
     * This is done in a thread to prevent blocking the main thread.
     * @see Thread#run()
     */
    @Override
    public void run()
    {
        SocketHints sHints = new SocketHints();
        sHints.connectTimeout = 10;
        
            while (true)
            {
                try {
                    Socket s;
                    s = serverController.server.accept(sHints);
                    serverController.clients.add(s);
                }
                catch (GdxRuntimeException e){
                    //This is completely normal, it just means that no new connections are available
                }
            }

    }
}
