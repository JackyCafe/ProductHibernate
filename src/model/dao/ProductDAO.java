package model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.misc.HibernateUtil;
import model.ProductBean;
import model.Interface.IDAO;

public class ProductDAO extends DAO implements IDAO<ProductBean> {
	public static SessionFactory factory;
	public static Transaction trx;
	public static Session session;

	public static void main(String[] args) {
		factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
 		ProductDAO dao = new ProductDAO(factory);
		/*  Insert
		 * 
		 * 
		 * */
		try {
			trx = dao.getSession().beginTransaction();
			ProductBean insert = new ProductBean();
			insert.setId(0);
			insert.setName("hTC");
			insert.setCountry("Taiwan");
			dao.insert(insert);
			System.out.println("insert "+insert);
		 	trx.commit();	
		 	
		}catch (Exception e) {
 			for (StackTraceElement s:e.getStackTrace())
 			{
 				System.out.println(s);
 			}
 			trx.rollback();
		}
		
		
		/* Select 
		 * */
		try {
			trx = dao.getSession().beginTransaction();
			List<ProductBean> select = (List<ProductBean>) dao.select("hTC");
			System.out.println("select" +select);
 		 	trx.commit();	
		 	
		}catch (Exception e) {
			for (StackTraceElement s:e.getStackTrace())
 			{
 				System.out.println(s);
 			}
 			trx.rollback();
		}
		
		/* Update
		 * */
		try {
			trx = dao.getSession().beginTransaction();
			ProductBean update = new ProductBean();
			update.setId(1);
			update.setName("hTC");
			update.setCountry("China");
			dao.update(update);
			System.out.println("update "+update);
		 	trx.commit();	
		 	
		}catch (Exception e) {
			for (StackTraceElement s:e.getStackTrace())
 			{
 				System.out.println(s);
 			}
			trx.rollback();
		}
		
		/* Delete
		 * */
		try {
			trx = dao.getSession().beginTransaction();
			Boolean delete = dao.delete(1);
			System.out.println("delect "+delete);
		 	trx.commit();	
		 	
		}catch (Exception e) {
 			trx.rollback();
		}
		
 	}

	
	public ProductDAO(SessionFactory factory) {
 		super(factory);
 	}
	
	
	
	@Override
	public List<ProductBean> select() {
 		return this.getSession().createQuery("from ProductBean", ProductBean.class).getResultList();
 	}

	/*
	 * 新增
	 */
	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean product = select(bean.getId());
		if (product == null) {
			//System.out.println(bean);
			this.getSession().save(bean);
			return bean;

		} else {
			return null;
		}

	}

	/*
	 * 刪除
	 * 
	 */
	@Override
	public Boolean delete(ProductBean bean) {
		return delete(bean.getId());
	}

	
	/*
	 * 刪除
	 * 
	 */
	@Override
	public Boolean delete(int id) {
		ProductBean temp = select(id);
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}	}

	/*更新
	 * 
	 * */
	@Override
	public ProductBean update(ProductBean bean) {
		ProductBean tmp = select(bean);
		if (tmp!=null)
		{
			tmp.setId(bean.getId());
			tmp.setName(bean.getName());
			tmp.setCountry(bean.getCountry());
			
		 	
		}else {
			return null;
		}
		return null;
	}

	@Override
	public ProductBean select(int id) {
			return this.getSession().get(ProductBean.class, id);
	}

	@Override
	public ProductBean select(ProductBean bean) {
 		return select(bean.getId());
	}

	@Override
	public List<ProductBean> select(String name) {
		return this.getSession().createQuery("from ProductBean", ProductBean.class).getResultList();

	}

	@Override
	public Session getSession() {
 		return factory.getCurrentSession();

	}

}
