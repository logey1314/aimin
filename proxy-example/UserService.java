package proxy;

/**
 * 用户服务接口
 */
public interface UserService {
    void addUser(String username);
    void deleteUser(String username);
    String getUser(String username);
}
