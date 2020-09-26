package com.winkey.scheduler;

/**
 * All scheduler beans should implement this.
 *
 */

public interface Scheduler {
    void schedule(Integer id);
    boolean acquireLock(Integer id);
    void resetLock(Integer id);
}
