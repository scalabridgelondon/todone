package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import todone.Style
import todone.data

@react object TagsView {

  val tagStyle =
    Style(
      "list-none",
      "inline-block",
      "text-sm",
      "p-2",
      "mr-2",
      "mb-2"
    ) ++ Styles.border

  case class Props(tags: data.Tags, removeTag: data.Tag => ())
  val component = FunctionalComponent[Props] { props =>
    ul(className := "flex")(
      props.tags.tags.map(t =>
        li(key := t.name, className := "list-none flex-grow-0")(
          TagView(tag = t, onClose = () => props.removeTag(t))
        )
      )
    )
  }
}
