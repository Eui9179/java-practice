package com.wisesaying;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    final static Map<Integer, WiseSaying> repository = new HashMap<>();

    public Integer register(String content, String author) {
        WiseSaying ws = new WiseSaying(content, author);
        repository.put(ws.getId(), ws);
        return ws.getId();
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
}
