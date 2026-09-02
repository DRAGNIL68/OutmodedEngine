package net.outmoded.outmodedEngine.pdc;

/**
 * stores internal data about values
 */
public record DataObject<T>(DataType<T> dataType, T value) {
}
