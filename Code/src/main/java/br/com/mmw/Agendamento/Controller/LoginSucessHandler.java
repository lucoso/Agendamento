package br.com.mmw.Agendamento.Controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSucessHandler implements AuthenticationSuccessHandler {
		
		public void onAuthenticationSuccess(HttpServletRequest request,
				HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			if(roles.contains("AdministradorGeral")){
				response.sendRedirect(request.getContextPath() + "/Administrador.xhtml");
			}
			
			if((roles.contains("Administrador") || roles.contains("Gerente") || roles.contains("Usuario"))){
				response.sendRedirect(request.getContextPath() + "/Home.xhtml");
			}
		}
}
