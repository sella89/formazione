package testFunctionalInterface;

public class testRetry {

	public static void main(String[] args) throws Throwable {

		boolean withRetryLogic = RetryUtils.withRetryLogic(3,1000, ()->{
			throw new Exception();
			});
	}

}
