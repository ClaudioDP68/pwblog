/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.blog.blog.entity;

/**
 *
 * @author claudio
 */
public class UserUpdate {
    public String fname;
    public String lname;
    public String pwd;
    
    
    public UserUpdate() {
    }

    public UserUpdate(String fname, String lname, String pwd) {
        this.fname = fname;
        this.lname = lname;
        this.pwd = pwd;
        
    }
}
