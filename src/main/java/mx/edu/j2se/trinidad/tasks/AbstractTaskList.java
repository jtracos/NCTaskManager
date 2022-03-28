package mx.edu.j2se.trinidad.tasks;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * create an object at most with 100 tasks
 *
 *
 * _____________________________________________
 * AbstractTaskList tasks = new ArrayTaskList(); // It creates an object containing tasks
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
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {

    /** size*/
    protected int currentSize;

    private AbstractTaskList self = this;

    /**
     * adds a Task object at the tasks list
     * @param task a Task object
     *
     */
    public abstract void add(Task task);

    /**
     * the size method returns the current number of tasks in the TaksList
     * object
     * @return size number of task in the current object
     */
    public int size(){
        return  currentSize;
    }

    /**
     * The getTask method returns the tasks allocated on the given index
     * @param index task index
     * @return an object of Task class
     */
    public abstract Task getTask(int index);

    /**
     * removes a task from the current object if it is found
     * @param task instance of Task
     * @return true if the task is found and removed
     */
    public abstract boolean remove(Task task);

    /**
     * this method returns a list with those tasks which are executed
     * between <i>to</i> and <i>from</i> time
     * @param from minimum time the task is executed
     * @param to maximum time the task is executed
     * @return an ArrayTaskList object
     */
    public final Stream<Task> incoming(int from, int to){
        Stream<Task> st = this.getStream();
        return st.filter(task -> {
            boolean res;
            if(task.isRepeated()){
                res = task.nextTimeAfter(from - 1) >= from
                        && task.nextTimeAfter(to - task.getRepeatInterval()) <= to;
            }
            else res = task.getTime() >= from && task.getTime() <= to;
        return  res;
        }
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TaskList[");
        for(int i = 0; i< this.size(); i++){
            Task t = this.getTask(i);
            if(i+1<this.size()) {
                sb.append(t.toString() + ",\n");
            }else{
                sb.append(t.toString());
            }
        }
       sb.append("]");
        return sb.toString();
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public AbstractTaskList clone() {
        AbstractTaskList l;
        if(this instanceof LinkedTaskList) {
            l = new LinkedTaskList((LinkedTaskList) this);
        }
        else {
            l = new ArrayTaskList((ArrayTaskList) this);
        }
        return l;
    }

    public Stream<Task> getStream(){
        Iterable<Task> it = this.clone();
        return StreamSupport.stream(it.spliterator(), false);
    };

    @Override
    public Iterator<Task> iterator() {
        AbstractTaskList self;
        self = this;
        return new Iterator<Task>() {
            int size = self.size();
            private int idx = 0;

            @Override
            public boolean hasNext() {
                return idx < size;
            }

            @Override
            public void remove(){
                idx++;
            }
            @Override
            public Task next() {
                Task res = null;
                if (idx < size) {
                    res = self.getTask(idx);
                    idx++;
                }
                return res.clone();
            }
        };
    }

}
