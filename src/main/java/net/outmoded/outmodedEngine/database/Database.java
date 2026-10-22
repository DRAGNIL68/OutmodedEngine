package net.outmoded.outmodedEngine.database;

public class Database {

    private Database() {}

    private static class SingletonHelper {
        private static final Database SINGLETON_INSTANCE = new Database();
    }

    public static Database getInstance() {
        return Database.SingletonHelper.SINGLETON_INSTANCE;
    }
}
