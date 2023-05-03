package ex_004_get_some_fields;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

/**
 * Created by Asus on 01.11.2017.
 */
public class HibernateUtil {

    private static final Logger LOG = Logger.getLogger(HibernateUtil.class.getName());
    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration()
                    .configure("ex_004_config.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            LOG.error(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

}
