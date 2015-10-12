package promiserpkg;

@FunctionalInterface
public interface YPSuccess<U> {
	public void run(U t);
}
