package ex_002_insert_and_update;

import ex_002_insert_and_update.entity.Author;
import ex_002_insert_and_update.entity.Book;
import org.hibernate.Session;
import org.jboss.logging.Logger;

import java.util.List;


public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {
        AuthorHelper authorHelper = new AuthorHelper();
        BookHelper bookHelper = new BookHelper();

        addBook(bookHelper);
        // Case 1. Add one more author
//         addOneMoreAuthor(authorHelper);

        // Case 2. Apply 1NF to 'name' db column data
/*
        List<Author> allAuthors = authorHelper.getAuthorList();
        for(Author a : allAuthors) {
            String[] namePieces = a.getName().split("\\s");
            if(namePieces.length == 2) {
                String firstName = namePieces[0];
                String lastName = namePieces[1];
                a.setName(firstName);
                a.setLastName(lastName);
                authorHelper.updateAuthor(a);
            }
        }
*/

        // Case 3. @DynamicUpdate annotation
        // dynamicUpdateExample();

        // Case 4. session.flush() example
        sessionFlush();

    }

    private static void addOneMoreAuthor(AuthorHelper authorHelper) {
        Author author = new Author("Clive", "Lewis");
        authorHelper.addAuthor(author);
    }

    private static void dynamicUpdateExample() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Author author = session.get(Author.class, 1L);

        // Case 1. Update entity without @DynamicUpdate annotation
        // only 1 field is changed, but Hibernate tries to update all fields in DB:
        // Hibernate: update Author set last_name=?, name=? where id=?
        author.setName("Franz4");
        author.setLastName("Kafka4");
        session.update(author);

        // add @DynamicUpdate annotation and try again
        // RESULT: only the modified field is sent to the DB
        // Hibernate: update Author set name=? where id=?

        session.getTransaction().commit();
        session.close();
    }

    private static void sessionFlush() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

            for (int i = 0; i < 200; i++) {
            session.save(new Author("Author" + i));

            if (i % 10 == 0) {
                session.flush();
                System.out.println("\u001B[31m"+ "flushed" + "\u001B[0m");
            }
        }

        session.getTransaction().commit();
        session.close();


    }

    private static Book addBook(BookHelper bookHelper) {
        Book book = new Book("Novel1", 1);
        bookHelper.addBook(book);
        List<Book> allBooks = bookHelper.getBookList();
        for (Book allBook : allBooks) {
            System.out.println(allBook);
        }
        return book;


    }




}


