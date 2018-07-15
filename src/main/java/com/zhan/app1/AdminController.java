package com.zhan.app1;

public class AdminController {
    
    private Admin model;
    private AdminView view;
    private AdminDAO database;
    
    AdminController(Admin model, AdminView view, AdminDAO database){
        this.model = model;
        this.view = view;
        this.database = database;
    }

    public AdminDAO getDatabase() {
        return database;
    }

    public void setDatabase(AdminDAO database) {
        this.database = database;
    }

    public Admin getModel() {
        return model;
    }

    public AdminView getView() {
        return view;
    }

    public void setModel(Admin model) {
        this.model = model;
    }

    public void setView(AdminView view) {
        this.view = view;
    }
    
    public void view(){
        view.printAdminInfo(model.getLogin(), model.getPassword());
    }
    
    public boolean validation(){
        if(model.getLogin().isEmpty())
        {
            return false;
        }
        if(model.getPassword().isEmpty())
        {
            return false;
        }
        return true;
    }

}
