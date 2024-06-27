package com.cauaconceicao.workshopmongo.resources;

import com.cauaconceicao.workshopmongo.domain.Post;
import com.cauaconceicao.workshopmongo.repository.util.URL;
import com.cauaconceicao.workshopmongo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping("/buscaPorId/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);

        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/buscaPorTitulo")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = postService.findyByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/buscaTotal")
    public ResponseEntity <List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(minDate, new Date());

        List<Post> list = postService.fullSearch(text, min, max);

        return ResponseEntity.ok().body(list);
    }
}
