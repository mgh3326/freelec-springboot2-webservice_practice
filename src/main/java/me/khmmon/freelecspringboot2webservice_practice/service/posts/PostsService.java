package me.khmmon.freelecspringboot2webservice_practice.service.posts;


import lombok.RequiredArgsConstructor;
import me.khmmon.freelecspringboot2webservice_practice.domain.posts.Posts;
import me.khmmon.freelecspringboot2webservice_practice.domain.posts.PostsRepository;
import me.khmmon.freelecspringboot2webservice_practice.web.dto.PostsResponseDto;
import me.khmmon.freelecspringboot2webservice_practice.web.dto.PostsSaveRequestDto;
import me.khmmon.freelecspringboot2webservice_practice.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
    posts.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  @Transactional
  public void delete(Long id) {
    Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

    postsRepository.delete(posts);
  }

  @Transactional(readOnly = true)
  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

    return new PostsResponseDto(entity);
  }
}