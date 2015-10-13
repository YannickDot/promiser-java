package promiserpkg;

@FunctionalInterface
public interface Resolver<T> {
	public void run(T t);
}
