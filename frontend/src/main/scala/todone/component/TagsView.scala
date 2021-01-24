package todone.component

import slinky.core._
import slinky.core.annotations.react
import slinky.web.html._
import todone.Style
import todone.data

@react object TagsView {

  val tagStyle =
    Style("list-none", "inline-block", "text-sm", "p-2", "mr-2", "mb-2") ++ Styles.border

  val component = FunctionalComponent[List[data.Tag]] { tags =>
    ul(tags.map(t => li(key := t.name, className := tagStyle.toString)(t.name)))
  }
}
