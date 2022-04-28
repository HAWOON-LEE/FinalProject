package lee.hawoob.finalproject.controller;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.BoardDto;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.form.CreatePostForm;
import lee.hawoob.finalproject.form.UpdateBoardForm;
import lee.hawoob.finalproject.repository.BoardRepository;
import lee.hawoob.finalproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("board")
@Transactional
public class BoardApiController implements WebMvcConfigurer {

    private final BoardService service;
    private final BoardRepository boardRepository;

//    @GetMapping
//    public ResponseEntity retrievePosts(final Pageable pageable) {
//        Page<Board> boards = boardRepository.findAll(pageable);
//        return new ResponseEntity<>(boards, HttpStatus.OK);
//    }

//    @GetMapping("/list")
//    public ModelAndView list(@PageableDefault Pageable pageable, ModelAndView mav, Model model){
//        List<SearchBoardDto> boardList = service.findAll();
//        mav.addObject("boardList", boardList);
//        mav.setViewName("board/list");
//
//        Page<Board> boardList1 = service.getBoardList(pageable);
//        model.addAttribute("boardList1", boardList1);
//
//        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
//                boardList1.getTotalElements(), boardList1.getTotalPages(), boardList1.getSize(),
//                boardList1.getNumber(), boardList1.getNumberOfElements());
//
//        return mav;
//    }

    @GetMapping("/list")
    public String boardView(@PageableDefault Pageable pageable, Model model){
        Page<SearchBoardDto> boardList1 = service.getBoardList(pageable);
        model.addAttribute("boardList1", boardList1);

        return "board/list";
    }

    @GetMapping("search")
    public String searchBoard(@RequestParam("keyword") String keyword,Pageable pageable , Model model){
        Page<SearchBoardDto> boardList = service.searchBoard(keyword, pageable);
        model.getAttribute("keyword");
        model.addAttribute("boardList1", boardList);

//        Page<SearchBoardDto> boardList1 = service.getBoardList(pageable);
//        model.addAttribute("boardList1", boardList1);

        return "board/list";
    }
    @GetMapping("/detail/{boardIndex}")
    public ModelAndView detailBoard(@PathVariable Long boardIndex, ModelAndView mav){
        Optional<Board> board = service.findByIndex(boardIndex);
        BoardDto dto =service.getBoardDto(board.get());
        service.updateView(boardIndex); // views ++
        mav.setViewName("board/details");
        mav.addObject("dto", dto);
        return mav;
    }

//    @GetMapping("/detail/{boardIndex}")
//    public String detailBoard(@PathVariable Long boardIndex, @LoginUser UserSessionDto user, Model model) {
//        BoardResponseDto dto = service.findById(boardIndex);
//        List<CommentResponseDto> comments = dto.getComments();
//
//        /* 댓글 관련 */
//        if (comments != null && !comments.isEmpty()) {
//            model.addAttribute("comments", comments);
//        }
//
//        /* 사용자 관련 */
//        if (user != null) {
//            model.addAttribute("user", user.getNickname());
//
//            /*게시글 작성자 본인인지 확인*/
//            if (dto.getUserId().equals(user.getId())) {
//                model.addAttribute("writer", true);
//            }
//        }
//        service.updateView(boardIndex); // views ++
//        model.addAttribute("posts", dto);
//        return "board/posts-read";
//    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute CreatePostForm form, ModelAndView mav){
        mav.addObject("form", form);
        mav.setViewName("board/create");
        return mav;
    }

    @PostMapping("/create")
    public String createBoard(@ModelAttribute CreatePostForm form, PrincipalDetails custom){
        service.createBoard(form, custom);
        return "redirect:/board/list";
    }

    @RequestMapping("/delete/{boardIndex}")
    public String deleteBoard(@PathVariable Long boardIndex){
        service.deleteBoard(boardIndex);

        return "redirect:/board/list";
    }

    @GetMapping("update/{boardIndex}")
    public ModelAndView updateBoard(@PathVariable Long boardIndex, ModelAndView mav){
        Optional<Board> board = service.findByIndex(boardIndex);
        UpdateBoardForm form = new UpdateBoardForm();

        form.setBoardIndex(board.get().getBoardIndex());
        form.setTitle(board.get().getTitle());
        form.setContent(board.get().getContent());
        form.setDate(board.get().getCreateDate());

        mav.setViewName("/board/update");
        mav.addObject("form", form);

        return mav;
}

    @PostMapping("/update")
    public ModelAndView updateBoard(@ModelAttribute UpdateBoardForm form, ModelAndView mav){
        service.updateBoard(form);

        mav = new ModelAndView("redirect:/board/list");
        return mav;
    }
}
