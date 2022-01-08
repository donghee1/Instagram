package h.project.con;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import h.project.dao.UserLogin;
import h.project.dto.RedisCrudSaveRequestDto;
import h.project.dto.UserDto;
import h.project.repo.UserLoginRepository;
import h.project.svc.RedisCrudService;
import h.project.svc.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	UserService userService;

	private final RedisCrudService redisCrudService;
	private final StringRedisTemplate redisTemplate;
	
	private final HttpSession httpSession;
		
	//login호출시 loginForm.html 이동
	@GetMapping("/user/login")
	public String login() {
		return "loginForm";
	}
	
	@GetMapping("/user/success")
	public String LoginSuccess(UserLogin userLogin){
		boolean user = (boolean) httpSession.getAttribute("emailYn");
		if(user == false) {
			return "loginForm2";
		}else {
			return "successForm";
		}
	}
	
	@PostMapping("/kakaoJoin")
	public String KakaoJoin(UserDto userDto, Model model, RedisCrudSaveRequestDto requestDto) {	
		//DB저장
		userService.createKakao(userDto);
		//레디스 저장
		//redisCrudService.RedisUserSave(requestDto);
		return "successForm";
	}
}
