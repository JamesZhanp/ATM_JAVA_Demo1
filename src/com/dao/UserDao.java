package com.dao;

import com.entity.UserEntity;
import com.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class UserDao {

    /**
     * 更新用户的数据
     * @param address
     * @param telNum
     * @param id
     */
    public void updateUser(String address,String telNum,int id){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            String hql = "UPDATE from UserEntity ue set ue.address = :add,ue.telNum = :tel where ue.id = :id";
            Query query = session.createQuery(hql);
            query.setString("add",address);
            query.setString("tel",telNum);
            query.setInteger("id",id);
            query.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session.isOpen() && session != null){
                session.close();
            }
        }
    }

    /**
     * 新增用户
     * @param userEntity
     */
    public void insert(UserEntity userEntity){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(userEntity);
            tx.commit();
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session != null || session.isOpen()){
                session.close();
            }
        }
    }

    /**
     * 获取用户信息
     * @param name
     */
    public UserEntity getUser(String name){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        UserEntity userEntity = null;
        try{
            Query query = session.createQuery("from UserEntity ue where ue.userName = :name");
            query.setString("name",name);
            if (query.list().size() > 0){
                userEntity = (UserEntity) query.list().get(0);
            }
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session.isOpen() || session != null)
                session.close();
        }
        return userEntity;
    }

    /**
     * 管理员修改用户信息
     * @param name
     * @param age
     * @param address
     * @param telNum
     * @param IDNum
     */
    public void managerUpdateMes(String name,int age,String address,String telNum,String IDNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            String hql = "UPDATE from UserEntity ue set ue.age = :age,ue.telNum = :telNum,ue.address = :address,ue.idNum = :ID where ue.userName = :name";
            Query query = session.createQuery(hql);
            query.setInteger("age",age);
            query.setString("telNum",telNum);
            query.setString("address",address);
            query.setString("ID",IDNum);
            query.setString("name",name);
            query.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session != null || session.isOpen()){
                session.close();
            }
        }
    }
}
