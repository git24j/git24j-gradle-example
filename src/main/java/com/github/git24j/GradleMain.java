package com.github.git24j;

import com.github.git24j.core.Oid;

public class GradleMain {
    public static void main(String[] args) {
        Oid oid = Oid.of("0123456789012345678901234567890123456789");
        System.out.println("Hello git24j: example Oid is " + oid.toString());
    }
}
