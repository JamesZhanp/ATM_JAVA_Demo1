package com.dao;

import com.entity.CardEntity;
import com.entity.InformationAccessDB;
import com.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class CardDao {

    public void insert(CardEntity cardEntity){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.save(cardEntity);
            tx.commit();
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            if (session.isOpen() || session != null){
                session.close();
            }
        }
    }
    /**
     * 获取卡号为用户输入的卡号的整体
     * @param cardNum
     * */
    public CardEntity getCardPas(String cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        CardEntity cardEntity = new CardEntity();
        try{
            String hql = "from CardEntity ce where ce.cardNum = :num";
            Query query = session.createQuery(hql);
            query.setString("num",cardNum);
            List<CardEntity> cardEntityList = query.list();
            if (cardEntityList.size() > 0){
                cardEntity = cardEntityList.get(0);
            }else{
                cardEntity = null;
            }
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cardEntity;
    }

    /**
     * 三个表结构连接的查询
     * 用户查询数据
     * @param cardNum
     * */
    public List<InformationAccessDB> InformationAccess(String cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        List<InformationAccessDB> result = new LinkedList<>();
        try{
            String hql = "select ce.cardNum,ue.userName,ue.gender,ue.age,ue.IDNum,c.cityName from user ue, card ce,city c where ue.userId = ce.userId and ce.registeredCity = c.cityId and ce.cardNum = :cn";
            Query query = session.createSQLQuery(hql);
            query.setString("cn",cardNum);
            result = query.list();
            if (result.size() > 0){
                return result;
            }
        }catch (Exception e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return null;
    }

    /**
     * 用户取款函数
     * @param money
     * @param cardNum
     * */
    public void withDrwal(double money,String cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            String hql = "UPDATE from CardEntity ce set ce.balance = ce.balance - :my where ce.cardNum = :cn and ce.isUsed = 1";
            Query query = session.createQuery(hql);
            query.setDouble("my",money);
            query.setString("cn",cardNum);
            query.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            if(session != null && session.isOpen())
                session.close();
        }
    }

    /**
     * 用户存款函数
     * @param money
     * @param cardNum
     * */
    public void despoit(double money,String cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
//            isUsed表示这个账户是否在用，若为0，认为注销，无法存取款
            String hql = "UPDATE from CardEntity ce set ce.balance = ce.balance + :my where ce.cardNum = :cn and ce.isUsed = 1";
            Query query = session.createQuery(hql);
            query.setDouble("my",money);
            query.setString("cn",cardNum);
            query.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            if(session != null && session.isOpen())
                session.close();
        }
    }

    /**
     * 用户转账
     * 转钱方少多少钱，收钱方多多少钱
     * 转账方少的钱为转账的钱加上手续费
     * @param giveMoneyCardNum
     * @param getMoneyCardNum
     * @param moneyNum
     * @param fee
     * */
    public void transfor(String giveMoneyCardNum,String getMoneyCardNum,double moneyNum,double fee){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        double totalMoneyNum = moneyNum + fee;
        try{
            String hql = "UPDATE from CardEntity ce set ce.balance = ce.balance - :my where ce.cardNum = :cn";
            String hql1 = "UPDATE from CardEntity ce set ce.balance = ce.balance + :my where ce.cardNum = :cn";
            Query query = session.createQuery(hql);
            Query query1 = session.createQuery(hql1);
            query.setDouble("my",totalMoneyNum);
            query.setString("cn",giveMoneyCardNum);
            query1.setDouble("my",moneyNum);
            query1.setString("cn",getMoneyCardNum);
            query.executeUpdate();
            query1.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            throw new RuntimeException(e.getMessage());
        }finally {
            if(session.isOpen() && session != null)
                session.close();
        }
    }

    /**
     * 用户修改密码
     * @param password
     * @param cardNum
     * */
    public void updatePas(String password,String cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            String hql = "UPDATE FROM CardEntity ce SET ce.password = :pas where ce.cardNum = :cn";
            Query queryUpdate = session.createQuery(hql);
            queryUpdate.setString("pas",password);
            queryUpdate.setString("cn",cardNum);
            int ret = queryUpdate.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    /**
     * 注销用户
     * @param cardNum
     * */
    public void logOut(String cardNum){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        try{
            String hql = "UPDATE CardEntity ce set ce.isUsed = 0 where ce.cardNum = :cn";
            Query query = session.createQuery(hql);
            query.setString("cn",cardNum);
            query.executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        finally {
            if(session != null && session.isOpen()){
                session.close();            }
        }
    }

    /**
     * 根据卡的ID返回卡的实例
     * @param cardId
     * @return
     */
    public CardEntity getCardMessage(int cardId){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        CardEntity cardEntity = new CardEntity();
        try{
            String hql = "from CardEntity ce where ce.cardId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",cardId);
            List<CardEntity> list = query.list();
            if (list.size() > 0){
                cardEntity = list.get(0);
            }
            tx.commit();
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return cardEntity;
    }
}
