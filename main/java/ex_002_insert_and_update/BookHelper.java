package ex_002_insert_and_update;

import ex_002_insert_and_update.entity.Author;
import ex_002_insert_and_update.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookHelper {

    private final SessionFactory sessionFactory;

    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    public Book addBook(Book book) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();

        return book;
    }

    public List<Book> getBookList() {

        Session session = sessionFactory.openSession();


        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> root = criteriaQuery.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)

        criteriaQuery.select(root);// необязательный оператор, если просто нужно получить все значения

        //этап выполнения запроса
        Query query = session.createQuery(criteriaQuery);

        List<Book> bookList = query.getResultList();

        session.close();

        return bookList;
    }

    public Book getBookById(long id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id); // получение объекта по id
        session.close();
        return book;
    }

    public Book updateBook(Book book) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(book);
        session.getTransaction().commit();
        session.close();

        return book;
    }
}
