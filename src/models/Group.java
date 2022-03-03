package models;

import java.sql.Date;

/**
 * @author: DABAGIRE Valens
 * @description: Chatting group class to model the fields needed to create and read content
 *               from group messaging table
 **/

public class Group {

    private int id;
    private String groupName;
    private String description;
    private int creator;
    private java.sql.Date created_at;
    private java.sql.Date updated_at;

    public Group(){};

    public Group(int id, String name){
        this.id = id;
        this.groupName = name;
    }

    public Group(int id, String name, String description, int creator){
        this.id = id;
        this.groupName = name;
        this.description = description;
        this.creator = creator;
    }

    public Group(int id, String name, String description, int creator, java.sql.Date created_at, java.sql.Date updated_at) {
        this.id = id;
        this.groupName = name;
        this.description = description;
        this.creator = creator;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Group(String name, String description, int creator) {
        this.groupName = name;
        this.description = description;
        this.creator = creator;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
