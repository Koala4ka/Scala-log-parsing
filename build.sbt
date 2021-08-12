name := "aws"

version := "1.0"

lazy val `aws` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.9"


libraryDependencies ++= Seq(guice,
  "org.reactivemongo" % "play2-reactivemongo_2.12" % "1.0.3-play28",
  "io.monix" %% "monix" % "3.3.0",

  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.scalamock" %% "scalamock" % "5.1.0" % Test

)

      