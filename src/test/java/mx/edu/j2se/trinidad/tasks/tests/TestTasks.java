package mx.edu.j2se.trinidad.tasks.tests;

import mx.edu.j2se.trinidad.tasks.Task;
import org.junit.Assert;
import org.junit.Test;

public class TestTasks {
    public static void main(String[] args){
        Task nts = new Task("Go to play football",5);
        Task cndts;
        try {
            cndts = (Task) nts.clone();
            System.out.println(cndts);
            System.out.println("nts = " + nts);
            System.out.println("cndts = nts :" + nts.equals(cndts));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestsNonRepetitiveTask(){
        Task noRepTask = new Task("Go to jail", 7);
        // Check status
        Assert.assertFalse(noRepTask.isActive());
        noRepTask.setActive(true);
        //Check status again
        Assert.assertTrue(noRepTask.isActive());
        //Check nextTimeAfter method
        Assert.assertEquals(noRepTask.nextTimeAfter(4), 7);
        // Check getting the title
        Assert.assertEquals(noRepTask.getTitle(),"Go to jail");

        // Testing change to repetitive task
        noRepTask.setTime(6, 13,2);
        Assert.assertTrue(noRepTask.isRepeated());
        //Another check
        Assert.assertEquals(noRepTask.nextTimeAfter(12),-1);
    }

    @Test
    public void TestsRepetitiveTask(){
        Task RepTask = new Task("Go to sleep", 7,24, 5);
        //check start and end time
        Assert.assertEquals(RepTask.getStartTime(), 7);
        Assert.assertEquals(RepTask.getEndTime(), 24);
        RepTask.setActive(true);

        //Testing the nextTimeAfter method
        Assert.assertEquals(RepTask.nextTimeAfter(6), 7);
        Assert.assertEquals(RepTask.nextTimeAfter(21), 22);
        Assert.assertEquals(RepTask.nextTimeAfter(7), 12);
        Assert.assertEquals(RepTask.nextTimeAfter(11), 12);
        Assert.assertEquals(RepTask.nextTimeAfter(14), 17);
        Assert.assertEquals(RepTask.nextTimeAfter(25), -1);
        Assert.assertEquals(RepTask.nextTimeAfter(24), -1);
        Assert.assertTrue(RepTask.isRepeated());
        //change to no repetitive task
        RepTask.setTime(5);
        //
        Assert.assertFalse(RepTask.isRepeated());
        Assert.assertEquals(RepTask.getTime(),5);
        Assert.assertFalse(RepTask.isRepeated());
    }

}
