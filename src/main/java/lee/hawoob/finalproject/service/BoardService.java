package lee.hawoob.finalproject.service;

import lee.hawoob.finalproject.auth.PrincipalDetails;
import lee.hawoob.finalproject.dto.BoardDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.dto.SearchBoardDto;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.form.CreatePostForm;
import lee.hawoob.finalproject.form.UpdateBoardForm;
//import lee.hawoob.finalproject.oauth.OAuth2UserService;
import lee.hawoob.finalproject.oauth.OAuth2UserService;
import lee.hawoob.finalproject.repository.BoardRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository repository;
    private final UserRepository userRepository;

    public List<SearchBoardDto> findAll(){
        List<SearchBoardDto> dto = repository.findAll().stream().map(b -> new SearchBoardDto(b)).collect(Collectors.toList());
        return dto;
    }

    public List<SearchBoardDto> searchBoard(String keyword) {
        List<SearchBoardDto> dto =  repository.findByBoardTitleAndPostContentContaining(keyword).stream().map(b -> new SearchBoardDto(b)).collect(Collectors.toList());
        return dto;
    }


    public BoardDto getBoardDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setBoardIndex(board.getBoardIndex());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setUser(board.getUser());

        return dto;
    }

    public Optional<Board> findByIndex(Long boardIndex){
        return repository.findById(boardIndex);
    }

    public void createBoard(CreatePostForm form, @AuthenticationPrincipal PrincipalDetails custom) {
        Board board = new Board();
        User user = userRepository.findById(custom.getUser().getUser_id()).get();

        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        board.setUser(user);

        repository.save(board);
    }

    public void deleteBoard(Long boardIndex) {
//        if (repository.findById(boardIndex).get().getUser().getUser_id().equals(custom.getId())) {
//            repository.deleteBoardById(boardIndex);
//            , custom.getId()
        Board board = new Board();
        board.getBoardIndex();

        repository.deleteById(boardIndex);
//        }
    }

    public void updateBoard(UpdateBoardForm form){
        Optional<Board> opBoard = repository.findById(form.getBoardIndex());

        Board board = opBoard.get();

        board.setBoardIndex(form.getBoardIndex());
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        repository.save(board);
    }

//    @Override
//    public void save(CreateBoardDto dto){
//        Post post = dto.toEntity();
//
//        post.confirmWriter(memberRepository.findByNickname(SecurityUtil.getLoginUsername())
//                .orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER)));
//
//        postRepository.save(post);
//    }


}



