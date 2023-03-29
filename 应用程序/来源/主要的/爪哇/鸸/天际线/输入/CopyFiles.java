package emu.skyine.input;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CopyFiles {
    public static void copyFile(Context context) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // 获取内置文件流
            inputStream = context.getResources().openRawResource(R.raw.example);

            // 获取要复制到的目录
            File dir = new File(Environment.getExternalStorageDirectory() + "/keys");

            // 如果该目录不存在，则创建该目录
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 创建要复制到的文件
            File file = new File(dir, "example.txt");

            // 如果文件已存在，则删除原文件
            if (file.exists()) {
                file.delete();
            }

            // 复制内置文件到指定目录
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
