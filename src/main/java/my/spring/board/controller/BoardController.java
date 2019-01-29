package my.spring.board.controller;

import my.spring.board.Paging;
import my.spring.board.dto.Board;
import my.spring.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    BoardService boardService;

    //public Controller(UserService userService){
       // this.userService = userService;
   // }
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }
    //  @RequestMapping(method=GET, path="/list") 와 같은 것
//    @GetMapping("/list")
//    public String main(Model model){
//        List<User> users = userService.getUsers();
//        model.addAttribute("users", users);
//        System.out.println("main controller");
//        for(User u : users){
//            System.out.println(u);
//        }
//
//        return "index"; // view name
//    }

    @GetMapping("/board")
    public String board(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model){
        List<Board> boards = boardService.getBoards(page);
        model.addAttribute("boards", boards);
        // 페이징 처리
        Paging paging = new Paging(boardService.getCountBoard());
        paging.makeBlock(page);
        model.addAttribute("pagestart", paging.getBlockStartNum());
        model.addAttribute("pageend", paging.getBlockLastNum());
        model.addAttribute("start", paging.getStart());
        model.addAttribute("count", paging.getBoardCount());

//
//        req.setAttribute("pagestart", paging.getBlockStartNum());
//        req.setAttribute("pageend", paging.getBlockLastNum());
//        req.setAttribute("start" , start);
//        req.setAttribute("count" , paging.getBoardCount());

        return "board";
    }
}
