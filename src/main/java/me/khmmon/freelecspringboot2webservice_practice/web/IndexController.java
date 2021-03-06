package me.khmmon.freelecspringboot2webservice_practice.web;

import lombok.RequiredArgsConstructor;
import me.khmmon.freelecspringboot2webservice_practice.config.auth.LoginUser;
import me.khmmon.freelecspringboot2webservice_practice.config.auth.dto.SessionUser;
import me.khmmon.freelecspringboot2webservice_practice.service.posts.PostsService;
import me.khmmon.freelecspringboot2webservice_practice.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
  private final PostsService postsService;

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());
    if (user != null) {
      model.addAttribute("userName", user.getName());
    }
    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model) {
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post", dto);

    return "posts-update";
  }
}
