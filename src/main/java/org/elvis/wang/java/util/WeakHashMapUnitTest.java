package org.elvis.wang.java.util;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertTrue;

/**
 * Created by wangzhiqun on 2018/1/11.
 */
public class WeakHashMapUnitTest {

    public static void main(String[] args) {

        List<String> s = asList("1","3");

        System.out.println(s.contains("1"));

        boolean f = Boolean.valueOf("0");
        System.out.println(f);
    }

    @Test
    public void givenWeakHashMap_whenCacheValueThatHasNoReferenceToIt_GCShouldReclaimThatObject() {
        //given
        WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap<UniqueImageName, BigImage>();
        BigImage bigImage = new BigImage("image_id");
        UniqueImageName imageName = new UniqueImageName("name_of_big_image");

        map.put(imageName, bigImage);
        assertTrue(map.containsKey(imageName));

        //when big image key is not reference anywhere
        imageName = null;
        System.gc();

        //then GC will finally reclaim that object
        await().atMost(10, TimeUnit.SECONDS).until(map::isEmpty);
    }

    @Test
    public void givenWeakHashMap_whenCacheValueThatHasNoReferenceToIt_GCShouldReclaimThatObjectButLeaveReferencedObject() {
        //given
        WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap<>();
        BigImage bigImageFirst = new BigImage("foo");
        UniqueImageName imageNameFirst = new UniqueImageName("name_of_big_image");

        BigImage bigImageSecond = new BigImage("foo_2");
        UniqueImageName imageNameSecond = new UniqueImageName("name_of_big_image_2");

        map.put(imageNameFirst, bigImageFirst);
        map.put(imageNameSecond, bigImageSecond);
        assertTrue(map.containsKey(imageNameFirst));
        assertTrue(map.containsKey(imageNameSecond));

        //when
        imageNameFirst = null;
        System.gc();

        //then
        await().atMost(10, TimeUnit.SECONDS).until(() -> map.size() == 1);
        await().atMost(10, TimeUnit.SECONDS).until(() -> map.containsKey(imageNameSecond));
    }



    class BigImage {
        public final String imageId;

        BigImage(String imageId) {
            this.imageId = imageId;
        }
    }

    class UniqueImageName {
        public final String imageName;

        UniqueImageName(String imageName) {
            this.imageName = imageName;
        }
    }
}
