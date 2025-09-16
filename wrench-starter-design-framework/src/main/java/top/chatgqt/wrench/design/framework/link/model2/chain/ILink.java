package top.chatgqt.wrench.design.framework.link.model2.chain;

/**
 * 链接口
 */
public interface ILink<E> {

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(E e);

    E get(int index);
}
