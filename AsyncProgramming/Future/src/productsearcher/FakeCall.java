package productsearcher;

import java.util.concurrent.Callable;

public class FakeCall {
    private final String methodCall;
    private int latency;

    public FakeCall(int latency, String methodCall) {
        this.latency = latency;
        this.methodCall = methodCall;
    }

    public <T> T call(Callable<T> function) {
        sleep();
        try {
            debug("Calling " + this.methodCall);
            T theReturn = function.call();
            debug(String.format("Called %s return %s", this.methodCall, theReturn.toString()));
            return theReturn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void debug(String message) {
        if(TestConfig.DEBUG) {
            System.out.println(String.format("%s: %s", Thread.currentThread().getName(), message));
        }
    }

    private void sleep() {
        try {
            Thread.sleep(latency);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
