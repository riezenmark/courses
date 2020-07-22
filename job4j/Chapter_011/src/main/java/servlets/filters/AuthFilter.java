package servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (!req.getRequestURI().contains("/login")) {
            HttpSession session = req.getSession();
            if (session.getAttribute("login") == null) {
                ((HttpServletResponse) servletResponse)
                        .sendRedirect(
                                String.format("%s/login", req.getContextPath())
                        );
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
