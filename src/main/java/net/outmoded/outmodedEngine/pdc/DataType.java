package net.outmoded.outmodedEngine.pdc;
/*
this class exists to stop idiots
from placing custom types in DataContainers.
 */
public class DataType<T> {

    private DataType(){}

    public static final DataType<Integer> INTEGER = new DataType<>();
    public static final DataType<Float> FLOAT = new DataType<>();
    public static final DataType<String> STRING = new DataType<>();
    public static final DataType<Boolean> BOOLEAN = new DataType<>();
}
