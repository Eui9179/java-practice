package com.app.wisesaying.controller;

import com.app.wisesaying.Repository;
import com.app.wisesaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    final Repository repository = new Repository();
    final Scanner sc = new Scanner(System.in);

    public void register() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        Integer id = repository.register(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void showList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        ArrayList<WiseSaying> all = new ArrayList<>(repository.findAll());
        Collections.reverse(all);
        all.forEach(System.out::println);
    }

    public void remove (Integer id) {
        boolean isRemove = repository.remove(id);
        if (!isRemove) System.out.println(id + "번 명언은 존재하지 않습니다.");
        else System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    public void update(Integer id) {
        WiseSaying ws = repository.find(id);
        System.out.println("명언(기존) : " + ws.getContent());
        System.out.print("명언 : ");
        String updatedContent = sc.nextLine();

        System.out.println("작가(기존) : " + ws.getAuthor());
        System.out.print("작가 : ");
        String updatedAuthor = sc.nextLine();

        repository.update(id, updatedContent, updatedAuthor);
    }
}
