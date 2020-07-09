package wget;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Downloader downloader = new Downloader(Integer.parseInt(args[1]));
        downloader.download(args[0]);
    }
}
