# Promiser-java

A lightweight implementation of [Promises/A+](https://promisesaplus.com) specification, inspiration taken from JavaScript.

## Requirements

This library is written using Java 8 syntax.
To use this into Java 7, 6 and 5 projects, don't forget to install [retrolambda](https://github.com/orfjackal/retrolambda) by [@orfjackal](https://github.com/orfjackal)

Let's embrace the future ! 😄

### Usage

You can create a Promiser<T> object like this :
``` java
Promiser<T> P = new Promiser((Resolver resolve, Rejecter reject) -> {
  // Place your asynchronous process here, and make sure
  // to trigger resolve.run() or reject.run() when needed.
});
```

`<T>` is the type of the result returned in case of success.

You can handle result and error cases like this now :
```java
P.success((T result) -> {
  // Handle success here
})
.error((Object err) -> {
  // Handle error here
})
```

The `reject` callback always handle an error of type Object.

### Example

For example let's mock an asynchronous process using a Timer :
``` java
Promiser<String> P = new Promiser((Resolver resolve, Rejecter reject) -> {
  int DELAY = 500;

  new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
  	     resolve.run("I'm done !"); //resolving
      }
  }, DELAY);

});
```

Now we can handle the success or the error of this promise using the `.success()` and `.error()` callbacks :

``` java
P.success((String result) -> {
  // Handle success here
})
.error((Object err) -> {
  // Handle failure here
})
```


## Next step

* Make a Promiser instance "thenable" so we can have a `.then()` and `.catch()` callbacks and provide an asynchronous flow control using `.then()` like this :

```java
  P.then(...)
  .then(...)
  .then(...)
  .then(...)
  .then(...)
  .catch(...)

```
