package exercies

import scala.util.Random

object OptionTest extends App {
  val config: Map[String, String] = Map(
    "host" -> "192.168.1.0",
    "port" -> "80"
  )

  class Connection {
    def connect(): String = "connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection())
      else None
    }
  }

  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  connection.map(_.connect()).foreach(println(_))

  val cc = for {
    h <- config.get("host")
    p <- config.get("port")
    c <- Connection(h, p)
  } yield c
  cc.map(_.connect()).foreach(println(_))
}
