package com.yorkwang.controller;

import com.jfinal.core.Controller;

public class AdminPageController extends Controller {
    public void index() {
        this.render("admin_page.html");
    }
}
