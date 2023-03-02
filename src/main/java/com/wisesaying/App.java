package com.wisesaying;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class App {
    Repository repository = new Repository();
    Scanner sc = new Scanner(System.in);

    public void run() throws IOException {
        String command;

        while (true) {
            System.out.print("명령) ");
            command = sc.nextLine();

            if (command.equals("종료")) {
                save();
                break;
            } else if (command.equals("등록")){
                register();
            } else if (command.equals("목록")) {
                showList();
            } else if (command.contains("삭제?id=")) {
                String[] ss = command.split("=");
                Integer id = Integer.parseInt(ss[ss.length - 1]);
                remove(id);
            } else if (command.contains("수정?id=")) {
                String[] ss = command.split("=");
                Integer id = Integer.parseInt(ss[ss.length - 1]);
                update(id);
            } else if (command.equals("빌드")) {
                build();
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

    public void save() throws IOException {
        File file = new File("src/main/resources/save.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fw);

        repository.findAll().iterator().forEachRemaining(
                ws -> {
                    try {
                        writer.write(ws.toString() + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        writer.close();
    }

    public void build () {
        JSONArray result = new JSONArray();
        JSONObject wsJson;

        List<WiseSaying> ws = repository.findAll();
        for (WiseSaying w : ws) {
            wsJson = new JSONObject();
            wsJson.put("id", w.getId());
            wsJson.put("content", w.getContent());
            wsJson.put("author", w.getAuthor());
            result.put(wsJson);
        }

        try {
            FileWriter file = new FileWriter("src/main/resources/data.json");
            file.write(result.toString());
            file.flush();;
            file.close();
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
