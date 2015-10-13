# Promiser-java

A lightweight implementation of [Promises/A+](https://promisesaplus.com) specification, inspiration taken from JavaScript.

## Requirements

This library is written using Java 8 syntax.

Let's embrace the future ! 😄

To use this into Java 7, 6 and 5 projects, don't forget to install [retrolambda](https://github.com/orfjackal/retrolambda) by [@orfjackal](https://github.com/orfjackal).


### Usage

You can create a Promiser<T, U> object like this :
``` java
Promiser<T, U> p = new Promiser<T, U>((Resolver<T> resolve, Rejecter<U> reject) -> {

  // Place your asynchronous process here, and make sure
  // to trigger resolve.run() or reject.run() when needed.

});
```

`<T>` is the type of the result returned in case of success and `<U>` is the type of the result returned in case of error.

You can handle result and error cases like this now :
```java
p.success((T result) -> {
  // Handle success here

})
.error((U err) -> {
  // Handle error here

})
```
---

### Example

For example let's mock an asynchronous process using a Timer :
``` java
Promiser<String, Integer> p = new Promiser<String, Integer>((Resolver<String> resolve, Rejecter<Integer> reject) -> {
  int DELAY = 500;

  new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
  	     resolve.run("I'm done !"); //resolving
         reject.run(404); //rejecting
      }
  }, DELAY);

});
```

Or using **Retrofit V2**

```java
 Promiser<String, Integer> p = new Promiser<>((Resolver<String> resolve, Rejecter<Integer> reject) ->
    retrofit.getService(IUserService.class).fetchUsers().enqueue(new Callback<String>() {
          @Override public void onResponse(Response<String> response) {
            if(response.isSuccess())
              resolve.run(response.body());
            else
              reject.run(response.code());
          }
​
          @Override public void onFailure(Throwable t)
          {
            reject.run(CodeError.Undefined.getCode());
          }
  }));
}
```

Now we can handle the success or the error of this promise using the `.success()` and `.error()` callbacks :

``` java
p.success((String result) -> {
  // Handle success here
})
.error((Integer err) -> {
  // Handle failure here
})
```

## Next step

* Make a Promiser instance "thenable" so we can have a `.then()` and `.catch()` callbacks and provide an asynchronous flow control using `.then()` like this :

```java
  p.then(...)
  .then(...)
  .then(...)
  .catch(...)

```



<!-- p.then(parse)
.then(transform)
.then(duplicate)
.then(render)
.catch(handleError) -->
