package com.github.git24j;

import com.github.git24j.core.Init;
import com.github.git24j.core.Libgit2;
import com.github.git24j.core.Oid;
import com.github.git24j.core.Repository;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GradleMain {
    public static void main(String[] args) {
        // Following two lines does not require libgit2 or libgit24j
        Oid oid = Oid.of("0123456789012345678901234567890123456789");
        System.out.println("Hello git24j: example Oid is " + oid.toString());

        // load native libraries and initialize
        Path target = Paths.get("dist/git24j/target");
        Init.loadLibraries(target.resolve("git2"), target.resolve("git24j"));
        Libgit2.init();

        // Start using
        Path repoDir = Paths.get(System.getProperty("user.dir"));
        try (Repository repo = Repository.open(repoDir)){
            System.out.println("Hellow git24j: example repo is " + repo.workdir());
        }

        // Shutdown and clean up libgit2 global states.
        // As libgit2 101 doc explained, usually you don't need to call this,
        // unless you want to be frugal with your resources.
        Libgit2.shutdown();
    }
}
