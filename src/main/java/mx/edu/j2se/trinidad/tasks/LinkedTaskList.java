package mx.edu.j2se.trinidad.tasks;

/**
 Check for the ArrayList documentation
 */
public class LinkedTaskList {
    private int currentSize;
    private Node node;

    /**
     * create an object at most with 100 tasks
     *
     *
     * _____________________________________________
     * ArrayTaskList tasks = new ArrayTaskList(); // It creates an object
     *                                            // at most with 100 tasks
     *
     */
    public LinkedTaskList(){
        node = null;
    }

    /**
     * adds a Task object at the tasks list
     * @param task a Task object
     *
     */
    public void add(Task task){
        Node tempNode = new Node(task, node );
        node = tempNode;
        currentSize++;
    }
    /**
     * the size method returns the current number of tasks in the ArrayTaksList
     * object
     * @return size number of taks in the current object
     */
    public int size(){
        return  currentSize;
    }
    /**
     * The getTask method returns the tasks allocated on the given index
     * @param index task index
     * @return an object of Task class
     */
    public Task getTask(int index){
        Task result = null;
        if(index>=currentSize || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + currentSize);
        }

        Node firstNode = node;
        for(int counter = 1; counter<index && firstNode!= null; counter++){

            firstNode = firstNode.nextNode;
        }
        if(firstNode!= null)
            result = firstNode.data;
        return result;
    }
    /**
     * removes a task from the current object if it is found
     * @param task instance of Task
     * @return true if the task is found and removed
     */
    public boolean remove(Task task){
        boolean done = false;
        Node firstNode = node;
        while(firstNode != null && !done){
            firstNode = firstNode.nextNode;
            if(task.equals(firstNode.data)){
                done = true;
                firstNode.data = node.data;
                node = node.nextNode;
                currentSize--;
            }

        }
        return done;
    }

    /**
     * this method returns a list with those tasks which are executed
     * between <i>to</i> and <i>from</i> time
     * @param from minimum time the task is executed
     * @param to maximum time the task is executed
     * @return an ArrayTaksList object
     */
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
    }
}
