package mx.edu.j2se.trinidad.tasks;

public class ArrayTaskList {
    private int size;
    private Node<Task> node;

    public ArrayTaskList(){
        node = null;
    }

    public void add(Task task){
        Node<Task> tempNode = new Node(task, node );
        node = tempNode;
        size++;
    }

    public int size(){
        return  size;
    }

    public Task getTask(int index){
        if(index>=size || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + size);
        }

        Node<Task> firstNode = node;
        for(int counter = 0; counter<index && firstNode!= null; counter++){

            firstNode = firstNode.nextNode;
        }
        return firstNode.data;
    }

    public boolean remove(Task task){
        boolean done = false;
        Node<Task> firstNode = node;
        while(firstNode != null && !done){
            firstNode = firstNode.nextNode;
            if(task.equals(firstNode.data)){
                done = true;
                firstNode.data = node.data;
                node = node.nextNode;
                size--;
            }

        }
        return done;
    }




    private class Node<T>{
        private Node nextNode;
        T data;

        private Node(T data){
            this(data, null);
        }

        private Node(T data, Node node){
            this.nextNode = node;
            this.data = data;
        }

    }
}
