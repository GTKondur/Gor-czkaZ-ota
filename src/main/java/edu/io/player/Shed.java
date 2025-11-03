package edu.io.player;

import java.util.Stack;

public class Shed {
    private final Stack<Tool> tools = new Stack<>();

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Cannot add null tool");
        }
        tools.push(tool);
    }

    public Tool getTool() {

        return tools.isEmpty() ? new NoTool() : tools.peek();
    }

    public void dropTool() {
        if (!tools.isEmpty()) {
            tools.pop();
        }
    }
}
