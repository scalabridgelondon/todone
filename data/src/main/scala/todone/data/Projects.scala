package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Projects(projects: List[Project])
object Projects {
  val empty = Projects(List.empty)

  implicit val projectsDecoder: Decoder[Projects] = deriveDecoder[Projects]
  implicit val projectsEncoder: Encoder[Projects] = deriveEncoder[Projects]
}
