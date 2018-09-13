package fastdexloader.by.modify24x7;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

public class MainActivity extends Activity {

    public static void CallMethod(String clazz, String methName, Class<?>[] argsType, Object... methArgs) {
        try {
            Class className = Class.forName(clazz);
            Object process = className.newInstance();
            Method aMethod = process.getClass().getDeclaredMethod(methName, argsType);
            if (!aMethod.isAccessible())
                aMethod.setAccessible(true);
            aMethod.invoke(process, methArgs);
        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallMethod("fastdexloader.by.modify24x7.TestClass", "showMsg", new Class[]{Context.class}, getApplicationContext());
            }
        });
    }
}
