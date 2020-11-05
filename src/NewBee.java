public class NewBee {
    static final int size = 10000000;
    static final int h = size / 2;

    public void runSingleThread() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long begin = System.currentTimeMillis();
        calculate(arr);
        long end = System.currentTimeMillis();
        long output = end - begin;
        System.out.println("One thread method begins with: " + begin);
        System.out.println("One thread method ends with: " + end);
        System.out.println("One thread method finished with: " + output);

    }

    public float[] calculate(float[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = (float) (array[i] * Math.sin(0.2f + array[i] / 5) * Math.cos(0.2f + array[i] / 5) * Math.cos(0.4f + array[i] / 2));
        return array;
    }

    public void runDoubleThread(){
        float[] array = new float[size];
        float[] array1 = new float[h];
        float[] array2 = new float[h];
        for (int i = 0; i < array.length; i++) array[i] = 1;
        long begin = System.currentTimeMillis();
        System.arraycopy(array, 0, array1, 0, h);
        System.arraycopy(array, h, array2, 0, h);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] a1 = calculate(array1);
                System.arraycopy(a1,0,array1,0,a1.length);
            }
        });
        thread1.start();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                float[] a2 = calculate(array2);
                System.arraycopy(a2,0,array2,0,a2.length);
            }
        });
        thread2.start();
        System.arraycopy(array1, 0, array, 0, h);
        System.arraycopy(array2, 0, array, h, h);
        long end = System.currentTimeMillis();
        long output = end - begin;
        System.out.println("One thread method begins with: " + begin);
        System.out.println("One thread method ends with: " + end);
        System.out.println("One thread method finished with: " + output);

    }


}




