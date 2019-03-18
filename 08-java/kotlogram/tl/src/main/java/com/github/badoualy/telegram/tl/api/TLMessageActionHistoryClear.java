package com.github.badoualy.telegram.tl.api;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLMessageActionHistoryClear extends TLAbsMessageAction {

    public static final int CONSTRUCTOR_ID = 0x9fbab604;

    private final String _constructor = "messageActionHistoryClear#9fbab604";

    public TLMessageActionHistoryClear() {
    }

    @Override
    public String toString() {
        return _constructor;
    }

    @Override
    public int getConstructorId() {
        return CONSTRUCTOR_ID;
    }
}
