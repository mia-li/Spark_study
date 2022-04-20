package test
import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

object Driver {
  def main(args: Array[String]): Unit = {
    //分布式计算，有两个节点用来共同计算数据，逻辑是一样的，数据拆分提交给各节点，可以提高计算速度
    //连接服务器
    val client1=new Socket("localhost",9999)
    val out1:OutputStream = client1.getOutputStream
    val objOut1=new ObjectOutputStream(out1)
    val task=new Task()
    val subTask1=new SubTask()
    subTask1.logic=task.logic
    subTask1.data=task.data.take(2)
    objOut1.writeObject(subTask1)
    objOut1.flush()
    objOut1.close()
    client1.close()

    val client2=new Socket("localhost",8888)
    val out2:OutputStream = client2.getOutputStream
    val objOut2=new ObjectOutputStream(out2)
    val subTask2=new SubTask()
    subTask2.logic=task.logic
    subTask2.data=task.data.takeRight(2)
    objOut2.writeObject(subTask2)
    objOut2.flush()
    objOut2.close()
    client2.close()

    println("客户端数据发送完毕")
  }
}
