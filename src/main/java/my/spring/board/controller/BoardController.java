package my.spring.board.controller;

import my.spring.board.Paging;
import my.spring.board.dto.Board;
import my.spring.board.dto.User;
import my.spring.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/board")
    public String board(@RequestParam(name = "page", required = false, defaultValue = "1") int page, Model model){
        List<Board> boards = boardService.getBoards(page);
        model.addAttribute("boards", boards);
        // 페이징 처리
        Paging paging = new Paging(boardService.getCountBoard());
        paging.makeBlock(page);
        System.out.println(paging);
        model.addAttribute("pagestart", paging.getBlockStartNum());
        model.addAttribute("pageend", paging.getBlockLastNum());
        model.addAttribute("start", paging.getStart());
        model.addAttribute("count", paging.getBoardCount());

        return "board";
    }

    @GetMapping("/read")
    public String read(@RequestParam(name = "id")long id, Model model){
        model.addAttribute("board",boardService.getBoard(id));

        return "read";
    }

    @GetMapping("/writeform")
    public String writeform(HttpSession httpSession){
       if(httpSession.getAttribute("logininfo")==null){
           return "login";
       }
       return "writeform";
    }

    @PostMapping("/write")
    public String write(@RequestParam(name = "title")String title,
                        @RequestParam(name = "content")String content,
                        HttpSession httpSession,  Model model){

        User writer = (User)httpSession.getAttribute("logininfo");
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setNickname(writer.getNickname());
        board.setUserId(writer.getId());

        boardService.addBoard(board);

        return "redirect:/board";

    }
}
