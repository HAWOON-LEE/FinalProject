package lee.hawoob.finalproject.service;

//import MBTI.domain.Post;
import lee.hawoob.finalproject.dto.BoardDto;
import lee.hawoob.finalproject.entity.Board;
import lee.hawoob.finalproject.entity.User;
import lee.hawoob.finalproject.dto.SearchBoardDto;
//import MBTI.dto.SearchInterface;
import lee.hawoob.finalproject.form.CreatePostForm;
import lee.hawoob.finalproject.form.UpdatePostForm;
import lee.hawoob.finalproject.repository.BoardRepository;
//import MBTI.repository.MemberRepository;
//import MBTI.repository.PostRepository;
import lee.hawoob.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository repository;

    public List<SearchBoardDto> findAll(){
        List<SearchBoardDto> dto = repository.findAll().stream().map(b -> new SearchBoardDto(b)).collect(Collectors.toList());
        return dto;
    }

    public List<SearchBoardDto> searchBoard(String keyword) {
        List<SearchBoardDto> dto =  repository.findByBoardTitleAndPostContentContaining(keyword).stream().map(b -> new SearchBoardDto(b)).collect(Collectors.toList());
        return dto;
    }


    public BoardDto getPostDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setUser(board.getUser());

        return dto;
    }

    public Optional<Board> findByIndex(Long boardIndex){
        return repository.findById(boardIndex);
    }

    public void createBoard(CreatePostForm form, @AuthenticationPrincipal UserDetailsService custom) {
        Board board = new Board();
//        User user = userRepository.findById(custom.getId()).get();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
//        board.setUser(user);

        repository.save(board);
    }

//    public void deleteBoard(Long boardIndex, @AuthenticationPrincipal UserDetailsService custom) {
//        if (repository.findById(boardIndex).get().getUser().getId().equals(custom.getId())) {
//            repository.deleteBoardById(boardIndex, custom.getId());
//        }
//    }

    public void updateBoard(Long boardIndex){
        Optional<Board> opBoard = repository.findById(boardIndex);
        UpdatePostForm form = new UpdatePostForm();


        if (opBoard.isPresent()) {
            Board board = opBoard.get();

            board.setTitle(form.getTitle());
            board.setContent(form.getContent());
        }
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



