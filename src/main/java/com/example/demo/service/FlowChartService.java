package com.example.demo.service;

import com.example.demo.response.FlowChart;
import com.example.demo.response.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FlowChartService {

    private static final Pattern STATEMENT_PATTERN = Pattern.compile("(.*?)([{;])");
    private static final Pattern CONTROL_FLOW_PATTERN = Pattern.compile("if|else|for|while|do|switch");
    public FlowChart getFlowChart(String code) {

        List<String> statements = parseCode(code);
        return buildFlowchart(statements);
    }
    // Method to parse code into individual statements
    private List<String> parseCode(String code) {
        List<String> statements = new ArrayList<>();
        Matcher matcher = STATEMENT_PATTERN.matcher(code);
        while (matcher.find()) {
            String statement = matcher.group(1).trim();
            if (!statement.isEmpty()) {
                statements.add(statement);
            }
        }
        return statements;
    }

    private FlowChart buildFlowchart(List<String> statements) {
        FlowChart flowchart = new FlowChart();
        Node startNode = new Node("Start");
        flowchart.addNode(startNode);

        Node previousNode = startNode;
        for (String statement : statements) {
            Node currentNode = new Node(statement);
            flowchart.addNode(currentNode);
            flowchart.addEdge(previousNode.getLabel(), currentNode.getLabel());
            previousNode = currentNode;

            if (isControlFlowStatement(statement)) {
                // Handle control flow statements
                // Example: if, else, for, while, etc.
                // You may need more sophisticated logic for control flow handling
            }
        }

        Node endNode = new Node("End");
        flowchart.addNode(endNode);
        flowchart.addEdge(previousNode.getLabel(), endNode.getLabel());

        return flowchart;
    }
    private boolean isControlFlowStatement(String statement) {
        Matcher matcher = CONTROL_FLOW_PATTERN.matcher(statement);
        return matcher.find();
    }

}
