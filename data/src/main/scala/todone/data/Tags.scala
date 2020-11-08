package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Tags(tags: List[Tag])
object Tags {
  val empty = Tags(List.empty)

  implicit val tagsDecoder: Decoder[Tags] = deriveDecoder[Tags]
  implicit val tagsEncoder: Encoder[Tags] = deriveEncoder[Tags]
}
