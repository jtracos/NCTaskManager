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
     * This class allows set a non repetitiva task
     * @param title:string
     *             Task title
     * @param time: int
     *            Event time
     *
     * Example:
     *            Task batmanTask = Task("Go to fight with people ",5);
     *            batmanTask.getTime()// 5
     */
    public Task(String title, int time){
        setTitle(title);
        setTime(time);

    }
    /**
     * This class allows set a repetitive task
     * @param title:string
     *             Task title
     * @param start: int
     *            Start time of the task
     * @param end: int
     *            End time of the task
     * @param interval: int
     *            interval of task repetition
     *
     * Example:
     *      Task batmanTask = Task("Go to fight with people ",7, 23, 6);
     *      batmanTask.getStartTime()// 7
     *      batmanTask.nextTimeAfter(13)// 19
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
     *      Task batmanTask = Task("Go to fight with people ",7, 23, 6);
     *      batmanTask.getTitle()// "Go to fight with people "
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * The setTitle method reset the title of the current task
     * @param title:String
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * The isActive method returns the task status
     * @return Task status
     */
    public boolean isActive(){
        return this.active;
    }
    /**
     * The setActive method set the task status
     * @param active :boolean
     */
    public void setActive(boolean active){
        this.active = active;
    }

    /**
     * the getTime method returns the event time of a non-repetitive task
     * or the start time of a repetitive one
     * @return int
     */
    public int getTime() {
        return getStartTime();
    }

    /**
     * the setTime method reset the time of the current task.
     * If the task is a repetitive one it becomes a single-time task
     * @param time: int
     */
    public void setTime(int time) {
        clearVars();
        this.start = time;
    }

    /**
     * the setTime method resets the time of the current task.
     * If the task is a single-time event, it becomes a repetitive one
     *
     * @param start:int
     *             Start time
     * @param end:int
     *           End time
     * @param interval: int
     *                Interval time
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

    public int getEndTime(){
        int time = getStartTime();
        if(isRepeated())
            time = this.end;
        return time;
    }

    /**
     * The getRepeatInterval method returns the timelapse between
     * two consecutive task events
     * @return int
     */
    public int getRepeatInterval(){
        int inter= 0;
        if (isRepeated()) inter = this.interval;
        return inter;
    }

    /**
     * The nextTimeAfter method returns the next event time of the task
     * after a given time
     * @param current:int
     *               Time before the next time
     * @return next task time after current
     */
    public int nextTimeAfter(int current){
        return isActive() ? next(0, current):0;
    }

    public boolean isRepeated(){
        return this.repetitive;
    }

    private int next(int idx, int threshold){
        int res = -1;
        if (threshold < getStartTime()) res = getStartTime();
        else if (isRepeated()
                && threshold < getEndTime()) {
            int val = getStartTime() + idx * getRepeatInterval();

            if(val<=threshold){
                res = next(idx+1, threshold);
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
