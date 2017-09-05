package model.Interface;

import java.util.List;

import org.hibernate.Session;
 
public interface IDAO<T> {
	public abstract List<T> select();
	public abstract T insert(T bean);
	public abstract Boolean delete(T bean);
	public abstract Boolean delete (int id);
	public abstract T update(T bean);
	public abstract T select(int id);
	public abstract T select(T bean);
	public abstract List<T> select(String name);
 
	public abstract Session getSession(); 
	
	
	
}
