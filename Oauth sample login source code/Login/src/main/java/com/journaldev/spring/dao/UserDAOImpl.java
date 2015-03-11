package com.journaldev.spring.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.UserInfo;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	private  final static Logger log=Logger.getLogger(UserDAOImpl.class);
	@Autowired
	 SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		if(sessionFactory==null)log.error("");;
		this.sessionFactory = sessionFactory;
	}


	@Override
	public List<UserInfo> LoginUser(String username, String password) {
		List<UserInfo> login_user=null;
		try{
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(UserInfo.class);
		cr.add(Restrictions.eq("userId", username));
		cr.add(Restrictions.eq("passwordEnc", password));
		login_user=cr.list();
		System.out.println("List Size : "+login_user.size());
		}catch(HibernateException hiber_ex){
			
		}
		return login_user;
	}

}
