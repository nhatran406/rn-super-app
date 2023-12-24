package com.superapp.miniapp;
import com.facebook.react.bridge.ReactContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MiniAppBundle {
    private static ReactContext reactContext;

    public void downloadFileInInternalStorage(String link, String fileName) throws IOException {
        String mFileName = fileName.replace(" ", "_")
                .replace(Pattern.compile("[.][.]+").pattern(), ".");
        String baseFolder;
        baseFolder = reactContext.getFilesDir().getAbsolutePath().toString();
        String filePath = baseFolder + "/" + mFileName;

        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("TODO: Exist: " + file.getUsableSpace() + ", " + file.getTotalSpace());
        } else {
            Request request = new Request.Builder()
                    .url(link)
                    .build();
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Failed to download file: " + response);
            }
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(response.body().bytes());
            System.out.println("TODO: file: " + filePath);
            fos.close();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    InputStream fileData = response.body().byteStream();
//
//
//                    if (fileData != null) {
//                        try {
//                            // true will be same as Context.MODE_APPEND
//                            FileOutputStream output = new FileOutputStream(new File(filePath), true);
//                            output.write(fileData.readAllBytes());
//                            output.close();
//                            System.out.println("TODO: file: " + filePath);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//            });
        }
    }

    public void setContext(ReactContext context) {
        reactContext = context;
    }
}
