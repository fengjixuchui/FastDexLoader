package fastdexloader.by.modify24x7;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static fastDex.NativeDexLoader.fastLoad;

public class MainApplication extends Application {

    static {
        System.loadLibrary("fastdexloader");
    }

    private ArrayList<String> isLoad = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        if (isLoad.size() < 1) {

            writeRaw(new File(getCacheDir(), "classes.dex"));

            fastLoad(base, new File(getCacheDir(), "classes.dex"), getDir("outDex", MODE_PRIVATE));

            isLoad.add("load");
        }
    }

    private void writeRaw(File outfile) {
        try {

            InputStream is = getResources().openRawResource(R.raw.classes);
            FileOutputStream fos = new FileOutputStream(outfile);

            byte[] buffer = new byte[1024];
            int read;

            while ((read = is.read(buffer)) > 0)
                fos.write(buffer, 0, read);

            is.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
