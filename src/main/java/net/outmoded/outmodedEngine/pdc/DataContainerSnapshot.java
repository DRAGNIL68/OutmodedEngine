package net.outmoded.outmodedEngine.pdc;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataContainerSnapshot {
    private final ConcurrentHashMap<String, DataObject<?>> hashMap = new ConcurrentHashMap<>();

    public DataContainerSnapshot(ConcurrentHashMap<String, DataObject<?>> oldHashMap){

        for (Map.Entry<String, DataObject<?>> entry : oldHashMap.entrySet()) {
            setSnapshotValue(entry.getKey(), entry.getValue());
        }

    }

    public DataObject<?> getDataObject(String namespacedId){
        return hashMap.get(namespacedId);
    }

    public Set<String> getKeys(){
        return hashMap.keySet();
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

    private <T> void setSnapshotValue(String namespacedId, DataObject<T> dataObject){
        hashMap.put(namespacedId, new DataObject<>(dataObject.dataType(), dataObject.value()));
    }

    //TODO finish
    /**
     * save a container to json
     */
    private String asJson(){
        return "N/A";
    }
}
