package com.byted.camp.todolist.beans;

public enum Priority {
    NORMAL(0), HIGH(1), HIGHER(2);
    
    public final int intValue;

    Priority(int intValue) {
        this.intValue = intValue;
    }
    
    public static Priority from(int intValue) {
        for (Priority pri :
                Priority.values()) {
            if (pri.intValue == intValue) {
                return pri;
            }
        }
        return NORMAL; // default
    }
}
