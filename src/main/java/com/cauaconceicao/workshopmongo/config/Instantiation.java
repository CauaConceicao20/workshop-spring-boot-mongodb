package com.cauaconceicao.workshopmongo.config;

import com.cauaconceicao.workshopmongo.domain.Post;
import com.cauaconceicao.workshopmongo.domain.User;
import com.cauaconceicao.workshopmongo.dto.AuthorDTO;
import com.cauaconceicao.workshopmongo.dto.CommentsDTO;
import com.cauaconceicao.workshopmongo.repository.PostRepository;
import com.cauaconceicao.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation  implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        sfd.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null, sfd.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post p2 = new Post(null, sfd.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));

        CommentsDTO comment1 = new CommentsDTO("Boa viagem mano", sfd.parse("21/03/2018"), new AuthorDTO(alex));
        CommentsDTO comment2 = new CommentsDTO("Aproveite", sfd.parse("22/03/2018"), new AuthorDTO(bob));
        CommentsDTO comment3 = new CommentsDTO("Tenha um otimo dia", sfd.parse("23/03/2018"), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(comment1, comment2));
        p2.getComments().addAll(Arrays.asList(comment3));

        postRepository.saveAll(Arrays.asList(p1, p2));

        maria.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(maria);
    }
}
