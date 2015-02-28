package il.ac.shenkar.finalproject.model;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Coupon object - represent DB coupon
 */
@Entity
public class Coupon{
	
	private String description;
	private String category;
	private String name;
	private int id;
	private Date date;
	private int longitude;
	private int latitude;
	
	/**
	 * default constructor for empty coupon object
	 */
	public Coupon() {

	}

	/**
	 * Coupon constructor - init with parameters
	 * @param String - coupon description
	 * @param String - coupon catgory
	 * @param String - coupon name	
	 * @param Integer - coupon id
	 * @param Date - coupon expiration date
	 * @param Integer - coupon longtitude
	 * @param Integer - coupon latitude
	 */
	public Coupon(String desc, String category, String name, int id, Date date, int lon, int lat) {
		this.description = desc;
		this.category = category;
		this.name = name;
		this.id = id;
		this.date = date;
		this.longitude = lon;
		this.latitude = lat;
	}

	
	/**
	 * Coupon hashcode method
	 * @return Ineteger - code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + latitude;
		result = prime * result + longitude;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Coupon equals method - to compare with other coupon
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
		Coupon other = (Coupon) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		}
		else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		}
		else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (latitude != other.latitude)
			return false;
		if (longitude != other.longitude)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Coupon toString method - to represent coupon as string with all values
	 * @return String representation
	 */
	@Override
	public String toString() {
		return "Coupon [description=" + description + ", category=" + category
				+ ", name=" + name + ", id=" + id + ", date=" + date
				+ ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

	/**
	 * Get coupon description
	 * @return description string
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set coupon description
	 * @param String - coupon description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get coupon category
	 * @return category string
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set coupon category
	 * @param String - coupon category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get coupon name
	 * @return name string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set coupon name
	 * @param String - coupon name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get coupon ID
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set coupon ID
	 * @param Integer - coupon id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get coupon date
	 * @return Date of coupon
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set coupon date
	 * @param Date - coupon expiration date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Get coupon longtitude
	 * @return longtitude integer
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * Set coupon longtitude
	 * @param Integer - longtitude
	 */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get coupon latitude
	 * @return latitude integer
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * Set coupon latitude
	 * @param Integer - latitude
	 */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
}	