package com.curator.curator_count_demo.share_count;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class test {

    public static void main(String[] args) {
        Hasher hasher = Hashing.md5().newHasher().putString("127.0.0.1", Charsets.UTF_8);
        HashCode hashCode = hasher.hash();
        System.out.println("hashCode : " + hashCode);
        HashFunction hashFunction = Hashing.md5();
        HashCode aLong = hashFunction.hashLong(2);
        System.out.println("a = " + aLong);
    }
}
