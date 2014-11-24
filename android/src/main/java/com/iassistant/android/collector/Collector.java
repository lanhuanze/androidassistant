package com.iassistant.android.collector;

import com.iassistant.android.entities.AbstractModule;

/**
 * Created by lan on 11/24/14.
 */
public interface Collector<T extends AbstractModule> {
    T collect();
}
