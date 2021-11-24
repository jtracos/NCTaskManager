package mx.edu.j2se.trinidad.tasks;

import java.util.Objects;

public class Task {

    String title;
     /** start variable indicates the start time of a repetitive task
      *  or the event time
      */
    private int start;
    /** end variable indicates the end time of a repetitive task*/
    private int end;
    /** interval variable indicates the time interval of a repetitive task*/
    private int interval;
    /**active variable indicates whether the task is active*/
    private boolean active = false;
    /**repetitive variable indicates whether the task is repetitive*/
    private boolean repetitive;

    /**
     * This class allows create a non repetitive task
     * @param title - Task title
     * @param time -Event time
     *
     * _________________________________________________________________________
     *  Task batmanTask = Task("Go to fight with bad people ",5);
     *  batmanTask.getTime()// 5
     */
    public Task(String title, int time){
        if(time<0)
            throw new IllegalArgumentException("The time must be equals o greater than 0");
        setTitle(title);
        setTime(time);

    }

    /**
     * This class allows set a repetitive task
     * @param title - Task title
     * @param start - Start time of the task
     * @param end - End time of the task
     * @param interval - interval of task repetition
     * 
     * _________________________________________________________________
     *   Task batmanTask = Task("Go to fight with bad people ",7, 23, 6);
     *   batmanTask.getStartTime() // 7
     *   batmanTask.setActive(true) // set the task as active
     *   batmanTask.nextTimeAfter(13) // 19
     */
    
    public Task(String title, int start, int end, int interval){
        setTitle(title);
        setTime(start, end, interval);
    }

    /**
     * The getTitle method returns the title the current task
     * @return task title
     *__________________________________________________________________________
     * Task batmanTask = Task("Go to fight with people ",7, 23, 6);
     * batmanTask.getTitle()// "Go to fight with people "
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * The setTitle method reset the title of the current task
     * @param title - New task title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * The isActive method returns the task status
     * @return - true if the current task is active
     */
    public boolean isActive(){
        return this.active;
    }
    
    /**
     * The setActive method set the task status
     * @param active - true to activate the task
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * the getTime method returns either the event time of a non-repetitive task
     * or the start time of a repetitive one
     * @return start time of the task
     */
    public int getTime() {
        return getStartTime();
    }

    /**
     * the setTime method reset the time of the current task.
     * If the task is a repetitive one it becomes a single-time task
     * @param time - Execution time of the task
     */
    public void setTime(int time) {
        clearVars();
        this.start = time;
    }

    /**
     * the setTime method resets the time of the current task.
     * If the task is a single-time event, it becomes a repetitive one
     *
     * @param start - Start time of the task
     * @param end - End time of the task
     * @param interval - Interval of time between consequtives events
     */
    public void setTime(int start, int end, int interval){
        if(start<0 || end<0)
            throw new IllegalArgumentException("Entries must be positive");
        if(interval<=0)
            throw new IllegalArgumentException("invertal must be positive o 0");
        //TODO: Agregar excepciones para start>end
        if(!isRepeated())
            this.repetitive = true;
        this.start = start;
        this.end = end;
        this.interval = interval;

    }

    public int getStartTime(){
        return this.start;
    }
    /**@return - End time of the task */
    public int getEndTime(){
        int time = getStartTime();
        if(isRepeated())
            time = this.end;
        return time;
    }

    /**
     * The getRepeatInterval method returns the timelapse between
     * two consecutive task events
     * @return interval of repetition of the task, if not repetitive returns 0 
     */
    public int getRepeatInterval(){
        int inter= 0;
        if (isRepeated()) inter = this.interval;
        return inter;
    }

    /**
     * The nextTimeAfter method finds the next event time of the task
     * after a given time
     * @param current - Time before the next time
     * @return next event time of the task after current, -1 if it is
     * out of range
     * ______________________________________________________________
     *   Task batmanTask = Task("Go to fight with people ",7, 23, 6);
     *   batmanTask.getStartTime()// 7
     * 
     *   batmanTask.nextTimeAfter(13)// 19
     *   batmanTask.nextTimeAfter(22)// -1
     */
    public int nextTimeAfter(int current){
        return isActive() ? next(current):-1;
    }

    public boolean isRepeated(){
        return this.repetitive;
    }
    
    /**
     * private method which finds the time of the next event of the tasks
     * relative to a given time
     * @param current time before the next time
     * @return the next event time after current time, -1 if
     *      it is out of range 
     */
    private int next(int current){
        int res = -1;
        if (current < getStartTime()) res = getStartTime();
        else if (isRepeated()
                && current < getEndTime()) {
            int diff = current - getStartTime();//diferencia entre el tiempo actual y el inicio
            int range = getEndTime() - getStartTime(); //rango de tiempo en que ocurren las tareas
            int div = diff/getRepeatInterval(); //cociente entero
            int nextValInRange = getRepeatInterval()*(div + 1);//
            if(nextValInRange>diff && nextValInRange<range) res = getStartTime() + nextValInRange;
        }
        return res;
    }

    private void clearVars(){
        if(isRepeated()) {
            setTime(0,0,0);
            this.repetitive = false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return start == task.getStartTime() && end == task.getEndTime()
                && interval == task.getRepeatInterval() && title.equals(task.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, start, end, interval);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                '}';
    }
}
