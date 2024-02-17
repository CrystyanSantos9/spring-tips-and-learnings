//package com.cryss.tipsandlearnings;
//
//import com.cryss.tipsandlearnings.model.Cart;
//import com.cryss.tipsandlearnings.model.Item;
//import com.cryss.tipsandlearnings.oneToMany.config.HibernateAnnotationUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class HibernateManyIsOwningSide {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateManyIsOwningSide.class);
//
//
//    public static void main(String[] args) {
//
//        Cart cart = new Cart();
//        Cart cart2 = new Cart();
//
//
//        Item item1 = new Item(cart);
//        Item item2 = new Item(cart2);
//
//        Set<Item> itemsSet = new HashSet<> ();
//        itemsSet.add(item1);
//        itemsSet.add(item2);
//
//        cart.setItems (itemsSet);
//
//        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
//        Session session = sessionFactory.getCurrentSession();
//        LOGGER.info("Session created");
//
//        try{
//
//            // start transaction
//            Transaction tx = session.beginTransaction();
//
//            // Save the Model object
//            session.save(cart);
//            session.save(item1);
//            session.save(item2);
//
//            // Commit transaction
//            tx.commit();
//
//            LOGGER.info("Cart ID={}", cart.getId());
//            LOGGER.info("item1 ID={}, Foreign Key Cart ID={}", item1.getId(), item1.getCart().getId());
//            LOGGER.info("item2 ID={}, Foreign Key Cart ID={}", item2.getId(), item2.getCart().getId());
//
//        }catch (Exception e){
//            LOGGER.error("Exception occurred", e);
//        } finally {
//            if (!sessionFactory.isClosed()) {
//                LOGGER.info("Closing SessionFactory");
//                sessionFactory.close();
//            }
//        }
//
//    }
//}
