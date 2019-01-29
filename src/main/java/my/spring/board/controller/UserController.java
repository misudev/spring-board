package my.spring.board.controller;

import my.spring.board.dto.User;
import my.spring.board.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //  @RequestMapping(method=GET, path="/list") 와 같은 것
    @GetMapping("/list")
    public String main(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        System.out.println("main controller");
        for(User u : users){
            System.out.println(u);
        }

        return "index"; // view name
    }

    @GetMapping("/joinform")
    public String joinform(){
        return "joinform";
    }

    @PostMapping("/join")
    public String join(@RequestParam(name = "name") String name,
                       @RequestParam(name = "nickname") String nickname,
                       @RequestParam(name = "email") String email,
                       @RequestParam(name = "passwd") String passwd,
                       @RequestHeader(name = "Accept") String accept,
                       HttpSession session){

        // 값에 검증.
        Assert.hasLength(name, "이름을 입력하세요.");
        if(name == null || name.length() <= 1)
            throw new IllegalArgumentException("이름을 입력하세요.");

        User user = new User();
        user.setPasswd(passwd);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setName(name);

        userService.addUser(user);

        return "redirect:/list";
    }
}
