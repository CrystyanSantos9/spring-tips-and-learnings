package com.cryss.tipsandlearnings;

import java.util.HashSet;
import java.util.Set;

import com.cryss.tipsandlearnings.model.CartOwnerSide;
import com.cryss.tipsandlearnings.model.ItemInverseSide;
import com.cryss.tipsandlearnings.oneToMany.config.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateOneIsOwningSide {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateOneIsOwningSide.class);

    public static void main(String[] args) {

        CartOwnerSide cart = new CartOwnerSide();
        CartOwnerSide cart2 = new CartOwnerSide();

        ItemInverseSide item1 = new ItemInverseSide(cart);
        ItemInverseSide item2 = new ItemInverseSide(cart2);

        Set<ItemInverseSide> itemsSet = new HashSet<>();
        itemsSet.add(item1);
        itemsSet.add(item2);

        cart.setItems(itemsSet);


        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        LOGGER.info("Session created");

        Transaction tx;
        try {
            // start transaction
            tx = session.beginTransaction();

            // Save the Model object
            session.save(cart);
            session.save(cart2);
            session.save(item1);
            session.save(item2);

            // Commit transaction
            tx.commit();

            session = sessionFactory.getCurrentSession();
            tx = session.beginTransaction();

            item1 = session.get(ItemInverseSide.class, 1L);
            item2 = session.get(ItemInverseSide.class, 2L);
            tx.commit();

            LOGGER.info("item1 ID={}, Foreign Key CartOwnerSide ID={}", item1.getId(), item1.getCart ().getId());
            LOGGER.info("item2 ID={}, Foreign Key CartOwnerSide ID={}", item2.getId(), item2.getCart ().getId());

        } catch (Exception e) {
            LOGGER.error("Exception occurred", e);
        } finally {
            if (!sessionFactory.isClosed()) {
                LOGGER.info("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    }
}