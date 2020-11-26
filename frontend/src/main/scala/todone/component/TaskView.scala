package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.web.html._

import todone.Style
import todone.data._
import todone.data.State.Open
import todone.data.State.Closed

@react object TaskView {
  val roundedBox = Style(
    "flex",
    "justify-center",
    "items-center",
    "w-6",
    "h-6",
    "mr-2"
  ) ++ Styles.border

  def checkbox(id: Id, state: State, close: Id => Unit): ReactElement =
    state match {
      case Open =>
        div(
          className := (roundedBox ++ Styles.borderHoverAccent).toString,
          onClick := (() => close(id))
        )(
          span(
            className := ("opacity-0 hover:opacity-100" +: Styles.textHoverAccent).toString
          )("✓")
        )

      case Closed =>
        div(
          className := roundedBox.toString
        )(
          span(className := Styles.textDimmed.toString)("✓")
        )
    }

  case class Props(id: Id, task: Task, close: Id => Unit)
  val component = FunctionalComponent[Props] { props =>
    val task = props.task

    div(className := "flex justify-start items-center bg-white px-4 py-2")(
      checkbox(props.id, task.state, props.close),
      task.state match {
        case Open =>
          h3(task.title)
        case Closed =>
          h3(className := ("line-through" +: Styles.textDimmed).toString)(
            task.title
          )
      }
    )
  }
}
