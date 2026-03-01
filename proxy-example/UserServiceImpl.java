package proxy;

/**
 * 用户服务实现类（真实对象）
 */
public class UserServiceImpl implements UserService {
    
    @Override
    public void addUser(String username) {
        System.out.println("添加用户: " + username);
    }
    
    @Override
    public void deleteUser(String username) {
        System.out.println("删除用户: " + username);
    }
    
    @Override
    public String getUser(String username) {
        System.out.println("查询用户: " + username);
        return "User[" + username + "]";
    }
}
