package io.github.cpaech.Pong4Net;


public interface IPermanentStorage {
    //onto disk or whatever
    public abstract void Store();
    public abstract void Load();

    //temporary
    public abstract void Read(String key, Object value);
    public abstract void Write(String key, Object value);

}
