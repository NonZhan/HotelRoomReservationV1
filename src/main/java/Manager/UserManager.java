

package Manager;

import DAO.UserDAO;
import Model.User;


public class UserManager
{
    private final User userModel;
    private final UserDAO userDAO;
    
    public UserManager(User userModel, UserDAO userDAO)
    {
        this.userModel = userModel;
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO()
    {
        return userDAO;
    }

    public User getUserModel()
    {
        return userModel;
    }
    
    public boolean validation()
    {
        if(userModel.getEmail().isEmpty())
        {
            return false;
        }
        if(userModel.getPassword().isEmpty())
        {
            return false;
        }
        return true;
    }

}