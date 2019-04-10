package com.assignmentjavaweb.entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Feedback {

    private int id;
    private int userId;
    private String username;
    private String title;
    private String content;
    private long createdAt;
    private long updatedAt;
    private int status;
    HashMap<String, ArrayList<String>> errors = new HashMap<>();

    public Feedback() {
    }



    public Feedback(int id, int userId, String title, String content, long createdAt, long updatedAt, int status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Feedback(int userId, String title, String content, long createdAt, long updatedAt, int status) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public Feedback(int userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Feedback(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Feedback(String title, String content, long updatedAt, int status) {
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid() {
        validate();
        return this.errors.size() == 0;
    }

    private void validate() {
        if (this.errors == null) {
            this.errors = new HashMap<>();
        }
        ArrayList<String> titleError = this.errors.get("title");
        if (titleError == null) {
            titleError = new ArrayList<>();
        }
        if (this.content == null || this.content.length() == 0) {
            titleError.add("Title is required!!!");
        }
        if (titleError.size() > 0) {
            this.errors.put("title",titleError);
        }

        ArrayList<String> contentError = this.errors.get("content");

        if (contentError == null) {
            contentError = new ArrayList<>();
        }
        if (this.content == null || this.content.length() == 0) {
            contentError.add("Content is required!!!");
        }
        if (contentError.size() > 0) {
            this.errors.put("content",contentError);
        }
    }

    public HashMap<String, ArrayList<String>> getErrors() {
        return this.errors;
    }
}
