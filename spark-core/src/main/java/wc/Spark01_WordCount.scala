package wc
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object Spark01_WordCount {
  def main(args: Array[String]): Unit = {
    //Application
    //Spark框架
    //TODO 建立和Spark框架的连接
    //JDBC:Connection
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)

    //TODO 执行业务操作

    //1.读取文件，获取一行一行的数据
    //2.将一行数据进行拆分，变成一个个的单词
    //3.将单词进行分组，相同单词在一起，便于统计
    //4.对分组后的单词进行转换 （hello,hello,hello)=>(hello,3)
    //5.将转换后的结果打印到控制台
    val textFile = sc.textFile(path = "data")
    //reducebyKey:相同key的数据，可以对value进行reduce聚合
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
    //TODO 关闭连接
    sc.stop();
  }
}
