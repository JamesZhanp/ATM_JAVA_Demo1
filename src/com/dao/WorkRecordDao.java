package com.dao;

import com.entity.WorkrecordEntity;
import com.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;


public class WorkRecordDao {

    /**
     * 将交易记录保存到数据表当中
     * @param workrecordEntity
     * */
    @Test
    public void insert(WorkrecordEntity workrecordEntity){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(workrecordEntity);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.getMessage();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    /**
     * 获取数据库当中的卡号为cardNum的卡片的所有的交易记录
     * @param cardNum
     * */
    @Test
    public List<WorkrecordEntity> getAllRecords(int cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<WorkrecordEntity> workrecordEntityList = new LinkedList<WorkrecordEntity>();
        try {
            String hql = "from WorkrecordEntity we where we.cardNum = :cn";
            Query query = session.createQuery(hql);
            query.setInteger("cn",cardNum);
            workrecordEntityList = query.list();
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            throw new RuntimeException(e.getMessage());
        }finally {
            if (session.isOpen() && session != null){
                session.close();
            }
        }
        return workrecordEntityList;
    }
}
