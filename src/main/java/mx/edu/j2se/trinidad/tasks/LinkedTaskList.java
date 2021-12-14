package mx.edu.j2se.trinidad.tasks;

import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList{
    private Node node;


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

    public int size(){
        return  currentSize;
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

    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList tmpList = new LinkedTaskList();
        Node nextNode = node;
        Task tmpTask;
        for (int i = 0; i<size() && nextNode != null; i++) {
            tmpTask = nextNode.data;
            if (tmpTask.isRepeated()) {// if repetitive
                //if time in [from, to]
                // add current task at task list
                if (tmpTask.nextTimeAfter(from - 1) != -1
                        && tmpTask.nextTimeAfter(to + 1) == -1)
                    tmpList.add(tmpTask);
            } else if (tmpTask.getTime() >= from && tmpTask.getTime() <= to) {
                tmpList.add(tmpTask);
            }
            nextNode = nextNode.nextNode;
        }
        return tmpList;
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

    private class Node {
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
