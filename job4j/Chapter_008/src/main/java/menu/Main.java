package menu;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu(
                List.of(
                        new Menu.ItemSet(
                                "Task 1.",
                                List.of(
                                        new Menu.ItemSet(
                                                "Task 1.1.",
                                                List.of(
                                                        new Menu.Item(
                                                                "Task 1.1.1.",
                                                                new Action("Action 1.1.1.")
                                                        ),
                                                        new Menu.Item(
                                                                "Task 1.1.2.",
                                                                new Action("Action 1.1.2.")
                                                        )
                                                )
                                        ),
                                        new Menu.ItemSet(
                                                "Task 1.2.",
                                                List.of(
                                                        new Menu.Item(
                                                            "Task 1.2.1.",
                                                                new Action("Action 1.2.1.")
                                                        )
                                                )
                                        )
                                )
                        ),
                        new Menu.ExecutableItemSet(
                                "Task 2.",
                                new Action("Action 2."),
                                List.of(
                                        new Menu.Item(
                                                "Task 2.1.",
                                                new Action("Action 2.1.")
                                        ),
                                        new Menu.Item(
                                                "Task 2.2.",
                                                new Action("Action 2.2.")
                                        )
                                )
                        )
                )
        );

        System.out.println("=========================");
        menu.execute("Task 1.");
        menu.execute("Task 1.1.");
        menu.execute("Task 1.1.1.");
        menu.execute("Task 1.1.2.");
        menu.execute("Task 1.2.");
        menu.execute("Task 1.2.1.");
        menu.execute("Task 2.");
        menu.execute("Task 2.1.");
        menu.execute("Task 2.2.");
        System.out.println("=========================");

        menu.formattedPrint("\t", 0);
    }
}
