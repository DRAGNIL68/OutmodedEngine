package net.outmoded.outmodedEngine.database;

import net.outmoded.outmodedEngine.Co;
import net.outmoded.outmodedEngine.OutmodedEngine;
import net.outmoded.outmodedEngine.live.Model;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;

import java.io.File;
import java.sql.*;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

// this is a singleton as there is exactly ZERO reason for it not to be
public class Database {

    //NOTE all database calls must have an async block using connection as the lock
    private Connection connection;
    private final Object syncLock = new Object();

    private Database() {}

    private static class SingletonHelper {
        private static final Database SINGLETON_INSTANCE = new Database();
    }

    public static Database getInstance() {
        return Database.SingletonHelper.SINGLETON_INSTANCE;
    }


    public void load(){
        if (connection != null)
            throw new RuntimeException("cannot load database more than once");

        this.connect();
        this.createTables();
    }

    private void connect() {
        try {
            if (this.connection != null && !this.connection.isClosed())
                return;

            Class.forName("org.sqlite.JDBC");

            final File file = new File(OutmodedEngine.getInstance().getDataFolder(), "save_data.sqlite");

            if (!file.exists())
                file.createNewFile();

            final String jdbcUrl = "jdbc:sqlite:" + file.getAbsolutePath();
            this.connection = DriverManager.getConnection(jdbcUrl);

        } catch (final Exception ex) {
            Co.sevr(" Failed to connect to database: " + ex.getMessage());
        }
    }

    // does not need to be async as it only going to be run once at the start same with close()
    private void createTables() {

        try {
            final Statement statement = this.connection.createStatement();
            statement.execute("PRAGMA foreign_keys = ON");

            statement.execute("CREATE TABLE IF NOT EXISTS savedata (" +
                    "model_uuid VARCHAR(36) PRIMARY KEY, " +
                    "model_template NOT NULL," +

                    "chunk_id NOT NULL," +
                    "location NOT NULL," +
                    "dataholder" +
                    ")");

            // Create index for faster lookups
            statement.execute("CREATE INDEX IF NOT EXISTS chunk_ids ON savedata (chunk_id)");

            statement.close();

        } catch (final SQLException ex) {
            Co.sevr("Failed to create tables: " + ex.getMessage());
        }
    }

    /**
     * Close the database connection
     */
    public void close() {
        synchronized (syncLock) {
            try {
                if (this.connection != null && !this.connection.isClosed()) {
                    this.connection.close();
                    this.connection = null; // so that the database can be restarted if need
                    Co.sevr("Database connection closed.");
                }
            } catch (final SQLException ex) {
                Co.sevr("sql error "+ex.getSQLState());
            }
        }

    }

    public boolean addModel(ModelData modelData) {
        synchronized (syncLock) {
            if (!modelData.isPersistent())
                return false;

            try {
                //removeModel(modelClass.uuid);
                //Location location = modelClass.getOriginLocation();
                //String chunk_id = location.getWorld().getName()+"|x-"+location.getChunk().getX()+"|z-"+location.getChunk().getZ();

                final PreparedStatement statement = this.connection.prepareStatement(
                        "INSERT INTO save_data (model_uuid, model_template, chunk_id, location, dataholder) VALUES (?, ?, ?, ?, ?)");

                statement.setString(1, modelData.uuid().toString());
                statement.setString(2, modelData.getTemplateKey().toString());
                statement.setString(3, "chunk_id");
                statement.setString(4, "location");


                final int rows = statement.executeUpdate();
                statement.close();
                return rows > 0;


            } catch (final SQLException ex) {
                ex.printStackTrace();

                return false;
            }
        }
    }
    //TODO needs work
    public static class ModelData{

        public static ModelData fromDatabase(){
            return null;
        }

        public static ModelData toModelData(Model model){
            return null;
        }

        private ModelData(Model model){}

        public boolean isPersistent(){ return true; }
        public UUID uuid(){ return null; }
        public NamespacedKey getTemplateKey(){ return null; }

    }
}
