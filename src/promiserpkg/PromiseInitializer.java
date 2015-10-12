package promiserpkg;

@FunctionalInterface
public interface PromiseInitializer<T> {
	public void run(YPSuccess<T> resolve, YPError reject);
}
