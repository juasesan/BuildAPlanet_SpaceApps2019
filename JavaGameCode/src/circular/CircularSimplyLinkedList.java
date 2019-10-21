/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circular;

import ec.edu.espol.list.List;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author emman
 */
public class CircularSimplyLinkedList<E> implements List<E>, Iterable<E>{
    
    private Node<E> first;
    private Node<E> last;
    private int efectivo;
    
    public CircularSimplyLinkedList(){
        
        first=last=null;
        efectivo=0;
    }

    @Override
    public boolean isEmpty() {
        return efectivo==0;
    }

    @Override
    public int size() {
        return efectivo;
    }

    @Override
    public boolean addFirst(E e) {
        
        if(e==null) return false;
        Node<E> node=new Node<>(e);
        
        if(isEmpty()){
            first=last=node;
            first.setNext(node);
            ++efectivo;
            return true;
        }
        
        last.setNext(node);
        node.setNext(first);
        first=node;
        ++efectivo;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        
        if(e==null) return false;
        
        Node<E> node = new Node<>(e);
        if(isEmpty()){
            first=node;
            last=node;
            first.setNext(node);
            ++efectivo;
            return true;
        }
        
        last.setNext(node);
        node.setNext(first);
        last=node;
        ++efectivo;
        return true;
    }

    @Override
    public boolean removeFirst() {
        
        if(isEmpty()) return false;
        else if(first==last){
            first.setData(null);
            first=last=null;
            --efectivo;
            return true;
        }
        
        Node<E> temp=first.getNext();
        first.setData(null);
        first.setNext(null);
        first=temp;
        last.setNext(temp);
        --efectivo;
        return true;
    }

    @Override
    public boolean removeLast() {
        
        if(isEmpty()) return false;
        else if(first==last){
            first.setData(null);
            first.setNext(null);
            first=last=null;
            --efectivo;
            return true;
        }
        
        Node<E> node= getPrevious(last);
        node.setNext(first);
        last.setData(null);
        last.setNext(null);
        last=node;
        --efectivo;
        return true;        
    }

    @Override
    public E remove(int ind) {
        
        if(isEmpty() || ind<0 || ind>=efectivo) return null;
        E element;
        if(ind==0){
            element = getFirst();
            removeFirst();
            return element;
        }
        else if(ind==efectivo-1){
            element = getLast();
            removeLast();
            return element;
        }
        
        Node<E> nodeDele=getNode(ind);
        element = nodeDele.getData();
        Node<E> nodeDelePrev=getNode(ind-1);
        nodeDelePrev.setNext(nodeDele.getNext());
        nodeDele.setNext(null);
        nodeDele.setData(null);
        --efectivo;
        return element;
    }

    @Override
    public boolean remove(E e) {
        
        if(e==null) return false;
        
        int ind=0;
        for(Node<E> p=first; p.getNext()!=null;p=p.getNext()){
            
            if(p.getData().equals(e)){
                
                if(ind==0){
                    removeFirst();
                    return true;
                }
                else if(ind==efectivo-1){
                    removeLast();
                    return true;
                }
                
                Node<E> nodePrev= getPrevious(p);
                nodePrev.setNext(p.getNext());
                p.setData(null);
                p.setNext(null);
                --efectivo;
                return true;
            }
            ind++;
        }
        return false;
    }

    @Override
    public E get(int index) {
        
        if(isEmpty()) return null;
        else if(index>=0 && index<efectivo){
            
            int i = 0;         
            Node<E> p=first;
            do{
                if(i==index) return p.getData();
                ++i;
                p=p.getNext();
            }while(p!=first);
        }
        return null;
    }
    
    private Node<E> getNode(int index) {
        
        if(isEmpty()) return null;
        else if(index>=0 && index<efectivo){
            
            int i = 0;         
            Node<E> p=first;
            do{
                if(i==index) return p;
                ++i;
                p=p.getNext();
            }while(p!=first);
        }
        return null;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return first.getData();
    }

    @Override
    public E getLast() {
        if(isEmpty()) return null;
        return last.getData();
    }
    
    private Node<E> getPrevious(Node<E> node){
        
        if(node== first) return last; 
                
        Node<E> p=first;
        do{
            if(p.getNext()==node) return p;    
            p=p.getNext();
        }while(p!=first);
  
        return null;
    }

    @Override
    public E set(int index, E e) {
        
        if(e == null) return null;
        
        else if(index>=0 && index<efectivo){
            Node<E> node = getNode(index);
            E element = node.getData();
            node.setData(e);
            return element;
        }
        return null;
    }

    @Override
    public boolean insert(int index, E e) {    
        
        if(isEmpty()) return false;
        else if(e != null && index>=0 && index<efectivo ){
            Node<E> nodeNew = new Node<>(e);
            if(index==0){
                nodeNew.setNext(first);
                last.setNext(nodeNew);
                ++efectivo;
                return true;
            }
            
            Node<E> nodeIndex = getNode(index);
            nodeNew.setNext(nodeIndex);
            getPrevious(nodeIndex).setNext(nodeNew);
            ++efectivo;
            return true;
        }
        return false; 
    }

    @Override
    public boolean contains(E e) {
        
        if(isEmpty() || e==null) return false;
                 
        Node<E> p=first;
        do{
            if(p.getData().equals(e)) return true;
            p=p.getNext();
        }while(p!=first);
        return false;
    }
    
    public void reverse(){
        
        if(!isEmpty()) reverse(first, last);
    }
    
    private void reverse(Node<E> p, Node<E> q){
        
        if(!(p.getNext().equals(q) || p==q)){
            
            E eLow = p.getData();
            p.setData(q.getData());
            q.setData(eLow);
            reverse(p.getNext(), getPrevious(q));
        }
    }
    

    @Override
    public boolean concatenate(Object collection) {
        
        if(collection == null) return false;
        CircularSimplyLinkedList<E> dl = (CircularSimplyLinkedList<E>) collection;
        last.setNext(dl.first);
        last= dl.last;
        last.setNext(first);
        efectivo+=dl.size();
        return true; 
    }
    
    @Override
    public boolean equals(Object o){
        
        if(o==null) return false;
        CircularSimplyLinkedList<E> dl = (CircularSimplyLinkedList<E>) o;
        if(size()!=dl.efectivo) return false;
        
        Node<E> q = dl.first;        
        Node<E> p=first;
        do{
            if(!p.getData().equals(q.getData())) return false;
            p=p.getNext();
            q=q.getNext();
        }while(p!=first);      
        return true;
    }
    
    public String toString(){
        
        if(efectivo==0) return "[]";
        else if(efectivo==1) return "["+first.getData()+"]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        Node<E> p=first;
        do{
            sb.append(p.getData()+",");
            p=p.getNext();  
        }while(p!=first);
        
        return sb.substring(0, sb.length()-1) + "]";
    }
    
    public CircularSimplyLinkedList<E> slicing(int start, int end) {
        
        return slicing(start, end, 0);
    }

    public CircularSimplyLinkedList<E> slicing(int start, int end, int step) {
        
        if(get(start)!=null && get(end)!=null){
            ++step;
            int stepA=0;
            CircularSimplyLinkedList<E> cl = new CircularSimplyLinkedList<>();
            for(int i=start;i<end;i++){
                if(i==stepA){
                    cl.addLast(get(i));
                    stepA+=step;
                }
            }
            return cl;
        }
        return null;
    }
    
    public ListIterator<E> listIterator(int index){
        
        ListIterator<E> lit = new ListIterator<E>(){
            
            private Node<E> p = getNode(index);
            
            @Override
            public boolean hasNext() {
                return p!=null;
            }

            @Override
            public E next() {
                E e = p.getData();
                p = p.getNext();
                return e;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E e = p.getData();
                p = getPrevious(p);
                return e;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {
                
            }

            @Override
            public void set(E e) {
                
            }

            @Override
            public void add(E e) {
                
            }
            
        };
        return lit;
    }

    @Override
    public Iterator<E> iterator(){
        
        Iterator<E> it = new Iterator<E>() {
            
            private Node<E> p = first;
            
            @Override
            public boolean hasNext() {
                return p != null;                
            }

            @Override
            public E next() {
                E e = p.getData();
                p = p.getNext();
                return e;
            }
        };
        return it;        
    }
    
    
    /**
     * Inner Class
     * @param <E> 
     */
    
    public class Node<E> {
    
    private E data;
    private Node<E> next;
    
    
    public Node(E data){
        
        this.data = data;
        this.next = null;
        }

    public E getData() {
        return data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    
    
    
    
}
    
}
