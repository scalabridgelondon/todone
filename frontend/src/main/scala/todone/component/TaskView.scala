package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.ReactElement
import slinky.web.html._
// import slinky.web.svg.{width, height}

import todone.{MaterialIcons, Style}
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
    val (expanded, updateExpanded) = useState(false)

    val task = props.task

    div(className := "bg-white px-4 py-2")(
      div(className := "flex justify-start items-baseline")(
        checkbox(props.id, task.state, props.close),
        div(onClick := (() => updateExpanded(e => !e)),
            className := "flex flex-grow justify-between items-center")(
          task.state match {
            case Open =>
              h3(className := "flex-grow-1 mr-2")(task.title)
            case Closed =>
              h3(className := ("mr-2" +: "line-through" +: Styles.textDimmed).toString)(
                task.title
              )
          },
          MaterialIcons.IconContext.Provider(value = MaterialIcons.IconConfiguration.empty.withSize("1.75rem"))(
            if(expanded) MaterialIcons.MdExpandLess()
            else MaterialIcons.MdExpandMore()
          )
        )
      ),
      if(expanded)
        div(className := "mt-4 mx-8")(
          p(task.description),
          task.project.map(project => project.name),
        )
      else div()
    )
  }
}
