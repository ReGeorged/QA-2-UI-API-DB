package helpers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import pojo.Test;

public class JpaHelper {

    public static EntityManager returnEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ur");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public static Test getTestViaId(int testId) {
        return returnEM().find(Test.class, testId);
    }

    public static void InsertTest(String name, String method_name, int project_id, int session_id, String env) {
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


    }

    public static void removeTest(int id) {
        EntityManager em = returnEM();
        //Query query =em.createQuery("DELETE FROM test WHERE id =3");
       // query.executeUpdate();
    }

    public static void main(String[] args) {
        System.out.println(getTestViaId(3));
        removeTest(3);
        System.out.println(getTestViaId(3));

    }
}
