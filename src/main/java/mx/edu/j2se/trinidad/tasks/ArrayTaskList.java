package mx.edu.j2se.trinidad.tasks;

/**
     * create an object at most with 100 tasks
     * 
     * 
     * _____________________________________________
     * ArrayTaskList tasks = new ArrayTaskList(); // It creates an object
     *                                            <br>//with at most 100 tasks
     * //adding tasks in the object
     * tasks.add(new Task("Go out for a burger", 5));<br>
     * .<br>
     * .<br>
     * <i>tasks.add(new Task("Catch spider-man without his disguise", 3, 24, 2));</i>
     *<br>
     * verifing number of taks recorded in the object<br>
     * <i>System.out.println("#tasks: " + tasks.size());</i>
     * 
     * 
     */
public class ArrayTaskList extends AbstractTaskList {
    private int MAX_SIZE;
    private Task[] array;

    /**
     * create a object containing at most with length tasks
     * @param length max size of the list
     * 
     * _____________________________________________
     * ArrayTaskList list = new ArrayTaskList(10); // It creates an object
     *                                             //with at most ten tasks
     * 
     */
    public ArrayTaskList(int length){
        MAX_SIZE = length;
        array = new Task[MAX_SIZE];
    }

    /**
     * create an object at most with 100 tasks
     * 
     * 
     * _____________________________________________
     * ArrayTaskList tasks = new ArrayTaskList(); // It creates an object
     *                                            // at most with 100 tasks
     * 
     */
    public ArrayTaskList(){
        this(100);
        System.out.println(array[0]);
    }

    /**
     * adds a Task object at the tasks list
     * @param task a Task object
     * 
     */
    public void add(Task task){
        if(task==null) throw new IllegalArgumentException("null values are not allowed");
        array[currentSize] = task;
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
        if(index>currentSize || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + currentSize);
        }
        return array[index];
    }
/**
 * removes a task from the current object if it is found
 * @param task instance of Task
 * @return true if the task is found and removed
 */
    public boolean remove(Task task){
        boolean done = false;
        for(int i = 0; i<currentSize && !done; i++){
            if(task.equals(array[i])){
                done = true;
                array[i] = array[currentSize-1];
                array[currentSize-1] = null;
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
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList tmpList = new ArrayTaskList();
        Task tmpTask;
        for (int i = 0; i<size(); i++) {
            tmpTask = array[i];
            if (tmpTask.isRepeated()) {// if repetitive
                //if time in [from, to]
                // add current task at task list
                if (tmpTask.nextTimeAfter(from - 1) != -1
                        && tmpTask.nextTimeAfter(to + 1) == -1)
                    try {
                        tmpList.add(tmpTask);
                    }catch (IllegalArgumentException ae){
                    }
            } else if (tmpTask.getTime() >= from && tmpTask.getTime() <= to) {
                try {
                    tmpList.add(tmpTask);
                }catch (IllegalArgumentException a){
                }
            }
        }
        return tmpList;
    }
}
