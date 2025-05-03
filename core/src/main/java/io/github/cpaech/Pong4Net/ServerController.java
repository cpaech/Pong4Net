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

import io.github.cpaech.Pong4Net.Messages.IMessage;
import io.github.cpaech.Pong4Net.Messages.MessageTest;





public class ServerController {
    
    /**
     * Reference to the Model provided for Model-View-Controller
     */
    public Model mvcModel;
    
    public NetworkController networkController;

    /**
     * This is the constructor for the server. It creates a new server socket and
     * starts a thread to accept incoming connections {@link IncomingConnections}. 
     * 
     * @param mvcModel {@link Model} passed by the program entry in {@link Main}
     */
    public ServerController(Model mvcModel) {
        this.mvcModel = mvcModel;
        networkController = new NetworkController(false);
        networkController.start();
        
    }
    /**
     * This method is called when the server is disposed. It will close all
     * connections and dispose of the server socket.
     */
    public void dispose() {
        networkController.dispose();
    }

    /**
     * This is called every frame. It goes through all connected clients and checks
     * if they are still connected. If they are not, it removes them from the list. If they are connected
     * it checks if a message is available and hadles it. It also adds new connections from the queue to the list of clients.
     * 
     */
    public void render() {
        IMessage msg = networkController.queue.poll();
        
        if (msg != null && msg.GetType() == 3)
        {
            System.out.println(((MessageTest)msg).message);
        }
    }
        
}

