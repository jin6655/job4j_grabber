package ru.job4.grabber;

import ru.job4.model.Post;

import java.io.IOException;
import java.util.List;

public interface Parse {

    List<Post> list(String link) throws IOException;

}
