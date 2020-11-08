package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Tag(name: String)
object Tag {
  implicit val tagDecoder: Decoder[Tag] = deriveDecoder[Tag]
  implicit val tagEncoder: Encoder[Tag] = deriveEncoder[Tag]
}
