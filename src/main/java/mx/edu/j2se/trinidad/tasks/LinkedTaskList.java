package mx.edu.j2se.trinidad.tasks;

import java.util.Iterator;
import java.util.Objects;

public class LinkedTaskList extends AbstractTaskList{
    Node firstNode = null;
    private  int length;

    public LinkedTaskList(){
        length = 0;
    }

    public LinkedTaskList(LinkedTaskList list){
        Node tmpNode = list.firstNode;
        int i = 0;
        while (i < list.size() && tmpNode != null) {
            add(tmpNode.getData().clone());
            tmpNode = tmpNode.getNextNode();
        }
    }

    public void add(Task task){
        Node newNode = new Node(task);
        newNode.setNextNode(firstNode);
        firstNode = newNode;
        currentSize++;
    }

    public Task getTask(int index){
        if(index>=currentSize || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + currentSize);
        }

        Task result;
        Node currentNode = firstNode;
        int i = 0;
        while(i < index){
           currentNode = currentNode.getNextNode();
           i++;
        }
        result = currentNode.getData();
        return result;
    }

    public boolean remove(Task task){
        boolean done = false;
        Node currentNode = firstNode;
        while(currentNode.getNextNode() != null && !done){
            if(task.equals(currentNode.getData())) {
                done = true;
                firstNode = currentNode.getNextNode();
            } else if(task.equals(currentNode.getNextNode().getData())){
                Node next = currentNode.getNextNode();
                currentNode.setNextNode(next.getNextNode());
                currentSize--;
            }
        }
        return done;
    }

    @Override
    public String toString() {
        Task t;
        Node tmpNode = firstNode;

        StringBuilder sb = new StringBuilder();
        sb.append("List[");

        while (tmpNode != null){
            t = tmpNode.getData();
            sb.append(t.toString());
            if (tmpNode.getNextNode() != null) {
                sb.append(",\n");
            }
            tmpNode = tmpNode.getNextNode();
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedTaskList)) return false;
        LinkedTaskList tasks = (LinkedTaskList) o;
        return Objects.equals(firstNode, tasks.firstNode);
    }

    @Override
    public AbstractTaskList clone() {
        AbstractTaskList l;
        l = new LinkedTaskList(this);
        return l;
    }

    @Override
    public Iterator<Task> iterator() {
        return new LinkedTaskListIterator(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNode);
    }

    class LinkedTaskListIterator  implements Iterator<Task> {


        Node currentNode ;
        int size;

        LinkedTaskListIterator(LinkedTaskList list){
            currentNode = list.firstNode;
            size = list.size();
        }

        @Override
        public boolean hasNext() {
            return currentNode.getNextNode() != null;
        }

        @Override
        public void remove(){
            if (hasNext()){
                //idx++;
                currentNode = currentNode.getNextNode();
            }
        }

        @Override
        public Task next() {
            Task res = currentNode.getData();
            currentNode = currentNode.getNextNode();
            //idx++;
            return res;
        }
    };

    private class Node implements Cloneable {
        private Node next;
        private Task data;
        private Node previous;

        public Node(Task data) {
            this(data, null);
        }

        public Node(Task data, Node node) {
            this.next = node;
            this.data = data;
            //node.setPreviousNode(this);
        }
        private Node( Node node) {
            if ( node != null){
                setData(node.getData().clone());
                Node tmpNode = new Node(node.getNextNode().clone());
                setNextNode(tmpNode);
            }

        }

        @Override
        public Node clone() {

            return new Node(this);
        }

        public void setNextNode(Node nextNode) {
            this.next = nextNode;
        }

        public void setPreviousNode(Node previousNode) {
            this.previous = previousNode;
        }

        public Node getNextNode() {
            return next;
        }

        public Node getPreviousNode() {
            return previous;
        }

        public void setData(Task data) {
            this.data = data;
        }
        public Task getData(){
            return data;
        }
    }
}
