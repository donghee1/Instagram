package h.project.con;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import h.project.dto.UserInfoDto;
import h.project.svc.UserService;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequiredArgsConstructor

public class UserController {

	@Resource
	private final UserService userService;
	
	
    @GetMapping("/memberForm")
    public String createUserForm(Model model){
        model.addAttribute("userForm",new UserInfoDto());
        return "account";
    }
    
    @PostMapping("/createMember")
    public void createMemberInInstargram(@ModelAttribute("userForm") UserInfoDto dto){
    	System.out.println("test=============================");
    	System.out.println(dto.getEmail());
    	dto.setEmail(dto.getEmail());
    	dto.setPassword(dto.getPassword());
    	dto.setName(dto.getName());
    	dto.setPhone(dto.getPhone());
    	dto.setGender(dto.getGender());
    	
    	// 메서드 별 하드코딩
    	dto.setLogin("instagram");
    	
    	userService.createUser(dto);
    	
    }

//    @PostMapping("/loginUser")
//    public String createUser(@Valid AccountDto dto, BindingResult result){
//    	System.out.println("test??????");
//        if(result.hasErrors()){
//            return "user/account";
//        }
//        accountService.createUser(dto);
//
//        return "redirect:/";
//    }
    
    
    @ResponseBody
    @PostMapping("/checkData")
    public void checkData (HttpServletRequest req, HttpServletResponse res){
    	
    	UserInfoDto dto = new UserInfoDto();
    	
    	dto.setEmail(req.getParameter("email"));
    	dto.setPassword(req.getParameter("password"));
    	dto.setName(req.getParameter("name"));
    	dto.setPhone(req.getParameter("phone"));
    	dto.setGender(req.getParameter("gender"));
    	
    	// 메서드 별 하드코딩
    	dto.setLogin("instagram");
    	
    	userService.createUser(dto);
    	
    }
    
    
    @PostMapping("/createMemberKakao")
    public void createMemberInKakao(HttpServletRequest req, HttpServletResponse res){
    	System.out.println("createMember start !!!");
//    	System.out.println("test1 = " + req.getParameter("id"));
//    	System.out.println("test2 = " + req.getParameter("name"));
//    	System.out.println("test3 = " + req.getParameter("password"));
//    	System.out.println("test4 = " + req.getParameter("email"));
//    	System.out.println("test5 = " + req.getParameter("phone"));
//    	System.out.println("test6 = " + req.getParameter("gender"));
    	
    	UserInfoDto dto = new UserInfoDto();

    	dto.setEmail(req.getParameter("email"));
    	dto.setPassword(req.getParameter("password"));
    	dto.setName(req.getParameter("name"));
    	dto.setPhone(req.getParameter("phone"));
    	dto.setGender(req.getParameter("gender"));
    	
    	// 메서드 별 하드코딩
    	dto.setLogin("instagram");
    	
    	userService.createUser(dto);
    	
    }
    
    
    
    
    
}
