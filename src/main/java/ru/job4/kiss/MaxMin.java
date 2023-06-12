package ru.job4.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class MaxMin {

    private static <T> T search (List<T> list, BiPredicate<T, T> pred) {
        T rsl = list.get(0);
        for (T i : list) {
            if (pred.test(rsl, i)) {
                rsl = i;
            }
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return search(value, (o1, o2) -> comparator.compare(o1, o2) < 0);
    }


    public <T> T min(List<T> value, Comparator<T> comparator) {
        return search(value, (o1, o2) -> {return comparator.compare(o1, o2) > 0;});
    }


}
