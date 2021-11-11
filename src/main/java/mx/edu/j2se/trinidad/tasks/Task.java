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
    /**a counter*/
    private int counter = 1;

    public Task(String title, int time){
        setTitle(title);
        setTime(time);

    }
    public Task(String title, int start, int end, int interval){
        setTitle(title);
        setTime(start, end, interval);
    }

    /**
     * The getTitle method returns the title the current task
     * @return title:String
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

    public boolean isActive(){
        return this.active;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public int getTime() {
        int temp = -1;
        if(isActive()) {// Checks whether is active
            if (!this.isRepeated()) temp = this.start; // if a non-repetitive task
            else temp = getStartTime();

        }
        return temp;
    }

    public void setTime(int time) {
        clearVars();
        this.start = time;
    }

    public void setTime(int start, int end, int interval){
        if(!isRepeated())
            this.repetitive = true;
        this.start = start;
        this.end = end;
        this.interval = interval;

    }

    public int getStartTime(){
        int tmp = -1;
        if(isActive()){
             tmp = this.start;
        }
        return tmp;
    }

    public int getEndTime(){
        int time = -1;
        if(isActive())
            time = this.end;
        return time;
    }

    public int getRepeatInterval(){
        int inter = -1;
        if(isActive()) {
            if (isRepeated())
                inter = this.interval;
            else inter = 0;
        }
        return inter;
    }

    public int nextTimeAfter(int current){
        return next(0, current);
    }


    public boolean isRepeated(){
        return this.repetitive;
    }

    private int next(int idx, int threshold){

        int val = -1;
        if(isActive()) {
            if (isRepeated()) {
                val = getStartTime() + idx * getRepeatInterval();
                if (threshold < getEndTime() && val <= threshold) {
                    val = next(idx + 1, threshold);
                }
            } else {
                if (threshold < getStartTime()) val = this.start;
            }
        }
        return val;
    }

    private void clearVars(){
        if(isRepeated()) {
            this.start = 0;
            this.interval = 0;
            this.end = 0;
            this.repetitive = false;
        }
    }
}
