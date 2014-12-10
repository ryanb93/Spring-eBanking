package components;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * This filter lets applications from different origins access our REST API.
 * 
 * Cross-Origin Resource Sharing (CORS) is a technique for relaxing
 * the same-origin policy, allowing JavaScript on a web page to consume
 * a REST API served from a different origin. (Source: http://spring.io/understanding/CORS)
 */
@Component
public class CORSFilter implements Filter {

        /**
         * Called each time a request is passed up through the filter chain.
         * This is where we implement our filtering logic for CORS.
         *
         * @param req - The request from the client.
         * @param res - The response from the server.
         * @param chain - The filter chain object.
         * @throws IOException
         * @throws ServletException 
         */
        @Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            //Cast the response.
            HttpServletResponse response = (HttpServletResponse) res;
            //Add headers to the response to allow CORS.
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods",  "GET, POST, PUT, DELETE, OPTIONS"); 
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, Content-Type, Origin, Cookie, X-Requested-With, Accept");
            //Pass the request and response up the filter chain.
            chain.doFilter(req, res);
	}

        /**
         * Init method needed by interface.
         * @param filterConfig 
         */
        @Override
	public void init(FilterConfig filterConfig) {
            //Do nothing.
        }

        /**
         * Destroy method needed by interface.
         */
        @Override
	public void destroy() {
            //Do nothing.
        }

}
