import sbt.Keys._

lazy val common = Seq(
    name := "hello0MQ",
    version := "1.0",
    scalaVersion := "2.10.4",
    unmanagedJars in Compile ++=
        (file("/usr/local/share/java/") * "zmq.jar").classpath
)

lazy val root = (project in file(".")).aggregate(client, server)

lazy val client = (project in file("client")).settings(common: _*).
    settings(
        name := "client",
        mainClass in Compile := Some("Main")
    )

lazy val server = (project in file("server")).settings(common: _*).
    settings(
        name := "server",
        mainClass in Compile := Some("Main")
    )
