package mx.edu.j2se.trinidad.tasks;

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
     *  Task batmanTask = Task("Go to fight with people ",5);
     *  batmanTask.getTime()// 5
     */
    public Task(String title, int time){
        setTitle(title);
        setTime(time);

    }
    /**
     * This class allows set a repetitive task
     * @param title - Task title
     * @param start - Start time of the task
     * @param end - End time of the task
     * @param interval - interval of task repetition
     * _________________________________________________________________
     *   Task batmanTask = Task("Go to fight with people ",7, 23, 6);
     *   batmanTask.getStartTime()// 7
     * 
     *    batmanTask.nextTimeAfter(13)// 19
     */
    public Task(String title, int start, int end, int interval){
        setTitle(title);
        setTime(start, end, interval);
    }

    /**
     * The getTitle method returns the title the current task
     * @return task title
     *
     * Example:
     *     Task batmanTask = Task("Go to fight with people ",7, 23, 6);
     *     batmanTask.getTitle()// "Go to fight with people "
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
     * @return - Start time of the task
     */
    public int getTime() {
        return getStartTime();
    }

    /**
     * the setTime method reset the time of the current task.
     * If the task is a repetitive one it becomes a single-time task
     * @param time: execution time of the task
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
     * @return Interval of repetition of the task, if not repetitive returns 0 
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
        return isActive() ? next(0, current):0;
    }

    public boolean isRepeated(){
        return this.repetitive;
    }
    
    /**
     * private method which finds the time of the next event of the tasks
     * relative to a given time
     * @param idx - parameter used for the search
     * @param current - time before the next time
     * @return the next event time after current time, -1 if
     *      it is out of range 
     */
    private int next(int idx, int current){
        int res = -1;
        if (current < getStartTime()) res = getStartTime();
        else if (isRepeated()
                && current < getEndTime()) {
            int val = getStartTime() + idx * getRepeatInterval();

            if(val<=current){
                res = next(idx+1, current);
            }else if(val<=getEndTime()){
                    res = val;
            }
        }
        return res;
    }

    private void clearVars(){
        if(isRepeated()) {
            setTime(0,0,0);
            this.repetitive = false;
        }
    }
}
