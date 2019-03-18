package com.github.badoualy.telegram.tl.api;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLInputPrivacyKeyChatInvite extends TLAbsInputPrivacyKey {

    public static final int CONSTRUCTOR_ID = 0xbdfb0426;

    private final String _constructor = "inputPrivacyKeyChatInvite#bdfb0426";

    public TLInputPrivacyKeyChatInvite() {
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
