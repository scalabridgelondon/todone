name := "todone"

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / useSuperShell := false

val catsVersion = "2.2.0"
val circeVersion = "0.13.0"
val http4sVersion = "1.0.0-M6"
val logbackVersion = "1.2.3"

val sharedSettings = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %%% "cats-core"     % catsVersion,
    "io.circe"      %%% "circe-core"    % circeVersion,
    "io.circe"      %%% "circe-generic" % circeVersion,
    "io.circe"      %%% "circe-parser"  % circeVersion,
    "org.scalameta" %%% "munit"         % "0.7.17" % Test
  ),
  scalacOptions ++= Seq(
    "-Ymacro-annotations",
    "-Wunused:imports",
    "-Werror"
  ),
  testFrameworks += new TestFramework("munit.Framework")
)

lazy val data = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("data"))
  .settings(sharedSettings)

lazy val backend = project
  .in(file("backend"))
  .settings(
    sharedSettings,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "ch.qos.logback" %  "logback-classic" % logbackVersion,
    )
  )
  .dependsOn(data.jvm)

lazy val frontend = project
  .in(file("frontend"))
  .settings(
    sharedSettings,
    Compile / npmDependencies ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1",
      "react-proxy" -> "1.1.8"
    ),
    Compile / npmDevDependencies ++= Seq(
      "file-loader" -> "6.0.0",
      "style-loader" -> "1.2.1",
      "css-loader" -> "3.5.3",
      "html-webpack-plugin" -> "4.3.0",
      "copy-webpack-plugin" -> "5.1.1",
      "webpack-merge" -> "4.2.2",
      "postcss-loader" -> "4.0.0",
      "postcss" -> "7.0.32",
      "tailwindcss" -> "1.9.0",
      "autoprefixer" -> "9.8.6",
      "react-icons" -> "3.11.0"
    ),
    libraryDependencies ++= Seq(
      "me.shadaj" %%% "slinky-web" % "0.6.6",
      "me.shadaj" %%% "slinky-hot" % "0.6.6",
      "org.scala-js" %%% "scalajs-dom" % "1.0.0"
    ),
    webpack / version := "4.43.0",
    startWebpackDevServer / version := "3.11.0",
    webpackResources := baseDirectory.value / "webpack" * "*",
    fastOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js"),
    fastOptJS / webpackDevServerExtraArgs := Seq("--inline", "--hot"),
    fastOptJS / webpackBundlingMode := BundlingMode.LibraryOnly(),
    fullOptJS / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js"),
    Test / webpackConfigFile := Some(baseDirectory.value / "webpack" / "webpack-core.config.js"),
    Test / requireJsDomEnv := true
  )
  .enablePlugins(ScalaJSBundlerPlugin)
  .dependsOn(data.js)

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")
addCommandAlias("build", "fullOptJS::webpack")
