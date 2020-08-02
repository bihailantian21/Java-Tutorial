

1.只有String才能转换成char或者char[]
     * Java中char是一个基本类型，而String是一个引用类型。有时候我们需要在它们之间互相转换。
String转换为char
     * 在Java中将String转换为char是非常简单的。
     * (1))使用String.charAt(index)（返回值为char）可以得到String中某一指定位置的char。
     * (2)使用String.toCharArray()（返回值为char[]）可以得到将包含整个String的char数组。这样我们就能够使用从0开始的位置索引来访问string中的任意位置的元素。
     *
char转换为String
     * 将char转换为String大致有6种方法。总结如下：
     * (1)String s = String.valueOf('c'); //效率最高的方法
     * (2)String s = Character.toString('c');
     * // Character.toString(char)方法实际上直接返回String.valueOf(char)
     * (3)String s = "" + 'c';
     * // 虽然这个方法很简单，但这是效率最低的方法
     * // Java中的String Object的值实际上是不可变的，是一个final的变量。
     * // 所以我们每次对String做出任何改变，都是初始化了一个全新的String Object并将原来的变量指向了这个新String。
     * // 而Java对使用+运算符处理String相加进行了方法重载。
     * // 字符串直接相加连接实际上调用了如下方法：
     * // new StringBuilder().append("").append('c').toString();
     *
     *
     *
     *
2.int转换为String
     * (1)num + ""
     * (2)String.valueOf(num)
     * (3)Integer.toString(num)
     *
     * String转int有两种方式
     * (1)Integer.parseInt(str)
     * (2)Integer.valueOf(str).intValue()直接使用静态方法，不会产生多余的对象，但会抛出异常
     * Integer.valueOf(s) 相当于 new Integer(Integer.parseInt(s))，也会抛异常，但会多产生一个对象
     *
     *
     *
3.Java基本类型共有八种，基本类型可以分为三类，
     * 字符类型char，布尔类型boolean以及数值类型又可以分为整数类型byte、short、int、long和浮点数类型float、double。
     * byte：8位，最大存储数据量是255，存放的数据范围是-128~127之间。
     * short：16位，最大数据存储量是65536，数据范围是-32768~32767之间。
     * int：32位，最大数据存储容量是2的32次方减1，数据范围是负的2的31次方到正的2的31次方减1。
     * long：64位，最大数据存储容量是2的64次方减1，数据范围为负的2的63次方到正的2的63次方减1。
     * float：32位，数据范围在3.4e-45~1.4e38，直接赋值时必须在数字后加上f或F。
     * double：64位，数据范围在4.9e-324~1.8e308，赋值时可以加d或D也可以不加。
     * boolean：只有true和false两个取值。
     * char：16位，存储Unicode码，用单引号赋值。
     *
     * ascii码
     * 其次，我们要知道，在计算机中，所有的数据在存储和运算时都要使用二进制数表示，例如，像a、b、c、d这样的字母以及各种符号，
     * 还有作为字符的数字，都要使用二进制数字来表示，因此需要一种二进制码与这些字符的对应关系。
     *
     * 数据类型之间的转换
     * 1).简单类型数据间的转换,有两种方式:自动转换和强制转换,通常发生在表达式中或方法的参数传递时。
     * 自动转换
     * 具体地讲,当一个较"小"数据与一个较"大"的数据一起运算时,系统将自动将"小"数据转换成"大"数据,再进行运算.
     * 而在方法调用时,实际参数较"小",而被调用的方法的形式参数数据又较"大"时(若有匹配的,当然会直接调用匹配的方法),
     * 系统也将自动将"小"数据转换成"大"数据,再进行方法的调用,自然,对于多个同名的重载方法,会转换成最"接近"的"大"数据并进行调用。
     * 这些类型由"小"到"大"分别为 (byte，short，char)--int--long--float—double。这里我们所说的"大"与"小",并不是指占用字节的多少,而是指表示值的范围的大小。
     * ①下面的语句可以在Java中直接通过：
     * byte b;int i=b; long l=b; float f=b; double d=b;
     * ②如果低级类型为char型，向高级类型（整型）转换时，会转换为对应ASCII码值，例如
     * char c='c'; int i=c;
     * System.out.println("output:"+i);输出：output:99;
     *
 总结：
     * char为16位的数据，为无符号数，其范围为：0 至 2 ^ 16 -1，即 0 - 65535，用十六进制码来看，则为：’\u0000’ - ‘\uffff’。
     * 再从前面引言中对于ascii码的描述，我们可以看出，无论是什么字符，在计算机中，其实也是以数字（本质为一个二进制数）的形式存储的，
     * 因此，在java中，无论是一个字符，还是一个字符型变量，实则也是一个数字，所以，可以直接将一个（不超过char的范围的）数字赋值给一个char变量，即可输出其结果：
     * char c1 = 97;System.out.println(c1);输出：a          正确输出了97的ascii码所对应的字符：‘a’。
     * char c2 = 'a' + 1;System.out.println("c2:          " + c2);输出：c2:          b
     * 我们可以看出，int是比char范围更大的，因此，这些错误主要便是会出现在从int转到char的过程中。
     * 虽然我们可以直接以一个数字赋给一个char型的变量，但是，若我们赋的值的大小大于了65536，则会报出错误来，如下代码：
     * char c3 = 65535;
     * System.out.println(c3);
     * char c4 = 65536;
     * System.out.println(c4);
     * 输出：Error:(33, 19) java: 不兼容的类型: 从int转换到char可能会有损失
     * 报错是报在了上面代码中的第3行，即char c4 = 65536，这一行，因此，当对一个char类型赋值的时候，要注意，所赋的值不能超过了65535。
     *
     * int类型是一个32位的数据类型，因为其位有符号数，所以，其取值范围为：-2^31 至 2^31 - 1。
     * 同样的，我们可以将一个字符赋值给一个int变量，因为字符也是一个数字嘛
     * int num1 = 'a';System.out.println(num1);输出：97
     * int num2 = 'a' + 1;System.out.println("num2:        " + num2);输出：num2:        98
     *
     * char->int
     *
     * int->char
     *
     * 一些技巧
     * 在上面，我们看到了，char其实也是一个数字，所以，可以利用这样的特性，在编程的时候，使用一些小技巧。
     * 比如，有一个字符为'1'，我们想将其变为数字1，或者，我们想将一个int类型的数字1转换为字符'1'，这时，我们就可以这样写：
     * // '1' -> 1
     * char c9 = '1';
     * int num9 = c9 - '0';
     * // 1 -> '1'
     * int num10 = 1;
     * char c10 = (char)(num10 + '0');
     * 同样的，如果我们想用1 - 26来表示字母’a’ 到 ‘z’ ，也可以用类似的方法来表示。
     *
     * ③对于byte,short,char三种类型而言，他们是平级的，因此不能相互自动转换，可以使用下述的强制类型转换。
     * short i=99 ; char c=(char)i; System.out.println("output:"+c);输出：output:c;
     *
     * 强制转换
     * 将"大"数据转换为"小"数据时，你可以使用强制类型转换。即你必须采用下面这种语句格式： int n=(int)3.14159/2;可以想象，这种转换肯定可能会导致溢出或精度的下降。
     *
     * 2)表达式的数据类型自动提升, 关于类型的自动提升，注意下面的规则。
     * ①所有的byte,short,char型的值将被提升为int型；
     * ②如果有一个操作数是long型，计算结果是long型；
     * ③如果有一个操作数是float型，计算结果是float型；
     * ④如果有一个操作数是double型，计算结果是double型；
     * 例， byte b; b=3; b=(byte)(b*3);//必须声明byte。
     *
     * 3)包装类过渡类型转换
     * 一般情况下，我们首先声明一个变量，然后生成一个对应的包装类，就可以利用包装类的各种方法进行类型转换了。例如：
     * ①当希望把float型转换为double型时：
     * float f1=100.00f;
     * Float F1=new Float(f1);
     * double d1=F1.doubleValue();//F1.doubleValue()为Float类的返回double值型的方法
     * ②当希望把double型转换为int型时：
     * double d1=100.00;
     * Double D1=new Double(d1);
     * int i1=D1.intValue();
     * 简单类型的变量转换为相应的包装类，可以利用包装类的构造函数。即：Boolean(boolean value)、Character(char value)、Integer(int value)、Long(long value)、Float(float value)、Double(double value)
     * 而在各个包装类中，总有形为××Value()的方法，来得到其对应的简单类型数据。利用这种方法，也可以实现不同数值型变量间的转换，
     * 例如，对于一个双精度实型类，intValue()可以得到其对应的整型变量，而doubleValue()可以得到其对应的双精度实型变量。
     *
     * 4)字符串与其它类型间的转换
     * 其它类型向字符串的转换
     * ①调用类的串转换方法:X.toString();
     * ②自动转换:X+"";
     * ③使用String的方法:String.volueOf(X);

     * 字符串作为值,向其它类型的转换
     * ①先转换成相应的封装器实例,再调用对应的方法转换成其它类型
     * 例如，字符中"32.1"转换double型的值的格式为:new Float("32.1").doubleValue()。也可以用:Double.valueOf("32.1").doubleValue()
     * ②静态parseXXX方
     * String s = "1";
     * byte b = Byte.parseByte( s );
     * short t = Short.parseShort( s );
     * int i = Integer.parseInt( s );
     * long l = Long.parseLong( s );
     * Float f = Float.parseFloat( s );
     * Double d = Double.parseDouble( s );
     * ③Character的getNumericValue(char ch)方法
     *
     * 5）Date类与其它数据类型的相互转换
     * 整型和Date类之间并不存在直接的对应关系，只是你可以使用int型为分别表示年、月、日、时、分、秒，这样就在两者之间建立了一个对应关系，在作这种转换时，你可以使用Date类构造函数的三种形式：
     * ①Date(int year, int month, int date)：以int型表示年、月、日
     * ②Date(int year, int month, int date, int hrs, int min)：以int型表示年、月、日、时、分
     * ③Date(int year, int month, int date, int hrs, int min, int sec)：以int型表示年、月、日、时、分、秒
     * 在长整型和Date类之间有一个很有趣的对应关系，就是将一个时间表示为距离格林尼治标准时间1970年1月1日0时0分0秒的毫秒数。对于这种对应关系，Date类也有其相应的构造函数：Date(long date)。
     * 获取Date类中的年、月、日、时、分、秒以及星期你可以使用Date类的getYear()、getMonth()、getDate()、getHours()、getMinutes()、getSeconds()、getDay()方法，你也可以将其理解为将Date类转换成int。
     * 而Date类的getTime()方法可以得到我们前面所说的一个时间对应的长整型数，与包装类一样，Date类也有一个toString()方法可以将其转换为String类。
     * 有时我们希望得到Date的特定格式，例如20020324，我们可以使用以下方法，首先在文件开始引入，
     *

     * 总结：只有boolean不参与数据类型的转换
     * （1）.自动类型的转换：
     * a.常数在表数范围内是能够自动类型转换的
     * b.数据范围小的能够自动数据类型大的转换（注意特例）
     * int到float，long到float，long到double 是不会自动转换的，不然将会丢失精度
     * c.引用类型能够自动转换为父类的
     * d.基本类型和它们包装类型是能够互相转换的
     * （2）.强制类型转换：用圆括号括起来目标类型，置于变量前
     *
     *
     *
     * 4，Java有 5种引用类型（对象类型）：类 接口 数组 枚举 标注
     * 引用类型：底层结构和基本类型差别较大
     * JVM的内存空间：（1）. Heap 堆空间：分配对象 new Student（）
     * （2）. Stack 栈空间：临时变量 Student stu
     * （3）.Code 代码区 ：类的定义，静态资源 Student.class
     * eg：Student stu = new Student（）； //new 在内存的堆空间创建对象
     * stu.study(); //把对象的地址赋给stu引用变量
     * 上例实现步骤：a.JVM加载Student.class 到Code区
     *      b.new Student()在堆空间分配空间并创建一个Student实例
     *      c.将此实例的地址赋值给引用stu， 栈空间
     *
     *
5.String类
     * 介绍
     * String类是一个特殊的对象，适用于描述字符串事物的，一旦被初始化就不可以被改变。String类复写了Object类中的equals方法，该方法用于判断字符串是否相同。
     * 特征
     * 1、String类是final的，不可被继承
     * 2、String类本质是字符型数组char[ ]，并且值不可改变；
     * 3、String类对象有个特殊的创建的方式，就是直接指定比如String x = "abc"，"abc"就表示一个字符串对象。而x是"abc"对象的地址，也叫做"abc"对象的引用；
     * 4、String对象可以通过“+”串联。串联后会生成新的字符串；
     * 5、Java运行时会维护一个String Pool（String池），JavaDoc翻译很模糊“字符串缓冲区”。String池用来存放运行时中产生的各种字符串，并且池中的字符串的内容不重复。而一般对象不存在这个缓冲池，并且创建的对象仅仅存在于方法的堆栈区；
     * 6、创建字符串的方式很多，归纳起来有三类：
     * 第一类，利用new关键字创建一个String对象，如：String s1 = new String("abc")；
     * 第二类，利用特性3直接创建，如:String s2 = "abc"；
     * 第三类，利用特性4串联生成新的字符串，如:String s3 = "a" + "b" + "c"；
     *
     *
     *
     * 常用方法
     * 1、length() 字符串的长度
     * 例：char chars[]={'a','b'.'c'};
     * String s=new String(chars);
     * int len=s.length();
     * 注意：数组中的是length属性，String类中是length();
     *
     * 2、charAt() 截取一个字符
     * 例：char ch;
     * ch="abc".charAt(1); 返回'b'
     * 根据位置获取对应位置上的字符。char  charAt(int index).当访问到字符串中不存在的角标时会发生StringIndexOfBoundsException
     *
     * 3、 getChars() 截取多个字符
     * void getChars(int sourceStart,int sourceEnd,char target[],int targetStart)
     * sourceStart指定了子串开始字符的下标，sourceEnd指定了子串结束后的下一个字符的下标。因此， 子串包含从sourceStart到sourceEnd-1的字符。接收字符的数组由target指定，target中开始复制子串的下标值是targetStart。
     * 例：String s="this is a demo of the getChars method.";
     * char buf[]=new char[20];
     * s.getChars(10,14,buf,0);
     *
     * 4、getBytes()
     * 替代getChars()的一种方法是将字符存储在字节数组中，该方法即getBytes()。
     *
     * 5、toCharArray()
     * String转换成char数组
     *
     * 6、equals()和equalsIgnoreCase()
     * 比较两个字符串
     *
     * 7、regionMatches()
     * 用于比较一个字符串中特定区域与另一特定区域，它有一个重载的形式允许在比较中忽略大小写。
     * boolean regionMatches(int startIndex,String str2,int str2StartIndex,int numChars)
     * boolean regionMatches(boolean ignoreCase,int startIndex,String str2,int str2StartIndex,int numChars)
     *
     * 8、startsWith()和endsWith()
     * startsWith()方法决定是否以特定字符串开始，endWith()方法决定是否以特定字符串结束
     *
     * 9、equals()和==
     * equals()方法比较字符串对象中的字符，==运算符比较两个对象是否引用同一实例。
     * 例：String s1="Hello";
     * String s2=new String(s1);
     * s1.eauals(s2); //true
     * s1==s2;//false
     *
     * 10、compareTo()和compareToIgnoreCase()
     * 比较字符串
     *
     * 11、indexOf()和lastIndexOf()
     * indexOf() 查找字符或者子串第一次出现的地方。
     * lastIndexOf() 查找字符或者子串是后一次出现的地方。
     * Java String.indexOf()用法
     * indexOf() 方法有以下四种形式：（为什么接收的是int呢？因为接收的是ASCII码。）
     * public int indexOf(int ch): 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
     * public int indexOf(int ch, int fromIndex): 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
     * int indexOf(String str): 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
     * int indexOf(String str, int fromIndex): 返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
     *
     * 12、substring()
     * 它有两种形式，第一种是：String substring(int startIndex)
     * 第二种是：String substring(int startIndex,int endIndex)beginIndex -- 起始索引（包括）。endIndex -- 结束索引（不包括）。
     *
     * 13、concat()
     * 连接两个字符串
     *
     * 14 、replace() 替换
     * 它有两种形式，第一种形式用一个字符在调用字符串中所有出现某个字符的地方进行替换，形式如下：
     * String replace(char original,char replacement)
     * 例如：String s="Hello".replace('l','w');
     * 第二种形式是用一个字符序列替换另一个字符序列，形式如下：
     * String replace(CharSequence original,CharSequence replacement)
     特别注意：str.replace('1',''2');这样是输出str还是原来的，因为这个替换必须得有返回值，所以需要str=str.replace('1',''2');这样输出str才可以
     *
     * 15、trim()
     * 去掉起始和结尾的空格
     *
     * 16、valueOf()
     * 转换为字符串
     * Java String.valueOf()用法
     *  valueOf() 方法有以下几种不同形式：
     *      * valueOf(boolean b): 返回 boolean 参数的字符串表示形式。.
     *      * valueOf(char c): 返回 char 参数的字符串表示形式。
     *      * valueOf(char[] data): 返回 char 数组参数的字符串表示形式。
     *      * valueOf(char[] data, int offset, int count): 返回 char 数组参数的特定子数组的字符串表示形式。
     *      * valueOf(double d): 返回 double 参数的字符串表示形式。
     *      * valueOf(float f): 返回 float 参数的字符串表示形式。
     *      * valueOf(int i): 返回 int 参数的字符串表示形式。
     *      * valueOf(long l): 返回 long 参数的字符串表示形式。
     *      * valueOf(Object obj): 返回 Object 参数的字符串表示形式。
     *
     * 17、toLowerCase()
     * 转换为小写
     *
     * 18、toUpperCase()
     * 转换为大写
     *
     * 19.Java String.format()用法
     * String类的format()方法用于创建格式化的字符串以及连接多个字符串对象。
     * 语法
     * Format(String, Object)  将指定的 String 中的格式项替换为指定的 Object 实例的值的文本等效项。
     * Format(String, array<>[]()[])  将指定 String 中的格式项替换为指定数组中相应 Object 实例的值的文本等效项。
     * Format(IFormatProvider, String, array<>[]()[])  将指定 String 中的格式项替换为指定数组中相应 Object 实例的值的文本等效项。指定的参数提供区域性特定的格式设置信息。
     * Format(String, Object, Object)  将指定的 String 中的格式项替换为两个指定的 Object 实例的值的文本等效项。
     * Format(String, Object, Object, Object)  将指定的 String 中的格式项替换为三个指定的 Object 实例的值的文本等效项。
     *
     * 20.Java String.split()用法
     * split() 方法根据匹配给定的正则表达式来拆分字符串。
     * 注意： . 、 | 和 * 等转义字符，必须得加 \\。
     * 注意：多个分隔符，可以用 | 作为连字符。
     * 语法
     * public String[] split(String regex, int limit)regex -- 正则表达式分隔符。limit -- 分割的份数。
     需要对路径进行分割操作时出现的异常
     '\'本身是个转义符所以需要转义
     \\ -> \
     所以实际匹配的是path.split('\\')也就可以对路径进行分割了

     * 21.contains()
     
StringBuilder
append()的参数可以是字符char       stringBuilder.append((char)(c + 1 +32));

注意char和别的数字进行相加之后一定要自己再转换回char，要不然就是数字
 StringBuffer 方法
 以下是 StringBuffer 类支持的主要方法：
 序号	方法描述
 1	public StringBuffer append(String s)
 将指定的字符串追加到此字符序列。
 2	public StringBuffer reverse()
  将此字符序列用其反转形式取代。
 3	public delete(int start, int end)
 移除此序列的子字符串中的字符。
 4	public insert(int offset, int i)
 将 int 参数的字符串表示形式插入此序列中。
 5	replace(int start, int end, String str)
 使用给定 String 中的字符替换此序列的子字符串中的字符。
 下面的列表里的方法和 String 类的方法类似：
 
 序号	方法描述
 1	int capacity()
 返回当前容量。
 2	char charAt(int index)
 返回此序列中指定索引处的 char 值。
 3	void ensureCapacity(int minimumCapacity)
 确保容量至少等于指定的最小值。
 4	void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
 将字符从此序列复制到目标字符数组 dst。
 5	int indexOf(String str)
 返回第一次出现的指定子字符串在该字符串中的索引。
 6	int indexOf(String str, int fromIndex)
 从指定的索引处开始，返回第一次出现的指定子字符串在该字符串中的索引。
 7	int lastIndexOf(String str)
 返回最右边出现的指定子字符串在此字符串中的索引。
 8	int lastIndexOf(String str, int fromIndex)
 返回 String 对象中子字符串最后出现的位置。
 9	int length()
  返回长度（字符数）。
 10	void setCharAt(int index, char ch)
 将给定索引处的字符设置为 ch。
 11	void setLength(int newLength)
 设置字符序列的长度。
 12	CharSequence subSequence(int start, int end)
 返回一个新的字符序列，该字符序列是此序列的子序列。
 13	String substring(int start)
 返回一个新的 String，它包含此字符序列当前所包含的字符子序列。
 14	String substring(int start, int end)
 返回一个新的 String，它包含此序列当前所包含的字符子序列。
 15	String toString()
 返回此序列中数据的字符串表示形式。
 
     
 6.Integer类的常用方法：
 1、Integer是int的包装类，int是java的一种基本数据类型 
 2、Integer变量必须实例化后才能使用，而int变量不需要 
 3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；
 而int则是直接存储数据值 非new生成的Integer变量指向的是java常量池中的对象，
 而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）
 Integer a = new Integer(99);
 Integer b = 99;
 System.out.print(a== b); 
 //false
 4、Integer的默认值是null，int的默认值是0
 Java的Ingeter是int的包装类,在开发中我们基本可以将两者等价。
 Integer类是对int进行封装,里面包含处理int类型的方法，比如int到String类型的转换方法或String类型转int类型的方法，
 也有与其他类型之间的转换方，当然也包括操作位的方法。
     * (1)
     * java中Integer.toBinaryString()用于将十进制转成二进制输出。
     * 定义
     * String toBinaryString(int i)   //以2为基返回整数参数的字符串表示形式，表示为无符号整数
     * (2)
     * Java Integer.toHexString()十进制转成十六进制
     * (3)
     * Java Integer.toOctalString()十进制转成八进制
     * (4)
     * Java Integer.valueOf()返回Integer整型对象
     * java中Integer.valueOf()用于返回一个Integer（整型）对象，当被处理的字符串在-128和127（包含边界）之间时，返回的对象是预先缓存的。
     * 定义
     * Integer valueOf(int i) 返回一个表示指定的int值的 Integer 实例。
     * Integer valueOf(String s) 返回保存指定的String的值的 Integer 对象。
     * Integer valueOf(String s, int radix)
     * (5)
     * Java Integer.parseInt()字符串转换int
     * java中Integer.parseInt()一般用于将字符串转成基本数据类型int类型，在进行转换的时候parseInt每次都会构造一个常量值。
     * 定义
     * int parseInt(String s) //将字符串转换成int
     * parseInt(String s, int radix) //以第二个参数指定的基数将字符串参数转换成int
     * (6)
     * Java Integer转换double，float，int，long，string
     * java中Integer类可以很方便的转换成double，float，int，long，string等类型，都有固定的方法进行转换。
     * 方法
     * double doubleValue() //以 double 类型返回该 Integer 的值。
     * float floatValue() //以 float 类型返回该 Integer 的值。
     * int intValue() //以 int 类型返回该 Integer 的值。
     * long longValue() //以 long 类型返回该 Integer 的值。
     * String toString() //返回一个表示该Integer值的String对象。
     * (7)
     * Java Integer.equals()判断相等
     * Java中判断Integer是否相等可以用equals()或者“==”,“==”是进行地址及值比较，equals方法是数值比较，
     * 当Integer的值不在-128到127的时候，会新new一个对象，因此这个时候如果用“==”进行判断就会报错。
     * 定义
     * boolean equals(Object obj)  //比较此对象与指定对象
     * Integer.equals()传入Integer对象，只进行值是否相等判断。
Integer类型的数据做加法：
有2个HashMap<String,Integer>[][]类型的对象  a和b
遍历b中对象的值，当对象b中的键值和a中一样的时候，需要把a中相应键所对应的Integer值加1
不知道Integer值加1怎么实现比较简单点呢
Map<String,Integer> a= new HashMap<String, Integer>();
Map<String,Integer> b= new HashMap<String, Integer>();  
        a.put("a", 2);
        a.put("b", 2);
        b.put("a", 2);
        b.put("b", 2);
        for(String key:b.keySet()){
            a.put(key, a.get(key)+1);
        }
        System.out.println(a);

有两个Integer类型的数值Integer  a1=3Integer  a1=5
我想把他们相加然后在把值=8在转换成Integer类型
new Integer(a1.intValue()+a2.intValue())

7.
     * double d=4.22;
     *
     * 小数转为整数分为：
     * 1.直接去除小数：Integer.parseInt(d);
     * 直接舍掉小数bai 比如float是4.7 转换du成int 后是4 而不是5 要四舍五入的zhi话转换前先加dao上0.5
     * 比如 int i ; double j = 4.7; i = (int)(j+0.5);
     * 2.4舍5入到整数：Math.round(d);
     * 3.取小数的最小整数(向下取整)：Math.floor(d);
     * 4.取小数的最大整数（向上取整）：Math.ceil(d);
     *
     * @param number
     * @return
     */
     


8.ASII码表：ASCII码128个，用7位二进制数可表示，第8位（最高位）是奇偶校验位，用于纠错。
33~47 其他字符   58~64   91~96   123~126
‘0’：48~57
’A’：65~90
’a’：97~122
char的用法：
     * 一些技巧
     * 在上面，我们看到了，char其实也是一个数字，所以，可以利用这样的特性，在编程的时候，使用一些小技巧。
     * 比如，有一个字符为'1'，我们想将其变为数字1，或者，我们想将一个int类型的数字1转换为字符'1'，这时，我们就可以这样写：
     * // '1' -> 1
     * char c9 = '1';
     * int num9 = c9 - '0';
     * // 1 -> '1'
     * int num10 = 1;
     * char c10 = (char)(num10 + '0');
     * 同样的，如果我们想用1 - 26来表示字母’a’ 到 ‘z’ ，也可以用类似的方法来表示。
     char c = (char)('A' + i);
     


 