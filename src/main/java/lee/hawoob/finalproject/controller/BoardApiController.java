package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.dto.BoardDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.form.CreatePostForm;
import lee.hawoob.finalproject.repository.BoardRepository;
import lee.hawoob.finalproject.service.BoardService;
import lee.hawoob.finalproject.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
@Transactional
public class BoardApiController {

    private final BoardService service;
    private final BoardRepository repository;

//    @GetMapping("/list")
//    public List<SearchBoardDto> findAll(){
//        List<Board> list = new ArrayList<>();
//        list = service.findAll();
//        List<SearchBoardDto> dto = list.stream().map(l -> new SearchBoardDto(l)).collect(Collectors.toList());
//        return dto;
//
//    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mav){
        List<SearchBoardDto> boardList = service.findAll();
        mav.addObject("boardList", boardList);
        mav.setViewName("board/rank");

        return mav;
    }


    //    @GetMapping
//    public List<SearchBoardDto> searchBoard(String keyword){
//        List<SearchBoardDto> searchPost = null;
//        if(keyword == null){
//            searchPost = service.findAll();
//        } else{
//            searchPost = service.searchBoard(keyword);
//        }
//
//
//        List<SearchBoardDto>dto = searchPost.stream().map(b -> new SearchBoardDto(b)).collect(Collectors.toList());
//        return dto;
//    }
    @GetMapping("search")
    public String searchBoard(@RequestParam("keyword") String keyword, Model model){
        List<SearchBoardDto> boardList = service.searchBoard(keyword);
        model.getAttribute("keyword");
        model.addAttribute("boardList", boardList);

        return "list";
    }

    @GetMapping("/board/{boardIndex}")
    public BoardDto detailBoard(@PathVariable Long boardIndex){
        Board board = service.findByIndex(boardIndex).get();
        BoardDto dto =service.getPostDto(board);
        return dto;
    }

    @GetMapping("/create")
    public ModelAndView insert(@ModelAttribute CreatePostForm form, ModelAndView mav){
        mav.addObject("form", form);
        mav.setViewName("board/create");
        return mav;
    }

    @PostMapping("/create")
    public String createBoard(@ModelAttribute CreatePostForm form, UserDetailsService custom){
        service.createBoard(form, custom);
        return "redirect:/board/rank";
    }

    @DeleteMapping("board/{boardIndex}")
    public void deleteBoard(@PathVariable Long boardIndex, @AuthenticationPrincipal UserDetailsService custom){
        service.deleteBoard(boardIndex, custom);
    }

//    @PutMapping("board")
//    public void updateBoard(@PathVariable UpdatePostForm form, BoardDto dto){
//        service.updateBoard(form.getPostIndex());
//    }
}
