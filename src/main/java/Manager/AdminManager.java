

package Manager;

import DAO.AdminDAO;
import Model.Admin;


public class AdminManager
{   
    private final Admin model;
    private final AdminDAO dao;
    
    public AdminManager(Admin model, AdminDAO dao)
    {
        this.model = model;
        this.dao = dao;
    }

    public AdminDAO getDao()
    {
        return dao;
    }

    public Admin getModel()
    {
        return model;
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