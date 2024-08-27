package org.example.exeptions;

public class NotFoundStudent   extends Exception {
    public NotFoundStudent() {
        super("Student not found");
    }
}
