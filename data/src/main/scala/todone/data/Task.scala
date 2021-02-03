package todone.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Task(
  state: State,
  title: String,
  description: String,
  project: Option[Project],
  tags: Tags
) {
  def close: Task =
    this.copy(state = State.closed)

  def isClosed: Boolean =
    state == State.closed

  def isOpen: Boolean =
    state == State.open
}
object Task {
  implicit val taskDecoder: Decoder[Task] = deriveDecoder[Task]
  implicit val taskEncoder: Encoder[Task] = deriveEncoder[Task]
}
