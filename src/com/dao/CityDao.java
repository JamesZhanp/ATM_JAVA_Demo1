package com.dao;

import com.entity.CityEntity;
import com.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDao {

    /**
     * return city Entity
     * @param cityName
     * */
    public CityEntity getCity(String cityName){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        CityEntity cityEntity = new CityEntity();
        try{
            String hql = "from CityEntity ce where ce.cityName = :cn";
            Query query = session.createQuery(hql);
            query.setString("cn",cityName);
            List<CityEntity> list = query.list();
            if (list.size() > 0){
                cityEntity = list.get(0);
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
        return cityEntity;
    }

    /**
     * @param  cityId
     */
    public CityEntity getCityName(int cityId){
        Session session = HibernateUtils.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        CityEntity cityEntity = new CityEntity();
        try{
            String hql = "from CityEntity ce where ce.cityId = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",cityId);
            List<CityEntity> list = query.list();
            if (list.size() > 0){
                cityEntity = list.get(0);
            }
            tx.commit();
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return cityEntity;
    }
}
