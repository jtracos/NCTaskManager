package mx.edu.j2se.trinidad.tasks;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedTaskList extends AbstractTaskList{
    Node node;


    public LinkedTaskList(){
        node = null;
    }

    public void add(Task task){
        Node tempNode = new Node();
        tempNode.nextNode= node;
        tempNode.data = task;
        node = tempNode;
        currentSize++;
    }

    public Task getTask(int index){
        if(index>=currentSize || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + currentSize);
        }
        Task result = null;
        Node firstNode = node;
        for(int counter = 0; counter<index && firstNode!= null; counter++){

            firstNode = firstNode.nextNode;
        }
        if(firstNode!= null)
            result = firstNode.data;
        return result;
    }

    public boolean remove(Task task){
        boolean done = false;
        Node firstNode;
        firstNode = node;
        while(firstNode != null && !done){
            if(task.equals(firstNode.data)){
                done = true;
                Node nd = node;
                firstNode.data = nd.data;
                node = nd.nextNode;
                currentSize--;
            }
            firstNode = firstNode.nextNode;
        }
        return done;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        return Objects.equals(node, tasks.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }

    @Override
    public Iterator<Task> iterator() {
        //try {
        AbstractTaskList self;
        self = (AbstractTaskList) this;
        return new Iterator<Task>() {
            int size = self.size();
            Node currentNode = node.clone();
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < size;
            }

            @Override
            public Task next() {
                Task res = null;
                if(idx==0){
                   res = currentNode.data;
                   idx++;
                }
                else if (idx < size) {
                    currentNode = currentNode.nextNode;
                    res = currentNode.data;
                    idx++;
                }
                return res;
            }
        };
    }

    private class Node implements Cloneable {
        private Node nextNode;
        Task data;

        private Node(Task data) {
            this(data, null);
        }

        private Node(Task data, Node node) {
            this.nextNode = node;
            this.data = data;
        }
        private Node() {
            this(null,null);
        }

        public Node clone() {
            try {
                return (Node) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace(System.err);
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(nextNode, node.nextNode) && Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nextNode, data);
        }


    }
}
