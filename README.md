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
#### Step 2, install and compile native libraries

You can install the shared libraries manually:
```shell script
git clone https://github.com/git24j/git24j.git dist/git24j
git -C dist/git24j/ submodule sync --recursive
git -C dist/git24j/ submodule update --init --recursive
make -C dist/git24j/ 
```

or create it a pre-build gradle task, so the shared libraries will be built automatically:
```
task installSharedLib(type: Exec) {
    commandLine "./install_native_libraries.sh"
}
build.dependsOn installSharedLib
```

If install succeeds, you should see compiled shared libraries:
```
# in linux:
$ find dist/git24j/ -name '*.so'
dist/git24j/target/git24j/libgit24j.so
dist/git24j/target/git2/libgit2.so
# in MacOs:
$ find dist/git24j/ -name '*.dylib'
dist/git24j/target/git24j/libgit24j.dylib
dist/git24j/target/git2/libgit2.dylib
```

#### Step 3: initialize shared lib in the project

Code to load and initialize git24j can be found in [GradleMain.java](src/main/java/com/github/git24j/GradleMain.java), more specifically:

```
// load native libraries and initialize
Path target = Paths.get("dist/git24j/target");
Init.loadLibraries(target.resolve("git2"), target.resolve("git24j"));
Libgit2.init();

// Start using
Path repoDir = Paths.get(System.getProperty("user.dir"));
try (Repository repo = Repository.open(repoDir)){
    System.out.println("Hellow git24j: example repo is " + repo.workdir());
}
```