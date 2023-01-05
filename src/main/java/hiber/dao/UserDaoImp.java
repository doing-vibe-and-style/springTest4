package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

   @Transactional
   @Override
   public List<User> getUserCar(String model, int series) {
      String HQL = "from User as u where u.car.model = :model and u.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL, User.class)
              .setParameter("model", model)
              .setParameter("series", series);
      return query.getResultList();
   }

   @Override
   @Transactional
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
