package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Project(name: String)
object Project {
  implicit val projectDecoder: Decoder[Project] = deriveDecoder[Project]
  implicit val projectEncoder: Encoder[Project] = deriveEncoder[Project]
}
