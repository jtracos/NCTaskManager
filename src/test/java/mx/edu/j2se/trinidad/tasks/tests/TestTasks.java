package mx.edu.j2se.trinidad.tasks.tests;

import org.junit.Assert;
import org.junit.Test;
import mx.edu.j2se.trinidad.tasks.Task;
public class TestTasks {
    @Test
    public void TestsNonRepetitiveTask(){
        Task task = new Task("Go to jail", 7);
        task.setActive(true);
        Assert.assertEquals(task.nextTimeAfter(4), 7);
    }

    @Test
    public void TestsRepetitiveTask(){
        Task nonRepTask = new Task("Go to sleep", 7,24, 5);
        Assert.assertEquals(nonRepTask.getStartTime(), -1);
        Assert.assertEquals(nonRepTask.getEndTime(), -1);
        nonRepTask.setActive(true);
        Assert.assertEquals(nonRepTask.getStartTime(), 7);
        Assert.assertEquals(nonRepTask.getEndTime(), 24);
        Assert.assertEquals(nonRepTask.nextTimeAfter(6), 7);
        Assert.assertEquals(nonRepTask.nextTimeAfter(7), 12);
        Assert.assertEquals(nonRepTask.nextTimeAfter(11), 12);
        Assert.assertEquals(nonRepTask.nextTimeAfter(14), 17);
        Assert.assertTrue(nonRepTask.isRepeated());
        nonRepTask.setTime(5);
        Assert.assertFalse(nonRepTask.isRepeated());
    }

}
