package readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Mr.xiao
 * @create 2021-02-07 18:01
 */

public class Cache<K,V> {
    final Map<K, V> m =
            new HashMap<>();
    final ReadWriteLock rwl =
            new ReentrantReadWriteLock();
    // 读锁
    final Lock r = rwl.readLock();
    // 写锁
    final Lock w = rwl.writeLock();
    // 读缓存
    V get(K key) {
        V res;
        r.lock();
        try {
            res= m.get(key);
        }
        finally { r.unlock(); }
        if(res!=null){
            return res;
        }
        //缓存没有情况，需要查询数据再写进cache
        w.lock();
        try {
            K key1=null;
            m.put(key1, res);
        }finally {
            w.unlock();
        }
        return res;
    }
    // 写缓存
    V put(K key, V value) {
        w.lock();
        try { return m.put(key, v); }
        finally { w.unlock(); }
    }
}