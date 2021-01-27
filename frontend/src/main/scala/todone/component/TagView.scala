package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import todone.{MaterialIcons, Style}
import todone.data

@react object TagView {

  val tagStyle =
    Style(
      "inline-block",
      "text-sm",
      "p-2",
      "mr-2",
      "mb-2",
      "flex",
      "items-center"
    ) ++ Styles.border

  case class Props(tag: data.Tag, onClose: () => Unit)
  val component = FunctionalComponent[Props] { props =>
    div(className := tagStyle.toString)(
      MaterialIcons
        .MdClose(className := "flex-grow-0 mr-1", onClick := props.onClose),
      span(className := "flex-grow-0")(props.tag.name)
    )
  }
}
