package helpers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import pojo.Test;
import utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class JpaHelper {

    private static EntityManager returnEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ur");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    private static void createQuery(String setQuery) {
        EntityManager em = returnEM();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(setQuery);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
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

    public static Test readRowInTestById(int testId) {
        return returnEM().find(Test.class, testId);
    }

    public static void updateQuery(int id, String gridName, String newValue) {
        createQuery("UPDATE test SET " + gridName + "='" + newValue + "' where id= " + id);
    }

    public static void deleteFromTestById(int id) {
        createQuery("DELETE FROM test WHERE id="+id);
    }
    @SuppressWarnings("unchecked")
    public static List<Test> getAllTest() {
        EntityManager em = returnEM();
        List<Test> testList = em.createQuery("SELECT e FROM Test e").getResultList();
        return testList;
    }

    public static ArrayList<Test> returnRepeatDigitIdList(int howManyRows) {
        ArrayList<Test> actualTestList = (ArrayList<Test>) getAllTest();
        ArrayList<Test> doubleIdList = new ArrayList<>();
        for (int i = 0; i < actualTestList.size(); i++) {
            if (StringUtils.findRepeater(String.valueOf(actualTestList.get(i).getId())) & doubleIdList.size() < howManyRows) {
                doubleIdList.add(actualTestList.get(i));
            }
        }
        return doubleIdList;
    }


    public static void updateDoubleIds(int howMany) {
        ArrayList<Test> doubleIdList = returnRepeatDigitIdList(howMany);
        for (int i = 0; i < doubleIdList.size(); i++) {
            updateQuery(doubleIdList.get(i).getId(), "name", "randomName" + i);
        }
    }

    public static void deleteDoubleIds(int howMany) {
        ArrayList<Test> doubleIdList = returnRepeatDigitIdList(howMany);
        for (int i = 0; i < doubleIdList.size(); i++) {
            deleteFromTestById(doubleIdList.get(i).getId());
        }
    }

}
