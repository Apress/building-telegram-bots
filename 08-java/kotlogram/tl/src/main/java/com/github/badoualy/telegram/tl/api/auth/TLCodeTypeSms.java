package com.github.badoualy.telegram.tl.api.auth;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLCodeTypeSms extends TLAbsCodeType {

    public static final int CONSTRUCTOR_ID = 0x72a3158c;

    private final String _constructor = "auth.codeTypeSms#72a3158c";

    public TLCodeTypeSms() {
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
