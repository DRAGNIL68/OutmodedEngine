package net.outmoded.outmodedEngine.pdc;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataContainer {
    private final ConcurrentHashMap<String, DataObject<?>> hashMap = new ConcurrentHashMap<>();


    public static DataContainer empty(){
        return new DataContainer();
    }
    public static DataContainer fromJson(String json){
        //TODO finish
        DataContainer dataContainer = new DataContainer();

        // do stuff
        return dataContainer;
    }

    public static DataContainer fromSnapshot(DataContainerSnapshot snapshot){
        Set<String> keys = snapshot.getKeys();
        DataContainer dataContainer = new DataContainer();

        for (String key : keys) {
            DataContainer.setSnapshotValue(dataContainer, key, snapshot.getDataObject(key));
        }

        return dataContainer;
    }

    private DataContainer(){}

    public <T> void setValue(String namespacedId, DataType<T> dataType, T data){
        hashMap.put(namespacedId, new DataObject<>(dataType, data));
    }

    public DataObject<?> getDataObject(String namespacedId){
        return hashMap.get(namespacedId);
    }

    public boolean hasValue(String namespacedId){
        return hashMap.containsKey(namespacedId);
    }

    public DataType<?> getValueType(String namespacedId){
        return hashMap.get(namespacedId).dataType();
    }

    public <T> T getValue(String namespacedId, DataType<T> dataType){
        return (T) hashMap.get(namespacedId).value();
    }

    public Set<String> getKeys(){
        return hashMap.keySet();
    }

    /**
     * create a snapshot of the DataContainer that can then be viewed
     */
    public DataContainerSnapshot snapshot(){
        return new DataContainerSnapshot(hashMap);
    }

    private static  <T> void setSnapshotValue(DataContainer dataContainer, String namespacedId, DataObject<T> dataObject){
        dataContainer.setValue(namespacedId, dataObject.dataType(), dataObject.value());
    }





}
