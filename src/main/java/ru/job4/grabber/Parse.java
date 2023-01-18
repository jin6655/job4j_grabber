package ru.job4.grabber;

import java.io.IOException;
import java.util.List;

public interface Parse {

    List<Post> list() throws IOException;

}
