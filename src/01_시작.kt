// 01_시작.kt
// - Java 에서는 패키지는 디렉토리로 파일명은 public 클래스 또는 인터페이스 기준으로 작성되어야 합니다.
// => 기존의 자바와 동일한 방식으로 작성하는 것이 좋습니다.

package ex1

// 1) 코틀린 언어 특징
//  - 간결함
//    : 보일러플레이트를 제거합니다.
//  - 안전함
//    : Null을 안전하게 다루는 방법을 제공합니다.
//      Nullable / Optional
//  - 상호운용성
//    : 자바로 작성된 코드를 코틀린에서 이용할 수 있고,
//      코틀린에서 작성된 코드를 자바에서도 이용할 수 있습니다.

/*
// Hello.java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}

public class Hello {
  public Hello();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello, Java
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}


public final class HelloKt {
  public static final void main(java.lang.String[]);
    Code:
       0: aload_0
       1: ldc           #9                  // String args
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkNotNullParameter:(Ljava/lang/Object;Ljava/lang/String;)V
       6: ldc           #17                 // String Hello, Kotlin
       8: astore_1
       9: iconst_0
      10: istore_2
      11: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
      14: aload_1
      15: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      18: return
}

$ javac Hello.java
$ java Hello
*/

fun main(args: Array<String>) {
    println("Hello, Kotlin")
}
// $ kotlinc Hello.kt
// $ kotlin HelloKt

// $ kotlinc Hello.kt -include-runtime -d Hello.jar
// $ java -jar Hello.jar

// 코틀린은 REPL(Read-Eval-Print-Loop)을 제공합니다.
// $ kotlinc-jvm











