package il.ac.shenkar.finalproject.model;

import javax.persistence.Entity;

/**
 * User object - represent user of DB
 */
@Entity
public class User {
	private String name;
	private String pass;
	private int id;	

	public User() {

	}

	/**
	 * User constructor with parameters
	 * @param String - user name
	 * @param String - user password
	 * @param Integer - user id
	 */
	public User(String name, String pass, int id) {
		super();
		this.name = name;
		this.pass = pass;
		this.id = id;
	}
	
	/**
	 * Get user id
	 * @return id integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set user id
	 * @param Integer - id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get user name
	 * @return name string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set user name
	 * @param String - name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get user password
	 * @return password string
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Set user password
	 * @param String - password
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * User toString method - to represent user as string with all values
	 * @return String representation
	 */
	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + ", id=" + id + "]";
	}

	/**
	 * User hashcode method
	 * @return Ineteger - code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	/**
	 * User equals method - to compare with other user
	 * @param - Object - object to compare with
	 * @return Boolean if equals or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		return true;
	}
}	