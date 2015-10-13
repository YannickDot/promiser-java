package promiserpkg;

@FunctionalInterface
public interface PromiseInitializer<T, U> {
	public void run(Resolver<T> resolve, Rejecter<U> reject);
}
