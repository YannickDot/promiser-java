package promiserpkg;

public class Promiser<T> {
	
	public PromiseState state;
	private Resolver<T> _success = null;
	private Rejecter _error = null;
	public PromiseInitializer<T> _init;
	
	T resolveResult;
	Object rejectError;
	
	public Promiser(PromiseInitializer<T> init) {
		this._init = init;
		state = PromiseState.PENDING;
		this.go();
	}

	public Resolver<T> resolve = (T res) -> {
		state = PromiseState.FULFILLED;
		resolveResult = res;
		next();
	};
	
	public Rejecter reject = (Object err) -> {
		state = PromiseState.REJECTED;
		rejectError = err;
		next();
	};
	
	private void next () {
		if(state == PromiseState.FULFILLED && this._success != null) {
			this._success.run(resolveResult);
			
		} else if(state == PromiseState.REJECTED && this._error != null) {
			this._error.run(rejectError);
			
		} else {
			
		}
	}


	public Promiser<T> success(Resolver<T> pSuccess) {
		this._success = pSuccess;
		if(state == PromiseState.FULFILLED) {
			this._success.run(resolveResult);
		}
		return this;
	}
	
	public Promiser<T> then(thenFunc<T> pThen) {
		PromiseInitializer<T> c = (Resolver<T> resolve, Rejecter reject) -> {
			try {
				resolve.run(pThen.run(resolveResult));
			} catch  (Exception e) {
				reject.run(e);
			}
		}; 
		Promiser<T> p = new Promiser<T>(c);
		return p;
	}

	
	public Promiser<T> error(Rejecter pError) {
		this._error = pError;
		if(state == PromiseState.REJECTED) {
			this._error.run(rejectError);
		}
		return this;
	}
	
	public Promiser<T> go() {
		_init.run(this.resolve, this.reject);
		return this;
	}
}
