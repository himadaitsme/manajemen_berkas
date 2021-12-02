package kpp.payakumbuh.manajemenberkas.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

	public UserModel getUserLogin() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserModel userLogin = (UserModel) authentication.getPrincipal();
		return userLogin;
	}
}
