package ex_004_get_some_fields;

import ex_004_get_some_fields.entity.Author;
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Created by Asus on 04.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {
        AuthorHelper authorHelper = new AuthorHelper();

        List<Author> authors = authorHelper.getAuthorList();

        for (Author author : authors) {
//            LOG.debug(author.getId() + " " + author.getName() + " " + author.getLastName());
            LOG.debug(author);
            System.out.println(author);
        }

    }

}
