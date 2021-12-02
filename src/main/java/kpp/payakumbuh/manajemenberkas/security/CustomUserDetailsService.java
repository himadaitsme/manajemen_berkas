package kpp.payakumbuh.manajemenberkas.security;
//package kpp.payakumbuh.monitoringpermohonan.security;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import kpp.payakumbuh.monitoringpermohonan.entity.UserPegawai;
//import kpp.payakumbuh.monitoringpermohonan.repository.UserPegawaiRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService{
//	
//	@Autowired
//	private UserPegawaiRepository userPegawaiRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Optional<UserPegawai> opt = userPegawaiRepository.findById(username);
//		
//		User springUser = null;
//		
//		if(opt.isEmpty()) {
//			throw new UsernameNotFoundException("User with nip: " +username +" not found");
//		}else {
//			
//			UserPegawai user =opt.get();
//
//			String role = "";
//        	
//        	switch (user.getIdUnitOrg()) {
//			case "0":
//				role = "ROLE_KAKAP";
//				break;
//			case "1":
//				role = "ROLE_PELAYANAN";
//				break;
//			case "2":
//				role = "ROLE_PKD";
//				break;
//			case "3":
//				role = "ROLE_P3";
//				break;
//			case "4":
//			case "5":
//			case "6":
//			case "7":
//			case "8":
//				role = "ROLE_PENGAWASAN";
//				break;
//			case "9":
//				role = "ROLE_SUKI";
//				break;
//			default:
//				role = "ROLE_USER";
//				break;
//			}
//        	
//			Set<GrantedAuthority> ga = new HashSet<>();
//			ga.add(new SimpleGrantedAuthority(role));
//			springUser = new User(username,user.getPassword(),ga);
//		}
//		
//		return springUser;
//	}
//	
//}	