package analyze;

import java.util.List;

public class Analyze {

    public static Info diff(List<User> previous, List<User> current) {
        int added = current.size();
        int changed = 0;
        int deleted = 0;
        boolean wasDeleted;
        for (var i : previous) {
            wasDeleted = true;
            for (var j : current) {
                if (i.id == j.id) {
                    added--;
                    wasDeleted = false;
                    if (!i.name.equals(j.name)) {
                        changed++;
                    }
                    break;
                }
            }
            if (wasDeleted) {
                deleted++;
            }
        }
        return new Info(added, changed, deleted);
    }

    static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class Info {
        private final int added;
        private final int changed;
        private final int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}
