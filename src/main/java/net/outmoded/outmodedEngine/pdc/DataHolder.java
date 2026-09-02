package net.outmoded.outmodedEngine.pdc;

public interface DataHolder {

    public DataContainer getDataContainer();

    /**
     * copy it before setting it to stop async problems
     * @param dataContainer
     */
    public void setDataContainer(DataContainer dataContainer);
}
