package promiserpkg;

@FunctionalInterface
public interface Rejecter {
	public void run(Object o);
}