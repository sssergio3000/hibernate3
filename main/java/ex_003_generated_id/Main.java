package ex_003_generated_id;

import ex_003_generated_id.entity.Author;
import org.jboss.logging.Logger;

/**
 * Created by Asus on 04.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {

        // Case 1. GenerationType.IDENTITY example
        AuthorHelper authorHelper = new AuthorHelper();

        // Author author = new Author("John Tolkien");
        /*
        RESULT:
        Hibernate: alter table Author add column name22 varchar(255)
        Hibernate: insert into Author (name22) values (?)
         */

        // authorHelper.addAuthor(author);

        // Case 2. GenerationType.SEQUENCE
        generationTypeSequence();

    }

    private static void generationTypeSequence() {
        AuthorHelper authorHelper = new AuthorHelper();

        authorHelper.addAuthor(new Author("John Tolkien")); // hbm2ddl.auto = create

        authorHelper.addAuthor(new Author("Miguel de Cervantes")); // hbm2ddl.auto = update
    }



}
