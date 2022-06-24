package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.BoardDto;
import lee.hawoob.finalproject.dto.BoardUpdateDto;
import lee.hawoob.finalproject.dto.CommentDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.form.CreateBoardForm;
import lee.hawoob.finalproject.form.UpdateBoardForm;
import lee.hawoob.finalproject.service.BoardService;
import lee.hawoob.finalproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 게시판 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("board")
@Transactional
public class BoardController {

    private final BoardService service;

    private final CommentService commentService;

    @GetMapping("/list")
    public String getBoardList(@PageableDefault Pageable pageable, Model model){
        Page<SearchBoardDto> boardList1 = service.getBoardList(pageable);
        model.addAttribute("boardList1", boardList1);

        return "board/list";
    }

    @GetMapping("search")
    public String searchBoard(@RequestParam("keyword") String keyword,Pageable pageable , Model model){
        Page<SearchBoardDto> boardList = service.searchBoard(keyword, pageable);
        model.getAttribute("keyword");
        model.addAttribute("boardList1", boardList);

        return "board/list";
    }

    @GetMapping("/detail/{boardIndex}")
    public ModelAndView detailBoard(@PathVariable Long boardIndex, ModelAndView mav, Model model){
        BoardDto dto =service.getBoardDto(boardIndex);
        service.updateView(boardIndex); // views ++

        mav.setViewName("board/detailsPost");
        mav.addObject("dto", dto);

        List<CommentDto> commentList = commentService.getCommentList(boardIndex);
        model.addAttribute("commentList", commentList);
        mav.setViewName("board/detailsPost");

        return mav;
    }

    @GetMapping("/create")
    public ModelAndView getCreateBoard(@ModelAttribute CreateBoardForm form, ModelAndView mav){
        mav.addObject("form", form);
        mav.setViewName("board/createPost");
        return mav;
    }

    @PostMapping("/create")
    public String createBoard(@ModelAttribute CreateBoardForm form, @AuthenticationPrincipal PrincipalDetails custom, Model model){
        model.addAttribute("form", form);
        service.createBoard(form, custom);
        return "redirect:/board/list";
    }

    @RequestMapping("/delete/{boardIndex}")
    public String deleteBoard(@PathVariable Long boardIndex){
        service.deleteBoard(boardIndex);

        return "redirect:/board/list";
    }

    @GetMapping("update/{boardIndex}")
    public ModelAndView getUpdateBoard(@PathVariable Long boardIndex, ModelAndView mav){
        BoardUpdateDto dto = service.getDtoByBoardIndex(boardIndex);

        mav.setViewName("/board/updatePost");
        mav.addObject("form", dto);

        return mav;
    }

    @PostMapping("/update")
    public ModelAndView updateBoard(@ModelAttribute UpdateBoardForm form, ModelAndView mav){
        mav.setViewName("board/updatePost");
        mav.addObject("form", form);
        service.updateBoard(form);

        mav = new ModelAndView("redirect:/board/list");
        return mav;
    }
}
