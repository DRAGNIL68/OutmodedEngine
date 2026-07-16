package net.outmoded.outmodedEngine.interfaces;

import net.outmoded.outmodedEngine.templates.ModelTemplate;

/**
 * takes JSON and converts it to a ModelTemplate
 */
public interface JsonProcessor {

    public ModelTemplate process(String json);
}
