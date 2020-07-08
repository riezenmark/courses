package threads;

public class ThreadUsage {

    public static class Calculate implements Runnable {
        private final String name;

        public Calculate(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s", this.name));
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        new Thread(new Calculate("thread 1")).start();
        new Thread(new Calculate("thread 2")).start();
        new Thread(() -> System.out.println("thread lambda")).start();
        new Thread() {
            @Override
            public void run() {
                System.out.println("thread anonymous");
            }
        }.start();
        System.out.println("Finish");
    }
}
