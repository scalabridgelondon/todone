package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Tasks(tasks: List[(Id, Task)])
object Tasks {
  val empty = Tasks(List.empty)

  implicit val tasksDecoder: Decoder[Tasks] = deriveDecoder[Tasks]
  implicit val tasksEncoder: Encoder[Tasks] = deriveEncoder[Tasks]
}
