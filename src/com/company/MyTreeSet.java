package com.company;

import java.util.*;

/**
 * Created by Женя on 06.06.2016.
 */
public class MyTreeSet<T extends Comparable<T>> implements Set<T> {
    private Node root;
    private int size;

    class Node<T>{
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
        private T value;

        public Node(T value){
            this.value = value;
        }
    }


    public boolean add(T o) {
        if (root == null){
            root = new Node(o);
        }
        Node<T> current = root;
        while(current != null){
            int cmp = o.compareTo(current.value);
            if (cmp == 0){
                return false;
            } else if (cmp < 0){
              if (current.left != null){
                  current = current.left;
              }else{
                  current.left = new Node<>(o);
                  current.left.parent = current;
                  return true;
              }
            }else{
                if (current.right != null){
                    current = current.right;
                }else{
                    current.right = new Node<>(o);
                    current.right.parent = current;
                    return true;
                }
            }
        }
        return false;
    }

    private int size(Node root){
        if (root == null){
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }

    public boolean remove(T o){
        Node<T> current = root;
        while (current != null){
            int cmp = o.compareTo(current.value);
            if (cmp < 0){
                current = current.left;
            }else if (cmp > 0){
                current = current.right;
            }else{
                if (current.left == null && current.right == null){
                    if(current.parent.left == current){
                        current.parent.left = null;
                    }else{
                        current.parent.right = null;
                    }
                } else if (current.left != null) {
                    Node<T> maxLeft = current.left;
                    while (maxLeft.right != null){
                        maxLeft = maxLeft.right;
                    }
                    current.value = maxLeft.value;
                    if (maxLeft.parent.right == maxLeft){
                        if (maxLeft.left != null){
                            maxLeft.parent.right = maxLeft.left;
                        }else{
                            maxLeft.parent.right=null;
                        }
                    }else{
                        Node<T> maxRight = current.right;
                        while(maxRight.left != null){
                            maxRight = maxRight.left;
                        }
                        current.value = maxRight.value;
                        if (maxRight.parent.left == maxRight){
                            if (maxRight.right != null){
                                maxRight.parent.left = maxRight.right;
                            }else{
                                maxRight.parent.left = null;
                            }
                        }else{
                            maxRight.parent.right = maxRight.right;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(T o) {
        Node<T> current = root;
        while(current != null){
            int cmp = o.compareTo(current.value);
            if (cmp == 0){
                return true;
            }
            if (cmp < 0){
                current = current.left;
            }
            if (cmp > 0){
                current = current.right;
            }
        }
        return false;
    }


    @Override
    public int size(){
        return size(root);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>() {
            Node<T> current = root;
            List<T> checked = new ArrayList<>();

            @Override
            public boolean hasNext() {
                return (current.left != null || current.right != null);
            }

            @Override
            public T next() {
                if (current.left != null){
                    current = current.left;
                    checked.add(current.value);
                    return current.value;
                }else if (current.right != null){
                    current = current.right;
                    checked.add(current.value);
                    return current.value;
                }
                while(checked.contains(current.value)) {
                    if (current == root && root.right == null) {
                        throw new ArrayIndexOutOfBoundsException("No elements else!");
                    }
                    if (current.parent.right != null && current.parent.right != current) { // current leaf is left
                        current = current.parent.right;
                    } else {
                        current = current.parent;
                    }
                }
                checked.add(current.value);
                return current.value;
            }

            @Override
            public boolean hasPrevious() {
                return current.parent != null;
            }

            @Override
            public T previous() {
                return current.parent.value;
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
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
