package pojo;

public class Library {
    private static void show(Book... books) {   //varargs method
        for(Book b : books) {
            System.out.println(b.getName() + " - " + b.getPages() + " pages");
        }
    }

    public static void main(String[] args) {
        Book book1 = new Book("Master and Margaret", 300);
        Book book2 = new Book("Dialogs", 1250);
        Book book3 = new Book("Faust", 289);
        Book book4 = new Book("Clean Code", 420);

        Book[] books = new Book[4];
        books[0] = book1;
        books[1] = book2;
        books[2] = book3;
        books[3] = book4;

        show(books);

        System.out.println("\nReplacing first and fourth books\n");

        Book tmp = books[0];
        books[0] = books[3];
        books[3] = tmp;

        show(books);

        System.out.println("\nShow only books with name \"Clean Code\"\n");

        for(Book b : books) {
            if (b.getName().equals("Clean Code")) {
                System.out.println(b.getName() + " - " + b.getPages() + " pages");
            }
        }
    }
}
