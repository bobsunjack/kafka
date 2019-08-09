package test
import com.mongodb.spark.MongoSpark
import org.apache.spark.sql.SparkSession
object One extends App {


  val spark = SparkSession.builder()
    .master("local")
    .appName("MyApp")
    .config("spark.mongodb.input.uri", "mongodb://192.168.0.104/capturedb.user")
    .getOrCreate()

  // 设置log级别
  spark.sparkContext.setLogLevel("WARN")


  val df = MongoSpark.load(spark)
  df.show()

  df.createOrReplaceTempView("user")

  val resDf = spark.sql("select name,creationDate from user")
  resDf.show()

  spark.stop()

  System.exit(0)

}
