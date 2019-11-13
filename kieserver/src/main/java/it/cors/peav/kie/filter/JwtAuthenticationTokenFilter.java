package it.cors.peav.kie.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Value("${auth.user.property}")
	private String USER_PROPERTY;

	@Value("${auth.role.property}")
	private String ROLE_PROPERTY;

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("call filter to url " + request.getRequestURI());

		String user = request.getHeader(USER_PROPERTY);
		String role = request.getHeader(ROLE_PROPERTY);
		if (user != null && role != null) {

			ArrayList<GrantedAuthority> grant = new ArrayList<GrantedAuthority>();
			grant.add(new SimpleGrantedAuthority(role));
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, grant);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);

	}

	// other methods
}