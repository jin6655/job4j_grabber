package ru.job4.tdd;

import org.junit.Test;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CinemaTest {

    /*
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenAddSession() {
        Cinema cinema = new Cinema3D();
        Session session = new CinemaSession();
        cinema.add(session);
        List<Session> expected = new ArrayList<>();
        Predicate<Session> filter = new Predicate{...};
        assertThat(expected, cinema.find(filter));

    @Test(expected = IllegalArgumentException.class)
    public  void WhenDateInvalid {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        date.set(2022, 7, 18);
        Ticket ticket = cinema.buy(account, 1, 2, date);
    }
        @Test(expected = IllegalArgumentException.class)
    public  void WhenRowInvalid {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        date.set(2022, 7, 18);
        Ticket ticket = cinema.buy(account, 1, 2, date);
    }
        @Test(expected = IllegalArgumentException.class)
    public  void WhenColumnInvalid {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        date.set(2022, 7, 18);
        Ticket ticket = cinema.buy(account, 1, 2, date);
    }

     */

}