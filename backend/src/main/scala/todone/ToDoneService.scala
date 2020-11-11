package todone

import cats.effect.IO
import org.http4s.HttpRoutes

import todone.data._

/**
 * This service provides the API for the user interface. Data is sent over HTTP
 * as JSON.
 */
object ToDoneService {
  import org.http4s.dsl.io._
  import org.http4s.circe.CirceEntityEncoder._
  object Description extends QueryParamDecoderMatcher[String]("description")

  val service: HttpRoutes[IO] =
    HttpRoutes.of[IO]{
      case GET -> Root / "api" / "tasks" =>
        Ok(Controller.tasks)

      case POST -> Root / "api" / "task" / IntVar(id) / "close" =>
        Ok(Controller.close(Id(id)))
    }
}
