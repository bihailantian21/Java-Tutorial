1.Set转换成List
做一个功能的时候 需要将已得到的map的键值放入一个List中做他用，我就很stupid的 逐个遍历、add到list中，真的很低级，简单回顾下其他简便方法吧。     
       
        //构造Map数据
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("ele1", "小樱");
    	map.put("ele2", "若曦");
    	map.put("ele3", "晴川");
    	Set<String> set = map.keySet();
    	
    	//Set转List,方法一 ：构造方法 ArrayList(Collection<?> c) 
    	List<String> list1 = new ArrayList<String>(set);
    	for(int i = 0; i < list1.size(); i++){
    		System.out.println("list1(" + i + ") --> " + list1.get(i));
    	}
    	
    	//Set转List,方法二：List实现类（ArrayList/LinkedList）的方法  -- addAll(Collection<?> c) 
    	List<String> list2 = new ArrayList<String> ();
    	list2.addAll(set);
    	for(String elem : list2){
    		System.out.println(elem);
    	}

2.JDK8新特性Map putIfAbsent、computeIfAbsent、computeIfPresent的深入使用
(1)putIfAbsent 方法
其实简单的说：传统的put方法，只要key存在，value值就会被覆盖,注意put方法返回的是put之前的值，如果无put之前的值返回null
putIfAbsent方法，只有在key不存在或者key为null的时候，value值才会被覆盖  
源码如下：

    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }
 
        return v;
    }
map.putIfAbsent("t1", "aa");的的等价代码如下：

    if(map.containsKey("t1")&&map.get("t1")!=null) {
        map.put("t1", "aa");
    }
使用场景：如果我们要变更某个key的值，我们又不知道key是否存在的情况下，而又不希望增加key的情况使用。 

(2)computeIfAbsent 方法
和putIfAbsent类似但是，在返回值上不一样，value值不存在的时候，返回的是新的value值，同时可以通过自定义一些条件进行过滤。

	List<People> list = Arrays.asList(
			new People("大仙",2),
			new People("朱半仙",15),
			new People("半仙",8),
			new People("大神",10),
			new People("超神",8),
			new People("大仙仙",2));
	public void testcomputeIfAbsent () {
   	 	//声明接收结果的 map
        Map<Integer, List<People>> resultMap = new HashMap<Integer, List<People>>();
        //对所有的人按年龄进行分组
        for (People people : list) {
        	//putIfAbsent方法，只有在key不存在或者key为null的时候，value值才会被覆盖  ,
            List<People> s = resultMap.putIfAbsent(people.getAge(), new ArrayList<People>());
            if(s==null) {//如果value值不存在，返回的是null,重新获取一遍
            	s = resultMap.putIfAbsent(people.getAge(), new ArrayList<People>());
            }
            s.add(people);
        }
        System.out.println(resultMap);
        resultMap = new HashMap<Integer, List<People>>();
        //对5岁以上的人进行分组
        for (People people : list) {
        	//如果value值不存在，返回的是新的value值
            List<People> s = resultMap.computeIfAbsent(people.getAge(), k ->{
            	if(k>5) {
            		return new ArrayList<TestMap.People>();
            	}
            	return null;
            });
            if(s!=null) {
            	s.add(people);
            }
        }
        System.out.println(resultMap);
	}
computeIfPresent 方法
和computeIfAbsent方法正好相反，只有当key存在的时候才执行操作，那么我们把上面的需求变更下，对5岁以上的人进行分组，并且对8岁的人进行年龄加1操作。

        //对5岁以上的人进行分组，并且设置所有的8岁的年龄增加1
        resultMap = new HashMap<Integer, List<People>>();
        for (People people : list) {
        	 List<People> s = resultMap.computeIfAbsent(people.getAge(), k ->{
             	if(k>5) {
             		return new ArrayList<TestMap.People>();
             	}
             	return null;
             });
             if(s!=null) {
             	s.add(people);
             }
		}
        //如果value值不存在，返回的是新的value值
        resultMap.computeIfPresent(8, (k,v)->{
    	    for(People people:v) {
    	    	people.setAge(people.getAge()+1);
    	    }
       		return v;
        });
        System.out.println(resultMap);


3.Collections
Collections是JDK提供的工具类，同样位于java.util包中。它提供了一系列静态方法，能更方便地操作各种集合。
  
  我们一般看方法名和参数就可以确认Collections提供的该方法的功能。例如，对于以下静态方法：
  
  public static boolean addAll(Collection<? super T> c, T... elements) { ... }
  addAll()方法可以给一个Collection类型的集合添加若干元素。因为方法签名是Collection，所以我们可以传入List，Set等各种集合类型。
  
  (1)创建空集合
  Collections提供了一系列方法来创建空集合：
  
  创建空List：List<T> emptyList()
  创建空Map：Map<K, V> emptyMap()
  创建空Set：Set<T> emptySet()
  要注意到返回的空集合是不可变集合，无法向其中添加或删除元素。
  
  此外，也可以用各个集合接口提供的of(T...)方法创建空集合。例如，以下创建空List的两个方法是等价的：
  
  List<String> list1 = List.of();
  List<String> list2 = Collections.emptyList();
  (2)创建单元素集合
  Collections提供了一系列方法来创建一个单元素集合：
  
  创建一个元素的List：List<T> singletonList(T o)
  创建一个元素的Map：Map<K, V> singletonMap(K key, V value)
  创建一个元素的Set：Set<T> singleton(T o)
  要注意到返回的单元素集合也是不可变集合，无法向其中添加或删除元素。
  
  此外，也可以用各个集合接口提供的of(T...)方法创建单元素集合。例如，以下创建单元素List的两个方法是等价的：
  
  List<String> list1 = List.of("apple");
  List<String> list2 = Collections.singletonList("apple");
  实际上，使用List.of(T...)更方便，因为它既可以创建空集合，也可以创建单元素集合，还可以创建任意个元素的集合：
  
  List<String> list1 = List.of(); // empty list
  List<String> list2 = List.of("apple"); // 1 element
  List<String> list3 = List.of("apple", "pear"); // 2 elements
  List<String> list4 = List.of("apple", "pear", "orange"); // 3 elements
  (3)排序
  Collections可以对List进行排序。因为排序会直接修改List元素的位置，因此必须传入可变List：
  
  import java.util.*;
  public class Main {
      public static void main(String[] args) {
          List<String> list = new ArrayList<>();
          list.add("apple");
          list.add("pear");
          list.add("orange");
          // 排序前:
          System.out.println(list);
          Collections.sort(list);
          // 排序后:
          System.out.println(list);
      }
  }
  输出：[apple, pear, orange]
     [apple, orange, pear]
  (4)洗牌
  Collections提供了洗牌算法，即传入一个有序的List，可以随机打乱List内部元素的顺序，效果相当于让计算机洗牌：
  
  import java.util.*;
  public class Main {
      public static void main(String[] args) {
          List<Integer> list = new ArrayList<>();
          for (int i=0; i<10; i++) {
              list.add(i);
          }
          // 洗牌前:
          System.out.println(list);
          Collections.shuffle(list);
          // 洗牌后:
          System.out.println(list);
      }
  }
  
   Run
  （5）不可变集合
  Collections还提供了一组方法把可变集合封装成不可变集合：
  
  封装成不可变List：List<T> unmodifiableList(List<? extends T> list)
  封装成不可变Set：Set<T> unmodifiableSet(Set<? extends T> set)
  封装成不可变Map：Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> m)
  这种封装实际上是通过创建一个代理对象，拦截掉所有修改方法实现的。我们来看看效果：
  
  import java.util.*;
  public class Main {
      public static void main(String[] args) {
          List<String> mutable = new ArrayList<>();
          mutable.add("apple");
          mutable.add("pear");
          // 变为不可变集合:
          List<String> immutable = Collections.unmodifiableList(mutable);
          immutable.add("orange"); // UnsupportedOperationException!
      }
  }
  
   Run
  然而，继续对原始的可变List进行增删是可以的，并且，会直接影响到封装后的“不可变”List：
  
  import java.util.*;
  public class Main {
      public static void main(String[] args) {
          List<String> mutable = new ArrayList<>();
          mutable.add("apple");
          mutable.add("pear");
          // 变为不可变集合:
          List<String> immutable = Collections.unmodifiableList(mutable);
          mutable.add("orange");
          System.out.println(immutable);
      }
  }
  
   Run
  因此，如果我们希望把一个可变List封装成不可变List，那么，返回不可变List后，最好立刻扔掉可变List的引用，这样可以保证后续操作不会意外改变原始对象，从而造成“不可变”List变化了：
  
  import java.util.*;
  public class Main {
      public static void main(String[] args) {
          List<String> mutable = new ArrayList<>();
          mutable.add("apple");
          mutable.add("pear");
          // 变为不可变集合:
          List<String> immutable = Collections.unmodifiableList(mutable);
          // 立刻扔掉mutable的引用:
          mutable = null;
          System.out.println(immutable);
      }
  }
  
   Run
  （6）线程安全集合
  Collections还提供了一组方法，可以把线程不安全的集合变为线程安全的集合：
  
  变为线程安全的List：List<T> synchronizedList(List<T> list)
  变为线程安全的Set：Set<T> synchronizedSet(Set<T> s)
  变为线程安全的Map：Map<K,V> synchronizedMap(Map<K,V> m)
  多线程的概念我们会在后面讲。因为从Java 5开始，引入了更高效的并发集合类，所以上述这几个同步方法已经没有什么用了。
  
  （7）小结
  Collections类提供了一组工具方法来方便使用集合类：
  创建空集合；
  创建单元素集合；
  创建不可变集合；
  排序／洗牌等操作。
  
 4.Arrays
 Arrays类
 Arrays类是数组的操作类，定义在java.util包中，主要的功能可以实现数组元素的查找，数组内容的填充、排序等。
 有以下的方法：
 public static boolean equals(int[] a, int[] a2) 判断两个数组是否相等，此方法被重载多次，可以判断各种数组类型的数组。
 public static void fill(int[] a,int val)  将指定的内容填充到数组之中，此方法被重载多次，可以填充各种数据类型的数组
 public static void sort(int[] a) 数组排序，此方法被重载多次，可以对各种类型的数组进行排序。
 public static int binarySearch(int[] a,int key) 对排序后的数组进行二分法检索，此方法被重载多次，可以对各种数据类型的数组进行搜索。
 public static String toString(int[] a) 输出数组信息，此方法被重载多次，可以输出各种数据类型的数组。
 
5.Comparable接口的作用
   之前Arrays类中存在sort()方法，注意sort方法中有一个专门对对象排序的辅助类参数，当然对于已经实现Comparable接口的对象直接对数组排序即可。
Comparable接口：
   可以直接使用java.util.Arrays类进行数组的排序操作，但对象所在的类必须实现Comparable接口，用于指定排序接口，Comparable接口定义如下：
   public interface Comparable<T>{
       public  int  compareTo(T o);
   }
此方法返回一个int类型的数据，但是此int的值只能是以下三种：
   1：表示大于
   -1：表示小于
   0：表示等于
要求：
   定义一个学生类，里面有姓名、年龄、成绩三个属性，要求按成绩由高到低排序，如果成绩相等则按照年龄由低到高排序。
   代码示例如下：
   class Student implements Comparable<Student> {	// 指定类型为Student
   	private String name ;
   	private int age ;
   	private float score ;
   	public Student(String name,int age,float score){
   		this.name = name ;
   		this.age = age ;
   		this.score = score ;
   	}
   	public String toString(){
   		return name + "\t\t" + this.age + "\t\t" + this.score ;
   	}
   	public int compareTo(Student stu){	// 覆写compareTo()方法，实现排序规则的应用
   		if(this.score>stu.score){//成绩由高到低
   			return -1 ;
   		}else if(this.score<stu.score){
   			return 1 ;
   		}else{
   			if(this.age>stu.age){//年龄由低到高
   				return 1 ;
   			}else if(this.age<stu.age){
   				return -1 ;
   			}else{
   				return 0 ;
   			}
   		}	
   	}
   };
   public class ComparableDemo01{
   	public static void main(String args[]){
   		Student stu[] = {
   			new Student("张三",20,90.0f),
   			new Student("李四",22,90.0f),
   			new Student("王五",20,99.0f),
   			new Student("赵六",20,70.0f),
   			new Student("孙七",22,100.0f)} ;
   		java.util.Arrays.sort(stu) ;	// 进行排序操作
   		for(int i=0;i<stu.length;i++){	// 循环输出数组中的内容
   			System.out.println(stu[i]) ;
   		}
   	}
   };
   运行结果如下：成绩由高到低排序，成绩相等按照年龄由低到高排序
   
   注意：如果此时Student类中没有实现Comparable接口，则在执行时会出现以下的异常：
   Exception in thread "main" java.lang.ClassCastException
   
分析比较器的排序原理
    实际上比较器的操作，就是数据结构里的二叉树排序算法。
    排序的基本原理：使用第一个元素作为根节点，之后如果后面的内容比根节点小，则放在左子树，如果内容比根节点的内容要大，则放在右子树。之后利用中序遍历的方式把内容依次读取出来。
   
   下边就手工实现一个二叉树的比较算法。
   为了操作方便，此处使用Integer类完成。
   class BinaryTree{
   	class Node{			// 声明一个节点类
   		private Comparable data ;	// 保存具体的内容
   		private Node left ;			// 保存左子树
   		private Node right ;		// 保存右子树
   		public Node(Comparable data){
   			this.data = data ;
   		}
   		public void addNode(Node newNode){
   			// 确定是放在左子树还是右子树
   			if(newNode.data.compareTo(this.data)<0){	// 内容小，放在左子树
   				if(this.left==null){
   					this.left = newNode ;	// 直接将新的节点设置成左子树
   				}else{
   					this.left.addNode(newNode) ;	// 继续向下判断
   				}
   			}
   			if(newNode.data.compareTo(this.data)>=0){	// 放在右子树
   				if(this.right==null){
   					this.right = newNode ;	// 没有右子树则将此节点设置成右子树
   				}else{
   					this.right.addNode(newNode) ;	// 继续向下判断
   				}
   			}
   		}
   		public void printNode(){	// 输出的时候采用中序遍历
   			if(this.left!=null){
   				this.left.printNode() ;	// 输出左子树
   			}
   			System.out.print(this.data + "\t") ;
   			if(this.right!=null){
   				this.right.printNode() ;
   			}
   		}
   	};
   	private Node root ;		// 根元素
   	public void add(Comparable data){	// 加入元素
   		Node newNode = new Node(data) ;	// 定义新的节点
   		if(root==null){	// 没有根节点
   			root = newNode ;	// 第一个元素作为根节点
   		}else{
   			root.addNode(newNode) ; // 确定是放在左子树还是放在右子树
   		}
   	}
   	public void print(){
   		this.root.printNode() ;	// 通过根节点输出
   	}
   };
   public class ComparableDemo03{
   	public static void main(String args[]){
   		BinaryTree bt = new BinaryTree() ;
   		bt.add(8) ;
   		bt.add(3) ;
   		bt.add(3) ;
   		bt.add(10) ;
   		bt.add(9) ;
   		bt.add(1) ;
   		bt.add(5) ;
   		bt.add(5) ;
   		System.out.println("排序之后的结果：") ;
   		bt.print() ;
   	}
   };
   
6. 另一种比较器：Comparator
   如果一个类已经开发完成，但是在此类建立的初期并没有实现Comparable接口，此时肯定是无法进行对象排序操作的，所以为了解决这样的问题，
   java又定义了另一个比较器的操作接口——Comparator。此接口定义在java.util包中，接口定义如下：
   public interface Comparator<T>{
         public int compare(T o1, T o2);
         boolean equals(Object obj);
   }
    下面定义一个自己的类，此类没有实现Comparable接口。
   如下所示： 
   import java.util.* ;
   class Student{	// 指定类型为Student
   	private String name ;
   	private int age ;
   	public Student(String name,int age){
   		this.name = name ;
   		this.age = age ;
   	}
   	public boolean equals(Object obj){	// 覆写equals方法
   		if(this==obj){
   			return true ;
   		}
   		if(!(obj instanceof Student)){
   			return false ;
   		}
   		Student stu = (Student) obj ;
   		if(stu.name.equals(this.name)&&stu.age==this.age){
   			return true ;
   		}else{
   			return false ;
   		}
   	}
   	public void setName(String name){
   		this.name = name ;
   	}
   	public void setAge(int age){
   		this.age = age ;
   	}
   	public String getName(){
   		return this.name ;
   	}
   	public int getAge(){
   		return this.age ;
   	}
   	public String toString(){
   		return name + "\t\t" + this.age  ;
   	}
   };
    
   class StudentComparator implements Comparator<Student>{	// 实现比较器
   	// 因为Object类中本身已经有了equals()方法
   	public int compare(Student s1,Student s2){
   		if(s1.equals(s2)){
   			return 0 ;
   		}else if(s1.getAge()<s2.getAge()){	// 按年龄比较     年龄从大到小排列
   			return 1 ;
   		}else{
   			return -1 ;
   		}
   	}
   };
    
   public class ComparatorDemo{
   	public static void main(String args[]){
   		Student stu[] = {new Student("张三",20),
   			new Student("李四",22),new Student("王五",20),
   			new Student("赵六",20),new Student("孙七",22),
   		new Student("郑八",18),new Student("谢九",30),
   		new Student("于十",18),new Student("八十一",21)};
   		java.util.Arrays.sort(stu,new StudentComparator()) ;	// 进行排序操作
   		for(int i=0;i<stu.length;i++){	// 循环输出数组中的内容
   			System.out.println(stu[i]) ;
   		}
   	}
   };
注意：equals方法不实现也可，因为Object类中已经有了equals方法。但最好还是实现，因为一般对象的比较都是比较内容和类型的，很少比较内存地址的。
   
   总结：
   在使用中还是尽可能的使用Comparable在需要排序的类上实现此接口，而Comparator需要单独建立一个排序的类，这样如果有很多的话，则排序的规则类也会非常多，操作起来非常麻烦。
   掌握一点：只要是对象排序，则在java中永远是以Comparable接口为准的。
   
   
 
7.在Java中的Collections类中有sort方法, 除了需要传入一个Comparator比较器, 或者需要排序的类实现了Comparable接口;  
  1.使用lambda表达式
  我写了3种lambda表达式的写法:
  第一种的解释可以看小标题3;
  后面两种本质上是一个意思, 传入2个量, 返回比较他们的结果, p在列表的前面, q在列表的后面, 如果希望是升序排列, 就要后面的比前面的大, 
  就是q.length()-p.length()>0; 如果要降序就是前面的要比后面的大, 就是p.length()-q.length()>0; 也可以直接调用Integer的比较方法, 返回的具体值是两个元素的差值;
  
  Collections.sort(Arrays.asList(s), Comparator.comparingInt(str->(str.length())));
  Collections.sort(Arrays.asList(s), (p,q)->{
      return Integer.valueOf(p.length()).compareTo(q.length());
  });
  Collections.sort(Arrays.asList(s), (p,q)->{
      return p.length()-q.length();
  });
  
  2.自定义Comparator方法(老方法)
  我这里写的时候是使用的匿名对象, 自己定义了一个Comparator, 用于比较String类型的比较器, 然后记得覆写Compare方法;
  Collections.sort(Arrays.asList(s), new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
          return o1.length()-o2.length();
      }
  });
  当然也可以这样写, 不用匿名函数比较繁琐一些:
  
  Comparator<String> comp = new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
          return o1.length()-o2.length();
      }
  };
  Collections.sort(Arrays.asList(s), comp);
  
  3.方法引用
  使用方法引用, 传入String类的length方法, 其实和上面的第一种lambda表达式是一个意思;
  
  Collections.sort(Arrays.asList(s), Comparator.comparingInt(String::length));
  Collections.sort(Arrays.asList(s), Comparator.comparingInt(str->(str.length())));
  不过这种方法似乎对于第二比较关键词就没法实现了, 所以还是得用前面两种方法;
  
  
  
 8.Collection
有序 List:ArrayList、LinkedList、
没有重复元素 Set:HashSet、TreeSet
键值（key-value）查找的映射表 Map:HashMap、TreeMap

有一小部分集合类是遗留类，不应该继续使用：
Hashtable：一种线程安全的Map实现；
Vector：一种线程安全的List实现；
Stack：基于Vector实现的LIFO的栈。

Java访问集合总是通过统一的方式——迭代器（Iterator）来实现，它最明显的好处在于无需知道集合内部元素是按什么方式存储的。

(1)ArrayList
把添加和删除的操作封装起来，让我们操作List类似于操作数组，却不用关心内部元素如何移动。
我们考察List<E>接口，可以看到几个主要的接口方法：
 在末尾添加一个元素：boolean add(E e)
   在指定索引添加一个元素：boolean add(int index, E e)
   删除指定索引的元素：int remove(int index)
   删除某个元素：int remove(Object e)
   获取指定索引的元素：E get(int index)
   获取链表大小（包含元素的个数）：int size()
   
LinkedList
通过“链表”也实现了List接口。在LinkedList中，它的内部每个元素都指向下一个元素
(2)
Set用于存储不重复的元素集合，它主要提供以下几个方法：
将元素添加进Set<E>：boolean add(E e)
将元素从Set<E>删除：boolean remove(Object e)
判断是否包含元素：boolean contains(Object e)

(3)
 
 
  
 (4)队列（Queue）是一种经常使用的集合。
 Queue实际上是实现了一个先进先出（FIFO：First In First Out）的有序表。它和List的区别在于，List可以在任意位置添加和删除元素，而Queue只有两个操作：
把元素添加到队列末尾；从队列头部取出元素。
  在Java的标准库中，队列接口Queue定义了以下几个方法：
    int size()：获取队列长度；
    boolean add(E)/boolean offer(E)：添加元素到队尾；     前面的会抛出异常，后面的是返回false或者null
    E remove()/E poll()：获取队首元素并从队列中删除；
    E element()/E peek()：获取队首元素但并不从队列中删除。
 
 (5)PriorityQueue和Queue的区别在于，它的出队顺序与元素的优先级有关，对PriorityQueue调用remove()或poll()方法，返回的总是优先级最高的元素。
要使用PriorityQueue，我们就必须给每个元素定义“优先级”。
放入PriorityQueue的元素，必须实现Comparable接口，PriorityQueue会根据元素的排序顺序决定出队的优先级。
如果我们要放入的元素并没有实现Comparable接口怎么办？PriorityQueue允许我们提供一个Comparator对象来判断两个元素的顺序。
public class Main {
    public static void main(String[] args) {
        Queue<User> q = new PriorityQueue<>(new UserComparator());
        // 添加3个元素到队列:
        q.offer(new User("Bob", "A1"));
        q.offer(new User("Alice", "A2"));
        q.offer(new User("Boss", "V1"));
        System.out.println(q.poll()); // Boss/V1
        System.out.println(q.poll()); // Bob/A1
        System.out.println(q.poll()); // Alice/A2
        System.out.println(q.poll()); // null,因为队列为空
    }
}

class UserComparator implements Comparator<User> {
    public int compare(User u1, User u2) {
        if (u1.number.charAt(0) == u2.number.charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return u1.number.compareTo(u2.number);
        }
        if (u1.number.charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }
    }
}

class User {
    public final String name;
    public final String number;

    public User(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String toString() {
        return name + "/" + number;
    }
}

(6)Deque来实现一个双端队列，它的功能是：
   既可以添加到队尾，也可以添加到队首；
   既可以从队首获取，又可以从队尾获取。
   我们来比较一下Queue和Deque出队和入队的方法：
Deque实现了一个双端队列（Double Ended Queue），它可以：

将元素添加到队尾或队首：addLast()/offerLast()/addFirst()/offerFirst()；
从队首／队尾获取元素并删除：removeFirst()/pollFirst()/removeLast()/pollLast()；
从队首／队尾获取元素但不删除：getFirst()/peekFirst()/getLast()/peekLast()；
总是调用xxxFirst()/xxxLast()以便与Queue的方法区分开；
避免把null添加到队列。


(7)Stack只有入栈和出栈的操作：
   把元素压栈：push(E)；
   把栈顶的元素“弹出”：pop(E)；
   取栈顶元素但不弹出：peek(E)。
   在Java中，我们用Deque可以实现Stack的功能：
   
   把元素压栈：push(E)/addFirst(E)；
   把栈顶的元素“弹出”：pop(E)/removeFirst()；
   取栈顶元素但不弹出：peek(E)/peekFirst()。
为什么Java的集合类没有单独的Stack接口呢？因为有个遗留类名字就叫Stack，出于兼容性考虑，所以没办法创建Stack接口，只能用Deque接口来“模拟”一个Stack了。
   
   当我们把Deque作为Stack使用时，注意只调用push()/pop()/peek()方法，不要调用addFirst()/removeFirst()/peekFirst()方法，这样代码更加清晰。
   最后，不要使用遗留类Stack。
  