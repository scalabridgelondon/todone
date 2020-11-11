package todone

import cats.effect._
import cats.implicits._
import fs2.Stream
import org.http4s._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.{Router, Server}
import org.http4s.server.middleware.CORS
import org.http4s.implicits._

/**
 * This object setups and runs the webserver.
 */
object Main extends IOApp {
  private def app(blocker: Blocker): HttpApp[IO] = {
    val services =
      (CORS(ToDoneService.service) <+> AssetService.service(blocker))
    Router("/" -> services).orNotFound
  }

  private def server(blocker: Blocker): Resource[IO, Server] =
    EmberServerBuilder
      .default[IO]
      .withHost("0.0.0.0")
      .withPort(3000)
      .withHttpApp(app(blocker))
      .build

  private val program: Stream[IO, Unit] =
    for {
      blocker <- Stream.resource(Blocker[IO])
      server <- Stream.resource(server(blocker))
      _ <- Stream.eval(IO(println(s"Started server on: ${server.address}")))
      _ <- Stream.never[IO].covaryOutput[Unit]
    } yield ()

  override def run(args: List[String]): IO[ExitCode] =
    program.compile.drain.as(ExitCode.Success)
}
