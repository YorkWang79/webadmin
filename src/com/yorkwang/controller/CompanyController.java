package com.yorkwang.controller;

import com.jfinal.core.Controller;

public class CompanyController extends Controller {
    public void edit() {
        this.render("company_edit.html");
    }
}
