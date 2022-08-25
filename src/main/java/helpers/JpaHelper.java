package helpers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.apache.logging.log4j.core.util.Assert;
import pojo.Test;

import java.util.ArrayList;
import java.util.List;

public class JpaHelper {

    public static EntityManager returnEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ur");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public static Test getRowInTestById(int testId) {
        return returnEM().find(Test.class, testId);
    }

    public static void createInTest(String name, String method_name, int project_id, int session_id, String env) {
        Test test = new Test();
        test.setName(name);
        test.setMethod_name(method_name);
        test.setProject_id(project_id);
        test.setSession_id(session_id);
        test.setEnv(env);

        EntityManager em = returnEM();

        em.getTransaction().begin();
        em.persist(test);
        em.getTransaction().commit();
        em.close();
    }

    public static void removeFromTestById(int id) {
        EntityManager em = returnEM();
        em.getTransaction().begin();

        Query query = em.createQuery("DELETE FROM Test WHERE id = " + id);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
    }

    @SuppressWarnings("unchecked")
    public static List<Test> getAllTest() {
        EntityManager em = returnEM();
        List<Test> testList = em.createQuery("SELECT e FROM Test e").getResultList();
        return testList;
    }

    public static void main(String[] args) {
        ArrayList<Test> actualTestList = (ArrayList<Test>) getAllTest();

        ArrayList<Test> doubleIdList = new ArrayList<>();
        for (int i = 0; i < actualTestList.size(); i++) {
            if (findRepeater(String.valueOf(actualTestList.get(i).getId()))) {
                doubleIdList.add(actualTestList.get(i));
            }
        }
        for (Test tst : doubleIdList) {
            System.out.println(tst);
        }
    }

    public static boolean findRepeater(String string) {
        String s = string;
        int distinct = 0;

        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; j < s.length(); j++) {

                if (s.charAt(i) == s.charAt(j)) {
                    distinct++;

                }
                if (distinct >= 2) {
                    return true;
                }
            }
            String d = String.valueOf(s.charAt(i)).trim();
            s = s.replaceAll(d, "");
            distinct = 0;

        }
        return false;
    }

}
