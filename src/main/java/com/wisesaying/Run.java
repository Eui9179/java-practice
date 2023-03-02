package com.wisesaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    Repository repository = new Repository();
    Scanner sc = new Scanner(System.in);

    public void run() {
        String command;

        while (true) {
            System.out.print("명령) ");
            command = sc.nextLine();

            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")){
                register();
            } else if (command.equals("목록")) {
                showList();
            } else if (command.contains("삭제?id=")) {
                String[] ss = command.split("=");
                Integer id = Integer.parseInt(ss[ss.length - 1]);
                remove(id);
            }
        }

    }

    public void register() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        Integer id = repository.register(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void showList() {
        List<WiseSaying> all = repository.findAll();
        all.forEach(System.out::println);
    }

    public void remove (Integer id) {
        boolean isRemove = repository.remove(id);
        if (!isRemove) System.out.println(id + "번 명언은 존재하지 않습니다.");
        else System.out.println(id + "번 명언이 삭제되었습니다.");
    }

}
