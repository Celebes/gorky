//package www.wcy.wat.gorky.repositories;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import www.wcy.wat.gorky.model.Users;
//
//@Repository
//@Transactional(readOnly = true)
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	UserDaoRepository userDaoRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username)
//			throws UsernameNotFoundException {
//		Users user = userDaoRepository.getUser(username);
//
//		boolean enabled = true;
//		boolean accountNonExpired = true;
//		boolean credentialsNonExpired = true;
//		boolean accountNonLocked = true;
//		
//        return new User(
//                user.getLogin(), 
//                user.getPassword(), 
//                enabled, 
//                accountNonExpired, 
//                credentialsNonExpired, 
//                accountNonLocked,
//                getAuthorities(user.getRole().getId())
//        );
//
//	}
//
//	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
//		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
//		return authList;
//	}
//
//	public List<String> getRoles(Integer role) {
//
//		List<String> roles = new ArrayList<String>();
//
//		if (role.intValue() == 1) {
//			roles.add("ROLE_MODERATOR");
//			roles.add("ROLE_ADMIN");
//		} else if (role.intValue() == 2) {
//			roles.add("ROLE_MODERATOR");
//		}
//		return roles;
//	}
//
//	public static List<GrantedAuthority> getGrantedAuthorities(
//			List<String> roles) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//		for (String role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role));
//		}
//		return authorities;
//	}
//
//}
