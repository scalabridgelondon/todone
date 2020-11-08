package todone

import cats.effect._
import org.http4s.HttpRoutes
import org.http4s.server.staticcontent._

/**
 * This service is responsible for serving static files. We serve anything found
 * in the src/main/resources/todone subdirectory, which includes the user
 * interface.
 */
object AssetService {
  def service(blocker: Blocker)(implicit cs: ContextShift[IO]): HttpRoutes[IO] =
    fileService(FileService.Config("./backend/assets/", blocker))
}
