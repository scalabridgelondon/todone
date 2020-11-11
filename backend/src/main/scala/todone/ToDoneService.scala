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
        val tasks =
          Tasks(
            List(
              Id(1) -> Task(
                State.open,
                "Play with the ToDone interface",
                "",
                None,
                Tags.empty),
              Id(2) -> Task(
                State.open,
                "Implement functionality to close a completed task",
                "",
                None,
                Tags.empty)
            )
          )
        Ok(tasks)
    }
}
