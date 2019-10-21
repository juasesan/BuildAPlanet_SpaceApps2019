/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.list;

/**
 *
 * @author CltControl
 * @param <E>
 */
public interface List<E> {
    
    boolean isEmpty();//
    int size();//
    boolean addFirst(E e);//
    boolean addLast(E e);//
    boolean removeFirst();//
    boolean removeLast();//
    E remove(int ind);//
    boolean remove(E e);//
    E get(int index);
    E getFirst();//
    E getLast();//
    E set(int index, E e);//
    boolean insert(int index, E e);//
    boolean contains(E e);//
    boolean concatenate(Object collection);
    

}
