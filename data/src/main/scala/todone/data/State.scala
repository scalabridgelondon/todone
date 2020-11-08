package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

sealed trait State
object State {
  case object Open extends State
  case object Closed extends State

  val open: State = Open
  val closed: State = Closed

  implicit val stateDecoder: Decoder[State] = deriveDecoder[State]
  implicit val stateEncoder: Encoder[State] = deriveEncoder[State]
}
