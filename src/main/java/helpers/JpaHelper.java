package helpers;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.testng.Assert;
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


    //TODO how to make this more generic?
    public static void createNewTest(String name, String method_name, int project_id, int session_id, String env) {
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

    public static <T> T readRowInTableById(Class<T> table, int id) {
        return returnEM().find(table, id);
    }

    public static void updateQuery(String tableName, String gridName, String newValue, int id) {
        StringBuilder sbSet = new StringBuilder("UPDATE ");
        sbSet.append(tableName);
        sbSet.append(" set ");
        sbSet.append(gridName);
        sbSet.append(" =");
        sbSet.append("'" + newValue + "'");
        sbSet.append("WHERE ID =" + id);
        createQuery(sbSet.toString());
    }

    public static void deleteFromTableById(String tableName, int id) {
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(tableName);
        sb.append(" WHERE id=");
        sb.append(id);
        createQuery(sb.toString());
    }

    public static <T> List<T> getAllDataFromTable(Class<T> entity) {
        EntityManager em = returnEM();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entity);
        Root<T> rootEntry = cq.from(entity);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public static <T> ArrayList<T> returnDoubleIdList(int howManyRows, Class<T> entity) {
        ArrayList<? extends Test> actualTestList = (ArrayList<? extends Test>) getAllDataFromTable(entity);
        ArrayList<T> doubleIdList = new ArrayList<>();
        for (Test t : actualTestList) {
            if (StringUtils.findRepeater(String.valueOf(t.getId())) & doubleIdList.size() < howManyRows) {
                doubleIdList.add((T) t);
            }
        }
        return doubleIdList;
    }


    public static void updateDoubleIds(int howMany, Class entity) {
        ArrayList<? extends Test> doubleIdList = returnDoubleIdList(howMany, entity);
        for (int i = 0; i < doubleIdList.size(); i++) {
            updateQuery(entity.getSimpleName(), "name", "randomName" + i, doubleIdList.get(i).getId());
        }
    }

    public static void deleteDoubleIds(int howMany, Class table) {
        ArrayList<? extends Test> doubleIdList = returnDoubleIdList(howMany, table);
        for (int i = 0; i < doubleIdList.size(); i++) {
            deleteFromTableById(table.getSimpleName(), doubleIdList.get(i).getId());
            // TODO figure out if its okay to leave this here
            Assert.assertNull(readRowInTableById(Test.class, i));
        }
    }

}
