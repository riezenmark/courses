package io;

public class Args {
    private String directory;
    private String exclude;
    private String output;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                this.directory = args[i + 1];
                i++;
            }
            if (args[i].equals("-e")) {
                this.exclude = args[i + 1];
                i++;
            }
            if (args[i].equals("-o")) {
                this.output = args[i + 1];
                i++;
            }
        }
    }

    public String directory() {
        return directory;
    }

    public String exclude() {
        return exclude;
    }

    public String output() {
        return output;
    }
}
