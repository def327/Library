package by.zyablov.library.beans;

import java.io.Serializable;

/**
 * Class {@code Entity} is a declares object in a database.
 * 
 * @author Дмитрий
 *
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = -4530667728349210633L;

	/**
	 * A unuque id of the {@code Entity} object at the database.
	 */
	private int id;

	/**
	 * Constructs a default <code>Entity</code>
	 */
	public Entity() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
