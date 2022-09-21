package singleton;

public class DbSingletonDemo {
    public static void main(String[] args){
        DbSingleton instance = DbSingleton.getInstance();
        System.out.println(instance);

        DbSingleton anotherIstance = DbSingleton.getInstance();
        System.out.println(anotherIstance);


    }
}
