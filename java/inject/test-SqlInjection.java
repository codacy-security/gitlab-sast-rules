// License: LGPL-3.0 License (c) find-sec-bugs
package injection;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.ArrayList;

public class SqlInjection {

    public class UserEntity {
        private Long id;
        private String test;
        public String getTest() {
            return test;
        }
        public void setTest(String test) {
            this.test = test;
        }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
    }

    private static final PersistenceManagerFactory pmfInstance =
            JDOHelper.getPersistenceManagerFactory("transactions-optional");


    public static PersistenceManager getPM() {
        return pmfInstance.getPersistenceManager();
    }

    public void testJdoQueries(String input) {
        PersistenceManager pm = getPM();

        pm.newQuery("select * from Users where name = " + input);

        pm.newQuery("sql", "select * from Products where name = " + input);

        //Test for false positive

        pm.newQuery("select * from Config");

        final String query = "select * from Config";
        pm.newQuery(query);

        pm.newQuery("sql", query);
    }

    public void testJdoQueriesAdditionalMethodSig(String input) {
        PersistenceManager pm = getPM();

        pm.newQuery(UserEntity.class,new ArrayList(),"id == "+ input); //Injection?

        pm.newQuery(UserEntity.class,new ArrayList(),"id == 1");

        pm.newQuery(UserEntity.class,"id == "+ input); //Injection?

        pm.newQuery(UserEntity.class,"id == 1");

        pm.newQuery((Extent) null,"id == "+input); //Injection?

        pm.newQuery((Extent) null,"id == 1");

    }
}
