package com.app.wisesaying;

import com.app.wisesaying.entity.WiseSaying;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Repository {
    public final static Map<Integer, WiseSaying> repository = new HashMap<>();

    public static ArrayList<WiseSaying> toArrayList() {
        return new ArrayList<>(repository.keySet().stream().map(repository::get).toList());
    }

    public Repository() {
        init();
    }

    public void init() {
        try {
            File file = new File("src/main/resources/save.txt");
            if (!file.exists()) {
                file.createNewFile();
                return;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] texts = sc.nextLine().split("/");
                register(texts[2].trim(), texts[1].trim());
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer register(String content, String author) {
        WiseSaying ws = new WiseSaying(content, author);
        repository.put(ws.getId(), ws);
        return ws.getId();
    }

    public WiseSaying find(Integer id) {
        return repository.get(id);
    }

    public List<WiseSaying> findAll() {
        return repository.keySet().stream().map(repository::get).toList();
    }

    public boolean remove(Integer id) {
        if (repository.get(id) == null) {
            return false;
        }
        repository.remove(id);
        return true;
    }

    public void update(Integer id, String content, String author) {
        WiseSaying ws = repository.get(id);
        ws.setContent(content);
        ws.setAuthor(author);
    }
}
