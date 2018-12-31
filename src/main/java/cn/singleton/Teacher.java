package cn.singleton;

/**
 * 懒汉式
 */
public class Teacher {
    private Teacher() {
    }

    private static Teacher t = null;

    public synchronized static Teacher getTeacher() {
        // t1,t2,t3
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (t == null) {//如果不加判断，会有线程安全问题
            //t1,t2,t3
            t = new Teacher();
        }
        return t;
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Teacher.getTeacher().hashCode());
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Teacher.getTeacher().hashCode());
                System.out.println(Teacher.getTeacher().hashCode());
            }
        });
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Teacher.getTeacher().hashCode());
            }
        });
        t3.start();


    }
}
