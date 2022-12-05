package com.store.sportswear.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		authorities.forEach(authority -> {
			// nếu quyền có vai trò user, chuyển đến trang "/" nếu login thành công
			if (authority.getAuthority().equals("ROLE_USER")) {
				try {
					redirectStrategy.sendRedirect(request, response, "/client/products/");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (authority.getAuthority().contains("ROLE_ADMIN")) {
				try {
					redirectStrategy.sendRedirect(request, response, "/api");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				throw new IllegalStateException();
			}
		});

	}

}
