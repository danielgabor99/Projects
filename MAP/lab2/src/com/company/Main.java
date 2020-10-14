package com.company;

import Controller.Controller;
import Model.Animale;
import Model.Pasari;
import Model.Porci;
import Model.Vaci;
import Repository.ArrayRepository;
import Repository.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(a.stream().filter(s -> s % 2 == 0).map(s -> "N" + (s + 1) + "R").reduce("", (s1, s2) -> s1 + s2));
    }
}
