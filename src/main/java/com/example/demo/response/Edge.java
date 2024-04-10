package com.example.demo.response;

import lombok.Data;

@Data
public class Edge {
    private String source;
    private String target;

    public Edge(String source, String target) {
        this.source = source;
        this.target = target;
    }
}
