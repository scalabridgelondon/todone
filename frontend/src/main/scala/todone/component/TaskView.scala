package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._

import todone.data._
import todone.data.State.Open
import todone.data.State.Closed

@react object TaskView {
  case class Props(id: Id, task: Task, close: Id => Unit)
  val component = FunctionalComponent[Props] { props =>
    val task = props.task

    div(className := "flex justify-start items-center bg-white px-4 py-2")(
      div(className := "bg-white border-2 rounded border-gray-400 w-6 h-6 mr-2 hover:border-blue-500",
          onClick := (() => props.close(props.id))),
      task.state match {
	      case Open => div(h3(task.title))
	      case Closed => div(h3(className := "line-through text-gray-600")(task.title))
      }
    )
  }
}
