package com.example.inos.echome.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by vinitsoni on 2018-01-21.
 */

public class QuestionStat {

    @SerializedName("answer_map")
    public Map<String, Integer> answer_map;

}
