package kpp.payakumbuh.manajemenberkas.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import kpp.payakumbuh.manajemenberkas.entity.UserPegawai;
import kpp.payakumbuh.manajemenberkas.repository.UserPegawaiRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	
	@Autowired
	UserPegawaiRepository userPegawaiRepository;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
 
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Optional<UserPegawai> existingUser = userPegawaiRepository.findById(name);
        
        if (existingUser.isPresent()) {
            
        	UserPegawai user = existingUser.get();
        	
        	try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
	            byte[] digest = md.digest();
	            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

	            if (user.getPassword().equalsIgnoreCase(passwordHash)) {
	            	
	            	List<SimpleGrantedAuthority> grantedAuths = new ArrayList<SimpleGrantedAuthority>();
	            	
	            	String role = "";
	            	
	            	switch (user.getIdUnitOrg()) {
					case "0":
						role = "ROLE_KAKAP";
						break;
					case "1":
						if ("5".equals(user.getIdJabatan())) {
							
							role = "ROLE_ASPEN";
						} else {
							
							role = "ROLE_PELAYANAN";
						}
						
						break;
					case "2":
						role = "ROLE_PKD";
						break;
					case "3":
						role = "ROLE_P3";
						break;
					case "4":
					case "5":
					case "6":
					case "7":
					case "8":
						role = "ROLE_PENGAWASAN";
						break;
					case "9":
						role = "ROLE_SUKI";
						break;
					default:
						role = "ROLE_USER";
						break;
					}
	            	
	            	SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
	            	grantedAuths.add(authority);
	            	
	            	UserModel userModel = new UserModel();
	            	userModel.setIp(user.getIp());
	            	userModel.setNip(user.getNip());
	            	userModel.setNama(user.getNama());
	            	userModel.setIdUnit(user.getIdUnitOrg());
	            	
	            	return new UsernamePasswordAuthenticationToken(userModel, password, grantedAuths);
	            } else {
	            	
	            	throw new BadCredentialsException("Username Dan Password Tidak Cocok");
	            }
			} catch (NoSuchAlgorithmException e) {

				throw new AuthenticationServiceException("Terjadi Kesalahan Pada Sistem Login");
			}
        } else {
        	throw new  UsernameNotFoundException("Username Tidak Ditemukan");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}