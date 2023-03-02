package com.app;

import com.app.build.JsonBuilder;
import com.app.files.FileSave;
import com.app.wisesaying.controller.WiseSayingController;

import java.io.IOException;

public class App {
    WiseSayingController controller = new WiseSayingController();

    public void run() throws IOException {
        String command;
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            command = MyScanner.getInstance().nextLine();

            if (command.equals("종료")) {
                FileSave.save();
                break;
            } else if (command.equals("등록")){
                controller.register();
            } else if (command.equals("목록")) {
                controller.showList();
            } else if (command.contains("삭제?id=")) {
                Integer id = getQueryId(command);
                controller.remove(id);
            } else if (command.contains("수정?id=")) {
                Integer id = getQueryId(command);
                controller.update(id);
            } else if (command.equals("빌드")) {
                new JsonBuilder().build();
            }
        }
    }

    public Integer getQueryId(String query) {
        return Integer.parseInt(query.split("=")[1]);
    }
}
