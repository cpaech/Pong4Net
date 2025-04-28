package io.github.cpaech.Pong4Net;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.GdxRuntimeException;

import io.github.cpaech.Pong4Net.Messages.MessageTest;





public class ServerController {
    /**
     * This is the queue that will be used to store incoming socket connections. It is a
     * ConcurrentLinkedQueue to allow multiple threads to access it at the same time
     */
    ConcurrentLinkedQueue<Socket> queue = new ConcurrentLinkedQueue<Socket>();
    /**
     * Reference to the Model provided for Model-View-Controller
     */
    public Model mvcModel;
    /**
     * This is the server socket that will be used to accept incoming connections
     */
    public ServerSocket server;
    /**
     * This is the thread that will be used to accept incoming connections
     */
    IncomingConnections incomingConnections;
    /**
     * This is the list of all connected clients
     */
    public List<Socket> clients;

    /**
     * This is the constructor for the server. It creates a new server socket and
     * starts a thread to accept incoming connections {@link IncomingConnections}. 
     * 
     * @param mvcModel {@link Model} passed by the program entry in {@link Main}
     */
    public ServerController(Model mvcModel) {
        this.mvcModel = mvcModel;
        
        try{
            server = Gdx.net.newServerSocket(Protocol.TCP, 7654, new ServerSocketHints());
            
            clients = new ArrayList<Socket>();
            incomingConnections = new IncomingConnections(this);
            incomingConnections.start();
        }
        catch (GdxRuntimeException e)
        {
            throw new RuntimeException("Failed creating a server", e); //TODO: Handle this by retuning to menu
        }
    }
    /**
     * This method is called when the server is disposed. It will close all
     * connections and dispose of the server socket.
     */
    public void dispose() {
        server.dispose();
        for (Socket s : clients) {
            s.dispose();
        }
    }

    /**
     * This is called every frame. It goes through all connected clients and checks
     * if they are still connected. If they are not, it removes them from the list. If they are connected
     * it checks if a message is available and hadles it. It also adds new connections from the queue to the list of clients.
     * 
     */
    public void render() {
        {
            Socket s = queue.poll();
            while (s != null) {
                clients.add(s);
                s = queue.poll();
            }
        }

        for (Socket s : clients) {
            //The Socket is closed if the client disconnects thus we remove it from the list
            if (s.isConnected() == false)
            {
                s.dispose();
                clients.remove(s);
                continue;

            }
            
            InputStream stream = (s.getInputStream());
            try {
                //This is a blocking call, it will wait until a message is received. Not great not terrible
                if (stream.read() == 3) //TODO: Replace with switch case
                {
                    System.out.println(new MessageTest(stream).message);
                }
            }
            catch (IOException e)
            {
                //Something is wrong with the socket, we remove it from the list
                e.printStackTrace();
                clients.remove(s);
                continue;
            }
        }


    }
        
}

