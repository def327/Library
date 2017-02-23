package by.zyablov.library.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import by.zyablov.library.datasource.DataBaseManager;

/**
 * {@code EncodingFilter} intercepts user's http requests to configurate content
 * to <b>'UTF-8'</b> encode at {@code ServletRequest} and
 * {@code ServletResponse } objects and passes these to the next filter.
 * 
 * 
 */
public class EncodingFilter implements Filter {

	/**
	 * Filter's parametr name to get a 'UTF-8' encode.
	 */
	private static final String FILTER_PARAM_ENCODING = "encoding";

	/**
	 * A variable to contain an encode.
	 */
	private String webAppEncode;

	/**
	 * Initializes {@code EncodingFilter} object.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.webAppEncode = fConfig.getInitParameter(FILTER_PARAM_ENCODING);
	}

	/**
	 * Configurates an encode.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		DataBaseManager.getInstance();

		request.setCharacterEncoding(webAppEncode);
		response.setCharacterEncoding(webAppEncode);

		chain.doFilter(request, response);
	}

	/**
	 * Destroys {@code EncodingFilter} object.
	 */
	public void destroy() {
		this.webAppEncode = null;
	}

}
