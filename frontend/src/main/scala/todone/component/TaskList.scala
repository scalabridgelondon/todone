package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import todone.data._

@react object TaskList {
  case class Props(tasks: Tasks, close: Id => Unit)
  val component = FunctionalComponent[Props] { props =>
    ul(
      props.tasks.tasks.map {
        case (id, task) =>
          li(className := "pb-1", key := id.id.toString)(
            TaskView(id = id, task = task, close = props.close)
          )
      }
    )
  }
}
