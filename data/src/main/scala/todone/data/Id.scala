package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Id(id: Int)
object Id {
  implicit val idDecoder: Decoder[Id] = deriveDecoder[Id]
  implicit val idEncoder: Encoder[Id] = deriveEncoder[Id]
}
