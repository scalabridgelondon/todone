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
                """Right now many things in the application are not working.
                  |Our task is to make it work, and the first step to that is
                  |finding what is needs to be fixed.""".stripMargin,
                Some(Project("todone")),
                Tags(List(Tag("scalabridge"), Tag("frontend")))),

              Id(2) -> Task(
                State.open,
                "Learn how to use the web developer tools",
                """The web developer tools are one of the most useful tools for
                  |debugging problems between the frontend and backend. We need
                  |open up the web developer tools and look at the network pane,
                  |where we'll find requests from the frontend that the backend
                  |is not properly responding to.""".stripMargin,
                Some(Project("todone")),
                Tags(List(Tag("scalabridge"), Tag("frontend")))
              ),

              Id(3) -> Task(
                State.open,
                "Implement functionality to close a completed task",
                """The close button is probably the simplest bit of functionality
                  |we can implement. (Hopefully you discovered the close button
                  |doesn't work.) Let's do that now! The worksheets have more
                  |details.""".stripMargin,
                Some(Project("todone")),
                Tags(List(Tag("scalabridge"), Tag("backend")))),

              Id(4) -> Task(
                State.open,
                "Have a break!",
                "We've done a lot. Time for a break.",
                None,
                Tags(List(Tag("chillout")))
              )
            )
          )
        Ok(tasks)
    }
}
