package io.github.cpaech.Pong4Net.Messages;

import java.io.OutputStream;

public interface IMessage  {
    

    /**
     * This method is used to send the message to the receiving end of the connection.
     * It takes an {@link OutputStream} as a parameter and writes the message to it.
     * 
     * @param writer {@link OutputStream} the OutputStream to write the message to
     */
    public abstract void Send(OutputStream writer);
    public abstract int GetType();
}
