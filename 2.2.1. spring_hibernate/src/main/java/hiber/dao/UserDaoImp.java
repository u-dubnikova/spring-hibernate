package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("select u from User u, Car c where c = u.car and c.model = :m and c.series = :s");
      query.setParameter("m", model);
      query.setParameter("s", series);
      return query.getResultList();
   }

}
