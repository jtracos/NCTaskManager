package mx.edu.j2se.trinidad.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayTaskList extends AbstractTaskList {
    private int MAX_SIZE;
    private Task[] array;


    public ArrayTaskList(int length){
        MAX_SIZE = length;
        array = new Task[MAX_SIZE];
    }

    public ArrayTaskList(){
        this(100);
    }

    private ArrayTaskList(ArrayTaskList list){
        this(list.MAX_SIZE);
        for (Task task : list) this.add(task.clone());
    }

    public void add(Task task){
        if(task==null) throw new IllegalArgumentException("null values are not allowed");
        ensureCapacity();
        array[currentSize] = task;
        currentSize++;
    }
    private void ensureCapacity(){
        if (currentSize==MAX_SIZE){
            MAX_SIZE = 2*MAX_SIZE;
            array = Arrays.copyOf(array,MAX_SIZE);
        }
    }

    public Task getTask(int index){
        if(index>currentSize || index < 0){
            throw new IndexOutOfBoundsException("Index out of bounds " + currentSize);
        }
        return array[index];
    }

    public boolean remove(Task task){
        int index = getIndexOf(task);
        if (index > -1) {
            currentSize--;
            array[index] = array[currentSize];
            array[currentSize] = null;
            return true;
        }
        return false;
    }
    /*
    This method returns the index of the task if exists
    */
    public int getIndexOf(Task task){
        boolean done = false;
        int index = -1;
        for(int i = 0; i<currentSize && !done; i++) {
            if (task.equals(array[i])) {
                done = true;
                index = i;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TaskList[");
        for(int i = 0; i< this.size(); i++){
            Task t = this.getTask(i);
            if(i+1<this.size()) {
                sb.append(t.toString()).append(",\n");
            }else{
                sb.append(t.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList tasks = (ArrayTaskList) o;
        return currentSize == tasks.currentSize && Arrays.equals(array, tasks.array);
    }
    @Override
    public AbstractTaskList clone() {
        AbstractTaskList l;
        l = new ArrayTaskList(this);
        return l;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator(this);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(currentSize);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    class ArrayTaskListIterator  implements Iterator<Task> {


        Task[] tasks ;
        int size;
        int idx;

        ArrayTaskListIterator(ArrayTaskList list){
            tasks = list.array;
            size = list.size();
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public void remove(){
            if (hasNext()){
                idx++;
            }
        }

        @Override
        public Task next() {
            Task res = array[idx];
            idx++;
            return res;
        }
    };


}
