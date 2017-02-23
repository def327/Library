package by.zyablov.library.beans;

public class Book extends Entity {

	private static final long serialVersionUID = -8978380883523355985L;

	String bookName;

	String bookPrice;

	String bookDescription;

	String bookReleaseDate;

	Categorie bookCategorie;

	User userBookProvider;

	public Book() {
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName
	 *            the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return the bookPrice
	 */
	public String getBookPrice() {
		return bookPrice;
	}

	/**
	 * @param bookPrice
	 *            the bookPrice to set
	 */
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	/**
	 * @return the bookDescription
	 */
	public String getBookDescription() {
		return bookDescription;
	}

	/**
	 * @param bookDescription
	 *            the bookDescription to set
	 */
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	/**
	 * @return the bookReleaseDate
	 */
	public String getBookReleaseDate() {
		return bookReleaseDate;
	}

	/**
	 * @param bookReleaseDate
	 *            the bookReleaseDate to set
	 */
	public void setBookReleaseDate(String bookReleaseDate) {
		this.bookReleaseDate = bookReleaseDate;
	}

	/**
	 * @return the bookCategorie
	 */
	public Categorie getBookCategorie() {
		return bookCategorie;
	}

	/**
	 * @param bookCategorie
	 *            the bookCategorie to set
	 */
	public void setBookCategorie(Categorie bookCategorie) {
		this.bookCategorie = bookCategorie;
	}

	/**
	 * @return the userBookProvider
	 */
	public User getUserBookProvider() {
		return userBookProvider;
	}

	/**
	 * @param userBookProvider
	 *            the userBookProvider to set
	 */
	public void setUserBookProvider(User userBookProvider) {
		this.userBookProvider = userBookProvider;
	}

}
