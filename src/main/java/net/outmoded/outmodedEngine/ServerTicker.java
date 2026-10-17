package net.outmoded.outmodedEngine;



/**
 * a simple server tick implementation
 * NOT async safe DO NOT call methods from other threads
 */
public class ServerTicker {
    private boolean run = true;
    private final long tickTime;

    /**
     * tick time in milliseconds
     */
    public ServerTicker(long tickTime){
        this.tickTime = tickTime*1000000; // yes I am converting it to nanoseconds
    }


    /**
     * stops the tick use start to start it once more
     */
    public void stop(){ run = false; }

    /**
     * creates a timed tick loop
     */
    public void loop(Runnable runnable){ // runs the logic tickTime times per second
        // this code is shit`
        long lastRun = System.nanoTime();
        long deltaTime;

        long accumulator = tickTime;

        while (run){

            deltaTime = (System.nanoTime() - lastRun);
            lastRun = System.nanoTime();

            accumulator += deltaTime;

            // 1000 = 1s 1000/20 = 50 (20 times per sec)

            while (accumulator >= tickTime){
                accumulator -= tickTime;
                runnable.run();
            }
        }
    }
}
