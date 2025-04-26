package io.github.cpaech.Pong4Net;

import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class IncomingConnections extends Thread{
    ServerController serverController;
    public IncomingConnections(ServerController sController)
    {
        serverController = sController;

    }
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
                    
                }
            }

    }
}
