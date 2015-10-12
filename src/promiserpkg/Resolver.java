package promiserpkg;

@FunctionalInterface
public interface Resolver<U> {
	public void run(U t);
}
