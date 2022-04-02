package mx.edu.j2se.trinidad.tasks;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedTaskList extends AbstractTaskList{
    Node head = null;
    Node tail = null;

    public LinkedTaskList(){
        tail = null;
    }

    public LinkedTaskList(LinkedTaskList list){
        this.tail = list.tail.clone();
        this.currentSize = list.size();
    }

    public void add(Task task){
        if(head == null) {
            Node tempNode = new Node();
            Node tmpNode = new Node(task, tail);
            head = tempNode;
            tail = tempNode;
            tail.prev = null;
            tail.next = null;
            currentSize++;
        }else{
            Node tmpNode = new Node(task);
            tmpNode.prev = tail;
            tail.next = tmpNode;
            tail = tmpNode;
            tmpNode.next = null;
        }
    }

    public Task getTask(int index){
        if(index>=currentSize || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + currentSize);
        }
        Task result = null;
        Node firstNode = tail;
        for(int counter = 0; counter<index && firstNode!= null; counter++){

            firstNode = firstNode.getNextNode();
        }
        if(firstNode!= null)
            result = firstNode.data;
        return result;
    }

    public boolean remove(Task task){
        boolean done = false;
        Node firstNode;
        firstNode = tail;
        while(firstNode != null && !done){
            if(task.equals(firstNode.data)){
                done = true;
                Node nd = tail;
                firstNode.data = nd.data;
                tail = nd.getNextNode();
                currentSize--;
            }
            firstNode = firstNode.getNextNode();
        }
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        return Objects.equals(tail, tasks.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tail);
    }

    private class Node implements Cloneable {
        private Node next, prev;
        private Task data;

        public Node(Task data) {
            this(data, null);
        }

        public Node(Task data, Node node) {
            this.next = node;
            this.data = data;
        }
        private Node() {
            this(null,null);
        }

        public Node clone() {
            Node n;
            if(this.next != null) {
                Node newNode = getNextNode().clone();
                n = new Node(getData().clone(), newNode);
            } else{
                n = new Node(getData().clone(), null);
            }
            return n;
        }

        public void setNextNode(Node nextNode) {
            this.next = nextNode;
        }

        public Node getNextNode() {
            return next;
        }

        public void setData(Task data) {
            this.data = data;
        }
        public Task getData(){
            return data;
        }

    }
}
