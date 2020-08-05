# git24j-gradle-example
Demo of using git24j in gradle java application

#### Step 1, adding dependencies

Add the following to the `dependencies` section in [build.gradle](build.gradle).

> implementation 'com.github.git24j:git24j:1.0.0.20200805'

Then build the project with 
```
./gradlew build
```

Now you can start using some trivial git24j functions, like `Oid.of`

```java
public class GradleMain {
    public static void main(String[] args) {
        Oid oid = Oid.of("0123456789012345678901234567890123456789");
        System.out.println("Hello git24j: example Oid is " + oid.toString());
    }
}
```
