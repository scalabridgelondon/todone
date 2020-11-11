package todone

import scala.collection.mutable
import todone.data._

object Model {
  val tasks: mutable.LinkedHashMap[Id, Task] =
    mutable.LinkedHashMap(
      Id(1) -> Task(
        State.open,
        "Play with the ToDone interface",
        "",
        None,
        Tags.empty),

      Id(2) -> Task(
        State.open,
        "Implement functionality to close a completed task",
        "",
        None,
        Tags.empty)
    )
}
