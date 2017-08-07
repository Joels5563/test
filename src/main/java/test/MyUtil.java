package test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 工具类,测试克隆
 * 文件拷贝
 *
 * @author joels
 * @create 2017-07-03 16:03
 **/
public class MyUtil {

    // 工具类中的方法都是静态方式访问的因此将构造器私有不允许创建对象(绝对好习惯)
    // 抛出异常,针对反射通过构造器创建对象
    private MyUtil() {
        throw new AssertionError();
    }

    /**
     * 通过序列化克隆对象
     *
     * @param obj 源对象
     * @param <T> 对象泛型
     * @return 克隆对象
     * @throws Exception IO异常
     */
    public static <T> T clone(T obj) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();

        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    /**
     * 文件拷贝通过文件流
     *
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException IO异常
     */
    public static void fileCopy(String source, String target) throws IOException {
        try (InputStream in = new FileInputStream(source)) {
            try (OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while ((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }

    /**
     * 文件拷贝通过管道NIO
     *
     * @param source 源文件
     * @param target 目标文件
     * @throws IOException IO异常
     */
    public static void fileCopyNIO(String source, String target) throws IOException {
        try (FileInputStream in = new FileInputStream(source)) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while (inChannel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }

    /**
     * 统计给定文件中给定字符串的出现次数
     *
     * @param filename 文件名
     * @param word     字符串
     * @return 字符串在文件中出现的次数
     */
    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try (FileReader fr = new FileReader(filename)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String line;
                while ((line = br.readLine()) != null) {
                    int index;
                    while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return counter;
    }

    /**
     * 获取当前目录下的文件
     *
     * @param path 目录的路径
     * @return 文件名路径列表
     */
    public static List<String> listFilesCurrentDir(String path) {
        List<String> fileNameList = new ArrayList<>();
        File file = new File(path);

        for (File temp : file.listFiles()) {
            if (temp.isFile()) {
                fileNameList.add(temp.getPath());
            }
        }
        return fileNameList;
    }

    /**
     * 获取目录下所有文件(包括子目录下的文件)
     *
     * @param path 目录路径
     * @return 文件路径列表
     */
    public static List<String> listFilesInDir(String path) {
        List<String> fileNameList = new ArrayList<>();
        File file = new File(path);
        if (file.isDirectory()) {
            for (File temp : file.listFiles()) {
                fileNameList.addAll(listFilesInDir(temp.getPath()));
            }
        } else {
            fileNameList.add(file.getPath());
        }

        return fileNameList;
    }

    /**
     * 获取目录下所有文件(包括子目录下的文件, 使用NIO)
     *
     * @param path 目录路径
     * @return 文件名列表
     */
    public static List<String> listFilesInDirNIO(String path) {
        List<String> fileNameList = new ArrayList<>();
        Path initPath = Paths.get(path);
        try {
            Files.walkFileTree(initPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    fileNameList.add(file.getFileName().toString());
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileNameList;
    }

    //计算中间位置时不应该使用(high+ low) / 2的方式
    //因为加法运算可能导致整数越界，这里应该使用以下三种方式之一：
    // low + (high – low) / 2或low + (high – low) >> 1或(low + high) >>> 1（>>>是逻辑右移，是不带符号位的右移）
    public static <T extends Comparable<T>> int binarySearch(T[] x, T key) {
        return binarySearch(x, 0, x.length - 1, key);
    }

    // 使用循环实现的二分查找
    public static <T> int binarySearch(T[] x, T key, Comparator<T> comp) {
        int low = 0;
        int high = x.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = comp.compare(x[mid], key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 使用递归实现的二分查找
    private static <T extends Comparable<T>> int binarySearch(T[] x, int low, int high, T key) {
        if (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (key.compareTo(x[mid]) == 0) {
                return mid;
            } else if (key.compareTo(x[mid]) < 0) {
                return binarySearch(x, low, mid - 1, key);
            } else {
                return binarySearch(x, mid + 1, high, key);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        try {
//            Person p1 = new Person("Hao LUO", 33, new Car("Benz", 300));
//            Person p2 = MyUtil.clone(p1);   // 深度克隆
//            p2.getCar().setBrand("BYD");
//            // 修改克隆的Person对象p2关联的汽车对象的品牌属性
//            // 原来的Person对象p1关联的汽车不会受到任何影响
//            // 因为在克隆Person对象时其关联的汽车对象也被克隆了
//            System.out.println(p1);
//            System.out.println(p2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String path = "D:\\doc";
        listFilesCurrentDir(path).forEach(System.out::println);
        listFilesInDir(path).forEach(System.out::println);
        listFilesInDirNIO(path).forEach(System.out::println);
    }
}

/**
 * 人类
 */
class Person implements Serializable {
    private static final long serialVersionUID = -9102017020286042305L;

    private String name;    // 姓名
    private int age;        // 年龄
    private Car car;        // 座驾

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
    }

}

/**
 * 小汽车类
 */
class Car implements Serializable {
    private static final long serialVersionUID = -5713945027627603702L;

    private String brand;       // 品牌
    private int maxSpeed;       // 最高时速

    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [brand=" + brand + ", maxSpeed=" + maxSpeed + "]";
    }
}
