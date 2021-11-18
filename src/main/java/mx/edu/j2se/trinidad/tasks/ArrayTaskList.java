package mx.edu.j2se.trinidad.tasks;

public class ArrayTaskList {
    private int currentSize;
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
        array[currentSize] = task;
        currentSize++;
    }

    public int size(){
        return  currentSize;
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

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList tmpList = new ArrayTaskList();
        for (int i = 0; i<size(); i++) {
            Task tmpTask = array[i];
            if (tmpTask.isRepeated()) {// if repetitive
                //if time in [from, to]
                // add current task at task list
                if (tmpTask.nextTimeAfter(from - 1) != -1
                        && tmpTask.nextTimeAfter(to + 1) == -1)
                    tmpList.add(tmpTask);
            } else if (tmpTask.getTime() >= from && tmpTask.getTime() <= to) {
                tmpList.add(tmpTask);
            }
        }
        return tmpList;
    }
}
