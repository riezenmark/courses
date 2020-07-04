package gc;

public class GCUsage {
    public static void main(String[] args) {
        User user1 = new User("Test1");
        User user2 = new User("Test2");
        User user3 = new User("Test3");
        User user4 = new User("Test4");
        User user5 = new User("Test5");
        info();
    }

    public static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }
    }

    public static void info() {
        int mb = 1024 * 1024;

        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap Utilization Statistics [MB] #####");
        System.out.println("Used memory: " + (runtime.totalMemory() - runtime.freeMemory()) / mb);
        System.out.println("Free memory: " + (runtime.freeMemory()) / mb);
        System.out.println("Total memory: " + (runtime.totalMemory()) / mb);
        System.out.println("Maximum memory: " + (runtime.maxMemory()) / mb);
    }
}
