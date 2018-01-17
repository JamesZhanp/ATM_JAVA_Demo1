package com.dao;

import com.entity.ManagerEntity;
import com.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManagerDao {
    public ManagerEntity getManager(String managerNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        ManagerEntity managerEntity = new ManagerEntity();
        try{
            String hql = "from ManagerEntity me where me.managerNum = :pas";
            Query query = session.createQuery(hql);
            query.setString("pas",managerNum);
            List<ManagerEntity> managerEntityList = query.list();
            if (managerEntityList.size() > 0){
                managerEntity = managerEntityList.get(0);
            }
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session.isOpen() || session != null){
                session.close();
            }
        }
        return managerEntity;
    }
}
