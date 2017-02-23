package by.zyablov.library.beans;

/**
 * Class {@code User} is used to store authority information about user.
 * 
 * @author Дмитрий
 * 
 * @see Entity
 *
 */
public class User extends Entity {

	private static final long serialVersionUID = 4025542135365903606L;

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private AuthorityType authorityType;

	public User() {
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the authorityType
	 */
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	/**
	 * @param authorityType the authorityType to set
	 */
	public void setAuthorityType(AuthorityType authorityType) {
		this.authorityType = authorityType;
	}

	

}
