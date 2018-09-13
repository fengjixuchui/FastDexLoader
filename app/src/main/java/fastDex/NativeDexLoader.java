package fastDex;

import android.content.Context;

import java.io.File;

public class NativeDexLoader {
    /***
     *
     * @param ctx Context
     * @param inFile apk, dex, jar
     * @param outPath optimize dir
     * @return true / false
     */
    public static native boolean fastLoad(Context ctx, File inFile, File outPath);
}
