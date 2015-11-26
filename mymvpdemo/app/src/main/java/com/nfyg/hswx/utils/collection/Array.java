
package com.nfyg.hswx.utils.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 可改变大小的，有序的或无序的对象数组 如果是无序的，该类应该避免当删除元素时内存拷贝（最后一个元素的位置移到被删除元素的位置）
 */
public class Array<T> implements Iterable<T>
{

    /********************************************************
     * 
     * 构造函数
     * 
     ********************************************************/
    /**
     * 指定Array的容量与是否有序
     * 
     * @param ordered
     *            是否有序
     * @param capacity
     *            容量
     */
    public Array(boolean ordered, int capacity)
    {
        this.ordered = ordered;
        items = (T[]) new Object[capacity];
    }

    /**
     * 
     * @param ordered
     *            是否有序
     * @param capacity
     *            容量
     * @param arrayType
     *            类型
     */
    public Array(boolean ordered, int capacity, Class<T> arrayType)
    {
        this.ordered = ordered;
        items = (T[]) java.lang.reflect.Array.newInstance(arrayType, capacity);
    }

    /** 用指定的array创建一个新的Array */
    public Array(Array array)
    {
        this(array.ordered, array.size, (Class<T>) array.items.getClass().getComponentType());
        size = array.size;
        System.arraycopy(array.items, 0, items, 0, size);
    }

    /**
     * 创建array
     * 
     * @param ordered
     *            是否有序
     * @param array
     *            对象类型
     */
    public Array(boolean ordered, T[] array)
    {
        this(ordered, array.length, (Class) array.getClass().getComponentType());
        size = array.length;
        System.arraycopy(array, 0, items, 0, size);
    }

    /** 创建一个有序的数组，容量为16 */
    public Array()
    {
        this(true, 16);
    }

    public Array(boolean ordered)
    {
        this(ordered, 16);
    }

    /** 创建一个指定容量的有序的数组 */
    public Array(int capacity)
    {
        this(true, capacity);
    }

    /** 创建指定类型的有序数组，容量为16 */
    public Array(Class<T> arrayType)
    {
        this(true, 16, arrayType);
    }

    /** 用制定的array创建一个有序的array */
    public Array(T[] array)
    {
        this(true, array);
    }

    /********************************************************
     * 
     * 公共方法
     * 1. add相关
     * 
     ********************************************************/
    /**
     * 添加元素
     * 
     * @param value
     */
    public void add(T value)
    {
        T[] items = this.items;
        if (size == items.length)
            items = resize(Math.max(8, (int) (size * 1.75f)));
        items[size++] = value;
    }

    /**
     * 添加所有
     * 
     * @param array
     *            指定的数据源
     */
    public void addAll(Array array)
    {
        addAll(array, 0, array.size);
    }

    /**
     * 添加所有
     * 
     * @param array
     *            指定的数据源
     * @param offset
     *            起始位置
     * @param length
     *            长度
     */
    public void addAll(Array array , int offset , int length)
    {
        if (offset + length > array.size)
            throw new IllegalArgumentException("offset + length must be <= size: " + offset + " + " + length + " <= "
                    + array.size);
        addAll((T[]) array.items, offset, length);
    }

    /**
     * 添加所有
     * 
     * @param array
     *            指定的数据源
     */
    public void addAll(T[] array)
    {
        addAll(array, 0, array.length);
    }

    /**
     * 添加所有
     * 
     * @param array
     *            指定的数据源
     * @param offset
     *            起始位置
     * @param length
     *            长度
     */
    public void addAll(T[] array , int offset , int length)
    {
        T[] items = this.items;
        int sizeNeeded = size + length;
        if (sizeNeeded > items.length)
            items = resize(Math.max(8, (int) (sizeNeeded * 1.75f)));
        System.arraycopy(array, offset, items, size, length);
        size += length;
    }

    /********************************************************
     * 
     * 公共方法
     * 2.get set相关
     * 
     ********************************************************/

    public T get(int index)
    {
        if (index >= size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        return items[index];
    }

    public void set(int index , T value)
    {
        if (index >= size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        items[index] = value;
    }

    /********************************************************
     * 
     * 公共方法
     * 3.insert相关
     * 
     ********************************************************/
    public void insert(int index , T value)
    {
        T[] items = this.items;
        if (size == items.length)
            items = resize(Math.max(8, (int) (size * 1.75f)));
        if (ordered)
            System.arraycopy(items, index, items, index + 1, size - index);
        else
            items[size] = items[index];
        size++;
        items[index] = value;
    }

    /********************************************************
    * 
    * 公共方法
    * 3.swap相关
    * 
    ********************************************************/
    /**
     * 交换
     * @param first
     * @param second
     */
    public void swap(int first , int second)
    {
        if (first >= size)
            throw new IndexOutOfBoundsException(String.valueOf(first));
        if (second >= size)
            throw new IndexOutOfBoundsException(String.valueOf(second));
        T[] items = this.items;
        T firstValue = items[first];
        items[first] = items[second];
        items[second] = firstValue;
    }

    /********************************************************
     * 
     * 公共方法
     * 4.查找相关
     * 
     ********************************************************/
    /**
     * @param identity
     *            If true, == comparison will be used. If false, .equals()
     *            comaparison will be used.
     */
    public boolean contains(T value , boolean identity)
    {
        T[] items = this.items;
        int i = size - 1;
        if (identity || value == null)
        {
            while (i >= 0)
                if (items[i--] == value)
                    return true;
        }
        else
        {
            while (i >= 0)
                if (value.equals(items[i--]))
                    return true;
        }
        return false;
    }

    public int indexOf(T value , boolean identity)
    {
        T[] items = this.items;
        if (identity || value == null)
        {
            for (int i = 0, n = size; i < n; i++)
                if (items[i] == value)
                    return i;
        }
        else
        {
            for (int i = 0, n = size; i < n; i++)
                if (value.equals(items[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(T value , boolean identity)
    {
        T[] items = this.items;
        if (identity || value == null)
        {
            for (int i = size - 1; i >= 0; i--)
                if (items[i] == value)
                    return i;
        }
        else
        {
            for (int i = size - 1; i >= 0; i--)
                if (value.equals(items[i]))
                    return i;
        }
        return -1;
    }

    /********************************************************
     * 
     * 公共方法
     * 5.删除相关
     * 
     ********************************************************/
    public boolean removeValue(T value , boolean identity)
    {
        T[] items = this.items;
        if (identity || value == null)
        {
            for (int i = 0, n = size; i < n; i++)
            {
                if (items[i] == value)
                {
                    removeIndex(i);
                    return true;
                }
            }
        }
        else
        {
            for (int i = 0, n = size; i < n; i++)
            {
                if (value.equals(items[i]))
                {
                    removeIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除并返回指定索引下的对象
     * 
     * @param index
     *            索引值
     * @return
     */
    public T removeIndex(int index)
    {
        if (index >= size)
            throw new IndexOutOfBoundsException(String.valueOf(index));
        T[] items = this.items;
        T value = (T) items[index];
        size--;
        if (ordered)
            System.arraycopy(items, index + 1, items, index, size - index);
        else
            items[index] = items[size];
        items[size] = null;
        return value;
    }

    /**
     * 删除包含在array中的所有元素
     * 
     * @param array
     * @param identity
     *            True 使用 ==, false 使用 .equals().
     * @return 修改成功返回true 否则 false
     */
    public boolean removeAll(Array<T> array , boolean identity)
    {
        int size = this.size;
        int startSize = size;
        T[] items = this.items;
        if (identity)
        {
            for (int i = 0, n = array.size; i < n; i++)
            {
                T item = array.get(i);
                for (int ii = 0; ii < size; ii++)
                {
                    if (item.equals(items[ii]))
                    {
                        removeIndex(ii);
                        size--;
                        break;
                    }
                }
            }
        }
        else
        {
            for (int i = 0, n = array.size; i < n; i++)
            {
                T item = array.get(i);
                for (int ii = 0; ii < size; ii++)
                {
                    if (item == items[ii])
                    {
                        removeIndex(ii);
                        size--;
                        break;
                    }
                }
            }
        }
        return size != startSize;
    }

    /**
     * 删除并返回 最后一个元素
     * 
     * @return
     */
    public T pop()
    {
        --size;
        T item = items[size];
        items[size] = null;
        return item;
    }

    /** 返回最后一个元素 */
    public T peek()
    {
        return items[size - 1];
    }

    /** 返回第一个元素 */
    public T first()
    {
        return items[0];
    }

    public void clear()
    {
        T[] items = this.items;
        for (int i = 0, n = size; i < n; i++)
            items[i] = null;
        size = 0;
    }

    /** 重置array */
    public void shrink()
    {
        resize(size);
    }

    /**
     * 扩展array的容量
     * 
     * @param additionalCapacity
     * @return
     */
    public T[] ensureCapacity(int additionalCapacity)
    {
        int sizeNeeded = size + additionalCapacity;
        if (sizeNeeded >= items.length)
            resize(Math.max(8, sizeNeeded));
        return items;
    }

    /** 根据指定的长度以及当前的元素创建新的数组 */
    protected T[] resize(int newSize)
    {
        T[] items = this.items;
        T[] newItems = (T[]) java.lang.reflect.Array.newInstance(items.getClass().getComponentType(), newSize);
        System.arraycopy(items, 0, newItems, 0, Math.min(size, newItems.length));
        this.items = newItems;
        return newItems;
    }

    /**
     * 排序 数组元素必须实现Comparable，改方法是非线程安全的
     */
    public void sort()
    {
        Sort.instance().sort(items, 0, size);
    }

    /**
     * 排序 该方法是非线程安全的
     * 
     * @param comparator
     */
    public void sort(Comparator<T> comparator)
    {
        Sort.instance().sort(items, comparator, 0, size);
    }

    public void reverse()
    {
        for (int i = 0, lastIndex = size - 1, n = size / 2; i < n; i++)
        {
            int ii = lastIndex - i;
            T temp = items[i];
            items[i] = items[ii];
            items[ii] = temp;
        }
    }

    /**
     * 类似洗牌功能
     */
    public void shuffle()
    {
//        for (int i = size - 1; i >= 0; i--)
//        {
//            //int ii = RandomUtils.nextInt(0, i + 1);
//            T temp = items[i];
//            items[i] = items[ii];
//            items[ii] = temp;
//        }
    }

    /**
     * 获取 iterator
     */
    public Iterator<T> iterator()
    {
        if (iterator == null)
            iterator = new ArrayIterator(this);
        else
            iterator.index = 0;
        return iterator;
    }

    /**
     * 减少数组的长度
     * 
     * @param newSize
     */
    public void truncate(int newSize)
    {
        if (size <= newSize)
            return;
        for (int i = newSize; i < size; i++)
            items[i] = null;
        size = newSize;
    }

    /** 随机获取数组的元素 */
//    public T random()
//    {
//        if (size == 0)
//            return null;
//        return items[RandomUtils.nextInt(0, size)];
//    }

    public T[] toArray()
    {
        return (T[]) toArray(items.getClass().getComponentType());
    }

    public <V> V[] toArray(Class<V> type)
    {
        V[] result = (V[]) java.lang.reflect.Array.newInstance(type, size);
        System.arraycopy(items, 0, result, 0, size);
        return result;
    }

    public boolean equals(Object object)
    {
        if (object == this)
            return true;
        if (!(object instanceof Array))
            return false;
        Array array = (Array) object;
        int n = size;
        if (n != array.size)
            return false;
        Object[] items1 = this.items;
        Object[] items2 = array.items;
        for (int i = 0; i < n; i++)
        {
            Object o1 = items1[i];
            Object o2 = items2[i];
            if (!(o1 == null ? o2 == null : o1.equals(o2)))
                return false;
        }
        return true;
    }

    public String toString()
    {
        if (size == 0)
            return "[]";
        T[] items = this.items;
        StringBuilder buffer = new StringBuilder(32);
        buffer.append('[');
        buffer.append(items[0]);
        for (int i = 1; i < size; i++)
        {
            buffer.append(", ");
            buffer.append(items[i]);
        }
        buffer.append(']');
        return buffer.toString();
    }

    public String toString(String separator)
    {
        if (size == 0)
            return "";
        T[] items = this.items;
        StringBuilder buffer = new StringBuilder(32);
        buffer.append(items[0]);
        for (int i = 1; i < size; i++)
        {
            buffer.append(separator);
            buffer.append(items[i]);
        }
        return buffer.toString();
    }

    /********************************************************
     * 
     * 静态类
     * 
     ********************************************************/
    /**
     * Array迭代器
     * 
     * @author twl
     * 
     * @param <T>
     */
    static public class ArrayIterator<T> implements Iterator<T>
    {
        private final Array<T> array;

        int index;

        public ArrayIterator(Array<T> array)
        {
            this.array = array;
        }

        public boolean hasNext()
        {
            return index < array.size;
        }

        public T next()
        {
            if (index >= array.size)
                throw new NoSuchElementException(String.valueOf(index));
            return array.items[index++];
        }

        public void remove()
        {
            index--;
            array.removeIndex(index);
        }

        public void reset()
        {
            index = 0;
        }
    }

    /**
     * 实现此类的子类才能具有Array迭代器
     * 
     * @author twl
     * 
     * @param <T>
     */
    static public class ArrayIterable<T> implements Iterable<T>
    {
        private ArrayIterator<T> iterator;

        public ArrayIterable(Array<T> array)
        {
            iterator = new ArrayIterator<T>(array);
        }

        @Override
        public Iterator<T> iterator()
        {
            iterator.reset();
            return iterator;
        }
    }

    /********************************************************
     * 
     * 类变量
     * 
     ********************************************************/
    public T[] items;

    public int size;

    public boolean ordered;

    private ArrayIterator iterator;
}
