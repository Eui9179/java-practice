package com.wisesaying;

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
            }
            else if (command.equals("등록")){
                register();
            }
        }

    }

    public void register() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        Integer id = repository.register(content, author);
        System.out.println(id + "명령이 등록되었습니다.");
    }
}
