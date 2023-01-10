package com.example.wmii.confg;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wmii.User.User;
import com.example.wmii.User.UserDto;
import com.example.wmii.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService{

//	user id,pw 하드코딩
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if("user_id".equals(username)) {
//			return new User("user_id", "$2a$10$Dx1iyLHuszXpL.wUfPtXDOychXoB5BiEx0ALWdW4FUBznXVEKjhWi", new ArrayList<>());			
//		}else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
	
	private final UserRepository userRepository;
	
	/*spring security 필수 메소드 구현
	 * 
	 * @parm Username
	 * @return UserDeatils
	 * @throws UsernameNotfoundException 유저가 없을 때 예외 발생
	 * */
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}
	
	@Transactional
	public Long save(UserDto infoDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		infoDto.setPassword(encoder.encode(infoDto.getPassword()));
		
		return  userRepository.save(User.builder()
				.name(infoDto.getId())
				.auth(infoDto.getAuth())
				.password(infoDto.getPassword()).build()).getId();
		
	}
}
