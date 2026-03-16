package bai6;

import java.lang.management.*;

public class DeadlockDetector implements Runnable {

    @Override
    public void run() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        while (true) {
            long[] ids = bean.findDeadlockedThreads();
            if (ids != null) {
                System.out.println("DEADLOCK DETECTED!");
                ThreadInfo[] infos = bean.getThreadInfo(ids);
                for (ThreadInfo i : infos)
                    System.out.println(i.getThreadName());
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                return;
            }
        }
    }
}