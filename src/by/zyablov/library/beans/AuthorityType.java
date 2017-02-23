package by.zyablov.library.beans;

/**
 * Class {@code AuthorityType} represents role of the user rights at the bank
 * system and it's type information data about user.
 * 
 * @author Дмитрий
 * 
 * @see Entity
 *
 */
public class AuthorityType extends Entity {

	private static final long serialVersionUID = -7157581215173089418L;

	/**
	 * An authority type title.
	 */
	private String authorityTypeTitle;

	/**
	 * Constructs a default <code>AuthorityType</code>.
	 */
	public AuthorityType() {
	}

	/**
	 * @return the authorityTypeTitle
	 */
	public String getAuthorityTypeTitle() {
		return authorityTypeTitle;
	}

	/**
	 * @param authorityTypeTitle
	 *            the authorityTypeTitle to set
	 */
	public void setAuthorityTypeTitle(String authorityTypeTitle) {
		this.authorityTypeTitle = authorityTypeTitle;
	}

}
