package com.liubo.concurrent;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hzlbo on 2017/1/5 0005.
 */
public class Downloader extends Thread {
    private InputStream in;
    private OutputStream out;
    private ArrayList<ProgressListener> listeners;


    public Downloader(URL url, String outputFileName) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFileName);
        listeners = new ArrayList<>();
    }

    public synchronized void addListener(ProgressListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(ProgressListener listener) {
        listeners.remove(listener);
    }

    private synchronized void updateProgress(int n) {
        for (ProgressListener listener : listeners) {
            listener.onProgress(n);
        }
    }

    @Override
    public void run() {
        int n = 0;
        int total = 0;
        byte[] buffer = new byte[1024];

        try {
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
                total += n;
                updateProgress(total);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
