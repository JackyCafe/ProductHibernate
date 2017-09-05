package model.dao;

import org.hibernate.SessionFactory;

public class DAO {
	public SessionFactory factory;
	public DAO(SessionFactory factory)
	{
		this.factory = factory;
	}

}
