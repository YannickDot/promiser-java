package promiserpkg;

@FunctionalInterface
public interface PromiseInitializer<T> {
	public void run(Resolver<T> resolve, Rejecter reject);
}
