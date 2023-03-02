package com.app.build;

import com.app.wisesaying.Repository;
import com.app.wisesaying.entity.WiseSaying;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonBuilder {
    public void build () {
        JSONArray result = new JSONArray();
        JSONObject wsJson;

        for (WiseSaying w : Repository.toArrayList()) {
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
