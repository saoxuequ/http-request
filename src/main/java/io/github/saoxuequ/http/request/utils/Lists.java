package io.github.saoxuequ.http.request.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lists {

    private Lists() {
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> newArrayList(E... elements) {
        Preconditions.checkNotNull(elements);
        ArrayList<E> list = new ArrayList<E>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> ArrayList<E> newArrayList(List<E> elements) {
        Preconditions.checkNotNull(elements);
        ArrayList<E> list = new ArrayList<E>(elements.size());
        list.addAll(elements);
        return list;
    }

    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList();
    }

    public static <E> LinkedList<E> newLinkedList(E... elements) {
        Preconditions.checkNotNull(elements);
        LinkedList<E> list = new LinkedList<E>();
        Collections.addAll(list, elements);
        return list;
    }

    public static <E> LinkedList<E> newLinkedList(List<E> elements) {
        Preconditions.checkNotNull(elements);
        LinkedList<E> list = new LinkedList<E>();
        list.addAll(elements);
        return list;
    }
}
