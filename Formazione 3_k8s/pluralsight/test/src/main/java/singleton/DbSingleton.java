package singleton;

public class DbSingleton {

    private static volatile DbSingleton instance = null;

    public DbSingleton() {
        if (instance!=null){
            throw new RuntimeException("Use getInstace() method to create");
        }
    }

    public static DbSingleton getInstance(){
        if(instance==null){
            synchronized (DbSingleton.class) {
                if(instance==null) {
                    instance = new DbSingleton();
                }
            }
        }
        return instance;
    }

}
