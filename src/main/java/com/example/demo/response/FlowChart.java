package com.example.demo.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FlowChart {
    private List<Node> nodes=new ArrayList<>();
    private List<Edge> edges=new ArrayList<>();

    public void addNode(Node startNode) {

        this.nodes.add(startNode);
    }

    public void addEdge(String s, String s1) {
        this.edges.add(new Edge(s,s1));
    }
}
