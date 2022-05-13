package filter;

import static constants.FilterConstants.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ((HttpServletResponse) response).addHeader(RESPONSE_CORS_HEADER, "*");
        ((HttpServletResponse) response).addHeader(CORS_RESPONSE_HEADER, HTTP_REQUEST);
        HttpServletResponse resp = (HttpServletResponse) response;

        if (((HttpServletRequest) request).getMethod().equals(OPTIONS)) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {System.out.println(FILTER_START_STRING);}

    @Override
    public void destroy() {System.out.println(FILTER_DESTROY_STRING);}
}
