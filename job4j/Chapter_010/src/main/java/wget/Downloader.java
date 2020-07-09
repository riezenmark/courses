package wget;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    private static final int BUFFER_SIZE = 4096;
    private HttpURLConnection connection;
    private static double receivedBytes = 0.0;
    private final int speedLimit;

    public Downloader(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void download(String link) throws IOException {
        if (connect(link) == HttpURLConnection.HTTP_OK) {
            String filename = getFileName(link);
            printFileInfo(filename);
            download(connection.getInputStream(), new FileOutputStream(filename));
            System.out.println("File Downloaded");
        } else {
            System.out.println("No File to Download.");
        }
        connection.disconnect();
    }

    private int connect(String link) throws IOException {
        URL url = new URL(link);
        connection = (HttpURLConnection) url.openConnection();
        return connection.getResponseCode();
    }

    private String getFileName(String link) {
        String filename = "";
        String disposition = connection.getHeaderField("Content-Disposition");
        if (disposition != null) {
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                filename = disposition.substring(index + 10, disposition.length() - 1);
            }
        } else {
            filename = link.substring(link.lastIndexOf("/") + 1);
        }
        return filename;
    }

    private void printFileInfo(String filename) {
        System.out.println("Content-Type = " + connection.getContentType());
        System.out.println("Content-Disposition = " + connection.getHeaderField("Content-Disposition"));
        System.out.println("Content-Length = " + connection.getContentLength());
        System.out.println("fileName = " + filename);
    }

    private void download(InputStream inputStream, OutputStream outputStream) throws IOException {
        SpeedChecker speedChecker = new SpeedChecker(this.speedLimit);
        Thread thread = new Thread(speedChecker);
        int bytesRead;
        byte[] buffer = new byte[BUFFER_SIZE];

        thread.start();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            if (!speedChecker.isOverLimit()) {
                receivedBytes += bytesRead;
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        thread.interrupt();
        outputStream.close();
        inputStream.close();
    }

    private static class SpeedChecker implements Runnable {
        private boolean speedIsOverLimit = false;
        private final int speedLimit;

        public SpeedChecker(int speedLimit) {
            this.speedLimit = speedLimit;
        }

        @Override
        public void run() {
            double previousReceivedBytes = receivedBytes;
            while (!Thread.currentThread().isInterrupted()) {
                double bytesReadInASecond = receivedBytes - previousReceivedBytes;
                printSpeed(bytesReadInASecond);
                checkSpeed(bytesReadInASecond);
                previousReceivedBytes = receivedBytes;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private void printSpeed(double bytesReadInASecond) {
            System.out.print(
                    "\r>> Speed: "
                    + Double.valueOf(bytesReadInASecond / 1024).intValue()
                    + " Kb/sec"
            );
        }

        private void checkSpeed(double bytesReadInASecond) {
            this.speedIsOverLimit = bytesReadInASecond > this.speedLimit;
        }

        public boolean isOverLimit() {
            return this.speedIsOverLimit;
        }
    }
}
