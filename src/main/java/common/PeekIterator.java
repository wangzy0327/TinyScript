package common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class PeekIterator <T> implements Iterator<T>{

    private Iterator<T> it;

    //顺序流队列
    private LinkedList<T> queueCache = new LinkedList<>();
    //peek 暂存流的栈
    private LinkedList<T> stackPutStacks = new LinkedList<>();

    //结束Token标识
    private T _endToken = null;

    //流缓存大小
    private static final Integer CACHE_SIZE = 10;

    /**
     * 流每流过一个就消费掉一个
     * @param stream
     */
    public PeekIterator(Stream<T> stream){
        it = stream.iterator();
    }

    public PeekIterator(Stream<T> stream,T endToken){
        this.it = stream.iterator();
        this._endToken = endToken;
    }

    //缓存顺序  A -> B -> C -> D
    //放回顺序  D -> C -> B -> A
    //获取顶部元素
    public T peek(){
        //取出
        T val = null;
        if(this.stackPutStacks.size() > 0){
            return stackPutStacks.getLast();
        }
        if(!it.hasNext()){
            return _endToken;
        }
        val = next();
        this.putBack();
        return val;
    }

    //放回
    public void putBack(){
        if(this.queueCache.size() > 0){
            this.stackPutStacks.offer(this.queueCache.pollLast());
        }
    }


    @Override
    public boolean hasNext() {
        return _endToken != null || this.stackPutStacks.size() > 0 || it.hasNext();
    }

    @Override
    public T next() {
        T val = null;
        if(stackPutStacks.size() > 0){
            val = stackPutStacks.pollLast();
        }else{
            if(!this.it.hasNext() ){
                T tmp = _endToken;
                _endToken = null;
                return tmp;
            }
            val = it.next();
        }
        while(queueCache.size() >= CACHE_SIZE){
            queueCache.poll();
        }
        queueCache.offer(val);
        return val;
    }
}
