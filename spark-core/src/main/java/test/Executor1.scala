package test

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Executor1 {
  def main(args: Array[String]): Unit = {
    //启动服务器，接收数据
    val server=new ServerSocket(9999)
    println("服务器启动，等待接收数据")
    //等待客户端的连接
    val client: Socket=server.accept()
    val in:InputStream = client.getInputStream

    val objIn=new ObjectInputStream(in)
    val subtask:SubTask=objIn.readObject().asInstanceOf[SubTask]
    val valueList:List[Int]=subtask.compute()

    println("计算节点[9999]的结果为："+valueList)
    in.close()
    client.close()
    server.close()
  }
}
