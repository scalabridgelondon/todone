package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import todone.Style
import todone.data

@react object ProjectView {

  val projectStyle =
    Style(
      "inline-block",
      "py-0.5",
      "px-1",
      "text-sm",
      "font-bold",
      "text-white",
      "border-2",
      "rounded",
      "border-blue-500",
      "bg-blue-400",
      "flex",
      "items-center"
    ) ++ Styles.border

  case class Props(project: data.Project)
  val component = FunctionalComponent[Props] { props =>
    div(className := projectStyle.toString)(
      span(className := "flex-grow-0")(props.project.name)
    )
  }
}
