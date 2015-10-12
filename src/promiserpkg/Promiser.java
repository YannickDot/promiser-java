package promiserpkg;

public class Promiser<T> {
	
	public PromiseState state;
	public YPSuccess<T> _success = null;
	public YPError _error = null;
	public PromiseInitializer<T> _init;
	
	T resolveResult;
	Object rejectError;
	
	public Promiser(PromiseInitializer<T> init) {
		this._init = init;
		state = PromiseState.PENDING;
		this.go();
	}

	public YPSuccess<T> resolve = (T res) -> {
		state = PromiseState.FULFILLED;
		resolveResult = res;
		next();
	};
	
	public YPError reject = (Object err) -> {
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


	public Promiser<T> success(YPSuccess<T> pSuccess) {
		this._success = pSuccess;
		if(state == PromiseState.FULFILLED) {
			this._success.run(resolveResult);
		}
		return this;
	}
	
	public Promiser<T> then(thenFunc<T> pThen) {
		PromiseInitializer<T> c = (YPSuccess<T> resolve, YPError reject) -> {
			try {
				resolve.run(pThen.run(resolveResult));
			} catch  (Exception e) {
				reject.run(e);
			}
		}; 
		Promiser<T> p = new Promiser<T>(c);
		return p;
	}

	
	public Promiser<T> error(YPError pError) {
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
