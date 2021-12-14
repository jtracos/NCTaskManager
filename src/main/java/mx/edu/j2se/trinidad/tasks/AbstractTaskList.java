package mx.edu.j2se.trinidad.tasks;

import java.util.Iterator;

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
    public abstract int size();

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
     * @return an ArrayTaksList object
     */
    public abstract AbstractTaskList incoming(int from, int to);

    @Override
    public String toString() {
        return "AbstractTaskList{" +
                "currentSize=" + currentSize +
                '}';
    }

    @Override
    public Iterator<Task> iterator() {
                return new Iterator<Task>() {
                    private int idx = 0;

                    @Override
                    public boolean hasNext() {
                        return idx < self.size();
                    }

                    @Override
                    public Task next() {
                        Task res = null;
                        if (idx < self.size()) {
                            res = self.getTask(idx);
                            idx++;
                        }
                        return res;
            }
        };
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
