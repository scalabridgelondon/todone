package todone

import cats.effect._
import org.http4s.HttpRoutes
import org.http4s.server.staticcontent._
import scala.util.Properties

/**
  * This service is responsible for serving static files. We serve anything
  * found in the directory specified by the "todone.assets" systems property,
  * falling back to "./assets/" if that property is not set.
  */
object AssetService {
  val assetDirectory = Properties.propOrElse("todone.assets", "./assets/")

  def service(blocker: Blocker)(implicit cs: ContextShift[IO]): HttpRoutes[IO] =
    fileService(FileService.Config(assetDirectory, blocker))
}
