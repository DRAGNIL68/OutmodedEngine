package net.outmoded.outmodedEngine;

import net.outmoded.outmodedEngine.templates.ModelTemplate;

/**
 * takes JSON and converts it to a ModelTemplate
 */
public interface JsonProcessor {

    public ModelTemplate process(String json);
}
