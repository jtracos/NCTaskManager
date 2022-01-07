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
        System.out.println(array[0]);
    }

    public void add(Task task){
        if(task==null) throw new IllegalArgumentException("null values are not allowed");
        if (currentSize==MAX_SIZE){
            MAX_SIZE = 2*MAX_SIZE;
            array = Arrays.copyOf(array,MAX_SIZE);
        }
        array[currentSize] = task;
        currentSize++;
    }

    public Task getTask(int index){
        if(index>currentSize || index < 0){
            throw new IndexOutOfBoundsException("Size out of bounds " + currentSize);
        }
        return array[index];
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTaskList)) return false;
        ArrayTaskList tasks = (ArrayTaskList) o;
        return currentSize == tasks.currentSize && Arrays.equals(array, tasks.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(currentSize);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

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
