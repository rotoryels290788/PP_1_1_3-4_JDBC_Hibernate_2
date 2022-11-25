package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Ivan","Ivanov",(byte)11);
        user.saveUser("Petr","Petrov",(byte)22);
        user.saveUser("Sidor","Sidorov",(byte)33);
        user.saveUser("Aleksandr","Pushkin",(byte)44);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();

    }
}