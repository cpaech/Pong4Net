package io.github.cpaech.Pong4Net;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.net.ServerSocket;
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
     * This is the queue that will be used to store incoming socket connections. It is a
     * ConcurrentLinkedQueue to allow multiple threads to access it at the same time
     */
    public ConcurrentLinkedQueue<Socket> queue;

    public ServerSocket server;
   
    public IncomingConnections(ServerSocket server)
    {
        this.server = server;
        queue = new ConcurrentLinkedQueue<Socket>();

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
                    s = server.accept(sHints);
                    queue.add(s);
                }
                catch (GdxRuntimeException e){
                    //This is completely normal, it just means that no new connections are available
                }
            }

    }
}
