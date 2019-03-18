package com.github.badoualy.telegram.tl.api;

/**
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
public class TLTopPeerCategoryGroups extends TLAbsTopPeerCategory {

    public static final int CONSTRUCTOR_ID = 0xbd17a14a;

    private final String _constructor = "topPeerCategoryGroups#bd17a14a";

    public TLTopPeerCategoryGroups() {
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
